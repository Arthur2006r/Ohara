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
    try {
      const avAux = await this.consultarMinhaAvaliacao(avaliacao.idManga, avaliacao.idUsuario);

      if (avAux) {
        return await firstValueFrom(
          this.httpClient.put<Avaliacao>(this.url, JSON.stringify(avaliacao), this.httpHeaders)
        );
      } else {
        return await firstValueFrom(
          this.httpClient.post<Avaliacao>(this.url, JSON.stringify(avaliacao), this.httpHeaders)
        );
      }
    } catch (erro) {
      console.error('Erro ao salvar avaliação:', erro);
      throw erro;
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

  async excluir(idUsuario: number | null, idManga: number | null): Promise<Avaliacao> {
    let urlAuxiliar = this.url + "/" + idUsuario + "/" + idManga;
    return await firstValueFrom(this.httpClient.delete<Avaliacao>(urlAuxiliar));
  }
}
