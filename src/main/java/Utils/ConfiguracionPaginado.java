/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author ildex
 */
public class ConfiguracionPaginado {

    private int numPagina;
    private int elementosPagina;

    /**
     *
     * Configuración de paginado por defecto que muestra la primera página con 5
     * elementos por página.
     */
    public ConfiguracionPaginado() {
        this.numPagina = 0;
        this.elementosPagina = 5;
    }

    /**
     *
     * Constructor que permite configurar la página y el número de elementos a
     * mostrar por página.
     *
     * @param numPagina El número de página que se desea mostrar.
     * @param elementosPagina El número de elementos a mostrar por página.
     */
    public ConfiguracionPaginado(int numPagina, int elementosPagina) {
        this.numPagina = numPagina;
        this.elementosPagina = elementosPagina;
    }

    /**
     *
     * Obtiene el número de página actual.
     *
     * @return El número de página actual.
     */
    public int getNumPagina() {
        return numPagina;
    }

    /**
     *
     * Configura el número de página actual.
     *
     * @param numPagina El número de página que se desea configurar.
     */
    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    /**
     *
     * Obtiene el número de elementos a mostrar por página.
     *
     * @return El número de elementos a mostrar por página.
     */
    public int getElementosPagina() {
        return elementosPagina;
    }

    /**
     *
     * Configura el número de elementos a mostrar por página.
     *
     * @param elementosPagina El número de elementos que se desea mostrar por
     * página.
     */
    public void setElementosPagina(int elementosPagina) {
        this.elementosPagina = elementosPagina;
    }

    /**
     *
     * Obtiene el número de elementos que se deben saltar para mostrar la página
     * actual.
     *
     * @return El número de elementos que se deben saltar.
     */
    public int getElementosASaltar() {
        return this.numPagina * this.elementosPagina;
    }

    /**
     *
     * Avanza a la siguiente página.
     */
    public void avanzarPagina() {
        this.numPagina++;
    }

    /**
     *
     * Retrocede a la página anterior si no se encuentra en la primera página.
     */
    public void retrocederPagina() {
        if (this.numPagina > 0) {
            this.numPagina--;
        }
    }
}
