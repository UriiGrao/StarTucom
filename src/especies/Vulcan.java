package especies;


import utilidades.MiExcepcion;

public class Vulcan extends Especie {
    int meditacion;

    public Vulcan(int meditacion, String nombre, String planeta) throws MiExcepcion {
        super(nombre, planeta);
        this.setMeditacion(meditacion);
    }

    /**
     * Devuelbe la meditacion del Vulcan
     *
     * @return meditacion
     */
    public int getMeditacion() {
        return meditacion;
    }

    /**
     * añadir meditacion al ser.
     *
     * @param meditacion el numero de meditacion del ser.
     * @throws MiExcepcion por si el nivel de meditacion es erroneo.
     */
    public void setMeditacion(int meditacion) throws MiExcepcion {
        if (meditacion < 0 || meditacion > 10) {
            throw new MiExcepcion("< ERROR 011: Nivel de meditación incorrecto >");
        } else {
            this.meditacion = meditacion;
        }
    }

    @Override
    public int compareTo(Especie o) {
        return 0;
    }
}
