import { Component, OnInit } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meu-perfil',
  templateUrl: './meu-perfil.page.html',
  styleUrls: ['./meu-perfil.page.scss'],
})
export class MeuPerfilPage implements OnInit {
  usuario: Usuario;

  menu = [
    { titulo: "Editar Perfil", rota: "/editar-perfil", icone: "person-circle", cor: "primary", click: null },
    { titulo: 'Deslogar', rota: null, icone: 'exit', cor: "danger", click: () => this.sair() }
  ];

  segment = [
    { titulo: 'Perfil', value: 'perfil' },
    { titulo: 'Curtidos', value: 'curtidos' },
    { titulo: 'Ler Depois', value: 'lerDepois' },
    { titulo: 'Listas', value: 'listas' }
  ];

  constructor(private usuarioService: UsuarioService, private alertController: AlertController, private navController: NavController, private router: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit() {
    let idUsuario = this.usuarioService.recuperarAutenticacao();

    if (idUsuario) {
      this.usuarioService.buscarPorId(idUsuario)
        .then((json) => {
          this.usuario = <Usuario>json;
        })
        .catch((erro) => {
          console.error('Erro ao buscar usuário:', erro);
        });
    } else {
      console.warn('ID do usuário não encontrado');
    }
  }

  mangasCurtidos(): void{
    this.router.navigate(['/mangas-curtidos']);
  }

  lerDepois(): void{
    this.router.navigate(['/ler-depois']);
  }
  
  mangasLidos(): void{
    this.router.navigate(['/lidos']);
  }

  listas(): void{
    this.router.navigate(['/listas']);
  }

  async sair() {
    const alert = await this.alertController.create({
      header: 'Sair da sua conta?',
      buttons: [
        {
          text: 'Cancelar'
        }, {
          text: 'Sair',
          cssClass: 'danger',
          handler: () => {
            this.navController.navigateBack('/inicio');
          }
        }
      ]
    });
    await alert.present();
  }
}
