package program;

import java.util.InputMismatchException;
import java.util.List;

import models.*;
import service.FridgeService;
import service.FridgeServiceImp;
import service.InspectorService;
import service.InspectorServiceImp;
import service.RestaurantService;
import service.RestaurantServiceImp;

import java.util.Scanner;

public class Program {
	public static FridgeService myFridgeService = new FridgeServiceImp();
	public static RestaurantService myRestaurantService = new RestaurantServiceImp();
	public static InspectorService myInspectorService = new InspectorServiceImp();
	public static int choice;
	public static String rName;
	public static String rPass;
	public static String inName;
	public static String inPass;

	public static void main(String[] args) {

		// This is the first thing the program user will see upon running the program.
		for (;;) {
			System.out.println("Welcome to the Fridge management system. Are you a restaurant owner or an inspector");
			System.out.println("1. Restaurant owner");
			System.out.println("2. Inspector");

			// take in the first choice and assign the input to the choice variable. Add in
			// the if statement in the try block to catch invalid integers submitted
			Scanner first = new Scanner(System.in);
			System.out.println("Enter the number of your choice");
			while (true) {
				try {
					choice = first.nextInt();
					if (choice != 1 && choice != 2) {
						System.out.println("Invalid input. Must select 1 or 2. you selected " + choice);
						continue;
					}
					break;
				} catch (NumberFormatException ignore) {
					System.out.println("Invalid input. Restart program and input numeric value");
				}
				first.close();
			}
			String accType = new String();
			if (choice == 1) {
				accType = "Restaurant owner";
			} else if (choice == 2) {
				accType = "Inspector";
			}
			System.out.println("Thank you, " + accType + ". Are you Creating an account or logging in?");

			if (choice == 1) {
				Scanner createOrLogin = new Scanner(System.in);
				while (true) {
					try {
						choice = createOrLogin.nextInt();
						break;
					} catch (InputMismatchException ignore) {
						System.out.println("Invalid input. Input an integer value");
					}
					createOrLogin.close();
				}

				if (choice == 1) {
					System.out.println("please enter your name");
					Scanner second = new Scanner(System.in);
					while (true) {
						try {
							rName = second.next();

							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						second.close();
					}

					System.out.println("Thank you. Please enter your password");

					Scanner third = new Scanner(System.in);
					while (true) {
						try {
							rPass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}

					Restaurant myRestaurant = new Restaurant(rName, rPass);
					myRestaurantService.insertRestaurant(rName, rPass);

					System.out.println("Welcome to your new Restaurant! Your options are below");
					System.out.println("1. Add a fridge");
					Scanner fridgeAdd = new Scanner(System.in);
					while (true) {
						try {
							String it1 = null;
							String it2 = null;
							String it3 = null;

							choice = fridgeAdd.nextInt();
							Restaurant r = myRestaurantService.getRestaurantByUserPass(rName, rPass);
							Fridge f = new Fridge(it1, it2, it3);
							myFridgeService.insertFridge(f, r.getAccountId());
							int s = myFridgeService.selectAllFridgesFromRestaurant(r).size();
							System.out.println("Thank you, there are now " + s
									+ " refrigerators in this location. What would you like to do next?");
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						fridgeAdd.close();
					}

					for (;;) {
						System.out.println("1. Add refrigerator");
						System.out.println("2. View items in your refrigerators");
						System.out.println("3. Add item to a refrigerator");
						System.out.println("4. Remove item from a refrigerator");
						System.out.println("5. Remove refrigerator");
						System.out.println("6. transfer item between refrigerators");
						System.out.println("7. Add inspector to a refrigerator");
						System.out.println("8. Remove inspector from a refrigerator");
						System.out.println("9. logout");

						Scanner option = new Scanner(System.in);
						while (true) {
							try {
								if (option.hasNextInt()) {
									choice = option.nextInt();
									break;
								}

							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
								continue;
							}
							option.close();
						}

						if (choice == 1) {
							String it1 = null;
							String it2 = null;
							String it3 = null;

							Restaurant r = myRestaurantService.getRestaurantByUserPass(rName, rPass);
							Fridge f = new Fridge(it1, it2, it3);
							myFridgeService.insertFridge(f, r.getAccountId());
							int s = myFridgeService.selectAllFridgesFromRestaurant(r).size();
							System.out.println("Thank you, there are now " + s
									+ " refrigerators in this location. What would you like to do next?");
							continue;
						}

						if (choice == 2) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are no more fridges left. Returning to menu");
							continue;
						}

						if (choice == 3) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								try {
									item = itemFinder.next();
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								itemFinder.close();

							}
							myFridgeService.addFoodToFridge(choice, item);
							continue;
						}

						if (choice == 4) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								System.out.println(257);
								try {
									item = itemFinder.next();
									System.out.println(260);
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								itemFinder.close();
								System.out.println(267);

							}
							myFridgeService.removeFoodFromFridge(choice, item);
							System.out.println("you are here");
							continue;
						}

						if (choice == 5) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index of the one you want to remove. Please ensure that all food items have been transferred.");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							myFridgeService.deleteFridge(choice);
							continue;

						}

						if (choice == 6) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								try {
									item = itemFinder.next();
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								itemFinder.close();
							}
							myFridgeService.removeFoodFromFridge(choice, item);

							Scanner whichFridge1 = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge1.hasNextInt()) {
										choice = whichFridge1.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge1.close();
							}

							myFridgeService.addFoodToFridge(choice, item);
							continue;

						}

						if (choice == 7) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the number of the one you want to add an inspector to");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
									continue;
								}
								whichFridge.close();
							}
							if (myFridgeService.getSpecificFridge(choice) == null) {
								continue;
							}
							System.out.println("Please enter the inspector's name");

