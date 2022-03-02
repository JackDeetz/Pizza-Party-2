# Pizza-Party-2
Lab 2 Assignment
Overview

In this lab, you are adding 5 additional features to the Pizza Party app.  See ZyBooks chapters 2-4.

Instructions

Complete any 5 of the following 10 features: (20 points each)

Add string resources
Pizza Party's activity_main.xml currently contains a number of hard-coded strings that belong in strings.xml. Use the "Extract string resource" functionality of Android Studio to create string resources for all strings used in android:hint and android:text attribute
Change the primary colors
Add dimension resources
Add a background image
MVC interaction
The Pizza Party app is not currently using MVC architecture. The business logic is the code concerned with calculating the number of pizzas required, and the calculations are integrated into the activity. Modifying the Pizza Party app to use MVC requires:
Use the ConstraintLayout
Replace the Pizza Party's LinearLayout with a ConstraintLayout. Use a baseline constraint to align the "Number of people?" TextView with the EditText with ID num_attend_edit_text.
Add a TextWatcher
Add a TextWatcher to Pizza Party's numAttendEditText. The TextWatcher should set numAttendEditText's text to an empty string as soon as the user starts typing the number of people. Hint: The TextWatcher should be added in MainActivity's onCreate() method.
numAttendEditText.addTextChangedListener(
   // Add the missing code here
})
Add a Style
Add a style to Pizza Party that modifies the appearance of the Calculate button
Modify the theme
Android Studio's Layout Editor allows the developer to see how different themes would modify the appearance of an activity's UI without having to run the activity on an emulator.
Change Pizza Party's activity_main.xml theme preview
Save "total pizzas" value when device is rotated
Note: You are welcome to do more than 5 from this list. I will only count the 5 highest scored items.

Create a video demo (required)

Create a video demo of your app running in the Android Studio emulator. Be sure to demonstrate the app you built, and the 5 features you added to your app, and how they work.  You can record your screen with our phone, or use free software to capture your screen such as Jing (Links to an external site.), or Screencast-O-Matic (Links to an external site.). Please show all parts of the app, and explain anything that is not obvious. 

What to submit

A list describing which 5 items you completed. You can list it out in the comment box when submitting, or attach a Text or Word file.
The URL of your app's GitHub repository.
The URL of your video demo.
Note: You will get a grade of 0 on this lab assignment if a video demo is not included with your submission
