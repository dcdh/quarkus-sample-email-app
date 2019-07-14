1. run mailhog using `docker run -p 8025:8025 -p 1025:1025 --rm mailhog/mailhog` or `docker rm $(docker ps -aq) && docker-compose up`
1. launch test
1. go to `http://localhost:8025` to read them :)