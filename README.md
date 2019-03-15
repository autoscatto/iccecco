# iccecco
Interpolation Checker Maven plugin


| Badges |
| ------------- |
| [![Build Status](https://travis-ci.org/autoscatto/iccecco.svg?branch=develop)](https://travis-ci.org/autoscatto/iccecco) |
| [![Download](https://api.bintray.com/packages/autoscatto/githubbo/iccecco/images/download.svg)](https://bintray.com/autoscatto/githubbo/iccecco/_latestVersion)  |


INSTALL
-------

### pom.xml ###

```xml
...
<dependencies>
...
<dependency>
  <groupId>com.github.autoscatto</groupId>
  <artifactId>iccecco</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
</dependencies>
```

### settings.xml ###

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd'
          xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>

    <profiles>
        <profile>
            <repositories>
                <repository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-autoscatto-githubbo</id>
                    <name>bintray</name>
                    <url>https://dl.bintray.com/autoscatto/githubbo</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                    <id>bintray-autoscatto-githubbo</id>
                    <name>bintray-plugins</name>
                    <url>https://dl.bintray.com/autoscatto/githubbo</url>
                </pluginRepository>
            </pluginRepositories>
            <id>bintray</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>bintray</activeProfile>
    </activeProfiles>
</settings>
```

