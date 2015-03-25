# Legendary Card Randomizer
Web Application to Build a Random Deck Setup for Marvel Legendary. Supports Marvel Legendary Base Game, All Current Expansions, and Custom Cards.

## Application Requirements
The application is written using JEE6 and is intended to run on WildFly 8.2 or Higher with WildFly Camel 2.1 or Higher installed.

All non-GET endpoints are secured using BASIC authentication via JAAS. A user needs to exist in the ApplicationRealm with the role of ADMIN in order to call them.
