package fr.univ_amu.iut.exercice3;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Contrôleur de la vue {@code FormulaireConnexionView.fxml}.
 *
 * <p>Concepts introduits :
 *
 * <ul>
 *   <li>injection de plusieurs types de contrôles via {@code @FXML} ({@link TextField}, {@link
 *       PasswordField}, {@link Button}, {@link Label})
 *   <li>plusieurs handlers reliés au FXML par {@code onAction="#..."}
 *   <li>mise en place des bindings de validation dans {@link #initialize()} (l'équivalent en FXML
 *       du {@code createBindings()} du TP2 exercice 6)
 *   <li>utilisation d'un {@link BooleanBinding} bas niveau pour exprimer une règle de validation
 *       qui ne se factorise pas avec les opérateurs {@link Bindings} de haut niveau
 * </ul>
 *
 * <p>Règles de validation (identiques au TP2 exercice 6) :
 *
 * <ul>
 *   <li>Le champ mot de passe n'est éditable que si l'identifiant contient au moins 6 caractères
 *   <li>Le bouton OK n'est actif que si le mot de passe est valide (>= 8 caractères, contient au
 *       moins une majuscule et au moins un chiffre)
 *   <li>Le bouton Annuler est désactivé si les deux champs sont vides
 * </ul>
 */
public class FormulaireConnexionController {

  @FXML private TextField champIdentifiant;

  @FXML private PasswordField champMotDePasse;

  @FXML private Button boutonOk;

  @FXML private Button boutonAnnuler;

  @FXML private Label labelMessage;

  /**
   * Méthode invoquée automatiquement par {@link FXMLLoader} une fois que tous les
   * champs annotés {@code @FXML} ont été injectés. C'est ici qu'on installe les bindings de
   * validation.
   */

  @FXML
  private void initialize() {
    champMotDePasse.editableProperty().bind(
            Bindings.greaterThanOrEqual(champIdentifiant.textProperty().length(), 6)
    );
    boutonAnnuler.disableProperty().bind(
            Bindings.and(
                    Bindings.equal(0, champIdentifiant.textProperty().length()),
                    Bindings.equal(0, champMotDePasse.textProperty().length())
            )
    );
    BooleanBinding motDePasseInvalide = new BooleanBinding() {
      // Le bloc d'initialisation (les accolades seules) :
      {
        super.bind(champMotDePasse.textProperty());
      }
      @Override
      protected boolean computeValue() {
        String mdp = champMotDePasse.getText();
        if (mdp == null) return true;
        return mdp.length() < 8 || !mdp.matches(".*[A-Z].*") || !mdp.matches(".*[0-9].*");
      }
    };
    boutonOk.disableProperty().bind(motDePasseInvalide);
  }

  @FXML
  private void valider() {
    String mdp = champMotDePasse.getText();
    String user = champIdentifiant.getText();
    String result = "";
    for (int i = 0; i < mdp.length(); i++) {
      result += "*";
    }
    labelMessage.setText(user + " " + result);
  }

  /** Action du bouton Annuler. Vide les deux champs et le label de message. */
  @FXML
  private void annuler() {
    // TODO exercice 3 : vider les deux champs et le label message.
    champMotDePasse.setText("");
    champIdentifiant.setText("");
    labelMessage.setText("");
  }
}
