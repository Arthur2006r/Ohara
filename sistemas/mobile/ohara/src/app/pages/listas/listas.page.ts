import { Component, OnInit } from '@angular/core';
import { ToastController } from '@ionic/angular';
import { Lista } from 'src/app/model/lista';
import { Usuario } from 'src/app/model/usuario';
import { ListaService } from 'src/app/services/lista.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-listas',
  templateUrl: './listas.page.html',
  styleUrls: ['./listas.page.scss'],
})
export class ListasPage implements OnInit {
  idUsuario: number;
  listas: Lista[];
  usuario: Usuario;


  constructor(private listaService: ListaService, private usuarioService: UsuarioService, private toastController: ToastController) {
    this.listas = [];
    this.usuario = new Usuario();
    this.idUsuario = 0;
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    if (this.idUsuario) {
      this.carregarListas();
      this.carregarUsuario();
    } else {
      console.warn('ID do usuário não encontrado');
    }

    console.log('ID do usuário recuperado:', this.idUsuario);
  }

  async carregarUsuario() {
    try {
      this.usuario = await this.usuarioService.buscarPorId(this.idUsuario);
    } catch (erro) {
      console.error('Erro ao buscar usuário:', erro);
    }
  }

  async carregarListas() {
    debugger
    if (this.idUsuario) {
      this.listas = await this.listaService.consultarTodosUsuario(this.idUsuario);
    }
  }

  verificarItem(item: any) {
    console.log('Item completo:', item);
    console.log('ID do Mangá:', item.idLista);
    if (item.idLista) {
      this.excluirLista(item.idLista);
    } else {
      console.error('ID do mangá inválido ou ausente.');
    }
  }

  async excluirLista(idLista: null | number) {
    try {

      await this.listaService.excluir(idLista);

      await this.carregarListas();

      const toast = await this.toastController.create({
        message: 'Lista removida com sucesso.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    } catch (error) {
      console.error('Erro ao remover lista:', error);

      const toast = await this.toastController.create({
        message: 'Não foi possível remover a lista.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    }
  }
}
