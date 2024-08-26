export class Usuario {
    idUsuario: number | null;
    nome: string;
    email: string;
    senha: string;
    // avatar: string;

    constructor() {
        this.idUsuario = null;
        this.nome = "";
        this.email = "";
        this.senha = "";
    }
}
