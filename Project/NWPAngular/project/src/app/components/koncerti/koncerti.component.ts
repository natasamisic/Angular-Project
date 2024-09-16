import { Component, Input, OnInit } from '@angular/core';
import { KoncertService } from 'src/app/services/koncert.service';
import { RadnikService } from 'src/app/services/radnik.service';
import { Koncert } from 'src/model/koncert';
import { Slika } from 'src/model/slika';
import { Zanr } from 'src/model/zanr';


@Component({
  selector: 'app-koncerti',
  templateUrl: './koncerti.component.html',
  styleUrls: ['./koncerti.component.scss']
})
export class KoncertiComponent implements OnInit {

  koncerti: Koncert[] =  [];
  koncerti1: Koncert[] = [];
  zanrovi: Zanr[] = [];

  constructor(private koncertService: KoncertService) { }

  ngOnInit() {
    this.koncertService.getKoncerti().subscribe((resp: Koncert[]) => {
      this.koncerti1 = resp
      this.koncerti = resp
    })
    this.koncertService.getZanrovi().subscribe((resp: Zanr[]) => {
      this.zanrovi = resp
    })
  }

   search($event: Event) {
    const searchKey = ($event.target as HTMLInputElement).value;

    if (searchKey === '') {
      this.koncerti = this.koncerti1;
      return;
    }

    this.koncerti = this.koncerti1.filter(
      (koncert) => koncert.izvodjac.toLowerCase().includes(searchKey.toLowerCase())
    );
  }

  searchZanr(event: string) {
    const searchKey = event;
    console.log('Search key '+searchKey)

    this.koncerti = this.koncerti1.filter(
      (koncert) => koncert.zanr.naziv.toLowerCase().includes(searchKey.toLowerCase())
    );
  }
}
