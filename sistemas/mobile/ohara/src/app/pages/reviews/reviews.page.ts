import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertController, LoadingController, ToastController } from '@ionic/angular';
import { Manga } from 'src/app/model/manga';
import { Review } from 'src/app/model/review';
import { MangaService } from 'src/app/services/manga.service';
import { ReviewService } from 'src/app/services/review.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.page.html',
  styleUrls: ['./reviews.page.scss'],
})
export class ReviewsPage implements OnInit {

  idUsuario: number;
  manga: Manga;
  reviews: Review[];
  valorSegment: string;

  constructor(private reviewService: ReviewService, private usuarioService: UsuarioService, private activatedRoute: ActivatedRoute, private loadingController: LoadingController, private mangaService: MangaService, private navController: NavController, private  alertController: AlertController, private toastController: ToastController) {
    this.idUsuario = 0;
    this.manga = new Manga();
    this.reviews = [];
    this.valorSegment = 'todos';
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    if (this.idUsuario !== 0) {
      let idManga = parseFloat(this.activatedRoute.snapshot.params['idManga']);

      if (idManga === 0) {
        console.warn('ID do mangá não encontrado');
      }
    } else {
      console.warn('ID do usuário não encontrado');
    }

    let idManga = parseFloat(this.activatedRoute.snapshot.params['idManga']);
    if (!isNaN(idManga)) {
      this.mangaService.buscarPorId(idManga)
        .then((json) => {
          this.manga = <Manga>json;
        })
        .catch(error => {
          console.error('Erro ao buscar mangá', error);
        });
    } else {
      console.error('Id do mangá não encontrado na rota.');
    }
  }

  async ionViewWillEnter() {
    this.carregarLista();
  }

  async carregarLista() {
    this.exibirLoader();
    this.filtrarReviews();
    this.fecharLoader();
  }

  async filtrarReviews() {
    if (this.valorSegment === 'meus') {
      await this.reviewService.listarMeusManga(this.manga.idManga, this.idUsuario).then((json) => {
        this.reviews = <Review[]>(json);
      });
    } else if (this.valorSegment === 'seguidos') {
      /*await this.reviewService.listarSeguidosManga(this.manga.idManga, this.idUsuario).then((json) => {
        this.reviews = <Review[]>(json);
      });*/
      this.reviews = [];
    } else {
      await this.reviewService.listarTodosManga(this.manga.idManga).then((json) => {
        this.reviews = <Review[]>(json);
      });
    }
  }

  segmentChanged(valorSegment: any) {
    this.valorSegment = valorSegment.detail.value;
    this.carregarLista();
  }

  exibirLoader() {
    this.loadingController.create({
      message: 'Carregando...'
    }).then((res) => {
      res.present();
    });
  }

  fecharLoader() {
    setTimeout(() => {
      this.loadingController.dismiss().then(() => {
      }).catch((erro) => {
        console.log('Erro: ', erro);
      });
    }, 500);
  }

  isUserReview(review: Review): boolean {
    return review.usuario?.idUsuario === this.idUsuario;
  }

  async excluirReview(review: Review) {
    const alert = await this.alertController.create({
      header: 'Você deseja excluir essa review?',
      buttons: [
        {
          text: 'Cancelar'
        },
        {
          text: 'Excluir Review',
          cssClass: 'danger',
          handler: () => {
            this.reviewService.excluir(review.idReview)
              .then(() => {
                this.exibirMensagem('Registro excluído com sucesso!!!');
              }).catch(() => {
                this.exibirMensagem('Erro ao excluir o registro.');
              });
          }
        }
      ]
    });

    await alert.present();
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present()
  }
}
