import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LoadingController, NavController, ToastController } from '@ionic/angular';
import { Lista } from 'src/app/model/lista';
import { ListaManga } from 'src/app/model/lista-manga';
import { Manga } from 'src/app/model/manga';
import { listaMangaService } from 'src/app/services/lista-manga.service';
import { ListaService } from 'src/app/services/lista.service';
import { MangaService } from 'src/app/services/manga.service';

@Component({
  selector: 'app-editar-lista',
  templateUrl: './editar-lista.page.html',
  styleUrls: ['./editar-lista.page.scss'],
})
export class EditarListaPage implements OnInit {
  lista: Lista;
  formGroup: FormGroup;

  mangasLista: Manga[];
  mangasModal: Manga[];

  constructor(private formBuilder: FormBuilder, private listaService: ListaService, private toastController: ToastController, private activatedRoute: ActivatedRoute, private navController: NavController, private listaMangaService: listaMangaService, private loadingController: LoadingController, private mangaService: MangaService) {
    this.lista = new Lista();
    this.mangasLista = [];
    this.mangasModal = [];
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
  }

  salvar() {
    this.lista.titulo = this.formGroup.value.descricao;

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

  adicionarMangaLista(idManga: number | null): void {
    let listaManga = new ListaManga();
    listaManga.idLista = this.lista.idLista;
    listaManga.idManga = idManga;
    this.listaMangaService.salvar(listaManga);
  }

  async carregarMangasLista() {
    this.exibirLoader();
    this.listaMangaService.consultarMangasLista(this.lista.idLista)
      .then((json) => {
        this.mangasLista = json;
      })
      .catch((error) => {
        console.error('Erro ao buscar mangás', error);
      });
    this.fecharLoader();
  }

  async carregarMangasModal() {
    this.exibirLoader();
    this.mangaService.consultarTop10()
      .then((json) => {
        this.mangasModal = json;
      })
      .catch((error) => {
        console.error('Erro ao buscar mangás', error);
      });
    this.fecharLoader();
  }

  exibirLoader() {
    this.loadingController.create({
      message: 'Carregando...'
    }).then((res) => {
      res.present();
    });
  }

  fecharLoader() {
    setTimeout(() => {
      this.loadingController.dismiss().then(() => {
      }).catch((erro) => {
        console.log('Erro: ', erro);
      });
    }, 500);
  }

  dismiss() {
    this.navController.back(); 
  }
}
