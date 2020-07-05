docker run --name sokobandb -v ~/IdeaProjects/sokoban/docker/data/mysql:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=testwachtwoord -d mysql:latest
