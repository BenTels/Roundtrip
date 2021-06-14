import { Component, OnDestroy, OnInit } from '@angular/core';
import { fromEvent, Observable, Subscription } from 'rxjs';
import { ClickListenerService } from '../click-listener.service';
import { map, tap } from 'rxjs/operators';

@Component({
  selector: 'app-click-source',
  templateUrl: './click-source.component.html',
  styleUrls: ['./click-source.component.css']
})
export class ClickSourceComponent implements OnInit, OnDestroy {

  phrases: string[] = ['Hello World', 'Live Long and Prosper', 'Today is a good day to die', 'Home is where the heart is, but the stars are made of latinum']

  el: HTMLButtonElement | null = null; 
  buttonClicks: Observable<MouseEvent> | null = null;

  subscription$ : Subscription | null = null;

  constructor(public clickListener: ClickListenerService) { }
  
  ngOnInit(): void {
    this.el = document.getElementById('theButton') as HTMLButtonElement;
    this.buttonClicks = fromEvent<MouseEvent>(this.el, 'click');
    // this.subscription$ = this.buttonClicks.subscribe({ next: (e: MouseEvent) => console.log(this.phrases[Math.floor(Math.random() * this.phrases.length)])} );
    this.subscription$ = this.buttonClicks
    .pipe(
      map((e: MouseEvent) => this.phrases[Math.floor(Math.random() * this.phrases.length)]),
      )
    .subscribe(this.clickListener.connect('ws://localhost:8080/ws'))
  }
  
  ngOnDestroy(): void {
    this.subscription$?.unsubscribe();
  }
}
