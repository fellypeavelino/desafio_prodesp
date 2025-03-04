import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, 
  IonButton, IonList, IonItem, IonLabel, IonButtons,
  IonInfiniteScroll, IonInfiniteScrollContent, IonFooter,
  IonGrid, IonRow, IonCol, IonInput, IonSelect, IonSelectOption,
  IonMenu, IonMenuButton
} from '@ionic/angular/standalone';
import { NavController, ToastController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { RequestPage } from 'src/app/models/request.page.model';
import { Tarefa } from 'src/app/models/tarefa.model';
import { TarefaService } from 'src/app/services/tarefa.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Categoria } from 'src/app/models/categoria.model';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Usuario } from 'src/app/models/usuario.model';
import { MenuComponent } from "src/app/components/menu/menu.component";

@Component({
  selector: 'app-tarefas',
  templateUrl: './tarefas.page.html',
  styleUrls: ['./tarefas.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonButton, IonList, IonItem, IonLabel, 
    IonButtons, IonInfiniteScroll, IonInfiniteScrollContent,
    IonGrid, IonRow, IonCol, IonFooter,
    IonInput, IonSelect, IonSelectOption,
    MenuComponent, IonMenu, IonMenuButton
  ]
})
export class TarefasPage implements OnInit {
  requestPage: RequestPage = {"page": 0, "size": 0,"sortBy": "id","sortDir": "desc","filtro": ""};
  page = 1;
  size = 5;
  totalTarefas = 0;
  totalPages = 0;
  isLoading = false;
  maxPagesToShow = 5;
  searchTerm:string = "";
  tarefas:Tarefa[] = [];
  categorias:Categoria[]=[];
  usuario:Usuario = {id: 0, loguin: '', senha: ''}

  constructor(
    private usuarioService: UsuarioService,
    private categoriaService: CategoriaService,
    private tarefaService: TarefaService, 
    private alertCtrl: AlertController,
    private navCtrl: NavController,
    private toastController: ToastController,
  ) { }

  ngOnInit() {
    this.usuario = this.usuarioService.getUsuarioLogin();
    this.loadTarefas();
    this.categoriaService.getCategorias().subscribe(res => {
      this.categorias = res;
    });
  }

  loadTarefas(event?: any) {
    if (this.isLoading) return;
    this.isLoading = true;
    if (this.requestPage.filtro != "") {
      this.page = 1;
    }
    this.requestPage.page = (this.page - 1);
    this.requestPage.size = this.size;
    this.tarefaService.getPagination(this.requestPage, this.usuario.id).subscribe(response => {
      this.tarefas = response.tarefasDto;
      this.totalTarefas = response.total;
      this.totalPages = Math.ceil(this.totalTarefas / this.size);
      this.isLoading = false;
    });
    // this.tarefaService.getPagination(this.requestPage).subscribe(data => {
    //   this.tarefas = [...this.tarefas, ...data.tarefasDto];
    //   this.isLoading = false;
    //   this.totalTarefas = data.total;
    //   if (event) {
    //     event.target.complete();
    //   }
    // });
  }

  buscarTarefas(){
    this.requestPage.filtro = this.searchTerm;
    this.loadTarefas();
  }

  // loadMore(event: any) {
  //   if (this.tarefas.length < this.totalTarefas) {
  //     this.page++;
  //     this.loadTarefas(event);
  //   } else {
  //     event.target.disabled = true;
  //   }
  // }

  
  async deleteTarefa(id: number) {
    const alert = await this.alertCtrl.create({
      header: 'Confirmar',
      message: 'Deseja excluir este tarefa?',
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel'
        },
        {
          text: 'Excluir',
          handler: () => {
            this.tarefaService.deleteTarefa(id).subscribe(() => {
              this.loadTarefas();
            });
          }
        }
      ]
    });

    await alert.present();
  }

  redirecionarParaForm(id:number = 0){
    if (id != 0) {
      this.navCtrl.navigateForward(`/tarefa-form/${id}`).then(res => {
        location.reload();
      }); 
      return ;  
    }
    this.navCtrl.navigateForward('/tarefa-form').then(res => {
      location.reload();
    }); 
  }

  nextPage() {
    if (this.page * this.size < this.totalTarefas) {
      this.page++;
      this.loadTarefas();
    }
  }

  prevPage() {
    if (this.page > 1) {
      this.page--;
      this.loadTarefas();
    }
  }

  retornarTotal(){
    return Math.ceil(this.totalTarefas / this.size);
  }

  goToPage(pageNumber: number) {
    if (pageNumber >= 1 && pageNumber <= this.totalPages) {
      this.page = pageNumber;
      this.loadTarefas();
    }
  }

  getPageRange(): number[] {
    const start = Math.max(1, this.page - Math.floor(this.maxPagesToShow / 2));
    const end = Math.min(this.totalPages, start + this.maxPagesToShow - 1);

    return Array.from({ length: end - start + 1 }, (_, i) => start + i);
  }

  logout(){
    this.navCtrl.navigateForward('/login'); 
  }

  handleChange($event:any, tarefa:Tarefa){
    console.log($event.detail.value, tarefa);
    const id = tarefa.id || 0;
    tarefa.categoria_id = $event.detail.value;
    tarefa.usuario_id = this.usuario.id;
    this.tarefaService.updateTarefa(id, tarefa).subscribe(() => {
      this.showToast('Tarefa atualizada com sucesso!');
    });
  }

  async showToast(message: string) {
    const toast = await this.toastController.create({
      message,
      duration: 2000
    });
    toast.present();
  }

  trazerCorDacategoria(id_categoria:any){
    let result = this.categorias.filter(c => c.id == id_categoria);
    if (result.length == 1) {
      return result[0].cor;
    }
    return 'black';
  }
}
