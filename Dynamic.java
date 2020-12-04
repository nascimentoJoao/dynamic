import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Dynamic {
	
	private Integer minimum;
	private Integer minimum_non_recursive;
	private ArrayList<String> movements;
	
	public Dynamic() {
		minimum = Integer.MAX_VALUE;
		minimum_non_recursive = 0;
		movements = new ArrayList<>();
	}
	
	public void countdown(Integer value, Integer count) {
		
		if(value%2 != 0 || value%3 != 0) {
			if(value != 1) {
				countdown(value-1, count+1);
			}
		} 
		
		if(value%3 == 0) { 
			if(value > 1 && count < this.minimum) {
				countdown(value/3, count+1);
			}
		}
		
		if(value%2 == 0) {
			if(value > 1 && count < this.minimum) {
				countdown(value/2, count+1);
			}
		}
		
		
		if(value == 1) {
			if(count < minimum) {
				this.minimum = count;
			}
			return; 
		}
		
	}
	
	public void countdown_memory(Integer value, Integer count) {
		
		this.movements = memory_aux(value, count, new ArrayList<>(), new HashMap<>());
		
	}
	
	public ArrayList<String> memory_aux(Integer value, Integer count, ArrayList<String> movements, HashMap<Integer, Integer> memory) {
		
		if (movements.size() >= count) {
        	return null;
        }
		
        if (value == 1) {
        	return movements;
        }
        
        Integer total_operations = memory.get(value);

        if (total_operations !=null && total_operations <= movements.size()) {
        	return null;
        }
        
        memory.put(value, movements.size());

        Integer size_1 = Integer.MAX_VALUE;
        Integer size_2 = Integer.MAX_VALUE;
        Integer size_3 = Integer.MAX_VALUE;
        
        ArrayList<String> movements_1 = null, movements_2 = null, movements_3 = null;

        if (value % 3 == 0) {
            ArrayList<String> aux = new ArrayList<String>(movements);
            aux.add(" /3 ");
            movements_3 = memory_aux(value / 3, count, aux, memory);
            if (movements_3 != null) {
            	size_3 = movements_3.size();
            }
        }
        
        if(count > size_3) {
        	count = size_3;
        }

        if (value % 2 == 0) {
            ArrayList<String> aux = new ArrayList<String>(movements);
            aux.add(" /2 ");
            movements_2 = memory_aux(value / 2, count, aux, memory);
            if (movements_2 != null) {
             size_2 = movements_2.size();
            }
        }

        if(count > size_2) {
        	count = size_2;
        }
        
        ArrayList<String> aux = new ArrayList<String>(movements);
        aux.add(" -1 ");
        movements_1 = memory_aux(value - 1, count, aux, memory);
        if (movements_1 != null) {
        	size_1 = movements_1.size();
        }

        
        
        Integer result = Math.min(Math.min(size_1, size_2),size_3);
        
        if (result == size_1) {
        	return movements_1;
        }
        
        if (result == size_2) {
        	return movements_2;
        }
        
        return movements_3;
	}
	
	public void countdown_non_recursive(Integer value) {
		
		HashSet<Integer> seen_values = new HashSet<>();
		
		HashMap<Integer, Integer> active_values = new HashMap<>();
		
		Integer count = 0;
		
		active_values.put(value, 0);
		
		while(!active_values.containsKey(1)) {
			HashMap<Integer, Integer> aux = new HashMap<>();
			
			for(Integer entry : active_values.keySet()) {
				if(entry % 3 == 0 && !seen_values.contains(entry / 3)) {
					seen_values.add(entry / 3);
					aux.put(entry/3, count + 1);
				}
				
				if(entry % 2 == 0 && !seen_values.contains(entry / 2)) {
					seen_values.add(entry / 2);
					aux.put(entry/2, count + 1);
				}
				
					seen_values.add(entry - 1);
					aux.put(entry - 1, count + 1);
				
				}
		
				active_values = aux;
				count = count+1;
			
		}
		
		this.minimum_non_recursive = active_values.get(1);
		
	}
	
	public Integer minimum() {
		return this.minimum;
	}
	
	public Integer minimum_non_recursive() {
		return this.minimum_non_recursive;
	}
	
	public ArrayList<String> getMovements() {
		return this.movements;
	}
}