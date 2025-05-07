// app/formas/circulo.tsx
import { View, Text, TextInput, Button, Image } from 'react-native';
import { useState } from 'react';

export default function Circulo() {
  const [raio, setRaio] = useState('');
  const [area, setArea] = useState<number | null>(null);
  const [perimetro, setPerimetro] = useState<number | null>(null);

  const calcular = () => {
    const raioNum = parseFloat(raio);
    if (!isNaN(raioNum)) {
      const areaCalculada = Math.PI * Math.pow(raioNum, 2);
      const perimetroCalculado = 2 * Math.PI * raioNum;
      setArea(areaCalculada);
      setPerimetro(perimetroCalculado);
    } else {
      alert('Por favor, insira um valor válido para o raio.');
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', gap: 20, alignItems: 'center', paddingHorizontal: 20 }}>
      

      <Image
        source={require('../../assets/images/circuloForm.png')}
        style={{ width: 400, height: 200, resizeMode: 'contain', marginBottom: 10 }}
      />

      <View style={{flexDirection: 'row', alignItems: 'center', gap:5}}>
        <Text style={{fontSize:18}}>r =</Text>
        <TextInput
          style={{
            height: 40,
            borderColor: 'gray',
            borderWidth: 1,
            width: 200,
            borderRadius: 6,
            paddingHorizontal: 10,
            textAlign: 'center',
          }}
          placeholder="Digite o raio"
          keyboardType="numeric"
          value={raio}
          onChangeText={setRaio}
        />
      </View>

      <View style={{gap:10,alignItems:'center'}}>
        <Button title="Calcular" onPress={calcular} />
        <Button title="Limpar" onPress={() => {
          setRaio('');
          setArea(null);
          setPerimetro(null);
        }}/>
      </View>

      {area !== null && perimetro !== null && (
        <View style={{ marginTop: 20, alignItems: 'center' }}>
          <Text style={{ fontSize: 20 }}>Área: {area.toFixed(2)}</Text>
          <Text style={{ fontSize: 20 }}>Perímetro: {perimetro.toFixed(2)}</Text>
        </View>
      )}
    </View>
  );
}
