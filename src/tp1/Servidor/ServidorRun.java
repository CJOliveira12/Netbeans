/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class ServidorRun implements Runnable {

    private Servidor servidor;
    private ServerSocket socket;
    private boolean running;

    public ServidorRun(ServerSocket socket, Servidor servidor) {
        this.servidor = servidor;
        this.socket = socket;
        this.running = false;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Socket s = socket.accept(); // fica a aguardar a ligação, qd se liga aloca um socket
                System.out.println("cliente ligado");
                SrvCliente sc = new SrvCliente(s, servidor); // passa o socket para a Classe SrvCliente
                servidor.adicionaCliente(sc);
                System.out.println("cliente passado");
                LeCliente lc = new LeCliente(sc); // 
                new Thread(lc).start();
            } catch (IOException ex) {
                System.out.println("run::" +ex.getMessage());
            }
        }
        System.out.println("bye bye..");
    }

    public void stop() {
        try {
            running = false;
            socket.close();
        } catch (IOException ex) {
            System.out.println("stop::" +ex.getMessage());
        }

    }
}
