-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июн 16 2017 г., 09:18
-- Версия сервера: 5.7.14
-- Версия PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `agency`
--

-- --------------------------------------------------------

--
-- Структура таблицы `clients`
--

CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `passport` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sur_name` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `clients`
--

INSERT INTO `clients` (`id`, `first_name`, `last_name`, `passport`, `phone`, `sur_name`) VALUES
(1, 'Микола', 'Олійник', 'RR 456890', '+380509876543', 'Миколайович'),
(2, 'Микола', 'Волошин  ', 'WW 765432', '+380776789567', 'Іванович'),
(3, 'Олександр', 'Кузьменко', 'UR 345678', '+380502345678', 'Миколайович'),
(4, 'Володимир', 'Мороз', 'RR 659076', '+380509876789', 'Іванович'),
(5, 'Василь', 'Попович', 'RR 456980', '+380689876754', 'Васильович'),
(6, 'Олександр', 'Руденко', 'YY 643689', '+380665438907', 'Іванович'),
(7, 'Іван', 'Карась', 'HH 456321', '+380673455521', 'Іванович'),
(8, 'Владислав', 'Коршун', 'RR 123876', '+380678945632', 'Станіславович');

-- --------------------------------------------------------

--
-- Структура таблицы `contracts_rent`
--

CREATE TABLE `contracts_rent` (
  `id` bigint(20) NOT NULL,
  `commission` double NOT NULL,
  `complete_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `clients_id` bigint(20) DEFAULT NULL,
  `flats_id` bigint(20) DEFAULT NULL,
  `realtors_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `contracts_rent`
--

INSERT INTO `contracts_rent` (`id`, `commission`, `complete_date`, `create_date`, `total_price`, `clients_id`, `flats_id`, `realtors_id`) VALUES
(2, 50, '2017-06-08', '2017-06-07', 1500, 5, 4, 2),
(3, 50, '2017-06-07', '2017-06-07', 650, 4, 5, 2),
(6, 50, NULL, '2017-06-12', 1050, 8, 40, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `contracts_sale`
--

CREATE TABLE `contracts_sale` (
  `id` bigint(20) NOT NULL,
  `commission` double NOT NULL,
  `complete_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `clients_id` bigint(20) DEFAULT NULL,
  `flats_id` bigint(20) DEFAULT NULL,
  `realtors_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `contracts_sale`
--

INSERT INTO `contracts_sale` (`id`, `commission`, `complete_date`, `create_date`, `total_price`, `clients_id`, `flats_id`, `realtors_id`) VALUES
(1, 4, '2017-06-07', '2017-06-07', 10000, 1, 1, 2),
(2, 4, '2017-06-07', '2017-06-07', 14000, 2, 2, 2),
(3, 4, '2017-06-07', '2017-06-07', 22200, 6, 6, 3),
(4, 4, '2017-06-07', '2017-06-07', 10800, 7, 11, 2),
(5, 4, '2017-06-15', '2017-06-15', 6020, 8, 7, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `flats`
--

CREATE TABLE `flats` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `area` double NOT NULL,
  `available` bit(1) NOT NULL,
  `district` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `floor` int(11) NOT NULL,
  `price` double NOT NULL,
  `reserved` bit(1) NOT NULL,
  `rooms` int(11) NOT NULL,
  `type_trade` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `flats`
--

INSERT INTO `flats` (`id`, `address`, `area`, `available`, `district`, `floor`, `price`, `reserved`, `rooms`, `type_trade`, `owner_id`) VALUES
(1, 'вул. Леніна,45 кв.75', 35, b'0', 'Центр', 3, 250000, b'0', 1, 'SALE', 1),
(2, 'вул. Зелена,2 кв. 54', 60, b'0', 'Хімселище', 4, 350000, b'0', 2, 'SALE', 2),
(3, 'вул. Конева,42 кв.77', 42, b'1', 'ПЗР', 3, 1700, b'0', 1, 'RENT', 3),
(4, 'вул. Шкільна,75 кв.9', 55, b'0', 'Луначарка', 2, 3000, b'0', 2, 'RENT', 4),
(5, 'вул. Гоголя.5 кв. 12', 45, b'0', 'Хімселище', 2, 1300, b'0', 1, 'RENT', 5),
(6, 'вул. Героїв Дніпра,86 кв.45', 100, b'0', 'Митниця', 7, 555000, b'0', 3, 'SALE', 6),
(7, 'вул. Зелена,5 кв.14', 36, b'0', 'Луначарка', 3, 150500, b'0', 1, 'SALE', 7),
(8, 'вул. Героїв Дніпра,54 кв.72', 55, b'1', 'Митниця', 2, 250200, b'0', 1, 'SALE', 8),
(9, 'вул. Конева,22 кв.17', 65, b'1', 'ПЗР', 4, 300100, b'0', 1, 'SALE', 9),
(10, 'вул. Гоголя.15 кв. 42', 45, b'1', 'Хімселище', 3, 158000, b'0', 1, 'SALE', 10),
(11, 'вул. Дашковича,64 кв.19', 45, b'0', 'Центр', 5, 270000, b'0', 1, 'SALE', 11),
(12, 'вул. Шкільна,45 кв.29', 57, b'1', 'Луначарка', 4, 220100, b'0', 2, 'SALE', 12),
(13, 'вул. Героїв Дніпра,40 кв.55', 60, b'1', 'Митниця', 8, 270000, b'0', 2, 'SALE', 13),
(14, 'вул. Конева,40 кв.37', 58, b'1', 'ПЗР', 6, 280000, b'0', 2, 'SALE', 14),
(15, 'вул. Зелена,42 кв. 54', 62, b'1', 'Хімселище', 3, 270000, b'0', 2, 'SALE', 15),
(16, 'вул. Шевченко,75 кв.77', 60, b'1', 'Центр', 3, 310000, b'0', 2, 'SALE', 16),
(17, 'вул. Шкільна,95 кв.39', 75, b'1', 'Луначарка', 2, 340000, b'0', 3, 'SALE', 17),
(18, 'вул. Героїв Дніпра,70 кв.25', 77, b'1', 'Митниця', 4, 410000, b'0', 3, 'SALE', 18),
(19, 'вул. Конева,72 кв.17', 72, b'1', 'ПЗР', 6, 340100, b'0', 3, 'SALE', 19),
(20, 'вул. Шкільна,95 кв.33', 75, b'1', 'Хімселище', 9, 208000, b'0', 3, 'SALE', 20),
(21, 'вул. Шевченко,105 кв.87', 120, b'1', 'Центр', 3, 450000, b'0', 3, 'SALE', 21),
(22, 'вул. Шкільна,5 кв.9', 110, b'1', 'Луначарка', 5, 340000, b'0', 4, 'SALE', 22),
(23, 'вул. Героїв Дніпра,114 кв.107', 125, b'1', 'Митниця', 10, 420000, b'0', 4, 'SALE', 23),
(24, 'вул. Конева,62 кв.97', 115, b'1', 'ПЗР', 6, 430000, b'0', 4, 'SALE', 24),
(25, 'вул. Шкільна,115 кв.59', 120, b'1', 'Хімселище', 3, 440000, b'0', 5, 'SALE', 25),
(26, 'вул. Дашковича,44 кв.54', 130, b'1', 'Центр', 2, 500200, b'0', 5, 'SALE', 26),
(27, 'вул. Шкільна,7 кв.39', 32, b'1', 'Луначарка', 4, 1200, b'0', 1, 'RENT', 27),
(28, 'вул. Героїв Дніпра,4 кв.72', 42, b'1', 'Митниця', 5, 1800, b'0', 1, 'RENT', 28),
(29, 'вул. Конева,32 кв.17', 62, b'1', 'ПЗР', 7, 2500, b'0', 2, 'RENT', 29),
(30, 'вул. Гоголя.25 кв. 12', 55, b'1', 'Хімселище', 3, 1700, b'0', 2, 'RENT', 30),
(31, 'вул. Шевченко,25 кв.7', 74, b'1', 'Центр', 2, 3500, b'0', 3, 'RENT', 1),
(32, 'вул. Зелена,75 кв.74', 95, b'1', 'Луначарка', 4, 2800, b'0', 3, 'RENT', 2),
(33, 'вул. Героїв Дніпра,114 кв.72', 32, b'1', 'Митниця', 5, 2500, b'0', 1, 'RENT', 3),
(34, 'вул. Конева,82 кв.87', 40, b'1', 'ПЗР', 6, 2000, b'0', 1, 'RENT', 4),
(35, 'вул. Шкільна,35 кв.63', 54, b'1', 'Хімселище', 3, 1400, b'0', 2, 'RENT', 5),
(36, 'вул. Дашковича,4 кв.19', 90, b'1', 'Центр', 5, 2800, b'0', 2, 'RENT', 6),
(37, 'вул. Героїв Дніпра,70 кв.85', 114, b'1', 'Митниця', 9, 3500, b'0', 3, 'RENT', 7),
(38, 'вул. Конева,32 кв.97', 90, b'1', 'ПЗР', 8, 3000, b'0', 3, 'RENT', 8),
(39, 'вул. Дашковича,64 кв.29', 42, b'1', 'Центр', 5, 1800, b'0', 1, 'RENT', 9),
(40, 'вул. Шкільна,85 кв.39', 62, b'1', 'Луначарка', 6, 2100, b'1', 2, 'RENT', 10),
(41, 'вул. Конева, 14 кв. 42', 75, b'1', 'ПЗР', 6, 3600, b'0', 3, 'RENT', 11),
(42, 'вул. Шевченко 34, кв.70', 80, b'1', 'Центр', 7, 320000, b'0', 2, 'SALE', 27),
(43, 'вул. Cмілянська, 75 кв.45', 85, b'1', 'Центр', 4, 310000, b'0', 2, 'SALE', 28);

-- --------------------------------------------------------

--
-- Структура таблицы `flat_image`
--

CREATE TABLE `flat_image` (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `flat_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `flat_image`
--

