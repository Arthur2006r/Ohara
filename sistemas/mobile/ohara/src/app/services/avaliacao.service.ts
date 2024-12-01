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
    const urlVerificacao = `${this.url}/existe?usuario=${avaliacao.idUsuario}&manga=${avaliacao.idManga}`;
    
    // Verifica se a avaliação já existe
    const existe = await firstValueFrom(this.httpClient.get<boolean>(urlVerificacao));

    if (!existe) {
      // Criar nova avaliação (POST)
      return await firstValueFrom(
        this.httpClient.post<Avaliacao>(this.url, avaliacao, this.httpHeaders)
      );
    } else {
      // Atualizar avaliação existente (PUT)
      return await firstValueFrom(
        this.httpClient.put<Avaliacao>(this.url, avaliacao, this.httpHeaders)
      );
    }
  }
  
  private async verificarAvaliacaoExistente(idUsuario: number, idManga: number): Promise<boolean> {
    const url = `${this.url}/existe?usuario=${idUsuario}&manga=${idManga}`;
    return await firstValueFrom(this.httpClient.get<boolean>(url));
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
