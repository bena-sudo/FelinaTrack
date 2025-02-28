import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-list-responsables',
  imports: [MatTableModule],
  templateUrl: './list-responsables.component.html',
  styleUrl: './list-responsables.component.css',
})
export class ListResponsablesComponent {
  displayedColumns: string[] = ['nombre', 'tipo', 'contacto'];
  responsables = [
    {
      id: 1,
      nombre: 'Juan Pérez',
      tipo: 'Voluntario',
      contacto: 'juan@example.com',
    },
    {
      id: 2,
      nombre: 'Asociación Felina',
      tipo: 'Asociación',
      contacto: 'contacto@felina.org',
    },
  ];
}
