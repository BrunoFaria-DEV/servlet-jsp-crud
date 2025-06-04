-- Cria o banco de dados
CREATE DATABASE "servlet-jsp-crud";

-- Depois de executar o comando acima, conecte-se ao banco recém-criado antes de rodar os próximos comandos.

-- Criação das tabelas
CREATE TABLE "municipios"(
    "id" SERIAL PRIMARY KEY,
    "nome" VARCHAR(120) NOT NULL
);

CREATE TABLE "usuarios" (
    "id" SERIAL PRIMARY KEY,
    "nome" VARCHAR(120) NOT NULL,
    "email" VARCHAR(220) NOT NULL,
    "CPF" VARCHAR(14),
    "municipio_id" INTEGER,
    FOREIGN KEY ("municipio_id") REFERENCES "municipios"("id")
);

INSERT INTO "municipios" ("nome") VALUES 
('São Paulo'),
('Rio de Janeiro'),
('Belo Horizonte'),
('Porto Alegre'),
('Curitiba'),
('Brasília'),
('Salvador'),
('Recife'),
('Fortaleza'),
('Manaus'),
('Belém'),
('Goiânia'),
('Campinas'),
('São Luís'),
('Natal'),
('João Pessoa'),
('Teresina'),
('Maceió'),
('Aracaju'),
('Cuiabá'),
('Campo Grande'),
('Florianópolis'),
('Vitória'),
('Uberlândia'),
('Ribeirão Preto'),
('Sorocaba'),
('Juiz de Fora'),
('Niterói'),
('Santo André'),
('São Bernardo do Campo'),
('Osasco'),
('Contagem'),
('Londrina'),
('Joinville'),
('Anápolis'),
('Pelotas'),
('Caxias do Sul'),
('Macapá'),
('Boa Vista'),
('Porto Velho'),
('Rio Branco'),
('Apucarana'),
('Maringá'),
('Cascavel'),
('Itajaí'),
('Blumenau'),
('Chapecó'),
('Feira de Santana'),
('Petrolina'),
('Caruaru');


-- Criação do usuário
CREATE USER "servlet-jsp-crud" WITH PASSWORD 'servlet-jsp-crud';

-- Permissões de conexão ao banco
GRANT CONNECT ON DATABASE "servlet-jsp-crud" TO "servlet-jsp-crud";

-- Permissões no schema público
GRANT USAGE ON SCHEMA public TO "servlet-jsp-crud";

-- Permissões em todas as tabelas existentes
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO "servlet-jsp-crud";

-- Permissões em todas as sequências existentes (necessárias para colunas SERIAL/IDENTITY)
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO "servlet-jsp-crud";

-- Permissões para tabelas futuras no schema public
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "servlet-jsp-crud";

-- Permissões para sequências futuras no schema public
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO "servlet-jsp-crud";
