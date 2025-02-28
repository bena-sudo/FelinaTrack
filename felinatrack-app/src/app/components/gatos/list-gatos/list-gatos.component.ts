import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-list-gatos',
  imports: [RouterLink, MatTableModule, MatButtonModule],
  templateUrl: './list-gatos.component.html',
  styleUrl: './list-gatos.component.css',
})
export class ListGatosComponent {
  displayedColumns: string[] = ['nombre', 'color', 'acciones'];
  gatos = [
    { id: 1, nombre: 'Michi', color: 'Negro' },
    { id: 2, nombre: 'Luna', color: 'Blanco' },
  ];
}
