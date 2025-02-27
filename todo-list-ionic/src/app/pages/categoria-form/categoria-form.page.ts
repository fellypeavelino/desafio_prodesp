import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonList, IonItem, IonLabel, IonInput, IonButton, 
  NavController
} from '@ionic/angular/standalone';

import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../models/categoria.model';
import { ToastController } from '@ionic/angular';
import { Location } from '@angular/common';
@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.page.html',
  styleUrls: ['./categoria-form.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonList, IonItem, IonLabel, IonInput, 
    IonButton 
  ]
})
export class CategoriaFormPage implements OnInit {

  categoria: Categoria = { id: 0, nome: '', tarefas: [] };
  isEdit = false;

  constructor(
    private route: ActivatedRoute,
    private router: NavController,
    private categoriaService: CategoriaService,
    private toastController: ToastController,
    private location: Location
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.categoriaService.getCategoria(+id).subscribe(data => {
        this.categoria = data;
      });
    }
  }

  saveCategoria() {
    if (this.isEdit) {
      this.categoriaService.updateCategoria(this.categoria.id, this.categoria).subscribe(() => {
        this.showToast('Categoria atualizada com sucesso!');
        this.redirecione('/categoria-list');
      });
    } else {
      this.categoriaService.addCategoria(this.categoria).subscribe(() => {
        this.showToast('Categoria criada com sucesso!');
        this.redirecione('/categoria-list');
      });
    }
  }

  redirecione(pagina:string){
    this.router.navigateForward(pagina).then(() => {
      location.reload();
    });
  }

  async showToast(message: string) {
    const toast = await this.toastController.create({
      message,
      duration: 2000
    });
    toast.present();
  }

}
