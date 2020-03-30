import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(
    private http: HttpClient
  ) { }

  public getAllPerson(): Observable<any> {
    return this.http.get('https://localhost:9000/api/v1/person');
  }

  public getPerson(id: string): Observable<any> {
    return this.http.get('https://localhost:9000/api/v1/person' + id);
  }

  public createPerson(person: any): Observable<any> {
    return this.http.put('https://localhost:9000/api/v1/person', person);
  }

}
