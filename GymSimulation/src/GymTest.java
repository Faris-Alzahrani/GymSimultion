
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GymTest {

	static int workingminutes = 0;

	public static void main(String[] args) {
		ArrayList<TrainingMachines> Machines = new ArrayList<TrainingMachines>(Arrays.asList(new InclineBenchpress(),
				new treadmel(), new squatmachine(), new latpulldown(), new tricepspushdown(), new dumbbelpress(),
				new benchpress(), new smithmachine(), new RowMachine(), new SpinBike()));
		String MachineHistory = "";
		ArrayList<Trainee> Trainees = new ArrayList<Trainee>();
		ArrayList<Trainee> ExpTrainees = new ArrayList<Trainee>();
		ArrayList<Trainee> DoneTraining = new ArrayList<Trainee>();
		ArrayList<String> history = new ArrayList<String>();

		int TraineeID = 100;
		int ExpTraineeID = 300;
		Random gen = new Random();
		Scanner input = new Scanner(System.in);
		int x;
		int numberOfMemberInGroup;
		int hours = 0;
		int minutes = 0;
		int decision = 0;
		int generateTime = 0;
		int choice = 0;
		int HH;
		System.out.println("Enter the workig duration for the Gym (in hours):  ");
		HH = input.nextInt();
		HH *= 60;
		System.out.println("Enter the maximum number per group entering the Gym:  ");
		int GroupMax = input.nextInt();
		// System.out.println("enter core for the time of generation (core 'start at 0
		// to core' + offset): ");int core= input.nextInt();
		// System.out.println("enter offset for the time of generation (core 'start at 0
		// to core' + offset): ");int offset= input.nextInt();

		Coach coach = new Coach();

		// coach.SetupTrainingSchedule(Trainees.get(numberOfTrainee).ExMachines,
		// Machines);

		for (workingminutes = 0; workingminutes < HH; workingminutes++) {

			// generate random new comers for the
			// Gym=========================================================================================start
			int arriveTime = gen.nextInt(16) + 10;

			if (generateTime % arriveTime == 0) {
				numberOfMemberInGroup = gen.nextInt(GroupMax) + 1;
				double percentage = gen.nextDouble();

				if (percentage <= 0.8) {
					for (int count = 0; count <= numberOfMemberInGroup; count++) {
						x = gen.nextInt(70) + 1;
						Trainees.add(new Trainee(TraineeID, x));
						TraineeID++;
					}
				}

				else if (percentage <= 1.0) {
					x = gen.nextInt(20 + 1) + 80;
					Trainees.add(new ExpTrainee(ExpTraineeID, x));
					ExpTraineeID++;
				}

				// setup exercise schedules for new
				// comers==========================================================================================start
				for (int numberOfTrainee = 0; numberOfTrainee < Trainees.size(); numberOfTrainee++) {

					if (Trainees.get(numberOfTrainee).getHaveschedule() == false) {
						if (Trainees.get(numberOfTrainee) instanceof ExpTrainee) {

							Trainees.get(numberOfTrainee).setEx(Machines);
							Trainees.get(numberOfTrainee).setExHistory("\n" + Trainees.get(numberOfTrainee).ExMachines);


						} else {
							Trainees.get(numberOfTrainee).setEx(coach.SetupTrainingSchedule( Machines));
							Trainees.get(numberOfTrainee).setExHistory("\n" + Trainees.get(numberOfTrainee).ExMachines);
						}

						for (int j = 0; j < Trainees.get(numberOfTrainee).ExMachines.size(); j++) {
							Trainees.get(numberOfTrainee).x.add(
									(Integer) (Trainees.get(numberOfTrainee).ExMachines.get(j).getExerciseDuration()));
						}
						Trainees.get(numberOfTrainee).setHaveschedule(true);
						Trainees.get(numberOfTrainee).setT(Trainees.get(numberOfTrainee).ExMachines);
					}

				}

				// send the comer trainees to start their schedules
				// ==================================================================================start

				for (int numberOfTrainee = 0; numberOfTrainee < Trainees.size(); numberOfTrainee++) {
					if (Trainees.get(numberOfTrainee).x.isEmpty() == false
							&& Trainees.get(numberOfTrainee).getTraining() == false) {
						Trainees.get(numberOfTrainee).Train(Trainees.get(numberOfTrainee));
					}
				}
			}

			// update=====================================================================================================================================start

			for (int i = 0; i < Trainees.size(); i++) {

				// if the trainee finished from all the machines one his schedule then add him
				// to the record and remove him from the Trainees list inside the Gym
				if (Trainees.get(i).x.isEmpty() == true && Trainees.get(i).ExMachines.isEmpty() == true) {

					history.add(Trainees.get(i).getExHistory());
					DoneTraining.add(Trainees.get(i));
					Trainees.remove(Trainees.get(i));

					continue;
				} // ====================================================

				// if the trainee did not finished his schedule then:
				else {

					// if he is training on one of the machines in his schedule then subtract 1 from
					// that machine time
					if (Trainees.get(i).x.get(0) > 0
							&& Trainees.get(i).ExMachines.get(0).getCurrnetTrainee() == Trainees.get(i)) {
						Integer temp = Trainees.get(i).x.get(0);
						temp -= 1;
						Trainees.get(i).x.set(0, temp);
						Trainees.get(i).setTrainingTime();

					}

					// if he is waiting increase the waiting time by 1 minute or if he is the next
					// trainee on the waiting list let him start exercising
					else if (Trainees.get(i).x.get(0) > 0
							&& Trainees.get(i).ExMachines.get(0).getCurrnetTrainee() != Trainees.get(i)) {

						if (Trainees.get(i).ExMachines.get(0).getIsOccupied() == true) {

							if (Trainees.get(i).getWaiting() == true && Trainees.get(i).getTraining() == false) {
								if (Trainees.get(i).ExMachines.get(0).waitinglist.contains(Trainees.get(i)) == true) {

									Trainees.get(i).setWaitingTime();
								} else {
									Trainees.get(i).ExMachines.get(0).waitinglist.add(Trainees.get(i));
									Trainees.get(i).setWaiting(true);
								}

							} else if (Trainees.get(i).getWaiting() == false
									&& Trainees.get(i).getTraining() == false) {
								Trainees.get(i).ExMachines.get(0).waitinglist.add(Trainees.get(i));
								Trainees.get(i).setWaiting(true);
							}

						} else { // checks if the machine is empty   

							if (Trainees.get(i) == Trainees.get(i).ExMachines.get(0).waitinglist.peek()) {

								Trainees.get(i).ExMachines.get(0).waitinglist.peek().setWaiting(false);

								Trainees.get(i).Train(Trainees.get(i).ExMachines.get(0).waitinglist.poll());

							} else {
								if (Trainees.get(i).ExMachines.get(0).waitinglist.contains(Trainees.get(i))) {
									Trainees.get(i).setWaitingTime();
								} else {
									Trainees.get(i).ExMachines.get(0).waitinglist.add(Trainees.get(i));
								}

							}
						}

					}

					// if he finished his training set on a single machine then add this to the
					// records and move him to the next machine on schedule
					else if (Trainees.get(i).x.get(0) <= 0) {
						Trainees.get(i).setExHistory("\n done working from machine( "
								+ Trainees.get(i).ExMachines.get(0).getName() + ") at  " + workingminutes);
						Trainees.get(i).setTotalTrainingTime(Trainees.get(i).ExMachines.get(0).getExerciseDuration());
						Trainees.get(i).ExMachines.get(0).getDoneTraining();

						Trainees.get(i).x.remove(0);
						Trainees.get(i).ExMachines.get(0).setOccupied(false); 
																				
						Trainees.get(i).ExMachines.get(0)
								.setHistory("Trainee #" + Trainees.get(i).getId() + "is done Training.");
						if (Trainees.get(i).ExMachines.get(0).waitinglist.isEmpty() == false) {
							Trainees.get(i).ExMachines.get(0).waitinglist.peek().setWaiting(false);
					
							Trainees.get(i).Train(Trainees.get(i).ExMachines.get(0).waitinglist.poll());

							

						}

						Trainees.get(i).ExMachines.remove(0);
				

						if (Trainees.get(i).x.isEmpty() != true && Trainees.get(i).ExMachines.isEmpty() != true) { 
							Trainees.get(i).setTraining(false);
							Trainees.get(i).setWaiting(false);
							Trainees.get(i).Train(Trainees.get(i));
							
							

						}
					}
				} // end of the main else at line ***
			} // end of the update loop
				// ===========================================================================================================

			// if the any machine become free then let the first one on the waiting list
			// start training===============================================start

			for (int i = 0; i < Machines.size(); i++) {
				if (Machines.get(i).getIsOccupied() == false) {
					Machines.get(i).setCurrnetTrainee(Machines.get(i).waitinglist.poll());
				}
			}

			// check if the Trainee is the current on the machine and also in the waiting
			// list=====================================================start
			for (int i = 0; i < Trainees.size(); i++) {
				if (Trainees.get(i).ExMachines.isEmpty() == false) {
					if (Trainees.get(i).ExMachines.get(0).waitinglist
							.contains(Trainees.get(i).ExMachines.get(0).getCurrnetTrainee()) == true) {
						Trainees.get(i).ExMachines.get(0).waitinglist
								.remove(Trainees.get(i).ExMachines.get(0).getCurrnetTrainee());
					}
				}
			}

			// get the time for each machine every
			// minutes==========================================================================================start
			for (int i = 0; i < Machines.size(); i++) {
				if (Machines.get(i).getIsOccupied() == true) {
					Machines.get(i).getTotalUsed();
					if (generateTime % 30 == 0 && generateTime != 0) {
						Machines.get(i).setTotalUsed();
					}
				} else {
					Machines.get(i).getFreeTime();
					if (generateTime % 30 == 0 && generateTime != 0) {
						Machines.get(i).setFreeTime();
					}
				}
				if (Machines.get(i).getIsOccupied() == true) {
					Machines.get(i).setStatus("Busy!");
					Machines.get(i).setHistory(
							"the device is busy at " + workingminutes + " by " + Machines.get(i).getCurrnetTrainee() + "\n");
				} else {
					Machines.get(i).setStatus("Free!");
					Machines.get(i).setHistory("the device is free at " + workingminutes + "\n");
				}
			}

			for (int i = 0; i < DoneTraining.size(); i++) {
				if (DoneTraining.contains(DoneTraining.get(i)) == true)
					DoneTraining.get(i).setStatus("Done");
			}
			for (int i = 0; i < Trainees.size(); i++) {
				if (Trainees.get(i).getTraining() == true && Trainees.get(i).getWaiting() == false) {
					Trainees.get(i).setStatus("Training");
					Trainees.get(i).setTT();

				} else if (Trainees.get(i).getWaiting() == true && Trainees.get(i).getTraining() == false) {
					Trainees.get(i).setStatus("Waiting");
					Trainees.get(i).org(Trainees.get(i), Machines);}

				
			}
			if (workingminutes == HH-1) {
				for (int j = 0; j < Trainees.size(); j++) {
					if (Trainees.contains(Trainees.get(j)) == true)
						Trainees.get(j).setStatus("Not Done");
					Trainees.get(j).setExHistory("\n the Trainee ran out time ");

				}
			}

			generateTime++;
			// print the report each 30
			// minutes==============================================================
			if (generateTime % 30 == 0) {
				hours = generateTime / 60;
				minutes = generateTime % 60;

				System.out.printf("the time now is: %d hours and %d%n", hours, minutes);
				System.out.println("the report until this time is:\n--------------------------------------");
				System.out.printf("| %-30s | %-10s | %-10s | %-10s | %-15s | %-15s |%n", "Machines", "Status",
						"TotalUsed", "FreeTime", "Waitinglist", "DoneTrining");
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------");

				for (int i = 0; i < 10; i++) {
					System.out.printf("| %-30s | %-10s | %-10d | %-10d | %-15d | %-15d |%n", Machines.get(i).getName(),
							Machines.get(i).getStatus(), Machines.get(i).getTotalUsed(), Machines.get(i).getFreeTime(),
							Machines.get(i).waitinglist.size(), Machines.get(i).getDoneTraining());
				}
				System.out.println("\n\n");
				System.out.printf("| %-40s | %-15s | %-20s | %-15s | %-15s | %-15s | %-10s |%n%n", "Trainees", "Status",
						"CurrentMachine", "TrainingTime", "WaitingTime", "TariningTimeLeft", "TotalTime");

				for (int i = 0; i < DoneTraining.size(); i++) {
					System.out.printf("| %-40s | %-15s | %-20s | %-15d | %-15d | %-15d | %-10d |%n",
							DoneTraining.get(i), DoneTraining.get(i).getStatus(), "None",
							DoneTraining.get(i).getTrainingTime(), DoneTraining.get(i).getWaitingTime(), 0,
							DoneTraining.get(i).getTotalTrainingTime());
				}
				try {

					for (int i = 0; i < Trainees.size(); i++) {
						if (Trainees.get(i).getStatus() == "Not Done") {
							Trainees.get(i).ExMachines.get(0).setName("None");
						}
						if (Trainees.get(i).ExMachines.isEmpty() == true) {
							for (int j = 0; j < Trainees.size(); j++) {
								if (Trainees.get(j).ExMachines.isEmpty() == true) {

									history.add(Trainees.get(j).getExHistory());
									/////////////////////////////////
									DoneTraining.add(Trainees.get(j));
									Trainees.remove(Trainees.get(j));
								}
							}
							i++;
						}
						System.out.printf("| %-40s | %-15s | %-20s | %-15d | %-15d | %-15d | %-10d |%n",
								Trainees.get(i), Trainees.get(i).getStatus(),
								Trainees.get(i).ExMachines.get(0).getName(), Trainees.get(i).getTrainingTime(),
								Trainees.get(i).getWaitingTime(),
								Trainees.get(i).getT() - Trainees.get(i).getTrainingTime(),
								Trainees.get(i).getTrainingTime() + Trainees.get(i).getWaitingTime());
					}
				} catch (Exception e) {
					for (int i = 0; i < Trainees.size(); i++) {
						if (Trainees.get(i).ExMachines.isEmpty() == true) {

							history.add(Trainees.get(i).getExHistory());
							/////////////////////////////////
							DoneTraining.add(Trainees.get(i));
							Trainees.remove(Trainees.get(i));
						}
					}
				}
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------------------------------");
				if (Trainees.size()>20) {System.out.println("(There is a rush hour in the Gym now!! )");}
						System.out.println( "\nEnter 1 for waiting list on machines\nEnter 2 to forward on time\nEnter 3 for Trainee history\nEnter 4 for machine history"
						+ "\nEnter 5 to check how many were affected by waiting time");
				decision = input.nextInt();
				while (decision != 2) {
					if (decision == 1) {
						System.out.println(
								"choose the machine number:\n1-InclineBenchpress\n2-treadmel\n3-squatmachine\n4-latpulldown"
										+ "\n5-tricepspushdown\n6-dumbbelpress\n7-benchpress\n8-smithmachine\n9-RowMachine\n10-SpinBike");
						choice = input.nextInt() - 1;
						System.out.println(choice);
						switch (choice) {
						case 0:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 1:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 2:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 3:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 4:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 5:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 6:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 7:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 8:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						case 9:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).waitinglist);
							break;
						}
					}

					else if (decision == 2) {
						continue;
					} else if (decision == 3) {
						System.out.print("enter the ID:  ");
						int id = input.nextInt();

						for (int h = 0; h < DoneTraining.size(); h++) {
							if (id == DoneTraining.get(h).getId()) {
								System.out.println(DoneTraining.get(h).getExHistory());
								System.out.println("Currenrt machine: none");
								break;
							}
						}
						for (int h = 0; h < Trainees.size(); h++) {
							if (id == Trainees.get(h).getId()) {
								System.out.println(Trainees.get(h).getExHistory());
								System.out.println("Currenrt machine: " + Trainees.get(h).ExMachines.get(0).getName());
								break;
							}
						}

					} else if (decision == 3) {
						System.out.print("enter the ID:  ");
						int id = input.nextInt();

						for (int h = 0; h < Trainees.size(); h++) {
							if (id == Trainees.get(h).getId()) {
								System.out.println(Trainees.get(h).getExHistory());
								System.out.println(Trainees.get(h).ExMachines.get(0).getName());
								break;
							}
						}
					}
					if (decision == 4) {
						System.out.println(
								"choose the machine number:\n1-InclineBenchpress\n2-treadmel\n3-squatmachine\n4-latpulldown"
										+ "\n5-tricepspushdown\n6-dumbbelpress\n7-benchpress\n8-smithmachine\n9-RowMachine\n10-SpinBike");
						choice = input.nextInt() - 1;
						System.out.println(choice);
						switch (choice) {
						case 0:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 1:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 2:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 3:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 4:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 5:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 6:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 7:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 8:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						case 9:
							System.out
									.println(Machines.get(choice).getName() + ": " + Machines.get(choice).getHistory());
							break;
						}
					} else if (decision==5){
						int counterLate=0;
						int counterEarly=0;
						
						for (int jj=0;jj<DoneTraining.size();jj++) {
							if (DoneTraining.get(jj).getWaitingTime()>35)
								counterLate++;
							else {counterEarly++;}
						}
						
						for (int h=0;h<Trainees.size();h++) {
							if (Trainees.get(h).getWaitingTime()>35)
								counterLate++;
							else {counterEarly++;}
						}
						System.out.println("the number of trainees spent more than 35 min waiting : "+ counterLate);
						System.out.println("the number of trainees spent less than 35 min waiting : "+ counterEarly);

					}

					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------------------------------"
							+ "\nEnter 1 for waiting list on machines\nEnter 2 to forward on time\nEnter 3 for Trainee history\nEnter 4 for machine history"
							+ "\nEnter 5 to check how many were affected by waiting time");
					decision = input.nextInt();
				}

			} // end of for
				// loop=====================================================================================

			if (workingminutes == HH ) {
				for (int j = 0; j < Trainees.size(); j++) {
					history.add(Trainees.get(j).getExHistory() + " run out of time  ");
					for (int t = 0; t < Trainees.size(); t++) {
						if (Trainees.contains(Trainees.get(t)) == true)
							Trainees.get(t).setStatus("Not Done");
					}
					Trainees.remove(Trainees.get(j));
				}
			}

			/////////////////////////////// =======================================================================
		} // end of the main loop
		for (int i = 0; i < Trainees.size(); i++) {
			if (Trainees.get(i).ExMachines.isEmpty() == true) {

				history.add(Trainees.get(i).getExHistory());
				/////////////////////////////////
				DoneTraining.add(Trainees.get(i));
				Trainees.remove(Trainees.get(i));
			}
		}
		System.out.println("\n\n\nthe Gym working time is up");
		System.out.println(
				"####################################################################################################################");

		System.out.printf("| %-30s | %-10s | %-10s | %-10s | %-15s | %-15s |%n", "Machines", "Status", "TotalUsed",
				"FreeTime", "Waitinglist", "DoneTrining");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < 10; i++) {
			System.out.printf("| %-30s | %-10s | %-10d | %-10d | %-15d | %-15d |%n", Machines.get(i).getName(),
					Machines.get(i).getStatus(), Machines.get(i).getTotalUsed(), Machines.get(i).getFreeTime(),
					Machines.get(i).waitinglist.size(), Machines.get(i).getDoneTraining());
		}
		System.out.println("\n\n");

		System.out.printf("| %-40s | %-15s | %-20s | %-15s | %-15s | %-15s | %-10s |%n%n", "Trainees", "Status",
				"CurrentMachine", "TrainingTime", "WaitingTime", "TariningTimeLeft", "TotalTime");

		for (int i = 0; i < DoneTraining.size(); i++) {
			System.out.printf("| %-40s | %-15s | %-20s | %-15d | %-15d | %-15d | %-10d |%n", DoneTraining.get(i),
					DoneTraining.get(i).getStatus(), "None", DoneTraining.get(i).getTrainingTime(),
					DoneTraining.get(i).getWaitingTime(), 0, DoneTraining.get(i).getTotalTrainingTime());
		}
		try {
			for (int i = 0; i < Trainees.size(); i++) {
				if (Trainees.get(i).getStatus() == "Not Done") {
					Trainees.get(i).ExMachines.get(0).setName("None");
				}
				System.out.printf("| %-40s | %-15s | %-20s | %-15d | %-15d | %-15d | %-10d |%n", Trainees.get(i),
						Trainees.get(i).getStatus(), Trainees.get(i).ExMachines.get(0).getName(),
						Trainees.get(i).getTrainingTime(), Trainees.get(i).getWaitingTime(),
						Trainees.get(i).returnT() - Trainees.get(i).getTrainingTime(),
						Trainees.get(i).getTrainingTime() + Trainees.get(i).getWaitingTime());
			}
		} catch (Exception e) {
			for (int i = 0; i < Trainees.size(); i++) {
				if (Trainees.get(i).ExMachines.isEmpty() == true) {

					history.add(Trainees.get(i).getExHistory());
					/////////////////////////////////
					DoneTraining.add(Trainees.get(i));
					Trainees.remove(Trainees.get(i));
				}
			}
		}

	}
}
