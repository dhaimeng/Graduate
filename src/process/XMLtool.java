package process;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import net.sf.json.JSONObject;


public class XMLtool {
	Map<Integer,Question> qlist=new HashMap<Integer,Question>();
	List<Answer> alist=new ArrayList<>();
	public void setQAlist(String rpath) {		//读取解析Post生成Qlist和Alist两个对象列表
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			int q_count = 0, a_count = 0;
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId = GetElementAttributeInt(allChildren, i, "PostTypeId");
				int Id = GetElementAttributeInt(allChildren, i, "Id");
				if (PostTypeId == 1) { // 问题

					Question q = new Question();
					String title = GetElementAttribute(allChildren, i, "Title");
					// System.out.println(title);
					// System.out.println(Clear.Clear(title));
					int answerCount = GetElementAttributeInt(allChildren, i, "AnswerCount");
					int acceptedAnswerId = GetElementAttributeInt(allChildren, i, "AcceptedAnswerId");
					String creationDate = GetElementAttribute(allChildren, i, "CreationDate");
					q.setId(q_count++);
					q.setOriginalId(Id);
					q.setTitle(title);
					q.setAnswerCount(answerCount);
					q.setCreationDate(creationDate);
					q.setAcceptedAnswerId(acceptedAnswerId);

					qlist.put(Id, q); // 将所有问题对象按照原始Id存入qlist
					
				} else if (PostTypeId == 2) { // 答案
					Answer a = new Answer();
					a.setId(a_count++);
					a.setOriginalId(Id);
					int parentId = GetElementAttributeInt(allChildren, i, "ParentId");
					String body = GetElementAttribute(allChildren, i, "Body");
					body = Clear.Clear(body);
					int score = GetElementAttributeInt(allChildren, i, "Score");
					int ownerUserId = GetElementAttributeInt(allChildren, i, "OwnerUserId");
					String creationDate = GetElementAttribute(allChildren, i, "CreationDate");
					a.setParentId(parentId);
					a.setBody(body);
					a.setScore(score);
					a.setOwnerUserId(ownerUserId);
					a.setCreationDate(creationDate);					
					alist.add(a);
				} else { // 未注明的类型
					// System.out.println(Id+" "+PostTypeId);
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, Question> getQlist() {
		return qlist;
	}
	public List<Answer> getAlist() {
		return alist;
	}
	String GetElementAttribute(List allChildren,int i,String attribute){
		try{
			String str=((Element)allChildren.get(i)).getAttributeValue(attribute);	
			return str;
		}catch (Exception e) {
			return "";
		}	
	}
	int GetElementAttributeInt(List allChildren,int i,String attribute){
		try{
			int num=Integer.parseInt(((Element)allChildren.get(i)).getAttributeValue(attribute));	
			return num;
		}catch (Exception e) {
			return -1;
		}
	}
	
	void ReadQAtext(String rpath,File f) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			int q_count = 0, a_count = 0;
			for (int i = 0; i < allChildren.size(); i++) {
				int PostTypeId = GetElementAttributeInt(allChildren, i, "PostTypeId");
				int Id = GetElementAttributeInt(allChildren, i, "Id");
				if (PostTypeId == 1) { // 问题
					String title = GetElementAttribute(allChildren, i, "Title");		
					FileUtils.write(f,title+"\n",true);
				} else{
					continue;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void WriteJson(Map<Integer,Question> qlist,String wpath){
		File f = new File(wpath);
		if (f.exists()) {
			f.delete();
		}
		Object[] key = qlist.keySet().toArray();
		Arrays.sort(key);
		int qnum=1;
		for (int i = 0; i < key.length; i++)
		{
			JSONObject json = Object2json(qlist.get(key[i]));
			try {
				FileUtils.write(f,json.toString()+"\n",true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	void ReadXML(String rpath,int type_flag){		
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(rpath));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			/*POSTs类型*/
			if(type_flag==1){
				int q_count=0,a_count=0;
//				for (int i = 0; i < allChildren.size(); i++) {
					for (int i = 0; i < 10; i++) {
					int PostTypeId=GetElementAttributeInt(allChildren,i,"PostTypeId");
					int Id=GetElementAttributeInt(allChildren,i,"Id");				
					if(PostTypeId==1){		//问题	
						
						Question q = new Question();
						String title=GetElementAttribute(allChildren,i,"Title");
						//System.out.println(title);
						//System.out.println(Clear.Clear(title));						
					    int answerCount=GetElementAttributeInt(allChildren,i,"AnswerCount");				
					    int acceptedAnswerId=GetElementAttributeInt(allChildren,i,"AcceptedAnswerId");				
					    String creationDate=GetElementAttribute(allChildren,i,"CreationDate");								
						q.setId(q_count++);
						q.setOriginalId(Id);
					    q.setTitle(title); 
						q.setAnswerCount(answerCount);
						q.setCreationDate(creationDate);
						q.setAcceptedAnswerId(acceptedAnswerId);
						
						qlist.put(Id, q);		//将所有问题对象按照原始Id存入qlist
						//JSONObject json = JSONObject.fromObject(q);
//						JSONObject json = Object2json(q);
						//System.out.println(json.toString());
//						FileUtils.write(f,json.toString()+"\n",true);
						//System.out.println(Id+" "+answerCount+" "+creationDate);
					}	
					else if(PostTypeId==2){		//答案
						Answer a = new Answer();
						a.setId(a_count++);
						a.setOriginalId(Id);		
					    int parentId=GetElementAttributeInt(allChildren,i,"ParentId");				
						String body=GetElementAttribute(allChildren,i,"Body");
						body=Clear.Clear(body);
					    int score=GetElementAttributeInt(allChildren,i,"Score");				
					    int ownerUserId=GetElementAttributeInt(allChildren,i,"OwnerUserId");				
					    String creationDate=GetElementAttribute(allChildren,i,"CreationDate");								
						a.setParentId(parentId); 
						a.setBody(body);
						a.setScore(score);
						a.setOwnerUserId(ownerUserId);
						
						alist.add(a);
						
//						JSONObject json = Object2json(a);
						//System.out.println(json.toString());
					}	
					else{		//未注明的类型
//						System.out.println(Id+" "+PostTypeId);
						continue;
					}
				}			
			}
			/*USER类型*/
			else {
				User u = new User();
				int Id;
				int Reputation;
				int Views;
				int UpVotes;
				int DownVotes;
				 
				JSONObject json = JSONObject.fromObject(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
JSONObject Object2json(Question q) {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Id",q.getId());
		jsonObj.put("originalId",q.getOriginalId());	
		jsonObj.put("Title",q.getTitle());
		jsonObj.put("CreationDate",q.getCreationDate());
		jsonObj.put("AcceptedAnswerId",q.getAcceptedAnswerId());
		jsonObj.put("AnswerCount",q.getAnswerCount());
		jsonObj.put("Anslist",q.getAnslist());
//		System.out.println(jsonObj);
		return jsonObj;
	}
	JSONObject Object2json(Answer a) {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Id",a.getId());
		jsonObj.put("CreationDate",a.getCreationDate());
		jsonObj.put("OwnerUserId",a.getOwnerUserId());
		jsonObj.put("Score",a.getScore());
		jsonObj.put("Body",a.getBody());
		jsonObj.put("originalId",a.getOriginalId());	
		jsonObj.put("ParentId",a.getParentId());	
//		System.out.println(jsonObj);
		return jsonObj;
	}
}
