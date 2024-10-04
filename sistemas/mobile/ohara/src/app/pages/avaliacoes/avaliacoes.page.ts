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
  idManga: number;
  avaliacoes: Avaliacao[];
  valorSegment: string;

  constructor(private avaliacaoService: AvaliacaoService, private usuarioService: UsuarioService, private mangaService: MangaService, private activatedRoute: ActivatedRoute, private loadingController: LoadingController) {
    this.idUsuario = 0;
    this.idManga = 0;
    this.avaliacoes = [];
    this.valorSegment = 'todos';
  }

  ngOnInit() {
    let idUsuario = this.usuarioService.recuperarAutenticacao();

    if (idUsuario !== 0) {
      let idManga = parseFloat(this.activatedRoute.snapshot.params['idManga']);
      if (idManga === 0) {
        console.warn('ID do mangá não encontrado');
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
      await this.avaliacaoService.listarSeguidosManga(this.idManga, this.idUsuario).then((json) => {
        this.avaliacoes = <Avaliacao[]>(json);
      });
    } else {
      await this.avaliacaoService.listarTodosManga(this.idManga).then((json) => {
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
