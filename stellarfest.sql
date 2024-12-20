-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2024 at 04:40 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stellarfest`
--

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `EventId` varchar(255) NOT NULL,
  `EventName` varchar(255) NOT NULL,
  `EventDate` date NOT NULL,
  `EventLocation` varchar(255) NOT NULL,
  `EventDescription` varchar(255) NOT NULL,
  `OrganizerId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EventId`, `EventName`, `EventDate`, `EventLocation`, `EventDescription`, `OrganizerId`) VALUES
('00001', 'My Birthday!', '2024-12-31', 'Bina Nusantara', 'Happy Birthday!', '00001');

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

CREATE TABLE `invitation` (
  `InvitationId` varchar(255) NOT NULL,
  `EventId` varchar(255) NOT NULL,
  `UserId` varchar(255) NOT NULL,
  `InvitationStatus` varchar(255) NOT NULL,
  `InvitationRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invitation`
--

INSERT INTO `invitation` (`InvitationId`, `EventId`, `UserId`, `InvitationStatus`, `InvitationRole`) VALUES
('00001', '00001', '00002', 'Waiting to be accepted', 'Vendor'),
('00002', '00001', '00003', 'Waiting to be accepted', 'Guest');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductId` varchar(255) NOT NULL,
  `VendorId` varchar(255) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `ProductDescription` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` varchar(255) NOT NULL,
  `UserEmail` varchar(255) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `UserPassword` varchar(255) NOT NULL,
  `UserRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `UserEmail`, `UserName`, `UserPassword`, `UserRole`) VALUES
('00001', 'eo@gmail.com', 'eo', 'eo123', 'Event Organizer'),
('00002', 'vendor@gmail.com', 'vendor', 'vendor123', 'Vendor'),
('00003', 'guest@gmail.com', 'guest', 'guest123', 'Guest'),
('00004', 'admin@gmail.com', 'admin', 'admin123', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`EventId`),
  ADD KEY `OrganizerId` (`OrganizerId`);

--
-- Indexes for table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`InvitationId`),
  ADD KEY `EventId` (`EventId`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductId`),
  ADD KEY `VendorId` (`VendorId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`OrganizerId`) REFERENCES `user` (`UserId`);

--
-- Constraints for table `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `invitation_ibfk_1` FOREIGN KEY (`EventId`) REFERENCES `event` (`EventId`),
  ADD CONSTRAINT `invitation_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`VendorId`) REFERENCES `user` (`UserId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
