import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { Lista } from 'src/app/model/lista';
import { ListaManga } from 'src/app/model/lista-manga';
import { Usuario } from 'src/app/model/usuario';
import { listaMangaService } from 'src/app/services/lista-manga.service';
import { ListaService } from 'src/app/services/lista.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-add-lista',
  templateUrl: './add-lista.page.html',
  styleUrls: ['./add-lista.page.scss'],
})
export class AddListaPage implements OnInit {
  lista: Lista;
  formGroup: FormGroup;

  usuario: Usuario;

  constructor(private formBuilder: FormBuilder, private listaService: ListaService, private toastController: ToastController, private activatedRoute: ActivatedRoute, private navController: NavController, private listaMangaService: listaMangaService, private usuarioService: UsuarioService) {
    this.lista = new Lista();
    this.usuario = new Usuario();
    this.formGroup = this.formBuilder.group({
      titulo: ['', [Validators.required]]
    })
  }

  ngOnInit() {
    let id = parseFloat(this.activatedRoute.snapshot.params['id']);

    if (!isNaN(id)) {
      this.listaService.buscarPorId(id).then((json) => {
        this.lista = <Lista>(json);
        this.formGroup.get('titulo')?.setValue(this.lista.titulo);
      });
    }

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

  salvar() {
    debugger
    this.lista.titulo = this.formGroup.value.titulo
    this.lista.usuario = this.usuario;

    this.listaService.salvar(this.lista)
      .then((json) => {
        this.lista = <Lista>(json);
        if (this.lista) {
          this.exibirMensagem('Registro salvo com sucesso!!!');
          this.navController.navigateBack('/listas');
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
