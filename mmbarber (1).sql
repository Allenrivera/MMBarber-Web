-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-09-2023 a las 01:11:01
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mmbarber`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrarservicio` (IN `Id` INT)   DELETE FROM servicios WHERE Id_servicio = Id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `crearservicio` (IN `Id_servicio` INT, IN `nombre_servicio` VARCHAR(30), IN `Id_tipo_servicio` INT, IN `Descripcion_servicio` VARCHAR(30), IN `precio` INT)   INSERT INTO Servicios (id_servicio, nombre_servicio, Id_tipo_servicio, Descripcion_servicio, precio) VALUES (id_servicio, nombre_servicio, Id_tipo_servicio, Descripcion_servicio, precio)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listaservicios` ()   SELECT * FROM servicios$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin_roles`
--

CREATE TABLE `admin_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admin_roles`
--

INSERT INTO `admin_roles` (`user_id`, `role_id`, `id_usuario`) VALUES
(1010960280, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `Id_area` int(11) NOT NULL,
  `Descripcion_area` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargos`
--

CREATE TABLE `cargos` (
  `Id_cargo` int(11) NOT NULL,
  `Id_Inventario` int(11) NOT NULL,
  `Id_cita` int(11) NOT NULL,
  `Id_proveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `Id_cita` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `Id_empleado` int(11) NOT NULL,
  `Id_servicio` int(11) NOT NULL,
  `Id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `Id_cliente` int(11) NOT NULL,
  `Id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `Id_empleado` int(11) NOT NULL,
  `Id_cargo` int(11) NOT NULL,
  `Id_area` int(11) NOT NULL,
  `Id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestion_servicios`
--

CREATE TABLE `gestion_servicios` (
  `id_servicio` bigint(20) NOT NULL,
  `descripcion_servicio` varchar(255) DEFAULT NULL,
  `id_tipo_servicio` int(11) NOT NULL,
  `nombre_servicio` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `Id_inventario` int(11) NOT NULL,
  `Cantidad_stock` int(11) NOT NULL,
  `precio_venta` int(11) NOT NULL,
  `Precio_compra` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `Id_producto` int(11) NOT NULL,
  `Id_orden_compra` int(11) NOT NULL,
  `Id_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_compra`
--

CREATE TABLE `orden_compra` (
  `Id_orden_compra` int(11) NOT NULL,
  `Fecha_compra` date NOT NULL,
  `Descripcion_compra` varchar(30) NOT NULL,
  `Id_proveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `Id_persona` int(11) NOT NULL,
  `primer_nombre` varchar(30) NOT NULL,
  `Segundo_nombre` varchar(30) DEFAULT NULL,
  `primer_apellido` varchar(30) NOT NULL,
  `segundo_apellido` varchar(30) DEFAULT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `contraseña` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pqrs`
--

CREATE TABLE `pqrs` (
  `Id_pqrs` int(11) NOT NULL,
  `descripcion_pqrs` varchar(150) NOT NULL,
  `Id_tipo_pqrs` varchar(30) NOT NULL,
  `Id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` bigint(20) NOT NULL,
  `descripcion_producto` varchar(255) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `imagen_producto` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `descripcion_producto`, `nombre_producto`, `imagen_producto`) VALUES
(1, '200g', 'Cera Moldeadora Fawy', NULL),
(2, '200g', 'Shaving Gel Fade 250ml', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `Id_proveedor` int(11) NOT NULL,
  `Tipo_reporte` varchar(30) NOT NULL,
  `Descripcion_reporte` varchar(30) NOT NULL,
  `Id_cargo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuesta_pqrs`
--

CREATE TABLE `respuesta_pqrs` (
  `respuesta_pqrs` varchar(150) NOT NULL,
  `Id_pqrs` int(11) NOT NULL,
  `Id_cliente` int(11) NOT NULL,
  `Id_tipo_pqrs` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_servicio` bigint(20) NOT NULL,
  `nombre_servicio` varchar(255) DEFAULT NULL,
  `Id_tipo_servicio` int(11) NOT NULL,
  `descripcion_servicio` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_servicio`, `nombre_servicio`, `Id_tipo_servicio`, `descripcion_servicio`, `precio`) VALUES
(3, 'Barba', 2, 'Corte de barba', 7000),
(4, 'Cejaz', 2, 'Arreglo de cejaz', 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_pqrs`
--

CREATE TABLE `tipo_pqrs` (
  `Id_tipo_pqrs` varchar(30) NOT NULL,
  `Descripcion_tipo_pqrs` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_servicio`
--

CREATE TABLE `tipo_servicio` (
  `Id_tipoo_servicio` int(11) NOT NULL,
  `Descripcion_servicio` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `usuario`, `password`) VALUES
(1010960280, 'julian', '12345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `Id_venta` int(11) NOT NULL,
  `Nombre_producto` varchar(30) NOT NULL,
  `cantidad_producto` int(11) NOT NULL,
  `valor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`Id_area`);

--
-- Indices de la tabla `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`Id_cargo`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`Id_cita`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id_cliente`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`Id_empleado`);

--
-- Indices de la tabla `gestion_servicios`
--
ALTER TABLE `gestion_servicios`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`Id_inventario`);

--
-- Indices de la tabla `orden_compra`
--
ALTER TABLE `orden_compra`
  ADD PRIMARY KEY (`Id_orden_compra`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`Id_persona`);

--
-- Indices de la tabla `pqrs`
--
ALTER TABLE `pqrs`
  ADD PRIMARY KEY (`Id_pqrs`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`Id_proveedor`);

--
-- Indices de la tabla `respuesta_pqrs`
--
ALTER TABLE `respuesta_pqrs`
  ADD PRIMARY KEY (`respuesta_pqrs`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_servicio`);

--
-- Indices de la tabla `tipo_pqrs`
--
ALTER TABLE `tipo_pqrs`
  ADD PRIMARY KEY (`Id_tipo_pqrs`);

--
-- Indices de la tabla `tipo_servicio`
--
ALTER TABLE `tipo_servicio`
  ADD PRIMARY KEY (`Id_tipoo_servicio`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`Id_venta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `gestion_servicios`
--
ALTER TABLE `gestion_servicios`
  MODIFY `id_servicio` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_servicio` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=249;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1010960281;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD CONSTRAINT `admin_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `admin_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
