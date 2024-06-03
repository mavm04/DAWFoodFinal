/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filesUtils;

import Models.Ticket;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;

/**
 *
 * @author migue
 */
public class GrabarTicket {

    public static void grabarTicket(String ticket, Ticket t1) {

        String rutaArchivo = "./tickets/ticket_" + t1.getIdTicket() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(t1.getFechaOperacion()) + "_" + t1.getHoraOperacion() + ".txt";

        try {
            Files.write(Paths.get(rutaArchivo), ticket.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
    }
}
