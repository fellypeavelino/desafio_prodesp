import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Client } from '@stomp/stompjs';
import { UsuarioService } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class RabbitMQService {
  private stompClient: Client;

  constructor(
    private toastController: ToastController,
    private usuarioService: UsuarioService,
  ) {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:15674/ws', // URL do RabbitMQ WebSocket
      connectHeaders: {
        login: 'guest',
        passcode: 'guest'
      },
      onConnect: () => {
        console.log('Conectado ao RabbitMQ');
        this.subscribeToQueue('/queue/tarefa.atrasada');
      },
      onStompError: (frame) => {
        console.error('Erro no STOMP: ', frame);
      }
    });

    this.stompClient.activate();
  }

  public subscribeToQueue(queue: string) {
    this.stompClient.subscribe(queue, message => {
      const usuario = this.usuarioService.getUsuarioLogin();
      const objTarefa = JSON.parse(message.body);
      if (usuario.id == objTarefa.usuario) {
        console.log('Mensagem recebida:', message.body, usuario);
        this.showToast(`A tarefa ${objTarefa.titulo} esta atrasada`);  
      }
    });
  }
  
  
  async showToast(message: string) {
    const toast = await this.toastController.create({
      message,
      duration: 10000
    });
    toast.present();
  }
}
