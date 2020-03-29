import { MapComponent } from './../map/map.component';
import { PostcodeService } from './../@core/services/postcode.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('map') mapComponent: MapComponent;

  name: string;
  postcode: string;

  constructor(
    private postcodeService: PostcodeService
  ) { }

  ngOnInit(): void {
  }

  confirm(): void {
    this.postcodeService.getPostCode(this.postcode).subscribe(data => {
      this.mapComponent.setView(data.result.latitude, data.result.longitude);
    });
  }

}
