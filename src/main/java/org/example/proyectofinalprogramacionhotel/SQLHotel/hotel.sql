-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2025 a las 20:36:10
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_general_ci NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `dni` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `email`, `contrasena`, `dni`, `telefono`) VALUES
(1, 'Jesus Galisteo', 'jesus.galisteo@gmail.com', '12312eqwe', '12312124Q', '123243242'),
(2, 'Pedro Sanchez', 'pedro.sanchez@gamil.com', 'contraseña123', '25368471P', '122423412'),
(3, 'Paco Lopez', 'paco.lopez@gmail.com', '638293fms', '63491784D', '834793421'),
(4, 'Pedro Muñoz', 'pedro.munoz@gmail.com', '16326ghg2', '12345678A', '612898232');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gerente`
--

CREATE TABLE `gerente` (
  `idGerente` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_general_ci NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `codigo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gerente`
--

INSERT INTO `gerente` (`idGerente`, `nombre`, `email`, `contrasena`, `codigo`) VALUES
(1, 'Francisco Garcia', 'francisco.garcia@gmail.com', '8927342f', 'z2b7ep1'),
(2, 'Paloma Cuevas', 'paloma.cuevas@gmail.com', '78123gdh', '9v4x725');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `idHabitacion` int(11) NOT NULL,
  `numeroHabitacion` int(10) NOT NULL,
  `tipoHabitacion` enum('Individual','Doble','Familiar','Suit') NOT NULL,
  `precioNoche` decimal(8,2) NOT NULL,
  `estadoHabitacion` enum('Libre','Ocupada','Mantenimiento') NOT NULL,
  `idReserva` int(11) DEFAULT NULL,
  `idGerente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`idHabitacion`, `numeroHabitacion`, `tipoHabitacion`, `precioNoche`, `estadoHabitacion`, `idReserva`, `idGerente`) VALUES
(1, 23, 'Doble', 100.00, 'Libre', NULL, 1),
(2, 2, 'Familiar', 175.50, 'Libre', NULL, 1),
(4, 25, 'Suit', 500.00, 'Libre', NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `fechaEntrada` date NOT NULL,
  `fechaSalida` date NOT NULL,
  `estadoReserva` enum('EnProceso','Cancelada','Completada') NOT NULL,
  `numeroPersonas` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`idReserva`, `fechaEntrada`, `fechaSalida`, `estadoReserva`, `numeroPersonas`, `idCliente`) VALUES
(1, '2025-05-23', '2025-05-26', 'EnProceso', 3, 1),
(2, '2025-05-27', '2025-05-29', 'EnProceso', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_servicio`
--

CREATE TABLE `reserva_servicio` (
  `idReserva` int(11) NOT NULL,
  `idServicio` int(11) NOT NULL,
  `fechaReserva` date NOT NULL,
  `numeroPersonas` int(20) NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva_servicio`
--

INSERT INTO `reserva_servicio` (`idReserva`, `idServicio`, `fechaReserva`, `numeroPersonas`, `precio`, `fechaInicio`, `fechaFin`) VALUES
(1, 1, '2025-05-21', 3, 110.00, '2025-05-22', '2025-05-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `idServicio` int(11) NOT NULL,
  `precioHora` decimal(6,2) NOT NULL,
  `tipoServicio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`idServicio`, `precioHora`, `tipoServicio`) VALUES
(1, 10.50, 'Spa'),
(2, 20.00, 'Gimnasio');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `gmail` (`email`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `gerente`
--
ALTER TABLE `gerente`
  ADD PRIMARY KEY (`idGerente`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD UNIQUE KEY `gmail` (`email`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`idHabitacion`),
  ADD KEY `idGerente` (`idGerente`),
  ADD KEY `idCliente` (`idReserva`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `reserva_servicio`
--
ALTER TABLE `reserva_servicio`
  ADD PRIMARY KEY (`idReserva`,`idServicio`),
  ADD KEY `idServicio` (`idServicio`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`idServicio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `gerente`
--
ALTER TABLE `gerente`
  MODIFY `idGerente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `idHabitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `idServicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`),
  ADD CONSTRAINT `habitacion_ibfk_2` FOREIGN KEY (`idGerente`) REFERENCES `gerente` (`idGerente`) ON DELETE CASCADE;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE;

--
-- Filtros para la tabla `reserva_servicio`
--
ALTER TABLE `reserva_servicio`
  ADD CONSTRAINT `reserva_servicio_ibfk_1` FOREIGN KEY (`idReserva`) REFERENCES `reserva` (`idReserva`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserva_servicio_ibfk_2` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
