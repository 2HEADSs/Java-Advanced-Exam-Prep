package guild;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Guild {

    private String name;
    private int capacity;
    private List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.roster.size() < this.capacity) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        for (Player player : this.roster) {
            if (player.getName().equals(name)) {
                return this.roster.remove(player);
            }
        }
        return false;
    }

    public void promotePlayer(String name) {
        for (Player player : this.roster) {
            if (player.getName().equals(name)) {
                if (!player.getRank().equals("Member")) {
                    player.setRank("Member");
                }
                break;
            }
        }
    }

    public void demotePlayer(String name) {
        for (Player player : this.roster) {
            if (player.getName().equals(name)) {
                if (!player.getRank().equals("Trial")) {
                    player.setRank("Trial");
                }
                break;
            }
        }
    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> playersList = new ArrayList<>();
        Iterator<Player> iterator = this.roster.iterator();

        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getClazz().equals(clazz)) {
                iterator.remove();
                playersList.add(player);
            }
        }

        Player[] array = new Player[playersList.size()];
        for (int i = 0; i < playersList.size(); i++) {
            array[i] = playersList.get(i);
        }
        return array;
    }

    public int count() {
        return this.roster.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Players in the guild: ").append(this.name).append(":");
        sb.append(System.lineSeparator());
        this.roster.forEach(r -> sb.append(r.toString()).append(System.lineSeparator()));
        return sb.toString();
    }
}
