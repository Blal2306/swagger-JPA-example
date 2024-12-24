-- Table for Category (only create if it does not exist)
CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Use INTEGER, will be treated as BIGINT in application
    description TEXT NOT NULL
);

-- Table for Recipe (only create if it does not exist)
CREATE TABLE IF NOT EXISTS recipe (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Use INTEGER, will be treated as BIGINT in application
    description TEXT,
    prep_time INTEGER,
    cook_time INTEGER,
    servings INTEGER,
    source TEXT,
    url TEXT,
    directions TEXT,
    image BLOB,
    difficulty TEXT
);

-- Join table for Many-to-Many relationship between Recipe and Category (only create if it does not exist)
CREATE TABLE IF NOT EXISTS recipe_category (
    recipe_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (recipe_id, category_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

-- Table for Ingredient (only create if it does not exist)
CREATE TABLE IF NOT EXISTS ingredient (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Use INTEGER, will be treated as BIGINT in application
    description TEXT,
    amount DECIMAL(10, 2),
    uom_id INTEGER,
    recipe_id INTEGER,
    FOREIGN KEY (uom_id) REFERENCES unit_of_measure (id) ON DELETE SET NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE
);

-- Table for UnitOfMeasure (only create if it does not exist)
CREATE TABLE IF NOT EXISTS unit_of_measure (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Use INTEGER, will be treated as BIGINT in application
    description TEXT
);

-- Table for Notes (only create if it does not exist)
CREATE TABLE IF NOT EXISTS notes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Use INTEGER, will be treated as BIGINT in application
    recipe_id INTEGER,
    recipe_notes TEXT,
    FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE
);

-- Table for Student (only create if it does not exist)
CREATE TABLE IF NOT EXISTS student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,  -- Auto-incremented ID for Student
    name TEXT NOT NULL,
    email TEXT NOT NULL
);