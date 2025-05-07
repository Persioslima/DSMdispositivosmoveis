// app/formas/retangulo.tsx
import { View, Text, TextInput, Button, Image } from 'react-native';
import { useState } from 'react';

export default function Retangulo() {
  const [largura, setLargura] = useState('');
  const [comprimento, setComprimento] = useState('');
  const [area, setArea] = useState<number | null>(null);
  const [perimetro, setPerimetro] = useState<number | null>(null);

  const calcular = () => {
    const larguraNum = parseFloat(largura);
    const comprimentoNum = parseFloat(comprimento);

    if (!isNaN(larguraNum) && !isNaN(comprimentoNum)) {
      const areaCalculada = larguraNum * comprimentoNum;
      const perimetroCalculado = 2 * (larguraNum + comprimentoNum);
      setArea(areaCalculada);
      setPerimetro(perimetroCalculado);
    } else {
      alert('Por favor, insira valores válidos.');
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', gap: 20, alignItems: 'center' }}>
      
      <Image
        source={require('../../assets/images/retanguloForm.png')}
        style={{width:400, height:200, resizeMode:'contain', marginBottom:10}}
      />
      <View style={{flexDirection: 'row', alignItems: 'center', gap:5}}>
        <Text style={{fontSize:18}}>h =</Text>
        <TextInput
          style={{ height: 40, borderColor: 'gray', borderWidth: 1, width: 200, textAlign: 'center' }}
          placeholder="Largura"
          keyboardType="numeric"
          value={largura}
          onChangeText={setLargura}
        />
      </View>

      <View style={{flexDirection:'row',alignItems: 'center', gap:5}}>  
        <Text style={{fontSize:18}}>b =</Text>      
        <TextInput
          style={{ height: 40, borderColor: 'gray', borderWidth: 1, width: 200, textAlign: 'center' }}
          placeholder="Comprimento"
          keyboardType="numeric"
          value={comprimento}
          onChangeText={setComprimento}
        />
      </View>

      <View style={{gap:10,alignItems:'center'}}>
        <Button title="Calcular" onPress={calcular} />
        <Button title="Limpar" onPress={() => {
          setLargura('');
          setComprimento('');
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
