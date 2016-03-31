# User schema

# --- !Ups
create table `apartments` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` TEXT NOT NULL,`rooms` INT
NOT NULL,`area` DOUBLE NOT NULL)

# --- !Downs
drop table `apartments`