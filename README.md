## Cams data aggregator  
[![Build Status](https://travis-ci.org/amasterenko/job4j_cams_aggregator.svg?branch=master)](https://travis-ci.org/amasterenko/job4j_cams_aggregator)
[![codecov](https://codecov.io/gh/amasterenko/job4j_cams_aggregator/branch/master/graph/badge.svg?token=6D9M1VVC7M)](https://codecov.io/gh/amasterenko/job4j_cams_aggregator)  
___

### Task

It is necessary to write a program for collecting and aggregating cams data from several web services.  

The list of cams is available at the following URL:  
http://www.mocky.io/v2/5c51b9dd3400003252129fb5  

>[  
{  
"id": 1,  
"sourceDataUrl": "http://www.mocky.io/v2/5c51b230340000094f129f5d",  
"tokenDataUrl": "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s"  
},  
{  
"id": 20,  
"sourceDataUrl": "http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms",  
"tokenDataUrl": "http://www.mocky.io/v2/5c51b5ed340000554e129f7e"  
},  
{  
"id": 3,  
"sourceDataUrl": "http://www.mocky.io/v2/5c51b4b1340000074f129f6c",  
"tokenDataUrl": "http://www.mocky.io/v2/5c51b600340000514f129f7f?mocky-delay=2s"  
},  
{  
"id": 2,  
"sourceDataUrl": "http://www.mocky.io/v2/5c51b5023400002f4f129f70",  
"tokenDataUrl": "http://www.mocky.io/v2/5c51b623340000404f129f82"  
}  
]  

The format of the response:   
● id - number, cam ID.  
● sourceDataUrl - string, link to the cam source data.  
● tokenDataUrl - string, link to the cam security token.  

The format of the response to _sourceDataUrl_ request:  
● urlType - string, type of link to video-stream. Possible values: "LIVE",
"ARCHIVE".      
● videoUrl - string, link to video-stream.    

The format of the response to _tokenDataUrl_ request:  
● ttl - number, time to live of the token.

The goal is to aggregate data for each cam.  

The expected result:  
>[  
{  
"id": 1,  
"urlType": "LIVE",  
"videoUrl": "rtsp://127.0.0.1/1",  
"value": "fa4b588e-249b-11e9-ab14-d663bd873d93",  
"ttl": 120  
},  
{  
"id": 3,  
"urlType": "ARCHIVE",  
"videoUrl": "rtsp://127.0.0.1/3",  
"value": "fa4b5d52-249b-11e9-ab14-d663bd873d93",  
"ttl": 120  
},  
{  
"id": 20,  
"urlType": "LIVE",  
"videoUrl": "rtsp://127.0.0.1/20",  
"value": "fa4b5f64-249b-11e9-ab14-d663bd873d93",  
"ttl": 180  
},  
{  
"id": 2,  
"urlType": "ARCHIVE",  
"videoUrl": "rtsp://127.0.0.1/2",  
"value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",  
"ttl": 60  
}  
]  

When writing code, you need to consider potentially large amounts of data.  
The collecting and aggregating must be performed in several threads and being
blocked as little as possible (on I/O operations or during awaiting).  
The solution must be published on GitHub and has unit tests.  

### Used technologies  
* Java Core
* JUnit
* JaCoCo
* Codecov
* Travis CI

### Usage  
1. Build the project with Maven: ```mvn clean package```.  
2. Run the app: ```java -jar target/cams.jar http://www.mocky.io/v2/5c51b9dd3400003252129fb5```,  
