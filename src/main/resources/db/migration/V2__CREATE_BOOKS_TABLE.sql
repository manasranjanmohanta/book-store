CREATE TABLE books (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       isbn VARCHAR(20) NOT NULL UNIQUE,
       title VARCHAR(255) NOT NULL,
       subtitle VARCHAR(255),
       description TEXT,
       language VARCHAR(50), # English, Hindi, etc.
       edition VARCHAR(50), # First, Second, etc.
       publication_date DATE,
       price DECIMAL(10,2) NOT NULL, # Selling price
       original_price DECIMAL(10,2), # MRP/original price
       pages INT,
       format VARCHAR(30), # Hardcover, Paperback, Ebook
       status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', # ACTIVE/INACTIVE
       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

       publisher_id BIGINT,
       CONSTRAINT fk_book_publisher
           FOREIGN KEY (publisher_id)
               REFERENCES publishers(id)
);