import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  // private eventSource: EventSource;

  // constructor() {
  //   this.eventSource = new EventSource('/api/notifications/stream');
  // }

  private socket!: WebSocket;
  private subject!: Subject<string>;

  constructor() {
    this.connect('ws://localhost:8080/ws/notifications');
  }

  connect(url: string): Observable<string> {
    this.socket = new WebSocket(url);
    this.subject = new Subject<string>();

    this.socket.onmessage = (event) => this.subject.next(event.data);
    this.socket.onerror = (error) => console.error('WebSocket Error:', error);
    this.socket.onclose = () => console.log('WebSocket Disconnected');

    return this.subject.asObservable();
  }

  sendMessage(message: string) {
    if (this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(message);
    }
  }

  getMessages(): Observable<string> {
    return this.subject.asObservable();
  }

  close() {
    if (this.socket) {
      this.socket.close();
    }
  }
}
