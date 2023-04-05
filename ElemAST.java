package app6;

/**
 * Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {

  Terminal terminal;
  
  /**
   * Evaluation d'AST
   */
  public abstract int EvalAST();

  /**
   * Lecture d'AST
   */
  public abstract String LectAST();
  public abstract String LectPostfix();

/**
 * ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
 */  
  public void ErreurEvalAST(String s) {	
    System.out.println(s);
  }

}
