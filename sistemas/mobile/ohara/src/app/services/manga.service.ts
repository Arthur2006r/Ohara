import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manga } from '../model/manga';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MangaService {

  url: string = 'http://localhost:8087/api/v1/manga';

  constructor(private httpClient: HttpClient) { }

  async consultarTodos(): Promise<Manga[]> {
    return firstValueFrom(this.httpClient.get<Manga[]>(this.url));
  }

  async consultarCurtidosUsuario(idUsuario: number | null): Promise<Manga[]> {
    let urlAuxiliar = this.url + "/" + "curtidos" + "/" + idUsuario;
    return firstValueFrom(this.httpClient.get<Manga[]>(urlAuxiliar));
  }

  async consultarLerDepoisUsuario(idUsuario: number | null): Promise<Manga[]> {
    let urlAuxiliar = this.url + "/" + "lerDepois" + "/" + idUsuario;
    return firstValueFrom(this.httpClient.get<Manga[]>(urlAuxiliar));
  }

  async buscarPorId(idManga: number | null): Promise<Manga> {
    let urlAuxiliar = this.url + "/" + idManga;
    return await firstValueFrom(this.httpClient.get<Manga>(urlAuxiliar));
  }
 
}
