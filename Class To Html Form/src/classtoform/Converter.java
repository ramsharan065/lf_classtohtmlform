package classtoform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
/**
 * 
 * @author ramsharan
 *
 */
public class Converter {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//if(args.length)
		String className = args[0];
		String htmlName = args[1];
		//className = "classtoform.Cf";
		//htmlName = "jpt.html";
		Class<?> entityClass=null;
		try {
			entityClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Field[] attribute = entityClass.getFields();
		String[] attributeName = new String[attribute.length];
		for(int i=0;i<attributeName.length;i++){
			attributeName[i] = attribute[i].getName();
		}
		File htmlFile = new File(htmlName);
		
		try {
			makeForm(attributeName,"simple form",htmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param attributeName
	 * @param title
	 * @param htmlFile
	 * @throws IOException
	 */
	protected static void makeForm(String[] attributeName, String title, File htmlFile) throws IOException{
		String upperPart="<!DOCTYPE html >" +
				"<html>" +
				"<head>" +
				"<title>"+title+
				"</title>" +
				"</head>" +
				"<body>" +
				"<form>" +
				"<table>";
		String lowerPart="</table></form>" +
				"</body>" +
				"</html>";
		String innerPart="</td> <td><input type=\"text\" name=";
		String innerWholeParts="";
		for(int i=0;i<attributeName.length;i++){
			innerWholeParts+="<tr><td>"+attributeName[i]+innerPart+"\""+attributeName[i]+"\"/></td></tr><br/>";
		}
		innerWholeParts+="<tr><td><input type=\"submit\" value=\"click\"/></td></tr>";
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(htmlFile));
		fileWriter.write(upperPart+innerWholeParts+lowerPart);
		fileWriter.close();
	}
}
