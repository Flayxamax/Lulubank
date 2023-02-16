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

    public ConfiguracionPaginado() {
        this.numPagina = 0;
        this.elementosPagina = 5;
    }

    public ConfiguracionPaginado(int numPagina, int elementosPagina) {
        this.numPagina = numPagina;
        this.elementosPagina = elementosPagina;
    }

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    public int getElementosPagina() {
        return elementosPagina;
    }

    public void setElementosPagina(int elementosPagina) {
        this.elementosPagina = elementosPagina;
    }

    public int getElementosASaltar() {
        return this.numPagina * this.elementosPagina;
    }

    public void avanzarPagina() {
        this.numPagina++;
    }

    public void retrocederPagina() {
        if (this.numPagina > 0) {
            this.numPagina--;
        }
    }
}
