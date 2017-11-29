/* @flow */

import React from 'react';
// import { ScreenOrientation } from 'expo';

// ScreenOrientation.allow(ScreenOrientation.Orientation.ALL);

import {
  Platform,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  Text,
  View,
  Button,
  Image,
} from 'react-native';
import { SafeAreaView, StackNavigator } from 'react-navigation';

import Banner from './Banner';
import Login from './Login';
import SimpleStack from './SimpleStack';

const MainScreen = ({ navigation }) => (
    <View style={styles.wrapper}>
      <View style={styles.container}>
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />          
        <Button 
          title="Cadastre-se"
          color=''
          onPress={()=> navigation.navigate('Register')}
        />
        <Text style={styles.marginText}></Text>
         <Button 
          title="Sou Cadastrado"
          color='#91d300'
          onPress={() => navigation.navigate('Login')}
        />
      </View>
    </View>
);

const ExampleRoutes = {
  Login: {
    name: 'Login Example',
    description: 'A card stack',
    screen: Login,
  },
  SimpleStack: {
    name: 'Stack Example',
    description: 'A card stack',
    screen: SimpleStack,
  },
};

const AppNavigator = StackNavigator(
  {
    ...ExampleRoutes,
    Index: {
      screen: MainScreen,
    },
  },
  {
    initialRouteName: 'Index',
    headerMode: 'none',

    /*
   * Use modal on iOS because the card mode comes from the right,
   * which conflicts with the drawer example gesture
   */
    mode: Platform.OS === 'ios' ? 'modal' : 'card',
  }
);

export default () => <AppNavigator />;

const styles = StyleSheet.create({
  container: {
    // flex: 2,
    // backgroundColor: '#212121',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  wrapper: {
    backgroundColor: '#212121',
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  text: {
    color: '#fff'
  },
  text_right: {
    textAlign: 'right' 
  },
  formControl:{
    height: 40, 
    borderColor: '#fff', 
    borderWidth: 1,
    color: '#fff',
    textAlign: 'center',
    marginBottom: 15
  },
  formButton:{
    backgroundColor: 'red',
    borderRadius: 10,
    padding: 10,
    marginBottom: 15,
    shadowColor: '#000000',
    shadowOffset: {
      width: 0,
      height: 3
    },
    shadowRadius: 10,
    shadowOpacity: 0.25
  },
  btnDefault:{
    width: 300,
    borderRadius: 10,
    marginBottom: 15,
  },
  logoMacro:{
    width: 250,
    height: 44,
    marginBottom: 30
  },
  marginText:{
    marginBottom: 15
  }
});