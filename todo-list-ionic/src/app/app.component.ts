import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { ProgressComponent } from "src/app/components/progress/progress.component";
import { LoadingService } from './services/loading.service';
import { Subscription } from 'rxjs';
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
    private loadingService: LoadingService
  ) {}

  ngOnInit(): void {
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
