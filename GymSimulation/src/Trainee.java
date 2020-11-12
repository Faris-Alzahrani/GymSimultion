import java.util.ArrayList;
import java.util.Collections;

public class Trainee {

	private boolean Haveschedule;
	protected ArrayList<TrainingMachines> ExMachines = new ArrayList<TrainingMachines>(5);
	private int experience;
	private int id;
	private boolean isTraining;
	private boolean isWaiting;

	public void setStart(int start) {
		this.start = start;
	}

	private int start;
	private String ExHistory = " ";

	ArrayList<Integer> x = new ArrayList<Integer>();

	public int getStart() {
		return start;
	}

	private int TotalTrainingTime;
	private int TrainingTime;
	private int WaitingTime;
	private int StartWaitingTime;
	private int T;
	private int TT;
	private String Status;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int returnT() {
		return T;
	}

	public int getT() {
		
		return T;
	}

	public void setT(ArrayList<TrainingMachines> ExMachines) {
		for (int k = 0; k < ExMachines.size(); k++) {
			int Temp = ExMachines.get(k).getExerciseDuration();
			T = T + Temp;
		}
	}

	public void setTotalTrainingTime(int TotalTrainingTime) {
		this.TotalTrainingTime = TotalTrainingTime;
	}

	public int getTotalTrainingTime() {

		return TrainingTime + WaitingTime;
	}

	public Trainee(int id, int exp) {
		experience = exp;
		this.id = id;
		this.isTraining = false;
		this.start = GymTest.workingminutes;
		this.ExHistory += "this Trainee enters the Gym at  " + start;

	}

	@Override
	public String toString() {
		return "Trainee [id=" + id + "]  entered at "  + start;
	}

	public int getTimeLeft() {
		int TotalTimeLeft = 0;
		for (int i = 0; i <= 4; i++) {
			TotalTimeLeft += x.get(i);
		}
		return TotalTimeLeft;
	}

	public void setExHistory(String ExHistory) {
		this.ExHistory += ExHistory;
	}

	public String getExHistory() {
		return ExHistory;
	}

	public void setEx(ArrayList<TrainingMachines> Machines) {


		for (int i = 0; i < 5; i++) {

			ExMachines.add(Machines.get(i));

		
		}
		
	
	}

	public void setMachine(TrainingMachines m) {
		int i = 0;
		ExMachines.add(i, m);
	}

	public Trainee(int id) {
		this.id = id;
	};

	public void Train(Trainee TR) {

		int z = 0;
		if (ExMachines.get(z).getIsOccupied() == false && TR.isWaiting == false) {
			ExMachines.get(z).setCurrnetTrainee(TR);
			ExMachines.get(z).setOccupied(true);
			TR.isTraining = true;
			TR.setStatus("Training");
			TR.ExHistory += "\n Training in machine (" + TR.ExMachines.get(z).getName() + ") at :"
					+ GymTest.workingminutes;
			TR.ExMachines.get(0).setHistory("Trainee #" + TR.getId() + " is A " + TR.getStatus() + "\n");

			if (x.isEmpty() == false) {
				Integer temp = x.get(z);
				temp -= 1;
				x.set(z, temp);
				TrainingTime += 1;
			}

		} else if (TR.isTraining == false && TR.isWaiting == false & ExMachines.get(z).getIsOccupied() == true) {
			ExMachines.get(z).waitinglist.add(TR);
			TR.ExHistory += "\n waiting for machine (" + TR.ExMachines.get(z).getName() + ") at : "
					+ GymTest.workingminutes +" ";
			TR.isWaiting = true;
			TR.setStatus("waiting");
			TR.ExMachines.get(0).setHistory("Trainee #" + TR.getId() + " is B " + TR.getStatus() + "\n");

		}

	}

	public boolean getHaveschedule() {
		return Haveschedule;
	}

	public void setHaveschedule(boolean haveschedule) {
		Haveschedule = haveschedule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getTraining() {
		return isTraining;
	}

	public void setTraining(boolean isTraining) {
		this.isTraining = isTraining;
	}

	public boolean getWaiting() {
		return isWaiting;
	}

	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public int getTrainingTime() {
		return TrainingTime;
	}

	public void setTrainingTime() {
		this.TrainingTime++;
	}

	public int getWaitingTime() {
		return WaitingTime;
	}

	public void setWaitingTime() {
		WaitingTime++;
	}

	public int getTT() {
		return TT;
	}

	public void setTT() {
		TT++;
	}

	boolean moved=false;

	public void org(Trainee TR, ArrayList<TrainingMachines> Machines) {

		if(TR.ExMachines.isEmpty()==false ) {
		
		if (TR.ExMachines.get(0).getIsOccupied() == true && TR.isWaiting == true  ) {

			for (int i = 0; i < TR.ExMachines.size(); i++) {

				if (TR.ExMachines.get(i).getIsOccupied() == false) {
					
					TR.ExMachines.get(0).setHistory("Trainee " + TR.id+ " left the waiting list \n");
					TR.ExMachines.get(0).waitinglist.remove(TR);
					Collections.swap(ExMachines, i, 0);
					Collections.swap(x, i, 0);
					TR.isWaiting = false;
					Train(TR);

					break;

					} /*
						 * else if (TR.ExMachines.get(0).waitinglist.size() >
						 * TR.ExMachines.get(i).waitinglist.size() && TR.ExMachines.get(0).getName()
						 * !=TR.ExMachines.get(i).getName() && TR.ExMachines.size() > 1 && moved ==false
						 * ) { TR.ExMachines.get(0).setHistory("Trainee " + TR.id+
						 * " left the waiting list \n"); TR.ExMachines.get(0).waitinglist.remove(TR);
						 * 
						 * Collections.swap(ExMachines, i, 0); Collections.swap(x, i, 0); TR.isWaiting =
						 * false; Train(TR); moved=true; break; }
						 */ else if (TR.ExMachines.get(0).waitinglist.size() > 2 && TR.ExMachines.size() ==1) {
					
					TR.ExMachines.get(0).setHistory("Trainee " + TR.id+ " left the waiting list \n");
					
					TR.ExMachines.get(0).waitinglist.remove(TR);
					for (int j = 0; j < Machines.size(); j++) {
						if (Machines.get(j).getIsOccupied() == false ) {
							TR.ExMachines.set(0, Machines.get(j));
							TR.x.set(0, Machines.get(j).getExerciseDuration());
							TR.isWaiting = false;
							TR.ExHistory+=" \n the trainee "+ TR.id+" changed the machine to "+Machines.get(j).getName() + "\n";
							Train(TR);
							break;
						} 
					} 

					if (TR.ExMachines.get(0).getCurrnetTrainee() != TR) {

						TR.ExMachines.remove(0);
						TR.x.remove(0);
						TR.ExHistory+=" \n the trainee #"+ TR.id+" decided to leave because of the machine is crowded "+ "\n";


					}

				} }
			}

		}

	}  

}