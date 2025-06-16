ALTER TABLE permissoes
ADD area VARCHAR(50);

UPDATE permissoes SET area = 'Produto' WHERE id = 1;
UPDATE permissoes SET area = 'Produto' WHERE id = 2;
UPDATE permissoes SET area = 'Produto' WHERE id = 3;
UPDATE permissoes SET area = 'Produto' WHERE id = 11;

UPDATE permissoes SET area = 'Movimentacao' WHERE id = 4;
UPDATE permissoes SET area = 'Movimentacao' WHERE id = 5;
UPDATE permissoes SET area = 'Movimentacao' WHERE id = 6;

UPDATE permissoes SET area = 'Categoria' WHERE id = 8;
UPDATE permissoes SET area = 'Categoria' WHERE id = 9;
UPDATE permissoes SET area = 'Categoria' WHERE id = 10;

UPDATE permissoes SET area = 'Estoque' WHERE id = 7;

INSERT INTO permissoes (nome, area) VALUES
('ESTOQUE_DESATIVAR', 'Estoque'),
('ESTOQUE_DELETAR', 'Estoque'),
('CATEGORIA_DELETAR', 'Categoria'),
('USUARIO_CADASTRAR', 'Usuario'),
('USUARIO_BUSCAR', 'Usuario'),
('USUARIO_DESATIVAR', 'Usuario'),
('USUARIO_DELETAR', 'Usuario');
