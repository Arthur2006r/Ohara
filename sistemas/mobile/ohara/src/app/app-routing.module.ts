import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full'
  },
  {
    path: 'cadastro-usuario',
    loadChildren: () => import('./pages/cadastro-usuario/cadastro-usuario.module').then(m => m.CadastroUsuarioPageModule)
  },
  {
    path: 'login-usuario',
    loadChildren: () => import('./pages/login-usuario/login-usuario.module').then(m => m.LoginUsuarioPageModule)
  },
  {
    path: 'inicio',
    loadChildren: () => import('./pages/inicio/inicio.module').then(m => m.InicioPageModule)
  },
  {
    path: 'tabs',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'editar-perfil',
    loadChildren: () => import('./pages/editar-perfil/editar-perfil.module').then(m => m.EditarPerfilPageModule)
  },
  {
    path: 'meu-perfil',
    loadChildren: () => import('./pages/meu-perfil/meu-perfil.module').then(m => m.MeuPerfilPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then(m => m.HomePageModule)
  },
  {
    path: 'manga',
    loadChildren: () => import('./pages/manga/manga.module').then(m => m.MangaPageModule)
  },
  {
    path: 'visualizar-manga/:idManga',
    loadChildren: () => import('./pages/visualizar-manga/visualizar-manga.module').then(m => m.VisualizarMangaPageModule)
  },
  {
    path: 'reviews/:idManga',
    loadChildren: () => import('./pages/reviews/reviews.module').then(m => m.ReviewsPageModule)
  },
  {
    path: 'avaliacoes/:idManga',
    loadChildren: () => import('./pages/avaliacoes/avaliacoes.module').then(m => m.AvaliacoesPageModule)
  },
  {
    path: 'add-review/:idManga',
    loadChildren: () => import('./pages/add-review/add-review.module').then(m => m.AddReviewPageModule)
  },
  {
    path: 'add-review/:idManga/:idReview',
    loadChildren: () => import('./pages/add-review/add-review.module').then(m => m.AddReviewPageModule)
  },
  {
    path: 'associacoes-usuario-manga/:associacao/:idUsuario',
    loadChildren: () => import('./pages/associacoes-usuario-manga/associacoes-usuario-manga.module').then( m => m.AssociacoesUsuarioMangaPageModule)
  },
  {
    path: 'mangas-curtidos',
    loadChildren: () => import('./pages/mangas-curtidos/mangas-curtidos.module').then( m => m.MangasCurtidosPageModule)
  },
  {
    path: 'ler-depois',
    loadChildren: () => import('./pages/ler-depois/ler-depois.module').then( m => m.LerDepoisPageModule)
  },
  {
    path: 'lidos',
    loadChildren: () => import('./pages/lidos/lidos.module').then( m => m.LidosPageModule)

  },  {
    path: 'listas',
    loadChildren: () => import('./pages/listas/listas.module').then( m => m.ListasPageModule)
  },
  {
    path: 'add-lista',
    loadChildren: () => import('./pages/add-lista/add-lista.module').then( m => m.AddListaPageModule)
  },
  {
    path: 'editar-lista',
    loadChildren: () => import('./pages/editar-lista/editar-lista.module').then( m => m.EditarListaPageModule)
  },
  {
    path: 'perfil-outro-usuario',
    loadChildren: () => import('./pages/perfil-outro-usuario/perfil-outro-usuario.module').then( m => m.PerfilOutroUsuarioPageModule)
  }



];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
