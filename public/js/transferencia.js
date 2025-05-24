document.getElementById('form-transferencia').addEventListener('submit', async function (e) {
  e.preventDefault();

  const destino = this.destino.value;
  const valor = this.valor.value;

  const resposta = await fetch('/transferir', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ destino, valor }),
  });

  const dados = await resposta.json();

  const resultadoDiv = document.getElementById('resultado');
  resultadoDiv.innerHTML = dados.mensagem;
});
