import { Component, Input, OnInit } from '@angular/core';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { OmiljeniKoncerti } from 'src/model/omiljeniKoncerti';

@Component({
  selector: 'app-lista-zelja',
  templateUrl: './lista-zelja.component.html',
  styleUrls: ['./lista-zelja.component.scss']
})
export class ListaZeljaComponent implements OnInit {

  lista: OmiljeniKoncerti[] = [];

  constructor(private korisnikService: KorisnikService) { }

  ngOnInit(): void {
    this.getKoncertiLista()
  }

  getKoncertiLista(){
    this.korisnikService.getListuZelja().subscribe((resp: OmiljeniKoncerti[]) => {
      this.lista = resp
    })
  }

  obrisiKoncertSaListe(koncert: OmiljeniKoncerti){
    console.log("brisanje "+koncert.idListaOmiljenihKoncerata)
    this.korisnikService.obrisiKoncertSaListe(koncert.idListaOmiljenihKoncerata).subscribe((resp: any) => {
      if(resp == true){
        this.getKoncertiLista()
      }else{
        alert('Došlo je do greške.')
      }
    })
  }
}
