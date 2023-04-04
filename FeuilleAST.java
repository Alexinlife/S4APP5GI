package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal t) {  // avec arguments
    terminal = t;
  }
  public FeuilleAST(String c, TerminalType t) {  // avec arguments
    terminal = new Terminal(c,t);
  }

  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    if (isANumber(terminal.chaine)){return Integer.parseInt(terminal.chaine);}
    ErreurEvalAST("Erreur : aucune valeur ne peut etre associe a l'identifiant " + terminal.chaine + " (considere comme 0)");
    return 0;
  }
public boolean isANumber (String s){
    for (char c:s.toCharArray()
         ) {
        if (!DescenteRecursive.isANumber(c)) {
            return false;
        }
    }
    return true;
}
 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return terminal.chaine;
  }
    public String LectPostfix( ) {
        return terminal.chaine;
    }

}
