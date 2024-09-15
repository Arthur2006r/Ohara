import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { VisualizarMangaPageRoutingModule } from './visualizar-manga-routing.module';

import { VisualizarMangaPage } from './visualizar-manga.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VisualizarMangaPageRoutingModule
  ],
  declarations: [VisualizarMangaPage]
})
export class VisualizarMangaPageModule {}
