import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by taha on 23/04/2017.
 */
public class Servidor {

    public static void main(String[] args) {
        System.out.println("Creant registre d'objectes remots");
        Registry reg = null;

        try{
            reg = LocateRegistry.createRegistry(5555);
            System.out.println(reg);
        }catch (Exception e){
            System.out.println("Error: No s'ha pogut crear el registre");
            e.printStackTrace();
        }



        System.out.println("Creant l'objecte servidor e inscribint-lo en el registre ...");
        ServerInterficie serverObject = new ServerInterficie() {

            @Override
            public double suma(double a, double b) throws RemoteException {
                return a + b;
            }

            @Override
            public double resta(double a, double b) throws RemoteException {
                return a - b;
            }

            @Override
            public double division(double a, double b) throws RemoteException {
                return a / b;
            }

            @Override
            public double multiplicar(double a, double b) throws RemoteException {
                return a * b;
            }
        };

        try{
                reg.rebind("conexion", UnicastRemoteObject.exportObject(serverObject,0));

        }catch (Exception e){
            System.out.println("No s'ha pogut inscriure l'objecte servidor");
            e.printStackTrace();
        }

    }

}
