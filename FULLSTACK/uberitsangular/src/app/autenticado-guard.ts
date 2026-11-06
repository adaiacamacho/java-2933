import { CanActivateFn, Router } from '@angular/router';
import { JwtService } from './jwt-service';
import { inject } from '@angular/core';

export const autenticadoGuard: CanActivateFn = (route, state) => {
  const jwtService = inject(JwtService);
  const router = inject(Router);

  if(jwtService.autenticado()) {
    return true;
  }

  return router.createUrlTree(['/login']);
};
