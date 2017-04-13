package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  Each world can have its size as size .
 *       | canHaveAsSize(this.getSize)
 * @invar   Each world must have proper entities.
 *        | hasProperEntities()
 * 
 */
public class World {
	/**
	 * Initialize this new world as a non-terminated world
	 * with given width and height and with no entities yet.
	 * 
	 * @param 	size
	 * 		  	The size for this new world.		
	 * @post  
	 *   	| 	if (canHaveAsSize(new Size(width, height))
	 *      |   	then new.getSize() == new Size(width, height)
	 *      | 	else 
	 *      |		new.getWidth() == new Size(Double.MAX_VALUE, Double.MAX_VALUE)  
	 * @post   This new world has no entities yet.
	 *       | new.getNbEntities() == 0
	 */
	public World (Size size) {
		if (canHaveAsSize(size)) 
			this.size = size;
		else
			this.size = Size.MAX_SIZE;
	}

	/**
	 * Initialize this new world as a non-terminated world with 
	 * no entities yet.
	 * 
	 * @post   This new world has no entities yet.
	 *       | new.getNbEntities() == 0
	 */
	@Raw
	public World() {
		this.size = Size.DEFAULT_SIZE;
	}	
	
	/**
	 * Return the size of this world.
	 */
	@Basic @Raw @Immutable
	public Size getSize() {
		return this.size;
	}
	
	/**
	 * Check whether this world can have the given size as its size.
	 *  
	 * @param  	size
	 *         	The size to check.
	 * @return 
	 *   	|	@see implementation
	 */
	@Raw
	private boolean canHaveAsSize(Size size) {
		return (Size.MIN_SIZE.compareTo(size) <= 0) && (size.compareTo(Size.MAX_SIZE) <= 0);
	}
	
	/**
	 * Variable registering the size of this world.
	 */
	private final Size size;
	
	/**
	 * Return the set collecting all the entities
	 * of this world.
	 * @return
	 * 		|	@see implementation
	 */
	@Basic
	public Set<Entity> getEntities() {
		return new HashSet<Entity>(this.entities.values());
	}
	
	/**
	 * Check whether this world has the given entity as one of its
	 * entities.
	 * 
	 * @param  entity
	 *         The entity to check.
	 */
	@Basic
	@Raw
	public boolean hasAsEntity(@Raw Entity entity) {
		return entities.containsValue(entity);
	}

