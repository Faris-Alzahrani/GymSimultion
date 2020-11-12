import java.util.LinkedList;
import java.util.Queue;

abstract class TrainingMachines {

	int SetupTime = 2;
	private String name;
	Queue<Trainee> waitinglist = new LinkedList<Trainee>();
	private Trainee CurrnetTrainee;
	private String History = "";
	private boolean isOccupied = false;
	private int ExerciseDuration;
	private int FreeTime;
    private int TotalUsed;
	private String Status="";
	private int DoneTraining;



	public TrainingMachines() {
		this.Status="";
	    this.TotalUsed=0;
	    this.FreeTime=0;
		this.SetupTime = 0;
		this.name = "";
		this.ExerciseDuration = 0;
	}
	public int getTotalUsed() {return TotalUsed++;}
    public int getFreeTime() {return FreeTime++;}
    public void setTotalUsed() {TotalUsed -= 2;}
    public void setFreeTime() {FreeTime -= 2;}
    public String getStatus() { return Status; }
    public void setStatus(String status) {
    Status = status;}
    public int getDoneTraining() {return DoneTraining++;}
    
    
	

	

	public Trainee getCurrnetTrainee() {
		return CurrnetTrainee;
	}

	public void setCurrnetTrainee(Trainee currnetTrainee) {
		CurrnetTrainee = currnetTrainee;
	}

	public int getSetupTime() {
		return SetupTime;
	}

	public void setSetupTime(int setupTime) {
		SetupTime = setupTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHistory() {
		return History ;
	}

	public void setHistory(String history) {
		History = History + history;
	}

	public boolean getIsOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getExerciseDuration() {
		return ExerciseDuration;
	}

	public void setExerciseDuration(int exerciseDuration) {
		ExerciseDuration = exerciseDuration;
	}

	@Override
	public String toString() {
		return " [name=" + name + "]";
	}

}

class benchpress extends TrainingMachines {

	public benchpress() {

		super();
		super.setExerciseDuration(7); 
		super.setName("benchpress");
		super.SetupTime = 2;
	}

}

class InclineBenchpress extends TrainingMachines {

	public InclineBenchpress() {

		
		super.setExerciseDuration(7); 
		super.setName("InclineBenchpress");
		super.SetupTime = 2;
	}

}

class treadmel extends TrainingMachines {

	public treadmel() {
		
		super.setExerciseDuration(7); 
		super.setName("treadmel");
		super.SetupTime = 1;

	}

}

class squatmachine extends TrainingMachines {

	public squatmachine() {

		super.setExerciseDuration(7); 
		super.setName("squatmachine");
		super.SetupTime = 2;
	}

}

class latpulldown extends TrainingMachines {

	public latpulldown() {

		super.setExerciseDuration(8); 
		super.setName("latpulldown");
		super.setSetupTime(2);
	}

}

class tricepspushdown extends TrainingMachines {


	public tricepspushdown() {

		super.setExerciseDuration(6); 
		super.setName("tricepspushdown");
		super.setSetupTime(1);
	}

}

class dumbbelpress extends TrainingMachines {

	public dumbbelpress() {
		super.setExerciseDuration(6); 
		super.setName("dumbbelpress");
		super.setSetupTime(3);
	}

}

class smithmachine extends TrainingMachines {

	public smithmachine() {

		super.setExerciseDuration(7); 
		super.setName("smithmachine");
		super.setSetupTime(2);
	}

}

class SpinBike extends TrainingMachines {

	public SpinBike() {
		super.setExerciseDuration(7); 
		super.setName("SpinBike");
		super.setSetupTime(1);

	}

}

class RowMachine extends TrainingMachines {

	public RowMachine() {

		super.setExerciseDuration(7); 
		super.setName("RowMachine");
		super.setSetupTime(2);

	}

}