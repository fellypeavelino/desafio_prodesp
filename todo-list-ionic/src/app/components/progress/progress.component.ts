import { Component, OnInit } from '@angular/core';
import { 
  IonProgressBar
} from '@ionic/angular/standalone';


@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.scss'],
  standalone: true,
  imports: [
    IonProgressBar
  ]
})
export class ProgressComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
