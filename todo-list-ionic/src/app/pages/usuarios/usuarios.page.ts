import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonButton, IonList, IonItem, IonLabel, IonButtons,
  IonInfiniteScroll, IonInfiniteScrollContent, IonFooter,
  IonGrid, IonRow, IonCol, IonInput, IonMenuButton
} from '@ionic/angular/standalone';
import { NavController } from '@ionic/angular';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';
import { AlertController } from '@ionic/angular';
import { RequestPage } from 'src/app/models/request.page.model';
import { MenuComponent } from "src/app/components/menu/menu.component";
@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.page.html',
  styleUrls: ['./usuarios.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonButton, IonList, IonItem, IonLabel, 
    IonButtons, IonInfiniteScroll, IonInfiniteScrollContent,
    IonGrid, IonRow, IonCol, IonFooter,
    IonInput, MenuComponent, IonMenuButton
  ]
})
export class UsuariosPage implements OnInit {
  usuarios: Usuario[] = [];
  requestPage: RequestPage = {"page": 0, "size": 0,"sortBy": "id","sortDir": "desc","filtro": ""};
  page = 1;
  size = 5;
  totalUsuarios = 0;
  totalPages = 0;
  isLoading = false;
  maxPagesToShow = 5;
  searchTerm:string = "";

  constructor(
    private usuarioService: UsuarioService, 
    private alertCtrl: AlertController,
    private navCtrl: NavController
  ) { }

  ngOnInit() {
    this.loadUsuarios();
  }

  loadUsuarios(event?: any) {
    if (this.isLoading) return;
    this.isLoading = true;

    this.requestPage.page = (this.page - 1);
    this.requestPage.size = this.size;
    this.usuarioService.getPagination(this.requestPage).subscribe(response => {
      this.usuarios = response.usuarioDto;
      this.totalUsuarios = response.total;
      this.totalPages = Math.ceil(this.totalUsuarios / this.size);
      this.isLoading = false;
    });
    // this.usuarioService.getPagination(this.requestPage).subscribe(data => {
    //   this.usuarios = [...this.usuarios, ...data.usuarioDto];
    //   this.isLoading = false;
    //   this.totalUsuarios = data.total;
    //   if (event) {
    //     event.target.complete();
    //   }
    // });
  }

  buscarUsuarios(){
    this.requestPage.filtro = this.searchTerm;
    this.loadUsuarios();
  }

  // loadMore(event: any) {
  //   if (this.usuarios.length < this.totalUsuarios) {
  //     this.page++;
  //     this.loadUsuarios(event);
  //   } else {
  //     event.target.disabled = true;
  //   }
  // }

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
      this.navCtrl.navigateForward(`/usuario-form/${id}`).then(() => {
        location.reload();
      }); 
      return ;  
    }
    this.navCtrl.navigateForward('/usuario-form').then(() => {
      location.reload();
    }); 
  }

  nextPage() {
    if (this.page * this.size < this.totalUsuarios) {
      this.page++;
      this.loadUsuarios();
    }
  }

  prevPage() {
    if (this.page > 1) {
      this.page--;
      this.loadUsuarios();
    }
  }

  retornarTotal(){
    return Math.ceil(this.totalUsuarios / this.size);
  }

  goToPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.totalPages) {
      this.page = pageNumber;
      this.loadUsuarios();
    }
  }

  getPageRange(): number[] {
    const start = Math.max(1, this.page - Math.floor(this.maxPagesToShow / 2));
    const end = Math.min(this.totalPages, start + this.maxPagesToShow - 1);

    return Array.from({ length: end - start + 1 }, (_, i) => start + i);
  }

}
