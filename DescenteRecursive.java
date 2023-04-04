package app6;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  int ptr;
  String input;
/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in) {
    Reader r = new Reader(in);
    input = r.toString();
    ptr = 0;
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
   return A();
}

public ElemAST A(){ //Nombre
  ElemAST newB = B();
  if (oob()) {
    return newB;
  }
  if (input.charAt(ptr) == '+') {
    ptr++;
    ElemAST newA = A();
    return new NoeudAST("+", TerminalType.op, newB, newA);
  } else if (input.charAt(ptr) == '-') {
    ptr++;
    ElemAST newA = A();
    return new NoeudAST("-", TerminalType.op, newB, newA);
  } else {
    return newB;
  }
}

  public ElemAST B(){ //Expression
  ElemAST newC = C();
  if (oob()) {
    return newC;
  }
  if (input.charAt(ptr) == '*') {
    ptr++;
    ElemAST newB = B();
    return new NoeudAST("*", TerminalType.op, newC, newB);
  } else if (input.charAt(ptr) == '/') {
    ptr++;
    ElemAST newB = B();
    return new NoeudAST("/", TerminalType.op, newC ,newB);
  } else {
    return newC;
  }
}

public ElemAST C(){ //Operateur
  if (oob()) {
    ErreurSynt("Aucun type trouvé dans C()");
  }
  if (input.charAt(ptr) == '(') {
    ptr++;
    return A();
  } else if (input.charAt(ptr) == ')') {
    ptr++;
  } else if (isANumber(input.charAt(ptr))) {
    return Nb();
  } else if (isASymboleTerminal(input.charAt(ptr))) {
    return Id();
  } else {
    ErreurSynt("Aucun type trouvé dans C()");
  }
  return null;
}

public ElemAST Id() { //Identificateur
  String identifiant = "";
  do {
    identifiant += input.charAt(ptr);
    ptr++;
    if (oob()){return new FeuilleAST(identifiant, TerminalType.id);}
    } while (isANumber(input.charAt(ptr)));
  return new FeuilleAST(identifiant, TerminalType.id);
}

public ElemAST Nb() {
  String number = "";
  do {
    number += input.charAt(ptr);
    ptr++;
    if (oob()){return new FeuilleAST(number, TerminalType.id);}
  } while (isANumber(input.charAt(ptr)));
  return new FeuilleAST(number, TerminalType.nb);
}

public Boolean oob() {return ptr >= input.length();}

public Boolean isASymboleTerminal(char c){
  return isACharacterLower(c) || isACharacterUpper(c) || isANumber(c) || c == '_';
}

public Boolean isANumber(char c){
  return (c=='0'||
          c=='1'||
          c=='2'||
          c=='3'||
          c=='4'||
          c=='5'||
          c=='6'||
          c=='7'||
          c=='8'||
          c=='9');
}

public Boolean isACharacterLower(char c){
  return (c=='a'||
          c=='b'||
          c=='c'||
          c=='d'||
          c=='e'||
          c=='f'||
          c=='g'||
          c=='h'||
          c=='i'||
          c=='j'||
          c=='k'||
          c=='l'||
          c=='m'||
          c=='n'||
          c=='o'||
          c=='p'||
          c=='q'||
          c=='r'||
          c=='s'||
          c=='t'||
          c=='u'||
          c=='v'||
          c=='w'||
          c=='x'||
          c=='y'||
          c=='z');
}

public Boolean isACharacterUpper(char c){
  return (c=='A'||
          c=='B'||
          c=='C'||
          c=='D'||
          c=='E'||
          c=='F'||
          c=='G'||
          c=='H'||
          c=='I'||
          c=='J'||
          c=='K'||
          c=='L'||
          c=='M'||
          c=='N'||
          c=='O'||
          c=='P'||
          c=='Q'||
          c=='R'||
          c=='S'||
          c=='T'||
          c=='U'||
          c=='V'||
          c=='W'||
          c=='X'||
          c=='Y'||
          c=='Z');
}

public Boolean isAnOperator(char c){
  return (c=='+'||
          c=='-'||
          c=='*'||
          c=='/');
}


/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    //
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

