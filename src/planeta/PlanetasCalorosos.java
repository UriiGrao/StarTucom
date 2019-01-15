package planeta;

import especies.Especie;

public class PlanetasCalorosos extends Planeta {
    /**
     * @param nombre nombre del planeta caloroso
     */
    public PlanetasCalorosos(String nombre) {
        super(nombre);
    }

    /**
     * Funcion para ver si el ser puede vivir o no en este planeta
     *
     * @param ser que vamos a mirar.
     * @return true si el ser puede vivir en el planeta
     */
    public boolean puedenVivir(Especie ser) {
        if (ser.getClass().getSimpleName().equals("klingon")) {
            return false;
        } else {
            return true;
        }
    }
}