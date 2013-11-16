package model.beans;

/**
 *
 * @author skuarch
 */
public class ValidationResponse {

    private boolean isAlive = false;
    private int responseCode = -1;
    
    //==========================================================================
    public ValidationResponse() {
    } // end ValidationResponse

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "isAlive=" + isAlive + " responseCode=" + responseCode;
    }
    
    
    
} // end class