import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-colonia',
  imports: [MatButtonModule],
  templateUrl: './detail-colonia.component.html',
  styleUrl: './detail-colonia.component.css',
})
export class DetailColoniaComponent {
  colonia = { id: 1, nombre: 'Colonia Centro', municipio: 'Madrid' };
  constructor(private readonly route: ActivatedRoute) {}
}
