import { Component, OnInit } from '@angular/core';
import { 
  IonMenu, IonContent, IonHeader, IonTitle, IonToolbar,
  IonList, IonItem, IonLabel, IonIcon,
  NavController
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
  standalone: true,
  imports: [
    IonMenu, IonContent, IonHeader, IonTitle, IonToolbar,
    IonList, IonItem, IonLabel, IonIcon
  ]
})
export class MenuComponent  implements OnInit {

  constructor(
    private navCtrl: NavController,
  ) { }

  ngOnInit() {}

  navigateTo(page:string){
    this.navCtrl.navigateForward(`/${page}`)
    .then(res => {
      location.reload();
    })
    ;
  }

  logout(){
    this.navCtrl.navigateForward('/login'); 
  }
}
