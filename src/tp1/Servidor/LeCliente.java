/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Servidor;

import java.util.Scanner;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class LeCliente implements Runnable {

    SrvCliente sc;
    Scanner scanner;

    LeCliente(SrvCliente sc) {
        this.sc = sc;
        scanner = new Scanner(sc.getInputStream());
    }
    // readline

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sc.getServidor().distribuiMensagem(line);
        }
    }

}
