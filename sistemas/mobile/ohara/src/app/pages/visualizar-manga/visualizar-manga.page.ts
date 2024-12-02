import { Component, EventEmitter, OnInit } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { MangaService } from 'src/app/services/manga.service';
import { CurtidaService } from 'src/app/services/curtida.service';
import { Curtida } from 'src/app/model/curtida';
import { UsuarioService } from 'src/app/services/usuario.service';
import { LerDepois } from 'src/app/model/ler-depois';
import { LerDepoisService } from 'src/app/services/ler-depois.service';
import { Visto } from 'src/app/model/visto';
import { VistoService } from 'src/app/services/visto.service';
import { Avaliacao } from 'src/app/model/avaliacao';
import { AvaliacaoService } from 'src/app/services/avaliacao.service';
import { Manga } from 'src/app/model/manga';
import { Router } from '@angular/router';
import { text } from 'ionicons/icons';

@Component({
  selector: 'app-visualizar-manga',
  templateUrl: './visualizar-manga.page.html',
  styleUrls: ['./visualizar-manga.page.scss'],
})
export class VisualizarMangaPage implements OnInit {

  manga: Manga;
  idUsuario: number;

  isExpanded = false;
  isCurtido = false;

  avaliacao: Avaliacao;
  curtida: Curtida;
  lerDepois: LerDepois;
  visto: Visto;

  estrelas: number[] = [];

  nota: string;

  constructor(private avaliacaoService: AvaliacaoService, private curtidaService: CurtidaService, private lerDepoisService: LerDepoisService, private vistoService: VistoService, private usuarioService: UsuarioService, private navController: NavController, private route: ActivatedRoute, private mangaService: MangaService, private activatedRoute: ActivatedRoute, private router: Router, private alertController: AlertController, private toastController: ToastController) {
    this.manga = new Manga();
    this.idUsuario = 0;
    this.avaliacao = new Avaliacao();
    this.curtida = new Curtida();
    this.lerDepois = new LerDepois();
    this.visto = new Visto();
    this.nota = "0,0";

    this.estrelas = Array(5).fill(0);
  }
  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    let idManga = parseFloat(this.activatedRoute.snapshot.params['idManga']);