INSERT INTO `flat_image` (`id`, `url`, `flat_id`) VALUES
(1, '06.jpg', 1),
(2, '07.jpg', 1),
(4, '007.jpg', 1),
(5, '011.jpg', 2),
(6, '012.jpg', 2),
(7, '014.jpg', 2),
(8, '005.jpg', 2),
(9, '08.jpg', 3),
(11, '001.jpg', 3),
(13, '042.jpg', 4),
(14, '043.jpg', 4),
(15, '004.jpg', 4),
(16, '051.jpg', 5),
(17, '015.jpg', 5),
(18, '014.jpg', 6),
(19, '015.jpg', 6),
(20, '017.jpg', 6),
(21, '005.jpg', 6),
(22, '05.jpg', 7),
(28, '001.jpg', 7),
(33, '018.jpg', 8),
(34, '007.jpg', 8),
(38, '018.jpg', 9),
(39, '020.jpg', 9),
(40, '022.jpg', 10),
(41, '020.jpg', 10),
(45, '029.jpg', 11),
(46, '039.jpg', 11),
(47, '094.jpg', 12),
(48, '095.jpg', 12),
(50, '005.jpg', 12),
(51, '086.jpg', 13),
(52, '087.jpg', 13),
(53, '022.jpg', 13),
(58, '027.jpg', 14),
(59, '031.jpg', 14),
(60, '021.jpg', 14),
(61, '046.jpg', 15),
(62, '048.jpg', 15),
(64, '029.jpg', 15),
(69, '009.jpg', 17),
(70, '010.jpg', 17),
(71, '011.jpg', 17),
(72, '008.jpg', 17),
(76, '043.jpg', 18),
(79, '045.jpg', 18),
(80, '02.jpg', 18),
(84, '008.jpg', 18),
(95, '050.jpg', 20),
(96, '051.jpg', 20),
(97, '053.jpg', 20),
(98, '024.jpg', 20),
(106, '069.jpg', 21),
(111, '012.jpg', 21),
(112, '014.jpg', 21),
(113, '013.jpg', 21),
(114, '075.jpg', 19),
(123, '06.jpg', 19),
(124, '07.jpg', 19),
(128, '006.jpg', 19),
(135, '06.jpg', 22),
(137, '018.jpg', 22),
(138, '03.jpg', 22),
(139, '007.jpg', 22),
(140, '032.jpg', 23),
(141, '036.jpg', 23),
(142, '040.jpg', 23),
(145, '015.jpg', 23),
(154, '03.jpg', 24),
(156, '07.jpg', 24),
(157, '010.jpg', 24),
(158, '007.jpg', 24),
(159, '042.jpg', 25),
(160, '045.jpg', 25),
(161, '056.jpg', 25),
(162, '006.jpg', 25),
(163, '086.jpg', 26),
(164, '087.jpg', 26),
(165, '091.jpg', 26),
(166, '004.jpg', 26),
(167, '045.jpg', 27),
(168, '002.jpg', 27),
(172, '035.jpg', 28),
(174, '013.jpg', 28),
(175, '014.jpg', 29),
(178, '044.jpg', 29),
(194, '01.jpg', 30),
(195, '003.jpg', 30),
(196, '086.jpg', 31),
(197, '087.jpg', 31),
(198, '091.jpg', 31),
(199, '004.jpg', 31),
(200, '042.jpg', 32),
(201, '045.jpg', 32),
(202, '039.jpg', 32),
(203, '002.jpg', 32),
(204, '090.jpg', 33),
(205, '032.jpg', 33),
(206, '097.jpg', 34),
(208, '022.jpg', 34),
(209, '083.jpg', 35),
(210, '084.jpg', 35),
(211, '007.jpg', 35),
(218, '005.jpg', 36),
(219, '062.jpg', 36),
(220, '071.jpg', 36),
(221, '046.jpg', 37),
(222, '048.jpg', 37),
(223, '050.jpg', 37),
(224, '026.jpg', 37),
(225, '039.jpg', 38),
(226, '041.jpg', 38),
(227, '042.jpg', 38),
(230, '008.jpg', 38),
(231, '042.jpg', 39),
(232, '002.jpg', 39),
(233, '083.jpg', 40),
(234, '084.jpg', 40),
(235, '007.jpg', 40),
(236, '01.jpg', 41),
(237, '02.jpg', 41),
(238, '03.jpg', 41),
(239, '04.jpg', 41),
(240, '048.jpg', 42),
(241, '050.jpg', 42),
(242, '049.jpg', 42),
(243, '046.jpg', 42),
(244, '033.jpg', 43),
(245, '034.jpg', 43),
(246, '035.jpg', 43),
(247, '036.jpg', 43),
(252, '012.jpg', 16),
(253, '013.jpg', 16),
(254, '014.jpg', 16),
(255, '015.jpg', 16);

