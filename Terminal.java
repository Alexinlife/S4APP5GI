package app6;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

String chaine;
String type;
boolean isTerminal;

/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal(String chaine, String type) {
    this.chaine = chaine;
    this.type = type;
    this.isTerminal = false;
  }
}
