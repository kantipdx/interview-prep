package recursion.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class Combinations {
    public static void main(String[] args) {
        int maximumTime = 30;
        List<String> logs = new ArrayList<>(Arrays.asList("12 10 sign-in", "5 10 sign-in","5 100 sign-out", "12 20 sign-out", "8 20 sign-in", "8 50 sign-out"));
        System.out.println(processEvents(logs, maximumTime));

    }
    private static List<String> processEvents(List<String> logs, int maximumTime){
        HashMap<Integer, Log> userEntries  =  new HashMap();
        for(String log: logs){
            String[] entries = log.split("\\s+");
            Log logEntry = new Log();
            logEntry.setUserId(Integer.parseInt(entries[0]));
            if(entries[2].equals("sign-in")){
                logEntry.setAction("sign-in");
                logEntry.setLogin(Integer.parseInt(entries[1]));
            }
            else if(entries[2].equals("sign-out")){
                logEntry.setAction("sign-out");
                logEntry.setLogOut(Integer.parseInt(entries[1]));
            }
            //Expectation is sign-in always occurs prior to sign-out in logs.
            if(userEntries.containsKey(Integer.parseInt(entries[0]))){
                Log existingLog = userEntries.get(logEntry.getUserId());
                existingLog.setLogOut(logEntry.getLogOut());
                userEntries.put(logEntry.getUserId(), existingLog);
            }
            else{
                userEntries.put(logEntry.getUserId(), logEntry);
            }
        }
        List<Integer> userIds = new ArrayList<>();

        for(Log log: userEntries.values()){
            if(log.getDuration() <=maximumTime){
                userIds.add(log.getUserId());
            }
        }
        Collections.sort(userIds);
        List<String> users = userIds.stream().map(Object::toString)
                .collect(Collectors.toList());

        return users;
    }

}
class Log{
    public Log(){

    }
    private int userId;
    private int login = 0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public int getLogOut() {
        return logOut;
    }

    public void setLogOut(Integer logOut) {
        this.logOut = logOut;
    }

    public Integer getDuration() {
        return this.logOut - this.login;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private int logOut = Integer.MAX_VALUE;
    private int duration;
    private String action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return userId == log.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
