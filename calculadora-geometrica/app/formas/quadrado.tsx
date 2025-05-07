// app/formas/quadrado.tsx
import { View, Text, TextInput, Button, Image } from 'react-native';
import { useState } from 'react';

export default function Quadrado() {
  const [lado, setLado] = useState('');
  const [area, setArea] = useState<number | null>(null);
  const [perimetro, setPerimetro] = useState<number | null>(null);

  const calcular = () => {
    const ladoNum = parseFloat(lado);
    if (!isNaN(ladoNum)) {
      const areaCalculada = Math.pow(ladoNum, 2);
      const perimetroCalculado = 4 * ladoNum;
      setArea(areaCalculada);
      setPerimetro(perimetroCalculado);
    } else {
      alert('Por favor, insira um valor válido para o lado.');
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', gap: 20, alignItems: 'center' }}>
      
      <Image
        source={require('../../assets/images/quadradoForm.png')}
        style={{width:400, height:200,resizeMode:'contain',marginBottom:10}}
      />

        <View style={{flexDirection: 'row', alignItems: 'center', gap:5}}>
            <Text style={{fontSize:18}}>L =</Text>
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
            placeholder="Lado"
            keyboardType="numeric"
            value={lado}
            onChangeText={setLado}
            />
        </View>

        <View style={{gap:10,alignItems:'center'}}>
            <Button title="Calcular" onPress={calcular} />
            <Button title="Limpar" onPress={() => {
            setLado('');
            setArea(null);
            setPerimetro(null);
            }}/>
        </View>

      {area !== null && perimetro !== null && (
        <View style={{ marginTop: 20 }}>
          <Text style={{ fontSize: 20 }}>Área: {area.toFixed(2)}</Text>
          <Text style={{ fontSize: 20 }}>Perímetro: {perimetro.toFixed(2)}</Text>
        </View>
      )}
    </View>
  );
}
