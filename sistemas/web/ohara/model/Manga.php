<?php

class Manga {
    public $idManga;
    public $titulo;
    public $autor;
    public $sinopse;
    public $capa;
    public $anoDePublicacao;
    public $qtdDeVolumes;
    public $qtdDeCapitulos;
    public $popularidade;
    public $status;

    public function __construct($idManga, $titulo, $status, $sinopse, $anoDePublicacao, $qtdDeVolumes, $qtdDeCapitulos, $popularidade, $capa, $autor) {
        $this->idManga = $idManga;
        $this->titulo = $titulo;
        $this->status = $status;
        $this->sinopse = $sinopse;
        $this->anoDePublicacao = $anoDePublicacao;
        $this->qtdDeCapitulos = $qtdDeCapitulos;
        $this->qtdDeVolumes = $qtdDeVolumes;
        $this->popularidade = $popularidade;
        $this->capa = $capa;
        $this->autor = $autor;
    }
}

?>
