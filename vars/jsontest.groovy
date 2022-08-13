import groovy.json.JsonSlurper

config = [
  APM : '',
  BSN : '',
  CONTACT : ''
]
def jsonSlurper = new JsonSlurper()
def configjson = jsonSlurper.parse(new File('../testdata.json'))

config.APM = configjson['APM']
config.BSN = configjson['BSN']
config.CONTACT = "venugopal.k@greencity.com"
print ("${config}")