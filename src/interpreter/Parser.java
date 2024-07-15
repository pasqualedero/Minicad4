package interpreter;

import exception.SyntaxException;
import interpreter.area.Area;
import interpreter.area.AreaAll;
import interpreter.area.AreaObjID;
import interpreter.area.AreaType;
import interpreter.list.*;
import interpreter.move.Move;
import interpreter.move.Mv;
import interpreter.move.MvOff;
import interpreter.perimeter.Perimeter;
import interpreter.perimeter.PerimeterAll;
import interpreter.perimeter.PerimeterObjID;
import interpreter.perimeter.PerimeterType;
import interpreter.typeConstr.TypeConstr;
import interpreter.typeConstr.TypeConstrCircle;
import interpreter.typeConstr.TypeConstrImg;
import interpreter.typeConstr.TypeConstrRectange;

import java.io.Reader;

public class Parser {
    private AnalizzatoreLessicale lexer;
    private Simboli simbolo;
    private CombinazioneTerminale combinazione;

    public Parser(Reader in) {
        lexer = new AnalizzatoreLessicale(in);
        combinazione = combinazione();
        atteso(Simboli.EOE);
    }

    private CombinazioneTerminale combinazione() {
        switch (lexer.prossimoSimbolo()){
            case NEW -> {return create();}
            case DEL -> {return remove();}
            case MV -> {return mv();}
            case MVOFF -> {return mvOff();}
            case SCALE -> {return scale();}
            case LS -> {return list();}
            case GRP -> {return group();}
            case UNGRP -> {return ungroup();}
            case AREA -> {return area();}
            case PERIMETER -> {return perimeter();}
            default -> throw new SyntaxException("comando invalido");
        }
    }

    private Ungroup ungroup() {
        ObjID id = objID();
        return new Ungroup(id);
    }

    private Remove remove() {
        return new Remove(objID());
    }

    private Scale scale() {
        ObjID id = objID();
        PosFloat pf = posFloat();
        return new Scale(id,pf);
    }

    private PosFloat posFloat() {
        atteso(Simboli.NUMERO_O_STRINGA);
        return new PosFloat(Float.parseFloat(lexer.getString()));
    }

    private ObjID objID()  {
        atteso(Simboli.NUMERO_O_STRINGA);
        return new ObjID(Integer.parseInt(lexer.getString()));
    }

    private Group group(){
        return new Group(listID());
    }

    private ListID listID()  {
        ListID res = new ListID(objID());
        for (;;){
            Simboli simbolo = lexer.prossimoSimbolo();
            if (simbolo == Simboli.EOE){
                break;
            }
            ObjID id = objIDsenzaAvanzamento();
            res.addObjID(id);
        }
        return res;
    }

    private TypeConstr typeConstr()  {
        switch (lexer.prossimoSimbolo()){
            case IMAGE -> {return typeConstrImg();}
            case CIRCLE -> {return typeConstrCircle();}
            case RECTANGLE -> {return typeConstrRectange();}
            default -> throw new SyntaxException("carattere invalido. Mi aspetto: image, circle o rectangle e non "+lexer.getString());
        }
    }

    private TypeConstrRectange typeConstrRectange() {
        Type t = new Type(lexer.getSimbolo());
        PosFloat base =posFloat();
        PosFloat altezza = posFloat();
        return new TypeConstrRectange(base,altezza);
    }

    private TypeConstrCircle typeConstrCircle() {
        Type t = new Type(lexer.getSimbolo());
        atteso(Simboli.TONDA_APERTA);
        PosFloat raggio = posFloat();
        atteso(Simboli.TONDA_CHIUSA);
        return new TypeConstrCircle(raggio);
    }

    private TypeConstrImg typeConstrImg(){
        atteso(Simboli.TONDA_APERTA);
        Path path = path();
        atteso(Simboli.TONDA_CHIUSA);
        return new TypeConstrImg(path);
    }

    private List list(){
        switch (lexer.prossimoSimbolo()){
            case NUMERO_O_STRINGA -> {return listObj();}
            case RECTANGLE, CIRCLE, IMAGE -> {return listType();}
            case ALL -> {return listAll();}
            case GROUPS -> {return listGroups();}
            default -> throw new SyntaxException("Mi aspetto un id, una forma, all o groups e non "+lexer.getString());
        }
    }

    private List listGroups() {
        return new ListGroups();
    }

    private List listAll() {
        return new ListAll();
    }

    private List listType() {
        Type type = new Type(lexer.getSimbolo());
        return new ListType(type);
    }

    private List listObj() {
        ObjID id = objIDsenzaAvanzamento();
        return new ListObj(id);
    }

    private Path path() {
        atteso(Simboli.NUMERO_O_STRINGA);
        return new Path(lexer.getString());
    }

    private Area area() {
        switch (lexer.prossimoSimbolo()){
            case NUMERO_O_STRINGA -> {return areaObjID();}
            case CIRCLE,RECTANGLE,IMAGE -> {return areaType();}
            case ALL -> {return areaAll();}
            default -> throw new SyntaxException("carattere invalido. Mi aspetto un id, una forma o all e non "+lexer.getString());
        }
    }

    private Area areaAll() {
        return new AreaAll();
    }

    private Area areaType() {
        Type t = new Type(lexer.getSimbolo());
        return new AreaType(t);
    }

    private Area areaObjID() {
        ObjID id = objIDsenzaAvanzamento();
        return new AreaObjID(id);
    }

    private Perimeter perimeter() {
        switch (lexer.prossimoSimbolo()){
            case NUMERO_O_STRINGA -> {return perimeterObjID();}
            case RECTANGLE, CIRCLE, IMAGE -> {return perimeterType();}
            case ALL -> {return perimeterAll();}
            default -> throw new SyntaxException("Mi aspetto un id, una forma o all e non "+lexer.getString());
        }
    }

    private Perimeter perimeterAll() {
        return new PerimeterAll();
    }

    private Perimeter perimeterType() {
        Type t = new Type(lexer.getSimbolo());
        return new PerimeterType(t);
    }

    private Perimeter perimeterObjID() {
        ObjID id = objIDsenzaAvanzamento();
        return new PerimeterObjID(id);
    }

    private ObjID objIDsenzaAvanzamento() {
        return new ObjID(Integer.parseInt(lexer.getString()));
    }


    private Create create() {
        TypeConstr typeConstr = typeConstr();
        Pos pos = pos();
        return new Create(typeConstr,pos);
    }

    private Pos pos() {
        atteso(Simboli.TONDA_APERTA);
        PosFloat p1 = posFloat();
        atteso(Simboli.VIRGOLA);
        PosFloat p2 = posFloat();
        atteso(Simboli.TONDA_CHIUSA);
        return new Pos(p1,p2);
    }

    public Move move() {
        switch (lexer.prossimoSimbolo()){
            case MV -> {return mv();}
            case MVOFF -> {return mvOff();}
            default -> throw new SyntaxException("Mi aspetto mv o mvoff e non "+lexer.getString());
        }
    }

    private Move mvOff() {
        ObjID id = objID();
        Pos p = pos();
        return new MvOff(id,p);
    }

    private Move mv() {
        ObjID id = objID();
        Pos p = pos();
        return new Mv(id,p);
    }

    private void atteso(Simboli s) {
        simbolo = lexer.prossimoSimbolo();
        if (simbolo != s) {
            String msg = " trovato " + simbolo + " mentre si attendeva " + s;
            throw new SyntaxException(msg);
        }
    }

    public CombinazioneTerminale getCombinazione() {
        return combinazione;
    }// ritorna il composite costruito
}
