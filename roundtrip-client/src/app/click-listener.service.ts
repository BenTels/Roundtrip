import { Injectable } from '@angular/core';
import { Observable, Observer, Subject } from 'rxjs';
import { AnonymousSubject } from 'rxjs/internal/Subject';

@Injectable({
  providedIn: 'root'
})
export class ClickListenerService {

  private subject: Subject<string> | null = null;

  constructor() { }

  public connect(url: string): Subject<string> {
    if (!this.subject) {
      this.subject = this.create(url);
      console.log("Successfully connected: " + url);
    }
    return this.subject!;
  }

  private create(url: string): Subject<string> {
    let ws: WebSocket = new WebSocket(url);

    let observable: Observable<string> = new Observable((obs: Observer<string>) => {
      ws.onmessage = (ev: MessageEvent<string>) => obs.next(ev.data);
      ws.onerror = obs.error.bind(obs);
      ws.onclose = obs.complete.bind(obs);
      return ws.close.bind(ws);
    });
    let observer: Observer<string> = {
      next: (data: string) => {
        if (ws.readyState === WebSocket.OPEN) {
          ws.send(data);
        }
      },
      error: (err: Error) => console.log(err),
      complete: () => console.log('Complete')
    };
    return new AnonymousSubject<string>(observer, observable);
  }
}
