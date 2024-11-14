-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 14 Nov 2024 pada 13.56
-- Versi server: 8.0.30
-- Versi PHP: 8.1.10

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
-- Struktur dari tabel `country`
--

CREATE TABLE `country` (
  `id` int NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `region_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `privilege`
--

CREATE TABLE `privilege` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `region`
--

CREATE TABLE `region` (
  `id` int NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `role`
--

CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `role_privilege`
--

CREATE TABLE `role_privilege` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_country`
--

CREATE TABLE `tb_country` (
  `id` int NOT NULL,
  `code` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `region_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_country`
--

INSERT INTO `tb_country` (`id`, `code`, `name`, `region_id`) VALUES
(1, 'ID', 'Indonesia', 1),
(2, 'PH', 'Philipina', 1),
(4, 'MY', 'Malaysia', 1),
(5, 'SG', 'Singapore', 1),
(6, 'TH', 'Thailand', 1),
(14, 'CU', 'Cuba', 2),
(15, 'BG', 'Belgia', 7),
(16, 'AT', 'Austria', 7),
(17, 'AG', 'Argentina', 2),
(18, 'FR', 'France', 7),
(19, 'ND', 'Netherland', 7),
(20, 'DZ', 'Algeria', 6),
(21, 'EG', 'Egypt', 6),
(22, 'GH', 'Ghana', 6),
(23, 'KE', 'Kenya', 6),
(24, 'IQ', 'Iraq', 1),
(25, 'KR', 'South Korea', 1),
(27, 'HT', 'Haiti', 2),
(28, 'FJ', 'Fiji', 8),
(29, 'AU', 'Australia', 8),
(30, 'HR', 'Croatia', 7),
(31, 'NZ', 'New Zealand', 8),
(32, 'VU', 'Vanuatu', 8),
(33, 'CH', 'Switzerland', 7);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_employee`
--

CREATE TABLE `tb_employee` (
  `id` int NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_employee`
--

INSERT INTO `tb_employee` (`id`, `email`, `name`, `phone`) VALUES
(1, 'doni@email.com', 'Doni', '081111111111'),
(2, 'sintya@email.com', 'Sintya', '082222222222'),
(4, 'irfan@email.com', 'Irfan', '081111222333'),
(5, 'agus@email.com', 'Agus', '081111222334'),
(6, 'Rani@email.com', 'Rani', '081111222344'),
(7, 'vino@email.com', 'Vino', '081111222355'),
(8, 'dean@email.com', 'Dean', '081111333355'),
(9, 'ezra@email.com', 'Ezra', '081111334466'),
(10, 'ana@yopmail.com', 'ana', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_privilege`
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
-- Struktur dari tabel `tb_region`
--

CREATE TABLE `tb_region` (
  `region_id` int NOT NULL,
  `region_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_region`
--

INSERT INTO `tb_region` (`region_id`, `region_name`) VALUES
(6, 'Africa'),
(2, 'Amerika'),
(1, 'Asia'),
(7, 'Eropa'),
(8, 'Oceania');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(3, 'admin'),
(1, 'user');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_role_privilege`
--

CREATE TABLE `tb_role_privilege` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_role_privilege`
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
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id`, `password`, `username`) VALUES
(5, '$2a$12$bhT2zqIPdd9OUHzsqO0XZOXuSBTlLfucwzLzakVVEXuXHwLgz/nBW', 'agus'),
(6, 'rani', 'rani'),
(7, '$2a$12$jT2SPuJHrr.0Rad53VSfg.fRGXfiidj48IuZEDurMc7vwWDC9p0QK', 'vino'),
(8, 'dean', 'dean'),
(9, '$2a$10$arSdq7fJ9Iv6TW6OkNthOOPdg311HhRngSeYa5CW0hlpJ95d6wGy2', 'ezra'),
(10, '$2a$10$35S4oQZgCUzT0oGjIGzcee.lj2e0PpKZVT7c/7AomzoFZGR9IgmZO', 'ana');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user_role`
--

CREATE TABLE `tb_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_user_role`
--

INSERT INTO `tb_user_role` (`user_id`, `role_id`) VALUES
(5, 3),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK5s4ptnuqtd24d4p9au2rv53qm` (`code`),
  ADD KEY `FKs3bda8801uhqtttuaur9r6eic` (`region_id`);

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKfopic1oh5oln2khj8eat6ino0` (`email`);

--
-- Indeks untuk tabel `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKh7iwbdg4ev8mgvmij76881tx8` (`name`);

--
-- Indeks untuk tabel `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKixr2itih2n9q41fv3qx6mbkrp` (`name`);

--
-- Indeks untuk tabel `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`);

--
-- Indeks untuk tabel `role_privilege`
--
ALTER TABLE `role_privilege`
  ADD KEY `FKdkwbrwb5r8h74m1v7dqmhp99c` (`privilege_id`),
  ADD KEY `FKsykrtrdngu5iexmbti7lu9xa` (`role_id`);

--
-- Indeks untuk tabel `tb_country`
--
ALTER TABLE `tb_country`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK2vjdpyjwg61lwl22lj4jh13r2` (`code`),
  ADD UNIQUE KEY `UK7id1m3ximdi1khqqqgewem6m` (`name`),
  ADD KEY `FKow3r4mvn453tgoqu3em28ilu5` (`region_id`);

--
-- Indeks untuk tabel `tb_employee`
--
ALTER TABLE `tb_employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKem9lvg7whqbrro5fbav5bt0gy` (`email`);

--
-- Indeks untuk tabel `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKt4vvgri0dgkkyq3uu04gfa3hu` (`name`);

--
-- Indeks untuk tabel `tb_region`
--
ALTER TABLE `tb_region`
  ADD PRIMARY KEY (`region_id`),
  ADD UNIQUE KEY `UKeiddog20nt66vhbfirjfky452` (`region_name`);

--
-- Indeks untuk tabel `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1ncmoedv5ta7r19y9d4oidn0y` (`name`);

--
-- Indeks untuk tabel `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD KEY `FK6gvpbopw3c17inmv17usj8kes` (`privilege_id`),
  ADD KEY `FKsncdml4xq9xf0opvvrxcycq7y` (`role_id`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK4wv83hfajry5tdoamn8wsqa6x` (`username`);

--
-- Indeks untuk tabel `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  ADD KEY `FK7vn3h53d0tqdimm8cp45gc0kl` (`user_id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indeks untuk tabel `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  ADD KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `country`
--
ALTER TABLE `country`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `privilege`
--
ALTER TABLE `privilege`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `region`
--
ALTER TABLE `region`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `role`
--
ALTER TABLE `role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tb_country`
--
ALTER TABLE `tb_country`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT untuk tabel `tb_employee`
--
ALTER TABLE `tb_employee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `tb_region`
--
ALTER TABLE `tb_region`
  MODIFY `region_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `country`
--
ALTER TABLE `country`
  ADD CONSTRAINT `FKs3bda8801uhqtttuaur9r6eic` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);

--
-- Ketidakleluasaan untuk tabel `role_privilege`
--
ALTER TABLE `role_privilege`
  ADD CONSTRAINT `FKdkwbrwb5r8h74m1v7dqmhp99c` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  ADD CONSTRAINT `FKsykrtrdngu5iexmbti7lu9xa` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_country`
--
ALTER TABLE `tb_country`
  ADD CONSTRAINT `FKow3r4mvn453tgoqu3em28ilu5` FOREIGN KEY (`region_id`) REFERENCES `tb_region` (`region_id`);

--
-- Ketidakleluasaan untuk tabel `tb_role_privilege`
--
ALTER TABLE `tb_role_privilege`
  ADD CONSTRAINT `FK6gvpbopw3c17inmv17usj8kes` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`),
  ADD CONSTRAINT `FKsncdml4xq9xf0opvvrxcycq7y` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `FKeevlntsedmt1rdsr2d2lilphg` FOREIGN KEY (`id`) REFERENCES `tb_employee` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_user_role`
--
ALTER TABLE `tb_user_role`
  ADD CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Ketidakleluasaan untuk tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKss65n39o4cvrrlryp5lmv19n0` FOREIGN KEY (`id`) REFERENCES `employee` (`id`);

--
-- Ketidakleluasaan untuk tabel `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
