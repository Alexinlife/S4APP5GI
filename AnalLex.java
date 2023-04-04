package app6;

import java.util.regex.Pattern;

/**
 * @author Ahmed Khoumsi
 * Cette classe effectue l'analyse lexicale
 */
public class AnalLex {
  private String chaine;
  private int curseur;
  private Etat etat;
  private String expression;
  private int longueurExpression;

/**
 * Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String expression) {  // arguments possibles
    chaine = "";
    curseur = 0;
    etat = Etat.INITIAL;
    this.expression = expression;
    longueurExpression = expression.length();
  }

  /**
   * Appelee a chaque detection d'erreur lexicale
   * Elle envoie un message qui informe sur le lieu et le type d'erreur lexicale detectee
   *
   * @return false  si tous les terminaux de l'expression arithmetique ont ete retournes et
   *       true s'il reste encore au moins un terminal qui n'a pas ete retourne
   */
  public boolean resteTerminal( ) {
    return curseur < longueurExpression;
  }
  
/**
 * Retourne le prochain terminal
 * Cette methode est une implementation d'un AEF
 */
  public Terminal prochainTerminal( ) {

    if (resteTerminal()) {
      char c = expression.charAt(curseur);
      curseur++;

      if (etat == Etat.INITIAL) {
        chaine = "";

        if (isANumber(c)){
          etat = Etat.NOMBRE;
          chaine += c;

        } else if (isACapitalLetter(c)) {
          etat = Etat.IDENTIFIANT;
          chaine += c;
          
        } else if (isAnOperator(c)) {
          chaine += c;
          etat = Etat.INITIAL;
          return new Terminal(chaine, TerminalType.op);

        } else if (!isASpace(c)) {
          ErreurLex("Caractere invalide a l'index " + (curseur - 1));
        }

      } else if (etat == Etat.NOMBRE) {

        if (isANumber(c)){
          etat = Etat.NOMBRE;
          chaine += c;

        } else if (isAnOperator(c) || isASpace(c)) {
          if (isAnOperator(c)) {
            curseur -= 1;
          }
          etat = Etat.INITIAL;
          return new Terminal(chaine, TerminalType.nb);

        } else {
          ErreurLex("Caractere invalide a l'index " + (curseur - 1));
        }

      } else if (etat == Etat.IDENTIFIANT) {

        if (isALetter(c)) {
          etat = Etat.IDENTIFIANT;
          chaine += c;

        } else if (c == '_') {
          etat = Etat.TIRETBAS;
          chaine += c;

        } else if (isAnOperator(c) || isASpace(c)) {
          if (isAnOperator(c)) {
            curseur -= 1;
          }
          etat = Etat.INITIAL;
          return new Terminal(chaine, TerminalType.id);

        } else {
          ErreurLex("Caractere invalide a l'index " + (curseur - 1));
        }

      } else if (etat == Etat.TIRETBAS) {

        if (isALetter(c)) {
          etat = Etat.IDENTIFIANT;
          chaine += c;

        } else {
          ErreurLex("Caractere invalide a l'index " + (curseur - 1));
        }
      }
    }
    return null;
  }

  /**
 * Envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {
    throw  new IllegalArgumentException(s);
  }

  private boolean isAnOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
  }

  private boolean isASpace(char c) {
    return c == ' ' || c == '\n';
  }

  private boolean isANumber(char c) {
    return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
            c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
  }

  private boolean isALetter(char c) {
    return isACapitalLetter(c) || isALowercaseLetter(c);
  }

  private boolean isACapitalLetter(char c) {
    return c == 'A' || c == 'B' ||c == 'C' ||c == 'D' ||c == 'E' ||c == 'F' ||c == 'G' ||c == 'H' ||c == 'I' ||
            c == 'J' || c == 'K' ||c == 'L' ||c == 'M' ||c == 'N' ||c == 'O' ||c == 'P' ||c == 'Q' ||c == 'R' ||
            c == 'S' || c == 'T' ||c == 'U' ||c == 'V' ||c == 'W' ||c == 'X' ||c == 'Y' ||c == 'Z';
  }

  private boolean isALowercaseLetter(char c) {
    return c == 'a' || c == 'b' ||c == 'c' ||c == 'd' ||c == 'e' ||c == 'f' ||c == 'g' ||c == 'h' ||c == 'i' ||
            c == 'j' || c == 'k' ||c == 'l' ||c == 'm' ||c == 'n' ||c == 'o' ||c == 'p' ||c == 'q' ||c == 'r' ||
            c == 's' || c == 't' ||c == 'u' ||c == 'v' ||c == 'w' ||c == 'x' ||c == 'y' ||c == 'z';
  }
  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");

    if (args.length == 0){
    args = new String [2];
            args[0] = "(U_x + V_y ) * W_z / 35\n";
            args[1] = "ResultatLexical.txt";
    }

    AnalLex lexical = new AnalLex(args[0]); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;

    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();

      if (t != null) {
        System.out.println(t.type + " " + t.chaine);
        toWrite += t.chaine + " ";  // toWrite contient le resultat d'analyse lexicale
      }
    }
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
