import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { 
  IonContent, IonHeader, IonTitle, IonToolbar, IonList,
  IonItem, IonLabel, IonText, IonInput, IonIcon, IonButton
} from '@ionic/angular/standalone';
import { Usuario } from 'src/app/models/usuario.model';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.page.html',
  styleUrls: ['./usuario-form.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    IonItem, IonLabel, IonText, IonInput, 
    IonIcon, IonButton, ReactiveFormsModule,
    IonList
  ]
})
export class UsuarioFormPage implements OnInit {

  usuarioForm: FormGroup;
  isEditing = false;
  userId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.usuarioForm = this.fb.group({
      loguin: ['', [Validators.required, Validators.minLength(3)]],
      senha: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditing = true;
      this.userId = +id;
      this.usuarioService.getUsuario(this.userId).subscribe(data => {
        this.usuarioForm.patchValue(data);
      });
    }
  }

  saveUsuario() {
    if (this.usuarioForm.invalid) {
      return;
    }

    const usuario: Usuario = {
      id: this.userId || 0,
      ...this.usuarioForm.value
    };

    if (this.isEditing) {
      this.usuarioService.updateUsuario(usuario).subscribe(() => {
        this.redirecione('/usuarios');
      });
    } else {
      this.usuarioService.addUsuario(usuario).subscribe(() => {
        this.redirecione('/usuarios');
      });
    }
  }

  redirecione(pagina:string){
    this.router.navigate([pagina]).then(() => {
      location.reload();
    });
  }

}
