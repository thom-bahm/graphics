import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * Thomas Bahmandeji
 * 
 * This project is a follow along based on Joni Salonen's article, "Let's Draw the mandelbrot set!" 
 * Article available at: https://jonisalonen.com/2013/lets-draw-the-mandelbrot-set/
 * 
 *
 */

public class Mandelbrot {
	
	public Mandelbrot () {
		
	}
	
	public static void mandelbrotBW () throws Exception{
        int width = 1920, height = 1080, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0x000000, white = 0xFFFFFF;
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	// c_re and c_im: given a pixel (col, row) subtract half of image height from vertical coord, half of width from horizontal coord.
            	//radius of set should be within circle of r = 2, so length of image should be 4
            	double c_re = (col - width/2)*4.0/width;
                double c_im = (row - height/2)*4.0/width;
                double x = 0, y = 0;
                int iterations = 0;
                //
                while (x*x+y*y < 4 && iterations < max) {
                	// (x^2 - y^2 + c_re)
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im; //y = 2xy + c_im
                    x = x_new;
                    iterations++;
                } 
                // if the
                if (iterations < max) image.setRGB(col, row, white);
                else image.setRGB(col, row, black);
            }
        }

        ImageIO.write(image, "png", new File("/Users/thomas/desktop/mandelbrot.png"));
	}
	
	public static void mandelbrotColor () throws Exception{
        int width = 1920*2, height = 1080*2, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0x000000, white = 0xFFFFFF;
        int[] colors = new int[max];
        for (int i = 0; i < max; i++) {
        	colors[i] = Color.HSBtoRGB((i/256.f), 1, 1);
        }
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	// c_re and c_im: given a pixel (col, row) subtract half of image height from vertical coord, half of width from horizontal coord.
            	//radius of set should be within circle of r = 2, so length of image should be 4
            	double c_re = (col - width/2)*4.0/width;
                double c_im = (row - height/2)*4.0/width;
                double x = 0, y = 0;
                int iterations = 0;
                // while x^2 + y^2 < 4 (z^2 < length of image)
                while (x*x+y*y < 4 && iterations < max) {
                	// (x^2 - y^2 + c_re)
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im; //y = 2xy + c_im
                    x = x_new;
                    iterations++;
                } 
                // if the sequence does not diverge to infinity, set it to some color. Else black.
                if (iterations < max) image.setRGB(col, row, colors[iterations]);
                else image.setRGB(col, row, black);
            }
        }

        ImageIO.write(image, "png", new File("/Users/thomas/desktop/mandelbrot/mandelbrotColored_4k_1k_.png"));
	}

    
}
