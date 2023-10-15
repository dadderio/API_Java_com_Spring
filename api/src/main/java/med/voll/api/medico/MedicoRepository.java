package med.voll.api.medico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//coloca o generics entre <>, neste caso passa dois tipos de objetos, tipo da entidade paciente é Medico e tipo do atributo da entidade da chave primária Long
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //o código abaixo foi feito para listar apenas os ativos:
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
