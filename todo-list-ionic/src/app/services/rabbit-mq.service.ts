import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class RabbitMQService {
  private stompClient: Client;

  constructor(
    private toastController: ToastController,
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
      console.log('Mensagem recebida:', message.body);
      this.showToast(message.body);
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
