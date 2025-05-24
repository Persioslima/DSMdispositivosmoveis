const crypto = require('crypto');

const chave = crypto.randomBytes(32);
const iv = crypto.randomBytes(16);

function criptografar(texto) {
  const cipher = crypto.createCipheriv('aes-256-cbc', chave, iv);
  let criptografado = cipher.update(texto, 'utf-8', 'base64');
  criptografado += cipher.final('base64');
  return criptografado;
}

function descriptografar(criptografado) {
  const decipher = crypto.createDecipheriv('aes-256-cbc', chave, iv);
  let decifrado = decipher.update(criptografado, 'base64', 'utf-8');
  decifrado += decipher.final('utf-8');
  return decifrado;
}

module.exports = { criptografar, descriptografar };
