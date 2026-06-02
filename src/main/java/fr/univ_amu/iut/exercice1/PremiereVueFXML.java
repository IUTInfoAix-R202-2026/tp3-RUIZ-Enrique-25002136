package fr.univ_amu.iut.exercice1;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Exercice 1 : première vue décrite en FXML.
 *
 * <p>
 * Aucun code Java ne construit l'interface ici : tout est dans
 * {@code PremiereVueFXML.fxml}.
 * Cette classe se contente de <b>charger</b> le fichier FXML et de l'afficher
 * dans une {@link
 * Stage}.
 *
 * <p>
 * Concepts introduits :
 *
 * <ul>
 * <li>{@link FXMLLoader#load(URL)} : le pont entre XML et objets Java
 * <li>{@code getClass().getResource("...")} : chemin vers la ressource FXML
 * <li>Racine unique ({@code BorderPane}) retournée par
 * {@code FXMLLoader.load()}
 * </ul>
 */
public class PremiereVueFXML extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // 1. Construire l'URL vers la ressource "PremiereVueFXML.fxml".
    URL resource = getClass().getResource("PremiereVueFXML.fxml");

    // 2. Charger le fichier FXML et obtenir la racine des la scène.
    Parent root = FXMLLoader.load(resource);

    // 3. Créer une Scene avec cette racine et l'associer au stage.
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);

    // 4. Donner un titre à la fenêtre et l'afficher.
    primaryStage.setTitle("Première vue FXML");
    primaryStage.show();
  }
}
