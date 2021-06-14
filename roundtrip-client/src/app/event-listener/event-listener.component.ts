import { Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { ObserveOnOperator } from 'rxjs/internal/operators/observeOn';

@Component({
  selector: 'app-event-listener',
  templateUrl: './event-listener.component.html',
  styleUrls: ['./event-listener.component.css']
})
export class EventListenerComponent implements OnInit, OnDestroy {

  messages: string[] = [];
  evtSrc$ : Observable<string> | null = null;
  zone: NgZone = new NgZone({});
  subscription : Subscription | null = null;

  constructor() { }

  ngOnInit(): void {
    this.evtSrc$ = new Observable(
      observer => {
        let eventSource: EventSource = new EventSource("http://localhost:8080/stream-flux");
        eventSource.onmessage = (msg:MessageEvent) => {
          this.zone.run(() => observer.next(msg.data));
        }
        eventSource.onerror = (error) => observer.error(error);
      }
    );
    this.subscription = this.evtSrc$.subscribe(
      {
        next: (msg:string) => this.messages.push(msg)
      }
    );
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}
