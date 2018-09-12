# ProjectNSample
 
Documentation for Notification Project
Android Version

Purpose:
In certain formats, by using the notification of Android system to send information to Carloudy Controller and Carloudy HUD will show your customized information.
You can show 1 picture and some texts.
You can customize positions and sizes of the texts and the picture by the detailed parameters attached in the same notification.

Registration:
Please send us the package name of your app (e.g. com.yourapp.android) and the icon image through email to register your app. Once your app is registered, Carloudy Controller app will catch information from notifications pushed by your app. 
Communication with Carloudy:
In Carlooudy Controller app, we call this function Widgets.
In Carlooudy Controller app, go to Menu (Top-Left corner, triple line icon) and choose Widgets. 
Choose the widgets from the list to add to your own widget library. Then, you will see the widgets in the drop-down list.
Choose the widget and it will direct you to the app with the same name.
For Carloudy to process your notification (information to be displayed on HUD),  please make sure your notification title is written in this format Title Carloudy HUD.

Components:
•	Image:	Set in Notification as large icon and will be shown on Carloudy HUD with parameters.
•	Title:	Set in Notification as title. Description for message type 
•	Text:	Set in Notification as text. A json format structure message with size, position-x, position-y, width, height for each text component
Format:
Title:
Title must be filled.
Only specific Titles are available for different functions:
[normal]:	clear all current message(s) on Carloudy HUD and display new message(s) – multiple texts and one picture
[update]:	clear specific message(s) shown on Carloudy HUD and display new message(s)
[remove]:	clear all current message(s) 
[stop]:	stop showing all the message(s) and go back to Carloudy original system
Text:
Text part in the notification is required to be written in JSON format.
First level:	you must start with json object “txt” or “img”
Second level:	in json object “txt”, you can include several text json objects with parameters for displaying on Carloudy HUD. The names for text json objects don’t matter.
in json object “img”, you can set 1 image object with parameters for displaying on Carloudy HUD. You must set the image as large icon in your notification.
Third level:	in each text json object, you MUST have seven keys with values listed below:
	id	(int) Unique for text json object. The id will be used for [update].
	tx	(String) Detail text information to show on Carloudy HUD.
	s	(int) Size for text information.
	x	(int) Position-x for text information.
	y	(int) Position-y for text information.
	w	(int) Text frame width. 0 for WRAP_CONTENT
	h	(int) Text frame height. 0 for WRAP_CONTENT
in each image json object, you MUST have seven keys with values listed below:
	id	(int) Unique for text json object. The id will be used for [update].
	x	(int) Position-x for text information.
	y	(int) Position-y for text information.
	w	(int) Text frame width. 0 for WRAP_CONTENT
	h	(int) Text frame height. 0 for WRAP_CONTENT

Sample in text:	{txt:{tx1:{id:111,tx:”sample text here”,s:24,x:50,y:75,w:400,h:550},tx2:{id:112,tx:”another sample text you want to show”,s:16,x:50,y:475,w:300,h:550}}},img:{id:1,x:350,y:100,w:300,h:300}}
(sample):	{
	txt:{
		tx1:{
			id:111,
			tx:”sample text here”,
			s:24,
			x:50,
			y:75,
			w:400,
			h:550
		},
		tx2:{
			id:112,
			tx:”another sample you want to show”,
			s:16,
			x:50,
			y:475,
			w:300,
			h:550
		}
	},
	img:{
		id:1,
		x:350,
		y:100,
		w:300,
		h:300
	}
}

Coding Guide:
You can download the sample app through the link:
https://github.com/Cognitive-AI-Tech/ProjectNSample
Attention:
For better user experience, you need to use a time scheduled handler to remove the notification in the notification center in a very short time, e.g. 100ms.

Notification Anatomy in Android (Android official document):
Found at https://developer.android.com/guide/topics/ui/notifiers/notifications
Small icon:	This is required and set with setSmallIcon().
App name:	This is provided by the system.
Time stamp:	This is provided by the system but you can override with setWhen() or hide it with setShowWhen(false).
Large icon:	This is optional (usually used only for contact photos; do not use it for your app icon) and set with setLargeIcon().
Title:	This is optional and set with setContentTitle().
Text:	This is optional and set with setContentText().

