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
import java.text.ParseException;
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
    @FXML
    private TableColumn<divisordto, String> divIndexCol;
    @FXML
    private TableColumn<divisordto, Integer> divFromDtCol;
    @FXML
    private TableColumn<divisordto, Integer> divToDtCol;
    @FXML
    private TableColumn<divisordto, Double> divDivisorCol;
    @FXML
    private TableColumn<divisordto, String> divUpdateSourceCol;
    @FXML
    private TableColumn<divisordto, String> divUpdateTimeCol;
    @FXML
    private Button divisorLoadBtn;
    @FXML
    private Button divisorSaveBtn;

    @FXML
    private TableView<dividenddto> dividendTable;
    @FXML
    private TableColumn<dividenddto, Integer> dividendDt;
    @FXML
    private TableColumn<dividenddto, String> dividendIndex;
    @FXML
    private TableColumn<dividenddto, Integer> dividendDivDt;
    @FXML
    private TableColumn<dividenddto, Double> dividendValue;
    @FXML
    private TableColumn<dividenddto, String> dividendUpdateSource;
    @FXML
    private TableColumn<dividenddto, String> dividendUpdateTime;
    @FXML
    private Button dividendLoadBtn;
    @FXML
    private Button dividendSaveBtn;

    @FXML
    private TableView<reporatedto> RepoRate;
    @FXML
    private TableColumn<reporatedto, Integer> Repdt;
    @FXML
    private TableColumn<reporatedto, String> RepIndex;
    @FXML
    private TableColumn<reporatedto, Integer> RepTerm;
    @FXML
    private TableColumn<reporatedto, Double> RepBid;
    @FXML
    private TableColumn<reporatedto, Double> RepOffer;
    @FXML
    private TableColumn<reporatedto, String> RepUpdateSource;
    @FXML
    private TableColumn<reporatedto, String> RepUpdateTime;
    @FXML
    private Button repoRateLoadBtn;
    @FXML
    private Button repoRateSaveBtn;

    @FXML
    private TableView<fundingratedto> FundingRate;
    @FXML
    private TableColumn<fundingratedto, Integer> FRdt;
    @FXML
    private TableColumn<fundingratedto, String> FRFIndec;
    @FXML
    private TableColumn<fundingratedto, Integer> FRTerm;
    @FXML
    private TableColumn<fundingratedto, Double> FRbid;
    @FXML
    private TableColumn<fundingratedto, Double> FRoffer;
    @FXML
    private TableColumn<fundingratedto, String> FRUpdateSource;
    @FXML
    private TableColumn<fundingratedto, String> FRUpdateTime;
    @FXML
    private Button fundingRateLoadBtn;
    @FXML
    private Button fundingRateSaveBtn;

    private final ObservableList<divisordto> divisorData = FXCollections.observableArrayList();
    private final ObservableList<fundingratedto> fundingData = FXCollections.observableArrayList();
    private final ObservableList<dividenddto> dividendData = FXCollections.observableArrayList();
    private final ObservableList<reporatedto> reportedData = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        divisorTable.setEditable(true);
        setupContextMenu();
        setupContextMenu1();
        setupContextMenu2();
        setupContextMenu3();
        setupDivisorTable();
        setupDividendTable();
        setreportTable();
        setupfundingTable();


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

        divIndexCol.setOnEditCommit(event -> event.getRowValue().setIndex(String.valueOf(event.getNewValue())));
        divFromDtCol.setOnEditCommit(event -> event.getRowValue().setFromDt(event.getNewValue()));
        divToDtCol.setOnEditCommit(event -> event.getRowValue().setToDt(Integer.valueOf(event.getNewValue())));
        divDivisorCol.setOnEditCommit(event -> event.getRowValue().setDivsior(event.getNewValue()));
        divUpdateSourceCol.setOnEditCommit(event -> event.getRowValue().setUpdateSource(event.getNewValue()));
        divUpdateTimeCol.setOnEditCommit(event -> event.getRowValue().setUpdateTime(event.getNewValue()));


        divisorSaveBtn.setOnAction(e -> {
            saveDivisorData();
            divisorTable.setItems(divisorData);
        });

    }

    private void setupDividendTable() {
        dividendTable.setEditable(true);

        dividendDt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dividendIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        dividendDivDt.setCellValueFactory(new PropertyValueFactory<>("divDate"));
        dividendValue.setCellValueFactory(new PropertyValueFactory<>("dividend"));
        dividendUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        dividendUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));


        dividendDt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dividendIndex.setCellFactory(TextFieldTableCell.forTableColumn());
        dividendDivDt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dividendValue.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dividendUpdateSource.setCellFactory(TextFieldTableCell.forTableColumn());
        dividendUpdateTime.setCellFactory(TextFieldTableCell.forTableColumn());

        dividendDt.setOnEditCommit(event -> event.getRowValue().setDate(Integer.valueOf(String.valueOf(event.getNewValue()))));
        dividendIndex.setOnEditCommit(event -> event.getRowValue().setIndex(event.getNewValue()));
        dividendDivDt.setOnEditCommit(event -> event.getRowValue().setDivDate(Integer.valueOf(event.getNewValue())));
        dividendValue.setOnEditCommit(event -> event.getRowValue().setDividend(Double.valueOf(event.getNewValue())));
        divUpdateSourceCol.setOnEditCommit(event -> event.getRowValue().setUpdateSource(event.getNewValue()));
        divUpdateTimeCol.setOnEditCommit(event -> event.getRowValue().setUpdateTime(event.getNewValue()));


        dividendSaveBtn.setOnAction(e -> {
            savedividendData();
            dividendTable.setItems(dividendData);
        });

    }

    private void setreportTable() {
        RepoRate.setEditable(true);

        Repdt.setCellValueFactory(new PropertyValueFactory<>("Date"));
        RepIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        RepTerm.setCellValueFactory(new PropertyValueFactory<>("term"));
        RepBid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        RepOffer.setCellValueFactory(new PropertyValueFactory<>("offer"));
        RepUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        RepUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));


        Repdt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        RepIndex.setCellFactory(TextFieldTableCell.forTableColumn());
        RepTerm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        RepBid.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        RepOffer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        RepUpdateSource.setCellFactory(TextFieldTableCell.forTableColumn());
        RepUpdateTime.setCellFactory(TextFieldTableCell.forTableColumn());

        Repdt.setOnEditCommit(event -> event.getRowValue().setDate(Integer.valueOf(String.valueOf(event.getNewValue()))));
        RepIndex.setOnEditCommit(event -> event.getRowValue().setIndex(event.getNewValue()));
        RepTerm.setOnEditCommit(event -> event.getRowValue().setTerm(Integer.valueOf(event.getNewValue())));
        RepBid.setOnEditCommit(event -> event.getRowValue().setBid(Double.valueOf(event.getNewValue())));

        RepOffer.setOnEditCommit(event -> event.getRowValue().setOffer(Double.valueOf(event.getNewValue())));
        RepUpdateSource.setOnEditCommit(event -> event.getRowValue().setUpdateSource(event.getNewValue()));
        RepUpdateTime.setOnEditCommit(event -> event.getRowValue().setUpdateTime(event.getNewValue()));


        repoRateSaveBtn.setOnAction(e -> {
            savereportData();
            RepoRate.setItems(reportedData);
        });

    }

    private void setupfundingTable() {
        FundingRate.setEditable(true);

        FRdt.setCellValueFactory(new PropertyValueFactory<>("Date"));
        FRFIndec.setCellValueFactory(new PropertyValueFactory<>("index"));
        FRTerm.setCellValueFactory(new PropertyValueFactory<>("term"));
        FRbid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        FRoffer.setCellValueFactory(new PropertyValueFactory<>("offer"));
        FRUpdateSource.setCellValueFactory(new PropertyValueFactory<>("updateSource"));
        FRUpdateTime.setCellValueFactory(new PropertyValueFactory<>("updateTime"));

        FRdt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        FRFIndec.setCellFactory(TextFieldTableCell.forTableColumn());
        FRTerm.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        FRbid.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        FRoffer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        FRUpdateSource.setCellFactory(TextFieldTableCell.forTableColumn());
        FRUpdateTime.setCellFactory(TextFieldTableCell.forTableColumn());

        FRdt.setOnEditCommit(event -> event.getRowValue().setDate(Integer.valueOf(String.valueOf(event.getNewValue()))));
        FRFIndec.setOnEditCommit(event -> event.getRowValue().setIndex(event.getNewValue()));
        FRTerm.setOnEditCommit(event -> event.getRowValue().setTerm(Integer.valueOf(event.getNewValue())));
        FRbid.setOnEditCommit(event -> event.getRowValue().setBid(Double.valueOf(event.getNewValue())));

        FRoffer.setOnEditCommit(event -> event.getRowValue().setOffer(Double.valueOf(event.getNewValue())));
        FRUpdateSource.setOnEditCommit(event -> event.getRowValue().setUpdateSource(event.getNewValue()));
        FRUpdateTime.setOnEditCommit(event -> event.getRowValue().setUpdateTime(event.getNewValue()));


        fundingRateSaveBtn.setOnAction(e -> {
            savefundingata();

            FundingRate.setItems(fundingData);
        });

    }

    private void loaddivisorTable() {
        System.out.println("loading data");
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement("select * from marketdivisor order by updatetime asc")) {
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
             PreparedStatement stm = con.prepareStatement("select * from marketdividend order by updatetime asc")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dividenddto divid = new dividenddto(
                        rs.getInt("dt"),
                        rs.getString("index"),
                        rs.getInt("divdt"),
                        rs.getDouble("dividend"),
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
             PreparedStatement stm = con.prepareStatement("select * from marketreporate order by updatetime asc")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                reporatedto rept = new reporatedto(
                        rs.getInt("dt"),
                        rs.getString("index"),
                        rs.getInt("term"),
                        rs.getDouble("bid"),
                        rs.getDouble("offer"),
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
             PreparedStatement stm = con.prepareStatement("select * from marketfundingrate order by updatetime asc ")) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                fundingratedto fund = new fundingratedto(
                        rs.getInt("dt"),
                        rs.getString("index"),
                        rs.getInt("term"),
                        rs.getDouble("bid"),
                        rs.getDouble("offer"),
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
            String checkQuery = "SELECT COUNT(*) FROM marketdivisor WHERE index = ? AND fromdt = ? AND todt = ?";
            String insertQuery = "INSERT INTO marketdivisor (index, fromdt, todt, divisor, updatesource, updatetime) VALUES (?, ?, ?, ?, ?, ?)";

            for (divisordto divisor : divisorData) {

                try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, divisor.getIndex());
                    checkStmt.setInt(2, divisor.getFromDt());
                    checkStmt.setInt(3, divisor.getToDt());

                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count == 0) {
                        if (divisor.getFromDt().toString().length() != 8 || !divisor.getFromDt().toString().matches("\\d{8}") || !isValidDate(divisor.getFromDt().toString())) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Tadashii deeta foomatto iremasuyo.");
                            alert.showAndWait();
                        } else if (divisor.getToDt().toString().length() != 8 || !divisor.getToDt().toString().matches("\\d{8}") || !isValidDate(divisor.getToDt().toString())) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Tadashii deeta foomatto iremasuyo.");
                            alert.showAndWait();
                        } else {
                            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                                insertStmt.setString(1, divisor.getIndex());
                                insertStmt.setInt(2, divisor.getFromDt());
                                insertStmt.setInt(3, divisor.getToDt());
                                insertStmt.setDouble(4, divisor.getDivisor());
                                insertStmt.setString(5, "manual");
                                insertStmt.setString(6, getDateTime());
                                insertStmt.executeUpdate();
                                System.out.println("Saved: " + divisor);
                            }
                        }
                    } else {
                        System.out.println("Duplicate record exists for: " + divisor);

                    }
                }
            }

