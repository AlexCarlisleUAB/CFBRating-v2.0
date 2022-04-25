public class Team
{
    private String name; //for identifying purposes
    private String abbreviation; //to simplify data entries
    private String conference;
    private double wins; 
    private double losses;
    private double rating; //calculated in CFBRating.java
    private double sos; //also calculated in CFBRating.java
    private double oppWins; 
    private double oppLosses;

    public Team(String n, String abb, String conf, int w, int l, double r, double s, int oW, int oL)
    {

        this.name = n;
        this.abbreviation = abb;
        this.conference = conf;
        this.wins = w;
        this.losses = l;
        this.rating = r;
        this.sos = s;
        this.oppWins = oW;
        this.oppLosses = oL;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public String getName()
    {
        return this.name;
    }

    public void setAbbreviation(String abb)
    {
        this.abbreviation = abb;
    }

    public String getAbbreviation()
    {
        return this.abbreviation;
    }

    public void setConference(String n)
    {
        this.conference = n;
    }

    public String getConference()
    {
        return this.conference;
    }

    public void setWins(double w)
    {
        this.wins = w;
    }

    public double getWins()
    {
        return this.wins;
    }

    public void setLosses(double l)
    {
        this.losses = l;
    }

    public double getLosses()
    {
        return this.losses;
    }

    public void setRating(double r)
    {
        this.rating = r;
    }

    public double getRating()
    {
        return this.rating;
    }

    public void setSOS(double s)
    {
        this.sos = s;
    }

    public double getSOS()
    {
        return this.sos;
    }

    public void setOppWins(double w)
    {
        this.oppWins = w;
    }

    public double getOppWins()
    {
        return this.oppWins;
    }

    public void setOppLosses(double l)
    {
        this.oppLosses = l;
    }

    public double getOppLosses()
    {
        return this.oppLosses;
    }
}