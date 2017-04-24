import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by taha on 23/04/2017.
 */
public class Client {

    private static int operacionTipo = 0;
    private static double num1 = 0;
    private static double num2 = 0;

    public static void main(String[] args) {
        ServerInterficie coneccion = null;
        Scanner sc = new Scanner(System.in);
        int operacion = 0;

        //esto es para conectarse al servidor
        try{
            System.out.println("Localitzant registre d'objectes remots ...");
            Registry registry = LocateRegistry.getRegistry("localhost",5555);
            System.out.println("Obtenint l'objecte remot...");

            System.out.println("-------------------------------------------------------");
            System.out.println("Introduce la operación que desea realizar: (ej:5 + 5)");
            System.out.println("-------------------------------------------------------");
            String operacionRealizar = sc.nextLine();

            operacionClient(operacionRealizar);
            operacion = operacionTipo;

            //establecemos la connexión
                coneccion = (ServerInterficie) registry.lookup("conexion");


        }catch (Exception e){
            e.printStackTrace();
        }
        //dependiendo de la opcion seleccionada en el menu ara una de las siguientes funciones
        if(coneccion!= null){
            System.out.println("Realitzant operacions");

            try{
                System.out.println("El resultat és:");
                if (operacion == 1){
                    System.out.println(coneccion.suma(num1, num2));
                }else if (operacion == 2){
                    System.out.println(coneccion.resta(num1, num2));
                }else if (operacion == 3){
                    System.out.println(coneccion.division(num1, num2));
                }else if (operacion == 4){
                    System.out.println(coneccion.multiplicar(num1, num2));
                }else{
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FI");

    }

    private static void operacionClient(String operacion) {

        //usamos el split para que nos separe los valores que estan antes de la operacion y el de despues
        // y para saber que tipo de operacion es
        if (operacion.contains("+")){
            operacionTipo = 1;
            String [] datos = operacion.split("\\+");
            num1 = Double.parseDouble(datos[0]);
            num2 = Double.parseDouble(datos[1]);
        }

        if (operacion.contains("-")){
            operacionTipo = 2;
            String [] datos = operacion.split("-");
            num1 = Double.parseDouble(datos[0]);
            num2 = Double.parseDouble(datos[1]);
        }

        if (operacion.contains("/")){
            operacionTipo = 3;
            String [] datos = operacion.split("/");
            num1 = Double.parseDouble(datos[0]);
            num2 = Double.parseDouble(datos[1]);
        }

        if (operacion.contains("*")){
            operacionTipo = 4;
            String [] datos = operacion.split("\\*");
            num1 = Double.parseDouble(datos[0]);
            num2 = Double.parseDouble(datos[1]);
        }

        if (!operacion.contains("*") && !operacion.contains("/") && !operacion.contains("-") && !operacion.contains("+")){
            throw new RuntimeException("Se ha producido, signo no localizado ");
        }

    }



}
