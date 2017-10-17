package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import dao.ShareRouteDao;
import model.UploadInfoStr;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.RouteService;
import util.FileUtil;
import util.ZipUtil;


public class RouteServiceImpl implements RouteService {
	
	private ShareRouteDao shareRouteDao;

	public ShareRouteDao getShareRouteDao() {
		return shareRouteDao;
	}

	public void setShareRouteDao(ShareRouteDao shareRouteDao) {
		this.shareRouteDao = shareRouteDao;
	}
	
	@Override
	public String getShareRoute(String id) {
		String folder = "C:/route/getRoute";
		String path = folder+".zip";
		File file = new File(path);
		try {
			shareRouteDao.getRoutePic(id).writeTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(ZipUtil.decompress(file)==false)
			return "failed";
		file.delete();
        File fileList[] = new File(folder).listFiles();
        String ret="failed";
        for ( int i = 0; i < fileList.length; i++ ) {
            String filename = fileList[i].getName();
            if (filename.contains(".jpg")) {
//                photos.add(traceidPath + "/" + filename);
            } else if (filename.contains("p_")) {
//                infos.add(traceidPath + "/" + filename);
            } else if (filename.contains("info_")) {
//                ti.setInfoFileName(traceidPath + "/" + filename);
            } else {
//                ti.setFileName(traceidPath + "/" + filename);
            	ret = FileUtil.fileToJson(folder+"/"+filename);
            }
        }
		return ret;
	}

	@Override
	public void addShareRoute(String id, String routedetail) {
		shareRouteDao.saveRoute(id, routedetail);
	}
	
	@Override
	public File getShareRoutePic(String id) {
		File file = new File("C:/getpic.zip");
		try {
			shareRouteDao.getRoutePic(id).writeTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	public void addShareRoutePic(String id, File pic) {
		shareRouteDao.saveRoutePic(id, pic);
	}
	
	@Override
	public String addTrace(UploadInfoStr str) {
		shareRouteDao.saveTrace(str);
		return "success";
	}
	
	@Override
	public String getTrace(String sid) {
		return shareRouteDao.getTrace(sid).getPolylines();
	}

}
