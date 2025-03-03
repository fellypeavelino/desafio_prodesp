import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { ProgressComponent } from "src/app/components/progress/progress.component";
import { LoadingService } from './services/loading.service';
import { Subscription } from 'rxjs';
import { WebsocketService } from './services/websocket.service';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  standalone: true,
  imports: [IonApp, IonRouterOutlet, ProgressComponent],
})
export class AppComponent implements OnInit, OnDestroy  {

  private subscription!: Subscription;
  showModal:boolean = true;

  constructor(
    private loadingService: LoadingService,
    private websocketService: WebsocketService
  ) {}

  ngOnInit(): void {
    this.websocketService.getMessages().subscribe((message) => {
      console.log('📢 Notificação recebida:', message);
      alert(message);
    });
    this.subscription = this.loadingService.loading$.subscribe(loading => {
      if (loading) {
        this.showModal = true;
      } else {
        this.showModal = false;
      }
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
