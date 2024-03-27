package uk.ac.ucl.main;

import java.io.File;
import java.util.List;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import uk.ac.ucl.model.*;
public class Main {

  public static void main(String[] args) throws Exception {
    String webappDirLocation = "src/main/webapp/";
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8139);

    tomcat.getConnector();
    StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

    File additionWebInfClasses = new File("target/classes");

    WebResourceRoot resources = new StandardRoot(ctx);
    resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
      additionWebInfClasses.getAbsolutePath(), "/"));
    ctx.setResources(resources);

    tomcat.start();
    tomcat.getServer().await();
  }
}