import { Manga } from "./manga";
import { Usuario } from "./usuario";

export class Lista {
    idLista: number | null;
    titulo: string;
    mangas: Manga[];
    usuario: Usuario;

    constructor() {
        this.idLista = null;
        this.titulo = "";
        this.mangas = [];
        this.usuario = new Usuario();
    }
}
