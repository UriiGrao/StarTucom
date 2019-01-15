package planeta;

public class PlanetasPequeños extends Planeta {
    private String nombre;
    private int nIntegrantes;

    /**
     * @param nombre nombre del planeta pequeño
     */
    public PlanetasPequeños(String nombre) {
        super(nombre);
    }

    /**
     * devuelve el nIntegrantes.
     *
     * @return nIntegrantes
     */
    public int getnIntegrantes() {
        return nIntegrantes;
    }

    /**
     * para añadir nIntegrantes
     *
     * @param nIntegrantes del planeta.
     */
    public void setnIntegrantes(int nIntegrantes) {
        this.nIntegrantes = nIntegrantes;
    }

    /**
     * maximo 30 seres en este planeta.
     *
     * @return false si no caben mas seres.
     */
    public boolean limitePlaneta() {
        if (nIntegrantes >= 30) {
            return false;
        }
        return true;
    }

}
