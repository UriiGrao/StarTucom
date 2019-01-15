package especies;

import utilidades.MiExcepcion;

/**
 * Clase Klingon para crear seres klingon
 */
public class Klingon extends Especie {
    private int fuerza;

    public Klingon(int fuerza, String nombre, String planeta) throws MiExcepcion {
        super(nombre, planeta);
        this.setFuerza(fuerza);
    }

    /**
     * Get the vaule of fuerza
     *
     * @return Fuerza de la class Klingon
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * para añadir la fuerza
     *
     * @param fuerza numero de fuerza del ser
     * @throws MiExcepcion por si dato incorrecto.
     */
    public void setFuerza(int fuerza) throws MiExcepcion {
        if (fuerza < 50 || fuerza > 350) {
            throw new MiExcepcion("<ERROR 012: Valor de fuerza incorrecto >");
        } else {
            this.fuerza = fuerza;
        }
    }

    @Override
    public int compareTo(Especie o) {
        return 0;
    }
}
