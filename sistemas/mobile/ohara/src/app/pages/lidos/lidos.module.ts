import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { LidosPageRoutingModule } from './lidos-routing.module';

import { LidosPage } from './lidos.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    LidosPageRoutingModule
  ],
  declarations: [LidosPage]
})
export class LidosPageModule {}
