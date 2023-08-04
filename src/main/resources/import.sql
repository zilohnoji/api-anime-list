INSERT INTO account_stats(watching, completed, dropped, plan_to_watch) VALUES(3, 10, 1, 250);

INSERT INTO profile_user(stats_id, img_url, bio) VALUES(1, 'http://icon.com.br/pedroImg', 'Sou o pedro');

INSERT INTO cart(total_animes) VALUES(1);

INSERT INTO tb_user(cart_id, profile_id, name, email, password) VALUES(1, 1, 'Pedro', 'pedro@gmail.com', '$2a$12$oU0V.6b9681VfT2XeOc/G.BAnjvCIzei.et8MLPX.W63ddwmaXNRu');

INSERT INTO anime(title, episodes, status, author_name, description, img_url) VALUES('Attack on Titan', 250, 'AIRING', 'Kukuku', 'Gigante', 'imagemfoda');

INSERT INTO anime_order(anime_id, cart_id, episode, status_order) VALUES(1, 1, 14, 'WATCHING');

INSERT INTO role(role_name) VALUES(0);

INSERT INTO role(role_name) VALUES(1);

INSERT INTO role(role_name) VALUES(2);

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);