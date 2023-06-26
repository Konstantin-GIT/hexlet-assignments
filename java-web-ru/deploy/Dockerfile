FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY /app .

RUN gradle installDist

CMD ./java-web-ru/deploy/build/install/deploy/bin/deploy