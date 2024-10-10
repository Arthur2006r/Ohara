import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { Manga } from 'src/app/model/manga';
import { MangaService } from 'src/app/services/manga.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  mangas: Manga[];

  constructor(private router: Router, private mangaService: MangaService, private loadingController: LoadingController) {
    this.mangas = [];
  }

  ngOnInit() {
    this.carregarMangas();
  }

  async carregarMangas() {
    this.exibirLoader();
    this.mangaService.consultarTop10()
      .then((json) => {
        this.mangas = json;
      })
      .catch((error) => {
        console.error('Erro ao buscar mangás', error);
      });
    this.fecharLoader();
  }

  getTitulo(manga: any): string {
    return manga?.titulo;
  }

  getCapa(manga: any): string {
    return manga?.capa
  }

  verMais(): any {
    console.log("teste")
  }

  onMangaClick(idManga: number | null): void {
    this.router.navigate(['/visualizar-manga', idManga]);
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
