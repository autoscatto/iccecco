package com.github.autoscatto;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

class InterpolationException extends MojoExecutionException {
    public InterpolationException(Object source, String shortMessage, String longMessage) {
        super(shortMessage);
        super.source = source;
        super.longMessage = longMessage;
    }
    public InterpolationException(String message) {
        super(message);
    }
}


@Mojo(name = "validate-interpolation")
public class MyMojo
    extends AbstractMojo
{
    @Parameter(property = "interpolDirs", defaultValue = "conf")
    private String interpolDirs;
    @Parameter(property = "encoding", defaultValue = "UTF-8")
    private String encoding;
    @Parameter(property = "includeFiles", defaultValue = ".*")
    private String includeFiles;
    @Parameter(property = "excludeFiles")
    private String excludeFiles;
    @Parameter(property = "basePath", defaultValue = "${project.build.directory}/docroot")
    private String basePath;
    public void execute() // phase package goal run
        throws MojoExecutionException
    {
        Charset ENCODING = Charset.forName(encoding);
        Pattern regexp = Pattern.compile("\\$\\{[^\\}]+\\}");
        Matcher matcher = regexp.matcher("");

        for (String dir: interpolDirs.split(",")){
            Path p = Paths.get(basePath, dir);
            try {
                List<Path> filesToCheck = Files.walk(p)
                        .filter(s -> Files.isRegularFile(s))
                        .filter(s -> s.toString().matches(includeFiles))
                        .filter(s -> (excludeFiles == null) || !s.toString().matches(excludeFiles))
                        .sorted()
                        .collect(toList());
                for (Path path: filesToCheck){
                    try {
                            BufferedReader reader = Files.newBufferedReader(path, ENCODING);
                            LineNumberReader lineReader = new LineNumberReader(reader);
                        String line = null;
                        while ((line = lineReader.readLine()) != null) {
                            matcher.reset(line); //reset the input
                            if (matcher.find()) {
                                String msg = String.format("[%s:%d] %s", path.toString(),lineReader.getLineNumber(), line );
                                throw new InterpolationException(msg);
                            }
                        }
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                        throw new MojoExecutionException(ex.getMessage());

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new MojoExecutionException(e.getMessage());
            }
        }

    }
}
