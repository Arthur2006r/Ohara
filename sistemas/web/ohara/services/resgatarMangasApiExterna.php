<?php
include_once 'C:\xampp\htdocs\PWII2023\Ohara\ohara\model\Manga.php';

$base_url = "https://api.jikan.moe/v4/top/manga?page=";

function fazerRequisicaoApiExterna($url, $metodo = 'GET', $dados = null)
{
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $metodo);

    if ($dados) {
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($dados));
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen(json_encode($dados))
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

function listarMangas($contador)
{
    $page = $contador;
    $limite = $contador + 60;
    $mangas = [];

    while (true) {
        $url = "https://api.jikan.moe/v4/top/manga?page=" . $page;
        try {
            $data = fazerRequisicaoApiExterna($url);

            if (!isset($data['data'])) {
                return $mangas;
            }

            $mangasPage = $data['data'];
            if (empty($mangasPage)) {
                return $mangas;
            }

            foreach ($mangasPage as $manga) {
                $status = formatarStatus($manga['status'] ?? '');

                $anoPublicacao = '';
                if (!empty($manga['published']['from'])) {
                    $anoPublicacao = formatarAnoDePublicacao($manga['published']['from']);
                }

                $nomeAutor = '';
                if (!empty($manga['authors'])) {
                    $nomeAutor = formatarNomeAutor($manga['authors'][0]['name']);
                }

                $sinopse = '';
                if (!empty($manga['synopsis'])) {
                    $sinopse = formatarSinopse($manga['synopsis']);
                }

                $mangaObj = new Manga(
                    $manga['mal_id'],
                    $manga['title'] ?? 'N/A',
                    $status,
                    $sinopse ?? 'N/A',
                    $anoPublicacao,
                    $manga['volumes'] ?? 0,
                    $manga['chapters'] ?? 0,
                    $manga['popularity'] ?? 0,
                    $manga['images']['jpg']['image_url'] ?? 'N/A',
                    $nomeAutor
                );

                $mangaObj->sinopse = preg_replace('/<br\s*\/?>/', "\n", $mangaObj->sinopse);
                $mangaObj->sinopse = strip_tags($mangaObj->sinopse);

                $mangas[] = $mangaObj;
            }

            $page++;
            if($page == $limite) {
                break;
            }
        } catch (Exception $e) {
            echo "Erro ao acessar a API: " . $e->getMessage();
            break;
        }
    }

    return $mangas;
}

function obterTotalPaginas()
{
    $url = "https://api.jikan.moe/v4/top/manga?page=1";
    try {
        $data = fazerRequisicaoApiExterna($url);
        return $data['pagination']['last_visible_page']?? 0;
    } catch (Exception $e) {
        echo "Erro ao acessar a API: " . $e->getMessage();
        return 0;
    }
}

function formatarNomeAutor($nome)
{
    $partes = explode(', ', $nome);
    if (count($partes) === 2) {
        return $partes[1] . ' ' . $partes[0];
    }
    return $nome;
}

function formatarAnoDePublicacao($dataPublicacao)
{
    $dataPublicacao = new DateTime($dataPublicacao);
    return $dataPublicacao->format('Y');
}

function formatarStatus($status)
{
    switch ($status) {
        case 'Finished':
            return 'Finalizado';
        case 'Publishing':
            return 'Em lançamento';
        case 'Cancelled':
            return 'Cancelado';
        case 'On Hiatus':
            return 'Em hiato';
    }
}

function formatarSinopse($sinopse)
{
    $posicaoRewrite = strpos($sinopse, '[Written by MAL Rewrite]');
    if ($posicaoRewrite !== false) {
        $sinopse = substr($sinopse, 0, $posicaoRewrite);
        return $sinopse;
    }
}