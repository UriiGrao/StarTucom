package planeta;

import especies.Especie;

import java.util.*;

/**
 * La clase planeta donde guardamos la informacion de los planetas.
 */
public class Planeta {
    private String nombre;

    /**
     * Nuestro constructor para crear planetas.
     *
     * @param nombre Nombre del planeta creado
     */
    public Planeta(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the vaule of Nombre
     *
     * @return nombre Planeta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre del Planeta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * para la rivalidad de los seres y que no pueda vivir en el planeta
     * @param ser el tipo del ser a crear.
     * @return true si pueden vivir false si no pueden vivir.
     */
    public boolean rivalidad(String ser) {
        if (ser.equalsIgnoreCase("andorian") && this.nombre.equalsIgnoreCase("vulcano")) {
            return false;
        } else if (ser.equalsIgnoreCase("vulcan") && this.nombre.equalsIgnoreCase("andoria")) {
            return false;
        } else {
            return true;
        }
    }



}



