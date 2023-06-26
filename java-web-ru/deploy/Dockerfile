FROM gradle:7.4.0-jdk17

WORKDIR /src/main/java/exercise/App

COPY /src/main/java/exercise/App .

RUN ./gradlew installDist

CMD ./build/install/deploy/bin/deploy