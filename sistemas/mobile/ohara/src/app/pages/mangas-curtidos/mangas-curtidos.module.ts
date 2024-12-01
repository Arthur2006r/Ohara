import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MangasCurtidosPageRoutingModule } from './mangas-curtidos-routing.module';

import { MangasCurtidosPage } from './mangas-curtidos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MangasCurtidosPageRoutingModule
  ],
  declarations: [MangasCurtidosPage]
})
export class MangasCurtidosPageModule {}
