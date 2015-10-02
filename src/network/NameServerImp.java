package network;

import remote.interfaces.ComModuleInterface;
import remote.interfaces.NameServerInterface;
import remote.objects.ComModuleImp;

import java.rmi.RemoteException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c10mjn on 2015-09-30.
 */
public class NameServerImp implements NameServerInterface {

    private HashMap<String, ComModuleInterface> leaderList;
    private HashMap<String, String> nameList;

    public NameServerImp() {
        this.leaderList = new HashMap<>();
        this.nameList = new HashMap<>();
    }

    @Override
    public ComModuleInterface joinGroup(String groupName, ComModuleInterface leader) throws RemoteException {
        if(!this.leaderList.containsKey(groupName)){
            this.leaderList.put(groupName, leader);
        }
        return this.leaderList.get(groupName);
    }

    @Override
    public String[][] groupList() throws RemoteException {
        String[][] groupList = new String[nameList.size()][2];
        int i = 0;
        for(Map.Entry e : this.nameList.entrySet()){
            groupList[i][0] = (String) e.getKey();
            groupList[i][1] = (String) e.getValue();
        }
        return groupList;
    }

    @Override
    public void assignNewLeader(String groupName, ComModuleInterface leader) throws RemoteException {
        this.leaderList.put(groupName, leader);
    }
}
