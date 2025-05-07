import { View, Text, Button } from 'react-native';
import { Link } from 'expo-router';

export default function Home() {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Calculadora Geométrica</Text>
      <Link href="/formas" asChild>
        <Button title="Ir para Calculadora" />
      </Link>
    </View>
  );
}
