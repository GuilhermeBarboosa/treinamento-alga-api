-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 10-Ago-2022 às 14:26
-- Versão do servidor: 8.0.29
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `treinamento`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(160) NOT NULL,
  `email` varchar(160) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `fone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `email`, `fone`) VALUES
(1, 'gui', 'gui@gmail.com', '34 999999'),
(2, 'Maria', 'maria@gmail.com', '37849823122'),
(4, 'teste', 'teste@gmail.com', '34 999999'),
(5, 'teste', 'null', '34 999999'),
(7, 'teste', 'teste1111@gmail.com', '34 999999'),
(8, 'aaa', 'guilherme.strg@gmail.com', '2312312'),
(9, 'teste', 'teste1@gmail.com', '34 999999'),
(10, 'aaaaaaaa', 'sdadsa@gmailsda', '231231'),
(11, 'aaaaaaaa', 'a231231@fdfas.com', '32131');

-- --------------------------------------------------------

--
-- Estrutura da tabela `entrega`
--

DROP TABLE IF EXISTS `entrega`;
CREATE TABLE IF NOT EXISTS `entrega` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente` bigint NOT NULL,
  `taxa` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `data_pedido` datetime NOT NULL,
  `data_finalizacao` datetime DEFAULT NULL,
  `destinatario_nome` varchar(60) NOT NULL,
  `destinatario_logradouro` varchar(255) NOT NULL,
  `destinatario_numero` varchar(60) NOT NULL,
  `destinatario_complemento` varchar(60) NOT NULL,
  `destinatario_bairro` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entrega_cliente` (`cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `entrega`
--

INSERT INTO `entrega` (`id`, `cliente`, `taxa`, `status`, `data_pedido`, `data_finalizacao`, `destinatario_nome`, `destinatario_logradouro`, `destinatario_numero`, `destinatario_complemento`, `destinatario_bairro`) VALUES
(13, 2, '100.50', 'PENDENTE', '2022-08-10 12:27:10', NULL, 'Guilherme', 'teste', '140', 'casa', 'Jardim');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ocorrencia`
--

DROP TABLE IF EXISTS `ocorrencia`;
CREATE TABLE IF NOT EXISTS `ocorrencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  `data_registro` datetime NOT NULL,
  `entrega` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrega` (`entrega`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

--
-- Extraindo dados da tabela `ocorrencia`
--

INSERT INTO `ocorrencia` (`id`, `descricao`, `data_registro`, `entrega`) VALUES
(1, 'a', '2022-08-11 08:41:04', 13),
(10, 'Ocorrencia teste', '2022-08-10 12:35:18', 13),
(11, 'Ocorrencia teste', '2022-08-10 12:40:02', 13);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `ocorrencia`
--
ALTER TABLE `ocorrencia`
  ADD CONSTRAINT `ocorrencia_ibfk_1` FOREIGN KEY (`entrega`) REFERENCES `entrega` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
