import { Uloga } from "./uloga";

export class Korisnik{
    idKorisnik: number = 0;
    ime: string = "";
    prezime: string = "";
    username: string = "";
    password: string = "";
    email: string = "";
    uloga: Uloga = new Uloga();
}