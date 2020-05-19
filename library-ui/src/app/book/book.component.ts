import { Component, OnInit } from '@angular/core';
import {BookService} from '../book-service/book.service';
import {Book} from '../book-model/book.model'
@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

books: Book[];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
  	this.bookService.getAllBooks().subscribe((data: Book[])=>{
  		this.books=data;
  		data.forEach(book=>{
  			console.log(book.bookId+" "+book.bookName+" "+book.author+" "+book.publication);
  		})
  	});
  }

}
