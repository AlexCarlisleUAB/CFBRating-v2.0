public class Conference 
{
    private String name;
    private double wins;
    private double losses;

    public Conference(String n, double w, double l)
    {
        this.name = n;
        this.wins = w;
        this.losses = l;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public String getName()
    {
        return this.name;
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
}
