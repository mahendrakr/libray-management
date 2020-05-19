import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Book} from '../book-model/book.model';
@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

   baseUrl ="http://localhost:8080";
   getAllBooks(){
   	return this.http.get<Book[]>(`${this.baseUrl}/books`);
   }
}
