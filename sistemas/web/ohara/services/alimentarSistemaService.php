<?php

$base_url = 'http://localhost:8087/api/v1/manga/';

function fazerRequisicaoApi($url, $metodo, $dados)
{
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $metodo);

    if ($dados) {
        $dadosJson = json_encode($dados, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $dadosJson);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($dadosJson)
        ]);
    }

    $resposta = curl_exec($ch);
    $statusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    curl_close($ch);

    if ($statusCode != 200) {
        throw new Exception("Erro ao acessar a API: " . $statusCode);
    }

    return json_decode($resposta, true);
}

function alimentarSistema($mangas)
{
    $endpoint = 'http://localhost:8080/api/v1/alimentar/sistema/';

    return fazerRequisicaoApi($endpoint, 'POST', $mangas);
}
