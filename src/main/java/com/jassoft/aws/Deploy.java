package com.jassoft.aws;

import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClient;
import com.amazonaws.services.ecr.model.PutImageRequest;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by jonshaw on 11/01/2016.
 */

@Mojo( name = "deploy")
public class Deploy extends AbstractMojo
{
    @Parameter
    private String repositoryName;

    public void execute() throws MojoExecutionException
    {
        AmazonECR amazonECR = new AmazonECRClient();

        PutImageRequest request = new PutImageRequest();
        request.setRepositoryName(repositoryName);

        amazonECR.putImage(request);
    }
}