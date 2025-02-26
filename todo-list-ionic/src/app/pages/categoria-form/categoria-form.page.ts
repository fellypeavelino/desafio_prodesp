import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.page.html',
  styleUrls: ['./categoria-form.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class CategoriaFormPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
