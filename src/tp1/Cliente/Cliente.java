/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        // dispara cliente
        new Cliente("127.0.0.1", 9999).executa();
    }

    private int porta;
    private String host;
    private ThCliente runThread;
    private String nick;
    private String texto;
    private Socket cliente;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Cliente(String host, int porta) throws IOException {
        this.host = host;
        this.porta = porta;

    }

    public void executa() throws IOException {
       
        Socket cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente ligou-se ao servidor!");
        
        // thread para receber mensagens do servidor
        runThread = new ThCliente(cliente.getInputStream()); //inicia a thread 
        new Thread(runThread).start();
        
        // lê msgs do teclado e manda pro servidor
        Scanner teclado = new Scanner(cliente.getInputStream()); // inicia a classe para a leitura do texto do teclado.
        PrintStream saida = new PrintStream(cliente.getOutputStream()); // cria um objeto PrintStream que vai receber o socket cliente.
        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();
        cliente.close();
    }
}