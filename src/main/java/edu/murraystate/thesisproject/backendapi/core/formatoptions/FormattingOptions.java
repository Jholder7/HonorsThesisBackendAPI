package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum FormattingOptions {
    DEFAULT ("", ""),
    BREAKCLOSINGBRACES ("--break-closing-braces", "void Foo(bool isFoo) {\n" +
            "    if (isFoo) {\n" +
            "        bar();\n" +
            "    }\n" +
            "    else {\n" +
            "        anotherBar();\n" +
            "    }\n" +
            "}"),
    BREAKELSEIFS ("--break-elseifs", "if (isFoo) {\n" +
            "    bar();\n" +
            "}\n" +
            "else\n" +
            "    if (isFoo1()) {\n" +
            "        bar1();\n" +
            "    }\n" +
            "    else\n" +
            "        if (isFoo2()) {\n" +
            "            bar2();\n" +
            "        }"),
    BREAKONELINEHEADERS ("--break-one-line-headers", "void Foo(bool isFoo)\n" +
            "{\n" +
            "    if (isFoo1)\n" +
            "        bar1();\n" +
            "\n" +
            "    if (isFoo2) {\n" +
            "        bar2();\n" +
            "    }\n" +
            "}"),
    ADDBRACES ("--add-braces", "if (isFoo) {\n" +
            "    isFoo = false;\n" +
            "}"),
    ADDONELINEBRACES ("--add-one-line-braces", "if (isFoo)\n" +
            "    { isFoo = false; }"),
    REMOVEBRACES ("--remove-braces", "if (isFoo)\n" +
            "    isFoo = false;"),
    BREAKRETURNTYPE ("--break-return-type", "void\n" +
            "Foo(bool isFoo);"),
    ATTACHRETURNTYPE ("--attach-return-type", "void Foo(bool isFoo);"),
    KEEPONELINEBLOCKS ("--keep-one-line-blocks", "if (isFoo)\n" +
            "{ isFoo = false; cout << isFoo << endl; }"),
    KEEPONELINESTATEMENTS ("--keep-one-line-statements", "if (isFoo)\n" +
            "{\n" +
            "    isFoo = false; cout << isFoo << endl;\n" +
            "}"),
    CONVERTTABS ("--convert-tabs", "    >\t"),
    CLOSETEMPLATES ("--close-templates", "Stack< int, List< int >> stack1;"),
    REMOVECOMMENTPREFIX ("--remove-comment-prefix", "/*\n" +
            "    comment line 1\n" +
            "    comment line 2\n" +
            "*/"),
    BREAKAFTERLOGICAL ("--break-after-logical", "if (thisVariable1 == thatVariable1 ||\n" +
            "        thisVariable2 == thatVariable2 ||\n" +
            "        thisVariable3 == thatVariable3)\n" +
            "    bar();");

    private final String cmdAddon;
    private final String exampleCode;
    private FormattingOptions (String cmdAddon, String exampleCode) {
        this.exampleCode = exampleCode;
        this.cmdAddon = cmdAddon;
    };

    public String getCmdAddon(){
        return this.cmdAddon;
    }

    public String getExample(){
        return this.exampleCode;
    }
}
