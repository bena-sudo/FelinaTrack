import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-gato',
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './detail-gato.component.html',
  styleUrl: './detail-gato.component.css',
})
export class DetailGatoComponent {
  gato = { id: 1, nombre: 'Michi', color: 'Negro', estadoSalud: 'Vacunado' };
  constructor(private readonly route: ActivatedRoute) {}
}
