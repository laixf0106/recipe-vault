```sql
-- Create recipes table
CREATE TABLE recipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    difficulty ENUM('Easy', 'Medium', 'Hard') NOT NULL,
    instructions TEXT,
    image_url VARCHAR(255),
    creator_name VARCHAR(100),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Create ingredients table
CREATE TABLE ingredients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recipe_id INT,
    ingredient_name VARCHAR(255),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE
);

-- Insert sample recipes
INSERT INTO recipes (title, difficulty, instructions, creator_name)
VALUES 
('Classic Pancakes', 'Easy', 'Mix flour, milk, and eggs. Cook on skillet.', 'Alice'),
('Spaghetti Bolognese', 'Medium', 'Cook spaghetti. Prepare meat sauce. Mix and serve.', 'Bob'),
('Chicken Curry', 'Medium', 'Cook chicken with curry spices. Simmer till done.', 'Charlie'),
('Beef Wellington', 'Hard', 'Wrap beef in pastry. Bake to perfection.', 'Diana'),
('Simple Salad', 'Easy', 'Chop veggies and mix with dressing.', 'Eve');

-- Insert sample ingredients
INSERT INTO ingredients (recipe_id, ingredient_name) VALUES
(1, 'Flour'), (1, 'Milk'), (1, 'Eggs'),
(2, 'Spaghetti'), (2, 'Ground Beef'), (2, 'Tomato Sauce'),
(3, 'Chicken'), (3, 'Curry Powder'), (3, 'Coconut Milk'),
(4, 'Beef Tenderloin'), (4, 'Puff Pastry'), (4, 'Mushrooms'),
(5, 'Lettuce'), (5, 'Tomatoes'), (5, 'Cucumber');
