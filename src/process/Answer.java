package process;

public class Answer {
	int Id;
	int ParentId;
	int originalId;	
	String CreationDate;
	int Score;
	String Body;
	int OwnerUserId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public int getOwnerUserId() {
		return OwnerUserId;
	}
	public void setOwnerUserId(int ownerUserId) {
		OwnerUserId = ownerUserId;
	}
	
	
	/*int LastEditorUserId;
	String LastEditDate;
	String LastActivityDate;
	int CommentCount;*/
	
}
