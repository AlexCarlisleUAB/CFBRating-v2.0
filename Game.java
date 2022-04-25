public class Game
{
    private String winningTeam;
    private String losingTeam;

    public Game(String winTeam, String lossTeam)
    {
        this.winningTeam = winTeam;
        this.losingTeam = lossTeam;
    }

    public void setWinningTeam(String s)
    {
        this.winningTeam = s;
    }

    public String getWinningTeam()
    {
        return this.winningTeam;
    }

    public void setLosingTeam(String s)
    {
        this.losingTeam = s;
    }

    public String getLosingTeam()
    {
        return this.losingTeam;
    }
}