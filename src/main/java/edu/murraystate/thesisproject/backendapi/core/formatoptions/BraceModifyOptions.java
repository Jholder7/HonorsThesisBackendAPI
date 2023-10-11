package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum BraceModifyOptions {
    DEFAULT ("", ""),
    ATTACHNAMESPACE ("--attach-namespaces", "namespace FooName {\n" +
            "...\n" +
            "}"),
    ATTACHCLASSES ("--attach-classes", "class FooClass {\n" +
            "...\n" +
            "};"),
    ATTACHINLINE ("--attach-inlines", "class FooClass\n" +
            "{\n" +
            "    void Foo() {\n" +
            "    ...\n" +
            "    }\n" +
            "};"),
    ATTACHCLOSINGWHILE ("--attach-closing-while", "do\n" +
            "{\n" +
            "    bar();\n" +
            "    ++x;\n" +
            "} while x == 1;");

    private final String cmdAddon;
    private final String exampleCode;
    private BraceModifyOptions (String cmdAddon, String exampleCode) {
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
