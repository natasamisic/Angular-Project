import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'project';

  constructor(private router: Router){}

  checkLogin(): boolean{
    const user = localStorage.getItem("uloga")
    if(user != undefined){
      return true;
    }
    return false
  }

  checkLoginRadnik(): boolean{
    const user = localStorage.getItem("uloga")
    if(user != undefined && user.localeCompare('radnik') == 0){
      return true;
    }
    return false
  }

  checkLoginKorisnik(): boolean{
    const user = localStorage.getItem("uloga")
    if(user != undefined && user.localeCompare('korisnik') == 0){
      return true;
    }
    return false
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login'])
  }
}
