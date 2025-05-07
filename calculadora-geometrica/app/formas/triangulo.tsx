import { View, Text, TextInput, Button, Image, ScrollView } from 'react-native';
import { useState } from 'react';

export default function Triangulo() {
  const [ladoA, setLadoA] = useState('');
  const [ladoB, setLadoB] = useState('');
  const [ladoC, setLadoC] = useState('');
  const [base, setBase] = useState('');
  const [altura, setAltura] = useState('');
  const [area, setArea] = useState<number | null>(null);
  const [perimetro, setPerimetro] = useState<number | null>(null);
  const [tipo, setTipo] = useState<string | null>(null);

  const calcular = () => {
    const a = parseFloat(ladoA);
    const b = parseFloat(ladoB);
    const c = parseFloat(ladoC);
    const baseNum = parseFloat(base);
    const alturaNum = parseFloat(altura);

    if ([a, b, c].some(val => isNaN(val))) {
      alert('Por favor, preencha todos os lados corretamente.');
      return;
    }

    if (a + b <= c || a + c <= b || b + c <= a) {
      alert('Os lados informados não formam um triângulo válido.');
      setArea(null);
      setPerimetro(null);
      setTipo(null);
      return;
    }

    const p = a + b + c;
    setPerimetro(p);

    if (!isNaN(baseNum) && !isNaN(alturaNum)) {
      setArea((baseNum * alturaNum) / 2);
    } else {
      const s = p / 2;
      const areaCalculada = Math.sqrt(s * (s - a) * (s - b) * (s - c));
      setArea(areaCalculada);
    }

    if (a === b && b === c) {
      setTipo('Equilátero');
    } else if (a === b || a === c || b === c) {
      setTipo('Isósceles');
    } else {
      setTipo('Escaleno');
    }
  };

  const limpar = () => {
    setLadoA('');
    setLadoB('');
    setLadoC('');
    setBase('');
    setAltura('');
    setArea(null);
    setPerimetro(null);
    setTipo(null);
  };

  return (
    <ScrollView contentContainerStyle={{ padding: 20, alignItems: 'center', gap: 20 }}>
      <Image
        source={require('../../assets/images/trianguloForm.png')}
        style={{ width: 400, height: 200, resizeMode: 'contain' }}
      />

      <CampoRotulado label="Lado A" valor={ladoA} aoMudar={setLadoA} />
      <CampoRotulado label="Lado B" valor={ladoB} aoMudar={setLadoB} />
      <CampoRotulado label="Lado C" valor={ladoC} aoMudar={setLadoC} />
      <CampoRotulado label="Base" valor={base} aoMudar={setBase} />
      <CampoRotulado label="Altura" valor={altura} aoMudar={setAltura} />

      <View style={{ gap: 10, marginTop: 20, width: '50%' }}>
        <Button title="Calcular" onPress={calcular} color="#007AFF" />
        <Button title="Limpar" onPress={limpar} color="#007AFF" />
      </View>

      {area !== null && perimetro !== null && (
        <View style={{ marginTop: 30, alignItems: 'center' }}>
          <Text style={{ fontSize: 20 }}>Área: {area.toFixed(2)}</Text>
          <Text style={{ fontSize: 20 }}>Perímetro: {perimetro.toFixed(2)}</Text>
          <Text style={{ fontSize: 20 }}>Tipo: {tipo}</Text>
        </View>
      )}
    </ScrollView>
  );
}

function CampoRotulado({ label, valor, aoMudar }: { label: string, valor: string, aoMudar: (text: string) => void }) {
  return (
    <View style={{ flexDirection: 'row', alignItems: 'center', gap: 10 }}>
      <Text style={{ width: 80, fontSize: 18 }}>{label}:</Text>
      <TextInput
        style={inputEstilo}
        keyboardType="numeric"
        value={valor}
        onChangeText={aoMudar}
      />
    </View>
  );
}

const inputEstilo = {
  height: 40,
  width: 120,
  borderColor: 'gray',
  borderWidth: 1,
  paddingHorizontal: 10,
  fontSize: 16,
  borderRadius: 5,
  textAlign: 'center' as const,
};
