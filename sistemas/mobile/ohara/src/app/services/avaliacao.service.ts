import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Avaliacao } from '../model/avaliacao';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/avaliacao';


  constructor(private httpClient: HttpClient) { }

  async salvar(avaliacao: Avaliacao): Promise<Avaliacao> {
    // resolver essa lógica
    if (null) {
      return await firstValueFrom(this.httpClient.post<Avaliacao>(this.url, JSON.stringify(avaliacao), this.httpHeaders));
    } else {
      return await firstValueFrom(this.httpClient.put<Avaliacao>(this.url, JSON.stringify(avaliacao), this.httpHeaders));
    }
  }

  async listarTodos(): Promise<Avaliacao[]> {
    return await firstValueFrom(this.httpClient.get<Avaliacao[]>(this.url));
  }

  async listarTodosManga(idManga: number | null): Promise<Avaliacao[]> {
    let urlAuxiliar = this.url + "/" + "todos" + "/" + idManga;
    return await firstValueFrom(this.httpClient.get<Avaliacao[]>(urlAuxiliar));
  }

  async listarSeguidosManga(idManga: number | null, idUsuario: number | null): Promise<Avaliacao[]> {
    let urlAuxiliar = this.url + "/" + "seguidos" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Avaliacao[]>(urlAuxiliar));
  }

  async consultarMinhaAvaliacao(idManga: number | null, idUsuario: number | null): Promise<Avaliacao> {
    let urlAuxiliar = this.url + "/" + "minha" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Avaliacao>(urlAuxiliar));
  }

  async excluir(id: number | null): Promise<Avaliacao> {
    let urlAuxiliar = this.url + "/" + id;
    return await firstValueFrom(this.httpClient.delete<Avaliacao>(urlAuxiliar));
  }
}
