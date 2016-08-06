#Pearl
----
Pearl is a powerful image caching and loading library. Developers can even save JSON data in the form of objects and can retrieve them later on if they want to display content in offline mode.    
Pearl uses concepts of Google's volley project and some of it's own image optimization algorithms to display the most vibrant and pixel rich image. It's light, cleaner and is maintained by the open source community itself.    
![](https://s32.postimg.org/wjw8qon51/pearl.png)
------    
#Download    
###Using Gradle:
    repositories {
    mavenCentral() // jcenter() works as well because it pulls from Maven Central
    }
    dependencies {
    compile 'com.github.hanuor:pearl:0.0.4'    
    }
**or** simply under dependencies section:   
  
    compile 'com.github.hanuor:pearl:0.0.4'    

### or Using Maven:
    <dependency>
    <groupId>com.github.hanuor</groupId>
    <artifactId>pearl</artifactId>
    <version>0.0.4</version>
    <type>pom</type>
    </dependency>

------
#Documentation

###Loading an Image
For now a premium fade in animation has been integrated into the library. Images will be displayed through fade in animation.

     Pearl.imageLoader(mContext,URLofImage,imageView);  

**where**   
mcontext = Context of the activity/fragment   
URLofImage = Url of image to be downloaded from web   
imageView = Target ImageView   

###Saving a Json object

    Pearl.saveJsonObject(Context context, String jsonObject,String tag);     

**having a problem to convert Json object into a string? Don't worry we've got you covered.**   
Use class PearlJSON:   

    String jsonstring = PearlJSON.convertToSerializableObject(jsonobject);  

###Retrieving a Json String   

    Object retrieve = Pearl.retrieveJsonObject(String tag);     
    String retString = retrieve.toString();     

###Cancel an image load

    Pearl.cancelImageLoad(String urlofImage)    

------
###Compatibility

**Minimum Android SDK**: Pearl requires a minimum API level of **8**.    

---------
###Special thanks to:

Google's volley for android   
Apache httpcore.jar   
Apache httpclient.jar   
Google's gson library     
Rohan Dikshit (Website)     
Shantanu Johri (Android)     

-----------     
###How it looks like?     
![](https://s32.postimg.org/7ijw20a5h/Screenshot_2016_08_06_13_41_24_182_nexus6p_portr.png)
![](https://s8.postimg.org/i6vw11yph/playicon.png)
[Pearl demo app](https://play.google.com/store/apps/details?id=com.hanuor.pearl_demonstration)




---------

###License
Copyright 2016 Hanuor Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


