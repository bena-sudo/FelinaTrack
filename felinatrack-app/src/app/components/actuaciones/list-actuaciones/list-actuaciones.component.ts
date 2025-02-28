import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-list-actuaciones',
  imports: [MatTableModule],
  templateUrl: './list-actuaciones.component.html',
  styleUrl: './list-actuaciones.component.css',
})
export class ListActuacionesComponent {
  displayedColumns: string[] = ['tipo', 'fecha', 'descripcion'];
  actuaciones = [
    {
      tipo: 'Adopción',
      fecha: '2025-02-20',
      descripcion: 'Gato adoptado por familia.',
    },
    {
      tipo: 'Vacunación',
      fecha: '2025-02-22',
      descripcion: 'Vacuna administrada a Michi.',
    },
  ];
}
