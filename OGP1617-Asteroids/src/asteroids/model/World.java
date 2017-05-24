package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import asteroids.part2.CollisionListener;

/**
 * A class of worlds involving a size and entities.
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  Each world can have its size as size .
 *       | canHaveAsSize(this.getSize)
 * @invar   Each world must have proper entities.
 *        | hasProperEntities()
 * @version 1.0
 * @author 	Simon Merckx and Tobias Cornille.
 *         	We both study informatics (1ba).
 *         	Private repo on https://github.com/tobiascornille/Asteroids
 *         	Please send us an email with your account info so we can add you as a contributer.
 */
public class World {
	/**
	 * Initialize this new world as a non-terminated world
	 * with given size and with no entities yet.
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
	public boolean canHaveAsSize(Size size) {
		if (! Double.isFinite(size.getXComponent()) || ! Double.isFinite(size.getYComponent()))
			return false;
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
	 *       | entity.getWorld() != this
	 * @post   This world no longer has the given entity as
	 *         one of its entities.
	 *       | ! new.hasAsEntity(entity)
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalArgumentException {
		if (! this.hasAsEntity(entity) || entity.getWorld() != this) throw new IllegalArgumentException();
		entity.setWorld(null);
		entities.values().remove(entity);
	}
	
	/**
	 * Prepares the given entity for removal during an iterator
	 * 
	 * @param  entity
	 *         The entity to be removed.
	 * @throws IllegalArgumentException
	 *       | ! this.hasAsEntity(entity)
	 * @throws IllegalArgumentException
	 *       | entity.getWorld() != this
	 */
	@Raw
	private void prepareRemovalForEntity(Entity entity) throws IllegalArgumentException {
		if (! this.hasAsEntity(entity) || entity.getWorld() != this) throw new IllegalArgumentException();
		entity.setWorld(null);
	}
	
	 /**
	  * Return the entity, if any, at a given position.
	  * 
	  * @param position
	  * 	   The given position.
	  * @return 
	  * 	  | @see implemantation
	  */
	 public Entity returnEntityGivenPosition(Vector position) {
		 return entities.get(position);
	 }

	/**
	 * Return a set of all the entities of subclass "type" of this world.
	 * @param 	type
	 * 			The subclass of Entity
	 * @return
	 * 		|	@see implementation
	 */
	 public Set<? extends Entity> getEntitiesOfType(Class<? extends Entity> type) {
		Set<Entity> entities = this.getEntities();
	 	return entities.stream().filter(entity -> type.isInstance(entity)).collect(Collectors.toSet());
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
	private Map<Vector, Entity> entities = new HashMap<>();
	
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
			 // We avoid ConcurrentModificationException by using the remove prepare method
			 // together with the iterator remove method from Java.
			 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    this.prepareRemovalForEntity(entity);
			    i.remove();
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
	  * 
	  */
	 public void evolve(double dt, CollisionListener listener) throws IllegalArgumentException { 	 
		 if (Double.isNaN(dt) || dt < 0) throw new IllegalArgumentException();
		 
		 while (dt > 0) {
			 double tC = Double.POSITIVE_INFINITY;
			  Map<Double, Entity[]> collisions = this.getCollisions();
			  if (!collisions.keySet().isEmpty())
				  tC = Collections.min(collisions.keySet());
				  
			  if (tC < dt) {
				  advance(tC);
				  Entity entity = collisions.get(tC)[0];
				  Entity otherEntity = collisions.get(tC)[1];
				  
				  if (otherEntity == null) {
					  Vector collisionPosition = entity.getCollisionBoundaryPosition();
					  if (listener != null)
						  listener.boundaryCollision(entity, collisionPosition.getXComponent(), collisionPosition.getYComponent());
					  this.boundaryCollision(entity, collisionPosition);
				  }
				  
				  else {
					  Vector collisionPosition = entity.getCollisionPosition(otherEntity);
					  if (listener != null)
						  listener.objectCollision(entity, otherEntity, collisionPosition.getXComponent(), collisionPosition.getYComponent());
					  entity.objectCollision(otherEntity);
				  }
				  
				  dt = dt - tC;  
			  }
			  
			  else {
				  advance(dt);
				  for(Ship ship: (Set<Ship>) this.getEntitiesOfType(Ship.class)) {
					  if(ship.getProgram() != null)
						  ship.executeProgram(dt);
				  }
				  return;
			  }
		  }
	 }
	 
	 /**
	  * 
	  */
	 private void advance(double dt) {
		 
		 Map<Vector, Entity> newEntities  = new HashMap<>();
		 for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    entity.move(dt);
			    
			    if (entity instanceof Ship && ((Ship) entity).getThrusterState()) {
			    	((Ship) entity).thrust(dt);
			    }
			    
			    newEntities.put(entity.getPosition(), entity); 	    
		 }
		 this.entities = newEntities;
	 }
	 
