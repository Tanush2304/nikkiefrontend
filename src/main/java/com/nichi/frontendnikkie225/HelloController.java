package com.nichi.frontendnikkie225;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.nichi.frontendnikkie225.Database.getConnection;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<divisordto> divisorTable;
    @FXML private TableColumn<divisordto, String> divIndexCol;
    @FXML private TableColumn<divisordto, Integer> divFromDtCol;
    @FXML private TableColumn<divisordto, Integer> divToDtCol;
    @FXML private TableColumn<divisordto, Double> divDivisorCol;
    @FXML private TableColumn<divisordto, String> divUpdateSourceCol;
    @FXML private TableColumn<divisordto, String> divUpdateTimeCol;
    @FXML private Button divisorLoadBtn;
    @FXML private Button divisorSaveBtn;

    @FXML
    private TableView<dividenddto> dividendTable;
    @FXML private TableColumn<dividenddto, Integer> dividendDt;
    @FXML private TableColumn<dividenddto, String> dividendIndex;
    @FXML private TableColumn<dividenddto, Integer> dividendDivDt;
    @FXML private TableColumn<dividenddto, String> dividendValue;
    @FXML private TableColumn<dividenddto, String> dividendUpdateSource;
    @FXML private TableColumn<dividenddto, String> dividendUpdateTime;
    @FXML private Button dividendLoadBtn;

    @FXML private TableView<reporatedto> RepoRate;
    @FXML private TableColumn<reporatedto, String> Repdt;
    @FXML private TableColumn<reporatedto, String> RepIndex;
    @FXML private TableColumn<reporatedto, String> RepTerm;
    @FXML private TableColumn<reporatedto, String> RepBid;
    @FXML private TableColumn<reporatedto, String> RepOffer;
    @FXML private TableColumn<reporatedto, String> RepUpdateSource;
    @FXML private TableColumn<reporatedto, String> RepUpdateTime;
    @FXML private Button repoRateLoadBtn;

    @FXML private TableView<fundingratedto> FundingRate;
    @FXML private TableColumn<fundingratedto, String> FRdt;
    @FXML private TableColumn<fundingratedto, String> FRFIndec;
    @FXML private TableColumn<fundingratedto, String> FRTerm;
    @FXML private TableColumn<fundingratedto, String> FRbid;
    @FXML private TableColumn<fundingratedto, String> FRoffer;
    @FXML private TableColumn<fundingratedto, String> FRUpdateSource;
    @FXML private TableColumn<fundingratedto, String> FRUpdateTime;
    @FXML private Button fundingRateLoadBtn;

    private final ObservableList<divisordto> divisorData = FXCollections.observableArrayList();
    private final ObservableList<fundingratedto> fundingData = FXCollections.observableArrayList();
    private final ObservableList<dividenddto> dividendData = FXCollections.observableArrayList();
    private final ObservableList<reporatedto> reportedData = FXCollections.observableArrayList();


    List<divisordto> newValue = new ArrayList<>();

    @FXML
    private void initialize() {
        divisorTable.setEditable(true);
        setupContextMenu();
        setupDivisorTable();

        dividendDt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dividendIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        dividendDivDt.setCellValueFactory(new PropertyValueFactory<>("divDate"));
        dividendValue.setCellValueFactory(new PropertyValueFactory<>("dividend"));
        dividendUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        dividendUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        Repdt.setCellValueFactory(new PropertyValueFactory<>("Date"));
        RepIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        RepTerm.setCellValueFactory(new PropertyValueFactory<>("term"));
        RepBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        RepOffer.setCellValueFactory(new PropertyValueFactory<>("offer"));
        RepUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        RepUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        FRdt.setCellValueFactory(new PropertyValueFactory<>("Date"));
        FRFIndec.setCellValueFactory(new PropertyValueFactory<>("index"));
        FRTerm.setCellValueFactory(new PropertyValueFactory<>("term"));
        FRbid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        FRoffer.setCellValueFactory(new PropertyValueFactory<>("offer"));
        FRUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        FRUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        divisorLoadBtn.setOnAction(e -> {
            divisorData.clear();
            loaddivisorTable();
            divisorTable.setItems(divisorData);
        });

        dividendLoadBtn.setOnAction(e -> {
            dividendData.clear();
            loaddividendTable();
            dividendTable.setItems(dividendData);
        });

        repoRateLoadBtn.setOnAction(e -> {
            reportedData.clear();
            loadreporateTable();
            RepoRate.setItems(reportedData);
        });

        fundingRateLoadBtn.setOnAction(e -> {
            fundingData.clear();
            loadfundingratetable();
            FundingRate.setItems(fundingData);
        });
    }

    private void setupDivisorTable() {
        divisorTable.setEditable(true);

        divIndexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        divFromDtCol.setCellValueFactory(new PropertyValueFactory<>("fromDt"));
        divToDtCol.setCellValueFactory(new PropertyValueFactory<>("toDt"));
        divDivisorCol.setCellValueFactory(new PropertyValueFactory<>("divisor"));
        divUpdateSourceCol.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        divUpdateTimeCol.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        divIndexCol.setCellFactory(TextFieldTableCell.forTableColumn());
        divFromDtCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        divToDtCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        divDivisorCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        divUpdateSourceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        divUpdateTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        divisorSaveBtn.setOnAction(e -> {
            saveDivisorData();
        });
    }

    private void loaddivisorTable() {
        System.out.println("loading data");
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement("select * from marketdivisor")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                divisordto divis = new divisordto(
                        rs.getString("index"),
                        rs.getInt("fromdt"),
                        rs.getInt("todt"),
                        rs.getDouble("divisor"),
                        rs.getString("updatesource"),
                        rs.getString("updatetime")
                );
                divisorData.add(divis);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loaddividendTable() {
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement("select * from marketdividend")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dividenddto divid = new dividenddto(
                        rs.getInt("dt"),
                        rs.getString("index"),
                        rs.getInt("divdt"),
                        rs.getString("dividend"),
                        rs.getString("updatesource"),
                        rs.getString("updatetime")
                );
                dividendData.add(divid);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadreporateTable() {
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement("select * from marketreporate")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                reporatedto rept = new reporatedto(
                        rs.getString("dt"),
                        rs.getString("index"),
                        rs.getString("term"),
                        rs.getString("bid"),
                        rs.getString("offer"),
                        rs.getString("updatesource"),
                        rs.getString("updatetime")
                );
                reportedData.add(rept);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadfundingratetable() {
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement("select * from marketfundingrate")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                fundingratedto fund = new fundingratedto(
                        rs.getString("dt"),
                        rs.getString("index"),
                        rs.getString("term"),
                        rs.getString("bid"),
                        rs.getString("offer"),
                        rs.getString("updatesource"),
                        rs.getString("updatetime")
                );
                fundingData.add(fund);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveDivisorData() {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO marketdivisor (index, fromdt, todt, divisor, updatesource, updatetime) VALUES (?, ?, ?, ?, ?, ?)";
            for (divisordto divisor : divisorData) {
                try (PreparedStatement stmt = con.prepareStatement(query)) {
                    stmt.setString(1, divisor.getIndex());
                    stmt.setInt(2, divisor.getFromDt());
                    stmt.setInt(3, divisor.getToDt());
                    stmt.setDouble(4, divisor.getDivisor());
                    stmt.setString(5, "manual");
                    stmt.setString(6, getDateTime());
                    stmt.executeUpdate();
                    System.out.println("Saved: " + divisor);
                }
                newValue.clear();
            }
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    private void setupContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addMenuItem = new MenuItem("Add");
        addMenuItem.setOnAction(e -> addDivisorData());
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(e -> deleteDivisorData());
        contextMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        divisorTable.setContextMenu(contextMenu);
    }

    private void addDivisorData() {
        divisordto newDivisor = new divisordto("", 0, 0, 0.0, "", getDateTime());
        newValue.add(newDivisor);
        divisorData.add(newDivisor);
        newValue.add(newDivisor);
        divisorTable.refresh();
        divisorTable.scrollTo(newDivisor);
        divisorTable.getSelectionModel().select(newDivisor);
    }

    private void deleteDivisorData() {
        divisordto selectedDivisor = divisorTable.getSelectionModel().getSelectedItem();
        if (selectedDivisor != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                divisorData.remove(selectedDivisor);
            }
        }
    }
}
