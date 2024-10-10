<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8" />
    <title>Sistema Ohara</title>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="css/estilo.css" />
    <style>
    </style>
</head>

<body>
    <div class="container">
        <h1 class="titulo mt-5">Sistema Privado da OHARA</h1>
        <hr>
        <p class="subTitulo mt-2">Sistema para alimentação do banco de dados da aplicação com mangás da API.</p>

        <div class="row mt-5">
            <div class="col-md-12 text-center">
                <form id="alimentarForm">
                    <button type="submit" class="btn botaoAlimentar">Alimentar Sistema</button>
                </form>
            </div>
        </div>
    </div>

    <div id="loader">
        <p id="texto">Carregando, por favor aguarde...</p>
        <div id="loadingBar"></div>
    </div>

    <div id="successMessage">Processo concluído com sucesso!</div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/additional-methods.js"></script>
    <script src="js/localization/messages_pt_BR.js"></script>
    <script>
        $(document).ready(function() {
            $('#alimentarForm').on('submit', function(event) {
                event.preventDefault();

                $('#loader').css('display', 'flex');

                $.ajax({
                    url: 'controller/alimentarSistemaController.php',
                    method: 'POST',
                    data: $(this).serialize(), 
                    success: function(response) {
                        $('#loader').css('display', 'none');

                        $('#successMessage').show();
                        setTimeout(function() {
                            $('#successMessage').hide();
                        }, 2000);
                    },
                    error: function() {
                        $('#loader').css('display', 'none');
                        alert('Ocorreu um erro durante o processamento.');
                    }
                });
            });
        });
    </script>
</body>

</html>