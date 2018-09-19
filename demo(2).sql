-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 19, 2018 at 01:18 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `fun_id` int(11) NOT NULL,
  `fun_name` varchar(100) NOT NULL,
  `fun_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`fun_id`, `fun_name`, `fun_url`) VALUES
(1, 'Create', 'create'),
(2, 'View', 'view'),
(3, 'Update', 'update'),
(4, 'Delete', 'delete'),
(5, 'Search', 'search');

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

CREATE TABLE `page` (
  `page_id` int(11) NOT NULL,
  `page_name` varchar(100) NOT NULL,
  `page_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `page`
--

INSERT INTO `page` (`page_id`, `page_name`, `page_url`) VALUES
(1, 'Manage Employees', 'emp'),
(2, 'Manage Customers', 'cust'),
(3, 'Manage Suppliers', 'sup'),
(4, 'Complains', 'comp'),
(5, 'Request Leave', 'req_leave');

-- --------------------------------------------------------

--
-- Table structure for table `page_functions`
--

CREATE TABLE `page_functions` (
  `role_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `fun_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `page_functions`
--

INSERT INTO `page_functions` (`role_id`, `page_id`, `fun_id`) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 1, 5),
(2, 1, 2),
(2, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'admin'),
(2, 'employee'),
(3, 'authorizer'),
(4, 'data entry'),
(5, 'auditor');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `role_id`, `username`, `password`) VALUES
(1, 1, 'admin', 'admin'),
(2, 2, 'robin', 'robin'),
(3, 3, 'taylor', 'taylor'),
(4, 4, 'vivian', 'vivian'),
(5, 1, 'harry', 'harry'),
(6, 3, 'melinda', 'melinda'),
(7, 2, 'harley', 'harley');

-- --------------------------------------------------------

--
-- Table structure for table `user_pages`
--

CREATE TABLE `user_pages` (
  `user_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `c` varchar(100) NOT NULL,
  `u` varchar(100) NOT NULL,
  `d` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_pages`
--

INSERT INTO `user_pages` (`user_id`, `page_id`, `c`, `u`, `d`) VALUES
(1, 1, 'addEmployee.jsp', 'updateEmployee.jsp', 'deleteEmployee.jsp'),
(1, 2, 'addCustomer.jsp', 'updateCustomer.jsp', 'deleteCustomer.jsp'),
(1, 3, 'addSupplier.jsp', 'updateSupplier.jsp', 'deleteSupplier.jsp'),
(1, 4, 'addComplain', 'updateComplain.jsp', 'deleteComplain.jsp'),
(1, 5, 'addLeave', 'updateLeave.jsp', 'deleteLeave.jsp'),
(2, 1, 'addEmployee.jsp', '', ''),
(2, 4, 'addComplain', 'updateComplain.jsp', ''),
(2, 5, 'addLeave', 'updateLeave.jsp', 'deleteLeave.jsp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`fun_id`);

--
-- Indexes for table `page`
--
ALTER TABLE `page`
  ADD PRIMARY KEY (`page_id`);

--
-- Indexes for table `page_functions`
--
ALTER TABLE `page_functions`
  ADD PRIMARY KEY (`role_id`,`page_id`,`fun_id`),
  ADD KEY `page_id` (`page_id`),
  ADD KEY `fun_id` (`fun_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_pages`
--
ALTER TABLE `user_pages`
  ADD PRIMARY KEY (`user_id`,`page_id`),
  ADD KEY `fk2` (`page_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
  MODIFY `fun_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `page`
--
ALTER TABLE `page`
  MODIFY `page_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `page_functions`
--
ALTER TABLE `page_functions`
  ADD CONSTRAINT `page_functions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `page_functions_ibfk_2` FOREIGN KEY (`page_id`) REFERENCES `page` (`page_id`),
  ADD CONSTRAINT `page_functions_ibfk_3` FOREIGN KEY (`fun_id`) REFERENCES `function` (`fun_id`);

--
-- Constraints for table `user_pages`
--
ALTER TABLE `user_pages`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`page_id`) REFERENCES `page` (`page_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
