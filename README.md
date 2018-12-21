<a id="top"></a>

# Ride-Together/Powder-Radar

```
Code Fellows; seattle-ja-401d2
Darrin Howell, Nick Crain, Hiwot Nega, Jason Burns
```

### Target Demographic:
Avid snowboarders with robust social lives who want to streamline their mountian plans, together.

### Preliminary Vision:
The app that we plan to develop is called “Ride Together”. This app will make API requests to get weather reports of ski resorts within a specified radius of their phone’s location (NOAA API / Cordova API). In addition to making requests for weather, the app will render the nearby ski resorts (Google Maps API), and show the icons of fellow app users who have checked in to that resort for a trip. After rendering the maps page, users will have the ability to send invites to other friends / groups of friends alerting them (Twilio API) to join them on their snowboarding trip. We will have a database of user checkins as well as group meetups that specify time, people included, and mountain visited. We could also add in a search function that would allow users to check-in / plan trips at mountains all over the world. 

Please comment / edit regarding stretch goal ideas, other APIs to use, and ideas for views / the user’s flow through the application. This is a rough, rough draft. 


### User Stories:

#### App Users:

- As a user, I want to be able to see mountain conditions and groups of people visiting ski resorts so that I can connect with other app users.<br/>

- As a user, I want to have login access to the application so that I can access application features. <br/> 

- As a user, I want to be able to create groups / trips at specific ski resorts to host snowboard meetups for a given time period. <br/>

- As a user, I want to be able to join groups that aren’t full so that I can connect with other snowboarders and skiers.<br/>

- As a user, I want to have a map of all of the resorts in my general vicinity so that I can pick a place that I want to go. <br/> 

- As a user, after creating the group, I want to have the ability to edit group details (potentially remove users from group.)<br/>


#### Developers:

- As a developer, I want the flow of the website to make sense, such that the user experience is enjoyable. <br/>

- As a developer, I want our database and view progression to have parallel structures so that we can streamline organization of both. <br/>

- As a developer, I want to use authorization in my website so that non-registered users don’t have access to the things that they shouldn’t. <br/>

- As a developer, I want an object representing representing each user to exist in the database so that I can access and show user profile data. <br/>

- As a developer, I want the information in the database to be relationally organized so that related data is easy to find. <br/>

- As a developer I want the API calls to Google Maps and NOAA to successfully render a map with  nearby resorts and to return weather data. <br/>

- As a developer, I want to make an API call to Cordova to ascertain my phones GeoCode location to provide Google Maps and NOAA with correct location data. <br/>

#### Stretch Goal:
- As a user, I want to be able to add friends to the app so that I can connect with people whom I’ve met at various meetups. <br/>



<!-- [:top: Contents](#contents) -->
