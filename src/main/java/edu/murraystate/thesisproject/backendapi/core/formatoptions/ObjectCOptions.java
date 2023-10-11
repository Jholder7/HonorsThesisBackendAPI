package edu.murraystate.thesisproject.backendapi.core.formatoptions;

//TODO: Document
public enum ObjectCOptions {
    DEFAULT("", ""),
    PADMETHODPREFIX ("--pad-method-prefix", "- (void)foo1;\n" +
            "- (void)foo2;"),
    UNPADMETHODPREFIX ("--unpad-method-prefix", "-(void) foo1;\n" +
            "-(void) foo2;"),
    PADRETURNTYPE ("--pad-return-type", "-(void) foo1;\n" +
            "-(void) foo2;"),
    UNPADRETURNTYPE ("--unpad-return-type", "-(void)foo1;\n" +
            "-(void)foo2;"),
    PADPARAMATERTYPE ("--pad-param-type", "-(void)foo1: (bool) barArg1;\n" +
            "-(void)foo2: (bool) barArg2;"),
    UNPADPARAMETERTYPE ("--unpad-param-type", "-(void)foo1: (bool)barArg1;\n" +
            "-(void)foo2: (bool)barArg2;"),
    ALIGNMETHODCOLON ("--align-method-colon", "-(void)longKeyword: (ID)theArg1\n" +
            "           keyword: (int)theArg2\n" +
            "             error: (NSError*)theError\n" +
            "{\n" +
            "    [myObj longKeyword: arg1\n" +
            "               keyword: arg2\n" +
            "                 error: arg3];\n" +
            "}"),
    PADMETHODCOLENNONE ("--pad-method-colon=none", "-(void)insertKey:(id)key;"),
    PADMETHODCOLONALL ("--pad-method-colon=all", "-(void)insertKey : (id)key;"),
    PADMETHODCOLONAFTER ("--pad-method-colon=after", "-(void)insertKey: (id)key;"),
    PADMETHODCOLONBEFORE ("--pad-method-colon=before", "-(void)insertKey :(id)key;");

    private final String cmdAddon;
    private final String exampleCode;
    private ObjectCOptions (String cmdAddon, String exampleCode) {
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
