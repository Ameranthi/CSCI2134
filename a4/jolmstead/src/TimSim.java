/**
   File: Cleaner.java
   Author: Alex Brodsky
   Date: August 26, 2015
   Purpose: CSCI 1110, Assignment 2 Solution

   Description: This program applies multiple filters to a gray scale image.
*/

import java.util.*;

/**
   This is the main (only) class of the program and contains all program code.
   The program starts running in the main() of this class.
*/
public class TimSim {
  /**
     This method is called when the program starts running. 
     It reads in a single integer from the console and then 
     determines its prime factorization by dividing the integer
     by all smaller values (greater than 1) until the result is
     $1$ or a prime number.  Each factor is printed out after it
     is computed.76
  */
  public static void main( String [] args ) {
    // Instatiate Scanner to read in he timbot configuration
    // console and loading the image
    Scanner stdin = new Scanner( System.in );   

    // for debugging purposes (please ignore)
    boolean debug = ( args.length > 0 ) && args[0].equals( "debug" );

    // load configuration of simulation
    int rows = stdin.nextInt();
    int columns = stdin.nextInt();
    int jolts = stdin.nextInt(); // for spresso plants
    int growth = stdin.nextInt(); // for spresso plants
    int rounds = stdin.nextInt();
    int numBots = stdin.nextInt();
    int numPlantConfigs = stdin.nextInt(); // number of plant configurations


    // Instantiate planet and array of timbots
    DohNat planet = new DohNat(rows, columns);
    TimBot [] bots = new TimBot[numBots];
    Spresso spresso = new Spresso(jolts, growth, "spresso", true, true);
    
    // Load timbot configurations
    for( int i = 0; i < numBots; i++ ) {
      // Read in one timbot config
      String personality = stdin.next();
      int id = stdin.nextInt();
      int x = stdin.nextInt();
      int y = stdin.nextInt();
      int energy = stdin.nextInt();

      // Insantiate the corresponding bot object.
      switch( personality ) {
      case "chicken":
        bots[i] = new ChickenBot( id, energy );
        break;
      case "spresso":
        bots[i] = new SpressoBot( id, energy );
        break;
      case "angry":
        bots[i] = new AngryBot( id, energy );
        break;
      case "bully":
        bots[i] = new BullyBot( id, energy );
        break;
      }


      // Error checking (unnecessary)
      if( !planet.setTimBot( bots[i], x, y ) ) {
        System.err.println( bots[i] + " not added" );
        return;
      } else if( debug ) {
        System.err.println( bots[i] + " added" );
      }
    }


    /**
     * plant configurations are read
     * A plant configuration consists of a single line containing one string and four integers: <NEW>
     */

    //reads in for additional plants
    for( int i = 0; i < numPlantConfigs; i++){

      String plantName = stdin.next();// N: string denoting name of plant. E.g., mericano, spresso.
      int xCoordinate = stdin.nextInt(); //X: the x coordinate of the district containing the plant, where 0 ≤ X < C
      int yCoordinate = stdin.nextInt(); //Y: the y coordinate of the district containing the plant, where 0 ≤ Y < R
      int plantEnergy = stdin.nextInt(); // E: number of Jolts the plant harvest yields
      int growthRate = stdin.nextInt(); //G: number of rounds needed for the plant to grow
    }


    // Loop for a specified number of rounds
    for( int i = 0; i < rounds; i++ ) {
      // Perform the phases of a round
      planet.newRound();
      planet.doSensePhase();
      if( debug ) {
        System.err.println( "Sense Phase " + i );
        System.err.print( planet );
      }
      planet.doFirePhase();
      if( debug ) {
        System.err.println( "Fire Phase " + i );
        System.err.print( planet );
      }
      planet.doDefensePhase();
      if( debug ) {
        System.err.println( "Defense Phase " + i );
        System.err.print( planet );
      }
      planet.doMovePhase();
      if( debug ) {
        System.err.println( "Move Phase " + i );
        System.err.print( planet );
      }
      planet.doBattlePhase();
      if( debug ) {
        System.err.println( "Battle Phase " + i );
        System.err.print( planet );
      }
      planet.doHarvestPhase();

      // Output results
      System.out.println( "Round " + i );
      System.out.print( planet );
      
      // Check if the simulation is done by counting number of functional bots
      int remaining = 0;
      for( int j = 0; j < numBots; j++ ) { 
        if( bots[j].isFunctional() ) {
          remaining++;
        }
      }
    }
  }
}
