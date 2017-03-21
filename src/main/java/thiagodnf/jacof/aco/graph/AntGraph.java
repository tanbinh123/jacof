package thiagodnf.jacof.aco.graph;

import java.util.Arrays;

import org.apache.log4j.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.graph.initialization.AbstractTrailInitialization;
import thiagodnf.jacof.problem.Problem;

/**
 * This class represents the graph where the ants 
 * will traveled. The default implementation uses a double matrix
 * to represent the pheronome's values.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class AntGraph {

	/** The pheromone matrix */
	protected double[][] tau;

	/** The addressed problem*/
	protected Problem problem;

	/** The class logger*/
	final static Logger LOGGER = Logger.getLogger(AntGraph.class);

	/**
	 * Constructor
	 * 
	 * @param problem The addressed problem
	 */
	public AntGraph(Problem problem) {

		checkNotNull(problem, "The problem cannot be null");

		LOGGER.info("Creating a AntGraph instance by using a double matrix");

		this.problem = problem;
	}

	/**
	 * Initialize all edges with the T0 values. 
	 * 
	 * @param trailInitialization The method used to initialize the arc(i,j)
	 */
	public void initialize(AbstractTrailInitialization trailInitialization) {

		checkNotNull(trailInitialization, "The trailInitialization cannot be null");

		LOGGER.info("The trail initilization is " + trailInitialization);

		int numberOfNodes = problem.getNumberOfNodes();

		LOGGER.info("Creating a graph with " + numberOfNodes + " nodes");

		this.tau = new double[numberOfNodes][numberOfNodes];

		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = 0; j < numberOfNodes; j++) {
				this.tau[i][j] = trailInitialization.getT0();
			}
		}

		LOGGER.info("Definied T0=" + trailInitialization.getT0() + " for all edges");
	}

	/**
	 * Set the pheromone value for an arc(i,j)
	 * 
	 * @param i Starting vertex
	 * @param j Final vertex
	 * @param value The pheromone value
	 */
	public void setTau(int i, int j, double value) {
		this.tau[i][j] = value;
	}

	/**
	 * Get the pheromone value for an arc(i,j)
	 * 
	 * @param i Starting vertex
	 * @param j Final vertex
	 * @return The pheromone value
	 */
	public synchronized double getTau(int i, int j) {
		return this.tau[i][j];
	}
	
	/**
	 * Get the the pheromone matrix
	 * 
	 * @return the matrix
	 */
	public double[][] getTau(){
		return this.tau;
	}

	/**
	 * Returns a string representation of the object. 
	 */	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < tau.length; i++) {
			builder.append(Arrays.toString(tau[i])).append("\n");
		}

		return builder.toString();
	}
}
