package app6;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
enum TerminalType {id, op, nb};
public class Terminal {

String chaine;
TerminalType type;
boolean isTerminal;

/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal(String chaine, TerminalType type) {
    this.chaine = chaine;
    this.type = type;
    this.isTerminal = false;
  }
}
