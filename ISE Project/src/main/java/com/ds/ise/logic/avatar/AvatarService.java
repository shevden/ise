package com.ds.ise.logic.avatar;


import com.ds.ise.constant.PathConstant;
import com.ds.ise.entity.User;
import com.ds.ise.exception.avatar.AvatarDeletionException;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Provides service to work with images that might be set as avatars.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Stateless
public class AvatarService {

    public static final String EMPTY_STRING = "";
    public static final String HEADER_NAME = "content-disposition";
    public static final String HEADER_START = "filename";
    public static final String DEFAULT_AVATAR = "defaultAvatar.png";

    private static final Logger LOG = Logger.getLogger(AvatarService.class);


    /**
     * Sets new film cover if it was changed since the edit form call.
     *
     * @param imagePart that will provide image.
     * @param user      that will be modified.
     */
    public void setUpAvatar(Part imagePart, User user) {
        LOG.debug("User avatar image update started.");
        String userEmail = user.getEmail();
        try {
            String filename = getFilename(imagePart);
            if (filename.isEmpty()) {
                user.setAvatarName(DEFAULT_AVATAR);
                LOG.debug("Default user avatar image has been set up.");

                return;
            }
            String[] partsOfFilename = filename.split("\\.");
            String coverExtension = partsOfFilename[partsOfFilename.length - 1];
            String newCoverPath = PathConstant.AVATAR_DIR + userEmail + "." + coverExtension;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Obtained image to set as user avatar: " + filename);
                LOG.debug("Will work with repository path equals to: " + PathConstant.AVATAR_DIR);
                LOG.debug("User email to process: " + userEmail);
                LOG.debug("Image extension to process: " + coverExtension);
                LOG.debug("New user avatar image path: " + newCoverPath);
            }
            if (user.getAvatarName() != null && !DEFAULT_AVATAR.equals(user.getAvatarName())) {
                File oldAvatar = new File(user.getAvatarName());
                boolean success = oldAvatar.delete();
                if (!success) {
                    AvatarDeletionException e = new AvatarDeletionException("Delete permission denied.");
                    LOG.error("Can not specified file from image repository.", e);
                    throw e;
                }
            }
            InputStream in = imagePart.getInputStream();
            File file = new File(newCoverPath);
            OutputStream out = new FileOutputStream(file);
            copyFile(in, out);
            user.setAvatarName(userEmail + "." + coverExtension);
        } catch (IOException ex) {
            LOG.error("Input data processing exception occurred while setting user avatar.", ex);
            user.setAvatarName(DEFAULT_AVATAR);
            LOG.debug("Default user avatar image has been set up.");
        }
        LOG.debug("User avatar image update finished.");
    }

    /**
     * Copies file from one stream to another.
     *
     * @param in  that act like input stream.
     * @param out that acts like output stream.
     * @throws IOException if some troubles while copying process occur.
     */
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        out.close();
    }

    /**
     * This method processes a part of network transfer as a file, that represents
     * new image for film cover. Returns filename of this image.
     *
     * @param part that represents image.
     * @return filename of the image.
     */
    private String getFilename(Part part) {

        // Processes null as empty multi-content part.
        if (part == null) {

            return EMPTY_STRING;
        }
        for (String cd : part.getHeader(HEADER_NAME).split(";")) {
            if (cd.trim().startsWith(HEADER_START)) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }

        return EMPTY_STRING;
    }
}
