# aws-ecr-maven-plugin
[![Build Status](https://travis-ci.org/JassoftLtd/aws-ecr-maven-plugin.svg)](https://travis-ci.org/JassoftLtd/aws-ecr-maven-plugin)

## Maven usage

```xml
<plugin>
  <groupId>com.jassoft.aws</groupId>
  <artifactId>aws-ecr-maven-plugin</artifactId>
  <version>1.0-SNAPSHOT</version>
  <configuration>
    <repositoryName>{Aws-Repo-URL}/ImageName</repositoryName>
    <imageManifest>imageManifest</imageManifest>
  </configuration>
</plugin>
```