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
    const tiles = L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      maxZoom: 18,
      id: 'mapbox/streets-v11',
      tileSize: 512,
      zoomOffset: -1,
      accessToken: 'pk.eyJ1IjoicnlhbndvbmcxMTMiLCJhIjoiY2s4ZGdqb2J1MGFudzNmcGdlZXdod2loZiJ9.jymWoJynxVqB-Opm0C8eIQ'
    });
    tiles.addTo(this.map);
  }

  setView(latitude: number, longitude: number): void {
    const latlng = L.latLng(latitude, longitude);
    this.map.setView(latlng);
    L.marker(latlng).addTo(this.map);
  }

}
