package network;

import remote.interfaces.ComModuleInterface;
import remote.interfaces.NameServerInterface;
import remote.objects.ComModuleImp;

import java.rmi.RemoteException;

import java.util.HashMap;

/**
 * Created by c10mjn on 2015-09-30.
 */
public class NameServerImp implements NameServerInterface {

    private HashMap<String, ComModuleInterface> leaderList;

    public NameServerImp() {
        this.leaderList = new HashMap<>();
    }

    @Override
    public void createGroup(String groupName, ComModuleInterface leader) throws RemoteException {

        if (this.leaderList.containsKey(groupName)) {
            throw new RemoteException("Group already exists");
        }
        this.leaderList.put(groupName, leader);
    }

    @Override
    public ComModuleInterface joinGroup(String groupName) throws RemoteException {
        return this.leaderList.get(groupName);
    }

    @Override
    public String[] groupList() throws RemoteException {
        String[] a = new String[1];
        return this.leaderList.keySet().toArray(a);
    }

    @Override
    public void assignNewLeader(String groupName, ComModuleInterface leader) throws RemoteException {
        this.leaderList.put(groupName, leader);
    }
}
