package network;

import remote.interfaces.NameServerInterface;
import remote.objects.ComModuleImp;

import java.rmi.RemoteException;

import java.util.HashMap;

/**
 * Created by c10mjn on 2015-09-30.
 */
public class NameServerImp implements NameServerInterface {

    private HashMap<String, ComModuleImp> leaderList;


    public NameServerImp() {
        this.leaderList = new HashMap<>();
    }

    @Override
    public void createGroup(String s, ComModuleImp leader) throws RemoteException {
        if (this.leaderList.containsKey(s)) {
            throw new RemoteException("Group already exists");
        }

        this.leaderList.put(s, leader);



    }

    @Override
    public ComModuleImp joinGroup(String s, ComModuleImp comModuleInterface) throws RemoteException {
        return null;
    }

    @Override
    public String[] groupList() throws RemoteException {
        return new String[0];
    }

    @Override
    public void assignNewLeader(String s, ComModuleImp comModuleImp) throws RemoteException {
        this.leaderList.put(s, comModuleImp);
    }





}
