package com;
import com.enums.SeatTypes;
/**
 * 
 * @author gowrishankar
 *
 */
public class SeatingArragement {

	public static void main(String[] args) {

		//Hardcoded Input
		int[][] availableSeatBlocks =  {
				{2, 3},
				{3, 4},
				{3, 2},
				{4, 3}
		};
		int passengerMaxCount = 30;

		fillSeats(availableSeatBlocks, passengerMaxCount);

	}


	public static int[][][] fillSeats(int[][] availableSeatBlocks, int passengerMaxCount) {
		int maximumBlocksAvailable = availableSeatBlocks.length;
		int maximumRowsAvailable = 0;

		//Wrapper 3D Array
		int[][][] seatsBlocksStructure = new int[maximumBlocksAvailable][][];
		findmaxRowAndTransformBlock(availableSeatBlocks, maximumBlocksAvailable, maximumRowsAvailable, seatsBlocksStructure);

		int count =1;

		//Iterate All Seat Types
		for(SeatTypes seat : SeatTypes.values() ) {
			//Iterate for All Seats Row wise
			for(int rowIterator=0; rowIterator< maximumBlocksAvailable; rowIterator++ ) {
				count = fillSeats(passengerMaxCount, seatsBlocksStructure, count, rowIterator,seat);
			}
		}

		printSeatAllocation(seatsBlocksStructure);
		
		return seatsBlocksStructure;
	}


/**
 * Pick A 2D block to fill seats
 * @param passengerMaxCount
 * @param seatsBlocksStructure
 * @param count
 * @param rowIterator
 * @param seatType
 * @return count
 */
	private static int fillSeats(int passengerMaxCount, int[][][] seatsBlocksStructure, int count,
			int rowIterator, SeatTypes seatType) {
		for( int wallBlock=0; wallBlock < seatsBlocksStructure.length ; wallBlock++) {

			//Pick a 2D block to fill seats
			int[][] block = seatsBlocksStructure[wallBlock];

			count = blockIterator(passengerMaxCount, seatsBlocksStructure, count, rowIterator, seatType, wallBlock,block);
		}
		return count;
	}


	/*
	 * Iterate Each Blocks to fill individual seats
	 */
	private static int blockIterator(int passengerMaxCount, int[][][] seatsBlocksStructure, int count, int rowIterator,
			SeatTypes seatType, int wallBlock, int[][] block) {
		for(int i=0; i< block.length ; i++) {

			if(rowIterator == i) {

				for(int j=0; j< block[0].length ; j++) {
					if(seatType.equals(SeatTypes.AISLE)) {
						count = fillingAisleSeats(passengerMaxCount, seatsBlocksStructure, count, wallBlock, block, i,j);
					}
					else 
					if(seatType.equals(SeatTypes.WINDOW)) {
						count = fillWindowSeats(passengerMaxCount, seatsBlocksStructure, count, wallBlock,block, i, j);
					}
					else 
					if(seatType.equals(SeatTypes.MIDDLE)) {
						count = fillMiddleSeats(passengerMaxCount, seatsBlocksStructure, count, wallBlock,block, i, j);
					}
				}
			}
		}
		return count;
	}

	/*
	 * Middle Seat Condition
	 */
	private static int fillMiddleSeats(int passengerMaxCount, int[][][] seatsBlocksStructure, int count, int wallBlock,
			int[][] block, int i, int j) {
		//Base Case
		if(count > passengerMaxCount) {
			return count;
		}
		//Middle Seats Condition
		if( !(j==block[0].length-1 || j==0)) {
			seatsBlocksStructure[wallBlock][i][j] = count++;

		}
		return count;
	}


	/*
	 * Aisle Seat Condition
	 */
	private static int fillingAisleSeats(int passengerMaxCount, int[][][] seatsBlocksStructure, int count,
			int wallBlock, int[][] block, int i, int j) {
		//Base Case
		if(count > passengerMaxCount) {
			return count;
		}
		//Boundary Condition :: Exclude Side walls
		if(  (wallBlock==0 && j==0 ) || ( wallBlock==seatsBlocksStructure.length-1 && j==block[0].length-1)) {
			return count;
		}
		if(  j==block[0].length-1 || j==0 ) {
			seatsBlocksStructure[wallBlock][i][j] 
					= count++;
		}
		return count;
	}

	/*
	 * Window Seat Condition
	 */
	private static int fillWindowSeats(int passengerMaxCount, int[][][] seatsBlocksStructure, int count, int wallBlock,
			int[][] block, int i, int j) {
		//Base Case
		if(count > passengerMaxCount) {
			return count;
		}
		if(  (wallBlock==0 && j==0 ) || ( wallBlock==seatsBlocksStructure.length-1 && j==block[0].length-1) ) {
			seatsBlocksStructure[wallBlock][i][j] = count++;
		}
		return count;
	}

	/*
	 * Convert 2D to 3D Array for easy iteration
	 */
	private static int findmaxRowAndTransformBlock(int[][] availableSeatBlocks, int maximumBlocksAvailable,
			int maximumRowsAvailable   , int[][][] seatsBlocksStructure) {
		for(int i = 0; i < maximumBlocksAvailable; i++) {

			seatsBlocksStructure[i] = new int[availableSeatBlocks[i][0]][availableSeatBlocks[i][1]];

			if(availableSeatBlocks[i][0] > maximumRowsAvailable) { 
				maximumRowsAvailable = availableSeatBlocks[i][0];
			}

		}
		return maximumRowsAvailable;
	}


	private static void printSeatAllocation(int[][][] seatsBlocksStructure) {
		System.out.println("Seating Arrangement");
		for(int[][] block : seatsBlocksStructure) {
			System.out.println("<><><><><><><><><><>");
			for(int i=0; i< block.length ; i++) {
				for(int j=0; j< block[0].length ; j++) {
					System.out.print("  "+ block[i][j] + " ");
				}
				System.out.println(" ");
			}
		}
	}


}
