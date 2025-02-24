package kr.library;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
public class Open {
    public static void main(String[] args) {
    	/*
        try {
            URL url = new URL("https://image.aladin.co.kr/product/26942/84/cover/k582730818_1.jpg"); // 책 이미지 URL
            BufferedImage image = ImageIO.read(url);

            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < height; y += 5) { // 디테일 조정
                for (int x = 0; x < width; x += 3) {
                    Color color = new Color(image.getRGB(x, y));
                    int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    char asciiChar = brightness > 128 ? ' ' : '#';
                    System.out.print(asciiChar);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    	
        String libraryArt =
                "  ██            ██  ██████    ██████       █████     █████     ██     ██ \n" +
                "  ██            ██  ██       ██  ██    ███   ██      ██   ██    ███   ██  ██\n" +
                "  ██            ██  ██████    █████       ███████   █████          ██ \n" +
                "  ██            ██  ██       ██  ██    ██     ██       ██   ██    ██        ██ \n" +
                "  ██████   ██  ██████    ██      ██   ██       ██   ██     ██       ██   \n" +
                "                                                             \n" +
                "               				📚 Welcome to the LIBRARY 📚                 ";

    	        System.out.println(libraryArt);
    	        System.out.println();
    	    }

        }