	/**
	 * Check whether this world can have the given entity
	 * as one of its entities.
	 * 
	 * @param  entity
	 *         The entity to check.
	 * @return True if and only if the given entity is effective
	 *         and that entity is a valid entity for a world.
	 *       | result ==
	 *       |   (entity != null) &&
	 *       |   Entity.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsEntity(Entity entity) {
		return (entity != null) && (entity.isValidWorld(this));
	}

	/**
	 * Check whether this world has proper entities attached to it.
	 * 
	 * @return True if and only if this world can have each of the
	 *         entities attached to it as one of its entities,
	 *         and if each of these entities references this world as
	 *         the world to which they are attached.
	 *       | for each entity in Entity:
	 *       |   if (hasAsEntity(entity))
	 *       |     then canHaveAsEntity(entity) &&
	 *       |          (entity.getWorld() == this)
	 */
	public boolean hasProperEntities() {
		for (Entity entity : entities.values()) {
			if (!canHaveAsEntity(entity))
				return false;
			if (entity.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of entities associated with this world.
	 *
	 * @return  The total number of entities collected in this world.
	 *        | result ==
	 *        |   card({entity:Entity | hasAsEntity({entity)})	// ???????
	 */
	public int getNbEntities() {
		return entities.size();
	}

	/**
	 * Add the given entity to the set of entities of this world.
	 * 
	 * @param  entity
	 *         The entity to be added.
	 * @throws IllegalArgumentException
	 * 		 | entity == null
	 * @throws IllegalArgumentException
	 * 		 | entity.getWorld() != this
	 * @throws IllegalArgumentException
	 * 		 | ! entity.hasValidPositionInWorld(this)
	 * @post   This world has the given entity as one of its entities.
	 *       | new.hasAsEntity(entity)
	 */
	public void addEntity(@Raw Entity entity) throws IllegalArgumentException {
		if (entity == null || entity.getWorld() != null) 
			throw new IllegalArgumentException();
		if (! entity.hasValidPositionInWorld(this))
			throw new IllegalArgumentException();
		entity.setWorld(this);
		entities.put(entity.getPosition(), entity);
	}

	/**
	 * Remove the given entity from the set of entities of this world.
	 * 
	 * @param  entity
	 *         The entity to be removed.
	 * @throws IllegalArgumentException
	 *       | ! this.hasAsEntity(entity)
	 * @throws IllegalArgumentException
	 *       | entity.getWorld() != null
	 * @post   This world no longer has the given entity as
	 *         one of its entities.
	 *       | ! new.hasAsEntity(entity)
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalArgumentException {
		if (! this.hasAsEntity(entity) || entity.getWorld() != null) throw new IllegalArgumentException();
		entity.setWorld(null);
		entities.values().remove(entity);
	}

	/**
	 * Variable referencing a set collecting all the entities
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | entities != null
	 * @invar  Each entity registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each entity in entities:
	 *       |   ( (entity != null) &&
	 *       |     (! entity.isTerminated()) )
	 */
	private final Map<Vector, Entity> entities = new HashMap<>();
	
	/**
	 * Terminate this world.
	 *
	 * @post   This world  is terminated.
	 *       | new.isTerminated()
	 * @post   All entities belonging to this world
	 *         upon entry, have been terminated.
	 *       | for each entity in Entity:
	 *       |   if (this.hasAsEntity(entity))
	 *       |     then ((new entity).isTerminated()) 
	 */
	 public void terminate() {
		 if (!isTerminated()) {
			// We avoid ConcurrentModificationException by using an iterator
			 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
				this.removeEntity(entity);  
			 }
			 this.isTerminated = true;
		 }
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this world
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this world is terminated.
	  */
	 private boolean isTerminated = false;
	 
	 /**
	  * Returns the entity, if any, at a given position.
	  * 
	  * @param postion
	  * 	   The given position.
	  * @return 
	  * 	  | @see implemantation
	  */
	 public Entity returnEntityGivenPosition(Vector position) {
		 return entities.get(position);
	 }
	 
	 /**
	  * 
	  * @return
	  * 	  | @see implementation
	  */
	 public Set<Ship> getWorldShips() {	 
		 Set<Ship> ships = new HashSet<>();
		 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    if (entity instanceof Ship)
			    	ships.add((Ship) entity);
		 }
		 
		 return ships;
			    	
	 }
	 
	 /**
	  * @return 
	  *       | @see implementation
	  */
	 public Set<Bullet> getWorldBullets() {	 
		 Set<Bullet> bullets = new HashSet<>();
		 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    if (entity instanceof Bullet)
			    	bullets.add((Bullet) entity);
		 }
		 
		 return bullets;
			    	
	 }
	 
	 /**
	  * 
	  * @param dt
	  */
	 private void advance(double dt) {
		 
		 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    entity.move(dt);
			    
			    if (entity instanceof Ship && ((Ship) entity).getThrusterState()) {
			    	((Ship) entity).thrust(dt);
			    }
			    
			    // Update position here
		 }
		 
		 
		 
	 }
	 
	 /**
	  * 
	  * @param dt
	  * 
	  */
	 public void evolve(double dt) {
		 
		  double tC = 0;
		  
		  while (true) {
			  
			  Map<Double, Entity[]> collisions = new HashMap<>();
			  double time;
			  
			  for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
				    Entity entity = i.next();
				    
				    for (Iterator<Entity> j = entities.values().iterator(); j.hasNext();) { 
				    	
				    	 Entity otherEntity = j.next();
				    	 time = entity.getTimeToCollision(otherEntity);
				    	 if	(time != Double.POSITIVE_INFINITY && collisions.get(time) == null)
				    		 collisions.put(time, new Entity[] {entity, otherEntity});
				    	 
				     time = entity.getTimeToCollisionBoundary();	 
				     if	(time != Double.POSITIVE_INFINITY && collisions.get(time) == null)
				    	 collisions.put(time, new Entity[] {entity, null});		     	 			    	 
				    }    	
			  }
			  
			  tC = Collections.min(collisions.keySet());
			
			  if (tC < dt) {
				  advance(tC);
				  // Resolve C here
				  dt = dt - tC;
			  }
			  
			  else {
				  advance(dt);
				  break;
			  }
		 	
		  }
		 		 
	 }

	 /**
	  * 
	  * @param entity
	  * @param otherEntity
	  */
	 private void resolveCollision(Entity entity, Entity otherEntity) {
		 
		 if (entity instanceof Ship && otherEntity instanceof Ship) {
			 
			  Vector newVelocityEntity;
			  Vector newVelocityOtherEntity;
			  Vector dv = otherEntity.getVelocity().subtract(entity.getVelocity());
			  double sigma = entity.getRadius() + otherEntity.getRadius();
			  Vector dr = otherEntity.getPosition().subtract(entity.getPosition());
			  double massEntity = entity.getMass();
			  double massOtherEntity = otherEntity.getMass();
			  			  
			  double j = ((2 * massEntity * massOtherEntity  * dv.dot(dr) ) / sigma * (massEntity+ massOtherEntity));
			  double jX = ((j * otherEntity.getPosition().getXComponent() - entity.getPosition().getXComponent()) / sigma );
			  double jY = ((j * otherEntity.getPosition().getYComponent() - entity.getPosition().getYComponent()) / sigma );
			  
			  newVelocityEntity = new Vector (entity.getVelocity().getXComponent() + (jX / entity.getMass()), entity.getVelocity().getYComponent() + (jY/entity.getMass()));
			  newVelocityOtherEntity = new Vector (otherEntity.getVelocity().getXComponent() + (jX / otherEntity.getMass()), otherEntity.getVelocity().getYComponent() + (jY/otherEntity.getMass()));
			  
			  entity.setVelocity( newVelocityEntity);
			  otherEntity.setVelocity( newVelocityOtherEntity);
			  
		 }
		 
		 else if (entity instanceof Bullet && (otherEntity != ((Bullet) entity).getShip() || otherEntity instanceof Bullet )) {
			entity.terminate();
			otherEntity.terminate();		
	     }
		 
		 else if (entity instanceof Bullet && otherEntity instanceof Ship) {
			 Bullet bullet = (Bullet)entity;
			 Ship ship = (Ship) otherEntity;
			 
			 if (otherEntity == bullet.getShip()) {
				 
				 this.removeEntity(bullet);
				 bullet.setPosition(ship.getPosition());
				 ship.loadBullet(bullet);	 
			 }
			 
		 }
		 
		 
		 
	 }
	 
}
