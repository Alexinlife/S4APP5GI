package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST( ) {  // avec arguments
    //
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    return Integer.getInteger(terminal.chaine);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return terminal.chaine;
  }

}
