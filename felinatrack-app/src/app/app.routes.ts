import { Routes } from '@angular/router';
import { DetailColoniaComponent } from './components/colonias/detail-colonia/detail-colonia.component';
import { ListColoniasComponent } from './components/colonias/list-colonias/list-colonias.component';
import { HomeComponent } from './components/home/home.component';
import { ListActuacionesComponent } from './components/actuaciones/list-actuaciones/list-actuaciones.component';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { DetailGatoComponent } from './components/gatos/detail-gato/detail-gato.component';
import { ListGatosComponent } from './components/gatos/list-gatos/list-gatos.component';
import { ListProblemasComponent } from './components/problemas/list-problemas/list-problemas.component';
import { ListResponsablesComponent } from './components/responsables/list-responsables/list-responsables.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';

export const routes: Routes = [
    { path: 'auth/login', component: LoginComponent },
    { path: 'auth/register', component: RegisterComponent },
    {
      path: '',
      component: MainLayoutComponent,
      children: [
        { path: '', component: HomeComponent },
        { path: 'colonias', component: ListColoniasComponent },
        { path: 'colonias/:id', component: DetailColoniaComponent },
        { path: 'gatos', component: ListGatosComponent },
        { path: 'gatos/:id', component: DetailGatoComponent },
        { path: 'responsables', component: ListResponsablesComponent },
        { path: 'actuaciones', component: ListActuacionesComponent },
        { path: 'problemas', component: ListProblemasComponent },
        { path: 'admin', component: AdminComponent }
      ]
    }
  ];
