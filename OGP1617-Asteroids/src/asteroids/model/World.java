package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Simon Merckx and Tobias Cornille
 * 
 * @invar  Each world can have its size as size .
 *       | canHaveAsSize(this.getSize()[0], this.getSize()[1])
 * @invar   Each world must have proper entities.
 *        | hasProperEntities()
 * 
 */
public class World {
	/**
	 * Initialize this new world as a non-terminated world
	 * with given width and height and with no entities yet.
	 * 
	 * @param width
	 * 		  The width for this new world.
	 * 
	 * @param height
	 * 		  The height of this new world.
	 * 			
	 * @post  
	 *       | if (canHaveAsSize(width, height))
	 *       |   then new.getSize() == new double[] {width, height}
	 *       |   else new.getWidth() == new double[] {Double.MAX_VALUE, Double.MAX_VALUE}  
	 * @post   This new world has no entities yet.
	 *       | new.getNbEntities() == 0
	 */
	public World (double width, double height) {
		if (canHaveAsSize(width, height)) 
			this.size = new double[] {width, height};
		else
			this.size = new double[] {Double.MAX_VALUE, Double.MAX_VALUE};
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
		this.size = new double[] {1000, 1000};
	}	
	
	/**
	 * Return the size of this world.
	 */
	@Basic @Raw @Immutable
	public double[] getSize() {
		return this.size;
	}
	
	/**
	 * Check whether this world can have the given size as its size.
	 *  
	 * @param  width
	 *         The width of the size to check.
	 * @param  height
	 *         The height of the size to check.
	 * @return 
	 *       | @see implementation
	 */
	@Raw
	private boolean canHaveAsSize(double width, double height) {
		return ((0 <= width && width <= Double.MAX_VALUE) && (0 <= height && height <= Double.MAX_VALUE));
	}
	
	/**
	 * Variable registering the size of this world.
	 */
	private final double[] size;
	
	/**
	 * Return the set collecting all the entities
	 * of this world.
	 * @return
	 * 		|	@see implementation
	 */
	@Basic
	public Set<Entity> getEntities() {
		return this.entities;
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
		return entities.contains(entity);
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
		for (Entity entity : entities) {
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
	 * @pre    The given entity is effective and already references
	 *         this world.
	 *       | (entity != null) && (entity.getWorld() == this)
	 * @post   This world has the given entity as one of its entities.
	 *       | new.hasAsEntity(entity)
	 */
	public void addEntity(@Raw Entity entity) {
		assert (entity != null) && (entity.getWorld() == this);
		entities.add(entity);
	}

	/**
	 * Remove the given entity from the set of entities of this world.
	 * 
	 * @param  entity
	 *         The entity to be removed.
	 * @pre    This world has the given entity as one of
	 *         its entities, and the given entity does not
	 *         reference any world.
	 *       | this.hasAsEntity(entity) &&
	 *       | (entity.getWorld() == null)
	 * @post   This world no longer has the given entity as
	 *         one of its entities.
	 *       | ! new.hasAsEntity(entity)
	 */
	@Raw
	public void removeEntity(Entity entity) {
		assert this.hasAsEntity(entity) && (entity.getWorld() == null);
		entities.remove(entity);
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
	private final Set<Entity> entities = new HashSet<Entity>();
	
	/**
	 * Terminate this world.
	 *
	 * @post   This world  is terminated.
	 *       | new.isTerminated()
	 @post   All entities belonging to this ship
	 *         upon entry, have been terminated.
	 *       | for each entity in Entity:
	 *       |   if (this.hasAsEntity(entity))
	 *       |     then ((new entity).isTerminated()) 
	 */
	 public void terminate() {
		 if (!isTerminated()) {
			// We avoid ConcurrentModificationException by using an iterator
			 for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			    Entity entity = i.next();
			    removeEntity(entity);
			    entity.terminate();
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
	 
}
