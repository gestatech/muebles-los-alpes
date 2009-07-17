package com.losalpes.utils;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
/**
 * Clase de utilidades para construir las sentecias SQL que se ejecutaran para las consultas.
 * @author Memo Toro
 * @author Hans Escallon
 */
public class SQLService {

    public static String findCliente = "SELECT o FROM Cliente AS o WHERE";
    public static String findMueble = "SELECT o FROM Mueble AS o WHERE";
    public static String findUsuario = "SELECT o FROM Usuario AS o WHERE";
    public static String findVentas = "SELECT o FROM Venta AS o WHERE";

    public static String buscarObjetos(String consulta, List<String> valores){
        String sql = "";
        if(consulta.equalsIgnoreCase("findCliente")){
            sql = findCliente;
        }
        else if(consulta.equalsIgnoreCase("findMueble")){
            sql = findMueble;
        }
        else if(consulta.equalsIgnoreCase("findUsuario")){
            sql = findUsuario;
        }
        else if(consulta.equalsIgnoreCase("findVentas")){
            sql = findVentas;
        }
        Iterator it;
        it = valores.iterator();
        int contador=0;
        while(it.hasNext()){
            String parametro = (String)it.next();
            StringTokenizer token = new StringTokenizer(parametro, "|");
            String columna = token.nextToken();
            String valor = token.nextToken();
            if(contador==0)
                sql+=" o."+columna+" LIKE '"+valor+"'";
            else
                sql+=" AND o."+columna+" LIKE '"+valor+"'";
            contador++;
        }
        return sql;
    }
}