
# Relatório - Criptografia AES-256 com Node.js

## 1. Introdução Teórica

### 🔐 Fundamentos do AES (Advanced Encryption Standard)

O AES é um algoritmo de criptografia simétrica (usa a mesma chave para criptografar e descriptografar) amplamente utilizado no mundo todo para proteger dados sensíveis.

### ➕ Estrutura de Bloco no AES-256

- **Tamanho do bloco:** 128 bits (ou seja, 16 bytes).  
*(Significa que o AES processa os dados em blocos de 16 bytes de cada vez.)*

- **Tamanho da chave:** 256 bits (ou seja, 32 bytes).  
*(Quanto maior a chave, mais segura a criptografia. A versão AES-256 usa uma chave bem robusta com 32 bytes.)*

- **Número de rodadas:** 14 rodadas.  
*(Cada rodada é um ciclo de operações matemáticas que transformam os dados, aumentando a segurança.)*

🔸 *Por que 14 rodadas?*  
Quanto maior o tamanho da chave, mais rodadas são necessárias para garantir segurança.  
- AES-128 → 10 rodadas  
- AES-192 → 12 rodadas  
- AES-256 → 14 rodadas  

### 🔁 Ciclo de Criptografia

Cada rodada (exceto a última) no AES tem quatro etapas principais:

1. **SubBytes**  
*(Substituição dos bytes usando uma tabela chamada "S-box", que embaralha os dados de forma não linear.)*

2. **ShiftRows**  
*(Deslocamento das linhas da matriz de dados, para criar difusão — ou seja, espalhar os dados pelo bloco.)*

3. **MixColumns**  
*(Mistura os dados dentro de cada coluna, aplicando operações matemáticas.)*

4. **AddRoundKey**  
*(Cada bloco é combinado com uma subchave derivada da chave principal usando a operação XOR.)*

### 🗝️ Expansão de Chave (Key Schedule)

O AES-256 transforma a chave principal de 256 bits em um conjunto de **15 subchaves**: uma para cada rodada mais uma inicial.

Esse processo gera variações da chave original, aplicando funções matemáticas e substituições, garantindo que cada rodada use uma chave única, dificultando qualquer tentativa de ataque.

### 🔗 Modo de Operação - **CBC (Cipher Block Chaining)**

O AES puro criptografa apenas um único bloco de 16 bytes. Para dados maiores, usamos um *modo de operação*.

Escolhi o modo **CBC** (Encadeamento de Blocos de Cifra) porque:

- 🔐 **Garante confidencialidade:** Cada bloco de texto é combinado com o bloco anterior antes de ser criptografado. Isso impede que blocos idênticos resultem em cifras idênticas, aumentando a segurança.

- 🔑 **Uso de IV (Vetor de Inicialização):** O primeiro bloco usa um IV aleatório que garante que criptografias diferentes sejam geradas mesmo com o mesmo texto.

❌ **Por que não usei outros modos?**
- **CTR:** Funciona como um contador, permite paralelismo, mas não oferece autenticação.
- **GCM:** Além da confidencialidade, oferece integridade e autenticação, porém é mais complexo e não era necessário para o objetivo deste projeto.

## 2. Exemplo Prático - Código em Node.js

```javascript
// 📦 Importando bibliotecas necessárias
const crypto = require('crypto');

// 🔑 Gerando uma chave AES de 256 bits (32 bytes) e um IV de 16 bytes
const chave = crypto.randomBytes(32); // Chave AES-256
const iv = crypto.randomBytes(16);    // Vetor de Inicialização (IV)

// 🧠 Função para aplicar padding (se necessário, usa PKCS#7 automaticamente)
function criptografar(texto) {
    const cipher = crypto.createCipheriv('aes-256-cbc', chave, iv);
    let criptografado = cipher.update(texto, 'utf-8', 'base64');
    criptografado += cipher.final('base64');
    return criptografado;
}

// 🔓 Função para descriptografar
function descriptografar(criptografado) {
    const decipher = crypto.createDecipheriv('aes-256-cbc', chave, iv);
    let decifrado = decipher.update(criptografado, 'base64', 'utf-8');
    decifrado += decipher.final('utf-8');
    return decifrado;
}

// 🚀 Testando
const textoOriginal = "Mensagem confidencial de exemplo.";
console.log("Texto original:", textoOriginal);

const cifra = criptografar(textoOriginal);
console.log("Texto criptografado (Base64):", cifra);

const textoDescriptografado = descriptografar(cifra);
console.log("Texto descriptografado:", textoDescriptografado);
```

## 🔧 ✔️ Como o código foi configurado:

- **Algoritmo:** AES-256-CBC
- **Tamanho da chave:** 256 bits (32 bytes)
- **Tamanho do bloco:** 128 bits (16 bytes)
- **Modo:** CBC
- **IV:** Gerado aleatoriamente na execução

- **Padding:** Automático, usando PKCS#7 (incluso na função `createCipheriv` do Node.js)

## 3. Prints de Funcionamento

*(Insira aqui seus prints do terminal mostrando o texto original, criptografado e descriptografado.)*

## ✅ Conclusão

Este trabalho demonstra como é possível proteger dados sensíveis utilizando criptografia simétrica AES-256 no modo CBC. Foi possível observar que mesmo dados simples podem ser transformados em cifras ilegíveis, e recuperados corretamente se a chave e o IV forem mantidos em segurança.
