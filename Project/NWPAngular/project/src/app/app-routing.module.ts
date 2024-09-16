import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AzurirajKoncertComponent } from './components/azuriraj-koncert/azuriraj-koncert.component';
import { DodajKoncertComponent } from './components/dodaj-koncert/dodaj-koncert.component';
import { KoncertViewComponent } from './components/koncert-view/koncert-view.component';
import { KoncertiComponent } from './components/koncerti/koncerti.component';
import { ListaZeljaComponent } from './components/lista-zelja/lista-zelja.component';
import { LoginComponent } from './components/login/login.component';
import { ProfilComponent } from './components/profil/profil.component';
import { RegisterComponent } from './components/register/register.component';
import { RezervacijeComponent } from './components/rezervacije/rezervacije.component';
import { AuthGuard } from './guard/auth-guard';
import { AuthGuardKorisnik } from './guard/auth-guard-korisnik';
import { AuthGuardRadnik } from './guard/auth-guard-radnik';

const routes: Routes = [{path: '', redirectTo: 'login', pathMatch: 'full'},
{path: 'login', component: LoginComponent},
{path: 'register', component: RegisterComponent},
{path: 'svi-koncerti', component: KoncertiComponent},
{path: 'prikaz-koncerta/:izvodjac', component: KoncertViewComponent},
{path: 'profil-korisnika', component: ProfilComponent, canActivate: [AuthGuard]},
{path: 'rezervacije-korisnika', component: RezervacijeComponent, canActivate: [AuthGuardKorisnik]},
{path: 'lista-zelja', component: ListaZeljaComponent, canActivate: [AuthGuardKorisnik]},
{path: 'dodaj-koncert', component: DodajKoncertComponent, canActivate: [AuthGuardRadnik]},
{path: 'azuriraj-koncert/:izvodjac', component: AzurirajKoncertComponent, canActivate: [AuthGuardRadnik]}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
