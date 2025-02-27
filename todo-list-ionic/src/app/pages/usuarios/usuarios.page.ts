import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonButton, IonList, IonItem, IonLabel, IonButtons
} from '@ionic/angular/standalone';
import { NavController } from '@ionic/angular';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.page.html',
  styleUrls: ['./usuarios.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonButton, IonList, IonItem, IonLabel, 
    IonButtons
  ]
})
export class UsuariosPage implements OnInit {
  usuarios: Usuario[] = [];

  constructor(
    private usuarioService: UsuarioService, 
    private alertCtrl: AlertController,
    private navCtrl: NavController
  ) { }

  ngOnInit() {
    this.loadUsuarios();
  }

  loadUsuarios() {
    this.usuarioService.getUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  async deleteUsuario(id: number) {
    const alert = await this.alertCtrl.create({
      header: 'Confirmar',
      message: 'Deseja excluir este usuário?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel'
        },
        {
          text: 'Excluir',
          handler: () => {
            this.usuarioService.deleteUsuario(id).subscribe(() => {
              this.loadUsuarios();
            });
          }
        }
      ]
    });

    await alert.present();
  }

  redirecionarParaForm(id:number = 0){
    if (id != 0) {
      this.navCtrl.navigateForward(`/usuario-form/${id}`); 
      return ;  
    }
    this.navCtrl.navigateForward('/usuario-form'); 
  }

}
