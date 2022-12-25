package sig.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import sig.view.invoiceFrame;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import sig.view.InvoiceHeaderDialog;
import sig.view.InvoiceLineDialog;
import sig.model.invoiceHeader;
import sig.model.HeadersTable;
import sig.model.invoiceLine;
import sig.model.LinesTable;





 public class FileOperations implements ActionListener{
         private invoiceFrame _Myframe;
         private InvoiceHeaderDialog _Allheaders;
         private InvoiceLineDialog _AllLines;

    public FileOperations(invoiceFrame frame) {
         
        this._Myframe = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        
        switch(e.getActionCommand()){
            
      
            
            case "Create New Invoice" : 
            
                CreateNewInvoice();
            break;
    
          case "Delete Invoice" : 
         
            DeleteInvoice();
              break;
    
              case "Add Item" : 
         
                 SaveChanges();
              break;
    
                 case "Delete Item" : 
           
                    cancel();
                  break;
             case "newInvoiceSave":
                newInvoiceDialogOK();
                break;

            case "newInvoiceCancel":
                newInvoiceDialogCancel();
                break;

            case "newLineCancel":
                newLineDialogCancel();
                break;

            case "newLineSave":
                newLineDialogOK();
                break;
    
              case "load file" : 
          
                     loadfile();
                  break;
    
                 case "save file" : 
                  System.out.println("save file");
                     savefile();
                    break;

       
}
    }
 
 
    String str1;
    String str2;
    String str3;
    String str4;
    
      private void loadfile() {
     JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(_Myframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                if(!headerFile.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(_Myframe, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                   
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<invoiceHeader> Headers = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                   try{
                       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                
                         Date invoiceDate = dateFormat.parse(str2);     
                         
                         invoiceHeader header = new invoiceHeader(code, str3, invoiceDate);
                         Headers.add(header);
                   }
                   catch(Exception e ){
                       System.out.println(e);
                            
                        JOptionPane.showMessageDialog(_Myframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                   }
                   
                  
                }
                _Myframe.setInvoicesArray(Headers);



               result = fileChooser.showOpenDialog(_Myframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    
                    if(!headerFile.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(_Myframe, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                    
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<invoiceLine> invoiceLines = new ArrayList<>();
                    for (String line : lineLines) {
                        String[] l = line.split(",");
                        String s1 = l[0];    // invoice num (int)
                        String s2 = l[1];    // item name   (String)
                        String s3 = l[2];    // price       (double)
                        String s4 = l[3];    // count       (int)
                        int invCode = Integer.parseInt(s1);
                        double price = Double.parseDouble(s3);
                        int count = Integer.parseInt(s4);
                        invoiceHeader inv = _Myframe.getInvObject(invCode);
                        invoiceLine invoiceLine = new invoiceLine(s2, price, count, inv);
                        inv.getLines().add(invoiceLine);
                    }
                }
                HeadersTable headerTable = new HeadersTable(Headers);
                _Myframe.setInvoiceheaderTable(headerTable);
                _Myframe.getheaderTable().setModel(headerTable);
                System.out.println("files read");
            }



       }
        
      
      
        catch (Exception ex) {
            JOptionPane.showMessageDialog(_Myframe, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CreateNewInvoice() {
        _Allheaders = new InvoiceHeaderDialog(_Myframe);
        _Allheaders.setVisible(true);
    }
        

    private void DeleteInvoice() {
        int selectedInvoiceIndex = _Myframe.getheaderTable().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            _Myframe.getInvoicesArray().remove(selectedInvoiceIndex);
            _Myframe.getInvoiceheaderTable().fireTableDataChanged();

            _Myframe.getlineTable().setModel(new LinesTable(null));
            _Myframe.setLinesArray(null);
            _Myframe.getCustNameLbl().setText("");
            _Myframe.getInvNumLbl().setText("");
            _Myframe.getInvTotalIbl().setText("");
            _Myframe.getDateLbl().setText("");
        }
    }

    private void SaveChanges() {
        _AllLines= new InvoiceLineDialog(_Myframe);
        _AllLines.setVisible(true);
    }

    private void cancel() {
        int selectedLineIndex = _Myframe.getlineTable().getSelectedRow();
        int selectedInvoiceIndex = _Myframe.getheaderTable().getSelectedRow();
        if (selectedLineIndex != -1) {
            _Myframe.getLinesArray().remove(selectedLineIndex);
            LinesTable lineTableModel = (LinesTable) _Myframe.getlineTable().getModel();
            lineTableModel.fireTableDataChanged();
            _Myframe.getInvTotalIbl().setText("" + _Myframe.getInvoicesArray().get(selectedInvoiceIndex).getItemTotal());
            _Myframe.getInvoiceheaderTable().fireTableDataChanged();
            _Myframe.getheaderTable().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }

    private void savefile() {
        ArrayList<invoiceHeader> invoicesArray = _Myframe.getInvoicesArray();
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showSaveDialog(_Myframe);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                if(!headerFile.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(_Myframe, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                   
                FileWriter hfw = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (invoiceHeader invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (invoiceLine line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fc.showSaveDialog(_Myframe);
                File lineFile = fc.getSelectedFile();
                 if(!lineFile.getAbsolutePath().endsWith(".csv")){
                     JOptionPane.showMessageDialog(_Myframe, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_Myframe, "Folder/File path is not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void  newLineDialogCancel() {
        _AllLines.setVisible(false);
        _AllLines.dispose();
        _AllLines = null;
    }
  

    private void newInvoiceDialogOK() {
        _Allheaders.setVisible(false);

        String custName = _Allheaders.getCustNameField().getText();
        String str = _Allheaders.getDateField().getText();
        Date d = new Date();
        
      
        try {
              
             d = invoiceFrame.dateFormat.parse(str);
             System.out.println(custName);
              System.out.println(str);
        } 
        catch (ParseException ex) {
            JOptionPane.showMessageDialog(_Myframe, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int invNum = 0;
        for (invoiceHeader inv : _Myframe.getInvoicesArray()) {
            if (inv.getNum() > invNum) {
                invNum = inv.getNum();
            }
        }
        invNum++;
        invoiceHeader newInv = new invoiceHeader(invNum, custName, d);
        _Myframe.getInvoicesArray().add(newInv);
        _Myframe.getInvoiceheaderTable().fireTableDataChanged();
        _Allheaders.dispose();
        _Allheaders = null;
    }

    private void newInvoiceDialogCancel() {
        _Allheaders.setVisible(false);
        _Allheaders.dispose();
        _Allheaders = null;
    }

    private void newLineDialogOK() {
        _AllLines.setVisible(false);

        String name = _AllLines.getItemNameField().getText();
        String str1 = _AllLines.getItemCountField().getText();
        String str2 = _AllLines.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(_Myframe, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(str2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(_Myframe, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = _Myframe.getheaderTable().getSelectedRow();
                 if (selectedInvHeader != -1) {
            invoiceHeader invHeader = _Myframe.getInvoicesArray().get(selectedInvHeader);
            invoiceLine line = new invoiceLine(name, price, count, invHeader);
            _Myframe.getLinesArray().add(line);
            LinesTable lineTable = (LinesTable) _Myframe.getlineTable().getModel();
            lineTable.fireTableDataChanged();
            _Myframe.getInvoiceheaderTable().fireTableDataChanged();
        }
        _Myframe.getheaderTable().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        _AllLines.dispose();
        _AllLines = null;
    }
}