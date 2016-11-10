/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class Servidor {

    private int porta;
    private List<SrvCliente> clientes;
    private boolean estado;
    private ServerSocket socketServidor;
    private ServidorRun runThread;

    // contrutor
    public Servidor(int porta) throws IOException {
        this.porta = porta;
        socketServidor = new ServerSocket(this.porta);
        this.clientes = new ArrayList();
        this.estado = false;
        
        
        /////////////////////////////////////////////////
        /*
        A testar obter o ip                        
         */
        InetAddress inet = socketServidor.getInetAddress();
        System.out.println("ip " + inet.getHostAddress());
        
        ////////////////////////////////////////////////

    }

    // funcao liga
    public void liga() {
        estado = true;
        // criar a runThread para accept
        runThread = new ServidorRun(socketServidor, this);
        new Thread(runThread).start();
    }

    // funcao desliga
    public void desliga() {
        estado = false;
        // avisar a runThread que vai ser desligado
        runThread.stop();
        for (SrvCliente cliente : this.clientes) {
            cliente.stop();
        }
    }

    // metodos Sets e Gets
    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public List<SrvCliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<SrvCliente> clientes) {
        this.clientes = clientes;
    }

    public void adicionaCliente(SrvCliente ps) {
        this.clientes.add(ps);
    }

    public void removeCliente(SrvCliente ps) {
        this.clientes.remove(ps);
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ServerSocket getServidor() {
        return socketServidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.socketServidor = servidor;
    }

    // metodo para distribuir a mensagem
    public void distribuiMensagem(String msg) {
        System.out.println("teste");
        // envia msg para todo mundo
        for (SrvCliente cliente : this.clientes) {
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            ps.println(msg);
            System.out.println("Distribui mensagem " + msg);
        }
    }
}

//    public void distribuiMensagem(String msg) {
//        System.out.println("metodo distribui mensagem");
//        // envia msg para todo mundo
//        for (SrvCliente cliente : this.clientes) {
//            try (PrintStream ps = new PrintStream (cliente.getSocket().getOutputStream())) {
//                ps.println(msg);
//                System.out.println("Distribui mensagem "+ msg);
//            } catch (IOException ex) {
//                System.out.println("Distribui mensagem "+ ex.getMessage());
//            }
//        }
//    }
//    // metodo para distribuir a mensagem
//    public void distribuiMensagem(String msg) {
//        System.out.println("metodo distribui mensagem");
//        // envia msg para todo mundo
//        for (SrvCliente cliente : this.clientes) {
//            try (PrintStream ps = new PrintStream (cliente.getSocket().getOutputStream())) {
//                ps.println(msg);
//                System.out.println("Distribui mensagem "+ msg);
//            } catch (IOException ex) {
//                System.out.println("Distribui mensagem "+ ex.getMessage());
//            }
//        }
//    }

