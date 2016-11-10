/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Carlos Jorge Oliveira_EI_3778
 */
public class SrvCliente {
    private Socket s;
    private InputStream input;
    private OutputStream output;
    private Servidor servidor;
    
    SrvCliente(Socket s, Servidor servidor) {
        try {
            this.s = s;
            this.input = s.getInputStream();
            this.output = s.getOutputStream();
            this.servidor = servidor;
        } catch (IOException ex) {
            System.err.println("SrvCliente: "+ ex.getMessage());
        }
    }

    public InputStream getInputStream() {
        return input;
    }
    public OutputStream getOutputStream() {
        return output;
    }

    public Servidor getServidor() {
        return servidor;
    }
    
    public void stop(){
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println("SrvCliente stop: " + ex.getMessage());
        }
    }
}
