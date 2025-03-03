import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tarefa } from '../models/tarefa.model';
import { Observable } from 'rxjs';
import { RequestPage } from '../models/request.page.model';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  private apiUrl = '/api/tarefas';

  constructor(private http: HttpClient) {}

  getPagination(param:RequestPage, id_usuario:number): Observable<any> {
    return this.http.post<any>(this.apiUrl+"/paginacao/"+id_usuario, param);
  }

  getTarefas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.apiUrl);
  }

  getTarefa(id: number): Observable<Tarefa> {
    return this.http.get<Tarefa>(`${this.apiUrl}/${id}`);
  }

  addTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.apiUrl, tarefa);
  }

  updateTarefa(id: number, tarefa: Tarefa): Observable<Tarefa> {
    return this.http.put<Tarefa>(`${this.apiUrl}/${id}`, tarefa);
  }

  deleteTarefa(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
