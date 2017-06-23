package uebung5;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTConnector;

public class FxmlController {

	@FXML
	private LineChart<Number, Number> areaBattery;
	@FXML
	private LineChart<Number, Number> areaCycleTime;
	@FXML
	private LineChart<Number, Number> areaGyroAngel;
	@FXML
	private LineChart<Number, Number> areaOffset;
	@FXML
	private LineChart<Number, Number> areaMotor;
	@FXML
	private TextField tfKp;
	@FXML
	private TextField tfTn;
	@FXML
	private TextField tfTv;
	@FXML
	private Button btSend;
	@FXML
	private Button btUp;
	@FXML
	private Button btLeft;
	@FXML
	private Button btDown;
	@FXML
	private Button btRight;

	private XYChart.Series voltage = new XYChart.Series<>();
	private ObservableList<Series<Number, Number>> voltageData;
	
	private XYChart.Series cycleTime = new XYChart.Series<>();
	private ObservableList<Series<Number, Number>> cycleTimeData;
	private XYChart.Series gyroAngel = new XYChart.Series<>();
	private ObservableList<Series<Number, Number>> gyroAngelData;
	private XYChart.Series offset = new XYChart.Series<>();
	private ObservableList<Series<Number, Number>> offsetData;
	private XYChart.Series motor = new XYChart.Series<>();
	private ObservableList<Series<Number, Number>> motorData;

	private NXTComm comm;

	@FXML
	public void buttonSendOnAction() {
	}

	@FXML
	public void buttonUp() {
		String toSend = "steering=0;driving=-0.15";
		try {
			comm.write(toSend.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void buttonLeft() {
		String toSend = "steering=5;driving=0";
		try {
			comm.write(toSend.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void buttonDown() {
		String toSend = "steering=0;driving=0.15";
		try {
			comm.write(toSend.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void buttonRight() {
		String toSend = "steering=-5;driving=0";
		try {
			comm.write(toSend.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setText(String string) {
		// TODO Auto-generated method stub
		btSend.setText(string);
	}

	public void addVoltageData(int counter, String string) {
		if (voltageData == null) {
			voltageData = areaBattery.getData();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update UI here.
				if (voltageData.size() == 0) {
					voltageData.add(voltage);
				}
				Series<Number, Number> series = voltageData.get(0);
				series.getData().add(new XYChart.Data(counter, Double.valueOf(string)));
			}
		});

	}

	public void addCycleTimeData(int counter, String string) {
		if (cycleTimeData == null) {
			cycleTimeData = areaCycleTime.getData();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update UI here.
				if (cycleTimeData.size() == 0) {
					cycleTimeData.add(cycleTime);
				}
				Series<Number, Number> series = cycleTimeData.get(0);
				series.getData().add(new XYChart.Data(counter, Double.valueOf(string)));
			}
		});

	}
	public void addGyroAngelData(int counter, String string) {
		if (gyroAngelData == null) {
			gyroAngelData = areaGyroAngel.getData();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update UI here.
				if (gyroAngelData.size() == 0) {
					gyroAngelData.add(gyroAngel);
				}
				Series<Number, Number> series = gyroAngelData.get(0);
				series.getData().add(new XYChart.Data(counter, Double.valueOf(string)));
			}
		});

	}
	public void addOffsetData(int counter, String string) {
		if (offsetData == null) {
			offsetData = areaOffset.getData();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update UI here.
				if (offsetData.size() == 0) {
					offsetData.add(offset);
				}
				Series<Number, Number> series = offsetData.get(0);
				series.getData().add(new XYChart.Data(counter, Double.valueOf(string)));
			}
		});

	}
	public void addMotorData(int counter, String string) {
		if (motorData == null) {
			motorData = areaMotor.getData();
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update UI here.
				if (motorData.size() == 0) {
					motorData.add(motor);
				}
				Series<Number, Number> series = motorData.get(0);
				series.getData().add(new XYChart.Data(counter, Double.valueOf(string)));
			}
		});

	}
	
	public void setNxtComm(NXTComm nxtComm){
		this.comm = nxtComm;
	}

	
	
}
