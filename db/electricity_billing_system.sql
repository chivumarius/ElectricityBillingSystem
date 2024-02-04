-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 03, 2024 at 05:12 PM
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
-- Database: `electricity_billing_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `meter` varchar(20) DEFAULT NULL,
  `month` varchar(20) DEFAULT NULL,
  `units` varchar(20) DEFAULT NULL,
  `total_bill` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`meter`, `month`, `units`, `total_bill`, `status`) VALUES
('687285', 'January', '100', '1044', 'Paid'),
('784747', 'January', '125', '1269', 'Not Paid'),
('921338', 'January', '48', '576', 'Not Paid'),
('45507', 'January', '95', '999', 'Not Paid'),
('837623', 'January', '110', '1134', 'Not Paid'),
('451545', 'January', '86', '918', 'Not Paid'),
('564683', 'January', '75', '819', 'Not Paid'),
('187020', 'January', '62', '702', 'Not Paid');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `name` varchar(30) DEFAULT NULL,
  `meter` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `meter`, `address`, `city`, `state`, `email`, `phone`) VALUES
('Jeanine ', '687285', 'Str. Ion Mihalache, nr. 207, sector 1', 'Bucuresti', 'Bucuresti', 'jeanine@gmail.com', '+40700700700'),
('Mikolas', '784747', 'str, Izbor, nr. 35', 'Brasov', 'Brasov', 'nikolas@gmail.com', '+40711722733'),
('Ella', '921338', 'str. Primaveri, nr. 248', 'Cluj Napoca', 'Cluj', 'ella@gmail.com', '+40722722722'),
('Cristian', '45507', 'str. Iuliu Maniu, nr. 105', 'Iasi', 'Iasi', 'cristian@gmail.com', '+40766766766'),
('Andrei', '837623', 'str. Ion Mincu, nr. 10', 'Timisoara', 'Timișs', 'andrei@gmail.com', '+40755755755'),
('Marian', '451545', 'str. Taberei, nr. 20', 'Ploiesti', 'Prahova', 'marian@gmail.com', '+40799777222'),
('Roger', '564683', 'str. Victoriei, br. 108', 'Oradea', 'Bihor', 'roger@gmail.com', '+40722722722'),
('Alexandra', '187020', 'str. Stefan cel Mare, nr. 205', 'Bucuresti', 'Bucuresti', 'alexandra@gmail.com', '+40755755755');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `meter_no` varchar(20) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `user` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`meter_no`, `username`, `name`, `password`, `user`) VALUES
('', 'mariuschivu', 'Marius Chivu', '123456', 'Administrator'),
('', 'admin', 'Administrator', '123456', 'Admin'),
('687285', 'jeanine ', 'Jeanine ', '123456', 'Customer'),
('784747', 'nikolas', 'Nikolas', '123456', 'Customer'),
('921338', 'ella', 'Ella', '123456', 'Customer'),
('45507', 'cristian', 'Cristian', '123456', 'Customer'),
('837623', 'andrei', 'Andrei', '123456', 'Customer'),
('451545', 'marian', 'Marian', '123456', 'Customer'),
('564683', 'roger', 'Roger', '123456', 'Customer'),
('187020', 'alexandra', 'Alexandra', '123456', 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `meter_info`
--

CREATE TABLE `meter_info` (
  `meter_number` varchar(20) DEFAULT NULL,
  `meter_location` varchar(20) DEFAULT NULL,
  `meter_type` varchar(20) DEFAULT NULL,
  `phase_code` varchar(20) DEFAULT NULL,
  `bill_type` varchar(20) DEFAULT NULL,
  `days` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meter_info`
--

INSERT INTO `meter_info` (`meter_number`, `meter_location`, `meter_type`, `phase_code`, `bill_type`, `days`) VALUES
('687285', 'Outside', 'Electric Meter', '011', 'Normal', '30'),
('784747', 'Inside', 'Solar Meter', '022', 'Normal', '30'),
('921338', 'Inside', 'Smart Meter', '033', 'Industrial', '30'),
('45507', 'Outside', 'Electric Meter', '044', 'Normal', '30'),
('837623', 'Outside', 'Electric Meter', '055', 'Normal', '30'),
('451545', 'Outside', 'Smart Meter', '011', 'Normal', '30'),
('564683', 'Inside', 'Electric Meter', '077', 'Industrial', '30'),
('187020', 'Inside', 'Solar Meter', '011', 'Normal', '30');

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE `tax` (
  `cost_per_unit` varchar(20) DEFAULT NULL,
  `meter_rent` varchar(20) DEFAULT NULL,
  `service_charge` varchar(20) DEFAULT NULL,
  `service_tax` varchar(20) DEFAULT NULL,
  `fixed_tax` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tax`
--

INSERT INTO `tax` (`cost_per_unit`, `meter_rent`, `service_charge`, `service_tax`, `fixed_tax`) VALUES
('9', '47', '22', '57', '18');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
