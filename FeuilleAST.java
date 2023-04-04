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
    return Integer.parseInt(terminal.chaine);
  }

 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return terminal.chaine;
  }

}
