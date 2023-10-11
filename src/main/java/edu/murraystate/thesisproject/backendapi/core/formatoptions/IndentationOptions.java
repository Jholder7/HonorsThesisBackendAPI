package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum IndentationOptions {
    DEFAULT ("", ""),
    INDENTCLASSES ("--indent-classes", "class Foo\n" +
            "{\n" +
            "    public:\n" +
            "        Foo();\n" +
            "        virtual ~Foo();\n" +
            "};"),
    INDENTMODIFIERS ("--indent-modifiers", "class Foo\n" +
            "{\n" +
            "  public:\n" +
            "    Foo();\n" +
            "    virtual ~Foo();\n" +
            "};"),
    INDENTSWITCHES ("--indent-switches", "switch (foo)\n" +
            "{\n" +
            "    case 1:\n" +
            "        a += 1;\n" +
            "        break;\n" +
            "\n" +
            "    case 2:\n" +
            "    {\n" +
            "        a += 2;\n" +
            "        break;\n" +
            "    }\n" +
            "}"),
    INDENTCASES ("--indent-cases", "switch (foo)\n" +
            "{\n" +
            "    case 1:\n" +
            "        a += 1;\n" +
            "        break;\n" +
            "\n" +
            "    case 2:\n" +
            "        {\n" +
            "            a += 2;\n" +
            "            break;\n" +
            "        }\n" +
            "}"),
    INDENTNAMESPACES ("--indent-namespaces", "namespace foospace\n" +
            "{\n" +
            "    class Foo\n" +
            "    {\n" +
            "        public:\n" +
            "            Foo();\n" +
            "            virtual ~Foo();\n" +
            "    };\n" +
            "}"),
    INDENTAFTERPARENS("--indent-after-parens", "void Foo(bool bar1,\n" +
            "    bool bar2)\n" +
            "{\n" +
            "    isLongFunction(bar1,\n" +
            "        bar2);\n" +
            "\n" +
            "    isLongVariable = foo1\n" +
            "        || foo2;\n" +
            "}"),
    INDENTLABELS("--indent-labels", "void Foo() {\n" +
            "    while (isFoo) {\n" +
            "        if (isFoo)\n" +
            "            goto error;\n" +
            "        ...\n" +
            "    error:\n" +
            "        ...\n" +
            "        }\n" +
            "}"),
    INDENTPREPROCBLOCK ("--indent-preproc-block", "#ifdef _WIN32\n" +
            "    #include <windows.h>\n" +
            "    #ifndef NO_EXPORT\n" +
            "        #define EXPORT\n" +
            "    #endif\n" +
            "#endif"),
    INDENTPREPROCDEFINE ("--indent-preproc-define", "#define Is_Bar(arg,a,b) \\\n" +
            "    (Is_Foo((arg), (a)) \\\n" +
            "     || Is_Foo((arg), (b)))"),
    INDENTPREPROCCOND("--indent-preproc-cond", "        isFoo = true;\n" +
            "        #ifdef UNICODE\n" +
            "        text = wideBuff;\n" +
            "        #else\n" +
            "        text = buff;\n" +
            "        #endif"),
    INDENTCOLLCOMMENTS("--indent-col1-comments", "void Foo()\\n\"\n" +
            "{\n" +
            "    // comment\n" +
            "    if (isFoo)\n" +
            "        bar();\n" +
            "}"),
    LAMBDAINDENT ("--lambda-indent", "void abssort (float* x, unsigned n)\n" +
            "{\n" +
            "    std::sort (x, x + n,\n" +
            "                [] (float a, float b) {\n" +
            "                    return (std::abs (a) < std::abs (b));\n" +
            "                }\n" +
            "               );\n" +
            "}");


    private final String cmdAddon;
    private final String exampleCode;
    private IndentationOptions (String cmdAddon, String exampleCode) {
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
