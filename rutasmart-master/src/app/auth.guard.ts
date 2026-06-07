import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {

  const router = inject(Router);

  const email = localStorage.getItem('rutasmart.email');

  if (email) {
    return true;
  }

  router.navigate(['/login']);

  return false;
};
