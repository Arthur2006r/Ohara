import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Visto } from '../model/visto';
import { firstValueFrom } from 'rxjs';
import { MangaService } from './manga.service';
import { Manga } from '../model/manga';

@Injectable({
  providedIn: 'root'
})
export class VistoService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/lido';


  constructor(private httpClient: HttpClient, private mangaService: MangaService) { }

  async salvar(visto: Visto): Promise<Visto> {
    return await firstValueFrom(this.httpClient.post<Visto>(this.url, JSON.stringify(visto), this.httpHeaders));
  }

  async listarTodos(): Promise<Visto[]> {
    return await firstValueFrom(this.httpClient.get<Visto[]>(this.url));
  }

  async consultarMeuLido(idManga: number | null, idUsuario: number | null): Promise<Visto> {
    let urlAuxiliar = this.url + "/" + "meu" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Visto>(urlAuxiliar));
  }

  async excluir(idManga: number | null, idUsuario: number | null): Promise<Visto> {
    if (!idManga || !idUsuario) {
      throw new Error('Parâmetros inválidos para exclusão.');
    }
    const urlAuxiliar = `${this.url}/${idUsuario}/${idManga}`;
    return await firstValueFrom(this.httpClient.delete<Visto>(urlAuxiliar));
  }

  async consultarLidoDoUsuario(idUsuario: number): Promise<any[]> {
    const urlAuxiliar = `${this.url}/usuario/${idUsuario}`;
    const lido = await firstValueFrom(this.httpClient.get<Visto[]>(urlAuxiliar));
    const lidoMangas = await Promise.all( 
      lido.map(async (lido) => {
        if (lido.idManga !== null) { 
          const manga = await this.mangaService.buscarPorId(lido.idManga); 
          return { lido, manga }; 
        } else {

          return { lido, manga: null };
        }
      })
    );

    return lidoMangas;
  }
}
