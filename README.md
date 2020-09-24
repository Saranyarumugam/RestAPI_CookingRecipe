# RestAPI_CookingRecipe
*****Below are the assumptions/mock data considered for building automation script*****

1.	Mock URI is https://myCookingDiary.com
2.	Resources considered are as below
a.	“/login” end point for log into application
b.	“User/recipes” end point where list recipes created by user will be displayed 
c.	“User/recipes/add” end point to create new recipes
3.	Considered JSON data in below format for testing

{
"id": "1",
"name": "vegSoup",
"Date": "22/09/2020",
"Time": "12:00:00",
"Type": "VEG",
"Count": "2",
"Ingredients": ["Tomato", "Carrot", "Spinach", "Beans", "Salt"],
"Instructions": "Boil water, add ingredients, cook for 5 mins”
}

4.	Valid User name data: User123, Password: Pass123
5.	Invalid User name data: UserXX, Password: PassXX
6.  Asuuming Unique ID for each recipe


*****Included code for below scenarios*****
1.	Login with valid credentials
2.	Login with invalid credentials
3.	GET request and validate recipe Indicator
4.	Create new recipe (POST)
5.	Update existing recipe(PUT)
6.	Delete existing recipe

•	Created test class using REST Assured and TestNG framework.
