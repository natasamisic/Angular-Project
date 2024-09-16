import { Component, Input, OnInit } from '@angular/core';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Karta } from 'src/model/karta';

@Component({
  selector: 'app-rezervacije',
  templateUrl: './rezervacije.component.html',
  styleUrls: ['./rezervacije.component.scss']
})
export class RezervacijeComponent implements OnInit {

  karte: Karta[] = [];

  constructor(private korisnikService: KorisnikService) { }

  ngOnInit(): void {
    this.getKarte()
  }

  getKarte(){
    this.korisnikService.getRezervacije().subscribe((resp: Karta[]) => {
      this.karte = resp
    })
  }

  otkaziRezervaciju(karta: Karta){
    this.korisnikService.otkaziRezervaciju(karta.idKarta).subscribe((resp: any) => {
      if(resp == true){
        this.getKarte()
      }else{
        alert('Došlo je do greške. Rezervacija nije otkazana.')
      }
    })
  }
}
