import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login-usuario',
  templateUrl: './login-usuario.page.html',
  styleUrls: ['./login-usuario.page.scss'],
})
export class LoginUsuarioPage implements OnInit {
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private toastController: ToastController, private navController: NavController) { 
    this.formGroup = this.formBuilder.group({ 
      'email': ["", Validators.compose([Validators.required, Validators.email])],
      'senha': ["", Validators.compose([Validators.required])],
    })
  }

  ngOnInit() {
    this.usuarioService.encerrarAutenticacao();
  }

  autenticar(){   
    let email = this.formGroup.value.email;
    let senha = this.formGroup.value.senha;

    this.usuarioService.autenticar(email, senha)
    .then((json)=>{
      let usuario = <Usuario>(json);

      if(usuario === undefined){
        this.exibirMensagem('Email e/ou senha inválidos!!!')
      }else{
        this.usuarioService.registrarAutenticacao(usuario);
        this.navController.navigateBack('/tabs/meu-perfil')
      }
    })
    .catch((erro=>{
      this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
    }));
  }

  async exibirMensagem(texto: string){
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present()
  }

}
