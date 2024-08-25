package br.com.ohara.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

    private Long idUsuario;

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 45, message = "Nome não pode exceder 45 caracteres")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    @Size(max = 45, message = "Email não pode exceder 45 caracteres")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 1, max = 45, message = "Senha deve ter entre 6 e 45 caracteres")
    private String senha;

    @Size(max = 45, message = "Avatar não pode exceder 45 caracteres")
    private String avatar;
}
