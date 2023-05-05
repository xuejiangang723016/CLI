package com.demo;

import com.sun.corba.se.impl.encoding.CDROutputObject;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xue
 * @version 1.0.0
 * @ClassName CLiTest.java
 * @Description TODO
 * @createTime 2023年03月24日 11:30:00
 */
public class CLiTest {


    public static Map<String, Boolean> parseCommandHelp(String helpText) {
        Map<String, Boolean> commandMap = new HashMap<>();

        // 匹配可选命令和参数的正则表达式
        //   String regex = "^\\s*\\-\\-?(\\w+)(?:[ =](\\w+))?\\s*(.*)$";
        String regex2 = "^-\\w\\s\\w+$/g";
        Pattern pattern = Pattern.compile(regex2, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(helpText);

        while (matcher.find()) {
            String commandName = matcher.group(1);
            String parameter = matcher.group(2);
            String description = matcher.group(3);
            boolean hasParameter = parameter != null;
            commandMap.put(commandName, hasParameter);
        }

        return commandMap;
    }


    public static final String freeHelpText = "Usage:\n"
            + " free [options]\n"
            + "\n"
            + "Options:\n"
            + " -b, --bytes         show output in bytes\n"
            + " -k, --kilo          show output in kilobytes\n"
            + " -m, --mega          show output in megabytes\n"
            + " -g, --giga          show output in gigabytes\n"
            + "     --tera          show output in terabytes\n"
            + "     --peta          show output in petabytes\n"
            + " -h, --human         show human-readable output\n"
            + "     --si            use powers of 1000 not 1024\n"
            + " -l, --lohi          show detailed low and high memory statistics\n"
            + " -t, --total         show total for RAM + swap\n"
            + " -s N, --seconds N   repeat printing every N seconds\n"
            + " -c N, --count N     repeat printing N times, then exit\n"
            + " -w, --wide          wide output\n"
            + "\n"
            + "     --help     display this help and exit\n"
            + " -V, --version  output version information and exit\n";
    public static final String topHelpText = "procps-ng version 3.3.10\n"
            + "Usage:\n"
            + "  top -hv | -bcHiOSs -d secs -n max -u|U user -p pid(s) -o field -w [cols]";
    public static final String pingHelpText = "Usage: ping.sh [-aAbBdDfhLnOqrRUvV64] [-c count] "
            + "[-i "
            + "interval] [-I interface]\n"
            + "            [-m mark] [-M pmtudisc_option] [-l preload] [-p pattern] [-Q tos]\n"
            + "            [-s packetsize] [-S sndbuf] [-t ttl] [-T timestamp_option]\n"
            + "            [-w deadline] [-W timeout] [hop1 ...] destination\n"
            + "Usage: ping.sh -6 [-aAbBdDfhLnOqrRUvV] [-c count] [-i interval] [-I interface]\n"
            + "             [-l preload] [-m mark] [-M pmtudisc_option]\n"
            + "             [-N nodeinfo_option] [-p pattern] [-Q tclass] [-s packetsize]\n"
            + "             [-S sndbuf] [-t ttl] [-T timestamp_option] [-w deadline]\n"
            + "             [-W timeout] destination";

    public static final String tcpDump = "Usage: tcpdump [-aAbdDefhHIJKlLnNOpqStuUvxX#] [ -B size"
            + " ] [ -c count ]\n"
            + "                [ -C file_size ] [ -E algo:secret ] [ -F file ] [ -G seconds ]\n"
            + "                [ -i interface ] [ -j tstamptype ] [ -M secret ] [ --number ]\n"
            + "                [ -Q|-P in|out|inout ]\n"
            + "                [ -r file ] [ -s snaplen ] [ --time-stamp-precision precision ]\n"
            + "                [ --immediate-mode ] [ -T type ] [ --version ] [ -V file ]\n"
            + "                [ -w file ] [ -W filecount ] [ -y datalinktype ] [ -z "
            + "postrotate-command ]\n"
            + "                [ -Z user ] [ expression ]";


    public static final String netStatHelpText = "usage: netstat [-vWeenNcCF] [<Af>] -r  "
            + "netstat {-V|--version|-h|--help}\n"
            + "       netstat [-vWnNcaeol] [<Socket> ...]\n"
            + "       netstat { [-vWeenNac] -I[<Iface>] | [-veenNac] -i | [-cnNe] -M | -s [-6tuw]"
            + " } [delay]\n"
            + "\n"
            + "        -r, --route              display routing table\n"
            + "        -I, --interfaces=<Iface> display interface table for <Iface>\n"
            + "        -i, --interfaces         display interface table\n"
            + "        -g, --groups             display multicast group memberships\n"
            + "        -s, --statistics         display networking statistics (like SNMP)\n"
            + "        -M, --masquerade         display masqueraded connections\n"
            + "\n"
            + "        -v, --verbose            be verbose\n"
            + "        -W, --wide               don't truncate IP addresses\n"
            + "        -n, --numeric            don't resolve names\n"
            + "        --numeric-hosts          don't resolve host names\n"
            + "        --numeric-ports          don't resolve port names\n"
            + "        --numeric-users          don't resolve user names\n"
            + "        -N, --symbolic           resolve hardware names\n"
            + "        -e, --extend             display other/more information\n"
            + "        -p, --programs           display PID/Program name for sockets\n"
            + "        -o, --timers             display timers\n"
            + "        -c, --continuous         continuous listing\n"
            + "\n"
            + "        -l, --listening          display listening server sockets\n"
            + "        -a, --all                display all sockets (default: connected)\n"
            + "        -F, --fib                display Forwarding Information Base (default)\n"
            + "        -C, --cache              display routing cache instead of FIB\n"
            + "        -k count,                dis                                     \n"
            + "        -Z, --context            display SELinux security context for sockets";

    /**
     * 解析ping命令的help信息
     */
    @Test
    public void parePingHelpInfo() {
        Map<String, String> map = new HashMap<>();
        String regex = "\\[[a-zA-Z\\s-\\_]*\\]";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(pingHelpText);
        while (matcher.find()) {
            String text = matcher.group();
            String regex1 = "\\[(.*?)\\]";
            Pattern memory = Pattern.compile(regex1);
            Matcher matcher1 = memory.matcher(text);
            while (matcher1.find()) {
                String data = matcher1.group(1);
                if (!data.contains(" ")) {
                    String data2 = data.replace("-", "");
                    char[] chars = data2.toCharArray();
                    for (char c : chars) {
                        map.put("-" + c, null);
                    }
                } else {
                    String[] s = data.split(" ");
                    map.put(s[0], s[1]);
                }
            }
        }
        System.out.println(map.keySet().size());
        //输出解析结果
        map.entrySet().stream().forEach(Entry -> {
            System.out.println(Entry.getKey() + "=======" + Entry.getValue());
        });
    }


    /**
     * 解析top命令的help信息
     */
    @Test
    public void parseTopHelpInfo() {
        // 定义正则表达式
        String regex = "(-[a-zA-Z])(\\s([a-zA-Z\\(\\)\\[\\]\\|]+))?";

        // 构建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(topHelpText);

        Map<String, String> options = new HashMap<>();

        while (matcher.find()) {
            System.out.println(matcher.group());
            String key = matcher.group(1);
            String value = matcher.group(3) == null ? "" : matcher.group(3).trim();
            options.put(key, value);
        }

        // 输出解析结果
        for (Map.Entry<String, String> entry : options.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * 解析free命令的help信息
     */
    @Test
    public void parseFreeHelpInfo() {
        // 定义正则表达式
        String regex = "(\\--[a-zA-Z]{2,}[\\,|\\s])(\\s[a_zA-Z]\\s)?";

        // 构建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(freeHelpText);

        Map<String, String> options = new HashMap<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2) == null ? "" : matcher.group(2).trim();
            System.out.println(key + "====" + value);
            options.put(key, value);
        }

        // 输出解析结果
        for (Map.Entry<String, String> entry : options.entrySet()) {
            //  System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * 解析tcpdump指令
     */
    @Test
    public void parseTcpDump() {
        Map<String, String> map = new HashMap<>();
        //   String regex = "\\[[a-zA-Z\\s|-]*\\]";
        String regex = "\\[.*?]*\\]";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(tcpDump);
        while (matcher.find()) {
            String text = matcher.group();
            System.out.println(text);
            String regex1 = "\\[(.*?)\\]";
            Pattern memory = Pattern.compile(regex1);
            Matcher matcher1 = memory.matcher(text);
            while (matcher1.find()) {
                String group = matcher1.group(1);
                System.out.println(group);
                String[] s = group.split(" ");
                System.out.println(s.length);
                if (s.length > 2) {
                    System.out.println("key:" + s[1] + "||" + "val:" + s[2]);
                    map.put(s[1], s[2]);
                } else if (s.length == 2) {
                    map.put(s[1], null);
                } else {
                    //提取数字字母
                    String s1 = group.replaceAll("-", "");
                    System.out.println(s1);
                    char[] chars = s1.toCharArray();
                    for (char c : chars) {
                        System.out.println(c);
                        map.put("-" + c, null);
                    }
                }
            }
        }
        //输出解析结果
        map.entrySet().stream().forEach(Entry -> {
            System.out.println(Entry.getKey() + "=======" + Entry.getValue());
        });
    }

    @Test
    public void parseNetstatHelpInfo() {
        Map<String, String> map = new HashMap<>();
        //   String regex = "\\[[a-zA-Z\\s|-]*\\]";
        String regex = "\\[.*?]*\\]";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(netStatHelpText);
        while (matcher.find()) {
            String text = matcher.group();
            System.out.println(text);
            String regex1 = "\\[(.*?)\\]";
            Pattern memory = Pattern.compile(regex1);
            Matcher matcher1 = memory.matcher(text);
            while (matcher1.find()) {
                String group = matcher1.group(1);
                System.out.println(group);
                String[] s = group.split(" ");
                System.out.println(s.length);
                if (s.length > 2) {
                    System.out.println("key:" + s[1] + "||" + "val:" + s[2]);
                    map.put(s[1], s[2]);
                } else if (s.length == 2) {
                    map.put(s[1], null);
                } else {
                    //提取数字字母
                    String s1 = group.replaceAll("-", "");
                    System.out.println(s1);
                    char[] chars = s1.toCharArray();
                    for (char c : chars) {
                        System.out.println(c);
                        map.put("-" + c, null);
                    }
                }
            }
        }
        //输出解析结果
        map.entrySet().stream().forEach(Entry -> {
            System.out.println(Entry.getKey() + "=======" + Entry.getValue());
        });
    }

    @Test
    public void parse1() {
        String output = "Displays protocol statistics and current TCP/IP network connections"
                + ".\n\nNETSTAT [-a] [-b] [-e] [-f] [-n] [-o] [-p proto] [-r] [-s] [-t] "
                + "[interval]\n\n  -a            Displays all connections and listening ports.\n "
                + " -b            Displays the executable involved in creating each connection "
                + "or\n                listening port. In some cases well-known executables "
                + "host\n                multiple independent components, and in these cases the "
                + "sequence\n                of components involved in creating the connection or"
                + " listening\n                port is displayed. In this case the executable "
                + "name is in [] at\n                the bottom, on top is the component it "
                + "called, and so forth until\n                TCP/IP was reached. Note that this"
                + " option can be time-consuming\n                and will fail unless you have "
                + "sufficient permissions.\n  -e            Displays Ethernet statistics. This "
                + "may be combined with the -s\n                option.\n  -f            Displays"
                + " Fully Qualified Domain Names (FQDN) for foreign\n                addresses.\n"
                + "  -n            Displays addresses and port numbers in numerical form.\n  -o  "
                + "          Displays the owning process ID associated with each connection.\n  "
                + "-p proto      Shows connections for the protocol specified by proto; proto\n  "
                + "              may be any of: TCP, UDP, TCPv6, or UDPv6.  If used with the -s\n"
                + "                option to display per-protocol statistics, proto may be any "
                + "of:\n                IP, IPv6, ICMP, ICMPv6, TCP, TCPv6, UDP, or UDPv6.\n  -r "
                + "           Displays the routing table.\n  -s            Displays per-protocol "
                + "statistics.  By default, statistics are\n                shown for IP, IPv6, "
                + "ICMP, ICMPv6, TCP, TCPv6, UDP, and UDPv6;\n                the -p option may "
                + "be used to specify a subset of the default.\n  -t            Displays the "
                + "current connection offload state.\n  interval      Redisplays selected "
                + "statistics, pausing interval seconds\n                between each display.  "
                + "Press CTRL+C to stop redisplaying\n                statistics.  If omitted, "
                + "netstat will print the current\n                configuration information once"
                + ".\n";

        Pattern pattern = Pattern.compile("\\s+(-\\w+)\\s+(.*)");

        // Create a Map to store the options and their descriptions
        Map<String, String> optionsMap = new HashMap<>();
        // Iterate over each line of the output
        // Match the pattern against the line
        Matcher matcher = pattern.matcher(output);
        while (matcher.find()) {
            // Extract the option and description from the matched groups
            String option = matcher.group(1);
            String description = matcher.group(2);

            // Add the option and description to the map
            optionsMap.put(option, description.trim());
        }
        for (Map.Entry<String, String> entry : optionsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void reduce() {
        Map<String, String> map = new HashMap<>();
        String data = com.demo.data.d3;
        // String data = topHelpText;
        //   String data = freeHelpText;
        // String data = tcpDump;
        //    String data = netStatHelpText;
        //  String data = pingHelpText;
        //按行获取数据
        String[] split = data.split("\\n");
        if (split.length <= 3) {
            map.putAll(parse03(data));
        } else {
            for (String sp : split) {
                //提取含有[]的字符
                String regex = "\\[.*?]*\\]";
                Pattern compile = Pattern.compile(regex);
                Matcher matcher = compile.matcher(sp);
                while (matcher.find()) {
                    String data1 = matcher.group();
                    Map<String, String> stringStringMap = pares01(data1);
                    map.putAll(stringStringMap);
                }
                //提取含有{}的字符
                String regex1 = "\\{.*?]*\\}";
                Pattern compile1 = Pattern.compile(regex1);
                Matcher matcher1 = compile1.matcher(sp);
                while (matcher1.find()) {
                    String data1 = matcher1.group();
                    Map<String, String> stringStringMap = parse02(data1);
                    map.putAll(stringStringMap);
                }
                //提取所有以-开头的字符
                String regex2 = "\\-\\w+\\,";
                Pattern compile2 = Pattern.compile(regex2);
                Matcher matcher2 = compile2.matcher(sp);
                while (matcher2.find()) {
                    String data3 = matcher2.group();
                    System.out.println(data3);
                    String data4 = data3.replace(",", "");
                    map.put(data4, null);
                }
                //提取含有以-开头的包含参数的字符
                String regex3 = "\\-\\w\\s\\w+\\,";
                Pattern compile3 = Pattern.compile(regex3);
                Matcher matcher3 = compile3.matcher(sp);
                while (matcher3.find()) {
                    String data4 = matcher3.group();
                    System.out.println(data4);
                    String[] split1 = data4.split("\\s");
                    map.put(split1[0], split1[1]);
                }
            }
        }
        map.entrySet().stream().forEach(entry -> {
            System.out.println("option cmd:" + entry.getKey() + " option param:" + entry.getValue());
        });

    }

    /**
     * 解析[]中可选命令
     *
     * @param data
     * @return
     */
    public static Map<String, String> pares01(String data) {
        System.out.println(data);
        Map<String, String> map = new HashMap<String, String>();
        if (!data.equals("[options]")) {
            //提取[]中内容
            String regex1 = "\\[(.*?)\\]";
            Pattern memory = Pattern.compile(regex1);
            Matcher matcher1 = memory.matcher(data);
            while (matcher1.find()) {
                String data1 = matcher1.group(1);
                String d = data1.trim();
                if (!d.matches(".*[<>\\[\\/].*")) {
                    System.out.println(d);
                    String[] s = d.split("\\s");
                    int num = s.length;
                    System.out.println("str lenth:" + data1 + "lenth:" + num);
                    if (num == 2) {
                        map.put(s[0], s[1]);
                    } else {
                        int specSymbolNum = getSpecSymbolNum(d, '-');
                        if (specSymbolNum == 2) {
                            map.put(s[0], null);
                        } else if (specSymbolNum == 1) {
                            char[] chars = data1.replace("-", "").trim().toCharArray();
                            for (char c : chars) {
                                if (c != '#') {
                                    map.put("-" + c, null);
                                    System.out.println("no args option:" + c);
                                }
                            }
                        }
                    }
                }
            }
        }
        return map;
    }


    /**
     * 解析{}中的内容
     *
     * @param data
     * @return
     */
    public static Map<String, String> parse02(String data) {
        Map<String, String> map = new HashMap<>();
        String data2 = data.replace("{", "").replace("}", "");
        if (data2.contains("|") && !data2.contains("[") && !data2.contains("]")) {
            String[] split2 = data2.split("\\|");
            for (String s2 : split2) {
                map.put(s2, null);
            }
        }
        return map;
    }

    /**
     * 解析top 等的输出信息 格式如：
     *
     * @return
     */
    public static Map<String, String> parse03(String data) {
        Map<String, String> map = new HashMap<>();
        //对数据按行进行分割
        String[] split = data.split("\n");
        //获取最后一行的内容
        String s = split[split.length - 1];
        //按照空格进行分割
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].contains("-") && !s1[i + 1].contains("-")) {//判断命令是否带参数

                if (s1[i].contains("\\|")) {

                }
                if (!s1[i + 1].equals("|")) {
                    map.put(s1[i], s1[i + 1]);
                } else {
                    map.put(s1[i], null);
                }
            } else if (s1[i].contains("-") && s1[i + 1].contains("-")) {//解析无参命令
                char[] s2 = s1[i].replace("-", "").toCharArray();
                for (char c : s2) {
                    map.put("-" + c, null);
                }
            }
        }
        return map;
    }

    public static int getSpecSymbolNum(String data, char c) {
        int i = 0;
        char[] chars = data.toCharArray();
        for (char c1 : chars) {
            if (c1 == c) {
                i++;
            }
        }
        return i;
    }

    @Test
    public void getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }


    @Test
    public void test01() throws IOException {
        Process process = Runtime.getRuntime().exec("ping --help");
//        ProcessBuilder builder = new ProcessBuilder("ping --help");
//        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> options = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("  -") || line.startsWith("\t-")) {
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    if (token.startsWith("-")) {
                        options.add(token);
                    }
                }
            }
        }
        options.stream().forEach(System.out::println);

    }

    @Test
    public void test123() {
//        String str1 = "a";
//        String str2 = "-";
//        String str3 = "b-";
//        String str4 = "-c";
//        String str5 = "x-y";
//
//        boolean isMatch1 = str1.matches("[a-zA-Z]");
//        boolean isMatch2 = str2.matches("[a-zA-Z-]");
//        boolean isMatch3 = str3.matches("[a-zA-Z-]+");
//        boolean isMatch4 = str4.matches("[a-zA-Z-]+");
//        boolean isMatch5 = str5.matches("[a-zA-Z-]+");
//
//        System.out.println(str1 + ": " + isMatch1);
//        System.out.println(str2 + ": " + isMatch2);
//        System.out.println(str3 + ": " + isMatch3);
//        System.out.println(str4 + ": " + isMatch4);
//        System.out.println(str5 + ": " + isMatch5);
//        String[] a1 = new String[]{"de", "ry", "yu", "io"};
//        System.out.println(a1[a1.length - 1]);
//        String data = "-/";
//
//        System.out.println( data.matches(".*[<>\\[\\/].*"));
//        String regex = "\\[\\[\\/\\<\\>\\]";
//        Pattern compile = Pattern.compile(regex);
//        Matcher matcher = compile.matcher(data);
//        System.out.println(matcher.find());

        System.out.println("-hello".length());
        String d3 = data.d3;
        String[] split = d3.split("\n");
        int i = 0;
        Map<String,String> list = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split("\\s");
            for (String s1 : split1) {
                if (s1.startsWith("-")) {
                    if (s1.contains(",")) {
                        String[] split2 = s1.split(",");
                        for (String s2 : split2) {
                            list.put(s2, null);
                        }
                    }else{
                        if(!s1.equals("--")) {
                            list.put(s1, null);
                        }
                    }
                }
            }
        }
        list.entrySet().stream().forEach(entry ->{
            System.out.println(entry.getKey()+"<==>"+entry.getValue());
        });
    }
}



