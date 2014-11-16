package epoc.impl.result;

import epoc.Job;
import epoc.impl.Server;

import java.util.ArrayList;

/**
 * Created by Gwen on 16/11/2014.
 */
public class ResultAlgorithm implements Cloneable{
    private ArrayList<Server> servers;
    private ArrayList<Job> rejects;

    public ResultAlgorithm(ArrayList<Server> servers, ArrayList<Job> rejects) {
        this.servers = servers;
        this.rejects = rejects;
    }

    @Override
    public String toString() {
        String serversString ="";
        for (Server server : servers){
            serversString += "\t" + server;
        }
        return "----- Liste des serveurs: \n" + serversString
                + "----- Liste des rejets: " + rejects;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public ArrayList<Job> getRejects() {
        return rejects;
    }

    public void setServers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    public void setRejects(ArrayList<Job> rejects) {
        this.rejects = rejects;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ResultAlgorithm cloned =  (ResultAlgorithm)super.clone();

        ArrayList<Server> clonedServers = new ArrayList<Server>();
        for (Server server : this.getServers()){
            clonedServers.add((Server) server.clone());
        }
        cloned.setServers(clonedServers);

        ArrayList<Job> clonedRejects = new ArrayList<Job>();
        for (Job job : this.getRejects()){
            clonedRejects.add((Job) job.clone());
        }
        cloned.setRejects(clonedRejects);
        return cloned;
    }
}
