## Android-UWAPIWrapper ##

Support Android SDK 8 and above

Created by Bo Yin, UW Mobile Club.

This wrapper is for students that want to integrate University of Waterloo Open Data API into their apps.
Website: api.uwaterloo.ca

NO WARRANTY. This simple wrapper is for demo only. Feel free to do whatever you want with it.
May explode if heated. Do not burn.

## Usage Instructions ##
To use this wrapper in your projects, follow these steps:

1. Add UWAPIWrapper.jar your project's lib folder

2. If your project doesn't already have permission to access the internet, add the android.permission.INTERNET permission to your project's AndroidManifest.xml

3. Have whatever class you want to handle the results implement the UWAPIWrapperListener interface. For example:
```
    public class MyListenerClass extends Activity implements UWAPIWrapperListener {
        ...
        public void onUWAPIRequestComplete(JSONObject result, boolean success) {
            // Do stuff with the results
        }
        ...
    }
```

4. Create a UWAPIWrapper object with the listener:
```
    UWAPIWrapper apiWrapper = new UWAPIWrapper(listener);
```

5. Use the wrapper's callService() method to make API calls. For example:
```
    // Calls the course search service with query "CS 135"
    apiWrapper.callService("CourseSearch", "CS 135");
    // Calls the OMGUW service with no query
    apiWrapper.callService("OMGUW");
```

6. The api wrapper will invoke the listener's onUWAPIRequestComplete() method after the request is complete.