package meserreurs;

public class MonException  extends Exception  implements java.io.Serializable {
	static final long serialVersionUID = 42L;
    private String unmessage;
    private String untype;

    public MonException() {
    }

    public MonException( String libelle,  String type) {
        this.unmessage = libelle;
        this.untype = type;
    }

    public MonException( String libelle) {
        this.unmessage = libelle;
       
    }

    public String getUnmessage() {
        return unmessage;
    }

    public void setUnmessage(java.lang.String libelle) {
        this.unmessage = libelle;
    }

    public String getunType() {
        return untype;
    }

    public void setUntype(String type) {
        this.untype = type;
    }
    
}
