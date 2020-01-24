##JRedditDownloader
JRedditDownloader is a cross-platform multi-threaded program utilizing the
Pushshift API to download all images from a Reddit subreddit.
It can be used either as a standalone program ran from the command line or
a library you can use in your own project. As of now the program supports i.redd.it, 
Imgur (single image and albums), Twitter, and any direct file link with many more hopefully on the way.
When used as a library in your own project you may choose to include or exclude any of
the methods. The specific implementations of each downloader are accessible through
static methods, making the library useful for more than just Reddit. 
##
####Dependencies

GSON 
https://github.com/google/gson

Maven
 ```
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.8.6</version>
</dependency>
```
Gradle
```
dependencies {
  implementation 'com.google.code.gson:gson:2.8.6'
}
```

##
####Usage
The Javadoc file covers the library jar and it's usage. As for the standalone jar
It only supports downloading an entire subreddit for now. Here's how to use the it.

```
java -jar JRedditDownloader_Standalone.jar {subreddit} {path} {maximum threads}
```
Max threads is the amount of threads added to the pool for downloading. 
If you have lightning fast internet set this a little higher. 20 seems to be 
a decent spot.

##

Feel free to open an issue if you have a problem or contact me directly.