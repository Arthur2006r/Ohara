<?php
include_once '../services/resgatarMangasApiExterna.php';
include_once '../services/alimentarSistemaService.php';

set_time_limit(0);

$totalPaginas = obterTotalPaginas();

for ($contador = 1; $contador <= $totalPaginas; $contador += 60) {
    $mangas = listarMangas($contador);
    sleep(5);
    alimentarSistema($mangas);
    
    sleep(65);
}
