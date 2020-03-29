import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(
    private http: HttpClient
  ) { }

  public getAllPerson(): any {
    return this.http.get('https://localhost:9000/api/v1/person');
  }

  public getPerson(id: string): any {
    return this.http.get('https://localhost:9000/api/v1/person' + id);
  }

  public createPerson(person: any): any {
    return this.http.put('https://localhost:9000/api/v1/person', person);
  }

}
