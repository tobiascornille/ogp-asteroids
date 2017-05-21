package asteroids.model;

import java.util.List;

import asteroids.programs.MyFunction;
import asteroids.programs.MyStatement;

public class Program {

	public Program(List<MyFunction> functions, MyStatement main) {
		
	}
	
	public void setExecutingShip(Ship ship) {
        this.executingShip = ship;
    }
	
    public Ship getExecutingShip() {
        return this.executingShip;
    }
    
    private Ship executingShip;
    
    
}
