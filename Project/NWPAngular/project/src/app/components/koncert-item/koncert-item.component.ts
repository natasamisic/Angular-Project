import { Byte } from '@angular/compiler/src/util';
import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { KoncertService } from 'src/app/services/koncert.service';
import { Koncert } from 'src/model/koncert';
import { Slika } from 'src/model/slika';

@Component({
  selector: 'app-koncert-item',
  templateUrl: './koncert-item.component.html',
  styleUrls: ['./koncert-item.component.scss']
})
export class KoncertItemComponent implements OnInit {

  @Input() koncert: Koncert;
  slika = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Muse_in_Sydney.jpg/800px-Muse_in_Sydney.jpg";

  ngOnInit(): void {
    this.slika = 'data:image/jpg;base64,'+this.koncert.slikas[0].slika
  }
  
}
