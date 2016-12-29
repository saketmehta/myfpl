import {Component} from "@angular/core";

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent {
  tiles = [
    {text: 'One', cols: 3, rows: 1, color: 'lightblue'},
    {text: 'Two', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
    {text: 'Five', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Six', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Seven', cols: 3, rows: 1, color: 'lightblue'}
  ];
}
