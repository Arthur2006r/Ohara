import { Usuario } from "./usuario";

export class Review {
    idReview: number | null;
    usuario: Usuario;
    idManga: number | null;
    descricao: string;

    constructor() {
        this.idReview = null;
        this.usuario = new Usuario();
        this.idManga = 0;
        this.descricao = "";
    }
}
