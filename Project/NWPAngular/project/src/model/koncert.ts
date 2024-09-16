import { Slika } from "./slika";
import { Zanr } from "./zanr";

export class Koncert{
    idKoncert: number = 0;
    adresa: string = "";
    grad: string = "";
    izvodjac: string = "";
    opis: string = "";
    maxBrojKarata: number = 0;
    trajanje: number = 0;
    cena: number = 0;
    slikas: Slika[] = [];
    zanr: Zanr = new Zanr();
    datumIzvodjenja: Date = new Date();
}