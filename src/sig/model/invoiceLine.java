
package sig.model;

public class invoiceLine {
    private String _theItem;
    private double _Theprice;
    private int _Thecount;
    private invoiceHeader _header;

    public invoiceLine() {
    }

    public invoiceLine(String item, double price, int count, invoiceHeader header) {
        this._theItem = item;
        this._Theprice = price;
        this._Thecount = count;
        this._header = header;
    }

    public invoiceHeader getHeader() {
        return _header;
    }

    public void setHeader(invoiceHeader header) {
        this._header = header;
    }

    public String getItem() {
        return _theItem;
    }

    public void setItem(String item) {
        this._theItem = item;
    }

    public double getPrice() {
        return _Theprice;
    }

    public void setPrice(double price) {
        this._Theprice = price;
    }

    public int getCount() {
        return _Thecount;
    }

    public void setCount(int count) {
        this._Thecount = count;
    }
    
    public double getLineTotal() {
        return _Theprice * _Thecount;
    }

    @Override
    public String toString() {
        return _header.getNum()+","+ _theItem + "," + _Theprice + "," + _Thecount;
    }

    
    
}