//            divisorData.clear();
//            loaddivisorTable();
//            divisorTable.setItems(divisorData);

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    private void savedividendData() {
        try (Connection con = getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM marketdividend WHERE dt = ? AND index = ? AND Divdt = ?";
            String insertQuery = "INSERT INTO marketdividend (dt, index, divdt ,dividend , updatesource, updatetime) VALUES (?, ?, ?, ?, ?, ?)";

            for (dividenddto dividend : dividendData) {

                try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                    checkStmt.setInt(1, Integer.parseInt(getDateTime1()));
                    checkStmt.setString(2, dividend.getIndex());
                    checkStmt.setInt(3, dividend.getDivDate());

                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count == 0) {
                        if (dividend.getDivDate().toString().length() != 8 || !dividend.getDivDate().toString().matches("\\d{8}") || !isValidDate(dividend.getDivDate().toString())) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Tadashii deeta foomatto iremasuyo.");
                            alert.showAndWait();
                        } else {


                            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                                insertStmt.setInt(1, Integer.parseInt(getDateTime1()));
                                insertStmt.setString(2, dividend.getIndex());
                                insertStmt.setInt(3, dividend.getDivDate());
                                insertStmt.setDouble(4, dividend.getDividend());

                                insertStmt.setString(5, "manual");
                                insertStmt.setString(6, getDateTime());
                                insertStmt.executeUpdate();
                                System.out.println("Saved: " + dividend);
                            }
                        }
                    } else {
                        System.out.println("Duplicate record exists for: " + dividend);

                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void savereportData() {
        try (Connection con = getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM marketreporate WHERE dt = ? AND index = ? AND term = ?";
            String insertQuery = "INSERT INTO marketreporate (dt, index, term , bid ,offer, updatesource, updatetime) VALUES (?, ?, ?,?, ?, ?, ?)";

            for (reporatedto report : reportedData) {

                try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                    checkStmt.setInt(1, Integer.parseInt(getDateTime1()));
                    checkStmt.setString(2, report.getIndex());
                    checkStmt.setInt(3, report.getTerm());

                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count == 0) {
                        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, Integer.parseInt(getDateTime1()));
                            insertStmt.setString(2, report.getIndex());
                            insertStmt.setInt(3, report.getTerm());
                            insertStmt.setDouble(4, report.getBid());
                            insertStmt.setDouble(5, report.getOffer());

                            insertStmt.setString(6, "manual");
                            insertStmt.setString(7, getDateTime());
                            insertStmt.executeUpdate();
                            System.out.println("Saved: " + report);
                        }
                    } else {
                        System.out.println("Duplicate record exists for: " + report);
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void savefundingata() {
        try (Connection con = getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM marketfundingrate WHERE dt = ? AND index = ? AND term = ?";
            String insertQuery = "INSERT INTO marketfundingrate (dt, index, term , bid ,offer, updatesource, updatetime) VALUES (?, ?, ?,?, ?, ?, ?)";

            for (fundingratedto funding : fundingData) {

                try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
                    checkStmt.setInt(1, Integer.parseInt(getDateTime1()));
                    checkStmt.setString(2, funding.getIndex());
                    checkStmt.setInt(3, funding.getTerm());

                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count == 0) {
                        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, Integer.parseInt(getDateTime1()));
                            insertStmt.setString(2, funding.getIndex());
                            insertStmt.setInt(3, funding.getTerm());
                            insertStmt.setDouble(4, funding.getBid());
                            insertStmt.setDouble(5, funding.getOffer());

                            insertStmt.setString(6, "manual");
                            insertStmt.setString(7, getDateTime());
                            insertStmt.executeUpdate();
                            System.out.println("Saved: " + funding);
                        }
                    } else {
                        System.out.println("Duplicate record exists for: " + funding);
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }




    private String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return formatter.format(new Date());
    }

    private String getDateTime1() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
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


    private void setupContextMenu1() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addMenuItem = new MenuItem("Add");
        addMenuItem.setOnAction(e -> addDividendData());
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(e -> deleteDividendData());
        contextMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        dividendTable.setContextMenu(contextMenu);
    }

    private void setupContextMenu2() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addMenuItem = new MenuItem("Add");
        addMenuItem.setOnAction(e -> addreportData());
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(e -> deletereportData());
        contextMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        RepoRate.setContextMenu(contextMenu);
    }

    private void setupContextMenu3() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addMenuItem = new MenuItem("Add");
        addMenuItem.setOnAction(e -> addfundingData());
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(e -> deletefundingData());
        contextMenu.getItems().addAll(addMenuItem, deleteMenuItem);
        FundingRate.setContextMenu(contextMenu);
    }

    private void addDivisorData() {
        divisordto newDivisor = new divisordto("", 0, 0, 0.0, "", "");

        divisorData.add(newDivisor);

        divisorTable.refresh();
        divisorTable.scrollTo(newDivisor);
        divisorTable.getSelectionModel().select(newDivisor);
    }


    private void addDividendData() {
        dividenddto newDividend = new dividenddto(Integer.parseInt(getDateTime1()), "", 0, 0.0, "", "");

        dividendData.add(newDividend);

        dividendTable.refresh();
        dividendTable.scrollTo(newDividend);
        dividendTable.getSelectionModel().select(newDividend);
    }

    private void addreportData() {
        reporatedto newreport = new reporatedto(Integer.parseInt(getDateTime1()), " ", 0, 0.0, 0.0, "", "");

        reportedData.add(newreport);

        RepoRate.refresh();
        RepoRate.scrollTo(newreport);
        RepoRate.getSelectionModel().select(newreport);
    }

    private void addfundingData() {
        fundingratedto funding = new fundingratedto(Integer.parseInt(getDateTime1()), " ", 0, 0.0, 0.0, "", "");

        fundingData.add(funding);

        FundingRate.refresh();
        FundingRate.scrollTo(funding);
        FundingRate.getSelectionModel().select(funding);
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
                try (Connection con = getConnection()) {
                    String query = "Delete from marketdivisor WHERE fromdt=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setInt(1, selectedDivisor.getFromDt());
                    stm.executeQuery();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }

        }

    }
    private void deleteDividendData () {
        divisordto selectedDividend = dividendTable.getSelectionModel().getSelectedItem();
        if (selectedDividend != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                dividendData.remove(selectedDividend);
                try (Connection con = getConnection()) {
                    String query = "Delete from marketdividend WHERE updatetime=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setString(1, selectedDividend.getUpdateTime());
                    stm.executeQuery();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No entry selected");
            alert.setContentText("Please select an entry to delete.");
            alert.showAndWait();
        }
    }

    private void deletereportData () {
        reporatedto selectedreport = RepoRate.getSelectionModel().getSelectedItem();
        if (selectedreport != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                dividendData.remove(selectedreport);
                try (Connection con = getConnection()) {
                    String query = "Delete from marketreporate WHERE updatetime=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setString(1, selectedreport.getUpdateTime());
                    stm.executeQuery();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No entry selected");
            alert.setContentText("Please select an entry to delete.");
            alert.showAndWait();
        }
    }
    private void deletefundingData () {
        fundingratedto selectedfunding = FundingRate.getSelectionModel().getSelectedItem();
        if (selectedfunding != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this entry?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                dividendData.remove(selectedfunding);
                try (Connection con = getConnection()) {
                    String query = "Delete from marketfundingrate WHERE updatetime=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setString(1, selectedfunding.getUpdateTime());
                    stm.executeQuery();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No entry selected");
            alert.setContentText("Please select an entry to delete.");
            alert.showAndWait();
        }
    }
}




