$bootstrap = <<SCRIPT
export DEBIAN_FRONTEND=noninteractive
export APT_KEY_DONT_WARN_ON_DANGEROUS_USAGE=1

apt-get update
apt-get install -y tree

echo ========================

echo installing openjdk-11-jdk...
apt-get install -y openjdk-11-jdk

echo ========================

echo installing LATEST maven ...
cd /tmp
LATEST_SEMVER=$(curl -s https://repo1.maven.org/maven2/org/apache/maven/maven/maven-metadata.xml \
| grep "<version>3.*</version>" \
| tr -s " " \
| cut -d ">" -f2 \
| cut -d "<" -f1 \
| sort -V -r \
| head -1)
LATEST_SEMVER=${LATEST_SEMVER:=3.9.3} # default to 3.9.3 if not detected in previous step
echo downloading https://dlcdn.apache.org/maven/maven-3/$LATEST_SEMVER/binaries/apache-maven-$LATEST_SEMVER-bin.tar.gz ...
curl -OLs --output /dev/null https://dlcdn.apache.org/maven/maven-3/$LATEST_SEMVER/binaries/apache-maven-$LATEST_SEMVER-bin.tar.gz
tar xf /tmp/apache-maven-*.tar.gz -C /opt
ln -s /opt/apache-maven-$LATEST_SEMVER /opt/maven
cat <<EOF >> /etc/profile.d/maven.sh
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=/opt/maven/bin:${PATH}
EOF
chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh

echo ========================

echo finished ...
SCRIPT

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/bionic64"
  config.vm.provision "shell", inline: $bootstrap
end