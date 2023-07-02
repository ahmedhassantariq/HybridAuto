package Utils;

import Entities.Customer;
import Entities.Services;
import Functionality.Forms.OrdersController;
import Screens.CheckOutForm;
import com.dlsc.pdfviewfx.PDFView;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class PDFDocument {
    private PdfWriter pdfWriter;
    private PdfDocument pdfDocument;
    private static PDFView pdfDisplayer;
    private LocalTime localTime;
    private LocalDate localDate;
    private PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
    private static String destination;
    private static String fileName;
    public PDFDocument(Customer customer, int orderID) throws IOException, SQLException {
        localTime = LocalTime.now();
        localDate = LocalDate.now();
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString().substring(0,8)+".pdf";
        destination = "C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Receipts\\"+orderID+".pdf";
        pdfWriter = new PdfWriter(destination);
        pdfDocument = new PdfDocument(pdfWriter);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);



        float col = 280f;
        float columnWidth[] = {col,col};
        Table table = new Table(columnWidth);
        table.addCell(new Cell()
                .add("Hybrid Master")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(10f)
                .setFontSize(38f)
                .setFont(font)
                .setBorder(Border.NO_BORDER));
        table.addCell(new Cell()
                .add("INVOICE")
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(10f)
                .setMarginRight(10f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER));
        table.addCell(new Cell()
                .add("[Street Address]\n [City, Zip Code]\nPhone no.: +92 304 4845231")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(12f)
                .setBorder(Border.NO_BORDER));


        float colWidth[] = {80, 300, 100, 80};
        Table customerInfoTable = new Table(colWidth);
        customerInfoTable.addCell(new Cell(0,4)
                .add("Customer Information")
                .setBold()
                .setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Name: ").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(customer.getFullName()).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Invoice #").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(""+orderID).setBorder(Border.NO_BORDER));

        customerInfoTable.addCell(new Cell().add("Phone No.: ").setBorder(Border.NO_BORDER).setMarginBottom(20f));
        customerInfoTable.addCell(new Cell().add(customer.getPhone()).setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add("Date:").setBorder(Border.NO_BORDER));
        customerInfoTable.addCell(new Cell().add(localDate.toString()).setBorder(Border.NO_BORDER));


        float itemInfoColWidth[] = {280,50,100,100};
        Table itemInfoTable = new Table(itemInfoColWidth);
        itemInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        itemInfoTable.addCell(new Cell().add("Description").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.LEFT));
        itemInfoTable.addCell(new Cell().add("QTY").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER));
        itemInfoTable.addCell(new Cell().add("Unit Price").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add("Amount").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT));


        //Information
        double totalAmount = 0;


        for(int i = 0; i< OrdersController.orderList.size(); i++) {

            itemInfoTable.addCell(new Cell().add(
                    OrdersController.orderList.get(i).getMake()+" "+
                    OrdersController.orderList.get(i).getModel()+" "+
                    OrdersController.orderList.get(i).getYear()+" "+
                    OrdersController.orderList.get(i).getProductCategory()+" "+
                    OrdersController.orderList.get(i).getSerialNumber()
            ).setTextAlignment(TextAlignment.LEFT));

            itemInfoTable.addCell(new Cell().add("1").setTextAlignment(TextAlignment.CENTER));
            itemInfoTable.addCell(new Cell().add(""+OrdersController.orderList.get(i).getCost()).setTextAlignment(TextAlignment.RIGHT));
            itemInfoTable.addCell(new Cell().add(""+OrdersController.orderList.get(i).getCost()).setTextAlignment(TextAlignment.RIGHT));
            totalAmount +=  Double.parseDouble(OrdersController.orderList.get(i).getCost());
        }


        //Subtotal
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("Subtotal")
                .setBackgroundColor(Color.GRAY)
                .setFontColor(Color.BLACK)
                .setTextAlignment(TextAlignment.RIGHT)
                );
        itemInfoTable.addCell(new Cell().add(totalAmount+"")
                .setBackgroundColor(Color.GRAY)
                .setFontColor(Color.BLACK)
                .setTextAlignment(TextAlignment.RIGHT));

        //Discount
        if(true) {
            itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemInfoTable.addCell(new Cell().add("Discount")
                    .setBackgroundColor(Color.GRAY)
                    .setFontColor(Color.BLACK)
                    .setTextAlignment(TextAlignment.RIGHT)
            );
            itemInfoTable.addCell(new Cell().add(Formatter.decimalFormat().format(CheckOutForm.totalDiscount)+"%")
                    .setBackgroundColor(Color.GRAY)
                    .setFontColor(Color.BLACK)
                    .setTextAlignment(TextAlignment.RIGHT));
        }
        //Total
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("").setBackgroundColor(Color.WHITE).setBorder(Border.NO_BORDER));
        itemInfoTable.addCell(new Cell().add("Total")
                .setBackgroundColor(Color.GRAY)
                .setFontColor(Color.BLACK)
                .setTextAlignment(TextAlignment.RIGHT)
        );
        itemInfoTable.addCell(new Cell().add(String.valueOf(CheckOutForm.totalAmount))
                .setBackgroundColor(Color.GRAY)
                .setFontColor(Color.BLACK)
                .setTextAlignment(TextAlignment.RIGHT));






        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfoTable);
        document.add(itemInfoTable);
        document.add(new Paragraph("Authority Signature: ")
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(25f)
                .setMarginRight(90f));
        document.close();
    }

    public static void show() {
        if(destination==null)
            return;
        //New Invoice
        Stage stage = new Stage();
        File file = new File(destination);
        pdfDisplayer = new PDFView();
        pdfDisplayer.setShowAll(false);
        pdfDisplayer.setShowToolBar(false);
        pdfDisplayer.setShowThumbnails(false);
        pdfDisplayer.setShowSearchResults(false);
        pdfDisplayer.load(file);
        stage.setTitle(destination.substring(21));
        stage.setScene(new Scene(pdfDisplayer,500,700));
        stage.show();

    }

    public static void show(String fileName) {
        //New Invoice
        Stage stage = new Stage();
        File file = new File("C:\\Users\\atari\\Documents\\GitHub\\HybridAuto\\HybridAuto\\src\\main\\java\\Receipts\\"+fileName);
        pdfDisplayer = new PDFView();
        pdfDisplayer.setShowAll(false);
        pdfDisplayer.setShowToolBar(false);
        pdfDisplayer.setShowThumbnails(false);
        pdfDisplayer.setShowSearchResults(false);
        if(file.exists()) {
            pdfDisplayer.load(file);
            stage.setTitle(fileName);
            stage.setScene(new Scene(pdfDisplayer, 500, 700));
            stage.show();
        }else {
            new Notification("Receipt Not Found");
        }
    }

}
