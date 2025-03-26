import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the number of families : ");
        Integer families = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Input the number of members in the family (separated by a space) : ");
        String[] familyMembers = scanner.nextLine().split(" ");

        if (familyMembers.length < families) {
            System.out.println("Input must be equal with count of family");
            return;
        }

        Integer busCount = calculateBusRequired(familyMembers);

        System.out.println("Minimum bus required is : " + busCount);
    }

    public static Integer calculateBusRequired(String[] familyMembers) {
        List<Integer> familyMembersList = new ArrayList<>();
        for (String member : familyMembers) {
            familyMembersList.add(Integer.parseInt(member));
        }
        
        familyMembersList.sort((a, b) -> b - a);
        
        Integer busCount = 0;
        Integer maxFamiliesInBus = 2;
        Integer maxPassengers = 4;

        while (!familyMembersList.isEmpty()) {
            Integer currentPassengers = 0;
            Integer familiesInBus = 0;
            busCount++;
            
            for (Integer i = 0; i < familyMembersList.size() && familiesInBus < maxFamiliesInBus;) {
                Integer familyMember = familyMembersList.get(i);
                
                if (currentPassengers + familyMember <= maxPassengers) {
                    currentPassengers += familyMember;
                    familiesInBus++;
                    familyMembersList.remove(i.intValue());
                } else {
                    i += 1;
                }
            }
            
            if (currentPassengers == 0 && !familyMembersList.isEmpty()) {
                familyMembersList.remove(0);
            }
        }
        
        return busCount;
    }
}
