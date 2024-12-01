import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curtida } from '../model/curtida';
import { firstValueFrom } from 'rxjs';
import { MangaService } from './manga.service'; // Importa o serviço de manga

@Injectable({
  providedIn: 'root'
})
export class CurtidaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/curtida';

  constructor(private httpClient: HttpClient, private mangaService: MangaService) { }

  async salvar(curtida: Curtida): Promise<Curtida> {
    return await firstValueFrom(this.httpClient.post<Curtida>(this.url, JSON.stringify(curtida), this.httpHeaders));
  }
  async consultarMinhaCurtida(idManga: number | null, idUsuario: number | null): Promise<Curtida> {
    let urlAuxiliar = this.url + "/" + "minha" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Curtida>(urlAuxiliar));
  }

  async excluir(idManga: number | null, idUsuario: number | null): Promise<Curtida> {
    if (!idManga || !idUsuario) {
      throw new Error('Parâmetros inválidos para exclusão.');
    }
    const urlAuxiliar = `${this.url}/${idUsuario}/${idManga}`;
    return await firstValueFrom(this.httpClient.delete<Curtida>(urlAuxiliar));
  }
  
  async consultarCurtidasDoUsuario(idUsuario: number): Promise<any[]> {

    const urlAuxiliar = `${this.url}/usuario/${idUsuario}`;
    const curtidas = await firstValueFrom(this.httpClient.get<Curtida[]>(urlAuxiliar));
    const curtidasComMangas = await Promise.all(
      curtidas.map(async (curtida) => {
        if (curtida.idManga !== null) {  
          const manga = await this.mangaService.buscarPorId(curtida.idManga); // 
          return { curtida, manga }; 
        } else {
          
          return {curtida, manga: null };
        }
      })
    );

    return curtidasComMangas;
  }
}

