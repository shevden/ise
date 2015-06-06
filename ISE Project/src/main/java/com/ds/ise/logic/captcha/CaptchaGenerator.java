package com.ds.ise.logic.captcha;

import javax.ejb.Stateless;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Generates captcha string and image.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Stateless
public class CaptchaGenerator {

    private final Random random;

    public CaptchaGenerator() {
        random = new Random(System.currentTimeMillis());
    }

    /**
     * Generates captcha image to the specified object by the obtained string.
     *
     * @param captchaString that will be reflected on the image.
     * @param captchaImage  that will be fulfilled.
     */
    public void generateCaptcha(String captchaString, BufferedImage captchaImage) {
        Graphics2D g2d = captchaImage.createGraphics();
        g2d.setPaint(new Color(255, 239, 213));
        g2d.fillRect(0, 0, captchaImage.getWidth(), captchaImage.getHeight());
        g2d.setColor(new Color(107, 142, 35));
        Font font = new Font("SansSerif", Font.BOLD, 14);
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics(font);
        int width = fm.stringWidth(captchaString);
        g2d.drawString(captchaString, captchaImage.getWidth() / 2 - width / 2, 15);
        fillWithRandomPoints(g2d, captchaImage.getWidth(), captchaImage.getHeight());
        g2d.dispose();
    }

    /**
     * Generates random string from numbers to display as captcha.
     *
     * @return random generated string.
     */
    public String generateCaptchaString() {
        int captchaNumber = random.nextInt();
        if (captchaNumber < 0) {
            captchaNumber *= -1;
        }

        return Integer.toString(captchaNumber);
    }

    private void fillWithRandomPoints(Graphics2D g2d, int width, int height) {
        final int POINTS_NUMBER = 45;
        for (int i = 0; i < POINTS_NUMBER; ++i) {
            setRandomPoint(g2d, width, height);
        }
    }

    private void setRandomPoint(Graphics2D g2d, int width, int height) {
        final int CIRCLE_DIAMETER = 3;
        int xPosition = random.nextInt(width);
        int yPosition = random.nextInt(height);
        g2d.fillOval(xPosition, yPosition, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

}