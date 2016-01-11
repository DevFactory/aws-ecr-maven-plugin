package com.jassoft.aws;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.Test;

import java.io.File;

/**
 * Created by jonshaw on 11/01/2016.
 */
public class DeployTest extends AbstractMojoTestCase {

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        // required for mojo lookups to work
        super.setUp();
    }

    @Test(expected = MojoExecutionException.class)
    public void testExecute() throws Exception {
        try {
            File testPom = new File(getBasedir(),
                    "src/test/resources/basic-invalid-repository-test-plugin-config.xml");

            Deploy mojo = (Deploy) lookupMojo("deploy", testPom);

            assertNotNull(mojo);

            mojo.execute();
        } catch (MojoExecutionException exception) {
            return;
        }
        fail("Should have thrown MojoExecutionException");
    }
}