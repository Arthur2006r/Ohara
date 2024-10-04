import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { Manga } from 'src/app/model/manga';
import { Review } from 'src/app/model/review';
import { MangaService } from 'src/app/services/manga.service';
import { ReviewService } from 'src/app/services/review.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.page.html',
  styleUrls: ['./add-review.page.scss'],
})
export class AddReviewPage implements OnInit {
  review: Review;
  idUsuario: number;
  manga: Manga;
  formGroup: FormGroup;

  constructor(
    private reviewService: ReviewService,
    private usuarioService: UsuarioService,
    private mangaService: MangaService,
    private formBuilder: FormBuilder,
    private toastController: ToastController,
    private navController: NavController,
    private activatedRoute: ActivatedRoute,
  ) {
    this.review = new Review();
    this.idUsuario = 0;
    this.manga = new Manga();
    this.formGroup = this.formBuilder.group({
      descricao: [this.review.descricao, Validators.required]
    });
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    if (this.idUsuario !== 0) {
      let idManga = parseFloat(this.activatedRoute.snapshot.params['idManga']);

      if (!isNaN(idManga)) {
        this.mangaService.buscarPorId(idManga)
          .then((json) => {
            this.manga = <Manga>json;

            let idReview = parseFloat(this.activatedRoute.snapshot.params['idReview']);
            if (!isNaN(idReview)) {
              this.reviewService.buscarPorId(idReview)
                .then((json) => {
                  this.review = <Review>json;
                  this.formGroup.get('descricao')?.setValue(this.review.descricao);
                })
                .catch((erro) => {
                  console.error('Erro ao buscar rerview:', erro);
                });
            }
          })
          .catch((erro) => {
            console.error('Erro ao buscar mangá:', erro);
          });
      }
    }
  }

  salvar() {
    this.review.idManga = this.manga.idManga;
    this.review.idUsuario = this.idUsuario;
    this.review.descricao = this.formGroup.value.descricao;

    this.reviewService.salvar(this.review)
    this.exibirreview('Registro salvo com sucesso!!!');
    this.navController.navigateBack('/visualizar-manga');
  }

  async exibirreview(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

}