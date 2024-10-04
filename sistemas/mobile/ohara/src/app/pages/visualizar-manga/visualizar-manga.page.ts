import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { MangaService } from 'src/app/services/manga.service';
<<<<<<< HEAD
import { CurtidaService } from 'src/app/services/curtida.service';
import { Curtida } from 'src/app/model/curtida';
import { UsuarioService } from 'src/app/services/usuario.service';
import { LerDepois } from 'src/app/model/ler-depois';
import { LerDepoisService } from 'src/app/services/ler-depois.service';
import { Visto } from 'src/app/model/visto';
import { VistoService } from 'src/app/services/visto.service';
import { Avaliacao } from 'src/app/model/avaliacao';
import { AvaliacaoService } from 'src/app/services/avaliacao.service';
=======
import { MarcarCurtidaService } from 'src/app/services/marcar-curtida.service';
import { UsuarioService } from 'src/app/services/usuario.service';

>>>>>>> ab838e80440377587cf37d6f13f28d0958fc1392
@Component({
  selector: 'app-visualizar-manga',
  templateUrl: './visualizar-manga.page.html',
  styleUrls: ['./visualizar-manga.page.scss'],
})
export class VisualizarMangaPage implements OnInit {

  manga: any;
<<<<<<< HEAD
  idUsuario: number;

  isExpanded = false;

  avaliacao: Avaliacao;
  curtida: Curtida;
  lerDepois: LerDepois;
  visto: Visto;

  constructor(private avaliacaoService: AvaliacaoService, private curtidaService: CurtidaService, private lerDepoisService: LerDepoisService, private vistoService: VistoService, private usuarioService: UsuarioService, private navController: NavController, private route: ActivatedRoute, private mangaService: MangaService) {
    this.idUsuario = 0;
    this.avaliacao = new Avaliacao();
    this.curtida = new Curtida();
    this.lerDepois = new LerDepois();
    this.visto = new Visto();
  }
=======
  isExpanded = false;
  isFavorite = false;
  idUsuario!: number; 

  constructor(
    private navController: NavController,
    private route: ActivatedRoute,
    private mangaService: MangaService,
    private marcarCurtidaService: MarcarCurtidaService,
    private usuarioService: UsuarioService
  ) { }
>>>>>>> ab838e80440377587cf37d6f13f28d0958fc1392

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    this.route.paramMap.subscribe(params => {
      const mangaId = params.get('id');
      if (mangaId) {
        const idManga = +mangaId;
        this.mangaService.buscarPorId(idManga)
          .then(data => {
            this.manga = data;

            this.recuperarAvaliacao();
            this.recuperarCurtida();
            this.recuperarLerDepois();
            this.recuperarVisto();
          })
          .catch(error => {
            console.error('Erro ao buscar mangá', error);
          });
      } else {
        console.error('Id do mangá não encontrado na rota.');
      }
    });

    this.idUsuario = this.usuarioService.recuperarAutenticacao();
  }

  recuperarAvaliacao() {
    this.avaliacaoService.consultarMinhaAvaliacao(this.manga.idManga, this.idUsuario)
      .then((json) => {
        this.avaliacao = <Avaliacao>json;
      })
      .catch((erro) => {
        console.error('Erro ao buscar curtida:', erro);
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

  voltar() {
    this.navController.back();
  }

  getCapa(): string {
    return this.manga?.capa;
  }

  get Sinopse() {
    return this.isExpanded ? this.manga.sinopse : this.manga.sinopse.substring(0, this.manga.sinopse.lastIndexOf(' ', 140)) + '...';
  }

  expandir() {
    this.isExpanded = !this.isExpanded;
  }

<<<<<<< HEAD
  marcarCurtida() {
    if (this.curtida.idManga === 0) {
      this.curtida.idManga = this.manga.idManga;
      this.curtida.idUsuario = this.idUsuario;
      this.curtidaService.salvar(this.curtida);
    } else {
      this.curtidaService.excluir(this.manga.idManga, this.idUsuario);
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
    } else {
      this.vistoService.excluir(this.manga.idManga, this.idUsuario);
    }
  }

  avaliar() {
    this.avaliacao.idManga = this.manga.idManga;
    this.visto.idUsuario = this.idUsuario;
    // this.avaliacao.nota = *nota do input*;
    this.vistoService.salvar(this.avaliacao);
=======
  favoritar() {
    if (!this.isFavorite) {
      this.marcarCurtidaService.favoritar(this.idUsuario, this.manga.idManga)
        .then(() => {
          this.isFavorite = true;
          console.log('Mangá favoritado!');
        })
        .catch(error => {
          console.error('Erro ao favoritar o mangá', error);
        });
    } else {
      this.marcarCurtidaService.desfavoritar(this.idUsuario, this.manga.idManga)
        .then(() => {
          this.isFavorite = false;
          console.log('Mangá desfavoritado!');
        })
        .catch(error => {
          console.error('Erro ao desfavoritar o mangá', error);
        });
    }
>>>>>>> ab838e80440377587cf37d6f13f28d0958fc1392
  }
  
}
