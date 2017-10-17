package model;

public class Comment {

	private int cid;
	private String sid;
	private Integer uida;
    private Integer uidb;
    private String unamea;
    private String unameb;
    private String comment;


	public Comment() {
	}

	public Comment(int cid, String sid, Integer uida, String unamea, String comment) {
		this.cid = cid;
		this.sid = sid;
		this.uida = uida;
		this.unamea = unamea;
		this.comment = comment;
	}
	
	public Comment(int cid, String sid, Integer uida, String unamea, 
			Integer uidb, String unameb, String comment) {
		this.cid = cid;
		this.sid = sid;
		this.uida = uida;
		this.unamea = unamea;
		this.uidb = uidb;
		this.unameb = unameb;
		this.comment = comment;
	}
	

	public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getUida() {
        return uida;
    }

    public void setUida(Integer uida) {
        this.uida = uida;
    }

    public Integer getUidb() {
        return uidb;
    }

    public void setUidb(Integer uidb) {
        this.uidb = uidb;
    }

    public String getUnamea() {
        return unamea;
    }

    public void setUnamea(String unamea) {
        this.unamea = unamea;
    }

    public String getUnameb() {
        return unameb;
    }

    public void setUnameb(String unameb) {
        this.unameb = unameb;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


	
}