import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MangaService } from 'src/app/services/manga.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  mangas: any[] = []; 

  constructor(private router: Router, private mangaService: MangaService) { }

  ngOnInit() {
    this.mangaService.consultarTodos()
      .then((data) => {
        this.mangas = data; 
      })
      .catch((error) => {
        console.error('Erro ao buscar mangás', error);
      });
  }
  

  getTitulo(manga: any): string {
    return manga?.titulo; 
  }

  getCapa(manga: any): string{
    return manga?.capa
  }

  verMais(): any {
    console.log("teste")
  }

  onMangaClick(idManga: number): void {
    this.router.navigate(['/visualizar-manga', idManga]);
  }

  getTop20Mangas(): any[] {
    return this.mangas
      .sort((a, b) => a.popularidade - b.popularidade)
      .slice(0, 20); // Pega os 20 mangás mais populares
  }

}
