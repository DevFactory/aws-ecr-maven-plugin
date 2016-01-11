package com.jassoft.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClient;
import com.amazonaws.services.ecr.model.PutImageRequest;
import com.amazonaws.services.ecr.model.PutImageResult;
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

    @Parameter
    private String imageManifest;

    public void execute() throws MojoExecutionException
    {
        try {
            AmazonECR amazonECR = new AmazonECRClient();

            PutImageRequest request = new PutImageRequest();
            request.setRepositoryName(repositoryName);
            request.setImageManifest(imageManifest);

            PutImageResult result = amazonECR.putImage(request);

            result.getImage();

        } catch (AmazonServiceException exception) {
            throw new MojoExecutionException(exception.getMessage(), exception);
        }

//        org.apache.maven.plugin.MojoExecutionException if an unexpected problem occurs. Throwing this exception causes a "BUILD ERROR" message to be displayed.
//        org.apache.maven.plugin.MojoFailureException if an expected problem (such as a compilation failure) occurs. Throwing this exception causes a "BUILD FAILURE" message to be displayed.
    }
}