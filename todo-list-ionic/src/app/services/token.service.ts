import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private token!:string;
  private apiUrl = '/api/token';

  constructor(private http: HttpClient) { }

  async tokenAsync(): Promise<any> {
    return await firstValueFrom (this.http.get<any>(this.apiUrl));
  }

  setToken(token:string){
    this.token = token;
    localStorage.setItem("token",token);
  }

  getToken(){
    return this.token ? this.token : localStorage.getItem("token");
  }

  deleteToken(){
    this.token = "";
    localStorage.removeItem("token");
  }
}
