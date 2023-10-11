package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum TabOptions {
    DEFAULT("", ""),
    SPACES ("--indent=spaces", "void Foo() {\n" +
            "....if (isBar1\n" +
            "............&& isBar2)\n" +
            "........bar();\n" +
            "}"),
    TAB ("--indent=tab", "void Foo() {\n" +
            ">   if (isBar1\n" +
            ">   ........&& isBar2)\n" +
            ">   >   bar();\n" +
            "}"),
    FORCETAB ("--indent=force-tab", "void Foo() {\n" +
            ">   if (isBar1\n" +
            ">   >   >   && isBar2)\n" +
            ">   >   bar();\n" +
            "}"),
    FORCETABX ("--indent=force-tab-x", "void Foo() {\n" +
            "....if (isBar1\n" +
            ">       ....&& isBar2)\n" +
            ">       bar();\n" +
            "}");

    private final String cmdAddon;
    private final String exampleCode;
    private TabOptions (String cmdAddon, String exampleCode) {
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
