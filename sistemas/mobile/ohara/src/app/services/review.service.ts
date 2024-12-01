import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Review } from '../model/review';
import { firstValueFrom } from 'rxjs';
import { UsuarioService } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/review';


  constructor(private httpClient: HttpClient, private usuarioService: UsuarioService) { }

  async salvar(review: Review): Promise<Review> {
    if (review.idReview === null) {
      return await firstValueFrom(this.httpClient.post<Review>(this.url, JSON.stringify(review), this.httpHeaders));
    } else {
      return await firstValueFrom(this.httpClient.put<Review>(this.url, JSON.stringify(review), this.httpHeaders));
    }
  }

  async excluir(id: number | null): Promise<Review> {
    let urlAuxiliar = this.url + "/" + id;
    return await firstValueFrom(this.httpClient.delete<Review>(urlAuxiliar));
  }

  async buscarPorId(idReview: number | null): Promise<Review> {
    let urlAuxiliar = this.url + "/" + idReview;
    return await firstValueFrom(this.httpClient.get<Review>(urlAuxiliar));
  }

  async listarTodos(): Promise<Review[]> {
    return await firstValueFrom(this.httpClient.get<Review[]>(this.url));
  }

  async listarTodosManga(idManga: number | null): Promise<Review[]> {
    let urlAuxiliar = this.url + "/" + "todos" + "/" + idManga;
    return await firstValueFrom(this.httpClient.get<Review[]>(urlAuxiliar));
  }

  async listarSeguidosManga(idManga: number | null, idUsuario: number | null): Promise<Review[]> {
    let urlAuxiliar = this.url + "/" + "seguidos" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Review[]>(urlAuxiliar));
  }

  async listarMeusManga(idManga: number | null, idUsuario: number | null): Promise<Review[]> {
    let urlAuxiliar = this.url + "/" + "meus" + "/" + idManga + "/" + idUsuario;
    return await firstValueFrom(this.httpClient.get<Review[]>(urlAuxiliar));
  }

  
}
