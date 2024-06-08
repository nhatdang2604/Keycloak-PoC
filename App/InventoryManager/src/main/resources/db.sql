CREATE TABLE `biz`.`categories` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` TEXT DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`product_category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT DEFAULT NULL,
    `category_id` BIGINT DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`products` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    PRIMARY KEY (`id`)
);

CREATE TABLE `biz`.`attributes` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`product_attribute` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT DEFAULT NULL,
    `attribute_id` BIGINT DEFAULT NULL,
    `value` TEXT DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`skus` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`price` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `price` DECIMAL(14, 4) DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

CREATE TABLE `biz`.`inventories` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `sku_id` BIGINT DEFAULT NULL,
    `price_id` BIGINT DEFAULT NULL,
    `stock_qty` BIGINT DEFAULT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)


