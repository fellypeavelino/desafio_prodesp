import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonButton, IonList, IonItem, IonLabel, IonButtons,
  IonInfiniteScroll, IonInfiniteScrollContent, IonFooter,
  IonGrid, IonRow, IonCol, IonInput
} from '@ionic/angular/standalone';
import { NavController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { RequestPage } from 'src/app/models/request.page.model';
import { Tarefa } from 'src/app/models/tarefa.model';

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.page.html',
  styleUrls: ['./tarefas.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonButton, IonList, IonItem, IonLabel, 
    IonButtons, IonInfiniteScroll, IonInfiniteScrollContent,
    IonGrid, IonRow, IonCol, IonFooter,
    IonInput
  ]
})
export class TarefasPage implements OnInit {
  requestPage: RequestPage = {"page": 0, "size": 0,"sortBy": "id","sortDir": "desc","filtro": ""};
  page = 1;
  size = 5;
  totalUsuarios = 0;
  totalPages = 0;
  isLoading = false;
  maxPagesToShow = 5;
  searchTerm:string = "";
  tarefa:Tarefa[] = [];
  
  constructor() { }

  ngOnInit() {
  }

}
