import { HttpInterceptorFn } from '@angular/common/http';
import { LoadingService } from '../services/loading.service';
import { inject } from '@angular/core';
import { finalize } from 'rxjs';
import { TokenService } from '../services/token.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);
  const tokenService = inject(TokenService);
  const authToken = tokenService.getToken();
  //console.log(authToken);
  const body:any = req.body;
  if (body.page && body.page > 0) {
    loadingService.setLoading(true); 
  }
  let authReq = req.clone();
  if (authToken) {
    authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authToken}`
      }
    });    
  }

  return next(authReq).pipe(
    finalize(() => loadingService.setLoading(false))
  );
};