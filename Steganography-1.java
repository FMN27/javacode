import java.awt.Color;
public class Steganography
{
    public static void main(String[] args)
    {
        //Notice how the picture needs to be created!
        //Don't change the String value in the Picture parameter.
        Picture beach = new Picture("beach.jpg");
        Picture revealed = revealePicture(beach);
        revealed.write("revealed.png");
        
        //beach.explore();
        //beach = testClearLow(beach);
        //beach.write("test.png");


    }

        //new method revealPicture
        public static Picture revealPicture(Picture p){
            Picture copy = new Picture(p);
            Pixel[][] pix_new = copy.getPixels2D();
            Pixel[][] pix_old = p.getPixels2D();

            //write nested loops
            //to visit every pixel in pix_new
            for(int r = 0; r < pix_new.length; r++){
                for(int r = 0; r < pix_new[0].length; c++){
                    Pixel x = pix_new[r][c];


                    //get the color of the old picture
                    Pixel x_old = pix_old[r][c];
                    Color c_old = x_old.getColor();
                    
                    //Turn that color into RGB values
                    int oldR = c_old.getRed();
                    int oldG = c_old.getGreen();
                    int oldB = c_old.getBlue();

                    //calculate the new color
                    int newR = oldR % 4 * 64;
                    int newG = oldG % 4 * 64;
                    int newB = oldB % 4 * 64;

                    //update Pixel x
                    x.setRed(newR);
                    x.setBlue(newB);
                    x.setGreen(newG);
                }
            }
            return copy;
        }    
            

        }
    
    //int i = 183;
        //i/=4;
        //i*=4;
        //System.out.println(i);

    
    
    /**
    * Clear the lower (rightmost) two bits in a pixel.
    */
    public static void clearLow( Pixel p )
    {
        /* To be implemented */
    }
    
    /**
     * Returns a new Picture object with the lowest two bits of each pixel cleared.
     */
    public static Picture testClearLow(Picture p)
    {
        /* To be implemented */
        Picture p2 = new Picture(p);

        Pixel[][] pix = p2.getPixels2D();

        //write loops to go to every pixel in pix
        for(int r = 0; r < pix.length; r++){ //loop through all the rowa
            for(int c = 0; c < pix[0].length; c++){ //loop through all the cols
                Pixel p = pix[r][c];
            }
        }
        return p2;
    }
    
    
}

public static boolean canHide(Picture source, Picture secret){
    int sourceWidth = source.getWidth();
    int sourceHeight = source.getHeight();
    int secretWidth = secret.getWidth();
    int secretHeight = secret.getHeight();
//the ints are there so that a comparison can be made
return(sourceWidth == secretWidth) && (sourceHeight == secretHeight); 
}
//the code above is the static method canHide that takes two pictures (source and secret) and
//checks picture sizes to make sure you can hide the secret within source. 

public static Picture hidePicture(Picture source, Picture secret){
    if (source == null || secret == null) {
        System.out.println("Hey, you forgot to give me pictures to work with!");
        return null;
    }

    if (source.getHeight() != secret.getHeight() || source.getWidth() != secret.getWidth()) {
        System.out.println("This won't work unless the pictures are the same size");
        return null;
    } 
    //the if statements are there to make it easier for the user to fix an errors that are made
    Picture combined = new Picture(source.getWidth(), source.getHeight());
        
    for (int y = 0; y < source.getHeight(); y++) {
        for (int x = 0; x < source.getWidth(); x++) {
            Pixel sourcePixel = source.getPixel(x, y);
            Pixel secretPixel = secret.getPixel(x, y);
            
            int red = (sourcePixel.getRed() & 0xF0) | (secretPixel.getRed() >> 4);
            int green = (sourcePixel.getGreen() & 0xF0) | (secretPixel.getGreen() >> 4);
            int blue = (sourcePixel.getBlue() & 0xF0) | (secretPixel.getBlue() >> 4);
            //the colors are apart of a mirrage technique that hides secret information in the least significant bits of the source image
        
            Pixel resultPixel = combined.getPixel(x, y);
            resultPixel.setColor(new Color(red, green, blue));
        }
    }
    
    return combined;
}
//the for loop is responsible for hiding the secret image within the main image