							Scanner nameS = new Scanner(System.in);
							while (true) {
								try {
									inName = nameS.next();

									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								nameS.close();
							}

							System.out.println("Thank you. Please let the inspector enter their password");

							Scanner passS = new Scanner(System.in);
							while (true) {
								try {
									inPass = passS.next();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								passS.close();
							}
							Inspector i = new Inspector(inName, inPass);
							if (myInspectorService.getInspectorByUserPass(inName, inPass) == null) {
								System.out.println(
										"This inspector is not in the system. Please allow the inspector to create an account.");
								continue;
							}
							if (myFridgeService.addInspectorToFridge(myFridgeService.getSpecificFridge(choice),
									i) == false) {
								System.out.println(
										"The inspector was not added to the database. please check with the developer");
							}

							continue;
						}

						if (choice == 8) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the number of the one you want to add an inspector to");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
									continue;
								}
								whichFridge.close();
							}
							if (myFridgeService.getSpecificFridge(choice) == null) {
								continue;
							}
							System.out.println("Please enter the inspector's name");

							Scanner nameS = new Scanner(System.in);
							while (true) {
								try {
									inName = nameS.next();

									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								nameS.close();
							}

							System.out.println("Thank you. Please let the inspector enter their password");

							Scanner passS = new Scanner(System.in);
							while (true) {
								try {
									inPass = passS.next();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								passS.close();
							}
							Inspector i = new Inspector(inName, inPass);
							if (myInspectorService.getInspectorByUserPass(inName, inPass) == null) {
								System.out.println(
										"This inspector is not in the system. Please allow the inspector to create an account.");
								continue;
							}
							if (myFridgeService.removeInspectorFromFridge(myFridgeService.getSpecificFridge(choice),
									i) == false) {
								System.out.println(
										"The inspector was not added to the database. please check with the developer");
							}
							continue;
						}

						if (choice == 9) {
							break;
						}

						break;
					}
				}

				// log in for the restaurant owner
				if (choice == 2) {

					System.out.println("please enter your name");
					Scanner second = new Scanner(System.in);
					while (true) {
						try {
							rName = second.next();

							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						second.close();
					}

					System.out.println("Thank you. Please enter your password");

					Scanner third = new Scanner(System.in);
					while (true) {
						try {
							rPass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}

					Restaurant myRestaurant = myRestaurantService.getRestaurantByUserPass(rName, rPass);

					if (myRestaurant == null) {
						System.out.println("Sorry, username or password didn't match");
						continue;
					}

					System.out.println("Welcome back to your Restaurant! Your options are below");

					for (;;) {
						System.out.println("1. Add refrigerator");
						System.out.println("2. View items in your refrigerators");
						System.out.println("3. Add item to a refrigerator");
						System.out.println("4. Remove item from a refrigerator");
						System.out.println("5. Remove refrigerator");
						System.out.println("6. transfer item between refrigerators");
						System.out.println("7. Add inspector to a refrigerator");
						System.out.println("8. Remove inspector from a refrigerator");
						System.out.println("9. logout");

						Scanner option = new Scanner(System.in);
						while (true) {
							try {
								if (option.hasNextInt()) {
									choice = option.nextInt();
									break;
								}

							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							option.close();
						}

						if (choice == 1) {
							String it1 = null;
							String it2 = null;
							String it3 = null;

							Restaurant r = myRestaurantService.getRestaurantByUserPass(rName, rPass);
							Fridge f = new Fridge(it1, it2, it3);
							myFridgeService.insertFridge(f, r.getAccountId());
							int s = myFridgeService.selectAllFridgesFromRestaurant(r).size();
							System.out.println("Thank you, there are now " + s
									+ " refrigerators in this location. What would you like to do next?");
							continue;
						}

						if (choice == 2) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are no more fridges left. Returning to menu");
							continue;
						}

						if (choice == 3) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								try {
									item = itemFinder.next();
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								itemFinder.close();

							}
							myFridgeService.addFoodToFridge(choice, item);
							continue;
						}

						if (choice == 4) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								try {
									item = itemFinder.next();
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								itemFinder.close();
							}

							myFridgeService.removeFoodFromFridge(choice, item);
							continue;
						}

						if (choice == 5) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index of the one you want to remove. Please ensure that all food items have been transferred.");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							myFridgeService.deleteFridge(choice);
							continue;

						}

						if (choice == 6) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							for (Fridge item : f) {
								int index = item.getFridgeId();
								String food1 = item.getItem1();
								String food2 = item.getItem2();
								String food3 = item.getItem3();
								System.out.println("Fridge in index: " + index);
								System.out.println(food1);
								System.out.println(food2);
								System.out.println(food3);
							}
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the index number of the one you want");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge.close();
							}
							System.out.println("Please enter item:");
							Scanner itemFinder = new Scanner(System.in);
							String item = new String();
							while (true) {
								try {
									item = itemFinder.next();
									break;

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								itemFinder.close();
							}
							myFridgeService.removeFoodFromFridge(choice, item);

							Scanner whichFridge1 = new Scanner(System.in);
							System.out.println();
							while (true) {
								try {
									if (whichFridge1.hasNextInt()) {
										choice = whichFridge1.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
								}
								whichFridge1.close();
							}

							myFridgeService.addFoodToFridge(choice, item);
							continue;

						}

						if (choice == 7) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the number of the one you want to add an inspector to");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
									continue;
								}
								whichFridge.close();
							}
							if (myFridgeService.getSpecificFridge(choice) == null) {
								continue;
							}
							System.out.println("Please enter the inspector's name");

							Scanner nameS = new Scanner(System.in);
							while (true) {
								try {
									inName = nameS.next();

									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								nameS.close();
							}

							System.out.println("Thank you. Please let the inspector enter their password");

							Scanner passS = new Scanner(System.in);
							while (true) {
								try {
									inPass = passS.next();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								passS.close();
							}
							Inspector i = new Inspector(inName, inPass);
							if (myInspectorService.getInspectorByUserPass(inName, inPass) == null) {
								System.out.println(
										"This inspector is not in the system. Please allow the inspector to create an account.");
								continue;
							}
							if (myFridgeService.addInspectorToFridge(myFridgeService.getSpecificFridge(choice),
									i) == false) {
								System.out.println(
										"The inspector was not added to the database. please check with the developer");
							}

							continue;
						}

						if (choice == 8) {
							List<Fridge> f = myFridgeService.selectAllFridgesFromRestaurant(myRestaurant);
							System.out.println("there are " + f.size()
									+ " refrigerators. Choose the number of the one you want to add an inspector to");
							Scanner whichFridge = new Scanner(System.in);
							while (true) {
								try {
									if (whichFridge.hasNextInt()) {
										choice = whichFridge.nextInt();
										break;
									}

								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input an integer value");
									continue;
								}
								whichFridge.close();
							}
							if (myFridgeService.getSpecificFridge(choice) == null) {
								continue;
							}
							System.out.println("Please enter the inspector's name");

							Scanner nameS = new Scanner(System.in);
							while (true) {
								try {
									inName = nameS.next();

									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								nameS.close();
							}

							System.out.println("Thank you. Please let the inspector enter their password");

							Scanner passS = new Scanner(System.in);
							while (true) {
								try {
									inPass = passS.next();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								passS.close();
							}
							Inspector i = new Inspector(inName, inPass);
							if (myInspectorService.getInspectorByUserPass(inName, inPass) == null) {
								System.out.println(
										"This inspector is not in the system. Please allow the inspector to create an account.");
								continue;
							}
							if (myFridgeService.removeInspectorFromFridge(myFridgeService.getSpecificFridge(choice),
									i) == false) {
								System.out.println(
										"The inspector was not added to the database. please check with the developer");
							}
							continue;
						}

						if (choice == 9) {
							break;
						}

						break;
					}

				}
			}
			if (choice == 2) {
				System.out.println("Welcome inspector! Are you (1) creating an account or (2) logging in?");
				Scanner createOrLogin = new Scanner(System.in);
				while (true) {
					try {
						choice = createOrLogin.nextInt();
						break;
					} catch (InputMismatchException ignore) {
						System.out.println("Invalid input. Input an integer value");
					}
					createOrLogin.close();
				}

				if (choice == 1) {
					System.out.println("please enter your name");
					Scanner second = new Scanner(System.in);
					while (true) {
						try {
							inName = second.next();

							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						second.close();
					}

					System.out.println("Thank you. Please enter your password");

					Scanner third = new Scanner(System.in);
					while (true) {
						try {
							inPass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}
					myInspectorService.insertInspector(inName, inPass);
					Inspector myInspector = myInspectorService.getInspectorByUserPass(inName, inPass);

					for (;;) {
						System.out.println("Welcome to your account! Please allow the restaurant owner to log in");
						System.out.println("please enter your name");
						Scanner rest1 = new Scanner(System.in);
						while (true) {
							try {
								rName = rest1.next();

								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							rest1.close();
						}

						System.out.println("Thank you. Please enter your password");

						Scanner rest2 = new Scanner(System.in);
						while (true) {
							try {
								rPass = rest2.next();
								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							rest2.close();
						}

						Restaurant myRestaurant = myRestaurantService.getRestaurantByUserPass(rName, rPass);

						if (myRestaurant == null) {
							System.out.println("Sorry, username or password didn't match");
							continue;
						}

						for (;;) {
							System.out
									.println("Welcome to the restaurant. What would you like to do? (enter a number)");
							System.out.println("1. Look at the refrigerators you have access to in a restaurant");
							System.out.println("2. Remove an item from a refrigerator");
							System.out.println("3. Log out");
							Scanner inspectorChoice = new Scanner(System.in);
							while (true) {
								try {
									choice = inspectorChoice.nextInt();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								inspectorChoice.close();
							}

							if (choice == 1) {
								List<Fridge> f = myFridgeService.selectAllFridgesRestaurantAndInspector(myRestaurant,
										myInspector);
								for (Fridge item : f) {
									int index = item.getFridgeId();
									String food1 = item.getItem1();
									String food2 = item.getItem2();
									String food3 = item.getItem3();
									System.out.println("Fridge in index: " + index);
									System.out.println(food1);
									System.out.println(food2);
									System.out.println(food3);
								}

								continue;
							}
							if (choice == 2) {
								List<Fridge> f = myFridgeService.selectAllFridgesRestaurantAndInspector(myRestaurant, myInspector);
								for (Fridge item : f) {
									int index = item.getFridgeId();
									String food1 = item.getItem1();
									String food2 = item.getItem2();
									String food3 = item.getItem3();
									System.out.println("Fridge in index: " + index);
									System.out.println(food1);
									System.out.println(food2);
									System.out.println(food3);
								}
								System.out.println("there are " + f.size()
										+ " refrigerators. Choose the index number of the one you want");
								Scanner whichFridge = new Scanner(System.in);
								while (true) {
									try {
										if (whichFridge.hasNextInt()) {
											choice = whichFridge.nextInt();
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									whichFridge.close();
								}
								System.out.println("Please enter item:");
								Scanner itemFinder = new Scanner(System.in);
								String item = new String();
								while (true) {
									try {
										item = itemFinder.next();
										break;

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input a String value");
									}
									itemFinder.close();
								}

								myFridgeService.removeFoodFromFridge(choice, item);
								continue;
						}

						break;
					}

				}

				if (choice == 2) {
					System.out.println("please enter your name");
					Scanner second1 = new Scanner(System.in);
					while (true) {
						try {
							inName = second1.next();

							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						second1.close();
					}

					System.out.println("Thank you. Please enter your password");

					Scanner third1 = new Scanner(System.in);
					while (true) {
						try {
							inPass = third1.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third1.close();
					}

					Inspector myInspector = myInspectorService.getInspectorByUserPass(inName, inPass);

					if (myInspector == null) {
						System.out.println("Sorry, username or password didn't match");
						continue;
					}
					for (;;) {
						System.out.println("Welcome to your account! Please allow the restaurant owner to log in");
						System.out.println("please enter your name");
						Scanner rest1 = new Scanner(System.in);
						while (true) {
							try {
								rName = rest1.next();

								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							rest1.close();
						}

						System.out.println("Thank you. Please enter your password");

						Scanner rest2 = new Scanner(System.in);
						while (true) {
							try {
								rPass = rest2.next();
								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							rest2.close();
						}

						Restaurant myRestaurant = myRestaurantService.getRestaurantByUserPass(rName, rPass);

						if (myRestaurant == null) {
							System.out.println("Sorry, usrname or password didn't match");
							continue;
						}

						for (;;) {
							System.out
									.println("Welcome to the restaurant. What would you like to do? (enter a number)");
							System.out.println("1. Look at the refrigerators you have access to in a restaurant");
							System.out.println("2. Remove an item from a refrigerator");
							System.out.println("3. Log out");
							Scanner inspectorChoice = new Scanner(System.in);
							while (true) {
								try {
									choice = inspectorChoice.nextInt();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								inspectorChoice.close();
							}

							if (choice == 1) {
								List<Fridge> f = myFridgeService.selectAllFridgesRestaurantAndInspector(myRestaurant,
										myInspector);
								for (Fridge item : f) {
									int index = item.getFridgeId();
									String food1 = item.getItem1();
									String food2 = item.getItem2();
									String food3 = item.getItem3();
									System.out.println("Fridge in index: " + index);
									System.out.println(food1);
									System.out.println(food2);
									System.out.println(food3);
								}

								continue;
							}
							if (choice == 2) {
								List<Fridge> f = myFridgeService.selectAllFridgesRestaurantAndInspector(myRestaurant, myInspector);
								for (Fridge item : f) {
									int index = item.getFridgeId();
									String food1 = item.getItem1();
									String food2 = item.getItem2();
									String food3 = item.getItem3();
									System.out.println("Fridge in index: " + index);
									System.out.println(food1);
									System.out.println(food2);
									System.out.println(food3);
								}
								System.out.println("there are " + f.size()
										+ " refrigerators. Choose the index number of the one you want");
								Scanner whichFridge = new Scanner(System.in);
								while (true) {
									try {
										if (whichFridge.hasNextInt()) {
											choice = whichFridge.nextInt();
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									whichFridge.close();
								}
								System.out.println("Please enter item:");
								Scanner itemFinder = new Scanner(System.in);
								String item = new String();
								while (true) {
									try {
										item = itemFinder.next();
										break;

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input a String value");
									}
									itemFinder.close();
								}

								myFridgeService.removeFoodFromFridge(choice, item);
								continue;
							}
							if (choice == 3) {
								System.out.println("Thank you for using the program");
							}
							break;
						}

						break;
					}

				}
			}

		}
	}
}
