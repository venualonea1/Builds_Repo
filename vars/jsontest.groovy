import groovy.json.JsonSlurper


def call(){
  config = [
  APM : '',
  BSN : '',
  CONTACT : ''
]
def configjson = readJSON file: "testdata"

config.APM = configjson['APM']
config.BSN = configjson['BSN']
config.CONTACT = "venugopal.k@greencity.com"
print ("${config}")
}
