import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Curtida } from 'src/app/model/curtida';
import { Usuario } from 'src/app/model/usuario';
import { CurtidaService } from 'src/app/services/curtida.service';
import { MangaService } from 'src/app/services/manga.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-mangas-curtidos',
  templateUrl: './mangas-curtidos.page.html',
  styleUrls: ['./mangas-curtidos.page.scss'],
})
export class MangasCurtidosPage implements OnInit {
  idUsuario: number;
  mangasCurtidos: any[] = [];
  usuario: Usuario;


  constructor(private curtidaService: CurtidaService, private usuarioService: UsuarioService, private navController: NavController, private route: ActivatedRoute, private mangaService: MangaService, private activatedRoute: ActivatedRoute, private router: Router, private toastController: ToastController) {
    this.usuario = new Usuario();
    this.idUsuario = 0;
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();

    if (this.idUsuario) {
      this.carregarCurtidas();
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

  async carregarCurtidas() {
    if (this.idUsuario) {
      this.mangasCurtidos = await this.curtidaService.consultarCurtidasDoUsuario(this.idUsuario);
    }
  }

  verificarItem(item: any) {
    console.log('Item completo:', item);
    console.log('ID do Mangá:', item.idManga);
    if (item.idManga) {
      this.removerCurtida(item.idManga);
    } else {
      console.error('ID do mangá inválido ou ausente.');
    }
  }

  async removerCurtida(idManga: number) {
    if (!idManga || !this.idUsuario) {
      console.error('ID do mangá ou do usuário inválido:', idManga, this.idUsuario);
      return;
    }

    try {

      await this.curtidaService.excluir(idManga, this.idUsuario);

      await this.carregarCurtidas();

      const toast = await this.toastController.create({
        message: 'Curtida removida com sucesso.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    } catch (error) {
      console.error('Erro ao remover curtida:', error);

      const toast = await this.toastController.create({
        message: 'Não foi possível remover a curtida.',
        duration: 2000,
        color: 'danger'
      });
      await toast.present();
    }
  }
}


