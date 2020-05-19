import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import  {Routes,RouterModule} from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';

const appRoutes: Routes = [
  {path:"show-book",component:BookComponent}
  
 ]

@NgModule({
  declarations: [
    AppComponent,
    BookComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
