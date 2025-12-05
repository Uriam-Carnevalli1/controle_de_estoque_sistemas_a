# 📦 Inventory Management -- Sistema de Controle de Estoque

Este projeto é um **sistema de controle de estoque** desenvolvido em
**Java 21** com **MySQL**.\
Ele permite:

-   🔐 Login de funcionários\
-   📦 Cadastro de produtos\
-   🚚 Cadastro de fornecedores\
-   👤 Cadastro de funcionários\
-   ➕ Registro de entrada de estoque\
-   📋 Menu com todas as funcionalidades principais

------------------------------------------------------------------------

## 🗂️ Estrutura do Banco de Dados

As tabelas utilizadas no sistema são:

-   **produto**
-   **fornecedor**
-   **funcionario**
-   **entrada_estoque**

------------------------------------------------------------------------

## 🛠️ Tecnologias Utilizadas

-   **Java 21.0.9**
-   **MySQL**
-   **Maven**
-   **NetBeans** (opcional)

------------------------------------------------------------------------

## 📥 Pré-requisitos

Instale os seguintes componentes:

### ✔️ Java

https://www.oracle.com/java/technologies/downloads/

**Instalar Java no Linux (Ubuntu/Debian):**

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```
### ✔️ MySQL

https://dev.mysql.com/downloads/installer/

### ✔️ MySQL Workbench (recomendado)

https://dev.mysql.com/downloads/workbench/

------------------------------------------------------------------------

## 🗄️ Criando o Banco de Dados

1.  Abra o **MySQL Workbench**.\

2.  No projeto localize o arquivo:

```{=html}
criar-banco.txt
```
    

3.  Copie o conteúdo do arquivo e cole no Workbench.\
4.  Execute o script para criar o banco e as tabelas.

------------------------------------------------------------------------

## 🔧 Configuração da Conexão com o Banco

Edite o arquivo:

    inventory-management/src/main/java/persistencia/Conexao.java

Modifique **as linhas 18 a 20** com as informações da sua máquina:



------------------------------------------------------------------------

# ▶️ Compilar e Executar o Projeto

------------------------------------------------------------------------

## 🟦 1. Compilar com Maven (Windows)

Na raiz do projeto, execute:

``` bash
mvn clean package
```

------------------------------------------------------------------------

## 🟧 2. Compilar usando o NetBeans (caso não tenha Maven instalado)

1.  Abra o projeto no NetBeans\
2.  Clique com o botão direito no projeto\
3.  Selecione **Clean and Build**

------------------------------------------------------------------------



------------------------------------------------------------------------

## ▶️ Executar o Sistema

Após biudar, entre na pasta:

``` bash
cd inventory-management/target
```

Execute o arquivo JAR:

``` bash
java -jar executavel.jar
```

------------------------------------------------------------------------
