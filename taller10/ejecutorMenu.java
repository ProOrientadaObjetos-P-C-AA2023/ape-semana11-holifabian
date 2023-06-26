/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller10;

/**
 *
 * @author USUARIOPC
 */
abstract class Menu {
    protected String nombrePlato;
    protected double valorMenu;
    protected double valorInicialMenu;

    public Menu(String nombrePlato, double valorMenu, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorMenu = valorMenu;
        this.valorInicialMenu = valorInicialMenu;
    }

    public abstract double calcularSubtotal();

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public double getValorMenu() {
        return valorMenu;
    }

    public void setValorMenu(double valorMenu) {
        this.valorMenu = valorMenu;
    }

    public double getValorInicialMenu() {
        return valorInicialMenu;
    }

    public void setValorInicialMenu(double valorInicialMenu) {
        this.valorInicialMenu = valorInicialMenu;
    }

    @Override
    public String toString() {
        return "Nombre del plato: " + nombrePlato + "\nValor del menú: $" + valorMenu +
                "\nValor inicial del menú: $" + valorInicialMenu;
    }
}
 class MenuCarta extends Menu {
    private double valorPorcionGuarnicion;
    private double valorBebida;
    private double porcentajeAdicional;

    public MenuCarta(String nombrePlato, double valorMenu, double valorInicialMenu,
                     double valorPorcionGuarnicion, double valorBebida, double porcentajeAdicional) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeAdicional = porcentajeAdicional;
    }

    @Override
    public double calcularSubtotal() {
        return valorMenu + valorPorcionGuarnicion + valorBebida +
                (porcentajeAdicional * valorInicialMenu / 100);
    }

    @Override
    public String toString() {
        return super.toString() + "\nValor de porción de guarnición: $" + valorPorcionGuarnicion +
                "\nValor de bebida: $" + valorBebida + "\nPorcentaje adicional por servicio: " + porcentajeAdicional + "%";
    }
}
 class MenuDia extends Menu {
    private double valorPostre;
    private double valorBebida;

    public MenuDia(String nombrePlato, double valorMenu, double valorInicialMenu,
                   double valorPostre, double valorBebida) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public double calcularSubtotal() {
        return valorMenu + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() + "\nValor de postre: $" + valorPostre +
                "\nValor de bebida: $" + valorBebida;
    }
}
 class MenuNinos extends Menu {
    private double valorPorcionHelado;
    private double valorPorcionPastel;

    public MenuNinos(String nombrePlato, double valorMenu, double valorInicialMenu,
                     double valorPorcionHelado, double valorPorcionPastel) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }

    @Override
    public double calcularSubtotal() {
        return valorMenu + valorPorcionHelado + valorPorcionPastel;
    }

    @Override
    public String toString() {
        return super.toString() + "\nValor de porción de helado: $" + valorPorcionHelado +
                "\nValor de porción de pastel: $" + valorPorcionPastel;
    }
}
 class MenuEconomico extends Menu {
    private double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorMenu, double valorInicialMenu, double porcentajeDescuento) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularSubtotal() {
        return valorMenu - (porcentajeDescuento * valorInicialMenu / 100);
    }

    @Override
    public String toString() {
        return super.toString() + "\nPorcentaje de descuento: " + porcentajeDescuento + "%";
    }
}

 class Cuenta {
    private String nombreCliente;
    private double iva;
    private List<Menu> listaMenus;
    private double valorTotal;

    public Cuenta(String nombreCliente, double iva) {
        this.nombreCliente = nombreCliente;
        this.iva = iva;
        this.listaMenus = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void agregarMenu(Menu menu) {
        listaMenus.add(menu);
        valorTotal += menu.calcularSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre del cliente: ").append(nombreCliente).append("\n");
        sb.append("Subtotal: $").append(valorTotal).append("\n");
        sb.append("IVA: ").append(iva).append("%\n");
        sb.append("Listado de menús:\n");
        for (Menu menu : listaMenus) {
            sb.append(menu).append("\n");
        }
        double valorTotalConIva = valorTotal + (valorTotal * iva / 100);
        sb.append("Valor a cancelar total (con IVA): $").append(valorTotalConIva);
        return sb.toString();
    }
}
public class ejecutorMenu {
    public static void main(String[] args) {
        MenuNinos menuNinos1 = new MenuNinos("Guata", 8.5, 10.0, 2.0, 3.0);
        MenuNinos menuNinos2 = new MenuNinos("Chaulafan", 9.0, 12.0, 1.5, 2.5);
        MenuEconomico menuEconomico = new MenuEconomico("Encebollado", 7.0, 9.0, 20.0);
        MenuDia menuDia = new MenuDia("Repe", 5.0, 7.0, 1.5, 1.0);
        MenuCarta menuCarta = new MenuCarta("Hornado", 15.0, 18.0, 3.0, 2.0, 10.0);

        Cuenta cuenta = new Cuenta("Fabian Galarza", 12.0);
        cuenta.agregarMenu(menuNinos1);
        cuenta.agregarMenu(menuNinos2);
        cuenta.agregarMenu(menuEconomico);
        cuenta.agregarMenu(menuDia);
        cuenta.agregarMenu(menuCarta);

        System.out.println(cuenta);
    }
}