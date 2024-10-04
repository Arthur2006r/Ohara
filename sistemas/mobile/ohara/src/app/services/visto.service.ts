import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Visto } from '../model/visto';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VistoService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/visto';


  constructor(private httpClient: HttpClient) { }

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
    let urlAuxiliar = this.url + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.delete<Visto>(urlAuxiliar));
  }
}
