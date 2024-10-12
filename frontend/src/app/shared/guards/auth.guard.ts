import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { UserService } from '../services/user.service';
import { catchError, lastValueFrom, of } from 'rxjs';

export const authGuard: CanActivateFn = async (route, state) => {
  const userService = inject(UserService);
  const router = inject(Router);
  // check if user has an account, if so go to path, else go to register  
  let user = await lastValueFrom(userService.getUser().pipe(catchError(err => { return of(null) })));

  if (user) {
    // user is logged in, follow path
    return true;
  } else {
    // go to register page
    return router.createUrlTree(['/register']);
  }
};
