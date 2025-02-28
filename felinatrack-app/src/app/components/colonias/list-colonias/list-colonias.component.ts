import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-list-colonias',
  imports: [RouterLink, MatTableModule, MatButtonModule],
  templateUrl: './list-colonias.component.html',
  styleUrl: './list-colonias.component.css',
})
export class ListColoniasComponent {
  displayedColumns: string[] = ['nombre', 'municipio', 'acciones'];
  colonias = [
    { id: 1, nombre: 'Colonia Centro', municipio: 'Madrid' },
    { id: 2, nombre: 'Colonia Norte', municipio: 'Barcelona' },
  ];
}
