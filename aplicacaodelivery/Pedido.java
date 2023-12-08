/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacaodelivery;

import java.util.ArrayList;

import java.util.List;


class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private double total;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
    
     public Cliente getCliente() {
        return cliente;
    }
}
