
package sig.model;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
        public class invoiceHeader {
                private int _Thenum;
                private String _customer;
                private Date d;
                private ArrayList<invoiceLine> _Alllines;
                private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

public  invoiceHeader() {

}

    public invoiceHeader(int num, String customer, Date date) {
        this._Thenum = num;
        this._customer = customer;
        this.d = date;
        }
    public int getNum() {
        return _Thenum;
    }

    public void setNum(int num) {
        this._Thenum = num;
    }

    public String getCustomer() {
        return _customer;
    }

    public void setCustomer(String customer) {
        this._customer = customer;
    }

    public Date getDate() {
        return d;
    }

    public void setDate(Date date) {
        this.d = date;
    }
    
  public ArrayList<invoiceLine> getLines() {
        if (_Alllines == null) {
            _Alllines = new ArrayList<>();
        }
        return _Alllines;
    }

    public void setLines(ArrayList<invoiceLine> lines) {
        this._Alllines = lines;
    }
    
    public double getItemTotal(){
        double total = 0.0;
        
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getLineTotal();
        }
        
        return total;
    }
    @Override
    public String toString() {
        return  + _Thenum + ","+ df.format(d) + ", "+ _customer ;
    }
        }

  


    
