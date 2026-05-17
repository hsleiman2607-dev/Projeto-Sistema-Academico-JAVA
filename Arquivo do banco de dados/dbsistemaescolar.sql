-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2026 at 12:22 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbsistemaescolar`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_aluno`
--

CREATE TABLE `tb_aluno` (
  `pk_rgm` char(8) NOT NULL,
  `pk_cpf` char(13) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `data_nasc` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `numero` char(14) NOT NULL,
  `endereco` varchar(30) NOT NULL,
  `municipio` varchar(20) NOT NULL,
  `uf` enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_aluno`
--

INSERT INTO `tb_aluno` (`pk_rgm`, `pk_cpf`, `nome`, `data_nasc`, `email`, `numero`, `endereco`, `municipio`, `uf`) VALUES
('11111111', '11111111111', '11111111111', '1111-11-11', '1111111111111', '(11)11111-1111', '111111111111', '1111', 'AC'),
('13123133', '22222222222', '11212121212', '1212-12-12', '222222222222', '(22)22222-2222', '22222222222', '222222', 'AC'),
('44444444', '44444444444', '444444444', '1222-12-12', '3333333333333', '(33)33333-3333', '33333333333333', '33333333333333', 'AC'),
('44444565', '55555555555', '555555', '1111-11-11', '2222222222222', '(12)31231-2313', '32131231231231', '3121313123', 'AC');

-- --------------------------------------------------------

--
-- Table structure for table `tb_curso`
--

CREATE TABLE `tb_curso` (
  `pk_id_curso` int(11) NOT NULL,
  `curso` enum('Analise e desenvolvimento de sistemas','Ciencia da computação','','') NOT NULL,
  `campus` enum('Tatuape','Villa-Lobos','EAD','') NOT NULL,
  `periodo` enum('Matutino','Vespertino','Noturno','') NOT NULL,
  `fk_rgm` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_curso`
--

INSERT INTO `tb_curso` (`pk_id_curso`, `curso`, `campus`, `periodo`, `fk_rgm`) VALUES
(1, 'Analise e desenvolvimento de sistemas', 'Tatuape', 'Matutino', '11111111'),
(2, 'Analise e desenvolvimento de sistemas', 'Tatuape', 'Vespertino', '13123133'),
(3, 'Analise e desenvolvimento de sistemas', 'Tatuape', 'Noturno', '44444444'),
(4, 'Analise e desenvolvimento de sistemas', 'Tatuape', 'Noturno', '44444565');

-- --------------------------------------------------------

--
-- Table structure for table `tb_notas`
--

CREATE TABLE `tb_notas` (
  `id_nota` int(11) NOT NULL,
  `disciplina` enum('Programação Orientada a Objeto','Banco de dados','Programação WEB','') NOT NULL,
  `semestre` enum('1º','2º','3º','4º','5º','6º','7º','8º') NOT NULL,
  `nota` enum('0','0,5','1','1,5','2','2,5','3','3,5','4','4,5','5','5,5','6','6,5','7','7,5','8','8,5','9','9,5','10') NOT NULL,
  `faltas` int(11) NOT NULL,
  `fk_rgm` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_notas`
--

INSERT INTO `tb_notas` (`id_nota`, `disciplina`, `semestre`, `nota`, `faltas`, `fk_rgm`) VALUES
(1, 'Programação Orientada a Objeto', '1º', '2,5', 3, '11111111'),
(3, 'Banco de dados', '4º', '2', 3, '11111111'),
(5, 'Programação Orientada a Objeto', '2º', '0', 22, '11111111'),
(7, 'Banco de dados', '2º', '0', 22, '11111111');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_aluno`
--
ALTER TABLE `tb_aluno`
  ADD PRIMARY KEY (`pk_cpf`,`pk_rgm`),
  ADD UNIQUE KEY `UNIQUE` (`email`,`numero`) USING BTREE,
  ADD UNIQUE KEY `pk_rgm` (`pk_rgm`);

--
-- Indexes for table `tb_curso`
--
ALTER TABLE `tb_curso`
  ADD PRIMARY KEY (`pk_id_curso`),
  ADD KEY `fk_rgm` (`fk_rgm`);

--
-- Indexes for table `tb_notas`
--
ALTER TABLE `tb_notas`
  ADD PRIMARY KEY (`id_nota`),
  ADD UNIQUE KEY `uq_aluno_disciplina_semestre` (`fk_rgm`,`disciplina`,`semestre`),
  ADD KEY `fk_rgm` (`fk_rgm`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_curso`
--
ALTER TABLE `tb_curso`
  MODIFY `pk_id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_notas`
--
ALTER TABLE `tb_notas`
  MODIFY `id_nota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_curso`
--
ALTER TABLE `tb_curso`
  ADD CONSTRAINT `tb_curso_ibfk_1` FOREIGN KEY (`fk_rgm`) REFERENCES `tb_aluno` (`pk_rgm`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_notas`
--
ALTER TABLE `tb_notas`
  ADD CONSTRAINT `tb_notas_ibfk_1` FOREIGN KEY (`fk_rgm`) REFERENCES `tb_aluno` (`pk_rgm`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
