import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';
import { firstValueFrom } from 'rxjs';

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

  adicionarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl, usuario);
  }
}