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

  listarMangas(): Promise<Manga[]> {
    return firstValueFrom(this.httpClient.get<Manga[]>(this.url));
  }

  consultarMangaPorId(id: number): Promise<any> {
    return firstValueFrom(this.httpClient.get<any>(`${this.url}/${id}`));
  }
 
}
