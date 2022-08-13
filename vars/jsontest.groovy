import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def config = jsonSlurper.parse(new File('../testdata.json'))

print ("${config}")