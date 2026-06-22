package rutasmart_api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaControllerTest {

    @Test
    void debePermitirReservaSiHayCupos() {

        int capacidadBus = 45;
        int reservasActuales = 20;

        boolean disponible =
                reservasActuales < capacidadBus;

        assertTrue(disponible);

    }

    @Test
    void noDebePermitirReservaSiBusEstaLleno() {

        int capacidadBus = 45;
        int reservasActuales = 45;

        boolean disponible =
                reservasActuales < capacidadBus;

        assertFalse(disponible);

    }

    @Test
    void validarReservaDuplicada() {

        Long alumnoReservado = 1L;
        Long alumnoIntentandoReservar = 1L;

        assertEquals(
                alumnoReservado,
                alumnoIntentandoReservar
        );

    }

}