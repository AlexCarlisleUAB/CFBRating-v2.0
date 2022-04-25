/*
TESTING PROGRESS:

THROUGH WEEK 11
*/

/*
PROGRAM SUMMARY

1.initialize method (COMPLETED)
    a. create team objects and save them to the arraylist of teams
    b. create game objects and save them to the arraylist of games
2. process games method (COMPLETED)
    a. for each game in gameList, 
        I. add 1 to every winning teams win count
        II. add 1 to every losing teams loss count
    b. if confName of winning team != confName of losing team
        I. add 1 win to winning team's conference's record (this will help with conference strength)
        II. add 1 loss to losing teams conference's record (this will help with conference strength)
3. update ratings method
    a. for each game in gameList,
        I. for winning teams, add the W/L of your opponent to oppWins and oppLosses
        II. for losing teams, also add W/L of your opponent to oppWins and oppLosses
    b. for all teams in teamList,
        I. calculate sos = oppWins/(oppWins + oppLosses)
        II. calculate rating = 55% record and 25% SOS and 20% conf strength
4. display method
    a. use sorting algorithm to sort the teams in the teamList by highest rating
    b. display list in either console or GUI text box

*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class CFBRating
{
    static ArrayList<Team> teamList = new ArrayList<>();
    static ArrayList<Game> gameList = new ArrayList<>();
    static ArrayList<Conference> confList = new ArrayList<>();

    public static void main(String [] args) throws FileNotFoundException
    {
        initialize(); //TESTED
        processGames(); //TESTED
        updateRatings(); //TESTING
        displayRatings();
    }

    //ADDS TEAMS FROM TEAM.TXT TO AN ARRAYLIST OF TEAM OBJECTS
    public static void initialize() throws FileNotFoundException
    {
        //initializes teams first
        File teamFile = new File("C:\\Users\\Alex\\Desktop\\Personal Projects\\CFB Projects\\CFB Rating\\team.txt");
        Scanner scTeam = new Scanner(teamFile);
        scTeam.useDelimiter(",");

        while(scTeam.hasNextLine())
        {
            String line = scTeam.nextLine();
            Scanner s = new Scanner(line);
            s.useDelimiter(",");
            String name = s.next();
            String abb = s.next();
            String conf = s.next();
            //System.out.println(name + " " + conf); //TESTING PURPOSES
            Team t = new Team(name, abb, conf, 0, 0, 0.0, 0.0, 0, 0);
            teamList.add(t);
            s.close();
        }

        scTeam.close();

        //initializing games now
        File gameFile = new File("C:\\Users\\Alex\\Desktop\\Personal Projects\\CFB Projects\\CFB Rating\\game.txt");
        Scanner scGame = new Scanner(gameFile);
        scGame.useDelimiter(",");

        while(scGame.hasNext())
        {
            String line = scGame.nextLine();
            Scanner s = new Scanner(line);
            s.useDelimiter(",");
            String winningTeam = s.next();
            String losingTeam = s.next();
            //System.out.println(winningTeam + " beats " + losingTeam); //TESTING PURPOSES
            Game g = new Game(winningTeam, losingTeam);
            gameList.add(g);
            s.close();
        }

        scGame.close();

        //initializing conferences
        Conference sec = new Conference("SEC", 0, 0);
        Conference acc = new Conference("ACC", 0, 0);
        Conference b10 = new Conference("Big Ten", 0, 0);
        Conference b12 = new Conference("Big XII", 0, 0);
        Conference pac = new Conference("PAC", 0, 0);
        Conference aac = new Conference("AAC", 0, 0);
        Conference mwc = new Conference("MWC", 0, 0);
        Conference cusa = new Conference("CUSA", 0, 0);
        Conference sbc = new Conference("SBC", 0, 0);
        Conference mac = new Conference("MAC", 0, 0);
        Conference ind = new Conference("Independent", 0, 0);
        Conference fcs = new Conference("FCS", 0, 0);

        confList.add(sec);
        confList.add(acc);
        confList.add(b10);
        confList.add(b12);
        confList.add(pac);
        confList.add(aac);
        confList.add(mwc);
        confList.add(cusa);
        confList.add(sbc);
        confList.add(mac);
        confList.add(ind);
        confList.add(fcs);

    }

    public static void processGames() 
    {
        for (int i = 0; i < gameList.size();)
        {
            Game g = gameList.get(i);
            String winTeam = g.getWinningTeam();
            String lossTeam = g.getLosingTeam();

            Team winningTeam = new Team("", "", "", 0, 0, 0, 0, 0, 0);
            Team losingTeam = new Team("", "", "", 0, 0, 0, 0, 0, 0);
            Conference winConf = new Conference("", 0, 0);
            Conference lossConf = new Conference("", 0, 0);


            for(int j = 0; j < teamList.size();)
            {
                Team t = teamList.get(j);
                String teamName = t.getName();
                String teamAbb = t.getAbbreviation();
                //System.out.println(lossTeam + " " + teamName); //TESTING PURPOSES
                if (teamName.equals(winTeam) || teamAbb.equals(winTeam))
                {
                    //System.out.println("Winning team found"); //TESTING PURPOSES
                    double prevWins = t.getWins();
                    double newWins = prevWins + 1;
                    t.setWins(newWins);
                    winningTeam = t;
                    String conf = t.getConference();
                    for (int k = 0; k < confList.size(); )
                    {
                        Conference temp = confList.get(k);
                        if (temp.getName().equals(conf))
                        {
                            winConf = temp;
                            winConf.setWins(winConf.getWins() + 1);
                        }
                        k = k + 1;
                    }
                }
                if (teamName.equals(lossTeam) || teamAbb.equals(lossTeam))
                {
                    //System.out.println("Losing team found"); //TESTING PURPOSES
                    double prevLoss = t.getLosses();
                    double newLoss = prevLoss + 1;
                    t.setLosses(newLoss);
                    losingTeam = t;
                    String conf = t.getConference();
                    for (int k = 0; k < confList.size(); )
                    {
                        Conference temp = confList.get(k);
                        if (temp.getName().equals(conf))
                        {
                            lossConf = temp;
                            lossConf.setLosses(lossConf.getLosses() + 1);
                        }
                        k = k + 1;
                    }
                }

                j = j + 1;
            }

            if (winConf != lossConf) 
            {
                for (int k = 0; k < confList.size();)
                {
                    Conference c = confList.get(k);
                    if (winningTeam.getConference() == c.getName())
                    {
                        c.setWins(c.getWins() + 1);
                    }
                    else if (losingTeam.getConference() == c.getName())
                    {
                        c.setLosses(c.getLosses() + 1);
                    }
                    k = k + 1;
                }
            }

            i = i + 1;
        }
        String fcs = "FCS";
        for (int i = 0; i < teamList.size();) //setting FCS record
        {
            Team t = teamList.get(i);
            if (t.getName().equals(fcs))
            {
                t.setWins(t.getWins()/12);
                t.setLosses(t.getLosses()/12);
                System.out.println("FCS: " + t.getWins() + "-" + t.getLosses());
            }
            i = i + 1;
        }
        for (int p = 0; p < teamList.size();)//FOR TESTING PURPOSES
        {
            Team t =teamList.get(p);
            System.out.println(t.getName() + " " + t.getOppWins() + "-" + t.getOppLosses());
            p = p + 1;
        }

        for (int p = 0; p < confList.size();) //FOR TESTING PURPOSES
        {
            Conference c = confList.get(p);
            System.out.println(c.getName() + " " + c.getWins() + "-" + c.getLosses());
            p = p + 1;
        }
        
    } 

    public static void updateRatings() //This is a seperate method so that the updated W/L of each team can be taken into account
    {   
        for(int i = 0; i < gameList.size();)
        {
            Team winTeam = new Team("", "", "", 0, 0, 0, 0, 0, 0);
            Team lossTeam = new Team("", "", "", 0, 0, 0, 0, 0, 0);
            Game g = gameList.get(i);
            for (int j = 0; j < teamList.size();)
            {
                Team t = teamList.get(j);
                if (g.getWinningTeam().equals(t.getName()))
                {
                    winTeam = t;
                }
                else if (g.getLosingTeam().equals(t.getName()))
                {
                    lossTeam = t;
                }
                j = j + 1;
            }
            winTeam.setOppWins(winTeam.getOppWins() + lossTeam.getWins());
            winTeam.setOppLosses(winTeam.getOppLosses() + lossTeam.getLosses());
            lossTeam.setOppWins(lossTeam.getOppWins() + winTeam.getWins());
            lossTeam.setOppLosses(lossTeam.getOppLosses() + winTeam.getLosses());

            i = i + 1;
        }

        for (int k = 0; k < teamList.size();)
        {
            Team x = teamList.get(k);
            if (x.getConference() != "Independent")
            {
                for (int r = 0; r < confList.size();)
                {
                    Conference c = confList.get(r);
                    if (x.getConference().equals(c.getName()))
                    {
                        double sos = x.getOppWins()/(x.getOppWins() + x.getOppLosses());
                        double winPer = x.getWins()/(x.getWins() + x.getLosses());
                        double confWinPer = c.getWins()/(c.getWins() + c.getLosses()); 
                        double rating = (.35 * winPer) + (.35 * sos) + (.30 * confWinPer);
                        x.setRating(rating);

                        //System.out.println(x.getName() + " " + x.getRating() + " " + winPer + " " +  confWinPer + " " + sos);
                    }
                    r = r + 1;
                }
                
            }
            else 
            {
                double sos = x.getOppWins()/(x.getOppWins() + x.getOppLosses());
                double winPer = x.getWins()/(x.getWins() + x.getLosses());
                double rating = (.35 * winPer) + (.65 * sos);
                x.setRating(rating);
            }
            
            k = k + 1;
        }
    }

    public static void displayRatings()
    {
        teamList.sort(Comparator.comparing(Team::getRating).reversed()); //if I need it reversed: add a .reversed() inside the outer ()

        System.out.println("Rank                  Team   wins loss  rating");
        System.out.println("------------------------------------------------");

        for(int i = 0; i < teamList.size();)
        {
            int rank = i + 1;
            if(rank == 26)
            {
                System.out.println();
                System.out.println("-------------------NEXT FIVE--------------------");
            }
            else if (rank == 31)
            {
                System.out.println();
                System.out.println("-------------------THE REST---------------------");
            }
            Team t = teamList.get(i);
            System.out.format("%4d   %20s    %.0f   %.0f    %6f   %n", rank, t.getName(), t.getWins(), t.getLosses(), t.getRating()); 
            i = i + 1;
        }
    }
}