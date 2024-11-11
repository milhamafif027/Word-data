-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2024 at 05:22 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_serverapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_country`
--

CREATE TABLE `tb_country` (
  `id` int(11) NOT NULL,
  `code` varchar(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  `region_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_country`
--

INSERT INTO `tb_country` (`id`, `code`, `name`, `region_id`) VALUES
(1, 'IN', 'Indonesia', 1),
(2, 'PH', 'Philipina', 1),
(4, 'MY', 'Malaysia', 1),
(5, 'SG', 'Singapore', 1),
(6, 'TH', 'Thailand', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_employee`
--

CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_employee`
--

INSERT INTO `tb_employee` (`id`, `email`, `name`, `phone`) VALUES
(1, 'doni@email.com', 'Doni', '081111111111'),
(2, 'sintya@email.com', 'Sintya', '082222222222'),
(4, 'irfan@email.com', 'Irfan', '081111222333'),
(5, 'agus@email.com', 'Agus', '081111222334'),
(6, 'Rani@email.com', 'Rani', '081111222344'),
(7, 'vino@email.com', 'Vino', '081111222355'),
(8, 'dean@email.com', 'Dean', '081111333355'),
(9, 'ezra@email.com', 'Ezra', '081111334466');

-- --------------------------------------------------------

--
-- Table structure for table `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_privilege`
--

INSERT INTO `tb_privilege` (`id`, `name`) VALUES
(5, 'create_admin'),
(1, 'create_user'),
(8, 'delete_admin'),
(4, 'delete_user'),
(6, 'read_admin'),
(2, 'read_user'),
(7, 'update_admin'),
(3, 'update_user');

-- --------------------------------------------------------

--
-- Table structure for table `tb_region`
--

CREATE TABLE `tb_region` (
  `region_id` int(11) NOT NULL,
  `region_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_region`
--

INSERT INTO `tb_region` (`region_id`, `region_name`) VALUES
(6, 'Africa'),
(2, 'Amerika'),
(1, 'Asia');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(3, 'admin'),
(1, 'user');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role_privilege`
--

CREATE TABLE `tb_role_privilege` (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_role_privilege`
--

INSERT INTO `tb_role_privilege` (`role_id`, `privilege_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `password`, `username`) VALUES
(5, '$2a$12$bhT2zqIPdd9OUHzsqO0XZOXuSBTlLfucwzLzakVVEXuXHwLgz/nBW', 'agus'),
(6, 'rani', 'rani'),
(7, '$2a$12$jT2SPuJHrr.0Rad53VSfg.fRGXfiidj48IuZEDurMc7vwWDC9p0QK', 'vino'),
(8, 'dean', 'dean'),
(9, '$2a$10$arSdq7fJ9Iv6TW6OkNthOOPdg311HhRngSeYa5CW0hlpJ95d6wGy2', 'ezra');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user_role`
--

CREATE TABLE `tb_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user_role`
--

INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES
(5, 3),
(6, 1),
(7, 1),
(8, 1),
(9, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_country`
--
ALTER TABLE `tb_country`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK2vjdpyjwg61lwl22lj4jh13r2` (`code`),
  ADD UNIQUE KEY `UK7id1m3ximdi1khqqqgewem6m` (`name`),
  ADD KEY `FKow3r4mvn453tgoqu3em28ilu5` (`region_id`);

--
-- Indexes for table `tb_employee`
--
ALTER TABLE `tb_employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKem9lvg7whqbrro5fbav5bt0gy` (`email`);

--
-- Indexes for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKt4vvgri0dgkkyq3uu04gfa3hu` (`name`);

--
-- Indexes for table `tb_region`
--
ALTER TABLE `tb_region`
  ADD PRIMARY KEY (`region_id`),
  ADD UNIQUE KEY `UKeiddog20nt66vhbfirjfky452` (`region_name`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1ncmoedv5ta7r19y9d4oidn0y` (`name`);

--
-- Indexes for table `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD KEY `FK6gvpbopw3c17inmv17usj8kes` (`privilege_id`),
  ADD KEY `FKsncdml4xq9xf0opvvrxcycq7y` (`role_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK4wv83hfajry5tdoamn8wsqa6x` (`username`);

--
-- Indexes for table `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  ADD KEY `FK7vn3h53d0tqdimm8cp45gc0kl` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_country`
--
ALTER TABLE `tb_country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_employee`
--
ALTER TABLE `tb_employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_region`
--
ALTER TABLE `tb_region`
  MODIFY `region_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_country`
--
ALTER TABLE `tb_country`
  ADD CONSTRAINT `FKow3r4mvn453tgoqu3em28ilu5` FOREIGN KEY (`region_id`) REFERENCES `tb_region` (`region_id`);

--
-- Constraints for table `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD CONSTRAINT `FK6gvpbopw3c17inmv17usj8kes` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`),
  ADD CONSTRAINT `FKsncdml4xq9xf0opvvrxcycq7y` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `FKeevlntsedmt1rdsr2d2lilphg` FOREIGN KEY (`id`) REFERENCES `tb_employee` (`id`);

--
-- Constraints for table `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
