import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuardKorisnik implements CanActivate {

    public uloga!: string

    constructor(private router:Router){}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        console.log(localStorage.getItem("uloga"))
        var x = localStorage.getItem("uloga");
        if (x != null){
            this.uloga = x; 
            if (localStorage.getItem("token") && this.uloga.localeCompare("korisnik") == 0){
                console.log("proveren token i uloga, poklapa se sa korisnikom.")
                return true;
            }
        }    
        alert("Niste ulogovani!")
        this.router.navigate(['/login'])
        return false;
    }
}