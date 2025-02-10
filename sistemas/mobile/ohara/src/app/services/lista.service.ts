import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Lista } from '../model/lista';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/lista';


  constructor(private httpClient: HttpClient) { }

  async salvar(lista: Lista): Promise<Lista> {
    if (lista.idLista === null) {
      return await firstValueFrom(this.httpClient.post<Lista>(this.url, JSON.stringify(lista), this.httpHeaders));
    } else {
      return await firstValueFrom(this.httpClient.put<Lista>(this.url, JSON.stringify(lista), this.httpHeaders));
    }
  }

  async excluir(id: number | null): Promise<Lista> {
    let urlAuxiliar = this.url + "/" + id;
    return await firstValueFrom(this.httpClient.delete<Lista>(urlAuxiliar));
  }

  async consultarTodosUsuario(idUsuario: number): Promise<Lista[]> {
    let urlAuxiliar = this.url + "/ListasUsuario" + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Lista[]>(urlAuxiliar));
  }

  async buscarPorId(id: number | null): Promise<Lista> {
    let urlAuxiliar = this.url + "/PorId" + "/" + id;
    return await firstValueFrom(this.httpClient.get<Lista>(urlAuxiliar));
  }
}
