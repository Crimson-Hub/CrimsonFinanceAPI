CREATE TABLE colors (
    id SMALLINT UNIQUE PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    color VARCHAR(7) NOT NULL
);

INSERT INTO colors VALUES
(1, 'Preto', '#000000'),
(2, 'Branco', '#FFFFFF'),
(3, 'Vermelho', '#FF0000'),
(4, 'Verde', '#00FF00'),
(5, 'Azul', '#0000FF'),
(6, 'Amarelo', '#FFFF00'),
(7, 'Ciano', '#00FFFF'),
(8, 'Magenta', '#FF00FF'),
(9, 'Cinza Claro', '#D3D3D3'),
(10, 'Cinza Escuro', '#A9A9A9'),
(11, 'Azul Marinho', '#000080'),
(12, 'Verde Escuro', '#006400'),
(13, 'Verde Limão', '#32CD32'),
(14, 'Laranja', '#FFA500'),
(15, 'Rosa', '#FFC0CB'),
(16, 'Roxo', '#800080'),
(17, 'Marron', '#A52A2A'),
(18, 'Bege', '#F5F5DC'),
(19, 'Azul Turquesa', '#40E0D0'),
(20, 'Salmão', '#FA8072'),
(21, 'Dourado', '#FFD700'),
(22, 'Prata', '#C0C0C0'),
(23, 'Coral', '#FF7F50'),
(24, 'Azul Celeste', '#87CEEB'),
(25, 'Verde Oliva', '#808000');