import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MangaService {

  url: string = 'http://localhost:8087/api/v1/alimentar/sistema';

  constructor(httpClient: HttpClient) { }
}
