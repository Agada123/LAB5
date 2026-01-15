package org.example.Route.utils;

import java.util.Scanner;


public class InputManager {
    private Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }


    public String readLine() {
        return scanner.nextLine();
    }


    public String readNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            }
        } while (input.isEmpty());
        return input;
    }


    public int readInt(String prompt, int lowerBound) {
        int value;
        while (true) {
            try {
                System.out.print(prompt + " ");
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value > lowerBound) {
                    return value;
                } else {
                    System.out.println("Value must be greater than " + lowerBound + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }


    public Integer readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }


    public float readFloat(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid float value. Try again.");
            }
        }
    }


    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid double value. Try again.");
            }
        }
    }


    public Double readDouble(String prompt, Double max) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value <= max) {
                    return value;
                } else {
                    System.out.println("Value must be less than or equal to " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid double value. Try again.");
            }
        }
    }



    public Long readLong(String prompt, Long max) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                long value = Long.parseLong(scanner.nextLine().trim());
                if (value <= max) {
                    return value;
                } else {
                    System.out.println("Value must be less than or equal to " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid long value. Try again.");
            }
        }
    }


    public Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid long value. Try again.");
            }
        }
    }
}