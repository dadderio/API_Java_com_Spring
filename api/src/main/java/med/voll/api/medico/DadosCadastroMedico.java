package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

//O que são Records? Os Records são uma nova forma de declarar classes no Java.
// Eles são imutáveis por padrão, o que significa que não podemos alterar seus valores após a criação do objeto.
// Além disso, eles são bastante úteis para representar dados, como, por exemplo, uma entidade de banco de dados.


public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp="\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {}
