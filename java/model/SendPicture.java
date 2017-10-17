package model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class SendPicture {
    private String sid;
    private int idx;
    private byte[] pic;

    public  SendPicture(){
    }
    
    public SendPicture(String sid, int idx, byte[] pic){
        this.sid = sid;
        this.idx = idx;
        this.pic = pic;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic) { this.pic = pic; }

}
