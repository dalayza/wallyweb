import React from 'react';
import { Button, ScrollView, View, Text } from 'react-native';
import { StackNavigator, SafeAreaView } from 'react-navigation';
const MyHomeScreen = ({ navigation }) => (
  <View>
    <Text>Page SimpleStack</Text>
    <Button onPress={() => navigation.goBack(null)} title="Go back" />
  </View>

);

MyHomeScreen.navigationOptions = {
  title: 'Welcome',
};


const SimpleStack = StackNavigator({
  Home: {
    screen: MyHomeScreen,
  },
});

export default SimpleStack;
