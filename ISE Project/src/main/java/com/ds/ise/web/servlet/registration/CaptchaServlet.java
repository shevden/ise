package com.ds.ise.web.servlet.registration;

import com.ds.ise.logic.captcha.CaptchaGenerator;
import com.ds.ise.logic.captcha.SessionCaptchaHandler;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Provides captcha image generation through the GET request.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebServlet("/captcha-generator")
public class CaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(CaptchaServlet.class);

    @EJB
    private SessionCaptchaHandler captchaHandler;

    @EJB
    private CaptchaGenerator captchaGenerator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ByteArrayOutputStream imgStream = new ByteArrayOutputStream();
        byte[] captchaBytes;
        try {
            BufferedImage captchaImage = new BufferedImage(168, 20, BufferedImage.TYPE_BYTE_INDEXED);

            // Receives string representation of captcha from request.
            String captchaString = captchaHandler.getCaptchaString(request);

            // Generate captcha image by the received string.
            captchaGenerator.generateCaptcha(captchaString, captchaImage);
            ImageIO.write(captchaImage, "jpeg", imgStream);
            captchaBytes = imgStream.toByteArray();

            // Clear any existing flag.
            request.getSession().removeAttribute("result");

            // Set appropriate http headers.
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            // Write the image to the client.
            OutputStream os = response.getOutputStream();

            if (captchaBytes != null) {
                os.write(captchaBytes);
            }
            os.flush();
            os.close();
        } catch (IOException ex) {
            LOG.error("Cannot write captcha image to the response.", ex);
        }
    }

}