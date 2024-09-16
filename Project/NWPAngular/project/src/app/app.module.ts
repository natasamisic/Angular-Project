import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guard/auth-guard';
import { KoncertService } from './services/koncert.service';
import { KorisnikService } from './services/korisnik.service';
import { RadnikService } from './services/radnik.service';
import { TokenInterceptor } from './services/token-interceptor';
import { KoncertiComponent } from './components/koncerti/koncerti.component';
import { KoncertItemComponent } from './components/koncert-item/koncert-item.component';
import { AuthGuardKorisnik } from './guard/auth-guard-korisnik';
import { AuthGuardRadnik } from './guard/auth-guard-radnik';
import { KoncertViewComponent } from './components/koncert-view/koncert-view.component';
import { ProfilComponent } from './components/profil/profil.component';
import { RezervacijeComponent } from './components/rezervacije/rezervacije.component';
import { ListaZeljaComponent } from './components/lista-zelja/lista-zelja.component';
import { DodajKoncertComponent } from './components/dodaj-koncert/dodaj-koncert.component';
import { AzurirajKoncertComponent } from './components/azuriraj-koncert/azuriraj-koncert.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    KoncertiComponent,
    KoncertItemComponent,
    KoncertViewComponent,
    ProfilComponent,
    RezervacijeComponent,
    ListaZeljaComponent,
    DodajKoncertComponent,
    AzurirajKoncertComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [KorisnikService, KoncertService, RadnikService, AuthGuard, AuthGuardKorisnik, AuthGuardRadnik, {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }

