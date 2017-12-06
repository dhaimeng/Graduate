package process;
/**
 * 
 * 生成json文件、
 * 从post文件抽取问题文本，答案文本组成训练语料库，生成词向量
 * 绘制问题答案服从长尾分布图像
 */
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="D:\\成果\\毕业设计\\实验\\coffee\\QAUdata\\";
		String rpath=path+"Posts.xml";		
		String wpath=path+"QAjson.json";
		XMLtool xt=new XMLtool();
		xt.setQAlist(rpath);
		Map<Integer,Question> qlist=xt.getQlist();
		List<Answer> alist=xt.getAlist();
		for(Answer a:alist){
			int parentId =a.getParentId();
			Question q=qlist.get(parentId);
			List<Answer> Anslist=q.getAnslist();
			Anslist.add(a);
		}
		//输出json文件
		xt.WriteJson(qlist,wpath);
		
		//用来画问题下答案数量长尾分布图
		/*Object[] key = qlist.keySet().toArray();
		Arrays.sort(key);
		int num0=0;
		for (int i = 0; i < key.length; i++){
			Question q=qlist.get(key[i]);
			if(q.getAnswerCount()<2){				
				num0++;
			}
		}
		System.out.println(num0);*/
	
		//问题文本抽取与保存
		/*		
		String QuesTextpath=path+"QuesText.txt";
		File f1 = new File(QuesTextpath);
		if (f1.exists()) {
			f1.delete();
		}
		xt.ReadQAtext(rpath, f1);
		*/
		
	
	}
	public static void testScore(Map<Integer,Question> qlist){
		Object[] key = qlist.keySet().toArray();
		Arrays.sort(key);
		int num0=0;
		for (int i = 0; i < key.length; i++){
			Question q=qlist.get(key[i]);
			int AcceptedAnswerId=q.getAcceptedAnswerId();
			List<Answer> Anslist=q.getAnslist();
			int maxScore=0;
			int bigAns=-1;
			for(Answer a:Anslist){
				if(a.getScore()>maxScore){
					maxScore=a.getScore();
					bigAns=a.getId();
				}
			}
		}
		System.out.println(num0);
	}

}
