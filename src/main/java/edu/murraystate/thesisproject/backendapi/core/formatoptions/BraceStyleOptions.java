package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum BraceStyleOptions {
    DEFAULT ("", ""),
    ALLMAN ("--style=allman", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar)\n" +
            "    {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    }\n" +
            "    else\n" +
            "        return 0;\n" +
            "}"),
    JAVA ("--style=java", "int Foo(bool isBar) {\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else\n" +
            "        return 0;\n" +
            "}"),
    KR ("--style=kr", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else\n" +
            "        return 0;\n" +
            "}"),
    STROUSTRUP ("--style=stroustrup", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    }\n" +
            "    else\n" +
            "        return 0;\n" +
            "}"),
    WHITESMITH ("--style=whitesmith", "int Foo(bool isBar)\n" +
            "    {\n" +
            "    if (isBar)\n" +
            "        {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "        }\n" +
            "    else\n" +
            "        return 0;\n" +
            "    }"),
    VTK ("--style=vtk", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar)\n" +
            "        {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "        }\n" +
            "    else\n" +
            "        return 0;\n" +
            "}"),
    RATLIFF ("--style=ratliff", "int Foo(bool isBar) {\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "        }\n" +
            "    else\n" +
            "        return 0;\n" +
            "    }"),
    GNU ("--style=gnu", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar)\n" +
            "        {\n" +
            "            bar();\n" +
            "            return 1;\n" +
            "        }\n" +
            "    else\n" +
            "        return 0;\n" +
            "}"),
    LINUX ("--style=linux", "int Foo(bool isBar)\n" +
            "{\n" +
            "        if (isFoo) {\n" +
            "                bar();\n" +
            "                return 1;\n" +
            "        } else\n" +
            "                return 0;\n" +
            "}"),
    HORSTMANN ("--style=horstmann", "int Foo(bool isBar)\n" +
            "{   if (isBar)\n" +
            "    {   bar();\n" +
            "        return 1;\n" +
            "    }\n" +
            "    else\n" +
            "        return 0;\n" +
            "}"),
    OTBS ("--style=otbs", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isFoo) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else {\n" +
            "        return 0;\n" +
            "    }\n" +
            "}"),
    GOOGLE ("--style=google", "int Foo(bool isBar) {\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else\n" +
            "        return 0;\n" +
            "}"),
    MOZILLA ("--style=mozilla", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else\n" +
            "        return 0;\n" +
            "}"),
    WEBKIT ("--style=webkit", "int Foo(bool isBar)\n" +
            "{\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1;\n" +
            "    } else\n" +
            "        return 0;\n" +
            "}"),
    PICO ("--style=pico", "int Foo(bool isBar)\n" +
            "{   if (isBar)\n" +
            "    {   bar();\n" +
            "        return 1; }\n" +
            "    else\n" +
            "        return 0; }"),
    LISP ("--style=lisp", "int Foo(bool isBar) {\n" +
            "    if (isBar) {\n" +
            "        bar();\n" +
            "        return 1; }\n" +
            "    else\n" +
            "        return 0; }");

    private final String cmdAddon;
    private final String exampleCode;
    private BraceStyleOptions (String cmdAddon, String exampleCode) {
        this.exampleCode = exampleCode;
        this.cmdAddon = cmdAddon;
    }

    public String getCmdAddon(){
        return this.cmdAddon;
    }

    public String getExample(){
        return this.exampleCode;
    }
}
