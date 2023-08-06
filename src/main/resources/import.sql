INSERT INTO anime(title, episodes, status, author_name, description, img_url) VALUES('Attack on Titan', 250, 'COMPLETE', 'Kukuku', 'Gigante', 'https://icon-aot.com.br/');
INSERT INTO anime(title, episodes, status, author_name, description, img_url) VALUES('100 Things to Do Before You Become a Zombie', 114, 'AIRING', 'Haro Aso e Kotaro Takata', 'Akira via sua vida sendo completamente sugada por uma empresa tóxica. No enquanto, quando de repente sua cidade é assolada por um apocalipse zumbi, ele acha um novo sentido na vida.', 'https://icon-100zombies.com.br/');

INSERT INTO role(role_name) VALUES(0);
INSERT INTO role(role_name) VALUES(1);
INSERT INTO role(role_name) VALUES(2);

INSERT INTO account_stats(watching, completed, dropped, plan_to_watch) VALUES(1, 2, 1, 23);
INSERT INTO profile_user(stats_id, img_url, bio) VALUES(1, 'http://icon.com.br/pedroImg', 'Me chamo Pedro, gosto de ler e moro no Rj.');
INSERT INTO cart(total_animes) VALUES(1);
INSERT INTO tb_user(cart_id, profile_id, name, email, password) VALUES(1, 1, 'Pedro', 'pedro@gmail.com', '$2a$12$oU0V.6b9681VfT2XeOc/G.BAnjvCIzei.et8MLPX.W63ddwmaXNRu');
INSERT INTO anime_order(cart_id) VALUES(1);
INSERT INTO anime_order_details(order_id, anime_id, episode, status_order) VALUES(1, 1, 26, 'WATCHING')
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);

INSERT INTO account_stats(watching, completed, dropped, plan_to_watch) VALUES(2, 20, 0, 45);
INSERT INTO profile_user(stats_id, img_url, bio) VALUES(2, 'http://icon.com.br/lucasImg', 'Me chamo Lucas, sou fã de rock e moro em Bh.');
INSERT INTO cart(total_animes) VALUES(2);
INSERT INTO tb_user(cart_id, profile_id, name, email, password) VALUES(2, 2, 'Lucas', 'lucas@gmail.com', '$2a$10$zsi.35sz9Uh2DtTUhdswS.6BlOpNb4mjPQOTn4yU0ge.pbZoT/GuG');
INSERT INTO anime_order(cart_id) VALUES(2);
INSERT INTO anime_order(cart_id) VALUES(2);
INSERT INTO anime_order_details(order_id, anime_id, episode, status_order) VALUES(2, 1, 0, 'PLAN_TO_WATCH')
INSERT INTO anime_order_details(order_id, anime_id, episode, status_order) VALUES(3, 2, 450, 'COMPLETED')

INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);