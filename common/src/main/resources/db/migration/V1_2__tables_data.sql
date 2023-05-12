INSERT INTO public.product (id, sku, description, created, changed, is_deleted, srp1, srp2, srp3, barcode)
VALUES (1, 'SK2020', 'Notebook', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', false, 'MobileDevices',
        'Notebooks', 'HP', 32323222);
INSERT INTO public.product (id, sku, description, created, changed, is_deleted, srp1, srp2, srp3, barcode)
VALUES (2, 'SK2021', 'Notebook', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', false, 'MobileDevices',
        'Notebooks', 'HP', 3434343);
INSERT INTO public.product (id, sku, description, created, changed, is_deleted, srp1, srp2, srp3, barcode)
VALUES (3, 'NB303030', 'Smartphone', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', false,
        'MobileDevices', 'Smartphones', 'SAM', 43434);

INSERT INTO public.analytics (id, batch_number, country_of_import, created, changed, is_deleted)
VALUES (1, '080523', 'CHN', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', 'false');

INSERT INTO public.analytics (id, batch_number, country_of_import, created, changed, is_deleted)
VALUES (2, '080223', 'POL', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', 'false');

INSERT INTO public.analytics (id, batch_number, country_of_import, created, changed, is_deleted)
VALUES (3, '080123', 'CHN', '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000', 'false');

INSERT INTO public.l_product_analytics (id, product_id, analytics_id, created, changed)
VALUES (1, 1, 2, '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000');

INSERT INTO public.l_product_analytics (id, product_id, analytics_id, created, changed)
VALUES (1, 2, 1, '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000');

INSERT INTO public.l_product_analytics (id, product_id, analytics_id, created, changed)
VALUES (1, 1, 2, '2023-05-07 13:49:10.000000', '2023-05-07 13:49:11.000000');