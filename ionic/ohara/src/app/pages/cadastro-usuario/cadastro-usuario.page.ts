import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.page.html',
  styleUrls: ['./cadastro-usuario.page.scss'],
})
export class CadastroUsuarioPage implements OnInit {
  usuario: Usuario;
  formGroup: FormGroup;
  avatarPreview: string | ArrayBuffer | null = null;

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private toastController: ToastController, private navController: NavController) {
    this.usuario = new Usuario();
    this.formGroup = this.formBuilder.group({
      'avatar': [null, null],
      'nome': ["", Validators.compose([Validators.required])],
      'email': ["", Validators.compose([Validators.required, Validators.email])],
      'senha': ["", Validators.compose([Validators.required])],
    })
  }

  ngOnInit() {
    this.usuarioService.encerrarAutenticacao();
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.avatarPreview = reader.result;
        this.formGroup.patchValue({
          avatar: file
        });
      };
      reader.readAsDataURL(file);
    }
  }

  cadastrar() {
    // this.usuario.avatar = this.formGroup.value.avatar;
    this.usuario.nome = this.formGroup.value.nome;
    this.usuario.email = this.formGroup.value.email;
    this.usuario.senha = this.formGroup.value.senha;

    this.usuarioService.salvar(this.usuario)
      .then((json) => {
        let usuario = <Usuario>(json);

        if (usuario === undefined) {
          this.exibirMensagem('Email e/ou senha inválidos!!!')
        } else {
          this.navController.navigateBack('/login-usuario')
        }
      })
      .catch((erro => {
        this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
      }));
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present()
  }
}
