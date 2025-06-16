ALTER TABLE produtos
ADD usuario_id BIGINT REFERENCES usuarios(id);

ALTER TABLE categorias
ADD usuario_id BIGINT REFERENCES usuarios(id);

ALTER TABLE movimentacao_estoque
ADD usuario_id BIGINT REFERENCES usuarios(id);