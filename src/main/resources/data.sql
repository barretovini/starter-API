-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.6.3-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela starter_4letras.desafios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `desafios` DISABLE KEYS */;
INSERT INTO `desafios` (`id`, `nome_desafio`) VALUES
	(1, 'Desafio MVC');
/*!40000 ALTER TABLE `desafios` ENABLE KEYS */;

-- Copiando dados para a tabela starter_4letras.entrega_desafio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `entrega_desafio` DISABLE KEYS */;
INSERT INTO `entrega_desafio` (`id`, `repositorio`, `desafios_id`, `starters_id`) VALUES
	(1, 'https://www.google.com', 1, 1);
/*!40000 ALTER TABLE `entrega_desafio` ENABLE KEYS */;

-- Copiando dados para a tabela starter_4letras.nota_desafio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `nota_desafio` DISABLE KEYS */;
INSERT INTO `nota_desafio` (`id`, `nota_qualidade`, `nota_quantidade`, `entrega_desafio_id`) VALUES
	(1, 2, 3, 1);
/*!40000 ALTER TABLE `nota_desafio` ENABLE KEYS */;

-- Copiando dados para a tabela starter_4letras.perfil: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` (`id`, `nome`) VALUES
	(1, 'INSTRUTOR'),
	(2, 'STARTER');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;

-- Copiando dados para a tabela starter_4letras.starters: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `starters` DISABLE KEYS */;
INSERT INTO `starters` (`id`, `email_starter`, `cep`, `complemento`, `estado`, `municipio`, `numero`, `rua`, `nome_linguagem`, `nome_starter`, `quatro_letras`, `telefone`) VALUES
	(1, 'robson.salazar@gft.com', '79003-109', NULL, 'MS', 'Itanhangá Park', 36, 'Rua Sofia Melke', 'Java', 'Robson', 'rbsz', '(15)92906-3220');
/*!40000 ALTER TABLE `starters` ENABLE KEYS */;

-- Copiando dados para a tabela starter_4letras.usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `senha`, `perfil_id`) VALUES
	(1, 'instrutor@gft.com', '$2a$12$XP0ILHZuyNNg4hwOnDkC.uTWnCzjlzPTC/QGunCLP277B6kza508y', 1),
	(2, 'starter@gft.com', '$2a$12$XP0ILHZuyNNg4hwOnDkC.uTWnCzjlzPTC/QGunCLP277B6kza508y', 2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

SET FOREIGN_KEY_CHECKS=0;
