package especies;

import utilidades.MiExcepcion;

/**
 * Class para crear Andorian
 */
public class Andorian extends Especie {
    boolean aenar;

    public Andorian(String aenar, String nombre, String planeta) throws MiExcepcion {
        super(nombre, planeta);
        this.setAenar(aenar);
    }

    /**
     * Devuelbe si el Andorian es Aenar o NO
     *
     * @return aenar
     */
    public boolean isAenar() {
        return aenar;
    }

    /**
     * funcion para cambiar añadir el aenar o noaenar.
     *
     * @param aenar nombre del tipo si aenar o noaenar
     * @throws MiExcepcion por si el dato es incorrecto.
     */
    public void setAenar(String aenar) throws MiExcepcion {
        if (aenar.equalsIgnoreCase("aenar")) {
            this.aenar = true;
        } else if (aenar.equalsIgnoreCase("noaenar")) {
            this.aenar = false;
        } else {
            throw new MiExcepcion("< ERROR 004: Dato incorrecto >");
        }
    }

    /**
     * @param o especie
     * @return devuelbe 0.
     */
    @Override
    public int compareTo(Especie o) {
        return 0;
    }
}
