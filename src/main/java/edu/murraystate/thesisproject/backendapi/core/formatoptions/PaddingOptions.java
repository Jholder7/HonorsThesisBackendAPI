package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum PaddingOptions {
    DEFAULT ("", ""),
    BREAKBLOCKS ("--break-blocks", "isFoo = true;\n" +
            "\n" +
            "if (isFoo) {\n" +
            "    bar();\n" +
            "} else {\n" +
            "    anotherBar();\n" +
            "}\n" +
            "\n" +
            "isBar = false;"),
    BREAKBLOCKSALL ("--break-blocks=all", "isFoo = true;\n" +
            "\n" +
            "if (isFoo) {\n" +
            "    bar();\n" +
            "\n" +
            "} else {\n" +
            "    anotherBar();\n" +
            "}\n" +
            "\n" +
            "isBar = false;"),
    PADOPERATORS ("--pad-oper", "if (foo == 2)\n" +
            "    a = bar((b - c) * a, d--);"),
    PADCOMMA ("--pad-comma", "if (isFoo(a, b))\n" +
            "    bar(a, b);"),
    PADPARENTHESIS ("--pad-paren", "if ( isFoo ( ( a+2 ), b ) )\n" +
            "    bar ( a, b );"),
    PADPARENTHESISOUTSIDE ("--pad-paren-out", "if (isFoo ( (a+2), b) )\n" +
            "    bar (a, b);"),
    PADFIRSTPARENTHESISOUTSIDE ("--pad-first-paren-out", "if (isFoo ((a+2), b))\n" +
            "    bar (a, b);"),
    PADPARENTHESISINSIDE ("--pad-paren-in", "if ( isFoo( ( a+2 ), b ) )\n" +
            "    bar( a, b );"),
    PADHEADER ("--pad-header", "if (isFoo((a+2), b))\n" +
            "    bar(a, b);"),
    UNPADPARENTHESIS ("--unpad-paren", "if(isFoo((a+2), b))\n" +
            "    bar(a, b);"),
    PADEMPTYPARENTHESIS ("--pad-empty-paren", "if ( isFoo () )\n" +
            "    bar( a, b );"),
    PADBRACKETS ("--pad-brackets", "a = b [ a [ 1 ] ];"),
    UNPADBRACKETS ("--unpad-brackets", "a = b[a[1]];"),
    DELETEEMPTYLINES ("--delete-empty-lines", "void Foo()\n" +
            "{\n" +
            "    foo1 = 1;\n" +
            "    foo2 = 2;\n" +
            "}"),
    FILLEMPTYLINES ("--fill-empty-lines", ""),
    SQUEEZEWHITESPACE ("--squeeze-ws", "void Foo ()\n" +
            "{\n" +
            "    foo1 = 1;\n" +
            "}"),
    ALIGNPOINTERTYPE ("--align-pointer=type", "char* foo1;\n" +
            "char& foo2;\n" +
            "string^ s1;"),
    ALIGNPOINTMIDDLE ("--align-pointer=middle", "char * foo1;\n" +
            "char & foo2;\n" +
            "string ^ s1;"),
    ALIGNPOINTERNAME ("--align-pointer=name", "char *foo1;\n" +
            "char &foo2;\n" +
            "string ^s1;"),
    ALIGNREFERENCENONE ("--align-reference=none", ""),
    ALIGNREFERENCETYPE ("--align-reference=type", "char& foo1;"),
    ALIGNREFERENCEMIDDLE ("--align-reference=middle", "char & foo2;"),
    ALIGNREFERENCENAME ("--align-reference=name", "char &foo3;");

    private final String cmdAddon;
    private final String exampleCode;
    private PaddingOptions (String cmdAddon, String exampleCode) {
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
