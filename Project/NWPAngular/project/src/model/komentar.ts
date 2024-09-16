import { Koncert } from "./koncert";
import { Korisnik } from "./korisnik";

export class Komentar{
    idKomentar: number = 0;
    tekst: string = "";
    koncert: Koncert = new Koncert();
    korisnik: Korisnik = new Korisnik();
}