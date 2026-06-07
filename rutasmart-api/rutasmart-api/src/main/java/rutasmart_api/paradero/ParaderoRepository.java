package rutasmart_api.paradero;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParaderoRepository extends JpaRepository<Paradero, Long> {

    List<Paradero> findByIdRutaOrderByOrdenRuta(Long idRuta);

}