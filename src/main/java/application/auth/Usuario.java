package application.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_de_usuario", nullable = false, unique = true)
    private String nomeDeUsuario;

    @Column(nullable = false)
    private String senha;

    // Nova propriedade para roles (papéis do usuário)
    @Column(name = "roles", nullable = false)
    private String roles;  // Exemplo simples de roles como String

    // Método para retornar as roles como lista (caso você precise)
    public List<String> getRolesAsList() {
        return List.of(roles.split(","));  // Separando as roles que são armazenadas como uma String
    }
}