    if (!isNaN(idManga)) {
      this.mangaService.buscarPorId(idManga)
        .then((json) => {
          this.manga = <Manga>json;

          this.recuperarAvaliacao();
          this.recuperarCurtida();
          this.recuperarLerDepois();
          this.recuperarVisto();

          this.calcularNota();
        })
        .catch(error => {
          console.error('Erro ao buscar mangá', error);
        });
    } else {
      console.error('Id do mangá não encontrado na rota.');
    }
    this.idUsuario = this.usuarioService.recuperarAutenticacao();
  }

  /* public actionSheetButtons = [
    {
      text: this.isCurtido ? 'Descurtir' : 'Curtir',
      icon: this.isCurtido ? 'heart' : 'heart-outline',
      handler: () =>
        this.marcarCurtida()
    },

    {
      text: 'Marcar como Lido',
      icon: 'eye',
      handler: () =>
        this.marcarVisto()
    },

    {
      text: 'Ler Depois',
      icon: 'alarm-outline',
      handler: () =>
        this.marcarLerDepois()
    },

  

    {
      text: 'Voltar',
      role: 'cancel',
      data: {
        action: 'cancel',
      },
    },
  ];
  */

  recuperarAvaliacao() {
    this.avaliacaoService.consultarMinhaAvaliacao(this.manga.idManga, this.idUsuario)
      .then((json) => {
        this.avaliacao = <Avaliacao>json;
      })
      .catch((erro) => {
        console.error('Erro ao buscar avaliacao:', erro);
      });
  }

  recuperarCurtida() {
    this.curtidaService.consultarMinhaCurtida(this.manga.idManga, this.idUsuario)
      .then((json) => {
        this.curtida = <Curtida>json;
      })
      .catch((erro) => {
        console.error('Erro ao buscar curtida:', erro);
      });
  }

  recuperarLerDepois() {
    this.lerDepoisService.consultarMeuLerDepois(this.manga.idManga, this.idUsuario)
      .then((json) => {
        this.lerDepois = <LerDepois>json;
      })
      .catch((erro) => {
        console.error('Erro ao buscar ler depois:', erro);
      });
  }

  recuperarVisto() {
    this.vistoService.consultarMeuLido(this.manga.idManga, this.idUsuario)
      .then((json) => {
        this.visto = <Visto>json;
      })
      .catch((erro) => {
        console.error('Erro ao buscar visto:', erro);
      });
  }

  calcularNota() {
    this.mangaService.calcularNota(this.manga.idManga)
      .then((json) => {
        let valor = <number>json;
        this.nota = valor.toFixed(1).replace('.', ',');
      })
      .catch((erro) => {
        console.error('Erro ao buscar nota:', erro);
      });
  }

  getCapa(): string {
    return this.manga.capa;
  }

  get Sinopse() {
    return this.isExpanded ? this.manga.sinopse : this.manga.sinopse.substring(0, this.manga.sinopse.lastIndexOf(' ', 140)) + '...';
  }

  expandir() {
    this.isExpanded = !this.isExpanded;
  }

  marcarCurtida() {
    if (this.curtida.idManga === 0) {
      this.curtida.idManga = this.manga.idManga;
      this.curtida.idUsuario = this.idUsuario;
      this.curtidaService.salvar(this.curtida);
      this.isCurtido = true;
    } else {
      this.curtidaService.excluir(this.manga.idManga, this.idUsuario);
      this.isCurtido = false;
    }
  }

  marcarLerDepois() {
    if (this.lerDepois.idManga === 0) {
      this.lerDepois.idManga = this.manga.idManga;
      this.lerDepois.idUsuario = this.idUsuario;
      this.lerDepoisService.salvar(this.lerDepois);
    } else {
      this.lerDepoisService.excluir(this.manga.idManga, this.idUsuario);
    }
  }

  marcarVisto() {
    if (this.visto.idManga === 0) {
      this.visto.idManga = this.manga.idManga;
      this.visto.idUsuario = this.idUsuario;
      this.vistoService.salvar(this.visto);
      this.lerDepoisService.excluir(this.manga.idManga, this.idUsuario);
    } else {
      this.vistoService.excluir(this.manga.idManga, this.idUsuario);
    }
  }

  avaliar(nota: number) {
    const avaliacaoCorreta: Avaliacao = {
      idManga: this.manga.idManga,
      idUsuario: this.idUsuario,
      nota: nota,
    };

    this.avaliacaoService.salvar(avaliacaoCorreta)
      .then(() => {
        console.log('Avaliação salva com sucesso!');
        this.visto.idManga = this.manga.idManga;
        this.visto.idUsuario = this.idUsuario;
        this.vistoService.salvar(this.visto);
        this.lerDepoisService.excluir(this.manga.idManga, this.idUsuario);
      })
      .catch((error) => {
        console.error('Erro ao salvar avaliação:', error);
      });
  }

  addReview(idManga: number | null): void {
    this.router.navigate(['/add-review', idManga]);
  }

  abrirAvaliacoes(idManga: number | null): void {
    this.router.navigate(['/avaliacoes', idManga]);
  }

  abrirReviews(idManga: number | null): void {
    this.router.navigate(['/reviews', idManga]);
  }

  getCorEstrela(index: number): string {
    const nota = this.avaliacao.nota - index;
    if (nota >= 1) return 'warning';
    return 'medium';
  }

  async excluirAvaliacao() {
    const alert = await this.alertController.create({
      header: 'Você deseja excluir sua avaliação?',
      buttons: [
        {
          text: 'Cancelar'
        },
        {
          text: 'Excluir Avaliação',
          cssClass: 'danger',
          handler: () => {
            this.avaliacaoService.excluir(this.avaliacao.idUsuario, this.avaliacao.idManga)
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
