import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { KoncertService } from 'src/app/services/koncert.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { RadnikService } from 'src/app/services/radnik.service';
import { Karta } from 'src/model/karta';
import { Komentar } from 'src/model/komentar';
import { Koncert } from 'src/model/koncert';

@Component({
  selector: 'app-koncert-view',
  templateUrl: './koncert-view.component.html',
  styleUrls: ['./koncert-view.component.scss']
})
export class KoncertViewComponent implements OnInit {

  koncert: Koncert = new Koncert();
  komentari: Komentar[] = [];
  karte: Karta[] = [];
  brojKarata: number = 0;
  koncertNaListi: boolean;
  slika = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Muse_in_Sydney.jpg/800px-Muse_in_Sydney.jpg";

  constructor(private koncertService: KoncertService, private korisnikService: KorisnikService, private route: ActivatedRoute, private radnikService: RadnikService, private router: Router) {

  }

  ngOnInit(): void {
    const izvodjac = this.route.snapshot.paramMap.get('izvodjac');
    this.koncertService.searchKoncert(izvodjac!).subscribe((resp: Koncert) => {
      this.koncert = resp
      this.slika = 'data:image/jpeg;base64,'+this.koncert.slikas[0].slika
      this.korisnikService.proveraDaLiJeNaListiVec(this.koncert.idKoncert).subscribe((resp: any) => {
        this.koncertNaListi = resp
      })
      this.radnikService.getBrojKarataZaKoncert(this.koncert.idKoncert).subscribe((resp: any) => {
        this.brojKarata = resp   
      })
      this.radnikService.getKarteKoncerta(this.koncert.idKoncert).subscribe((resp: Karta[]) => {
        this.karte = resp
      })
      this.getKomentari()
    }) 
  }

  getKomentari(){
    this.koncertService.getKomentari(this.koncert.idKoncert).subscribe((res: Komentar[]) => {
      this.komentari = res
    })
  }

  addKomentar(form: any) {
    this.korisnikService.addKomentar(form.value.tekst, this.koncert.idKoncert).subscribe((resp: any) => {
      if (resp == true) {
        form.reset()
        this.getKomentari()
      } else {
        alert("Došlo je do greške. Komentar nije dodat.")
      }
    })
  }

  checkLoginKorisnik(): boolean {
    const user = localStorage.getItem("uloga")
    if (user != undefined && user.localeCompare('korisnik') == 0) {
      return true;
    }
    return false
  }

  checkLoginRadnik(): boolean {
    const user = localStorage.getItem("uloga")
    if (user != undefined && user.localeCompare('radnik') == 0) {
      return true;
    }
    return false
  }

  dodajUListuZelja() {
    if (this.koncertNaListi) {
      alert('Koncert se već nalazi na vašoj listi želja!')
    } else {
      this.korisnikService.dodajUListuZelja(this.koncert.idKoncert).subscribe((resp: any) => {
        if (resp == true) {
          alert('Koncert uspešno dodat u listu želja!')
        } else {
          alert('Došlo je do greške. Koncert nije dodat u listu želja!')
        }
      })
    }
  }

  rezervisiKartu() {
    this.korisnikService.rezervisiKartu(this.koncert.idKoncert).subscribe((resp: any) => {
      if (resp == true) {
        alert('Rezervacija uspešna!')
      } else {
        alert('Došlo je do greške. Rezervacija nije uspela!')
      }
    })
  }

  obrisiKoncert(){
    this.radnikService.obrisiKoncert(this.koncert.idKoncert).subscribe((resp: any) => {
      if(resp == true){
        alert('Koncert uspešno obrisan!')
        this.router.navigate(['/svi-koncerti'])
      }else{
        alert('Došlo je do greške! Koncert nije obrisan!')
      }
    })
  }

  proveriKomentarKorisnika(komentar: Komentar){
    return komentar.korisnik.idKorisnik == Number(localStorage.getItem('idKorisnik'))
  }

  obrisiKomentar(idKomentar: number){
    this.korisnikService.obrisiKomentar(idKomentar).subscribe((resp: any) => {
      if(resp == true){
        this.getKomentari()
      }else{
        alert('Došlo je do greške! Komentar nije obrisan!')
      }
    })
  }
}
