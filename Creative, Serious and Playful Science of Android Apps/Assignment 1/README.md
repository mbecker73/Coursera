Guidelines for App #1: Two Famous Computer Scientists

Step 1: Research for Your App

Find and pick 2 famous computer scientists (living or dead) that you will include in your app. For each person you will need to include an image and text. Some suggested historical figures are listed below as a starting point. You're not limited to picking one of these, but we encourage learning about them and also trying to find at least 1 famous computer scientist not listed below!

Ada Lovelace
Alan Turing
Grace Hopper
Charles Babbage
John von Neumann
Donald Knuth


Step 2: Create Your App

Requirements:

The app name should include the last names of the 2 computer scientists you chose (see above).
The package name should not include your real name. Remember to use the format abc.def (lowercase words, separated with periods).
The app icon is up to you but should represent the contents of the app in some simple way.
The minimum SDK value should be 8.
Use 2 relative layouts to implement the following:

In portrait orientation, the following should appear in order from top to bottom: 

The first person's full name. The name should be centered.
The first person’s image (as a jpg or png file). This image should be centered.
Descriptive text about this person.

When the phone is flipped from portrait to landscape, the layout should include: 

The second person's name centered in the middle of the display.
The second person’s image. This image should be on the left and underneath the name.
The descriptive text of this person. This text should be to the right of the image and also underneath the name.


Step 3: Refine and Test Your App

Check for the following:

Check the dimensions of your images. The total in-memory size for each image must be less than 2MB (assume each pixel requires 4 bytes and there are 1024×1024 bytes in 1MB).
There should be a small 8dp margin between each item.
Text size must be at least 22sp. For grading purposes, write the text size directly in your layout xml (rather than using a theme or dimension value).
The image must not be too large for small phones.
There must be at least 2 lines of text displayed in portrait view; the image should not be more than half the screen width in landscape view. Hint: Check your layout in different device screens.
If any text is too long for the small screen, then the layout should include a scroll view.
For accessibility purposes, add content description attributes to your images to help partially-sighted and blind users know the content of the images (blind Android users use a text-to-speech tool).
