-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Dec 02, 2014 at 12:52 PM
-- Server version: 5.5.38
-- PHP Version: 5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `guahao`
--

-- --------------------------------------------------------

--
-- Table structure for table `gh_city`
--

CREATE TABLE `gh_city` (
`id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_doctor`
--

CREATE TABLE `gh_doctor` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL COMMENT '科室的名称',
  `hospital_id` int(11) NOT NULL COMMENT '所属医院的id',
  `section_id` int(11) NOT NULL COMMENT '所属科室的id',
  `img` varchar(40) CHARACTER SET latin1 NOT NULL COMMENT '医生图片的URL',
  `ticket_num` int(11) NOT NULL COMMENT '此医生能挂的号的数量',
  `level` varchar(20) CHARACTER SET latin1 NOT NULL COMMENT '医生的职称',
  `favorite` text NOT NULL COMMENT '医生擅长的方面'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_doctor_online`
--

CREATE TABLE `gh_doctor_online` (
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title` varchar(90) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `time` varchar(30) CHARACTER SET latin1 NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`id` int(11) NOT NULL,
  `img` text CHARACTER SET latin1
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
  `title` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `time` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `img` text CHARACTER SET utf8 COLLATE utf8_bin
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gh_health`
--

INSERT INTO `gh_health` (`id`, `title`, `content`, `time`, `img`) VALUES
(1, '三高人群每年做一次下肢超声筛查', '...', '2014-11-26', '...');

-- --------------------------------------------------------

--
-- Table structure for table `gh_hospital`
--

CREATE TABLE `gh_hospital` (
`id` int(11) NOT NULL COMMENT '医院编号',
  `name` varchar(30) NOT NULL COMMENT '医院名称',
  `level` varchar(20) NOT NULL COMMENT '医院等级',
  `location` varchar(20) NOT NULL COMMENT '医院地址',
  `img` varchar(40) NOT NULL COMMENT '医院图片的URL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_illhistory`
--

CREATE TABLE `gh_illhistory` (
  `id` int(11) NOT NULL COMMENT '记录主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `docter_id` int(11) NOT NULL COMMENT '医生id',
  `hospital_id` int(11) NOT NULL COMMENT '医院id',
  `section_id` int(11) NOT NULL COMMENT '科室id',
  `description` text NOT NULL COMMENT '病例描述',
  `add_up_time` date NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_interested_hospital`
--

CREATE TABLE `gh_interested_hospital` (
`id` int(11) NOT NULL COMMENT '记录主键',
  `user_id` int(11) NOT NULL COMMENT '用户的id',
  `hospital_id` int(11) NOT NULL COMMENT '被关注的医院的id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_interest_docter`
--

CREATE TABLE `gh_interest_docter` (
`id` int(11) NOT NULL COMMENT '记录的主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `docter_id` int(11) NOT NULL COMMENT '被关注的医生的id'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gh_province`
--

CREATE TABLE `gh_province` (
`id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_record`
--

CREATE TABLE `gh_record` (
  `id` int(11) DEFAULT NULL COMMENT '挂号记录的主键，自增',
  `user_id` int(11) NOT NULL,
  `docter_id` int(11) NOT NULL,
  `hospital_id` int(11) NOT NULL COMMENT '挂号医院的id',
  `section_id` int(11) NOT NULL COMMENT '挂号科室的id',
  `deadline` date NOT NULL COMMENT '预约就诊的时间',
  `add_up_time` date NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_section`
--

CREATE TABLE `gh_section` (
`id` int(11) NOT NULL COMMENT '医生的id',
  `name` varchar(20) NOT NULL COMMENT '医生姓名',
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gh_user`
--

CREATE TABLE `gh_user` (
`user_id` int(8) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL COMMENT '0 female ,1 male',
  `age` int(8) DEFAULT NULL,
  `identification` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '住址',
  `rep` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '5',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `img` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户头像地址'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gh_user`
--

INSERT INTO `gh_user` (`user_id`, `username`, `password`, `nickname`, `gender`, `age`, `identification`, `telephone`, `address`, `rep`, `email`, `state`, `img`) VALUES
(8, '123', '12345', '123', 1, 18, '123', '123', NULL, NULL, NULL, '0', ''),
(11, 'niuwei', 'niuwei', 'niuwei', 1, 123, '9876543210', '15652953456', NULL, '5', NULL, '0', '');

-- --------------------------------------------------------

--
-- Table structure for table `gh_zixun`
--

CREATE TABLE `gh_zixun` (
  `id` int(11) NOT NULL COMMENT '记录主键,自增',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `description` text CHARACTER SET latin1 NOT NULL COMMENT '咨询描述',
  `img` varchar(40) CHARACTER SET latin1 NOT NULL COMMENT '图片URL',
  `add_up_time` date NOT NULL COMMENT '更新时间',
  `section_id` int(11) NOT NULL COMMENT '科室id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gh_city`
--
ALTER TABLE `gh_city`
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
-- Indexes for table `gh_hospital`
--
ALTER TABLE `gh_hospital`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_interested_hospital`
--
ALTER TABLE `gh_interested_hospital`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_interest_docter`
--
ALTER TABLE `gh_interest_docter`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_province`
--
ALTER TABLE `gh_province`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_section`
--
ALTER TABLE `gh_section`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gh_user`
--
ALTER TABLE `gh_user`
 ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `identification` (`identification`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gh_city`
--
ALTER TABLE `gh_city`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
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
-- AUTO_INCREMENT for table `gh_hospital`
--
ALTER TABLE `gh_hospital`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医院编号';
--
-- AUTO_INCREMENT for table `gh_interested_hospital`
--
ALTER TABLE `gh_interested_hospital`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录主键';
--
-- AUTO_INCREMENT for table `gh_interest_docter`
--
ALTER TABLE `gh_interest_docter`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录的主键，自增';
--
-- AUTO_INCREMENT for table `gh_province`
--
ALTER TABLE `gh_province`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `gh_section`
--
ALTER TABLE `gh_section`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生的id';
--
-- AUTO_INCREMENT for table `gh_user`
--
ALTER TABLE `gh_user`
MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;