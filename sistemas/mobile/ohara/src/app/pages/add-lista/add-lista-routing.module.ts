import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddListaPage } from './add-lista.page';

const routes: Routes = [
  {
    path: '',
    component: AddListaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddListaPageRoutingModule {}
