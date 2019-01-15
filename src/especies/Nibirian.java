package especies;

import utilidades.MiExcepcion;

public class Nibirian extends Especie {

    private String alimentacion;

    public Nibirian(String alimentacio, String nombre, String planeta) throws MiExcepcion {
        super(nombre, planeta);
        puedeVivir(planeta, alimentacio);
        this.setAlimentacion(alimentacio);
    }

    /**
     * para devolver la alimentacion del ser
     *
     * @return la alimentacion
     */
    public String getAlimentacion() {
        return alimentacion;
    }

    /**
     * para añadir alimentacion al ser.
     *
     * @param alimentacion la comida del ser.
     * @throws MiExcepcion por si el dato es incorrecto.
     */
    public void setAlimentacion(String alimentacion) throws MiExcepcion {
        if (alimentacion.equalsIgnoreCase("vegetarian")
                || alimentacion.equalsIgnoreCase("novegetarian")) {
            this.alimentacion = alimentacion;
        } else {
            throw new MiExcepcion("< ERROR 004: Dato incorrecto >");
        }
    }

    /**
     * para mirar si el ser con esa alimentacion puede vivir en ese planeta.
     *
     * @param pla el nombre del planeta
     * @param ali el nombre de la alimentacino
     * @return devuelve true si todo se cumple.
     * @throws MiExcepcion por si el dato es incorrecto.
     */
    public boolean puedeVivir(String pla, String ali) throws MiExcepcion {
        if (ali.equalsIgnoreCase("vegetarian") && pla.equalsIgnoreCase("Nibiru")
                || ali.equalsIgnoreCase("novegetarian")) {
            return true;
        } else {
            throw new MiExcepcion("< ERROR 004: Dato incorrecto >");
        }
    }

    @Override
    public int compareTo(Especie o) {
        return 0;
    }
}
