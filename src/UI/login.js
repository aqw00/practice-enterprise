/*set right user and password*/
var Ruser = "test";
var Rpassword = "password";

/*prompt user*/
var user = prompt("Username:");
if(user == Ruser)
{
    /*if given user is correct*/
    /*prompt password*/
    var password = prompt("Password for " + user);
    if(password != Rpassword)
    {
        /*if given password is incorrect*/
        window.location = "./wrongPasswd.html";
    }
}
else if (user != Ruser)
{
    /*if given user is incorrect*/
    window.location = "./nonexistantUser.html";
}
