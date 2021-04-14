package com.khanna111.javascrap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# Your goal is to simulate Actions in a round of Diplomacy, a game of armies and warfare.

# You'll receive a list of strings in the following format:
#   - army_name start_location action_type destination_location

# Your goal will be to output the final locations of each of the armies, or the string "[dead]" if the army dies

# Action types
#    - Hold the army stays in the current position
#       - army_name location Hold

#    - Move the army moves to the given position
#       - army_name start_location Move destination_locationkmk

#    - Support the army adds 1 strength to another army
#       - army_name location Support target_army_name

# Example input 1

# A Prussia Move France
# B Peru Hold

# Example output 1
# A France
# B Peru

# Explanation
# Army A moves to France and B stays in Peru. Thus, their ending locations

# Example input 2
#     D Prussia Move France
#     I Clayton Move France
#     K Spain Move England
#     L Peru Hold

# ** Example output 2 **
#     D [dead]
#     I [dead]
#     K England
#     L Peru

# Explanation
#     Armies D and I move to France, both with strength 0. If two armies both move with the same strength to the same    location, they will both die. Otherwise, the one with the higher strength lives and the others die.

# Army K will succesfully move to England and L will stay in Peru.

# ## Part Two: Support

# Let&#39;s add in the support command!

# ```
actions = """
D Prussia Move France
I Clayton Move France
K Russia Move England
J Turkey Support I 
L Peru Hold
"""
# **Output**

# ```
# output = """
# D [dead]
# I France
# K England
# J Turkey
# L Peru
# """
# ```



// Solution:
// Data Structures:
// Map 1: 
//  DL to List of Armies
//    Eg: FRANCE --> A, B, C
// Map 2:
//  Supported Army to count of being supported
//    Eg: I --> 2 [I is being supported by 2 armies]
// Map 3:
//  Armies to final location
//    Eg: J -> France
// 

*/

class Test {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		list.add("J Turkey Support I");
		list.add("D Prussia Move France");
		list.add("I Clayton Move France");
		list.add("K Russia Move England");
		list.add("L Peru Hold");
		list.add("M England Hold");
		list.add("N England Hold");
		list.add("O England Support N");

		output(list);
	}

	public static List<String> output(List<String> inputList) {

		Map<String, List<String>> mapDestToArmies = new HashMap<>();
		Map<String, Integer> mapArmyToSuppCount = new HashMap<>();
		Map<String, String> mapArmyToDest = new HashMap<>();

		for (String i : inputList) {
			String[] splitI = i.split(" ");
			int index = 0;
			String army = splitI[index++]; // army
			String startLocation = splitI[index++]; // start location
			String action = splitI[index++]; // support, move or hold
			String destinationLocationOrSupportedArmy = null; // destination location / supported Army if provided
			if (index < splitI.length) {
				// we have one more to go
				destinationLocationOrSupportedArmy = splitI[index];

			}
			switch (action) {
			case "Hold":
				mapArmyToDest.put(army, startLocation);
				
				List<String> l = addToArmyList(mapDestToArmies, army, startLocation);
				mapDestToArmies.put(startLocation, l);

				break;

			case "Support":
				mapArmyToSuppCount.put(destinationLocationOrSupportedArmy,
						mapArmyToSuppCount.getOrDefault(destinationLocationOrSupportedArmy, 0) + 1);

				mapArmyToDest.put(army, startLocation);
				
				List<String> l1 = addToArmyList(mapDestToArmies, army, startLocation);
				mapDestToArmies.put(startLocation, l1);
				break;

			default:
				// we have "Move" here
				List<String> l2 = addToArmyList(mapDestToArmies, army, destinationLocationOrSupportedArmy);
				mapDestToArmies.put(destinationLocationOrSupportedArmy, l2);
				// also update the army to dest mapping
				mapArmyToDest.put(army, destinationLocationOrSupportedArmy);

			}

		} // end for

		// export it into a list as output.
		// print the hold as it is
		List<String> outputList = new ArrayList<>();

		for (String army : mapArmyToDest.keySet()) {
		
			StringBuilder strB = new StringBuilder();
			
			// if this dest has no other army mapping then add it as it is
			// Else:
			// 		check the count and the higher count wins
			// 		the rest are "dead"
			String dest = mapArmyToDest.get(army);
			if (mapDestToArmies.get(dest) == null) {
				strB.append(army).append(' ').append(mapArmyToDest.get(army));
				outputList.add(strB.toString());
				
			}
			else {
				// check the max count or tie and add accordingly
				int suppCount = mapArmyToSuppCount.getOrDefault(army, 0);
				// check if there is a higher support count
				boolean isSupportCountSame = false;
				int maxSupportCount = suppCount;
				for (String a : mapDestToArmies.get(dest)) {
					if (a.equals(army)) {
						continue;
					}
					int thisArmyCount = mapArmyToSuppCount.getOrDefault(a, 0);
					
					if (thisArmyCount == suppCount) {
						isSupportCountSame = true;
					}
					if (Math.max(thisArmyCount, suppCount) != suppCount) {
						// we have more support for some other army
						// mark as "Dead"
						strB.append(army).append(' ').append("Dead");
						outputList.add(strB.toString());
						maxSupportCount = thisArmyCount;
						
						break;
					}
				}
				if (maxSupportCount == suppCount) {
					// check now for duplicate support
					if (isSupportCountSame) {
						strB.append(army).append(' ').append("Dead");
						outputList.add(strB.toString());
					}
					else {
						strB.append(army).append(' ').append(dest);
						outputList.add(strB.toString());
					}
				}
				
				
				
			}
			
		}

		System.out.println("Answer\n" + outputList);

		return outputList;

	}

	private static List<String> addToArmyList(Map<String, List<String>> mapDestToArmies, String army,
			String destinationLocationOrSupportedArmy) {
		List<String> l = mapDestToArmies.getOrDefault(destinationLocationOrSupportedArmy, new ArrayList<>());
		
		l.add(army);
		return l;
	}

}