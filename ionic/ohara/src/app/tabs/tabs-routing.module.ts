import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children: [
      //{
      //  path: 'home',
     //   loadChildren: () => import('../pages/home/home.module').then(m => m.HomePageModule)
      //},
      //{
      //  path: 'pesquisa',
      //  loadChildren: () => import('../pages/pesquisa/pesquisa.module').then(m => m.PesquisaPageModule)
      //},
      {
        path: 'meu-perfil',
        loadChildren: () => import('../pages/meu-perfil/meu-perfil.module').then(m => m.MeuPerfilPageModule)
      },
      {
        path: '',
        redirectTo: 'meu-perfil',
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsPageRoutingModule { }
