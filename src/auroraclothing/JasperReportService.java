/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auroraclothing;

import java.io.InputStream;
import java.sql.Connection;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportService {

    public static void showSalesReport() {

        Connection conn = null;

        try {

            // ================================================
            // DATABASE CONNECTION
            // ================================================
            conn = DatabaseConnection.getConnection();


            // ================================================
            // LOAD PRE-COMPILED JASPER REPORT
            // ================================================
            InputStream reportStream =
                    JasperReportService.class.getResourceAsStream(
                            "/auroraclothing/reports/AuroraSalesReport.jasper"
                    );


            if (reportStream == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "AuroraSalesReport.jasper could not be found.",
                        "AURORA Reports",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // ================================================
            // LOAD .JASPER FILE
            // No runtime JRXML compilation
            // ================================================
            JasperReport report =
                    (JasperReport) JRLoader.loadObject(
                            reportStream
                    );


            // ================================================
            // FILL REPORT USING MYSQL CONNECTION
            // ================================================
            JasperPrint print =
                    JasperFillManager.fillReport(
                            report,
                            null,
                            conn
                    );


            // ================================================
            // CHECK REPORT DATA
            // ================================================
            if (print.getPages().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "There is no sales data available for the report.",
                        "AURORA Reports",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            // ================================================
            // OPEN JASPER VIEWER
            // ================================================
            JasperViewer viewer =
                    new JasperViewer(
                            print,
                            false
                    );


            viewer.setTitle(
                    "AURORA | Sales & Business Performance Report"
            );


            viewer.setVisible(true);


        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Unable to generate Sales Report.\n\n"
                    + e.getMessage(),
                    "AURORA Reports",
                    JOptionPane.ERROR_MESSAGE
            );


        } finally {

            if (conn != null) {

                try {

                    conn.close();

                } catch (Exception ignored) {

                }
            }
        }
    }
}