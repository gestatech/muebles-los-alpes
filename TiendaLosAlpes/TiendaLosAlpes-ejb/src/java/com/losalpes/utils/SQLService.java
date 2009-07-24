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
    /**
     * String static para las cadenas SQL a construir.
     */
    public static String findCliente = "SELECT o FROM Cliente AS o WHERE";
    public static String findMueble = "SELECT o FROM Mueble AS o WHERE";
    public static String findUsuario = "SELECT o FROM Usuario AS o WHERE";
    public static String findVentas = "SELECT o FROM Venta AS o WHERE";
    /**
     * Método para calular y contruir una sentencia SQL.
     * @param consulta Palabra clave para conocer cual String static seleccionar.
     * @param valores List con el campo y el valor a consutlar.
     * @return String sentencia SQL construida.
     */
    public static String buscarObjetos(String consulta, List<String> valores){
        String sql = "";
        // Verifica que tipo de SQL usar según lo que venga en consulta.
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
        // Bucle que permite anidar estamentos SQL según los parametros que llegan en el arreglo.
        while(it.hasNext()){
            String parametro = (String)it.next();
            // Tokenizer para parsear el arreglo.
            StringTokenizer token = new StringTokenizer(parametro, "|");
            String columna = token.nextToken();
            String valor = token.nextToken();
            // Verifica si solo hay un parametro y contruye la sentencia.
            if(contador==0)
                sql+=" o."+columna+" LIKE '"+valor+"'";
            // Si ha mas de un parametro concatena con AND para la sentecia SQL>
            else
                sql+=" AND o."+columna+" LIKE '"+valor+"'";
            contador++;
        }
        return sql;
    }
}