	 /**
	  *
	  */
	 private Map<Double, Entity[]> getCollisions() { 
		  Map<Double, Entity[]> collisions = new HashMap<>();
		  double time;
		  
		  for (Iterator<Entity> i = entities.values().iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    
			    for (Iterator<Entity> j = entities.values().iterator(); j.hasNext();) { 
			    	Entity otherEntity = j.next();
			    	
			    	if ((otherEntity != null) && !(entity.overlap(otherEntity)))
			    		time = entity.getTimeToCollision(otherEntity);
			    	else
			    		time = Double.POSITIVE_INFINITY;
					
			    	if	(time != Double.POSITIVE_INFINITY && collisions.get(time) == null)
			    		collisions.put(time, new Entity[] {entity, otherEntity});
			    }
			    
				time = entity.getTimeToCollisionBoundary();	 
				 
				if (time != Double.POSITIVE_INFINITY && collisions.get(time) == null)
					collisions.put(time, new Entity[] {entity, null});		     	 			    	    	
		  }
		  return collisions; 
	 }
	 /**
	  * 
	  *
	  */
	 public double getTimeNextCollision() {
		 Map<Double, Entity[]> collisions = this.getCollisions();
		 return Collections.min(collisions.keySet());  
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 public Vector getPositionNextCollision() { 
		  Map<Double, Entity[]> collisions = this.getCollisions();
		  double time = Collections.min(collisions.keySet()); 
		  Entity[] collidingEntities = collisions.get(time);
		  if (collidingEntities[1] == null)
			  return  collidingEntities[0].getCollisionBoundaryPosition();
		  else
			  return collidingEntities[0].getCollisionPosition(collidingEntities[1]);
	 }
	 
	/**
	  * 
	  * @param entity
	  * @param collisionPosition
	  */
	void boundaryCollision(Entity entity, Vector collisionPosition) {
		 if (entity instanceof Bullet) {
			 if (((Bullet)entity).getCollisionCounter() >= 2) {
				 entity.terminate();
		 		 return;
			 }
			((Bullet)entity).incrementCollisionCounter();
		 }
		 
		 bounceOffBoundary(entity, collisionPosition);
	}
	
	/**
	 * 
	 * @param entity
	 * @param collisionPosition
	 */
	private void bounceOffBoundary(Entity entity, Vector collisionPosition) {
		if (collisionPosition.getXComponent() == 0){
			entity.setVelocity(new Vector(entity.getVelocity().getXComponent() * -1, entity.getVelocity().getYComponent()));
		}
		 else if (collisionPosition.getXComponent() == (entity.getWorld().getSize().getXComponent())){
			 entity.setVelocity(new Vector(entity.getVelocity().getXComponent() * -1, entity.getVelocity().getYComponent()));
		 }
		 if (collisionPosition.getYComponent() == 0){
			 entity.setVelocity(new Vector(entity.getVelocity().getXComponent(), entity.getVelocity().getYComponent() * -1));
		 }
		 else if (collisionPosition.getYComponent() == (entity.getWorld().getSize().getYComponent())){
			 entity.setVelocity(new Vector(entity.getVelocity().getXComponent(), entity.getVelocity().getYComponent() * -1));
		 }
	}
	
	/**
	 * 
	 * @param type
	 * @param ship
	 * @return
	 */
	public <T extends Entity> T getClosestEntityOfType(Class type, Ship ship) {
	 	T closestEntity = null;
	 	double minDistance = Double.POSITIVE_INFINITY;
	 	for (T entity: (Set<T>) this.getEntitiesOfType(type)) {
	 		double distance = entity.getPosition().getDistanceBetween(ship.getPosition());
	 		if (distance < minDistance && ship != entity) {
				minDistance = distance;
	 			closestEntity = entity;
			}
		}

		return closestEntity;
	}
	
	/**
	 * Return a bullet that was fired by the executing ship 
	 * and is not terminated yet. 
	 * 
	 * @param 	executingShip
	 * 			The ship you want the bullet from.
	 * 		  
	 * @return
	 */
	public Entity getBulletFromShip(Ship executingShip) {
		for (Bullet bullet: (Set<Bullet>) this.getEntitiesOfType(Bullet.class))
			if (bullet.getSourceShip() == executingShip)
				return bullet;
		return null;
	}
}
