import { Component, Input, OnInit } from '@angular/core';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Korisnik } from 'src/model/korisnik';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  korisnik: Korisnik;

  constructor(private korisnikService: KorisnikService) { }

  ngOnInit(): void {
    const id = localStorage.getItem('idKorisnik')
    this.korisnikService.getProfil(id!).subscribe((resp: Korisnik) => {
        this.korisnik = resp
        console.log(this.korisnik)
    })
  }

}
