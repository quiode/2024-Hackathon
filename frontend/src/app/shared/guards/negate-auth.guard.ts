import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { catchError, lastValueFrom, of } from 'rxjs';

export const negateAuthGuard: CanActivateFn = async (route, state) => {
  const userService = inject(UserService);
  const router = inject(Router);
  // check if user has an account, if so go to path, else go to register  
  let user = await lastValueFrom(userService.getUser().pipe(catchError(err => { return of(null) })));

  if (user) {
    // user is logged in, go to dashboard
    return router.createUrlTree(['']);
  } else {
    // go to register page
    return true;
  }
};
