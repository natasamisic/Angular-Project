import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Karta } from 'src/model/karta';
import { Korisnik } from 'src/model/korisnik';
import { OmiljeniKoncerti } from 'src/model/omiljeniKoncerti';

@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  private BACKEND_BASE = 'http://localhost:8080/Koncert/'

  constructor(private httpClient: HttpClient) { }

  login(username: string, password: string) {
    let params = new HttpParams()
      .set('username', username)
      .set('password', password);
    console.log(username + ' | ' + password);
    return this.httpClient.post(this.BACKEND_BASE + 'auth/login', params);
  }

  register(userForm: any) {
    let params = new HttpParams()
      .set('ime', userForm.value.ime)
      .set('prezime', userForm.value.prezime)
      .set('username', userForm.value.username)
      .set('password', userForm.value.password)
      .set('email', userForm.value.email)
    return this.httpClient.post(this.BACKEND_BASE + 'auth/register', params);
  }

  addKomentar(tekst: string, idKoncert: number): Observable<any>{
    let params = new HttpParams()
    .set('tekst', tekst)
    .set('idKoncerta', idKoncert)
    .set('idKorisnika', localStorage.getItem('idKorisnik')!)
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/dodajKomentar', params)
  }

  getProfil(id: string): Observable<Korisnik>{
    return this.httpClient.get<Korisnik>(this.BACKEND_BASE + 'korisnik/getProfil/'+id)
  }

  getListuZelja(): Observable<OmiljeniKoncerti[]>{
    return this.httpClient.get<OmiljeniKoncerti[]>(this.BACKEND_BASE + 'korisnik/getListuOmiljenihKoncerata/'+localStorage.getItem('idKorisnik'))
  }

  dodajUListuZelja(idKoncerta: number){
    let params = new HttpParams()
    .set('idKoncerta', idKoncerta)
    .set('idKorisnika', localStorage.getItem('idKorisnik')!)
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/dodajUListuOmiljenih', params)
  }

  obrisiKoncertSaListe(idKoncerta: number){
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/izbaciIzListeOmiljenih/'+idKoncerta, null)
  }

  rezervisiKartu(idKoncerta: number){
    let params = new HttpParams()
    .set('idKoncerta', idKoncerta)
    .set('idKorisnika', localStorage.getItem('idKorisnik')!)
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/rezervisiKartu', params)
  }

  getRezervacije(): Observable<Karta[]>{
    return this.httpClient.get<Karta[]>(this.BACKEND_BASE + 'korisnik/getRezervisaneKarte/'+localStorage.getItem('idKorisnik'))
  }

  otkaziRezervaciju(idKarte: number){
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/otkaziRezervaciju/'+idKarte, null)
  }

  proveraDaLiJeNaListiVec(idKoncerta: number){
    let params = new HttpParams()
    .set('idKoncerta', idKoncerta)
    .set('idKorisnika', localStorage.getItem('idKorisnik')!)
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/proveraListeKorisnikaZaKoncert', params)
  }

  obrisiKomentar(idKomentar: number){
    return this.httpClient.post(this.BACKEND_BASE + 'korisnik/obrisiKomentar/'+ idKomentar, null)
  }
}
