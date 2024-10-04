import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Manga } from 'src/app/model/manga';
import { Usuario } from 'src/app/model/usuario';
import { MangaService } from 'src/app/services/manga.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-associacoes-usuario-manga',
  templateUrl: './associacoes-usuario-manga.page.html',
  styleUrls: ['./associacoes-usuario-manga.page.scss'],
})
export class AssociacoesUsuarioMangaPage implements OnInit {

  associacao: string;
  usuario: Usuario;
  mangas: Manga[];

  constructor(private mangaService: MangaService, private usuarioService: UsuarioService, private loadingController: LoadingController, private activatedRoute: ActivatedRoute) {
    this.associacao = "";
    this.usuario = new Usuario();
    this.mangas = [];
  }

  ngOnInit() {
    this.associacao = this.activatedRoute.snapshot.params['associacao'];

    if (this.associacao !== "") {
      let idUsuario = this.usuarioService.recuperarAutenticacao();

      if (idUsuario) {
        this.usuarioService.buscarPorId(idUsuario)
          .then((json) => {
            this.usuario = <Usuario>json;
          })
          .catch((erro) => {
            console.error('Erro ao buscar usuário:', erro);
          });
      } else {
        console.warn('ID do usuário não encontrado');
      }
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
    if (this.associacao === 'Curtidos') {
      await this.mangaService.consultarCurtidosUsuario(this.usuario.idUsuario).then((json) => {
        this.mangas = <Manga[]>(json);
      });
    } else if (this.associacao === 'ParaLerDepois') {
      await this.mangaService.consultarLerDepoisUsuario(this.usuario.idUsuario).then((json) => {
        this.mangas = <Manga[]>(json);
      });
    }
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
