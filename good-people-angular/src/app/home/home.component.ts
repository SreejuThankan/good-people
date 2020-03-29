import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  users = [
    {name: 'Sreewho', address: '4th floor'}
  ]

  constructor() { }

  ngOnInit(): void {
  }

  confirmHelpOthers(): void {

  }

  confirmNeedHelp(): void {

  }

}
