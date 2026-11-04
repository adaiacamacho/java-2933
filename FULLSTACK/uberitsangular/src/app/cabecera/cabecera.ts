import { ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-cabecera',
  styleUrl: './cabecera.css',
  templateUrl: './cabecera.html',
})
export class Cabecera {
  sesionIniciada =signal<boolean>(false);
  private readonly changeDetectorRef = inject(ChangeDetectorRef);

  constructor(){
  this.sesionIniciada.set(!!sessionStorage.getItem("token"));
  this.changeDetectorRef.markForCheck();
  }
  

  cerrarSesion() {
    sessionStorage.removeItem("token");
  }
}
