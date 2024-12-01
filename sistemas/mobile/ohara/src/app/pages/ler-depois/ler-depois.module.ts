import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { LerDepoisPageRoutingModule } from './ler-depois-routing.module';

import { LerDepoisPage } from './ler-depois.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    LerDepoisPageRoutingModule
  ],
  declarations: [LerDepoisPage]
})
export class LerDepoisPageModule {}
