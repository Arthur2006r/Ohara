import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-meu-perfil',
  templateUrl: './meu-perfil.page.html',
  styleUrls: ['./meu-perfil.page.scss'],
})
export class MeuPerfilPage implements OnInit {
  usuario: Usuario;

  constructor(private usuarioService: UsuarioService, private toastController: ToastController, private navController: NavController) {
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
  
}
