import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LidosPage } from './lidos.page';

const routes: Routes = [
  {
    path: '',
    component: LidosPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LidosPageRoutingModule {}
