package app6;

import java.util.regex.Pattern;

/**
 * Cette classe effectue l'analyse lexicale
 */
public class AnalLex {
  private char c; // caractere lu de l'expression
  private String chaine; // construction du symbole
  private int curseur; // position dans l'expression
  private Etat etat; // etat courant de l'analyseur
  private String expression; // expression a evaluer
  private int longueurExpression;

  /**
   * Constructeur pour l'initialisation d'attribut(s)
   * @param expression a evaluer
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
   * Methode principale de l'analyseur lexical
   * @return le prochain terminal valide
   */
  public Terminal prochainTerminal( ) {

    if (resteTerminal()) {
      c = expression.charAt(curseur);
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
          ErreurLex("Les caracteres supportes a ce stade sont les nombres, les lettres majuscules, " +
                  "les operateurs +, -, *, /, ( et ), les espaces ainsi que les retours de ligne.");
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
          ErreurLex("Les caracteres supportes a ce stade sont les nombres, les operateurs +, -, *, /, ( et ), " +
                  "les espaces ainsi que les retours de ligne.");
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
          ErreurLex("Les caracteres supportes a ce stade sont les lettres, les tirets bas, " +
                  "les operateurs +, -, *, /, ( et ), les espaces ainsi que les retours de ligne.");
        }

      } else if (etat == Etat.TIRETBAS) {

        if (isALetter(c)) {
          etat = Etat.IDENTIFIANT;
          chaine += c;

        } else {
          ErreurLex("Les caracteres supportes a ce stade sont les lettres. " +
                  "Un tiret bas ne peut pas etre suivi d'un deuxieme ni etre le dernier caractere d'un identifiant.");
        }
      }
    }
    return null;
  }

  /**
   * Envoie un message d'erreur lexical comprehensif
   * @param s texte personnalise selon le contexte
   */
  public void ErreurLex(String s) {
    String errPtr = "";

    for (int i = 0; i < curseur - 1; i++) {
      errPtr += " ";
    }
    errPtr += '^';

    String msg = "\nERREUR DE L'ANALYSEUR LEXICAL\n" +
            "Caractere  '" + c + "' invalide a l'index " + (curseur - 1) + '\n' +
            expression.trim() + '\n' +
            errPtr + '\n' +
            s;
    throw  new IllegalArgumentException(msg);
  }

  /**
   * valide si le caractere lu est un operateur
   * @param c caractere lu de l'expression
   * @return true si le caractere est un operateur, sinon false
   */
  private boolean isAnOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
  }

  /**
   * valide si le caractere lu est un espace ou un retour de ligne
   * @param c caractere lu de l'expression
   * @return true si le caractere est un espace ou un retour de ligne, sinon false
   */
  private boolean isASpace(char c) {
    return c == ' ' || c == '\n';
  }

  /**
   * valide si le caractere lu est un nombre
   * @param c caractere lu de l'expression
   * @return true si le caractere est un nombre, sinon false
   */
  private boolean isANumber(char c) {
    return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
            c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
  }

  /**
   * valide si le caractere lu est une lettre
   * @param c caractere lu de l'expression
   * @return true si le caractere est une lettre, sinon false
   */
  private boolean isALetter(char c) {
    return isACapitalLetter(c) || isALowercaseLetter(c);
  }

  /**
   * valide si le caractere lu est une lettre majuscule
   * @param c caractere lu de l'expression
   * @return true si le caractere est une lettre majuscule, sinon false
   */
  private boolean isACapitalLetter(char c) {
    return c == 'A' || c == 'B' ||c == 'C' ||c == 'D' ||c == 'E' ||c == 'F' ||c == 'G' ||c == 'H' ||c == 'I' ||
            c == 'J' || c == 'K' ||c == 'L' ||c == 'M' ||c == 'N' ||c == 'O' ||c == 'P' ||c == 'Q' ||c == 'R' ||
            c == 'S' || c == 'T' ||c == 'U' ||c == 'V' ||c == 'W' ||c == 'X' ||c == 'Y' ||c == 'Z';
  }

  /**
   * valide si le caractere lu est une lettre minuscule
   * @param c caractere lu de l'expression
   * @return true si le caractere est une lettre minuscule, sinon false
   */
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
            args[0] = "(U_x + V_y ) * W__z / 35\n\n";
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
