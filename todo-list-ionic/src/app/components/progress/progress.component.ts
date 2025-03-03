import { Component, Input, OnInit } from '@angular/core';
import { 
  IonProgressBar, IonModal, IonContent,
  IonCard, IonCardHeader, IonCardTitle,
  IonCardContent
} from '@ionic/angular/standalone';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.scss'],
  standalone: true,
  imports: [
    IonProgressBar, IonModal, IonContent,
    IonCard, IonCardHeader, IonCardTitle,
    IonCardContent, CommonModule
  ]
})
export class ProgressComponent  implements OnInit {

  @Input() isOpen = false; 
  progress = 0; 

  progressResult(){
    return this.progress * 100;
  }

  ngOnInit() {
    this.startProgress();
  }

  startProgress() {
    const interval = setInterval(() => {
      this.progress += 0.1;
      if (this.progress >= 1) {
        clearInterval(interval);
        setTimeout(() => this.close(), 500); // Fecha o modal quando chega a 100%
      }
    }, 500);
  }

  close() {
    this.isOpen = false;
  }

}
