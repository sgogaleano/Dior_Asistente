package app.moviles.sgogaleano.dior_asistente.Utilidades;

public class Utilidades {
    /*Se recomienda crear esta clase con las constantes que representan las tablas y los campos
    de la base de datos. */

    //Constantes con los nombres de los campos
    public static final String TABLA_PEDIDOS = "pedidos";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_NIT_CLIENTE ="nitCliente";


    //Constantes de los campos de la tabla
    public static final String TABLA_PRODUCTOS = "productos";
    public static final String CAMPO_ID_PROD = "id_prod";
    public static final String CAMPO_NOMBRE_PROD = "nombre_prod";
    public static final String CAMPO_PRECIO_PROD = "precio_prod";

    //Constantes de los campos de la tabla temporal
    public static final String TABLA_TEMPORAL = "temporal";
    public static final String CAMPO_CANT_PROD = "cant_prod";
    public static final String CAMPO_ID_PEDIDO = "id_pedido";

    //Constante para crear la tabla pedidos:
    public static final String CREAR_TABLA_PEDIDOS = "CREATE TABLE "+TABLA_PEDIDOS+" ("+CAMPO_ID+" INTEGER, "+CAMPO_FECHA+" DATE, "+CAMPO_NIT_CLIENTE+" TEXT)";

    //Constante para crear la tabla productos
    public static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE "+TABLA_PRODUCTOS+" ("+CAMPO_ID_PROD+" TEXT PRIMARY KEY, "+CAMPO_NOMBRE_PROD+" TEXT, "+CAMPO_PRECIO_PROD+" LONG DEFAULT 0)";

    //Constante para crear la tabla temporal de productos pedidos
    public static final String CREAR_TABLA_TEMPORAL = "CREATE TABLE "+TABLA_TEMPORAL+" ("+CAMPO_ID_PEDIDO+" INTEGER PRIMARY KEY, "+CAMPO_FECHA+" TEXT, "+CAMPO_NIT_CLIENTE+" TEXT, "+CAMPO_ID_PROD+" TEXT, "+CAMPO_NOMBRE_PROD+" TEXT, "+CAMPO_PRECIO_PROD+" DOUBLE, "+CAMPO_CANT_PROD+" INTEGER)";

}