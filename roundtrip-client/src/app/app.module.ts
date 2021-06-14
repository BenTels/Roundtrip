import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ClickSourceComponent } from './click-source/click-source.component';
import { EventListenerComponent } from './event-listener/event-listener.component';

@NgModule({
  declarations: [
    AppComponent,
    ClickSourceComponent,
    EventListenerComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
