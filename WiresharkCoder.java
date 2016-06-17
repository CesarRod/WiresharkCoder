/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiresharkcoder;

import java.util.ArrayList;

/**
 *
 * @author CesarOmar
 */
public class WiresharkCoder {

    private String trama;

    public WiresharkCoder(String trama) {
        this.trama = trama;
    }

    //menjsaje tipico de la trama, solo necesitamos los ultimos 2 digitos y convertirlos a 
    //decimal
    //0000   ff ff ff ff ff ff 64 5a 04 47 de a7 08 06 00 01
    //0010   08 00 06 04 00 01 64 5a 04 47 de a7 -> ip que manda el ping c0 a8 00 66
    //0020   00 00 00 00 00 00 -> ip destino c0 a8 00 59 
    /*
    0000   ff ff ff ff ff ff 64 5a 04 47 de a7 08 06 00 01
    0010   08 00 06 04 00 01 64 5a 04 47 de a7 c0 a8 00 66
    0020   00 00 00 00 00 00 c0 a8 00 59
     */
    public String traductor() {
        
        ArrayList<String> hexReceptor = new ArrayList();
        ArrayList<String> hexLanzador = new ArrayList();
        ArrayList<Integer> decReceptor = new ArrayList();
        ArrayList<Integer> decLanzador = new ArrayList();
        StringBuilder aux = new StringBuilder();
        try{
        String[] partes = trama.split("\n");

        for (String a : partes) {
            System.out.println(a + "\n");
        }

        if (partes[1].contains("c0")) {
            aux.append("Ping lanzado de la ip: ");
            String[] trama2 = partes[1].split("c0");

            String[] subTrama = trama2[1].split(" ");
            hexLanzador.add("c0");
            for (String a : subTrama) {
                if(!a.isEmpty())
                hexLanzador.add(a);
            }
            for(int i=0;i<hexLanzador.size();i++){
            decLanzador.add(Integer.parseInt(hexLanzador.get(i), 16));
               aux.append(decLanzador.get(i).intValue()+".");
               
               
            }
           
        }
        if (partes[2].contains("c0")) {
            aux.delete(aux.length()-1, aux.length());
            aux.append(" para esta ip: ");
            String[] trama3 = partes[2].split("c0");
            String[] subTrama = trama3[1].split(" ");
            hexReceptor.add("c0");
            for (String a : subTrama) {
               if(!a.isEmpty())
                hexReceptor.add(a);
            }
            
            for(int i=0;i<hexReceptor.size();i++){
            decReceptor.add(Integer.parseInt(hexReceptor.get(i), 16));
                aux.append(decReceptor.get(i).intValue()+".");
            }
            
        }
        return aux.toString();
        }catch(Exception e){
            System.err.println("String vacia");
        }
        return "Mensaje vacio o incompleto o dañado intente de nuevo";
        //return mensaje;

    }
}
  

  //  public static void main(String[] args) {
    //    WiresharkCoder a = new WiresharkCoder(""
      //          + "0000   ff ff ff ff ff ff 64 5a 04 47 de a7 08 06 00 01\n"
        //        + "0010   08 00 06 04 00 01 64 5a 04 47 de a7 c0 a8 00 66\n"
         //       + "0020   00 00 00 00 00 00 c0 a8 00 59");
        //a.traductor();
   // }
//}
