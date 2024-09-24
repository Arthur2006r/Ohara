import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { MangaService } from 'src/app/services/manga.service';
@Component({
  selector: 'app-visualizar-manga',
  templateUrl: './visualizar-manga.page.html',
  styleUrls: ['./visualizar-manga.page.scss'],
})
export class VisualizarMangaPage implements OnInit {

  manga: any;

  isExpanded = false;
  
  isFavorite = false;

  constructor(private navController: NavController, private route: ActivatedRoute, private mangaService: MangaService) { }

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
  }

  voltar() {
    this.navController.back();
  }

  getCapa(): string {
    return this.manga?.capa;
  }

  get Sinopse() {
    return this.isExpanded ? this.manga.sinopse : this.manga.sinopse.substring(0, this.manga.sinopse.lastIndexOf(' ', 140)) + '...'; // Mostra até x caracteres da sinopse
  }

  expandir() {
    this.isExpanded = !this.isExpanded; // Expandir, não expandir
  }

  favoritar() {
    this.isFavorite = !this.isFavorite; // Alterna o estado de favorito
  }
}


