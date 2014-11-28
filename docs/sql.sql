-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 11 月 25 日 10:59
-- 服务器版本: 5.6.12-log
-- PHP 版本: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `AID` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `ID` varchar(20) NOT NULL,
  PRIMARY KEY (`AID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='管理员' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `doctorinfo`
--

CREATE TABLE IF NOT EXISTS `doctorinfo` (
  `DID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) NOT NULL,
  `HID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `protitle` varchar(1) DEFAULT NULL COMMENT '职称',
  `adept` varchar(30) DEFAULT NULL COMMENT '擅长',
  `data` varchar(2) DEFAULT NULL COMMENT '可预约日期。如十月22日下午课预约，则文本加上“10-22B%”。',
  `more_info` varchar(50) NOT NULL,
  PRIMARY KEY (`DID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `doctorinfo`
--

INSERT INTO `doctorinfo` (`DID`, `LID`, `HID`, `name`, `protitle`, `adept`, `data`, `more_info`) VALUES
(1, 0, 0, '', NULL, NULL, NULL, '');

-- --------------------------------------------------------

--
-- 表的结构 `hospitalinfo`
--

CREATE TABLE IF NOT EXISTS `hospitalinfo` (
  `HID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `province` varchar(2) NOT NULL,
  `city` varchar(8) NOT NULL,
  `street` varchar(20) NOT NULL,
  `level` char(1) NOT NULL,
  `own_lab` varchar(100) NOT NULL,
  `more_info` varchar(50) NOT NULL,
  PRIMARY KEY (`HID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `labinfo`
--

CREATE TABLE IF NOT EXISTS `labinfo` (
  `LID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `introduce` text,
  PRIMARY KEY (`LID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `MID` int(11) NOT NULL AUTO_INCREMENT,
  `OID` int(11) NOT NULL,
  `point` int(11) DEFAULT '10' COMMENT '打分',
  `comment` text COMMENT '评论',
  PRIMARY KEY (`MID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `OID` int(11) NOT NULL AUTO_INCREMENT,
  `UID` int(11) NOT NULL,
  `DID` int(11) NOT NULL,
  `getdate` date NOT NULL COMMENT '下订单时间',
  `orderdate` date NOT NULL COMMENT '预约时间',
  `state` int(11) NOT NULL COMMENT '当前状态：0：已取消；1：预订成功时间未到；2：已打印预约单；3：订单成功完成；4：用户违约',
  PRIMARY KEY (`OID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `IDcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(10) DEFAULT NULL,
  `state` varchar(1) NOT NULL DEFAULT '0' COMMENT '用户当前状态 0：未激活 1：正常使用 2：黑名单',
  `rep` varchar(1) NOT NULL DEFAULT '5' COMMENT '用户信誉度',
  PRIMARY KEY (`UID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
