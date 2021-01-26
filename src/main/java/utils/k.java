package utils;

public class k {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static boolean d = true;


    public static void d(Class<?> cls, String str) {
        System.out.println("method d");
    }

    private static String a(String str) {
        if (str == null || str.length() <= 4000) {
            return str;
        }
        return "打印信息过长，只截取了一段，有需要完整再打断点，或者另外打印---->" + str.substring(0, 3900);
    }

    private static String e(Class<?> cls, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(">>>>");
        sb.append(cls != null ? cls.getSimpleName() : "not_inputclassname");
        sb.append(">>>>>> ");
        sb.append(str);
        sb.append(" <<<<<<");
        return sb.toString();
    }
}