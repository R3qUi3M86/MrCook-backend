INSERT INTO public.product_comment(body, create_date, modify_date, rating, title)
VALUES ('bardzo mi sie podoba :)', timestamp '2022-08-18 22:39:19.485000', timestamp '2022-08-18 22:39:19.485000', 5, 'Super produkt!');
INSERT INTO public.product_comment(body, create_date, modify_date, rating, title)
VALUES ('A mi tak srednio. Calskiem spoko opcja dla ludzi co lubia gotowac ale ja jestem w chooy leniwy i mi sie nie chce wiec robi za miske do platkow ;q', timestamp '2022-05-17 15:41:48.000000', timestamp '2022-06-17 15:41:48.000000', 2, 'Taki se...');

INSERT INTO public.user(email, password, member, roles, username, fk_product_comment)
VALUES ('admin@mrcook.com', 'pass', true, 'ADMIN', 'mr-cook', 1);
INSERT INTO public.user(email, password, member, roles, username, fk_product_comment)
VALUES ('stiepan@mrcook.com', 'pass2', true, 'CUSTOMER', 'stiepan', 2);
INSERT INTO public.user(email, password, member, roles, username)
VALUES ('stiepan@mrcook.com', 'pass3', false, 'CUSTOMER', 'marian');
