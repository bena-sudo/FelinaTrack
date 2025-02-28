import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-list-problemas',
  imports: [MatTableModule],
  templateUrl: './list-problemas.component.html',
  styleUrl: './list-problemas.component.css',
})
export class ListProblemasComponent {
  displayedColumns: string[] = ['tipo', 'descripcion', 'fecha'];
  problemas = [
    {
      tipo: 'Emergencia',
      descripcion: 'Gato herido en la calle',
      fecha: '2025-02-25',
    },
    {
      tipo: 'Conflicto',
      descripcion: 'Vecinos reportan ruido excesivo',
      fecha: '2025-02-26',
    },
  ];
}
