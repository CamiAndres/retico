/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.Reports;

import co.usa.ciclo3.ciclo3.model.Client;

/**
 *
 * @author lenovo
 */
public class CountClient {
      private Long total;
    private Client client;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CountClient(Long total, Client client) {
        this.total = total;
        this.client = client;
    }
}
