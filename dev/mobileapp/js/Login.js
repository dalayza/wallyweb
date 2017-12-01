import React from 'react';
import { Button, ScrollView, View, Text, Image, TextInput, StyleSheet } from 'react-native';
import { SafeAreaView, StackNavigator } from 'react-navigation';

import Leads from './Leads';

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

class MyLoginScreen extends React.Component {
  	static navigationOptions = {
	    title: 'Login',
	  };
  	state = {
      inputUserEmail: '',
      inputUserPassword: '',
      data: '',
      statusLogin: '',
    }
    userLogin(){
      if(this.state.inputUserEmail.trim() !== "" && this.state.inputUserPassword.trim() !== ""){
        fetch('https://krushwebapi.appspot.com/session', {
          method: 'POST',
          headers: new Headers({
             "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
          }),
          body: "email="+this.state.inputUserEmail.trim()+"&passwd="+this.state.inputUserPassword.trim()
        })
        .then((response) => response.json())
        .then((responseJson) => {
           console.log(responseJson);
           // alert(JSON.stringify(responseJson));

           this.setState({
              data: responseJson,
              statusLogin: 'in'
           });

           this.props.navigation.navigate('Leads', { email: 'webmasterteste@agenciamacro.com.br' });
        })
        .catch((error) => {
           console.error(error);
        });

      }else{
        alert("Preencha os campos corretamente");
      }
   }

   render() {
      const {navigate} = this.props.navigation;

      return (
        <View style={styles.wrapper}>
          <View style={styles.container}>
            <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />
            <TextInput
                style={styles.formControl}
                placeholder="Email"
                placeholderTextColor="gray"
                value={this.state.inputUserEmail}
                onChangeText={(value) => this.setState({inputUserEmail: value})}
              />
            <TextInput
                style={styles.formControl}
                placeholder="Senha"
                placeholderTextColor="gray"
                secureTextEntry={true}
                value={this.state.inputUserPassword}
                onChangeText={(value) => this.setState({inputUserPassword: value})}                
              />
            <Button 
                style={styles.btnDefault}
                title="Entrar"
                color=''
                onPress={() => {this.userLogin()}}
              />
           
            
            

            {/*
              <Text>Clique no botão abaixo para sair</Text>
            <Button 
              style={styles.btnDefault}
              title="Logout"
              color=''
              onPress={() => {this.userLogout()}}
            />
            <Button 
              title="Leads"
              color='#91d300'
              onPress={() => navigate('Leads')}
            />
            */}
            

          </View>
        </View>
      )
   }
}

const ExampleRoutes = {
  Leads: {
    name: 'Leads',
    description: '',
    screen: Leads,
  },
};

const Login = StackNavigator(
  {
    ...ExampleRoutes,
    Home: {
      screen: MyLoginScreen,
    },
  },
  {
    initialRouteName: 'Home',
    headerMode: 'none',

    /*
   * Use modal on iOS because the card mode comes from the right,
   * which conflicts with the drawer example gesture
   */
    mode: Platform.OS === 'ios' ? 'modal' : 'card',
  }
);

export default Login;