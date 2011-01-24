package org.jimbarritt.spikes.twist.workflow;

import org.jimbarritt.spikes.twist.bee.*;

import java.util.*;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BeeWaggling {

	public void assertThatTrueIs(boolean input) throws Exception {
		assertTrue(input + " Should be true", input);
	}

	public void waggleSomeBees() throws Exception {
        BeeWaggler waggler = new BeeWaggler();

        List<Bee> bees = waggler.waggleSomeBees();

        assertThat(bees.size(), is(3));
	}

}
