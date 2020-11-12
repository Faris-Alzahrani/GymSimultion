import java.util.*;

public class Coach  {

	public  ArrayList<TrainingMachines> SetupTrainingSchedule( ArrayList<TrainingMachines> Machines) {
		int size = 10;
		int temp[] = new int[10];
		int count = 0;
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		ArrayList<TrainingMachines> Ex = new ArrayList<TrainingMachines>(5);

		for (int i = 0; i <= size - 1; i++) {
			list.add(i);
		}
		Random rand = new Random();

		while (list.size() > 0) {
			int index = rand.nextInt(list.size());
			temp[count] = list.remove(index);
			count++;
		}

		
		for (int i = 0; i < 5; i++) {

			Ex.add(Machines.get(temp[i]));

		}
		
		return  Ex;
		
		
	}

}
