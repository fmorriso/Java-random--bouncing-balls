import java.awt.*;

public class ColorExtensions
{

    private static final double LOWEST = 0;

    // returns a random Color
    public static Color getRandomColor()
    {
        final double HIGHEST = 255;
        final double RANGE = HIGHEST - LOWEST + 1;

        int r = (int) (Math.random() * RANGE + LOWEST);
        int g = (int) (Math.random() * RANGE + LOWEST);
        int b = (int) (Math.random() * RANGE + LOWEST);

        return new Color(r, g, b);
    }

    // returns a random color that is biased towards the brigher
    // portion of the light spectrum
    public static Color getRandomBrightColor()
    {
        final double HIGHEST = 255;
        final double RANGE = HIGHEST - LOWEST + 1;

        final int BRIGHT_COLOR_THRESHOLD = 128;

        StringBuilder hexColor = new StringBuilder();
        hexColor.append("#");
        // generate the three parts of the color
        for (int j = 0; j < 3; j++) {
            int k;
            // make sure we get something closer to white than black
            do {
                k = (int) (Math.random() * RANGE + LOWEST);
            } while (k < BRIGHT_COLOR_THRESHOLD);
            hexColor.append(String.format("%02X", k));
        }
        return Color.decode(hexColor.toString());
    }

    // return a color that is biased towards the darker portion of the light
    // spectrum
    public static Color getRandomDarkColor()
    {
        final double HIGHEST = 127;
        final double RANGE = HIGHEST - LOWEST + 1;

        int r = (int) (Math.random() * RANGE + LOWEST);
        int g = (int) (Math.random() * RANGE + LOWEST);
        int b = (int) (Math.random() * RANGE + LOWEST);

        return new Color(r, g, b);
    }

    // return a color that is biased towards the darker portion of the light
    // spectrum
    // and is sufficiently different from the comparison color by the specified
    // amount.
    public static Color getRandomDarkColor(Color compareColor, int byTotalRGB)
    {
        Color c;
        do {
            c = getRandomDarkColor();
        } while (!areSignificantlyDifferentColors(compareColor, c, byTotalRGB));

        return c;
    }

    // if two Color instances are significantly different from each other
    // in terms of their total RGB values, return true; otherwise, return false.
    public static boolean areSignificantlyDifferentColors(Color c1, Color c2, int byTotalRGB)
    {
        return getColorDifference(c1, c2) < byTotalRGB;
    }

    public static int getColorDifference(Color c1, Color c2)
    {
        int totalRGB1 = c1.getRed() + c1.getGreen() + c1.getBlue();
        int totalRGB2 = c2.getRed() + c2.getGreen() + c2.getBlue();
        return Math.abs(totalRGB1 - totalRGB2);
    }
}
