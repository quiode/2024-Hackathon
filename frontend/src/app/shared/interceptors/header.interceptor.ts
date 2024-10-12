import { HttpInterceptorFn } from '@angular/common/http';
import { isDevMode } from '@angular/core';

/**
 * Creates the required auth headers in dev mode
 */
export const headerInterceptor: HttpInterceptorFn = (req, next) => {
  // set header to dev value
  let reqWithHeader = req;
  if (isDevMode()) {
    reqWithHeader = reqWithHeader.clone({
      headers: reqWithHeader.headers.set('X-authentik-username', 'dschwaiger'),
    });
    reqWithHeader = reqWithHeader.clone({
      headers: reqWithHeader.headers.set('X-authentik-name', 'Dominik Schwaiger'),
    });
    reqWithHeader = reqWithHeader.clone({
      headers: reqWithHeader.headers.set('X-authentik-email', 'dschwaiger@ethz.ch'),
    });
  }

  return next(reqWithHeader);
};
