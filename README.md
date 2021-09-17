# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step1) demonstrates how to setup the initial project structure with the first set of unit tests using JUnit5 and the @Test annotation, uses a fake implementation of the Bitcoin Price Index API

Install JDK11 and Maven 3 on Ubuntu 18.04:

Note: This is already done as part of the [Vagrantfile](https://github.com/cloudacademy/java-tdd-bitcoinconverter/blob/step1/Vagrantfile) setup

```
{
wget https://packages.microsoft.com/config/ubuntu/18.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
sudo dpkg -i packages-microsoft-prod.deb
sudo apt-get update
sudo apt-get install -y apt-transport-https
sudo apt-get update
sudo apt-get install -y dotnet-sdk-3.1export DEBIAN_FRONTEND=noninteractive
export APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=1
apt-get update
apt-get install -y tree
echo ========================
echo installing openjdk-11-jdk...
apt-get install -y openjdk-11-jdk
echo ========================
echo installing maven 3.8.2...
cd /tmp
curl -OLs --output /dev/null https://dlcdn.apache.org/maven/maven-3/3.8.2/binaries/apache-maven-3.8.2-bin.tar.gz
tar xf /tmp/apache-maven-*.tar.gz -C /opt
ln -s /opt/apache-maven-3.8.2 /opt/maven
cat <<EOF >> /etc/profile.d/maven.sh
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=/opt/maven/bin:${PATH}
EOF
chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh
echo ========================
echo finished!!
}
```

Test JDK11 and Maven 3 tools:

```
java -version
mvn --version
```

Install custom Java11 and JUnit5 [Maven Archetype](https://github.com/cloudacademy/java11-junit5-archetype):

```
git clone https://github.com/cloudacademy/java11-junit5-archetype.git
cd java11-junit5-archetype
mvn install
```

Create a new Java11 and JUnit5 solution using custom Maven Archetype:

```
cd ..
mvn archetype:generate \
 -DarchetypeGroupId=com.cloudacademy.devops \
 -DarchetypeArtifactId=java11-junit5-archetype \
 -DarchetypeVersion=1.0.0-SNAPSHOT \
 -DgroupId=com.cloudacademy \
 -DartifactId=bitcoin-converter-svc-lib \
 -Dversion=1.0.0-SNAPSHOT \
 -DinteractiveMode=false
```

Build, test, and package Java11 and JUnit5 solution:

```
mvn clean compile test package
```

Run executable Jar:

```
java -jar target/bitcoin-converter-svc-lib-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```