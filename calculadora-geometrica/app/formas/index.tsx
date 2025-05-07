import { Link } from 'expo-router';
import { FlatList, Image, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

const formas = [
  { nome: 'Círculo', imagem: require('../../assets/images/circulo.png'), href: '/formas/circulo' },
  { nome: 'Retângulo', imagem: require('../../assets/images/retangulo.png'), href: '/formas/retangulo' },
  { nome: 'Quadrado', imagem: require('../../assets/images/quadrado.png'), href: '/formas/quadrado' },
  { nome: 'Triângulo', imagem: require('../../assets/images/triangulo.png'), href: '/formas/triangulo' },
];

export default function SelecionarForma() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>CALCULADORA GEOMÉTRICA</Text>
      <Text style={styles.subtitle}>Escolha a Forma</Text>
      <FlatList
        data={formas}
        keyExtractor={(item) => item.href} // Usando href como chave única
        numColumns={2}
        contentContainerStyle={styles.list}
        renderItem={({ item }) => (
          <Link href={item.href} asChild>
            <TouchableOpacity style={styles.card}>
              <Image source={item.imagem} style={styles.image} resizeMode="contain" />
              <Text style={styles.cardText}>{item.nome}</Text>
            </TouchableOpacity>
          </Link>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    alignSelf: 'center',
    marginVertical: 20,
  },
  subtitle: {
    fontSize: 18,
    fontWeight: '500',
    alignSelf: 'center',
    marginVertical: 10,
  },
  list: {
    gap: 16,
    justifyContent: 'center',
  },
  card: {
    width: 150, // Ajustando para garantir que os cards fiquem bem distribuídos em 2 colunas
    backgroundColor: '#0093dd',
    margin: 8, // Ajustado o espaçamento para que os cards não fiquem muito distantes
    borderRadius: 12,
    padding: 16,
    alignItems: 'center',
    elevation: 4,
  },
  image: {
    width: 80,
    height: 80,
    marginBottom: 12,
  },
  cardText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#fff',
  },
});
