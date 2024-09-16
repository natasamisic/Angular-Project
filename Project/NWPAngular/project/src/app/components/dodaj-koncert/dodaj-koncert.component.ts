import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KoncertService } from 'src/app/services/koncert.service';
import { RadnikService } from 'src/app/services/radnik.service';
import { Zanr } from 'src/model/zanr';

@Component({
  selector: 'app-dodaj-koncert',
  templateUrl: './dodaj-koncert.component.html',
  styleUrls: ['./dodaj-koncert.component.scss']
})
export class DodajKoncertComponent implements OnInit {

  zanrovi: Zanr[] = [];
  uploadedImage: File;

  constructor(private koncertService: KoncertService, private radnikService: RadnikService, private router: Router) { }

  ngOnInit(): void {
    this.koncertService.getZanrovi().subscribe((resp: Zanr[]) => {
      this.zanrovi = resp
      console.log(this.zanrovi)
    })
  }

  dodajKoncert(koncertForm: any){
    this.radnikService.dodajKoncert(koncertForm).subscribe(response => {
      if(response == true){
        this.koncertService.searchKoncert(koncertForm.value.izvodjac).subscribe(resp => {
          this.imageUploadAction(resp.idKoncert)
        })
      }else{
        alert('Došlo je do greške prilikom čuvanja podataka!')
      }
    })
  }

  public onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
  }

  imageUploadAction(idKoncert: any) {
    const imageFormData = new FormData();
    imageFormData.append('slika', this.uploadedImage, this.uploadedImage.name);
    this.radnikService.sacuvajSliku(idKoncert, imageFormData).subscribe(resp => {
      if(resp == true){
        alert('Uspešno dodat!')
        this.router.navigate(['/svi-koncerti'])
      }else{
        alert('Došlo je do greške prilikom čuvanja slike!')
      }
    })
  }
}