-- --------------------------------------------------------

--
-- Структура таблицы `owners`
--

CREATE TABLE `owners` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `passport` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sur_name` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `owners`
--

INSERT INTO `owners` (`id`, `first_name`, `last_name`, `passport`, `phone`, `sur_name`) VALUES
(1, 'Олександр', 'Ковальчук', 'RR 145876', '+380506573432', 'Васильович'),
(2, 'Микола', 'Ткачук', 'RY 675487', '+380674359876', 'Іванович'),
(3, 'Сергій', 'Бойко', 'YY 456760', '+380675437654', 'Миколайович'),
(4, 'Анатолій', 'Мельник', 'YY 987346', '+380670876543', 'Васильович'),
(5, 'Микола', 'Лисенко', 'RR 348905', '+380661236785', 'Миколайович'),
(6, 'Микола', 'Марченко', 'RT 765321', '+380675457896', 'Миколайович'),
(7, 'Микола', 'Бондаренко', 'YY 777444', '+380639876562', 'Петрович'),
(8, 'Юрій', 'Кравченко', 'RT 961756', '+380509871235', 'Миколайович'),
(9, 'Олександр', 'Хоменко', 'YR 754091', '+380634567841', 'Миколайович'),
(10, 'Анатолій', 'Шевченко', 'RR 324789', '+380674567788', 'Григорович'),
(11, 'Петро', 'Шевчук', 'RY 765342', '+380957655477', 'Васильович'),
(12, 'Володимир', 'Гончаренко', 'RR 789032', '+380507890234', 'Миколайович'),
(13, 'Богдан', 'Демченко', 'HH 436765', '+380634567754', 'Васильович'),
(14, 'Сергій', 'Павленко', 'HH 345123', '+380735678899', 'Миколайович'),
(15, 'Микола', 'Панченко', 'HG 776554', '+380635554433', 'Іванович'),
(16, 'Надія', 'Радченко', 'HH 432567', '+380674556788', 'Іванівна'),
(17, 'Ганна', 'Романенко', 'RR 689031', '+380507890506', 'Миколаївна'),
(18, 'Лідія', 'Сергієнко', 'RR 123098', '+380737658901', 'Володимирівна'),
(19, 'Валентина', 'Тарасенко', 'RY 853321', '+380731237281', 'Федорівна'),
(20, 'Тетяна', 'Ткачук', 'HH 443442', '+380958978854', 'Анатоліївна'),
(21, 'Галина', 'Харченко', 'RR 732541', '+380737890201', 'Григорівна'),
(22, 'Світлана', 'Юрченко', 'HH 567453', '+380638765577', 'Миколаївна'),
(23, 'Марія', 'Білоус', 'HG 678901', '+380668978855', 'Петрівна'),
(24, 'Наталія', 'Василенко', 'RR 253435', '+380978767753', 'Іванівна'),
(25, 'Валентина', 'Дорошенко', 'HH 761234', '+380976785541', 'Іванівна'),
(26, 'Валентина', 'Іващенко', 'RR 978432', '+380958907754', 'Іванівна'),
(27, 'Галина', 'Литвиненко', 'HR 571439', '+380507892132', 'Олександрівна'),
(28, 'Віктор', 'Сорока', 'RR 674531', '+380509876543', 'Миколайович'),
(29, 'Віктор', 'Авраменко', 'HH 712345', '+380637657744', 'Іванович'),
(30, 'Василь', 'Біленко', 'RR 790478', '+380976789822', 'Іванович');

-- --------------------------------------------------------

--
-- Структура таблицы `realtors`
--

CREATE TABLE `realtors` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `realtors`
--

INSERT INTO `realtors` (`id`, `full_name`, `phone`) VALUES
(1, 'Воскубенко Владислав Станіславович', '+380685645164'),
(2, 'Бондар Володимир Іванович', '+380674534332'),
(3, ' Коваль Володимир Васильович', '+380507896543');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `realtor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `enabled`, `password`, `username`, `realtor_id`) VALUES
(1, b'1', '$2a$10$8IcjmWTX4xKr9VGS2mZFMeumZHmxRyHUiwfu0oUrq8uySxikHodge', 'admin', NULL),
(2, b'1', '$2a$10$Sohi5Xsza6enOiyhIubKceiivUiH1.ztgXUg5ujK94QEcS9OiZzjS', 'vlad', 1),
(3, b'1', '$2a$10$GB4uCVdeq3j2uGjMV4pdvONVjad7FELuRRCTEeoPsDzkETMulHy3G', 'realtor_01', 2),
(4, b'1', '$2a$10$enNiV/UwID3eT0SCHuOPhunPWmkLQboAUSw90IN5mm75Fp2LzGyTC', 'realtor_02', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `user_authorities`
--

CREATE TABLE `user_authorities` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `user_authorities`
--

INSERT INTO `user_authorities` (`id`, `authority`, `user_id`) VALUES
(1, 'ADMIN', 1),
(2, 'ADMIN', 2),
(3, 'USER', 3),
(4, 'USER', 4);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `contracts_rent`
--
ALTER TABLE `contracts_rent`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK42jxsh2fgetdlqfeh7mv87a9i` (`flats_id`),
  ADD KEY `FKcn8stdeyskqtlpgl0p5k2hgk4` (`clients_id`),
  ADD KEY `FK4so708iilfh5qbhoskr4tr2l` (`realtors_id`);

--
-- Индексы таблицы `contracts_sale`
--
ALTER TABLE `contracts_sale`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK7xkwb78gt0he9pj7oqdpc8044` (`flats_id`),
  ADD KEY `FKqvhw030t8a2a3m5k9xpstloe4` (`clients_id`),
  ADD KEY `FK68abxkx8mk1p4ultai9qkcgql` (`realtors_id`);

--
-- Индексы таблицы `flats`
--
ALTER TABLE `flats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfkps9gdea8pblt2ex2fwtipbs` (`owner_id`);

--
-- Индексы таблицы `flat_image`
--
ALTER TABLE `flat_image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5elt99l4ao691d2ld22y6jak4` (`flat_id`);

--
-- Индексы таблицы `owners`
--
ALTER TABLE `owners`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `realtors`
--
ALTER TABLE `realtors`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqsj9pkv3d4r8xvghjqk1v5sn8` (`realtor_id`);

--
-- Индексы таблицы `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhiiib540jf74gksgb87oofni` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `clients`
--
ALTER TABLE `clients`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT для таблицы `contracts_rent`
--
ALTER TABLE `contracts_rent`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `contracts_sale`
--
ALTER TABLE `contracts_sale`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `flats`
--
ALTER TABLE `flats`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT для таблицы `flat_image`
--
ALTER TABLE `flat_image`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;
--
-- AUTO_INCREMENT для таблицы `owners`
--
ALTER TABLE `owners`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT для таблицы `realtors`
--
ALTER TABLE `realtors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `user_authorities`
--
ALTER TABLE `user_authorities`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `contracts_rent`
--
ALTER TABLE `contracts_rent`
  ADD CONSTRAINT `FK261wttwrs6lfekfoa0uwqljiv` FOREIGN KEY (`flats_id`) REFERENCES `flats` (`id`),
  ADD CONSTRAINT `FK4so708iilfh5qbhoskr4tr2l` FOREIGN KEY (`realtors_id`) REFERENCES `realtors` (`id`),
  ADD CONSTRAINT `FKcn8stdeyskqtlpgl0p5k2hgk4` FOREIGN KEY (`clients_id`) REFERENCES `clients` (`id`);

--
-- Ограничения внешнего ключа таблицы `contracts_sale`
--
ALTER TABLE `contracts_sale`
  ADD CONSTRAINT `FK50ojixteoh9ofh72eaqrwc7v9` FOREIGN KEY (`flats_id`) REFERENCES `flats` (`id`),
  ADD CONSTRAINT `FK68abxkx8mk1p4ultai9qkcgql` FOREIGN KEY (`realtors_id`) REFERENCES `realtors` (`id`),
  ADD CONSTRAINT `FKqvhw030t8a2a3m5k9xpstloe4` FOREIGN KEY (`clients_id`) REFERENCES `clients` (`id`);

--
-- Ограничения внешнего ключа таблицы `flats`
--
ALTER TABLE `flats`
  ADD CONSTRAINT `FKfkps9gdea8pblt2ex2fwtipbs` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`);

--
-- Ограничения внешнего ключа таблицы `flat_image`
--
ALTER TABLE `flat_image`
  ADD CONSTRAINT `FK5elt99l4ao691d2ld22y6jak4` FOREIGN KEY (`flat_id`) REFERENCES `flats` (`id`);

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKqsj9pkv3d4r8xvghjqk1v5sn8` FOREIGN KEY (`realtor_id`) REFERENCES `realtors` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD CONSTRAINT `FKhiiib540jf74gksgb87oofni` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
