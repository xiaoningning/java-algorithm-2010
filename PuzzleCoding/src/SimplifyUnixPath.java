import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p/>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyUnixPath {
    public static void main(String[] args) {

        String s1 = "//../";
        System.out.println(simplifyUnixPath(s1));

        String s2 = "/a/./.b//../c/";
        System.out.println(simplifyUnixPath(s2));

        String s3 = "/home/of/foo/../../bar/../is/.//here/.";
        System.out.println(simplifyUnixPath(s3));

        String s4 = "/home/of/foo/../";
        System.out.println(simplifyUnixPath(s4));

    }

    public static String simplifyUnixPath(String path) {
        Stack<String> pathStack = new Stack<String>();
        String dir = "";

        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c != '/') {
                dir += c;
                continue;
            }
            // only "/"
            if (dir.length() == 0)
                continue;
            if (dir.charAt(0) == '.') {
                if (dir.length() > 1) {
                    if (dir.charAt(1) == '.') {
                        // dir /..ab/
                        if (dir.length() > 2) {
                            pathStack.push(dir);
                        } else {
                            // dir /../
                            if (pathStack.size() > 0) {
                                pathStack.pop();
                            }
                        }
                    } else {
                        // dir /.ab/
                        pathStack.push(dir);
                    }
                }
                // current path, do nothing
            } else { // one dir
                pathStack.push(dir);
            }

            // clear dir for next one
            dir = "";
        }

        String finalPath = "";
        for (int i = 0; i < pathStack.size(); i++) {
            finalPath += "/" + pathStack.get(i);
        }
        if (pathStack.size() == 0)
            finalPath = "/";

        return finalPath;

    }
}
