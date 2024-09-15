<?php
include_once '../services/resgatarMangasApiExterna.php';
include_once '../services/alimentarSistemaService.php';

set_time_limit(0);

$mangas = listarMangas();
alimentarSistema($mangas);

?>