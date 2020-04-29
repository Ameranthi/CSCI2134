/**
   File: District.java
   Author: Alex Brodsky
   Date: September 21, 2015
   Purpose: CSCI 1110, Assignment 4 Solution

   Description: This class specifies the District class.
*/

/**
   This is the District class for representing districts on the planet DohNat
   Each District object is responsible for keeping track of the spresso plants
   in the district, the TimBot (if one is present), and any incoming ion shots
   coming into and out of the district.  It is also repsonsible for gathering
   sense data from surrunding districts and passing it to the TimBot during the
   Sense phase.
*/
public class District { 
  // Thse are the district ID used to index into the spresso and bots
  // arrays passed to the senseDistrict method and also used to specify
  // where the TimBot wishes to fire the ion cannon.
  public static final int CURRENT = 0;
  public static final int NORTH = 1;
  public static final int EAST = 2;
  public static final int SOUTH = 3;
  public static final int WEST = 4;

}
