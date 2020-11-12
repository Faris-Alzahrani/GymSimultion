import java.util.ArrayList;
import java.util.Random;

public class ExpTrainee extends Trainee implements CanTrainHimSelf {


	
	public ExpTrainee (int iD,int exp) {super(iD,exp);}
	
	
	
	
	
	@Override
	public String toString() {
		return "ExpTrainee [id=" + super.getId() + "]  enterd at "+ super.getStart();
	}





	public ExpTrainee(int traineeID) {
		
		super(traineeID);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void setEx(ArrayList <TrainingMachines> Machines) {
		int size = 10;
		int temp[] = new int[10];
		int count = 0;
		ArrayList<Integer> list = new ArrayList<Integer>(size);
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

			super.ExMachines.add(Machines.get(temp[i]));

		//	System.out.print(i);
		}
		
		
		//System.out.println(ExMachines);
	}

}
