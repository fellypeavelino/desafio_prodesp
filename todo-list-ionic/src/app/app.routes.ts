import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'tarefas', pathMatch: 'full' },
  {
    path: 'home',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage),
    canActivate: [authGuard]
  },
  {
    path: 'home',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.page').then( m => m.LoginPage)
  },
  {
    path: 'categoria-list',
    loadComponent: () => import('./pages/categoria-list/categoria-list.page').then( m => m.CategoriaListPage),
    canActivate: [authGuard]
  },
  {
    path: 'categoria-form',
    loadComponent: () => import('./pages/categoria-form/categoria-form.page').then( m => m.CategoriaFormPage),
    canActivate: [authGuard]
  },
  {
    path: 'categoria-form/:id',
    loadComponent: () => import('./pages/categoria-form/categoria-form.page').then( m => m.CategoriaFormPage),
    canActivate: [authGuard]
  },
  {
    path: 'usuarios',
    loadComponent: () => import('./pages/usuarios/usuarios.page').then( m => m.UsuariosPage),
    canActivate: [authGuard]
  },
  {
    path: 'usuario-form',
    loadComponent: () => import('./pages/usuario-form/usuario-form.page').then( m => m.UsuarioFormPage),
    canActivate: [authGuard]
  },
  {
    path: 'usuario-form/:id',
    loadComponent: () => import('./pages/usuario-form/usuario-form.page').then( m => m.UsuarioFormPage),
    canActivate: [authGuard]
  },
  {
    path: 'tarefas',
    loadComponent: () => import('./pages/tarefas/tarefas.page').then( m => m.TarefasPage),
    canActivate: [authGuard]
  },
  {
    path: 'tarefa-form',
    loadComponent: () => import('./pages/tarefa-form/tarefa-form.page').then( m => m.TarefaFormPage),
    canActivate: [authGuard]
  },
  {
    path: 'tarefa-form/:id',
    loadComponent: () => import('./pages/tarefa-form/tarefa-form.page').then( m => m.TarefaFormPage),
    canActivate: [authGuard]
  },
];
