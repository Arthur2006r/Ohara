import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AssociacoesUsuarioMangaPage } from './associacoes-usuario-manga.page';

const routes: Routes = [
  {
    path: '',
    component: AssociacoesUsuarioMangaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AssociacoesUsuarioMangaPageRoutingModule {}
