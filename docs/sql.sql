-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Nov 28, 2014 at 03:34 PM
-- Server version: 5.5.38
-- PHP Version: 5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `guahao`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
`AID` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `ID` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='管理员';

-- --------------------------------------------------------

--
-- Table structure for table `doctorinfo`
--

CREATE TABLE `doctorinfo` (
`DID` int(11) NOT NULL,
  `LID` int(11) NOT NULL,
  `HID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `protitle` varchar(1) DEFAULT NULL COMMENT '职称',
  `adept` varchar(30) DEFAULT NULL COMMENT '擅长',
  `data` varchar(2) DEFAULT NULL COMMENT '可预约日期。如十月22日下午课预约，则文本加上“10-22B%”。',
  `more_info` varchar(50) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doctorinfo`
--

INSERT INTO `doctorinfo` (`DID`, `LID`, `HID`, `name`, `protitle`, `adept`, `data`, `more_info`) VALUES
(1, 0, 0, '', NULL, NULL, NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `gh_doctor_dynamic`
--

CREATE TABLE `gh_doctor_dynamic` (
`id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  `time` varchar(11) COLLATE utf8_bin NOT NULL,
  `img` text COLLATE utf8_bin
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `gh_doctor_dynamic`
--

INSERT INTO `gh_doctor_dynamic` (`id`, `doctor_id`, `doctor_name`, `content`, `time`, `img`) VALUES
(1, 1, 'test', '...', '...', '...');

-- --------------------------------------------------------

--
-- Table structure for table `gh_doctor_online`
--

CREATE TABLE `gh_doctor_online` (
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title` varchar(90) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `time` varchar(30) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`id` int(11) NOT NULL,
  `img` text
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gh_doctor_online`
--

INSERT INTO `gh_doctor_online` (`doctor_id`, `doctor_name`, `title`, `time`, `content`, `id`, `img`) VALUES
(1, 'test', '..', '..', 'dsadasds', 1, '..');

-- --------------------------------------------------------

--
-- Table structure for table `gh_health`
--

CREATE TABLE `gh_health` (
`id` int(11) NOT NULL,
  `title` varchar(60) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  `time` varchar(12) COLLATE utf8_bin NOT NULL,
  `img` text COLLATE utf8_bin
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `gh_health`
--

INSERT INTO `gh_health` (`id`, `title`, `content`, `time`, `img`) VALUES
(1, '三高人群每年做一次下肢超声筛查', '...', '2014-11-26', '...');

-- --------------------------------------------------------

--
-- Table structure for table `gh_user`
--

CREATE TABLE `gh_user` (
`user_id` int(8) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `nickname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL COMMENT '0 female ,1 male',
  `age` int(8) DEFAULT NULL,
  `identification` varchar(18) COLLATE utf8_bin NOT NULL,
  `telephone` varchar(11) COLLATE utf8_bin NOT NULL,
  `address` varchar(120) COLLATE utf8_bin DEFAULT NULL COMMENT '住址',
  `rep` varchar(1) COLLATE utf8_bin DEFAULT '5',
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `state` varchar(1) COLLATE utf8_bin DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `gh_user`
--

INSERT INTO `gh_user` (`user_id`, `username`, `password`, `nickname`, `gender`, `age`, `identification`, `telephone`, `address`, `rep`, `email`, `state`) VALUES
(8, '123', '12345', '123', 1, 18, '123', '123', NULL, NULL, NULL, '0'),
(11, 'niuwei', 'niuwei', 'niuwei', 1, 123, '9876543210', '15652953456', NULL, '5', NULL, '0');

-- --------------------------------------------------------

--
-- Table structure for table `hospitalinfo`
--

CREATE TABLE `hospitalinfo` (
`HID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `province` varchar(2) NOT NULL,
  `city` varchar(8) NOT NULL,
  `street` varchar(20) NOT NULL,
  `level` char(1) NOT NULL,
  `own_lab` varchar(100) NOT NULL,
  `more_info` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `labinfo`
--

CREATE TABLE `labinfo` (
`LID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `introduce` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
`MID` int(11) NOT NULL,
  `OID` int(11) NOT NULL,
  `point` int(11) DEFAULT '10' COMMENT '打分',
  `comment` text COMMENT '评论'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
`OID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `DID` int(11) NOT NULL,
  `getdate` date NOT NULL COMMENT '下订单时间',
  `orderdate` date NOT NULL COMMENT '预约时间',
  `state` int(11) NOT NULL COMMENT '当前状态：0：已取消；1：预订成功时间未到；2：已打印预约单；3：订单成功完成；4：用户违约'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
`UID` int(11) NOT NULL,
  `IDcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(10) DEFAULT NULL,
  `state` varchar(1) NOT NULL DEFAULT '0' COMMENT '用户当前状态 0：未激活 1：正常使用 2：黑名单',
  `rep` varchar(1) NOT NULL DEFAULT '5' COMMENT '用户信誉度'
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UID`, `IDcard`, `password`, `email`, `phone`, `address`, `state`, `rep`) VALUES
(1, '12211023', '12211023', 'nniuwei@163.com', '15652953455', '北航', '2', '5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
 ADD PRIMARY KEY (`AID`);

--
-- Indexes for table `doctorinfo`
--
ALTER TABLE `doctorinfo`
 ADD PRIMARY KEY (`DID`);

--
-- Indexes for table `gh_doctor_dynamic`
--
ALTER TABLE `gh_doctor_dynamic`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_doctor_online`
--
ALTER TABLE `gh_doctor_online`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_health`
--
ALTER TABLE `gh_health`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_user`
--
ALTER TABLE `gh_user`
 ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `identification` (`identification`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `hospitalinfo`
--
ALTER TABLE `hospitalinfo`
 ADD PRIMARY KEY (`HID`);

--
-- Indexes for table `labinfo`
--
ALTER TABLE `labinfo`
 ADD PRIMARY KEY (`LID`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`MID`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
 ADD PRIMARY KEY (`OID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
MODIFY `AID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `doctorinfo`
--
ALTER TABLE `doctorinfo`
MODIFY `DID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `gh_doctor_dynamic`
--
ALTER TABLE `gh_doctor_dynamic`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `gh_doctor_online`
--
ALTER TABLE `gh_doctor_online`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `gh_health`
--
ALTER TABLE `gh_health`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `gh_user`
--
ALTER TABLE `gh_user`
MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `hospitalinfo`
--
ALTER TABLE `hospitalinfo`
MODIFY `HID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `labinfo`
--
ALTER TABLE `labinfo`
MODIFY `LID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
MODIFY `MID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
MODIFY `OID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
