package model;

public class Photo {
    public String photoid;
    public String traceid;
    public double lat;
    public double lon;
    public String time;
    public String filename;
    public String path;
    public String geo;

    public Photo() {
        super();
    }

    public Photo(String photoid, String traceid, double lat, double lon, String time, String filename, String path, String geo) {
        super();
        this.photoid = photoid;
        this.traceid = traceid;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.filename = filename;
        this.path = path;
        this.geo = geo;
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public String getTraceid() {
        return traceid;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
