/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filesUtils;

import Models.Productos;
import Models.Ticket;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControllers.TicketJpaController;
import views.VentanaCarrito;

/**
 *
 * @author migue
 */
public class GenerarTicket {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("repaso_DAWFoodFinal_jar_1.0-SNAPSHOTPU");
    private static final TicketJpaController tijc = new TicketJpaController(emf);

    public static void ticketString() {

        Map<Productos, Integer> carritoFinal = VentanaCarrito.getCarritoMap();

        //saco el ultimo ticket para saber la fecha y la hora
        List<Ticket> listaTicket = tijc.findTicketEntities();
        Ticket t1 = listaTicket.get(listaTicket.size() - 1);
        Date fecha = t1.getFechaOperacion();
        String hora = t1.getHoraOperacion();

        String ticket = String.format("""
                        --------------------------------------------------------
                                            TICKET DE COMPRA
                        --------------------------------------------------------
                        Id ticket: %d      Fecha: %s       Hora: %s
                        --------------------------------------------------------
                        ID   NOMBRE                   PRECIO    IVA     CANTIDAD \n
                                      """, t1.getIdTicket(), new SimpleDateFormat("yyyy-MM-dd").format(fecha), hora);

        try {
            for (Map.Entry<Productos, Integer> entry : carritoFinal.entrySet()) {
                Productos key = entry.getKey();
                Integer value = entry.getValue();

                ticket += key.toString() + "   " + value + "\n";
            }
        } catch (Exception e) {
        }

        String ticketFin = String.format("""
                        --------------------------------------------------------
                                                        Importe Total: %s
                        --------------------------------------------------------
                                      """, VentanaCarrito.getPrecioFinal());

        ticket += ticketFin;

        GrabarTicket.grabarTicket(ticket, t1);
    }

    public static String ajustarDescripcionString(String descripcion) {

        char[] desc = new char[24];
        char[] descInicial = descripcion.toCharArray();
        String descFinal = "";

        for (int i = 0; i < desc.length; i++) {

            if (i < descInicial.length) {
                desc[i] = descInicial[i];
                descFinal += desc[i];
            } else {
                desc[i] = ' ';
                descFinal += desc[i];
            }
        }
        return descFinal;
    }
}
