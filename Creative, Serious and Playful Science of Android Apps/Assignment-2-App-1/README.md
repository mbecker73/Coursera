App #1: "I turned your webpage into an app"
======================
Directions for Assignment #2 taken from Coursera

Requirements:

* For privacy, the package name should not include your real name. Remember to use the format abc.def (lowercase words, separated with periods).
* The app icon and name is up to you but should represent the contents of the app in some simple way.
* The minimum SDK value should be 10.
* Create 4 activities; one for each of the 4 items listed above. Each activity will use a WebView to display the locally-stored files.
Hint: One of these items may need Javascript and DomStorage enabled to function correctly. Some Internet sleuthing may be required to learn how to enable these settings. 
* You will also need to add the INTERNET permission to your app.
* Each activity will have a relevant name and will also appear in the Launcher (HOME) window.
* Constrain each activity to run in portrait orientation only.
* Play your mp3 file only when the user is viewing Jabberwocky otherwise the app should be silent.
* The Jabberwocky activity will also include two buttons beneath the web view:
* The first button will use an Intent to open the Jabberwocky Wikipedia page in an external browser. The second button will use WebView's 'loadURL' method to change the web view to display a picture of your choice. For example, the picture could be a drawing of the frumious beast. Hint: web views can display jpg files directly or you can create a html page that includes an 'img' element. 