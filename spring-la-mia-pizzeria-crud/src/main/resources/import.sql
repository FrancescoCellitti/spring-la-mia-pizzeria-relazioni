-- import.sql for Hibernate
-- This file inserts 10 example pizzas into the `pizzas` table.
-- Place under src/main/resources so Hibernate will execute it after schema generation

INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Margherita', 'Pomodoro, mozzarella, basilico', 'margherita.jpg', 6.50, 'La classica pizza napoletana con pomodoro fresco e basilico.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Marinara', 'Pomodoro, aglio, origano', 'marinara.jpg', 5.50, 'Semplice e saporita: pomodoro, aglio e origano.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Quattro Formaggi', 'Mozzarella, gorgonzola, parmigiano, provolone', '4formaggi.jpg', 8.90, 'Ricca e cremosa con quattro formaggi italiani.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Diavola', 'Pomodoro, mozzarella, salame piccante', 'diavola.jpg', 7.80, 'Piccante e gustosa, per chi ama i sapori forti.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Capricciosa', 'Pomodoro, mozzarella, prosciutto, funghi, carciofi, olive', 'capricciosa.jpg', 9.20, 'Ricca di ingredienti, ogni morso è una sorpresa.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Prosciutto e Funghi', 'Pomodoro, mozzarella, prosciutto cotto, funghi', 'prosciutto_funghi.jpg', 8.00, 'Combinazione classica e molto apprezzata.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Vegetariana', 'Pomodoro, mozzarella, melanzane, zucchine, peperoni', 'vegetariana.jpg', 7.50, 'Colorata e leggera, ideale per vegetariani.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Tonno e Cipolla', 'Pomodoro, mozzarella, tonno, cipolle', 'tonno_cipolla.jpg', 8.10, 'Sapore deciso con tonno di qualità.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Bufalina', 'Pomodoro, mozzarella di bufala, basilico', 'bufalina.jpg', 10.00, 'Mozzarella di bufala DOP per un gusto superiore.');
INSERT INTO pizzas (name, description, image, price, synopsis) VALUES ('Ortolana', 'Mozzarella, pomodorini, rucola, scaglie di parmigiano', 'ortolana.jpg', 9.00, 'Fresca con rucola e scaglie di formaggio.');

-- End of import.sql
