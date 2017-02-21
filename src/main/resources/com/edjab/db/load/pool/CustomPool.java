package com.edjab.db.load.pool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.target.CommonsPoolTargetSource;

public class CustomPool extends CommonsPoolTargetSource  {
	
	public void initCustomPool() throws Exception {
		
	    List<Object> beans = new ArrayList<Object>();

	    //create and retain minIdle objects to force the creation of others
	    for(int i=0 ; i<this.getMinIdle() ; ++i)
	    beans.add(this.getTarget());

	    for(Object o : beans)
	      this.releaseTarget(o);
	  }
}
