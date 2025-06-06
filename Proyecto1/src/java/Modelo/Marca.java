/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dkred
 */
public class Marca {
    private int id_marca;
    private String nombre_marca;
    private String img_marca;

    // Constructor vacío
    public Marca() {
    }

    // Constructor con parámetros
    public Marca(int id_marca, String nombre_marca, String img_marca) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
        this.img_marca = img_marca;
    }

    // Getters y Setters
    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getImg_marca() {
        return img_marca;
    }

    public void setImg_marca(String img_marca) {
        this.img_marca = img_marca;
    }
}