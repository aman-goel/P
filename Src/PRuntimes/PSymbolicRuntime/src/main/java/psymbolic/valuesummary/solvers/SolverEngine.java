package psymbolic.valuesummary.solvers;

import psymbolic.valuesummary.solvers.bdd.PJBDDImpl;
import psymbolic.valuesummary.solvers.sat.JavaSmtImpl;
import psymbolic.valuesummary.solvers.sat.YicesImpl;

/**
 * Represents the generic backend engine
 */
public class SolverEngine {
    private static SolverEngine solverEngine;
    private static SolverLib solverImpl;
    private static SolverType solverType;
	
	// Make this constructor static so that the class cannot be instantiated
    public SolverEngine() {
    	setSolver(SolverType.BDD);
    }

    public static SolverEngine getEngine() {
        return solverEngine;
    }
    
    public static SolverLib getSolver() {
        return solverImpl;
    }
    
    public static void resetEngine() {
    	resetEngine(solverType);
    }

    public static void resetEngine(SolverType type) {
    	setSolver(type);
    }

    public static void cleanupEngine() {
        solverImpl.cleanup();
    }
    
    public static void setSolver(SolverType type) {
    	solverType = type;
    	switch(type) {
    	case BDD:		solverImpl = new PJBDDImpl(false);
    		break;
    	case CBDD:		solverImpl = new PJBDDImpl(true);
    		break;
    	case YICES2:	solverImpl = new YicesImpl();
			break;
		default:		solverImpl = new JavaSmtImpl(type);
    	}
    }

}
