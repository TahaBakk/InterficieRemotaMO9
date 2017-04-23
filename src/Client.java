import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by taha on 23/04/2017.
 */
public class Client {

    public static void main(String[] args) {
        ServerInterficie coneccion = null;
        Scanner sc = new Scanner(System.in);
        int operacion = 0;
        double num1 =0;
        double num2 =0;
        //esto es para conectarse al servidor
        try{
            System.out.println("Localitzant registre d'objectes remots ...");
            Registry registry = LocateRegistry.getRegistry("localhost",5555);
            System.out.println("Obtenint l'objecte remot...");

            //dependiendo de la opcion seleccionada en el menu ara una de las siguientes funciones
            System.out.println("-------------------------------------------------------");
            System.out.println("Menu (seleccione una opción)");
            System.out.println("1.Sumar");
            System.out.println("2.Restar");
            System.out.println("3.dividir");
            System.out.println("4.Multiplicar");
            System.out.println("-------------------------------------------------------");

            operacion = sc.nextInt();

            //Pedimos los numeros a calcular
            System.out.println("Dame el primer numero a calcular");
            num1 = sc.nextDouble();
            System.out.println("Dame el segundo numero a calcular");
            num2 = sc.nextDouble();

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
}
