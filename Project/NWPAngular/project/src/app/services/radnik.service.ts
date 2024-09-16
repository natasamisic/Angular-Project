import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Karta } from 'src/model/karta';

@Injectable({
  providedIn: 'root'
})
export class RadnikService {

  private BACKEND_BASE = 'http://localhost:8080/Koncert/'

  constructor(private httpClient: HttpClient) { }

  getBrojKarataZaKoncert(idKoncerta: number): Observable<number>{
    return this.httpClient.get<number>(this.BACKEND_BASE + 'radnik/getBrojKarataZaKoncert/' + idKoncerta)
  }

  getKarteKoncerta(idKoncerta: number): Observable<Karta[]>{
    return this.httpClient.get<Karta[]>(this.BACKEND_BASE + 'radnik/getKarteKoncerta/' + idKoncerta)
  }

  azurirajKoncert(idKoncerta: number, koncertForm: any){
    let params = new HttpParams()
      .set('idKoncerta', idKoncerta)
      .set('adresa', koncertForm.value.adresa)
      .set('trajanje', koncertForm.value.trajanje)
      .set('cena', koncertForm.value.cena)
      .set('datumIzvodjenja', koncertForm.value.datumIzvodjenja)
    return this.httpClient.post(this.BACKEND_BASE + 'radnik/azurirajKoncert', params)
  }

  obrisiKoncert(idKoncerta: number){
    return this.httpClient.post(this.BACKEND_BASE + 'radnik/obrisiKoncert/' + idKoncerta, null)
  }

  sacuvajSliku(idKoncerta: number, imageFormData: FormData){
    return this.httpClient.post(this.BACKEND_BASE + 'radnik/sacuvajSliku/'+idKoncerta, imageFormData)
  }

  dodajKoncert(koncertForm: any){
    let params = new HttpParams()
      .set('izvodjac', koncertForm.value.izvodjac)
      .set('grad', koncertForm.value.grad)
      .set('adresa', koncertForm.value.adresa)
      .set('opis', koncertForm.value.opis)
      .set('trajanje', koncertForm.value.trajanje)
      .set('cena', koncertForm.value.cena)
      .set('nazivZanra', koncertForm.value.zanr)
      .set('datumIzvodjenja', koncertForm.value.datumIzvodjenja)
    return this.httpClient.post(this.BACKEND_BASE + 'radnik/dodajKoncert', params)
  }
}
