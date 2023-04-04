package app6;

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
    etat = Etat.ZERO;
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
      char caractere = expression.charAt(curseur);
      curseur++;

      if (etat == Etat.ZERO) {

        if (isaNumber(caractere)){
          etat = Etat.UN;
          chaine += caractere;

        } else if (isanOperator(caractere)) {
          chaine += caractere;
          return new Terminal(chaine, TerminalType.op);

        } else {
          ErreurLex("Caractere invalide a l'index " + (curseur - 1));
        }

      } else if (etat == Etat.UN) {

        if (isaNumber(caractere)){
          etat = Etat.UN;
          chaine += caractere;

        } else {
          curseur -= 1;
          return new Terminal(chaine, TerminalType.nb);
        }
      }
    }
    return null;
  }

  private boolean isanOperator(char caractere) {
    return caractere == '+' ||
            caractere == '-' ||
            caractere == '*' ||
            caractere == '/';
  }

  private boolean isaNumber(char caractere) {
    return caractere == '0' ||
            caractere == '1' ||
            caractere == '2' ||
            caractere == '3' ||
            caractere == '4' ||
            caractere == '5' ||
            caractere == '6' ||
            caractere == '7' ||
            caractere == '8' ||
            caractere == '9';
  }


  /**
 * Envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {
    throw  new IllegalArgumentException(s);
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
