import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NavController } from '@ionic/angular';
import { IonItem, IonLabel, IonText, IonInput, IonIcon, IonButton } from '@ionic/angular/standalone';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  standalone: true,
  imports: [
    IonContent, IonHeader, IonTitle, 
    IonToolbar, CommonModule, FormsModule,
    ReactiveFormsModule, IonItem, IonLabel,
    IonText, IonInput, IonIcon, IonButton
  ]
})
export class LoginPage implements OnInit {

  usuario:Usuario = {id:0, loguin:"", senha:""};
  loginForm!: FormGroup;
  showPassword = false; // Controla a exibição da senha

  constructor(
    private fb: FormBuilder, 
    private navCtrl: NavController,
    private usuarioService: UsuarioService,
  ) { 
    this.loginForm = this.fb.group({
      loguin: ['', [Validators.required]],
      senha: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  async login() {
    if (this.loginForm.valid) {
      this.usuario = this.loginForm.value;
      this.usuario = await this.usuarioService.loguinAsync(this.usuario);
      if(!this.usuario.id || this.usuario.id == 0){
        alert('usuario ou senha não existe');
        this.navCtrl.navigateForward('');
        return;
      }
      this.navCtrl.navigateForward('/home'); 
    } else {
      console.log('Formulário inválido');
    }
  }
}
