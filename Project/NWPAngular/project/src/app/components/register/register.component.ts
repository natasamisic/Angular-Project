import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(private korisnikService: KorisnikService, private router: Router) { }

  ngOnInit(): void {
  }

  register(userForm: any) {
    console.log(userForm.value.ime)
    this.korisnikService.register(userForm).subscribe((resp: any) => {
      if (resp == true) {
        alert("Uspešna registracija!")
        this.router.navigate(['/login'])
      } else {
        alert("Korisnik sa ovim korisničkim imenom već postoji! Probajte ponovo")
      }
    })
  }
}
