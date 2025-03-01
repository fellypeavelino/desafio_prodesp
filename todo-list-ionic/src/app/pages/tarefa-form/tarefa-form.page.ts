import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.page.html',
  styleUrls: ['./tarefa-form.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class TarefaFormPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
