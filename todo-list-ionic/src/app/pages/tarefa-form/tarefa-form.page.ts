import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, IonList,
  IonItem, IonLabel, IonText, IonInput, IonIcon, IonButton,
  IonSelect, IonSelectOption, IonCheckbox, IonButtons,
  ToastController, IonMenu, IonMenuButton,
  NavController
} from '@ionic/angular/standalone';
import { ActivatedRoute, Router } from '@angular/router';
import { TarefaService } from 'src/app/services/tarefa.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Categoria } from 'src/app/models/categoria.model';
import { Tarefa } from 'src/app/models/tarefa.model';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Usuario } from 'src/app/models/usuario.model';
import { MenuComponent } from "src/app/components/menu/menu.component";

@Component({
  selector: 'app-tarefa-form',
  templateUrl: './tarefa-form.page.html',
  styleUrls: ['./tarefa-form.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonItem, IonLabel, IonText, IonInput, 
    IonIcon, IonButton, ReactiveFormsModule,
    IonList, IonSelect, IonSelectOption,
    IonCheckbox, IonMenu, IonMenuButton,
    MenuComponent, IonButtons
  ]
})
export class TarefaFormPage implements OnInit {

  tarefaForm!: FormGroup;
  isEditing = false;
  tarefaId: number | null = null;
  categorias:Categoria[] = [];
  tarefa:Tarefa = {
    "titulo": "", 
    "descricao" : "", "completada": false, 
    "data": "", "usuario_id": 0, 
    "categoria_id": 0
  };
  isEdit = false;
  usuario:Usuario = {id: 0, loguin: '', senha: ''}
  constructor(
    private usuarioService: UsuarioService,
    private categoriaService: CategoriaService,
    private fb: FormBuilder,
    private toastController: ToastController,
    private tarefaService: TarefaService, 
    private route: ActivatedRoute,
    private router: NavController
  ) { 
    this.tarefaForm = this.fb.group({
      titulo: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      categoria_id: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.tarefaService.getTarefa(+id).subscribe(data => {
        this.tarefa = data;
      });
    }
    this.categoriaService.getCategorias().subscribe(response => {
      this.categorias = (response);
    });
    this.usuario = this.usuarioService.getUsuarioLogin();
  }

  saveTarefa(){
    this.tarefa.usuario_id = this.usuario.id;
    if (this.isEdit) {
      const id = this.route.snapshot.paramMap.get('id') || "";
      this.tarefa.id = parseInt(id);
      this.tarefaService.updateTarefa(this.tarefa.id, this.tarefa).subscribe(() => {
        this.showToast('Tarefa atualizada com sucesso!');
        this.redirecione('/tarefas');
      });
    } else {
      console.log(this.tarefa);
      
      this.tarefaService.addTarefa(this.tarefa).subscribe(() => {
        this.showToast('Tarefa criada com sucesso!');
        this.redirecione('/tarefas');
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
