import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.page.html',
  styleUrls: ['./editar-perfil.page.scss'],
})
export class EditarPerfilPage implements OnInit {

  usuario: Usuario;
  formGroup: FormGroup;
  avatarPreview: string | ArrayBuffer | null = null;

  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private toastController: ToastController, private navController: NavController, private alertController: AlertController) {
    this.usuario = new Usuario();
    this.formGroup = this.formBuilder.group({
      'nome': [this.usuario.nome, Validators.compose([Validators.required])],
      'email': [this.usuario.email, Validators.compose([Validators.required, Validators.email])],
      'senha': [this.usuario.senha, Validators.compose([Validators.required])],
    })
  }

  ngOnInit() {
    let idUsuario = this.usuarioService.recuperarAutenticacao();

    if (idUsuario) {
      this.usuarioService.buscarPorId(idUsuario)
        .then((json) => {
          this.usuario = <Usuario>(json);
          if (this.usuario) {
            this.formGroup.get('nome')?.setValue(this.usuario.nome);
            this.formGroup.get('email')?.setValue(this.usuario.email);
            this.formGroup.get('senha')?.setValue(this.usuario.senha);
          }
        })
        .catch((erro => {
          this.exibirMensagem('Erro ao buscar usuario! Erro: ' + erro['mensage']);
        }));
    }
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

  salvar() {
    this.usuario.nome = this.formGroup.value.nome;
    this.usuario.email = this.formGroup.value.email;
    this.usuario.senha = this.formGroup.value.senha;

    this.usuarioService.salvar(this.usuario)
      .then((json) => {
        this.usuario = <Usuario>(json);
        if (this.usuario) {
          this.exibirMensagem('Registro salvo com sucesso!!!')
          this.navController.navigateBack('/meu-perfil')
        }
      })
      .catch((erro => {
        this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
      }));

  }

  async excluir() {
    const alert = await this.alertController.create({
      header: 'Você deseja excluir sua conta permanentemente?',
      buttons: [
        {
          text: 'Cancelar'
        },
        {
          text: 'Excluir Conta',
          cssClass: 'danger',
          handler: () => {
            this.usuarioService.excluir(this.usuario.idUsuario)
              .then(() => {
                this.exibirMensagem('Registro excluído com sucesso!!!');
                this.navController.navigateBack("/inicio")
              }).catch(() => {
                this.exibirMensagem('Erro ao excluir o registro.');
              });
          }
        }
      ]
    });

    await alert.present();
  }

  voltar() {
    this.navController.back();
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present()
  }
}
