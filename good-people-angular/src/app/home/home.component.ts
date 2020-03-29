import { MapComponent } from './../map/map.component';
import { PostcodeService } from './../@core/services/postcode.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NbToastrService } from '@nebular/theme';

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
    private postcodeService: PostcodeService,
    private nbToastrService: NbToastrService
  ) { }

  ngOnInit(): void {
  }

  confirm(): void {
    this.postcodeService.getPostCode(this.postcode).subscribe(
      data => {
        this.mapComponent.setView(data.result.latitude, data.result.longitude);
      },
      error => {
        this.nbToastrService.danger('Postcode ' + this.postcode + ' can not be found', 'Error');
        console.error(error);
      });
  }

}
