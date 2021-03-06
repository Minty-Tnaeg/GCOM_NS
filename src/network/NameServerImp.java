package network;

import remote.interfaces.ComModuleInterface;
import remote.interfaces.NameServerInterface;

import java.rmi.RemoteException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c10mjn on 2015-09-30.
 */
public class NameServerImp<T extends ComModuleInterface> implements NameServerInterface<T> {

    private HashMap<String,T> leaderList;
    private HashMap<String, String> nameList;

    public NameServerImp() {
        this.leaderList = new HashMap<>();
        this.nameList = new HashMap<>();
    }

    @Override
    public T joinGroup(String groupName, T leader) throws RemoteException {
        if(!this.leaderList.containsKey(groupName)){
            this.leaderList.put(groupName, leader);
            this.nameList.put(groupName, leader.getNickName());
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
            i++;
        }
        return groupList;
    }

    @Override
    public T assignNewLeader(String groupName, T leader) throws RemoteException {
        if (isAlive(leader)) {
        //System.err.println("Take over attempt by: " + leader.getNickName());
        //System.err.println("Leader at start: " + this.leaderList.get(groupName).getNickName());
        this.leaderList.put(groupName, leader);
        //System.err.println("Leader after check: " + this.leaderList.get(groupName).getNickName());
        }
        return this.leaderList.get(groupName);
    }

    private boolean isAlive(T comModule) throws RemoteException {
        Long time = System.currentTimeMillis();
        Long pingTime = comModule.ping();
        //System.err.println("Time. " + time);
        //System.err.println("Ping: " + pingTime);
        //System.err.println("Difference: " + (time-pingTime));
        return (Math.abs(time - pingTime) <= 5000 );


    }
}
