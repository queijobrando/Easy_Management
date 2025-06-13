ALTER TABLE usuarios
ADD COLUMN grupo_id BIGINT NOT NULL;

ALTER TABLE usuarios
ADD CONSTRAINT fk_usuarios_grupo
FOREIGN KEY (grupo_id) REFERENCES grupos(id);

DROP TABLE IF EXISTS usuario_grupos;