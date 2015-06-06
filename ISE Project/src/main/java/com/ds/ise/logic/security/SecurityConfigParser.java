package com.ds.ise.logic.security;

import com.ds.ise.entity.additional.UserRole;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides Security XML file parsing and
 * security configuration TOs forming.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class SecurityConfigParser {

    public static final String URL_PATTERN_ATTR_NAME = "url-pattern";
    public static final String ROLE_ATTR_NAME = "role";

    /**
     * Parses Security XML file and forms security configuration TOs.
     *
     * @param path that specifies what XML file to process.
     * @return {@code List} of transport objects with security configuration.
     * @throws Exception
     */
    public Set<UserRoleConfig> parseUserRoleConfigs(String path) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(SecurityConfigParser.class.getClassLoader().getResourceAsStream(path));
        Set<UserRoleConfig> configList = new HashSet<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                UserRoleConfig config = new UserRoleConfig();
                NodeList childNodes = node.getChildNodes();
                Set<UserRole> roles = new HashSet<>();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode instanceof Element) {
                        String content = childNode.getLastChild().getTextContent().trim();
                        switch (childNode.getNodeName()) {
                            case URL_PATTERN_ATTR_NAME:
                                config.setUrlPattern(content);
                                break;
                            case ROLE_ATTR_NAME:
                                roles.add(UserRole.getByName(content));
                                break;
                        }
                    }
                }
                config.setRoles(roles);
                configList.add(config);
            }
        }

        return configList;
    }
}

