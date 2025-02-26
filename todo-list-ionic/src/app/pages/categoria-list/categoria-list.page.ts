import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonButton, IonList, IonItem, IonLabel, IonButtons,
  NavController
} from '@ionic/angular/standalone';
import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../models/categoria.model';
import { AlertController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-categoria-list',
  templateUrl: './categoria-list.page.html',
  styleUrls: ['./categoria-list.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonButton, IonList, IonItem, IonLabel, 
    IonButtons
  ]
})
export class CategoriaListPage implements OnInit {
  categorias: Categoria[] = [];
  constructor(
    private categoriaService: CategoriaService,
    private alertController: AlertController,
    private toastController: ToastController,
    private navCtrl: NavController
  ) {}

  ngOnInit() {
    this.loadCategorias();
  }

  loadCategorias() {
    this.categoriaService.getCategorias().subscribe(data => {
      this.categorias = data;
    });
  }

  async deleteCategoria(id: number) {
    const alert = await this.alertController.create({
      header: 'Confirmação',
      message: 'Tem certeza que deseja excluir esta categoria?',
      buttons: [
        { text: 'Cancelar', role: 'cancel' },
        {
          text: 'Excluir',
          handler: () => {
            this.categoriaService.deleteCategoria(id).subscribe(() => {
              this.showToast('Categoria excluída com sucesso!');
              this.loadCategorias();
            });
          }
        }
      ]
    });
    await alert.present();
  }

  async showToast(message: string) {
    const toast = await this.toastController.create({
      message,
      duration: 2000
    });
    toast.present();
  }

  redirecionarParaForm(id:number = 0){
    if (id != 0) {
      this.navCtrl.navigateForward(`/categoria-form/${id}`); 
      return ;  
    }
    this.navCtrl.navigateForward('/categoria-form'); 
  }

}
