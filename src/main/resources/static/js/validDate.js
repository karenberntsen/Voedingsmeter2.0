/**
 * 
 */
function validDate(date) {
	console.log("validDate on "+date);
	var dateArray=date.split("-");
	return validDateByComponents(dateArray[0],dateArray[1],dateArray[2]);
}

function validDateByComponents(month, day, year)
{
   // Test for leap year
    if ((year % 400 == 0) || (year % 4 == 0) &&
       !(year % 100 == 0))
          leap = true;
    else
          leap = false;

    // Validate date                              // Assume a valid date-test otherwise
    if (year < 1900 || year > 2050)               // year must be four digits and
        return false;                             //     within reasonable range
    else if ((month < 1) || (month > 12) ||       // test general date and month range
             (day < 1) || (day > 31))
        return false;
    else if (((month == 4) || (month == 6) ||     // test 30 Day months
              (month == 9) || (month == 11)) && (day == 31))
        return false;
    else if (month == 2 && leap && day > 29)      // test February leap years
        return false;
    else if (month == 2 &&  !leap && day > 28)    // test February NON-leap years
        return false;
    else
        return true;                              // otherwise, date is OK

}