

import React from 'react';
import { StyleSheet, Text, View, ScrollView, Image, TextInput, Button, TouchableHighlight, Linking, Alert, Switch, Slider, Picker, Modal, ListView, WebView, ImageBackground } from 'react-native';
import {Column as Col, Row} from 'react-native-flexbox-grid';
import { StackNavigator } from 'react-navigation';

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
  
  {/* Tela Inicial */}
  const HomeScreen = ({ navigation }) => (
    <View style={styles.wrapper}>
      <View style={styles.container}>
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />          
        <Button 
          title="Cadastre-se"
          color=''
          onPress={()=> navigation.navigate('RegisterScreen')}
        />
        <Text style={styles.marginText}></Text>
         <Button 
          title="Sou Cadastrado"
          color='#91d300'
          onPress={() => navigation.navigate('LoginScreen')}
        />
      </View>
    </View>
  );

  {/* Tela de Login */}
  const LoginScreen = () => (
    <View style={styles.wrapper}>
      <View style={styles.container}>
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />

        <TextInput
            style={styles.formControl}
            placeholder="Login"
            placeholderTextColor="gray"
          />
        <TextInput
            style={styles.formControl}
            placeholder="Senha"
            placeholderTextColor="gray"
            secureTextEntry={true}
            
          />
        <Button 
            style={styles.btnDefault}
            title="Entrar"
            color=''
            onPress={() => navigation.navigate('LeadListScreen')}
          />
      </View>
    </View>    
  );

  {/* Tela de Cadastro */}
  const RegisterScreen = () => (
    <View style={styles.wrapper}>
      <View style={styles.container}>
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />

        <TextInput
            style={styles.formControl}
            placeholder="Login"
            placeholderTextColor="gray"
          />
        <TextInput
            style={styles.formControl}
            placeholder="Email"
            placeholderTextColor="gray"
        />
        <TextInput
            style={styles.formControl}
            placeholder="Senha"
            placeholderTextColor="gray"
            secureTextEntry={true}
          />
        <Button 
            style={styles.btnDefault}
            title="Cadastrar"
            color=''
            onPress={()=>{}}
        />       
      </View>
    </View>   
  );

  const RootNavigator = StackNavigator({
    HomeScreen: {
      screen: HomeScreen,
      navigationOptions: {
        header: null
      }
    },
    LoginScreen: {
      screen: LoginScreen,
      navigationOptions: {
        header: null
      }
    },
    RegisterScreen: {
      screen: RegisterScreen,
      navigationOptions: {
        header: null
      }
    },
  });

export default RootNavigator;






