/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Objects;

/**
 *
 * @author ildex
 */
public class Cliente {

    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private int edad;
    private String correo;
    private String password;
    private int idDireccion;
    
    /**
     * Contructor por omisión
     * 
     */
    public Cliente() {
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase Cliente
     * 
     * @param idCliente el identificador del cliente
     * @param nombre el nombre del cliente
     * @param apellidoPaterno el apellido paterno del cliente
     * @param apellidoMaterno el apellido materno del cliente
     * @param fechaNacimiento la fecha de nacimiento del cliente
     * @param edad la edad del cliente
     * @param correo el correo electrónico del cliente
     * @param password la contraseña del cliente
     * @param idDireccion el identificador de la dirección del cliente
     */
    
    public Cliente(Integer idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, int edad, String correo, String password, int idDireccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.password = password;
        this.idDireccion = idDireccion;
    }
    
    /**
     * Constructor que inicializa todos los atributos de la clase Cliente excepto idCliente
     * 
     * @param nombre el nombre del cliente
     * @param apellidoPaterno el apellido paterno del cliente
     * @param apellidoMaterno el apellido materno del cliente
     * @param fechaNacimiento la fecha de nacimiento del cliente
     * @param edad la edad del cliente
     * @param correo el correo electrónico del cliente
     * @param password la contraseña del cliente
     * @param idDireccion el identificador de la dirección del cliente
     */
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, int edad, String correo, String password, int idDireccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.password = password;
        this.idDireccion = idDireccion;
    }
    
    /**
     * Constructor que inicializa el correo electrónico y la contraseña del cliente
     * 
     * @param correo el correo electrónico del cliente
     * @param password la contraseña del cliente
     */
    public Cliente(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    
    /**
     * Método getter para obtener el identificador del cliente
     * 
     * @return el identificador del cliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }
    
    /**
     * Método setter para establecer el identificador del cliente
     * 
     * @param id el identificador del cliente
     */
    public void setId(Integer id) {
        this.idCliente = id;
    }
    
    /**
     * Método getter para obtener el nombre del cliente
     * 
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo que ingresa nombre
     * 
     * @param nombre nombre cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo regresar apellidoPaterno
     * 
     * @return apellido paterno apellidoP
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    /**
     * Metodo ingresa apellido paterno
     * 
     * @param apellidoPaterno aepellidoP
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    /**
     * Metodo get apellidoM
     * 
     * @return apellido materno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
    /**
     * Set apellido materno
     * 
     * @param apellidoMaterno apellidoM
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    /**
     * Metodo que regreas la fecha de nacimiento
     * 
     * @return fecha de nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Set fecha de nacimiento
     * 
     * @param fechaNacimiento fecha de nacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Regresa¿
     * 
     * @return edad
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Método que recibe un entero "edad" y lo asigna a la variable "edad" de la clase actual
     * 
     * @param edad Edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Regresa la contraseña
     * 
     * @return contraseña
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Inserta la contraseña
     * 
     * @param password contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Regresa el id dirección
     * 
     * @return id dirección
     */
    public int getIdDireccion() {
        return idDireccion;
    }
    
    /**
     * Ingresa el id dirección
     * 
     * @param idDireccion id dirección
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    /**
     * Regresa el correo
     * 
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Inserta el correo
     * 
     * @param correo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * HashCode
     * 
     * @return Hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idCliente);
        return hash;
    }
    /**
     * eqquals
     * 
     * @param obj Objeto
     * @return a
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return true;
    }
    
    /**
     * Cadena toString
     * 
     * @return cadena de texto
     */
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", correo=" + correo + ", password=" + password + ", idDireccion=" + idDireccion + '}';
    }

}
