FROM java:8


COPY . /app
WORKDIR /app/

RUN mkdir -p /usr/lib/grails
WORKDIR /usr/lib/grails
RUN wget https://github.com/grails/grails-core/releases/download/v2.5.4/grails-2.5.4.zip
RUN unzip grails-2.5.4.zip && rm grails-2.5.4.zip
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV GRAILS_HOME /usr/lib/grails/grails-2.5.4/
ENV PATH $PATH:$GRAILS_HOME/bin



RUN cd /app && grails war
WORKDIR /usr/local/tomcat/bin
COPY ./target/SpeedMart-0.1.war /usr/local/tomcat/webapp/ROOT.war
CMD ["catalina.sh", "run"]
