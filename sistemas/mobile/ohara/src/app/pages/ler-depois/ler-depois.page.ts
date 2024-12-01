import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Curtida } from 'src/app/model/curtida';
import { Usuario } from 'src/app/model/usuario';
import { LerDepoisService } from 'src/app/services/ler-depois.service';
import { MangaService } from 'src/app/services/manga.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-ler-depois',
  templateUrl: './ler-depois.page.html',
  styleUrls: ['./ler-depois.page.scss'],
})
export class LerDepoisPage implements OnInit {

  idUsuario: number;
  mangasLerDepois: any[] = [];
  usuario: Usuario;


  constructor(private lerDepoisService: LerDepoisService, private usuarioService: UsuarioService, private navController: NavController, private route: ActivatedRoute, private mangaService: MangaService, private activatedRoute: ActivatedRoute, private router: Router, private toastController: ToastController) {
    this.usuario = new Usuario();
    this.idUsuario = 0; 
  }

  ngOnInit() {
    this.idUsuario = this.usuarioService.recuperarAutenticacao();
  
    if (this.idUsuario) {
      this.carregarLerDepois();
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

  async carregarLerDepois() {
    if (this.idUsuario) {
      this.mangasLerDepois = await this.lerDepoisService.consultarLerDepoisDoUsuario(this.idUsuario);
    }
  }

  verificarItem(item: any) {
    console.log('Item completo:', item);
    console.log('ID do Mangá:', item.idManga);
    if (item.idManga) {
      this.removerLerDepois(item.idManga);
    } else {
      console.error('ID do mangá inválido ou ausente.');
    }
  }

  async removerLerDepois(idManga: number) {
    if (!idManga || !this.idUsuario) {
      console.error('ID do mangá ou do usuário inválido:', idManga, this.idUsuario);
      return;
    }
  
    try {
      await this.lerDepoisService.excluir(idManga, this.idUsuario);
  
      await this.carregarLerDepois();
  
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
