import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LerDepoisPage } from './ler-depois.page';

const routes: Routes = [
  {
    path: '',
    component: LerDepoisPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LerDepoisPageRoutingModule {}
