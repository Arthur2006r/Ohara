import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { VistoService } from 'src/app/services/visto.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-lidos',
  templateUrl: './lidos.page.html',
  styleUrls: ['./lidos.page.scss'],
})
export class LidosPage implements OnInit {

  idUsuario: number;
  mangasLidos: any[] = [];
  usuario: Usuario;


  constructor(private usuarioService: UsuarioService, private vistoService: VistoService, private toastController: ToastController) { 
    this.idUsuario = 0;
    this.usuario = new Usuario()
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();
  
    if (this.idUsuario) {
      this.carregarLidos();
      this.carregarUsuario();
    } else {
      console.warn('ID do usuário não encontrado');
    }
  }

  async carregarUsuario() {
    try {
      this.usuario = await this.usuarioService.buscarPorId(this.idUsuario);
    } catch (erro) {
      console.error('Erro ao buscar usuário:', erro);
    }
  }

  async carregarLidos() {
    if (this.idUsuario) {
      this.mangasLidos = await this.vistoService.consultarLidoDoUsuario(this.idUsuario);
    }
  }

  verificarItem(item: any) {
    console.log('Item completo:', item);
    console.log('ID do Mangá:', item.idManga);
    if (item.idManga) {
      this.removerLido(item.idManga);
    } else {
      console.error('ID do mangá inválido ou ausente.');
    }
  }

  async removerLido(idManga: number) {
    if (!idManga || !this.idUsuario) {
      console.error('ID do mangá ou do usuário inválido:', idManga, this.idUsuario);
      return;
    }
  
    try {
      await this.vistoService.excluir(idManga, this.idUsuario);
  
      await this.carregarLidos();
  
      const toast = await this.toastController.create({
        message: 'Mangá removido com sucesso.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    } catch (error) {
      console.error('Erro ao remover o mangá:', error);
  
      const toast = await this.toastController.create({
        message: 'Não foi possível remover o mangá.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    }
  }

}
