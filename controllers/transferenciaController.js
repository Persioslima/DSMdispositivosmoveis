const { criptografar, descriptografar } = require('../utils/crypto');

let saldo = 5000;

function criptografarDados(req, res) {
  const { destino, valor } = req.body;
  const cipherDestino = criptografar(destino);
  const cipherValor = criptografar(valor);
  res.json({ cipherDestino, cipherValor });
}

function descriptografarDados(req, res) {
  const { cipherDestino, cipherValor } = req.body;
  try {
    const destino = descriptografar(cipherDestino);
    const valor = descriptografar(cipherValor);
    res.json({ destino, valor });
  } catch {
    res.json({ erro: 'Falha na descriptografia' });
  }
}

function transferir(req, res) {
  const { destino, valor } = req.body;
  const valorNum = parseFloat(valor);

  if (isNaN(valorNum) || valorNum <= 0) {
    return res.json({ sucesso: false, mensagem: 'Valor inválido.' });
  }

  if (valorNum > saldo) {
    return res.json({ sucesso: false, mensagem: 'Saldo insuficiente.' });
  }

  saldo -= valorNum;

  res.json({
    sucesso: true,
    mensagem: `Transferência de R$ ${valorNum.toFixed(2)} para ${destino} realizada com sucesso! Saldo atual: R$ ${saldo.toFixed(2)}.`
  });
}

function transferirVulneravel(req, res) {
  const { valor } = req.body;
  const valorNum = parseFloat(valor);

  if (isNaN(valorNum) || valorNum <= 0) {
    return res.json({ mensagem: 'Valor inválido.' });
  }

  if (valorNum > saldo) {
    return res.json({ mensagem: 'Saldo insuficiente.' });
  }

  saldo -= valorNum;

  const destinoMalicioso = "&lt;script&gt;CONTA DO INVASOR&lt;/script&gt;";

  res.json({
    mensagem: `Transferência de R$ ${valorNum.toFixed(2)} para ${destinoMalicioso} realizada com sucesso! Saldo atual: R$ ${saldo.toFixed(2)}.`
  });
}

module.exports = {
  criptografarDados,
  descriptografarDados,
  transferir,
  transferirVulneravel
};
