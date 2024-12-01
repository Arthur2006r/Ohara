import { Injectable } from '@angular/core';
import { LerDepois } from '../model/ler-depois';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { MangaService } from './manga.service';

@Injectable({
  providedIn: 'root'
})
export class LerDepoisService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/lerDepois';


  constructor(private httpClient: HttpClient, private mangaService: MangaService) { }

  async salvar(lerDepois: LerDepois): Promise<LerDepois> {
    return await firstValueFrom(this.httpClient.post<LerDepois>(this.url, JSON.stringify(lerDepois), this.httpHeaders));
  }

  async listarTodos(): Promise<LerDepois[]> {
    return await firstValueFrom(this.httpClient.get<LerDepois[]>(this.url));
  }

  async consultarMeuLerDepois(idManga: number | null, idUsuario: number | null): Promise<LerDepois> {
    let urlAuxiliar = this.url + "/" + "meu" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<LerDepois>(urlAuxiliar));
  }

  async excluir(idManga: number | null, idUsuario: number | null): Promise<LerDepois> {
    if (!idManga || !idUsuario) {
      throw new Error('Parâmetros inválidos para exclusão.');
    }
    const urlAuxiliar = `${this.url}/${idUsuario}/${idManga}`;
    return await firstValueFrom(this.httpClient.delete<LerDepois>(urlAuxiliar));
  }

  async consultarLerDepoisDoUsuario(idUsuario: number): Promise<any[]> {
    const urlAuxiliar = `${this.url}/usuario/${idUsuario}`;
    const lerDepois = await firstValueFrom(this.httpClient.get<LerDepois[]>(urlAuxiliar));
    const lerDepoisMangas = await Promise.all( 
      lerDepois.map(async (lerDepois) => {
        if (lerDepois.idManga !== null) { 
          const manga = await this.mangaService.buscarPorId(lerDepois.idManga); 
          return { lerDepois, manga }; 
        } else {

          return { lerDepois, manga: null };
        }
      })
    );

    return lerDepoisMangas;
  }
}
