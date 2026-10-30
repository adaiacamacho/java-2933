import { Component, signal } from '@angular/core';
import { Cabecera } from './cabecera/cabecera';

@Component({
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
  imports: [Cabecera],
})
export class App {
  protected readonly title = signal('uberitsangular');
}
