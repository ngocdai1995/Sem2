-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 10, 2021 lúc 02:20 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `supermarket01`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `title`, `created_at`, `updated_at`) VALUES
(1, 'Hoa quả', '2021-11-21 00:00:00', '2021-11-21 00:00:00'),
(2, 'Kem', '2021-11-21 00:00:00', '2021-11-21 00:00:00'),
(5, 'Đồ uống', '2021-11-21 00:00:00', '2021-12-10 15:46:23');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `staff_id`, `created_at`, `updated_at`) VALUES
(45, 8, '2021-12-04 08:24:50', '2021-12-04 08:24:50'),
(46, 1, '2021-12-04 08:46:07', '2021-12-04 08:46:07'),
(47, 8, '2021-12-06 03:34:53', '2021-12-06 03:34:53'),
(48, 8, '2021-12-06 03:36:51', '2021-12-06 03:36:51'),
(49, 8, '2021-12-06 03:45:49', '2021-12-06 03:45:49'),
(50, 1, '2021-12-06 03:51:52', '2021-12-06 03:51:52'),
(51, 8, '2021-12-07 11:42:42', '2021-12-07 11:42:42'),
(52, 8, '2021-12-07 11:47:30', '2021-12-07 11:47:30'),
(53, 8, '2021-12-07 11:50:41', '2021-12-07 11:50:41'),
(54, 8, '2021-12-07 11:53:45', '2021-12-07 11:53:45'),
(55, 8, '2021-12-07 11:55:21', '2021-12-07 11:55:21'),
(56, 8, '2021-12-07 11:58:19', '2021-12-07 11:58:19'),
(57, 1, '2021-12-07 11:59:05', '2021-12-07 11:59:05'),
(58, 1, '2021-12-08 12:00:06', '2021-12-08 12:00:06'),
(59, 1, '2021-12-08 12:01:00', '2021-12-08 12:01:00'),
(60, 1, '2021-12-08 12:01:48', '2021-12-08 12:01:48'),
(61, 1, '2021-12-08 12:03:55', '2021-12-08 12:03:55'),
(62, 1, '2021-12-08 12:05:22', '2021-12-08 12:05:22'),
(63, 1, '2021-12-08 08:49:01', '2021-12-08 08:49:01'),
(64, 1, '2021-12-08 08:50:02', '2021-12-08 08:50:02'),
(65, 1, '2021-12-08 08:50:56', '2021-12-08 08:50:56'),
(66, 1, '2021-12-08 08:52:40', '2021-12-08 08:52:40'),
(67, 1, '2021-12-08 08:53:14', '2021-12-08 08:53:14'),
(68, 8, '2021-12-08 10:34:31', '2021-12-08 10:34:31'),
(69, 8, '2021-12-10 15:01:42', '2021-12-10 15:01:42');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `quantity`, `price`, `created_at`, `updated_at`) VALUES
(42, 45, 8, 10, 100000, '2021-12-04 08:24:50', '2021-12-04 08:24:50'),
(43, 46, 8, 10, 100000, '2021-12-04 08:46:07', '2021-12-04 08:46:07'),
(44, 47, 27, 10, 15000, '2021-12-06 03:34:53', '2021-12-06 03:34:53'),
(45, 48, 8, 100, 100000, '2021-12-06 03:36:51', '2021-12-06 03:36:51'),
(46, 49, 26, 50, 100000, '2021-12-06 03:45:49', '2021-12-06 03:45:49'),
(47, 50, 24, 30, 100000, '2021-12-06 03:51:52', '2021-12-06 03:51:52'),
(48, 51, 8, 10, 100000, '2021-12-07 11:42:42', '2021-12-07 11:42:42'),
(49, 53, 8, 10, 100000, '2021-12-07 11:50:41', '2021-12-07 11:50:41'),
(50, 54, 8, 10, 100000, '2021-12-07 11:53:45', '2021-12-07 11:53:45'),
(51, 55, 8, 10, 100000, '2021-12-07 11:55:21', '2021-12-07 11:55:21'),
(52, 56, 8, 10, 100000, '2021-12-07 11:58:19', '2021-12-07 11:58:19'),
(53, 57, 8, 10, 100000, '2021-12-07 11:59:05', '2021-12-07 11:59:05'),
(54, 58, 8, 100, 100000, '2021-12-08 12:00:06', '2021-12-08 12:00:06'),
(55, 59, 27, 10, 15000, '2021-12-08 12:01:00', '2021-12-08 12:01:00'),
(56, 59, 26, 10, 100000, '2021-12-08 12:01:00', '2021-12-08 12:01:00'),
(57, 60, 26, 20, 100000, '2021-12-08 12:01:48', '2021-12-08 12:01:48'),
(58, 60, 27, 20, 15000, '2021-12-08 12:01:48', '2021-12-08 12:01:48'),
(59, 61, 27, 10, 15000, '2021-12-08 12:03:55', '2021-12-08 12:03:55'),
(60, 61, 26, 10, 100000, '2021-12-08 12:03:55', '2021-12-08 12:03:55'),
(61, 62, 25, 50, 100000, '2021-12-08 12:05:22', '2021-12-08 12:05:22'),
(62, 63, 9, 10, 100000, '2021-12-08 08:49:01', '2021-12-08 08:49:01'),
(63, 63, 10, 10, 100000, '2021-12-08 08:49:01', '2021-12-08 08:49:01'),
(64, 64, 26, 10, 100000, '2021-12-08 08:50:02', '2021-12-08 08:50:02'),
(65, 64, 27, 10, 15000, '2021-12-08 08:50:02', '2021-12-08 08:50:02'),
(66, 65, 26, 20, 100000, '2021-12-08 08:50:56', '2021-12-08 08:50:56'),
(67, 65, 27, 20, 15000, '2021-12-08 08:50:56', '2021-12-08 08:50:56'),
(68, 66, 27, 50, 15000, '2021-12-08 08:52:41', '2021-12-08 08:52:41'),
(69, 67, 23, 50, 100000, '2021-12-08 08:53:14', '2021-12-08 08:53:14'),
(70, 67, 20, 50, 15000, '2021-12-08 08:53:14', '2021-12-08 08:53:14'),
(71, 68, 26, 20, 100000, '2021-12-08 10:34:31', '2021-12-08 10:34:31'),
(72, 69, 9, 10, 100000, '2021-12-10 15:01:42', '2021-12-10 15:01:42'),
(73, 69, 10, 10, 100000, '2021-12-10 15:01:42', '2021-12-10 15:01:42');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  `thumbnail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `thumbnail`, `description`, `quantity`, `created_at`, `updated_at`, `category_id`) VALUES
(8, 'mít thái', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\5555.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:44:04', 1),
(9, 'xoài', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 990, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(10, 'táo', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 990, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(11, 'Kem lê', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(20, 'Kem sầu riêng', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 1000, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(23, 'Hồng xiêm', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\5555.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:44:04', 1),
(24, 'Kiwi', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(25, 'Dưa hấu', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(26, 'Kem cacao', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(27, 'Kem ốc quế', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 500, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(31, 'Sầu riêng', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-09 11:02:15', 1),
(32, 'Dưa vàng', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\anh1112231.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-09 11:05:20', 1),
(33, 'Bơ', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-09 11:06:46', 1),
(34, 'Kem cốm', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(35, 'Kem đậu xanh', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 500, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(36, 'Ổi', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\5555.png', 'aaa', 500, '2021-11-22 00:00:00', '2021-12-04 10:44:04', 1),
(37, 'Vú sữa', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(38, 'Đu đủ', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(39, 'Kem sữa dừa', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 800, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(40, 'Kem cam', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 500, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(41, 'Thanh long', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\5555.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:44:04', 1),
(42, 'Bưởi', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(43, 'Măng cụt', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(44, 'Kem chanh', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 1000, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(45, 'Kem sô cô la', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 500, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(46, 'Dâu tây', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\5555.png', 'aaa', 50, '2021-11-22 00:00:00', '2021-12-04 10:44:04', 1),
(47, 'Nho', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 100, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(48, 'Lê', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'aaa', 100, '2021-11-22 00:00:00', '2021-11-22 00:00:00', 1),
(49, 'Kem dâu', 100000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\ảnh staffs.png', 'aaa', 80, '2021-11-22 00:00:00', '2021-12-04 10:42:52', 2),
(50, 'Kem gấu', 15000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\1234.png', 'kem ngon', 50, '2021-12-04 10:42:02', '2021-12-04 10:42:02', 2),
(51, 'Trà việt quất', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(52, 'Ca cao', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(53, 'Nâu đen', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(54, 'Nâu đá', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(55, 'Sinh tố bơ', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(56, 'Sinh tố mãng cầu', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(57, 'Nước cam', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(58, 'Bạc sỉu', 10000, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'đồ ngon', 1000, '2021-12-09 11:17:59', '2021-12-09 11:17:59', 5),
(60, 'aaa', 1, 'C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\add-128.png', 'a', 1, '2021-12-10 15:46:47', '2021-12-10 15:46:47', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'Staff'),
(2, 'Admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_day` datetime DEFAULT NULL,
  `gender` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `fullname`, `phone_number`, `address`, `email`, `password`, `birth_day`, `gender`, `created_at`, `updated_at`, `role_id`) VALUES
(1, 'Phạm Ngọc Đại', '0904550022', 'Định Công', 'ngocdai195@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2001-07-19 20:57:24', 'Nam', '2021-11-21 20:57:53', '2021-11-21 20:57:56', 2),
(8, 'Tran Van A', '1234567890', 'ha noi', 'vana@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2021-12-09 00:00:00', 'Nữ', '2021-11-23 12:28:00', '2021-12-09 11:18:18', 1),
(12, 'Ngoc A', '1234567890', '123456', 'a@gmail.com', '202cb962ac59075b964b07152d234b70', '2021-12-10 15:15:47', 'Female', '2021-12-10 15:15:47', '2021-12-10 15:15:47', 1),
(13, 'Thành Trung', '0123456789', 'Hà Nội', 'thanhtrung@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '2021-12-10 15:41:41', 'Nam', '2021-12-10 15:41:42', '2021-12-10 15:41:42', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
