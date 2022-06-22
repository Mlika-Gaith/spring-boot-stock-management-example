-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2022 at 10:37 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_produits`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `age`, `email`, `nom`, `prenom`) VALUES
(11, 24, 'gaith@email.com', 'Mlika', 'Ghaith'),
(4, 24, 'kelly@email.com', 'vedovelli', 'kelly'),
(12, 22, 'zied@email.com', 'mlika', 'Zied');

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id` bigint(20) NOT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `somme` double NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `produit_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facture`
--

INSERT INTO `facture` (`id`, `designation`, `somme`, `client_id`, `produit_id`) VALUES
(13, 'facture001', 2850, 11, 5),
(15, 'facture002', 850, 4, 6),
(19, 'facture004', 2850, 11, 5);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` bigint(20) NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `prix` double NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `designation`, `prix`, `quantite`) VALUES
(20, 'Imprimente HP', 700, 45),
(21, 'Asus Gaming Laptop New Generation', 5580, 30);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `login` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login`, `pass`, `active`) VALUES
('ghaith', '123', 1),
('zied', '123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_role`
--

CREATE TABLE `users_role` (
  `login` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_role`
--

INSERT INTO `users_role` (`login`, `role`) VALUES
('ghaith', 'ADMIN'),
('zied', 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkimq62662qs9wrpfvsw8mcnvf` (`client_id`),
  ADD KEY `FKtrlftomfa1vbdtxuajt441ah` (`produit_id`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3ddvfopmefpp0kv6vi5j6dnju` (`designation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
