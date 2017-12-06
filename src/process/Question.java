package process;

import java.util.ArrayList;
import java.util.List;

public class Question {
	int Id;
	int originalId;	
	String Title;
	String CreationDate;
	int AcceptedAnswerId;
	int AnswerCount;
	List<Answer> Anslist=new ArrayList<>();
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public void setAcceptedAnswerId(int acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}
	public void setAnswerCount(int answerCount) {
		AnswerCount = answerCount;
	}
	public List<Answer> getAnslist() {
		return Anslist;
	}
	public void setAnslist(List<Answer> anslist) {
		this.Anslist = anslist;
	}
	public int getId() {
		return Id;
	}
	public int getOriginalId() {
		return originalId;
	}
	public String getTitle() {
		return Title;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public int getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}
	public int getAnswerCount() {
		return AnswerCount;
	}
	
	/*
	int PostTypeId;
	String[] Tags; 	
	int Score;
	int ViewCount;
	String Body;
	int OwnerUserId;
	String LastActivityDate;
	int CommentCount; 
	int FavoriteCount;
	*/
	  
}
