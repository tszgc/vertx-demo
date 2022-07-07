INSERT INTO `t_order` (`id`, `user_id`, `create_time`, `is_report`) VALUES (1, 1, '2022-07-07 21:29:03', 0);
INSERT INTO `t_order` (`id`, `user_id`, `create_time`, `is_report`) VALUES (2, 1, '2022-07-07 21:29:19', 0);

INSERT INTO `t_order_detail` (`id`, `order_id`, `price`, `num`, `goods_id`) VALUES (1, 1, 20.00, 1, 1);
INSERT INTO `t_order_detail` (`id`, `order_id`, `price`, `num`, `goods_id`) VALUES (2, 1, 30.00, 2, 1);
INSERT INTO `t_order_detail` (`id`, `order_id`, `price`, `num`, `goods_id`) VALUES (3, 2, 40.00, 1, 1);
