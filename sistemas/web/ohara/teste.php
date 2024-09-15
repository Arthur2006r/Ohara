<?php
include_once 'services/alimentarSistemaService.php';

echo "Carregando...";

$usuarios = teste();

if (!empty($usuarios) && is_array($usuarios)) {
    // Acessa o primeiro usuário e imprime seus valores do array associativo
    $usuario = $usuarios[0];

    echo "ID: " . $usuario['idUsuario'] . "<br>";
    echo "Nome: " . $usuario['nome'] . "<br>";
    echo "Email: " . $usuario['email'] . "<br>";
    echo "Avatar: " . $usuario['avatar'] . "<br>";
} else {
    echo "Nenhum usuário encontrado.";
}

echo "Finalizado!";

exit();
?>