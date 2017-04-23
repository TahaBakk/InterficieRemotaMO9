import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by taha on 23/04/2017.
 */
public interface ServerInterficie extends Remote {

    public double suma(double a, double b) throws RemoteException;
    public double resta(double a, double b) throws RemoteException;
    public double division(double a, double b) throws RemoteException;
    public double multiplicar(double a, double b) throws RemoteException;

}
