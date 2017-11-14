
const styles = StyleSheet.create({
  container: {
    // flex: 2,
    // backgroundColor: '#212121',
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  wrapper: {
    backgroundColor: '#ffffff',
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  topBar: {
    backgroundColor: '#92d400',
    width: '100%',
    justifyContent: 'center',
    paddingTop: 30,
    height: 70,
  },
  simbolMacroTopBar:{
    alignSelf: 'flex-end', 
  },
  textWhite:{
    color: '#ffffff',   
  },
  textBold:{
    fontWeight: 'bold',
  },
  textCenter:{
    alignSelf: 'center',
    justifyContent: 'center',
  },
  leadWrapper:{
    width: '100%',
    justifyContent: 'center',

  },
  leadRow:{   
    width: '100%',
    borderLeftColor: '#008ad4',
    borderLeftWidth: 5,
    borderLeftStyle: 'solid',
    flex: 1, 
    flexDirection: 'row'
  },
  leadRowOdd: {
    backgroundColor: '#f4f4f4',    
  },
  leadRowEven: {
    backgroundColor: '#ffffff',    
  },
  leadCaption:{
    // display: 'flex',
    // alignSelf: 'flex-start',
    width: '75%',
    color: '#000000',
    paddingTop: 10,
    paddingRight: 10,
    paddingBottom: 10,
    paddingLeft: 10,
    minHeight: 80,
  },
  leadCaptionInfo:{
    color: '#3C3C3B',
    fontSize: 16,
    lineHeight: 26,

  },
  leadOptions:{
    justifyContent: 'flex-end',
    width: '25%',    
  },
  leadContact:{
    width: 75,
    height: 55,
    alignSelf: 'flex-end',
    alignItems: 'center',
    justifyContent: 'center',
  },
  leadContactPhone:{
    backgroundColor: '#92d400'
  },
  leadContactMail:{
    backgroundColor: '#ff9500'
  },
  leadContactIcons:{
    width: 20,
  },
  leadDate:{
    width: '100%',
    height: 40,
    alignSelf: 'center',
    justifyContent: 'center',
  },
  colFormat:{
    padding: 10,
  },
  bottomSpace: {
    width: '100%',
    paddingTop: 30,
    height: 70,    
  },

});


  
  state = {
    leadsList: []
  }

  componentDidMount(){
    this.getLeads()
      .then(responseJson => {
          this.setState({leadsList: responseJson})
      })
  }

  getLeads(){
    return fetch('https://krushweb-183017.appspot.com/leads')
          .then(response => response.json())
          .then((responseJson) => {
            return responseJson;
          })
          .catch((error) => {
            console.error(error);
          });
  }
  
  render() {
    return (
	    <View>
          <View style={styles.topBar}>
              <View style={styles.container}>        
                <Row size={12}>
                  <Col sm={3} style={styles.colFormat}>
                    <Text>
                      Logout
                    </Text>
                  </Col>
                  <Col sm={6} style={styles.colFormat}>
                    <Text style={[styles.textCenter, styles.textWhite, styles.textBold]}>
                       Gestão de Leads
                    </Text>
                  </Col>
                  <Col sm={3} style={styles.colFormat}>
                    <Image source={{uri: 'http://agenciamacro.com.br/mobileapp/assets/logo-macro.png?v=2'}} style={[{width: 27, height: 27}, styles.simbolMacroTopBar]} />
                  </Col>
                </Row>      
              </View>
          </View>
          <ScrollView>

            <View style={styles.leadDate}>
              <Text style={[styles.textBold, styles.textCenter]}>Hoje</Text> 
            </View>

          
            <View style={styles.leadWrapper}>
              {
                this.state.leadsList.map((elem,index) => {
                  return (

                    <View style={[styles.leadRow,{ backgroundColor: (index % 2 == 0) ? '#f4f4f4' : '#fff' }]}>
                      <View style={styles.leadCaption}>
                        <Text style={[styles.leadCaptionInfo, styles.textBold]}>{elem.client_id}</Text>
                        <Text style={styles.leadCaptionInfo}>{elem.phone}</Text>
                        <Text style={styles.leadCaptionInfo}>{elem.email} </Text>
                      </View>
                      <View style={styles.leadOptions}>
                        <TouchableHighlight onPress={() => Linking.openURL('tel:'+elem.phone)} underlayColor="transparent">
                          <View style={[styles.leadContact, styles.leadContactPhone]}>
                            <Image source={{uri: 'http://agenciamacro.com.br/mobileapp/assets/icons/phone.png?v=2'}} style={{width: 30, height: 30}} />
                          </View>
                        </TouchableHighlight>
                        <TouchableHighlight onPress={() => Linking.openURL('mailto:'+elem.email+'?subject=&body=')} underlayColor="transparent">
                          <View style={[styles.leadContact, styles.leadContactMail]}>   
                            <Image source={{uri: 'http://agenciamacro.com.br/mobileapp/assets/icons/mail.png?v=2'}} style={{width: 35, height: 35}} />              
                          </View>
                        </TouchableHighlight>
                      </View>
                    </View>
                  )
                })
              }

            </View>

            <View style={styles.bottomSpace}></View>
            
          </ScrollView>
        </View> 
    );
  }
}
