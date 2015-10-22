package us.lsi.ag.agoperators;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.BinaryMutation;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.RandomKey;
import org.apache.commons.math3.genetics.RandomKeyMutation;

import us.lsi.ag.agchromosomes.PermutationIndexSubListChromosome;

import com.google.common.base.Preconditions;

/**
 * <p> Un operador de mutaci�n adecaudo para cromosomas de tipo mixto y que reutiliza los operadores mutaci�n proporcionados
 * en <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/package-summary.html" 
 * target="_blank"> Apache Genetics </a>
 *   
 * @author Miguel Toro
 *
 */

public class SubListMutationPolicy implements MutationPolicy {

	protected static MutationPolicy binaryOperator = new BinaryMutation();
	protected static MutationPolicy randomKeyOperator  = new RandomKeyMutation();
	
	public SubListMutationPolicy() {
		super();
	}
	
	@Override
	public Chromosome mutate(Chromosome chr0) throws MathIllegalArgumentException {
		if (!(chr0 instanceof PermutationIndexSubListChromosome))
			throw new IllegalArgumentException();;
		PermutationIndexSubListChromosome c0 = (PermutationIndexSubListChromosome) chr0;		
		BinaryChromosome binary = c0.getBinary();
		RandomKey<Integer> randomKey = c0.getRandomKey();
		Preconditions.checkArgument(binary != null);		
		Preconditions.checkArgument(randomKey != null);	
		Chromosome c1 = binaryOperator.mutate(binary);
		Preconditions.checkArgument(c1 instanceof BinaryChromosome);
		Chromosome c2 = randomKeyOperator.mutate(randomKey);		
		Preconditions.checkArgument(c2 instanceof RandomKey);
		return new PermutationIndexSubListChromosome(c1,c2);
	}	
	
}