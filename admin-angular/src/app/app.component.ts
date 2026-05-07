import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

// Root container; route views are rendered via RouterOutlet.
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<h1>ScholarSync Admin</h1><router-outlet></router-outlet>`
})
export class AppComponent {}

