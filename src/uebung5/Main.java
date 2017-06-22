package uebung5;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;

public class Main extends Application {

	private static NXTInfo[] nxts;
	private static NXTConnector conn = new NXTConnector();
	private static Scanner sc = new Scanner(System.in);
	int counter = 0;
	private FxmlController controller;

	@Override
	public void start(Stage primaryStage) {

		System.out.println("Suche BT NXTs...");
		nxts = conn.search("", null, NXTCommFactory.BLUETOOTH);

		for (int i = 0; i < nxts.length; i++) {
			System.out.println(i + ": " + nxts[i].name + " " + nxts[i].deviceAddress);
		}

		int index = sc.nextInt();
		if (!conn.connectTo(nxts[index], NXTComm.PACKET)) {
			System.out.println("Konnte nicht verbinden...");
			System.exit(0);
		}

		NXTComm comm = conn.getNXTComm();
		sc.useDelimiter(System.getProperty("line.separator"));

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				byte[] reply;
				while (true) {
					try {
						reply = comm.read();
						String s = new String(reply);
						System.out.println(s);
						handelInput(s);
						counter++;
						Thread.sleep(1000);
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		t.start();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			Pane root = (Pane) loader.load();
			controller = loader.<FxmlController>getController();
			//controller.addVoltageData(1, "test");
			// BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Segway-GUI");
			primaryStage.show();
			controller.setText("hallo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void handelInput(String s) {
		String[] split = s.split(";");
		for (int i = 0; i < split.length; i++) {
			String[] split2 = split[i].split("=");
			switch (i) {
			case 0:
				System.out.println(split2[1]);
				controller.addVoltageData(counter, split2[1]);
				break;
			case 1:
				controller.addCycleTimeData(counter, split2[1]);

				break;
			case 2:
				controller.addOffsetData(counter, split2[1]);
	
				break;
			case 3:
				controller.addGyroAngelData(counter, split2[1]);

				break;
			case 4:
				controller.addMotorData(counter, split2[1]);

				break;

			default:
				break;
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
