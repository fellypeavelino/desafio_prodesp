import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { NavController } from '@ionic/angular';
import { TokenService } from '../services/token.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const navCtrl = inject(NavController);
  const tokenService = inject(TokenService);
  const token = tokenService.getToken();
  if (authService.isLoggedIn() || token) {
    return true;
  } else {
    navCtrl.navigateRoot('/login');
    return false;
  }
};
