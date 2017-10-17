import React from 'react';
import { StyleSheet, Text, View, ScrollView, Image, TextInput, Button, TouchableHighlight , Alert, Switch, Slider, Picker, Modal, ListView, WebView } from 'react-native';
import { StackNavigator,} from 'react-navigation';



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


export default class App extends React.Component {
  state = {
    counter: 0,
    textLogin: 'Usuário',
    textNome: 'Nome',
    textEmail: 'Email',
    textSenha: 'Senha',
    switchValue: true,
    language: 'Item 1',
    open: false
  }

  constructor(props){
    super(props);
    setInterval(()=>{this.increment();}, 1000);

    // const ds = new ListView.DataSource({rowHasChanged: (r1,r2) => r1 !== r2});
    // this.state = {
    //   dataSource: ds.cloneWithRows([
    //     {source: {uri: "http://lorempixel.com/300/200"}},
    //     {source: {uri: "http://lorempixel.com/300/200"}},
    //     {source: {uri: "http://lorempixel.com/300/200"}},
    //     {source: {uri: "http://lorempixel.com/300/200"}},
    //   ])
    // };
  }

  increment(){
    this.setState({counter: this.state.counter+1});
  }

  render() {
    return (  

      <View style={styles.wrapper}>
        <View style={styles.container}>
    {/* Tela Inicial */}
        {/*
          <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />
          
          <Button 
            title="Cadastre-se"
            color=''
            onPress={()=>{}}
          />
          <Text style={styles.marginText}></Text>

          <Button 
            title="Sou Cadastrado"
            color='#91d300'
            onPress={()=>{}}
          />
        */}

        {/* Tela de Login */}
        {/*
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />

        <TextInput
            style={styles.formControl}
            placeholder={this.state.textLogin}
            placeholderTextColor="gray"
          />
        <TextInput
            style={styles.formControl}
            placeholder={this.state.textSenha}
            placeholderTextColor="gray"
            secureTextEntry={true}
          />
        </View>
        <Button 
            style={styles.btnDefault}
            title="Entrar"
            color=''
            onPress={()=>{}}
          />
          */}

        {/* Tela de Cadastro */}
        
        <Image source={{uri: 'http://agenciamacro.com.br/wp-content/uploads/2015/01/Logo_MACRO_light.png'}} style={styles.logoMacro} />

        <TextInput
            style={styles.formControl}
            placeholder={this.state.textLogin}
            placeholderTextColor="gray"
          />
        <TextInput
            style={styles.formControl}
            placeholder={this.state.textEmail}
            placeholderTextColor="gray"
        />
        <TextInput
            style={styles.formControl}
            placeholder={this.state.textSenha}
            placeholderTextColor="gray"
            secureTextEntry={true}
          />
        </View>
        <Button 
            style={styles.btnDefault}
            title="Cadastrar"
            color=''
            onPress={()=>{}}
        />        

      </View>
    );
  }
}

