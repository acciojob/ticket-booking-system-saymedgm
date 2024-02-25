package com.driver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicketBookingSystem {
	private Queue<String> ticketQueue;
	private Deque<String> cancelledTickets;

	public TicketBookingSystem() {
		ticketQueue = new LinkedList<String>();
		cancelledTickets = new ArrayDeque<String>();
	}

	public void bookTicket(String ticket) {
		if (ticketQueue.contains(ticket)) {
			throw new IllegalArgumentException("Ticket already booked.");
		}

		if (cancelledTickets.contains(ticket)) {
			cancelledTickets.remove(ticket);
		}

		ticketQueue.add(ticket);
		System.out.println("Ticket '" + ticket + "' booked successfully.");
	}

	public void cancelTicket(String ticket) {
		if (!ticketQueue.contains(ticket)) {
			throw new IllegalArgumentException("Ticket is not booked.");
		}

		ticketQueue.remove(ticket);
		cancelledTickets.add(ticket);
		System.out.println("Ticket '" + ticket + "' cancelled successfully.");
	}

	public void displayCurrentBookings() {
		if (ticketQueue.isEmpty()) {
			System.out.println("No tickets booked.");
		} else {
			System.out.println("Current Bookings:");
			for (String ticket : ticketQueue) {
				System.out.println("- " + ticket);
			}
		}
	}

	public static void main(String[] args) {
		TicketBookingSystem bookingSystem = new TicketBookingSystem();
		Scanner scanner = new Scanner(System.in);

		boolean exit = false;
		while (!exit) {
			System.out.println("1. Book Ticket");
			System.out.println("2. Cancel Ticket");
			System.out.println("3. Display Current Bookings");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					System.out.print("Enter Ticket ID: ");
					scanner.nextLine(); // Consume newline character
					String ticketId = scanner.nextLine();
					try {
						bookingSystem.bookTicket(ticketId);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 2:
					System.out.print("Enter Ticket ID to cancel: ");
					scanner.nextLine(); // Consume newline character
					String cancelTicketId = scanner.nextLine();
					try {
						bookingSystem.cancelTicket(cancelTicketId);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					bookingSystem.displayCurrentBookings();
					break;

				case 4:
					exit = true;
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
	}
}