# AirplaneSeatingArragement

/**
 * 
 * @author gowrishankar
 * 
 *  
 * fillSeats method takes two inputs
 * seatStructure - Two dimensional integer that holds the seating structure of an Airplane
 * passengerMaxCount - number of seats that needs to be filled
 *
 *  Below logic I considered for filling the seats

     1. AISLE SEAT
		1. Fill right corner seat in left blocks
		2. Fill left and right corner seats in all center blocks
		3. Fill left corner seat in right blocks

		4. Continue 1 to 3 for all rows

	2. WINDOW SEAT
		1. Fill left corner seat in left blocks
		2. Fill right corner seat in right blocks

		3. Continue 1 to 2 for all rows

	3. CENTER SEAT
		1. Fill all center seats (if available) in left blocks
		2. Fill all center seats (if available) in center blocks
		3. Fill all center seats (if available) in right blocks

		4. Continue 1 to 3 for all rows

 * */
