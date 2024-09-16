import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { KoncertService } from 'src/app/services/koncert.service';
import { RadnikService } from 'src/app/services/radnik.service';
import { Koncert } from 'src/model/koncert';
import { Zanr } from 'src/model/zanr';

@Component({
  selector: 'app-azuriraj-koncert',
  templateUrl: './azuriraj-koncert.component.html',
  styleUrls: ['./azuriraj-koncert.component.scss']
})
export class AzurirajKoncertComponent implements OnInit {

  koncert: Koncert = new Koncert();
  zanrovi: Zanr[] = [];

  constructor(private route: ActivatedRoute, private koncertService: KoncertService, private radnikService: RadnikService, private router: Router) { }

  ngOnInit(): void {
    const izvodjac = this.route.snapshot.paramMap.get('izvodjac');
    this.koncertService.searchKoncert(izvodjac!).subscribe((resp: Koncert) => {
      this.koncert = resp
    })
    this.koncertService.getZanrovi().subscribe((resp: Zanr[]) => {
      this.zanrovi = resp
      console.log(this.zanrovi)
    })
  }

  azurirajKoncert(koncertForm: any){
    this.radnikService.azurirajKoncert(this.koncert.idKoncert, koncertForm).subscribe((resp: any) => {
      if(resp == true){
        alert('Podaci koncerta su uspešno ažurirani!')
        this.router.navigate(['/prikaz-koncerta/'+this.koncert.izvodjac])
      }else{
        alert('Došlo je do greške. Podaci koncerta nisu ažurirani.')
      }
    })
  }
}
