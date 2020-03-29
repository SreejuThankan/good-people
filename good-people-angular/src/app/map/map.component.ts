import { Component, OnInit, Input } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {

  private CENTRAL_LONDON_LATLNG = L.latLng(51.510067, -0.133869);
  private DEFAULT_ZOOM = 13;

  private map: any;

  constructor() { }

  ngOnInit(): void {
    this.map = L.map('map', {
      center: this.CENTRAL_LONDON_LATLNG,
      zoom: this.DEFAULT_ZOOM
    });
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    tiles.addTo(this.map);
  }

  setView(latitude: number, longitude: number): void {
    const latlng = L.latLng(latitude, longitude);
    this.map.setView(latlng);
    L.marker(latlng).addTo(this.map);
  }

}
