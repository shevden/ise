package com.ds.ise.constant;

public final class PathConstant {

    private PathConstant(){
        throw new AssertionError("Must not be instantiated.");
    }

    public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin.jsp";
    public static final String PAGE_ANSWER = "/WEB-INF/jsp/answer.jsp";
    public static final String PAGE_CLIENT = "/WEB-INF/jsp/client.jsp";
    public static final String PAGE_NOTE_LIST = "/WEB-INF/jsp/note_list.jsp";
    public static final String PAGE_SAVE_NOTE = "/WEB-INF/jsp/save_note.jsp";
    public static final String PAGE_PLAIN_NOTE = "/WEB-INF/jsp/plain_note.jsp";
    public static final String PAGE_ATTACHED_NOTE = "/WEB-INF/jsp/attached_note.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/registration.jsp";

    // Command values that process POST requests.
    public static final String REQUEST_REGISTRATION = "registration";
    public static final String REQUEST_CLIENT = "client";
    public static final String REQUEST_NO_PERMISSION = "noPermission";
    public static final String REQUEST_NOTE_LIST = "noteList";

    // Common attribute names.
    public static final String CURRENT_URL_PATH_ATTR_NAME = "currentRequestPath";
    public static final String USER_ATTR_NAME = "user";
    public static final String AVATAR_DIR = "images/avatars/";

}
