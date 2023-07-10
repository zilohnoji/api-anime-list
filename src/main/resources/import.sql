INSERT INTO account_stats(watching, completed, dropped, plan_to_watch) VALUES(3, 10, 1, 250);

INSERT INTO tb_user(name, email, password) VALUES('Pedro', 'pedro@gmail.com', '123');

INSERT INTO profile_user(stats_id, user_id, img_url, bio) VALUES(1, 1, 'http://icon.com.br/pedroImg', 'Sou o pedro');

INSERT INTO cart(user_id, total_animes) VALUES(1, 0);

INSERT INTO anime(title, episodes, status, author_name, description, img_url) VALUES('Atack on Titan', 250, 'AIRING', 'Kukuku', 'Gigante', 'imagemfoda');

INSERT INTO anime_order(anime_id, cart_id, episode, status_order) VALUES(1, 1, 14, 'WATCHING');