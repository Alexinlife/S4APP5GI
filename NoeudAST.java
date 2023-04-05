package app6;

/**
 * Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  ElemAST feuilleGauche;
  ElemAST feuilleDroite;

  /**
   * Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal t, ElemAST fg, ElemAST fd) { // avec arguments
    terminal = t;
    feuilleGauche = fg;
    feuilleDroite = fd;
  }

  /**
   * Constructeur avec chaine du symbole en parametre
   * @param c chaine du symbole
   * @param t type de symbole
   * @param fg feuille gauche
   * @param fd feuille droite
   */
  public NoeudAST(String c, TerminalType t, ElemAST fg, ElemAST fd) { // avec arguments
    terminal = new Terminal(c,t);
    feuilleGauche = fg;
    feuilleDroite = fd;
  }
  /**
   * Evaluation de noeud d'AST
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

  /**
   * Lecture de noeud d'AST
   */
  public String LectAST( ) {
           if (terminal.chaine == "+")    {
      return "(" + feuilleGauche.LectAST() + " + " + feuilleDroite.LectAST() + ")";
    } else if (terminal.chaine == "-") {
      return "(" + feuilleGauche.LectAST() + " - " + feuilleDroite.LectAST() + ")";
    } else if (terminal.chaine == "*") {
      return "(" + feuilleGauche.LectAST() + " * " + feuilleDroite.LectAST() + ")";
    } else if (terminal.chaine == "/") {
      return "(" + feuilleGauche.LectAST() + " / " + feuilleDroite.LectAST() + ")";
    }
    return null;
  }

  /**
   * Lecture de noeud postfix d'AST
   */
  public String LectPostfix( ) {
    if (terminal.chaine == "+")    {
      return feuilleGauche.LectPostfix() + " " + feuilleDroite.LectPostfix() + " + ";
    } else if (terminal.chaine == "-") {
      return feuilleGauche.LectPostfix() + " " + feuilleDroite.LectPostfix() + " - ";
    } else if (terminal.chaine == "*") {
      return feuilleGauche.LectPostfix() + " " + feuilleDroite.LectPostfix() + " * ";
    } else if (terminal.chaine == "/") {
      return feuilleGauche.LectPostfix() + " " + feuilleDroite.LectPostfix() + " / ";
    }
    return null;
  }
}


