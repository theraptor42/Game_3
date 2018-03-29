package Assets;

public abstract class Location
{
    public enum LocationType
    {
        City, Town, Plain, Cave, Forrest, Mountain
    }
    LocationType locationType;
    String name;


}