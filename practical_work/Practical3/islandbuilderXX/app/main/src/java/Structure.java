package ...;

/**
 * Represents a possible structure to be placed on the map. A structure simply contains a drawable
 * int reference, and a string label to be shown in the selector.
 */
public class Structure
{
    private final int drawableId;
    private String label;

    public Structure(int drawableId, String label)
    {
        this.drawableId = drawableId;
        this.label = label;
    }

    public int getDrawableId()
    {
        return drawableId;
    }

    public String getLabel()
    {
        return label;
    }
}
