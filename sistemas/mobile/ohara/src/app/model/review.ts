import { Usuario } from "./usuario";

export class Review {
    idReview: number | null;
    usuario: Usuario | null;
    idUsuario: number;
    idManga: number | null;
    descricao: string;

    constructor() {
        this.idReview = null;
        this.usuario = new Usuario();
        this.idUsuario = 0;
        this.idManga = 0;
        this.descricao = "";
    }
}
