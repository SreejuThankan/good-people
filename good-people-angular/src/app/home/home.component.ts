import { PersonService } from './../@core/services/person.service';
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

  firstName: string;
  lastName: string;
  addressLine1: string;
  addressLine2: string;
  postcode: string;
  phoneNo: string;
  email: string;

  createdPerson: any;

  postcodeToCheck: string;

  constructor(
    private postcodeService: PostcodeService,
    private personService: PersonService,
    private nbToastrService: NbToastrService
  ) { }

  ngOnInit(): void {
  }

  createUser(): void {
    this.personService.createPerson({
      firstName: this.firstName,
      lastName: this.lastName,
      addressLine1: this.addressLine1,
      addressLine2: this.addressLine2,
      postCode: this.postcode,
      phoneNo: this.phoneNo,
      email: this.email
    });
  }

  checkPostcode(): void {
    this.postcodeService.getPostCode(this.postcodeToCheck).subscribe(
      data => {
        this.mapComponent.setView(data.result.latitude, data.result.longitude);
      },
      error => {
        this.nbToastrService.danger('Postcode ' + this.postcodeToCheck + ' is invalid', 'Error');
        console.error(error);
      });
  }

}
