import { Usuario } from "./usuario";

export class Avaliacao {
    idUsuario: number | null;
    usuario: Usuario | null;
    idManga: number | null;
    nota: number;

    constructor() {
        this.idUsuario = 0;
        this.usuario = new Usuario();
        this.idManga = 0;
        this.nota = 0;
    }
}
