import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ListaManga } from '../model/lista-manga';
import { firstValueFrom } from 'rxjs';
import { Manga } from '../model/manga';

@Injectable({
  providedIn: 'root'
})
export class listaMangaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/listaManga';


  constructor(private httpClient: HttpClient) { }

  async salvar(listaManga: ListaManga): Promise<ListaManga> {
      return await firstValueFrom(this.httpClient.post<ListaManga>(this.url, JSON.stringify(listaManga), this.httpHeaders));
  }

  async excluir(idLista: number | null, idManga: number | null): Promise<ListaManga> {
    let urlAuxiliar = this.url + "/" + idLista + "/" + idManga;
    return await firstValueFrom(this.httpClient.delete<ListaManga>(urlAuxiliar));
  }

  async consultarMangasLista(idLista: number | null): Promise<Manga[]> {
    let urlAuxiliar = this.url + "/" + idLista;
    return await firstValueFrom(this.httpClient.get<Manga[]>(urlAuxiliar));
  }
}
