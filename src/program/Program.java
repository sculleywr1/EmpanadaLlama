package program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import models.*;
import java.util.Scanner;

public class Program {

	static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	static ArrayList<Inspector> inspectors = new ArrayList<Inspector>();
	static int numberOfRestaurants = restaurants.size();
	static int numberOfInspectors = inspectors.size();

	static int choice;
	static String name;
	static String pass;
	// the below variables are used in the case of the inspector. A restaurant owner
	// must log in to confirm that an inspector has access to the refrigerators the
	// inspector is accessing
	static String restName;
	static String restPass;

	public static void main(String[] args) {

		// This is the first thing the program user will see upon running the program.
		for (;;) {
			System.out.println("Welcome to the Fridge management system. Are you a restaurant owner or an inspector");
			System.out.println("1. Restaurant owner");
			System.out.println("2. Inspector");
			System.out.println("There are currently " + numberOfRestaurants + " restaurants and " + numberOfInspectors
					+ " inspectors registered in the system. For a total of "
					+ (numberOfInspectors + numberOfRestaurants) + " accounts registered");

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
						System.out.println("Invalid input. Input a String value");
					}
					createOrLogin.close();
				}

				if (choice == 1) {
					System.out.println("please enter your name");
					Scanner second = new Scanner(System.in);
					while (true) {
						try {
							name = second.next();

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
							pass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}

					Restaurant myRestaurant = new Restaurant(name, pass, accType);
					restaurants.add(myRestaurant);
					numberOfRestaurants = restaurants.size();

					System.out.println("Welcome to your new Restaurant! Your options are below");
					System.out.println("1. Add a fridge");
					Scanner fridgeAdd = new Scanner(System.in);
					while (true) {
						try {
							String it1 = null;
							String it2 = null;
							String it3 = null;

							String[] items = { it1, it2, it3 };
							choice = fridgeAdd.nextInt();
							Restaurant r = myRestaurant;
							Fridge f = new Fridge(items);
							myRestaurant.addFridge(f);
							int i = restaurants.indexOf(r);
							restaurants.remove(i);
							restaurants.add(i, myRestaurant);
							int s = restaurants.get(i).getRefrigerators().size();
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
							}
							option.close();
						}

						if (choice == 1) {
							String it1 = null;
							String it2 = null;
							String it3 = null;

							String[] items = { it1, it2, it3 };

							Restaurant r = myRestaurant;
							Fridge f = new Fridge(items);
							myRestaurant.addFridge(f);
							int i = restaurants.indexOf(r);
							restaurants.remove(i);
							restaurants.add(i, myRestaurant);
							int s = restaurants.get(i).getRefrigerators().size();
							System.out.println("Thank you, there are now " + s
									+ " refrigerators in this location. What would you like to do next?");
							continue;
						}

						if (choice == 2) {
							Restaurant r = myRestaurant;
							for (Fridge item : myRestaurant.getRefrigerators()) {
								int a;
								a = r.getRefrigerators().indexOf(item);
								System.out.println("refrigerator " + (a + 1));
								item.getInventory();
							}
							continue;
						}

						if (choice == 3) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								System.out.println("Please enter item:");
								Scanner itemFinder = new Scanner(System.in);
								String item = new String();
								while (true) {
									try {
										item = itemFinder.next();
										r.getFridgeInventory(choice - 1);
										r.addItem(choice - 1, item);
										break;

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									itemFinder.close();
								}

								System.out.println("Thank you. the current inventory of your fridge is:");
								r.getRefrigerators().get(choice - 1).getInventory();
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 4) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String item;
								System.out.println("Here is the inventory of the fridge you chose");
								r.getFridgeInventory(choice - 1);

								System.out.println("What item would you like to remove?");
								Scanner remove = new Scanner(System.in);
								while (true) {
									try {
										if (remove.hasNext()) {
											item = remove.next();
											r.removeItem(choice - 1, item);
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									remove.close();
								}
								System.out.println("enjoy your " + item);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 5) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								r.removeFridge(choice - 1);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 6) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String item;
								System.out.println("Here is the inventory of the fridge you chose");
								r.getFridgeInventory(choice - 1);

								System.out.println("What item would you like to remove?");
								Scanner remove = new Scanner(System.in);
								while (true) {
									try {
										if (remove.hasNext()) {
											item = remove.next();
											r.removeItem(choice - 1, item);
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									remove.close();
								}

								System.out.println("Which refrigerator would you like to add it to?");

								Scanner whichFridge2 = new Scanner(System.in);
								while (true) {
									try {
										if (whichFridge2.hasNextInt()) {
											choice = whichFridge2.nextInt();
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									whichFridge2.close();
								}
								if (choice > a) {
									System.out.println("That number is not available. Returning to options screen");
									continue;
								} else {
									r.addItem(choice - 1, item);
								}

								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 7) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String inName;
								String inPass;

								System.out.println("What is the inspector's name?");

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

								Inspector in = new Inspector(inName, inPass, "Inspector");
								r.addInspectorToFridge(choice - 1, in);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 8) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String inName;
								String inPass;

								System.out.println("What is the inspector's name?");
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

								Inspector in = new Inspector(inName, inPass, "Inspector");
								r.removeInspectorFromFridge(choice - 1, in);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 9) {
							numberOfRestaurants = restaurants.size();
							break;
						}

						numberOfRestaurants = restaurants.size();
						break;
					}
				}

				// log in for the restaurant owner
				if (choice == 2) {

					System.out.println("please enter your name");
					Scanner second = new Scanner(System.in);
					while (true) {
						try {
							name = second.next();

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
							pass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}

					Restaurant myRestaurant = restaurants.stream().filter(
							restaurant -> name.equals(restaurant.getName()) && pass.equals(restaurant.getPass()))
							.findAny().orElse(null);

					if (myRestaurant == null) {
						System.out.println("Sorry, usrname or password didn't match");
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

							String[] items = { it1, it2, it3 };

							Restaurant r = myRestaurant;
							Fridge f = new Fridge(items);
							myRestaurant.addFridge(f);
							int i = restaurants.indexOf(r);
							restaurants.remove(i);
							restaurants.add(i, myRestaurant);
							int s = restaurants.get(i).getRefrigerators().size();
							System.out.println("Thank you, there are now " + s
									+ " refrigerators in this location. What would you like to do next?");
							continue;
						}

						if (choice == 2) {
							Restaurant r = myRestaurant;
							for (Fridge item : myRestaurant.getRefrigerators()) {
								int a;
								a = r.getRefrigerators().indexOf(item);
								System.out.println("refrigerator " + (a + 1));
								item.getInventory();
							}
							continue;
						}

						if (choice == 3) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								System.out.println("Please enter item:");
								Scanner itemFinder = new Scanner(System.in);
								String item = new String();
								while (true) {
									try {
										item = itemFinder.next();
										r.getFridgeInventory(choice - 1);
										r.addItem(choice - 1, item);
										break;

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									itemFinder.close();
								}

								System.out.println("Thank you. the current inventory of your fridge is:");
								r.getRefrigerators().get(choice - 1).getInventory();
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 4) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String item;
								System.out.println("Here is the inventory of the fridge you chose");
								r.getFridgeInventory(choice - 1);

								System.out.println("What item would you like to remove?");
								Scanner remove = new Scanner(System.in);
								while (true) {
									try {
										if (remove.hasNext()) {
											item = remove.next();
											r.removeItem(choice - 1, item);
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									remove.close();
								}
								System.out.println("enjoy your " + item);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 5) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println(
									"there are " + a + " refrigerators. Choose the number of the one you want");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								r.removeFridge(choice - 1);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 6) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String item;
								System.out.println("Here is the inventory of the fridge you chose");
								r.getFridgeInventory(choice - 1);

								System.out.println("What item would you like to remove?");
								Scanner remove = new Scanner(System.in);
								while (true) {
									try {
										if (remove.hasNext()) {
											item = remove.next();
											r.removeItem(choice - 1, item);
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									remove.close();
								}

								System.out.println("Which refrigerator would you like to add it to?");

								Scanner whichFridge2 = new Scanner(System.in);
								while (true) {
									try {
										if (whichFridge2.hasNextInt()) {
											choice = whichFridge2.nextInt();
											break;
										}

									} catch (InputMismatchException ignore) {
										System.out.println("Invalid input. Input an integer value");
									}
									whichFridge2.close();
								}
								if (choice > a) {
									System.out.println("That number is not available. Returning to options screen");
									continue;
								} else {
									r.addItem(choice - 1, item);
								}

								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 7) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String inName;
								String inPass;

								System.out.println("What is the inspector's name?");

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

								Inspector in = new Inspector(inName, inPass, "Inspector");
								r.addInspectorToFridge(choice - 1, in);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 8) {
							int a = myRestaurant.getRefrigerators().size();
							System.out.println("there are " + a
									+ " refrigerators. Choose the number of the one you want to remove an item from");
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
							if (choice > a) {
								System.out.println("That number is not available. Returning to options screen");
								continue;
							} else {
								Restaurant r = myRestaurant;
								Restaurant old = myRestaurant;
								String inName;
								String inPass;

								System.out.println("What is the inspector's name?");
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

								Inspector in = new Inspector(inName, inPass, "Inspector");
								r.removeInspectorFromFridge(choice - 1, in);
								int i = restaurants.indexOf(old);
								restaurants.remove(i);
								restaurants.add(i, r);
								myRestaurant = r;
								continue;
							}
						}

						if (choice == 9) {
							numberOfRestaurants = restaurants.size();
							break;
						}

						numberOfRestaurants = restaurants.size();
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
							name = second.next();

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
							pass = third.next();
							break;
						} catch (InputMismatchException ignore) {
							System.out.println("Invalid input. Input a String value");
						}
						third.close();
					}

					Inspector myInspector = new Inspector(name, pass, accType);

					inspectors.add(myInspector);

					for (;;) {
						System.out.println("Welcome to your account! Please allow the restaurant owner to log in");
						System.out.println("please enter your name");
						Scanner rest1 = new Scanner(System.in);
						while (true) {
							try {
								restName = rest1.next();

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
								restPass = rest2.next();
								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							rest2.close();
						}

						Restaurant myRestaurant = restaurants.stream()
								.filter(restaurant -> restName.equals(restaurant.getName())
										&& restPass.equals(restaurant.getPass()))
								.findAny().orElse(null);

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
								int a = myRestaurant.getRefrigerators().size();
								for (int i = 0; i < a; i++) {
									if (myRestaurant.inspectorAllowed(i, myInspector)) {
										myRestaurant.getFridgeInventory(i);
										continue;
									}
								}

								continue;
							}
							if (choice == 2) {
								int a = myRestaurant.getRefrigerators().size();
								System.out.println(
										"there are " + a + " refrigerators. Choose the number of the one you want");
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
								if (choice > a) {
									System.out.println("That number is not available. Returning to options screen");
									continue;
								} else {
									Restaurant r = myRestaurant;
									Restaurant old = myRestaurant;
									String item;

									if (r.inspectorAllowed(choice - 1, myInspector)) {
										System.out.println("Here is the inventory of the fridge you chose");
										r.getFridgeInventory(choice - 1);
									} else {
										System.out.println(
												"Sorry. You do not have access to this refrigerator. Returning you to the options screen.");
										break;
									}

									System.out.println("What item would you like to remove?");
									Scanner remove = new Scanner(System.in);
									while (true) {
										try {
											if (remove.hasNext()) {
												item = remove.next();
												break;
											}

										} catch (InputMismatchException ignore) {
											System.out.println("Invalid input. Input an integer value");
										}
										remove.close();
									}

									if (myRestaurant.inspectorAllowed(choice - 1, myInspector)) {
										myRestaurant.removeItem(choice - 1, item);
										;
										continue;
									} else {
										System.out.println(
												"Sorry. You do not have permission to access that refrigerator or the item entered is not in the refrigerator. Returning you to the options screen");
										continue;
									}
								}
							}
							if (choice == 3) {
								System.out.println("Thank you for using the program");
								numberOfInspectors = inspectors.size();
							}
							break;
						}

						break;
					}

				}

				if (choice == 2) {
					if (choice == 1) {
						System.out.println("please enter your name");
						Scanner second = new Scanner(System.in);
						while (true) {
							try {
								name = second.next();

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
								pass = third.next();
								break;
							} catch (InputMismatchException ignore) {
								System.out.println("Invalid input. Input a String value");
							}
							third.close();
						}

						Inspector myInspector = inspectors.stream().filter(
								inspector -> name.equals(inspector.getName()) && pass.equals(inspector.getPass()))
								.findAny().orElse(null);

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
									restName = rest1.next();

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
									restPass = rest2.next();
									break;
								} catch (InputMismatchException ignore) {
									System.out.println("Invalid input. Input a String value");
								}
								rest2.close();
							}

							Restaurant myRestaurant = restaurants.stream()
									.filter(restaurant -> restName.equals(restaurant.getName())
											&& restPass.equals(restaurant.getPass()))
									.findAny().orElse(null);

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
									int a = myRestaurant.getRefrigerators().size();
									for (int i = 0; i < a; i++) {
										if (myRestaurant.inspectorAllowed(i, myInspector)) {
											myRestaurant.getFridgeInventory(i);
											continue;
										}
									}

									continue;
								}
								if (choice == 2) {
									int a = myRestaurant.getRefrigerators().size();
									System.out.println(
											"there are " + a + " refrigerators. Choose the number of the one you want");
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
									if (choice > a) {
										System.out.println("That number is not available. Returning to options screen");
										continue;
									} else {
										Restaurant r = myRestaurant;
										Restaurant old = myRestaurant;
										String item;

										if (r.inspectorAllowed(choice - 1, myInspector)) {
											System.out.println("Here is the inventory of the fridge you chose");
											r.getFridgeInventory(choice - 1);
										} else {
											System.out.println(
													"Sorry. You do not have access to this refrigerator. Returning you to the options screen.");
											break;
										}

										System.out.println("What item would you like to remove?");
										Scanner remove = new Scanner(System.in);
										while (true) {
											try {
												if (remove.hasNext()) {
													item = remove.next();
													break;
												}

											} catch (InputMismatchException ignore) {
												System.out.println("Invalid input. Input an integer value");
											}
											remove.close();
										}

										if (myRestaurant.inspectorAllowed(choice - 1, myInspector)) {
											myRestaurant.removeItem(choice - 1, item);
											;
											continue;
										} else {
											System.out.println(
													"Sorry. You do not have permission to access that refrigerator or the item entered is not in the refrigerator. Returning you to the options screen");
											continue;
										}
									}
								}
								if (choice == 3) {
									System.out.println("Thank you for using the program");
									numberOfInspectors = inspectors.size();
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
}
