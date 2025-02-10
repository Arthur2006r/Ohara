import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EditarListaPageRoutingModule } from './editar-lista-routing.module';
import { ReactiveFormsModule } from '@angular/forms';

import { EditarListaPage } from './editar-lista.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EditarListaPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [EditarListaPage]
})
export class EditarListaPageModule {}
