package com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.interfaces.Block;
import com.serviceBlocks.BlockA;
import com.serviceBlocks.BlockB;
import com.serviceBlocks.BlockC;
import com.serviceBlocks.BlockD;

public class SeatingArragementNewApp {

	public static void main(String[] args) {
		
		BlockA A = new BlockA();
		BlockB B = new BlockB();
		BlockC C = new BlockC();
		BlockD D = new BlockD();
		
		//TODO:: take inputs from User, Currently harcoded
		initializeBlocks(A, B, C, D);
		
		
		//combine the blocks
		Map<String,Block> map = new HashMap<>();
		addBlocks(A, B, C, D, map);
		
		int count =0;
		
		//TODO:: max Row size is hardcoded
		
		//Adding Aisle Seats
		count = setAisleSeats(map, count);
		
		//Adding Window Seats
		count = setWindowSeats(map, count);
		
		//Adding Middle Seats
		setMiddleSeats(map, count);
			
		System.out.println("val ==============>");
		
		
		printSeatAllocation(map);
		

	}

	private static void setMiddleSeats(Map<String, Block> map, int count) {
		for(int jumpBlocks=0 ; jumpBlocks < 4 ; jumpBlocks++ ) { //4 is max row size
			for(Block block : map.values() ) {
				int[][] unitBlock = block.getBlock();
				for(int i=0; i< unitBlock.length ; i++) {
					for(int j=0; j< unitBlock[0].length ; j++) {
						if(i == jumpBlocks &&  !(j==0  || j == unitBlock[0].length-1) ){
							unitBlock[i][j] = ++count;
						}

					}
				}
				block.setBlock(unitBlock);
			}
		}
	}

	private static int setWindowSeats(Map<String, Block> map, int count) {
		for(int jumpBlocks=0 ; jumpBlocks < 4 ; jumpBlocks++ ) { //4 is max row size
			for(Block block : map.values() ) {
				int[][] unitBlock = block.getBlock();
				for(int i=0; i< unitBlock.length ; i++) {
					for(int j=0; j< unitBlock[0].length ; j++) {
						if( (i == jumpBlocks) && (block instanceof BlockB  || block instanceof BlockC) ){
							continue;
						}
						if( i == jumpBlocks   && ((block instanceof BlockA  && j==0 ) ||
								(block instanceof BlockD  && j==unitBlock[0].length-1))){
							unitBlock[i][j] = ++count;
						}

					}
				}
				block.setBlock(unitBlock);
			}
		}
		return count;
	}

	private static int setAisleSeats(Map<String, Block> map, int count) {
		for(int jumpBlocks=0 ; jumpBlocks < 4 ; jumpBlocks++ ) { //4 is max row size
			for(Block block : map.values() ) {
				int[][] unitBlock = block.getBlock();
				for(int i=0; i< unitBlock.length ; i++) {
					for(int j=0; j< unitBlock[0].length ; j++) {
						if(i == jumpBlocks && (block instanceof BlockA  && j==0 ) ||
								(block instanceof BlockD  && j==unitBlock[0].length-1)){
							continue;
						}
						if(i == jumpBlocks && (j==unitBlock[0].length-1 || j==0)) {
							unitBlock[i][j] = ++count; 
						}
					}
				}
				block.setBlock(unitBlock);
			}
		}
		return count;
	}

	private static void printSeatAllocation(Map<String, Block> map) {
		for(Block block : map.values() ) {
			System.out.println(block);
			int[][] unitBlock = block.getBlock();
			for(int i=0; i< unitBlock.length ; i++) {
				for(int j=0; j< unitBlock[0].length ; j++) {
					System.out.print("  "+ unitBlock[i][j] + " ");
				}
				System.out.println(" ");
			}
		}
	}

	private static void addBlocks(BlockA A, BlockB B, BlockC C, BlockD D, Map<String, Block> map) {
		map.put("A",A);
		map.put("B",B);
		map.put("C",C);
		map.put("D",D);
	}

	private static void initializeBlocks(BlockA A, BlockB B, BlockC C, BlockD D) {
		int[][] blockA = new int [2][3];
		fillWithZero(blockA);
		A.setBlock(blockA);
		
		int[][] blockB = new int [3][4];
		fillWithZero(blockB);
		B.setBlock(blockB);
		
		
		int[][] blockC = new int [3][2];
		fillWithZero(blockC);
		C.setBlock(blockC);
		
		int[][] blockD = new int [4][3];
		fillWithZero(blockD);
		D.setBlock(blockD);
	}

	private static void fillWithZero(int[][] block) {
		for (int[] row: block)
		    Arrays.fill(row, 0);
	}

}
