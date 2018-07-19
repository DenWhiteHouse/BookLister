
package com.example.denny.booklister.BookData;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice_ implements Parcelable
{

    @SerializedName("amountInMicros")
    @Expose
    private Double amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    public final static Creator<RetailPrice_> CREATOR = new Creator<RetailPrice_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RetailPrice_ createFromParcel(Parcel in) {
            return new RetailPrice_(in);
        }

        public RetailPrice_[] newArray(int size) {
            return (new RetailPrice_[size]);
        }

    }
    ;

    protected RetailPrice_(Parcel in) {
        this.amountInMicros = ((Double) in.readValue((Double.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RetailPrice_() {
    }

    public Double getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Double amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amountInMicros);
        dest.writeValue(currencyCode);
    }

    public int describeContents() {
        return  0;
    }

}
