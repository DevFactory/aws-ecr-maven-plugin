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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jonshaw on 11/01/2016.
 */

@Mojo( name = "deploy")
public class Deploy extends AbstractMojo
{
    @Parameter(required = true)
    private String registryId;

    @Parameter(required = true)
    private String repositoryName;

    @Parameter(required = true)
    private String imageManifest;

    public void execute() throws MojoExecutionException
    {
        try {
            AmazonECR amazonECR = new AmazonECRClient();

            String manifest = readFile(imageManifest, Charset.defaultCharset());

            getLog().info("Loaded Manifest [" + manifest + "]");

            PutImageRequest request = new PutImageRequest();
            request.setRegistryId(registryId);
            request.setRepositoryName(repositoryName);
            request.setImageManifest(manifest);

            PutImageResult result = amazonECR.putImage(request);

            result.getImage();

        } catch (AmazonServiceException | IOException exception) {
            throw new MojoExecutionException(exception.getMessage(), exception);
        }

//        org.apache.maven.plugin.MojoExecutionException if an unexpected problem occurs. Throwing this exception causes a "BUILD ERROR" message to be displayed.
//        org.apache.maven.plugin.MojoFailureException if an expected problem (such as a compilation failure) occurs. Throwing this exception causes a "BUILD FAILURE" message to be displayed.
    }

    private String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}