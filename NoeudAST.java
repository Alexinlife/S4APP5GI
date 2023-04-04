package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  ElemAST feuilleGauche;
  ElemAST feuilleDroite;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal t, ElemAST fg, ElemAST fd) { // avec arguments
    terminal = t;
    feuilleGauche = fg;
    feuilleDroite = fd;
  }

  public NoeudAST(String c, TerminalType t, ElemAST fg, ElemAST fd) { // avec arguments
    terminal = new Terminal(c,t);
    feuilleGauche = fg;
    feuilleDroite = fd;
  }
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
    if (terminal.chaine == "+")    {
      return feuilleGauche.EvalAST() + feuilleDroite.EvalAST();
    } else if (terminal.chaine == "-") {
      return feuilleGauche.EvalAST() - feuilleDroite.EvalAST();
    } else if (terminal.chaine == "*") {
      return feuilleGauche.EvalAST() * feuilleDroite.EvalAST();
    } else if (terminal.chaine == "/") {
      return feuilleGauche.EvalAST() / feuilleDroite.EvalAST();
    }
    return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
           if (terminal.chaine == "+")    {
      return feuilleGauche.LectAST() + " " + feuilleDroite.LectAST() + " + ";
    } else if (terminal.chaine == "-") {
      return feuilleGauche.LectAST() + " " + feuilleDroite.LectAST() + " - ";
    } else if (terminal.chaine == "*") {
      return feuilleGauche.LectAST() + " " + feuilleDroite.LectAST() + " * ";
    } else if (terminal.chaine == "/") {
      return feuilleGauche.LectAST() + " " + feuilleDroite.LectAST() + " / ";
    }
    return null;
  }

}


