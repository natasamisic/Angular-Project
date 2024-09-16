import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { Komentar } from 'src/model/komentar';
import { Koncert } from 'src/model/koncert';
import { Zanr } from 'src/model/zanr';

@Injectable({
  providedIn: 'root'
})
export class KoncertService {

  BACKEND_BASE = 'http://localhost:8080/Koncert/koncert/' 

  constructor(private httpClient: HttpClient) { }

  getKoncerti(): Observable<Koncert[]>{
    console.log('pre poziva metode')
    return this.httpClient.get<Koncert[]>(this.BACKEND_BASE + 'getSviKoncerti')
    .pipe(
      map((results) =>
        results.sort((koncert1, koncert2) => koncert1.izvodjac.localeCompare(koncert2.izvodjac))
      )
    );
  }

  searchKoncert(izvodjac: string): Observable<Koncert>{
    return this.httpClient.get<Koncert>(this.BACKEND_BASE + 'nadjiKoncertPoIzvodjacu', {
      params: {izvodjac: izvodjac}
    })
  }

  getKomentari(idKoncert: number): Observable<Komentar[]>{
    return this.httpClient.get<Komentar[]>(this.BACKEND_BASE + 'nadjiKomentareKoncerta/'+idKoncert)
  }
  
  getZanrovi(): Observable<Zanr[]>{
    return this.httpClient.get<Zanr[]>(this.BACKEND_BASE + 'getZanroveZaPretragu')
  }

  getSlike(): Observable<any>{
    return this.httpClient.get(this.BACKEND_BASE + 'getSlike')
  }

  getSlikeKoncerta(idKoncerta: number): Observable<any>{
    return this.httpClient.get(this.BACKEND_BASE + 'getSlikeKoncerta/'+idKoncerta)
  }
}
