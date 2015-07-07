import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Room {
	private String name;
	private String description;
	private List<computerPlayer> enemies = new ArrayList<computerPlayer>();
	private List<computerPlayer> npcs = new ArrayList<computerPlayer>();
	private List<item> items = new ArrayList<item>();
	private List<String> exits = new ArrayList<String>();
	
	Room(String n ,String desc, List<computerPlayer> enemy, List<computerPlayer> npc, List<item> itemsArr, List<String> exit) {
		ListIterator iter;
		int i = 0;
		name = n;
		description = desc;
		iter = enemy.listIterator();
		while(iter.hasNext()) {
			enemies.add(enemy.get(i));
			i++;
		}
		i = 0;
		iter = npc.listIterator();
		while(iter.hasNext()) {
			npcs.add(npc.get(i));
			i++;
		}
		//i = 0;
		//iter = itemsArr.listIterator();
		//while(iter.hasNext()) {
		//	items.add(itemsArr.get(i));
		//	i++;
		//}
		i = 0;
		iter = exit.listIterator();
		while(iter.hasNext()) {
			exits.add(exit.get(i));
			i++;
		}
	}
	
	String getName() {
		return name;
	}
	
	String getDescription() {
		return description;
	}
	
	List<computerPlayer> getEnemies() {
		return enemies;
	}
	
	List<computerPlayer> getNpcs() {
		return npcs;
	}
	
	List<item> getItems() {
		return items;
	}
	
	List<String[]> getExits() {
		return exits;
	}
}
