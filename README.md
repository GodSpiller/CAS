# CAS #

It is assumed you use Intellij

## GUIDE FOR ADDING EXTERNAL LIBRARIES ##

2 libraries from UPPAAL 2.1.24 are needed to run this application.

File -> Project Structure -> Under project settings: 'Libraries' -> click the plus icon -> Java -> Add the following libraries:

model.jar (Found under the 'lib' folder in UPPAAL 2.1.24)

uppaal.jar (Found in the root of UPPAAL 2.1.24)

## SETTING SERVER PATH ##
In the ModelHandler.java, change the engine.setServerPath() to your local path

## CHOOSING MODEL TO TEST ##
In the repository, there are 2 models that are also implemented: CAS 2.3.xml and updown.xml.
In the ModelHandler.java, set the URL variable to the desired model.
UnitTestFactory.java has 2 methods, makeUnitTestsUpDown() and makeUnitTestsCAS().
In Main.java, call the method corresponding to the model you wish to test.
The output of the program is unit tests, the tests are located in the 'test' folder
