
# 🔐 Projeto AES-256 Criptografia e Transferência Segura

Este projeto demonstra o funcionamento da criptografia simétrica utilizando **AES-256** no modo **CBC**, além de ilustrar, na prática, conceitos de segurança como **criptografia, vulnerabilidades XSS** e manipulação segura de dados em APIs web.

## 🗂️ Estrutura do Projeto

```
📁 projeto-aes256
├── 📁 public
│   ├── index.html
│   ├── transferencia.html
│   ├── transferencia_vulneravel.html
│   └── styles.css
├── 📜 server.js
├── 📜 package.json
├── 📜 Relatorio_AES256.md
└── 📜 README.md
```

## 🚀 Tecnologias Utilizadas

- **Node.js** (Runtime JavaScript)
- **Express** (Framework de servidor web)
- **Crypto (Node.js)** (Biblioteca nativa para criptografia)

## 📦 Instalação e Execução

### 🔧 Pré-requisitos

- Ter o **Node.js** instalado (https://nodejs.org)

### 💻 Instalar dependências

```bash
npm install
```

### ▶️ Rodar o servidor

```bash
node server.js
```

O servidor estará disponível em:

```
http://localhost:3000
```

## 🛠️ Funcionalidades

- 🔐 **Criptografia AES-256 em modo CBC**
- 🔓 **Descriptografia AES-256**
- 💸 **Transferência de valores simulada**
- ⚠️ **Demonstração de uma vulnerabilidade XSS para fins educacionais**

## 🔗 Rotas da API

### ✅ Rota principal (Interface)

```http
GET /
```

Abre a página inicial.

### 🔐 Criptografar dados

```http
POST /criptografar
```

**Body:**
```json
{
  "destino": "João",
  "valor": "1000"
}
```

**Resposta:**
```json
{
  "cipherDestino": "Base64...",
  "cipherValor": "Base64..."
}
```

### 🔓 Descriptografar dados

```http
POST /descriptografar
```

**Body:**
```json
{
  "cipherDestino": "...",
  "cipherValor": "..."
}
```

**Resposta:**
```json
{
  "destino": "João",
  "valor": "1000"
}
```

### 💸 Transferir (Seguro)

```http
POST /transferir
```

Faz a simulação de uma transferência, validando saldo e entrada.

### ⚠️ Transferir (Vulnerável a XSS)

```http
POST /transferir_vulneravel
```

Inclui código malicioso simulando um ataque XSS. **Atenção: uso apenas para fins educacionais.**

## 🔒 Segurança

Este projeto demonstra na prática como proteger dados sensíveis com **AES-256 em modo CBC**, além de como uma má validação pode gerar vulnerabilidades, como **XSS (Cross-Site Scripting)**.

> ⚠️ Atenção: As chaves e IV são gerados de forma simplificada para demonstração. Em aplicações reais, devem ser armazenados e gerenciados de forma segura.

## 📄 Documentação Teórica

A explicação completa sobre AES-256, modos de operação, funcionamento interno e exemplos está no arquivo:

- [`Relatorio_AES256.md`](./Relatorio_AES256.md)

## 🧠 Autor

- **Pérsio de Souza Lima**
- Curso: Tecnólogo em Desenvolvimento de Software Multiplataforma - FATEC Praia Grande
- 🌎 Local: Praia Grande - SP
- 📅 Ano: 2025

## 📜 Licença

Este projeto tem fins educacionais e pode ser utilizado para aprendizado.
