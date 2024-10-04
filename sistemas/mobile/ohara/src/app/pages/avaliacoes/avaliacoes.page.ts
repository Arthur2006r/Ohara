import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Avaliacao } from 'src/app/model/avaliacao';
import { Manga } from 'src/app/model/manga';
import { Usuario } from 'src/app/model/usuario';
import { AvaliacaoService } from 'src/app/services/avaliacao.service';
import { MangaService } from 'src/app/services/manga.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-avaliacoes',
  templateUrl: './avaliacoes.page.html',
  styleUrls: ['./avaliacoes.page.scss'],
})
export class AvaliacoesPage implements OnInit {

  idUsuario: number;
  manga: Manga;
  avaliacoes: Avaliacao[];
  valorSegment: string;

  constructor(private avaliacaoService: AvaliacaoService, private usuarioService: UsuarioService, private mangaService: MangaService, private activatedRoute: ActivatedRoute, private loadingController: LoadingController) {
    this.idUsuario = 0;
    this.manga = new Manga();
    this.avaliacoes = [];
    this.valorSegment = 'todos';
  }

  ngOnInit() {
    let idUsuario = this.usuarioService.recuperarAutenticacao();

    if (idUsuario !== 0) {
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
    } else {
      console.warn('ID do usuário não encontrado');
    }
  }

  async ionViewWillEnter() {
    this.carregarLista();
  }

  async carregarLista() {
    this.exibirLoader();
    this.filtraravaliacoes();
    this.fecharLoader();
  }

  async filtraravaliacoes() {
    if (this.valorSegment === 'seguidos') {
      await this.avaliacaoService.listarSeguidosManga(this.manga.idManga, this.idUsuario).then((json) => {
        this.avaliacoes = <Avaliacao[]>(json);
      });
    } else {
      await this.avaliacaoService.listarTodosManga(this.manga.idManga).then((json) => {
        this.avaliacoes = <Avaliacao[]>(json);
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
}
