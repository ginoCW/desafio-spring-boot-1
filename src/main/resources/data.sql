INSERT INTO usuario (nombre_usuario) VALUES ('dummy');

INSERT INTO tarea (titulo, descripcion, usuario) VALUES ('tarea 1', 'Descripcion 1', 1);
INSERT INTO tarea (titulo, descripcion, usuario) VALUES ('tarea 2', 'Descripcion 2', 1);
INSERT INTO tarea (titulo, descripcion, usuario) VALUES ('tarea 3', 'Descripcion 3', 1);

INSERT INTO estado_tarea (estado, estado_activo, tarea) VALUES ('PENDIENTE', FALSE, 1);
INSERT INTO estado_tarea (estado, estado_activo, tarea) VALUES ('EN PROGRESO', TRUE, 1);
INSERT INTO estado_tarea (estado, estado_activo, tarea) VALUES ('PENDIENTE', TRUE, 2);
INSERT INTO estado_tarea (estado, estado_activo, tarea) VALUES ('PENDIENTE', TRUE, 3);