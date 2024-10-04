export class Review {
    idReview: number | null;
    idUsuario: number | null;
    idManga: number | null;
    descricao: string;

    constructor() {
        this.idReview = null;
        this.idUsuario = 0;
        this.idManga = 0;
        this.descricao = "";
    }
}
