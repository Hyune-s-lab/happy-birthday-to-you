./gradlew bootjar

sudo docker build -f docker/Dockerfile -t happy-birthday-to-you .
sudo docker-compose -f docker/docker-compose.yml --env-file docker/.env.prod up
