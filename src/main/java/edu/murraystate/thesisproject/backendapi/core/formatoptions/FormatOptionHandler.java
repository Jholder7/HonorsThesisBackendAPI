package edu.murraystate.thesisproject.backendapi.core.formatoptions;

import java.util.ArrayList;
import java.util.List;

//TODO: Document
public class FormatOptionHandler {
    private BraceStyleOptions BSO;
    private List<BraceModifyOptions> BMO;
    private TabOptions TO;
    private List<IndentationOptions> IO;
    private List<PaddingOptions> PO;
    private List<FormattingOptions> FO;
    private List<ObjectCOptions> OCO;

    public FormatOptionHandler(){
        this.BSO = BraceStyleOptions.DEFAULT;
        this.BMO = new ArrayList<>();
        this.BMO.add(BraceModifyOptions.DEFAULT);
        this.TO = TabOptions.DEFAULT;
        this.IO = new ArrayList<>();
        this.IO.add(IndentationOptions.DEFAULT);
        this.PO = new ArrayList<>();
        this.PO.add(PaddingOptions.DEFAULT);
        this.FO = new ArrayList<>();
        this.FO.add(FormattingOptions.DEFAULT);
        this.OCO = new ArrayList<>();
        this.OCO.add(ObjectCOptions.DEFAULT);
    }

    public FormatOptionHandler setBraceStyleOption(BraceStyleOptions option) {
        System.out.println("setting added " + option.name());
        this.BSO = option;
        return this;
    }

    public FormatOptionHandler setBraceModifyOption(BraceModifyOptions option) {
        System.out.println("setting added " + option.name());
        if (!this.BMO.contains(option)) {
            this.BMO.add(option);
        }
        return this;
    }

    public FormatOptionHandler setTabOption(TabOptions option) {
        System.out.println("setting added " + option.name());
        this.TO = option;
        return this;
    }

    public FormatOptionHandler setIndentationOption(IndentationOptions option) {
        System.out.println("setting added " + option.name());
        if (!this.IO.contains(option)) {
            this.IO.add(option);
        }
        return this;
    }

    public FormatOptionHandler setPaddingOption(PaddingOptions option) {
        System.out.println("setting added " + option.name());
        if (!this.PO.contains(option)) {
            this.PO.add(option);
        }
        return this;
    }

    public FormatOptionHandler setFormattingOption(FormattingOptions option) {
        System.out.println("setting added " + option.name());
        if (!this.FO.contains(option)) {
            this.FO.add(option);
        }
        return this;
    }

    public FormatOptionHandler setObjectCOption(ObjectCOptions option) {
        System.out.println("setting added " + option.name());
        if (!this.OCO.contains(option)) {
            this.OCO.add(option);
        }
        return this;
    }

    public void applyOptionList(String[] settings){
        if (settings == null) {return;}
        for(String str : settings) {
            System.out.println("evaling settings " + str);
            switch(str.charAt(0)){
                case '1':
                    this.setBraceModifyOption(BraceModifyOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
                case '2':
                    this.setTabOption(TabOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
                case '3':
                    this.setBraceStyleOption(BraceStyleOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
                case '4':
                    this.setFormattingOption(FormattingOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
                case '5':
                    this.setIndentationOption(IndentationOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
                case '6':
                    this.setPaddingOption(PaddingOptions.valueOf(str.substring(1).toUpperCase()));
                    break;
            }
        }
    }

    public List<String> getOptionSet(){
        List<String> optionSet = new ArrayList<>();
        optionSet.add(this.BSO.getCmdAddon());
        for (BraceModifyOptions option : this.BMO) {
            if ((this.BMO.size() > 1 && option != BraceModifyOptions.DEFAULT)) {
                optionSet.add(option.getCmdAddon());
            }
        }
        optionSet.add(this.TO.getCmdAddon());
        for (IndentationOptions option : this.IO) {
            if ((this.IO.size() > 1 && option != IndentationOptions.DEFAULT)) {
                optionSet.add(option.getCmdAddon());
            }
        }
        for (PaddingOptions option : this.PO) {
            if ((this.PO.size() > 1 && option != PaddingOptions.DEFAULT)) {
                optionSet.add(option.getCmdAddon());
            }
        }
        for (FormattingOptions option : this.FO) {
            if ((this.FO.size() > 1 && option != FormattingOptions.DEFAULT)) {
                optionSet.add(option.getCmdAddon());
            }
        }
        for (ObjectCOptions option : this.OCO) {
            if ((this.OCO.size() > 1 && option != ObjectCOptions.DEFAULT)) {
                optionSet.add(option.getCmdAddon());
            }
        }
        return optionSet;
    }
}

