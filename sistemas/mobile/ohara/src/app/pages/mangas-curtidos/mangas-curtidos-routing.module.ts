import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MangasCurtidosPage } from './mangas-curtidos.page';

const routes: Routes = [
  {
    path: '',
    component: MangasCurtidosPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MangasCurtidosPageRoutingModule {}
