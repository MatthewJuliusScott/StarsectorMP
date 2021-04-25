
package com.baeldung.maven.plugin.validator;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * Counts the number of maven dependencies of a project.
 * 
 * It can be filtered by scope.
 *
 */
@Mojo(name = "dependency-counter", defaultPhase = LifecyclePhase.COMPILE)
public class DependencyCounterMojo extends AbstractMojo {

	/**
	 * The project currently being built.
	 **/
	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject				project;

	@Parameter(defaultValue="${localRepository}")
	private ArtifactRepository			localRepository;

	@Parameter(defaultValue="${project.remoteArtifactRepositories}")
	private List<ArtifactRepository>	remoteRepositories;

	@Component
	private RepositorySystem			respositorySystem;

	@Component
	private ArtifactResolver			resolver;

	public void execute() throws MojoExecutionException, MojoFailureException {
		@SuppressWarnings("unchecked")
		final List<Dependency> dependencies = project.getDependencies();

		for (Dependency d : dependencies) {

			final Artifact artifact = respositorySystem
			        .createArtifactWithClassifier(d.getGroupId(),
			                d.getArtifactId(), d.getVersion(), d.getType(),
			                d.getClassifier());

			ArtifactResolutionRequest request = new ArtifactResolutionRequest();
			request.setArtifact(artifact);
			request.setResolveTransitively(true);
			request.setLocalRepository(localRepository);
			request.setRemoteRepositories(remoteRepositories);

			resolver.resolve(request);

			File artifactFile = artifact.getFile();
			getLog().info("This is the plugin output " + artifactFile.getAbsolutePath());
			
			// TODO replace with a property
			Reflections reflections = new Reflections(new ConfigurationBuilder()
					  .setUrls(ClasspathHelper.forPackage("com.fs.starfarer")));

			Set<Class<? extends Object>> subTypes = reflections.getSubTypesOf(Object.class);
			for (Class clazz : subTypes) {
				getLog().info("Plugin output: Class name " + clazz.getName());
			}

		}

	}

}