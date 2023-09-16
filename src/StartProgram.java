import java.util.List;
import java.util.Scanner;

import controller.PhotoSessionHelper;
import model.PhotoSession;

public class StartProgram {

    static Scanner in = new Scanner(System.in);
    static PhotoSessionHelper psh = new PhotoSessionHelper();

    private static void addASession() {
        System.out.print("Enter the client's name: ");
        String client = in.nextLine();
        System.out.print("Enter the session location: ");
        String location = in.nextLine();
        PhotoSession toAdd = new PhotoSession(client, location);
        psh.insertSession(toAdd);
    }

    private static void deleteASession() {
        System.out.print("Enter the client's name to delete: ");
        String clientName = in.nextLine();
        PhotoSession toDelete = new PhotoSession(clientName, "");
        psh.deleteSession(toDelete);
    }

    private static void editASession() {
        System.out.println("Enter the client's name: ");
        String clientName = in.nextLine();
        List<PhotoSession> foundSessions = psh.searchForSessionByClientName(clientName);

        if (!foundSessions.isEmpty()) {
            System.out.println("Found Sessions.");
            for (PhotoSession s : foundSessions) {
                System.out.println(s.getId() + " : " + s.toString());
            }
            System.out.print("Which ID to edit: ");
            int idToEdit = in.nextInt();
            in.nextLine();

            PhotoSession toEdit = psh.searchForSessionById(idToEdit);
            System.out.println("Retrieved session for " + toEdit.getClientName() + " at " + toEdit.getSessionLocation());
            System.out.println("1 : Update Client Name");
            System.out.println("2 : Update Session Location");
            int update = in.nextInt();
            in.nextLine();

            if (update == 1) {
                System.out.print("New Client Name: ");
                String newClient = in.nextLine();
                toEdit.setClientName(newClient);
            } else if (update == 2) {
                System.out.print("New Session Location: ");
                String newLocation = in.nextLine();
                toEdit.setSessionLocation(newLocation);
            }

            psh.updateSession(toEdit);

        } else {
            System.out.println("---- No results found");
        }
    }

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        boolean goAgain = true;
        System.out.println("--- Welcome to PhotographyBiz Management! ---");
        while (goAgain) {
            System.out.println("* Select an item:");
            System.out.println("* 1 -- Add a session");
            System.out.println("* 2 -- Edit a session");
            System.out.println("* 3 -- Delete a session");
            System.out.println("* 4 -- View all sessions");
            System.out.println("* 5 -- Exit the program");
            System.out.print("* Your selection: ");
            int selection = in.nextInt();
            in.nextLine();

            if (selection == 1) {
                addASession();
            } else if (selection == 2) {
                editASession();
            } else if (selection == 3) {
                deleteASession();
            } else if (selection == 4) {
                viewAllSessions();
            } else {
                psh.cleanUp();
                System.out.println("   Goodbye!   ");
                goAgain = false;
            }
        }
    }

    private static void viewAllSessions() {
        List<PhotoSession> allSessions = psh.showAllSessions();
        for(PhotoSession singleSession : allSessions) {
            System.out.println(singleSession.returnSessionDetails());
        }
    }
}