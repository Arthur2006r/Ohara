import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AssociacoesUsuarioMangaPageRoutingModule } from './associacoes-usuario-manga-routing.module';

import { AssociacoesUsuarioMangaPage } from './associacoes-usuario-manga.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AssociacoesUsuarioMangaPageRoutingModule
  ],
  declarations: [AssociacoesUsuarioMangaPage]
})
export class AssociacoesUsuarioMangaPageModule {}
