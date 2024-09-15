import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VisualizarMangaPage } from './visualizar-manga.page';

const routes: Routes = [
  {
    path: '',
    component: VisualizarMangaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VisualizarMangaPageRoutingModule {}
