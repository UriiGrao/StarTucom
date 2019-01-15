package especies;

import utilidades.*;

/**
 * Class para crear un Human
 */
public class Human extends Especie {
    private int edad;

    public Human(int edad, String nombre, String planeta) throws MiExcepcion {
        super(nombre, planeta);
        this.setEdad(edad);
    }

    /**
     * Get de Edad del Human
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * para añadir edad al humano
     *
     * @param edad la edad del ser
     * @throws MiExcepcion por is el dato incorrecto
     */
    public void setEdad(int edad) throws MiExcepcion {
        if (edad < 0 || edad > 130) {
            throw new MiExcepcion("< ERROR 004: Dato incorrecto >");
        } else {
            this.edad = edad;
        }
    }

    @Override
    public int compareTo(Especie o) {
        return 0;
    }
}
