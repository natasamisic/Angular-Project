import { Koncert } from "./koncert";
import { Korisnik } from "./korisnik";

export class Karta{
    idKarta: number = 0;
    datumRezervacije: string = "";
    koncert: Koncert = new Koncert();
    korisnik: Korisnik = new Korisnik();
}