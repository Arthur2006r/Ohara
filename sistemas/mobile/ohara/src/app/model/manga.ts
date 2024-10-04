export class Manga {
    idManga: number | null;
    titulo: string;
    autor: string;
    sinopse: string;
    capa: string;
    anoDePublicacao: string;
    status: string;
    qtdDeVolumes: number;
    qtdDeCapitulos: number;
    popularidade: number;

    constructor() {
        this.idManga = null;
        this.titulo = "";
        this.autor = "";
        this.sinopse = "";
        this.capa = "";
        this.anoDePublicacao = "";
        this.status = "";
        this.qtdDeVolumes = 0;
        this.qtdDeCapitulos = 0;
        this.popularidade = 0;
    }
}
