# Toaster Simulation
## Background

This project aims to simulate the process of converting bread to toast? ***SUPER EXCITING RIGHT??***
enclosed you will see a series of classes, each of which control some functionality of the toaster and or bread objects. 

## Instructions For Users
Are you sick of making real bread? Having to wait real time? Well ***you're*** in ***luck!!!!*** 
Follow the steps provided and you will have bagel's toaster fasther than you can explain ***inheritance!!***

Step1: Download the code off o GitHub 

Step2: Open the code in Visual Studio Code
    Upon Opening the program you will notice 4 classes in the Source folder and 4 image files in the Res folder
    To run the program, open the ToasterSimulation class and hit the 'Run' button in the top right corner

Step 3: Begin toasting 
    IMPORTANT: It is important that as a user you understand that this program is still a work in progress. Due to time constraints my team and I were not able to implement
    all of the ideas we wanted to add to our program. We would have liked to add an optional secong bagel to the user interface and the ability for the user to add condiments to the toast.
    Nevertheless, the program that we are presenting to you does fulfil the basic requirement of creating a toaster that actually toasts under certain conditions. If we were afforded more time and the mental capacity to continue with this program after an awfully long and stressful module we would certainly spend more time diagnosing and fixing bugs that exist in the program.

    A few things to keep in mind while using the program:
        - When you drag the bagel and the lever on the screen it is important that you keep the cursor inside of the object you want to drag, our drag methods work by constantly identifying the object that is being clicked on by the cursor. Therefore, if you click and drag an object and keep the cursor inside the shape of the object, the drag will work properly, but if you move your cursor too fast and surpass the bounds of the object the mouse will no longer interact with the selected object because it does not correlate with the position of the cursor

        - When inserting the object into the toaster there may or may not be a weird bouncing affect that occurs on the bagel. This occurs because of the way we have sliced our toaster image
        and made it impossible to move the bagel below the top slit to avoid other weird bugs that occur when you try to drag the lever and the bagel when theyre in the same position. 

        - Once you place the bagel in the toaster the user can not remove the bagel from the toaster until they have toasted the bread. 

    The Steps below detail how the user can get the program to work properly in its current form, enjoy.
## Steps:
Steps: 
1) Grab and drag the bagel to the first slot from the top
2) Once inserted, enter an integer to represent toast time, in seconds, and ***press*** "Set Time"
    A reasonable range of times for our program ranges from 0-10 seconds
    You can toast it longer but just like in real life if you toast it too long, you will burn the toast, but hey, to eaches own.....
3) After pressing "Set Time" click and drag the toaster lever to its maximum Y position
    You will know you have reached the maximum Y position when because the canvas will pause, simulating the fact that it is now toasting(at the bottom)
4) Now wait for your ***AMAZZZZING*** Bagel to come out freshly toasted
    Most of the time you can remove the bagel from the toaster and drag it around the screen as you please, other times, the bagel will respawn in the toaster after being toasted, if this occurs the user may restart the program if they choose or re-toast the bread for another specified time and the bagel will re appear another color, there is not guarantee that once you re-toast the bagel that you will be able to move it, this is a bug we have not yet completely diagnosed and certainly have not fixed
5) If you dont like the outcome of your bagel, feel free to reinsert it and add more time to your bagel

## Ending
If by the end of this you aren't satisfied with the amount of time you ***saved*** not waiting for real bread, try inputting 127 for set time to unlock a cool easter egg. Hopefully you'll enjoy this toast simulation.

## Credits: 
***Emiliano Huerta***, ***Victor Wright***, ***Jonny Xue***