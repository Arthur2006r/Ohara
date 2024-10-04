import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curtida } from '../model/curtida';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurtidaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/curtida';


  constructor(private httpClient: HttpClient) { }

  async salvar(curtida: Curtida): Promise<Curtida> {
    return await firstValueFrom(this.httpClient.post<Curtida>(this.url, JSON.stringify(curtida), this.httpHeaders));
  }

  async consultarMinhaCurtida(idManga: number | null, idUsuario: number | null): Promise<Curtida> {
    let urlAuxiliar = this.url + "/" + "minha" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Curtida>(urlAuxiliar));
  }

  async excluir(idManga: number | null, idUsuario: number | null): Promise<Curtida> {
    let urlAuxiliar = this.url + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.delete<Curtida>(urlAuxiliar));
  }
}
