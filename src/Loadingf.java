import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
 
public class Loadingf {
 
 
 /*
        public static void main(String[] args) throws IOException {
 
                BufferedImage hugeImage = ImageIO.read(new File("qwe.png"));
                int[][] result = convertTo2DWithoutUsingGetRGB(hugeImage);
 
 
                int ni = result.length;
                int nj = result[0].length;
            for (int i = 0; i < ni; i++) {
                for (int j = 0; j <nj; j++) {
                    if(result[i][j] != -1) {
                        System.out.print("1");
                    }else{
                        System.out.print("0");
 
                    }
                }
                System.out.println();
            }
        }
 */
 
 
    private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {
 
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
 
        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
 
        return result;
    }
 
 
    }