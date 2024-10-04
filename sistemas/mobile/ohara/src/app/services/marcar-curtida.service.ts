import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarcarCurtidaService {
  apiUrl = 'http://localhost:8087/api/v1/UsuarioMarcaCurtidaManga';

  constructor(private http: HttpClient) {}

  favoritar(idUsuario: number, idManga: number): Promise<any> {
    const body = { idUsuario, idManga };
    return firstValueFrom(this.http.post(this.apiUrl, body));
  }

  desfavoritar(idUsuario: number, idManga: number): Promise<any> {
    const body = { idUsuario, idManga };
    return firstValueFrom(this.http.post(this.apiUrl, body));
  }
  
}