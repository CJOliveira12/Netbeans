/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Cliente;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class ThCliente implements Runnable {

    private InputStream servidor;
    

    public ThCliente(InputStream servidor) {
        this.servidor = servidor;

    }

    @Override
    public void run() {
        // recebe msgs do servidor e imprime na tela
        Scanner s = new Scanner(this.servidor);
        System.out.println("entrou no thread");
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
    }
}