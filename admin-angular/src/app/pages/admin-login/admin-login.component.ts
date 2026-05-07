import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';

// Admin login using backend /auth/login. Access only if role is ADMIN.
@Component({
  selector: 'app-admin-login',
  standalone: true,
  imports: [FormsModule],
  template: `<h2>Login admin</h2>
  <input [(ngModel)]="email" placeholder="email" />
  <input [(ngModel)]="password" placeholder="password" type="password" />
  <button (click)="login()">Entrar</button>
  <p>{{msg}}</p>`
})
export class AdminLoginComponent {
  email = 'admin@scholarsync.com';
  password = '123456';
  msg = '';
  constructor(private api: AdminApiService, private router: Router) {}
  login(): void {
    this.api.login(this.email, this.password).subscribe({
      next: (user) => {
        if (user.role === 'ADMIN') {
          localStorage.setItem('admin', JSON.stringify(user));
          this.router.navigate(['/admin/events']);
        } else this.msg = 'Acesso negado';
      },
      error: () => (this.msg = 'Login invalido')
    });
  }
}

