import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';
import { firstValueFrom } from 'rxjs';
import { RequestPage } from '../models/request.page.model';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  
  usuario!:Usuario;  
  private apiUrl = '/api/usuarios';

  constructor(private http: HttpClient) {}

  login(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl+"/login", usuario);
  }

  async loguinAsync(usuario: Usuario): Promise<Usuario> {
    return await firstValueFrom (this.http.post<Usuario>(this.apiUrl+"/login", usuario));
  }

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.apiUrl);
  }

  getPagination(param:RequestPage): Observable<any> {
    return this.http.post<any>(this.apiUrl+"/paginacao", param);
  }

  addUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl, usuario);
  }

  updateUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/${usuario.id}`, usuario);
  }

  deleteUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  } 
  
  getUsuario(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }
}