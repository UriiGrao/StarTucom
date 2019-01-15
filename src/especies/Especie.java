package especies;

import planeta.Planeta;

/**
 * Clase especie donde tenemos las especies de los seres.
 */
public abstract class Especie implements Comparable<Especie> {
    private String nombre;
    private String planeta;

    public Especie(String nombre, String planeta) {
        this.nombre = nombre;
        this.planeta = planeta;
    }

    /**
     * Get the vaule of Nombre
     *
     * @return nombre de la Especie
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Get the vaule of Nombre
     *
     * @return nombre del planeta donde vive este ser.
     */
    public String getPlaneta() {
        return planeta;
    }

}
