import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtService } from './jwt-service';

export const adminGuard: CanActivateFn = (route, state) => {
  const jwtService = inject(JwtService);
  const router = inject(Router);

  if(jwtService.getRol() === 'ADMINISTRADOR') {
    return true;
  }

  return router.createUrlTree(['/login']);
};
