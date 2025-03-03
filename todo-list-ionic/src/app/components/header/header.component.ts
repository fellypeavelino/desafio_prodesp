import { Component, Input, OnInit } from '@angular/core';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, IonButtons,
  IonItem, IonLabel, IonText, IonInput, IonIcon, IonButton,
  IonMenu, IonMenuButton
} from '@ionic/angular/standalone';
import { ProgressComponent } from 'src/app/components/progress/progress.component';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, IonItem, IonLabel,
    IonText, IonInput, IonIcon, IonButton,
    ProgressComponent, IonButtons,
    IonMenu, IonMenuButton
  ]
})
export class HeaderComponent  implements OnInit {

  @Input() title!: string;
  @Input() ativarButtonMenu: boolean = true;

  constructor() { }

  ngOnInit() {}

}
