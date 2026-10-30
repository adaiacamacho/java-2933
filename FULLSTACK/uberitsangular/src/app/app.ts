import { Component, signal } from '@angular/core';
import { Cabecera } from './cabecera/cabecera';
import { Pie } from './pie/pie';

@Component({
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
  imports: [Cabecera, Pie],
})
export class App {
  protected readonly title = signal('uberitsangular');
}
