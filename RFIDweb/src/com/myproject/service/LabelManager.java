package com.myproject.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.myproject.DAO.LabelDAO;
import com.myproject.DAOimpl.LabelDAOImpl;
import com.myproject.Model.Factory;
import com.myproject.Model.Store;
import com.myproject.Model.Tags;
import com.myproject.Model.Transport;
import com.myproject.serverAcess.ServerAccess;
import com.myproject.serverAcess.impl.ServerAccessImpl;


public class LabelManager {
	public LabelManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JSONArray sendToWeb(String table){
		JSONArray jArray = new JSONArray();
		LabelDAO db = new LabelDAOImpl();
		try{
			switch (table) {
			case "factory":
				List fac= db.queryAll("factory").list();
				for(int i=0;i<fac.size();i++){
					JSONObject jso= new JSONObject();
					jso.put("tagid", ((Factory) fac.get(i)).getTags().getTagsid());
					jso.put("factoryname", ((Factory) fac.get(i)).getFactoryname());
					jso.put("leavestatus", ((Factory) fac.get(i)).getLeaveFactoryStatus());
					jso.put("outputtime", ((Factory) fac.get(i)).getOutputtime());
					jso.put("line1status", ((Factory) fac.get(i)).getProductLine1Status());
					jso.put("line2status", ((Factory) fac.get(i)).getProductLine2Stauts());
					jArray.put(i, jso);
				}
				break;
			case "store":
				List store= db.queryAll("store").list();
				for(int i=0;i<store.size();i++){
					JSONObject jso= new JSONObject();
					jso.put("tagid", ((Store) store.get(i)).getTags().getTagsid());
					jso.put("storename", ((Store) store.get(i)).getStorename());
					jso.put("stocktime", ((Store) store.get(i)).getStocktime());
					jso.put("soldtime", ((Store) store.get(i)).getSoldtime());
					jso.put("storestatus", ((Store) store.get(i)).getStorestatus());
					jArray.put(i, jso);
				}
				break;
			case "tag":
				List tag= db.queryAll("tag").list();
				for(int i=0;i<tag.size();i++){
					JSONObject jso= new JSONObject();
					jso.put("tagid", ((Tags) tag.get(i)).getTagsid());
					jso.put("medicine", ((Tags) tag.get(i)).getMedicinename());
					jArray.put(i, jso);
				}
				break;
			case "transport":
				List trans= db.queryAll("transport").list();
				for(int i=0;i<trans.size();i++){
					JSONObject jso= new JSONObject();
					jso.put("tagid", ((Transport) trans.get(i)).getTags().getTagsid());
					jso.put("transportname", ((Transport) trans.get(i)).getTansportname());
					jso.put("starttime", ((Transport) trans.get(i)).getStarttime());
					jso.put("endtime", ((Transport) trans.get(i)).getEndtime());
					jso.put("transportstatus", ((Transport) trans.get(i)).getTransportstatus());
					jArray.put(i, jso);
				}
				break;
			default:
				break;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jArray;
	}
	public JSONObject sendToAPP(String table,int tagid){
		JSONObject jso = new JSONObject();
		try{
			LabelDAO db = new LabelDAOImpl();
			switch (table) {
			case "factory":
				List fac= db.queryfromdb("factory",tagid).list();
				for(int i=0;i<fac.size();i++){
					jso.put("tagid", ((Factory) fac.get(i)).getTags().getTagsid());
					jso.put("factoryname", ((Factory) fac.get(i)).getFactoryname());
					jso.put("leavestatus", ((Factory) fac.get(i)).getLeaveFactoryStatus());
					jso.put("outputtime", ((Factory) fac.get(i)).getOutputtime());
					jso.put("line1status", ((Factory) fac.get(i)).getProductLine1Status());
					jso.put("line2status", ((Factory) fac.get(i)).getProductLine2Stauts());
				}
				break;
			case "store":
				List store= db.queryfromdb("store",tagid).list();
				for(int i=0;i<store.size();i++){
					jso.put("tagid", ((Store) store.get(i)).getTags().getTagsid());
					jso.put("storename", ((Store) store.get(i)).getStorename());
					jso.put("stocktime", ((Store) store.get(i)).getStocktime());
					jso.put("soldtime", ((Store) store.get(i)).getSoldtime());
					jso.put("storestatus", ((Store) store.get(i)).getStorestatus());
				}
				break;
			case "tag":
				List tag= db.queryfromdb("tag",tagid).list();
				for(int i=0;i<tag.size();i++){
					jso.put("tagid", ((Tags) tag.get(i)).getTagsid());
					jso.put("medicine", ((Tags) tag.get(i)).getMedicinename());
				}
				break;
			case "transport":
				List trans= db.queryfromdb("transport",tagid).list();
				for(int i=0;i<trans.size();i++){
					jso.put("tagid", ((Transport) trans.get(i)).getTags().getTagsid());
					jso.put("transportname", ((Transport) trans.get(i)).getTansportname());
					jso.put("starttime", ((Transport) trans.get(i)).getStarttime());
					jso.put("endtime", ((Transport) trans.get(i)).getEndtime());
					jso.put("transportstatus", ((Transport) trans.get(i)).getTransportstatus());
				}
				break;
			default:
				break;
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return jso;
	}
	
	
	public JSONArray dopost(String table){
		ServerAccess serv = new ServerAccessImpl();
		try {
			String s = serv.dopost(table);
//			JSONObject js = new JSONObject();
			JSONArray ja = new JSONArray(s);
			return ja;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
