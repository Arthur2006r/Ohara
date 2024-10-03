import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { MangaService } from 'src/app/services/manga.service';
import { MarcarCurtidaService } from 'src/app/services/marcar-curtida.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-visualizar-manga',
  templateUrl: './visualizar-manga.page.html',
  styleUrls: ['./visualizar-manga.page.scss'],
})
export class VisualizarMangaPage implements OnInit {

  manga: any;
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

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const mangaId = params.get('id');
      if (mangaId) {
        const idManga = +mangaId;
        this.mangaService.consultarMangaPorId(idManga)
          .then(data => {
            this.manga = data;
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
  }
  
}
