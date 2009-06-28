package com.losalpes.test;

import javax.ejb.Local;

/**
 *
 * @author Orlando
 */
@Local
public interface TestSessionLocal {

    void testMethod(String parameter, String parameter1);
    
}