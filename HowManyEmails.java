import java.util.*;

public class HowManyEmails{
    public static int numUniqueEmails(String[] emails) {
        System.out.print("List contains "+emails.length+" emails\n");
        Map<String, Integer> map = new HashMap<String, Integer>();
        String tmpString = null; 
        int count=0; 
        for(int i=0; i<emails.length; i++) {
            //filter out . in the email local name
            tmpString = dotRule(emails[i]);
            System.out.print(tmpString+"\n");
            //apply + rule by deleting all characters after + in local name
            tmpString = plusRule(tmpString); 
            System.out.print("after plusRule, "+tmpString+"\n");
            if(tmpString!=null) {
                map.put(tmpString, count);
                count++; 
            }
        }
        return map.size(); 
    }
    
    private static String dotRule(String email) {
        StringBuilder sb = new StringBuilder(email); 
        String result = null; 
        for(int i =0; i<email.length(); i++) {
            if(sb.charAt(i)!='@') {
                if(sb.charAt(i)=='.') {
                    sb.deleteCharAt(i); 
                }
            }
            else break; 
        }
        result = sb.toString();
        return result; 
    }
    private static String plusRule(String email) {
        System.out.print("in plusRule, email is "+email+"\n");
        //StringBuilder sb = new StringBuilder(email); 
        //System.out.print(sb+"\n");
        String result = null; 
        //boolean deleteFlag=false; 
        /* this will need to calculate the length again after a character is removed... so much trouble...
        for(int i=0; i<email.length(); i++) {
            if(deleteFlag==true && sb.charAt(i)!='@') {
                System.out.print("am i here? i is "+i+"\n");
                sb.deleteCharAt(i); 
                continue; 
            }
            if(sb.charAt(i)!='@') {
                System.out.print("or am i here? i is "+i+" and charAt is "+sb.charAt(i)+"\n");
                if(sb.charAt(i)=='+') {
                    sb.deleteCharAt(i);
                    System.out.print("So waht is sb? "+sb+"\n");
                    deleteFlag=true; 
                }
            }
            else break; 
        }
        */
        //try regular expression
        String firstSplit[] = email.split("@"); //this will split local name and domain name, 2 parts
        System.out.print("firstSplit[1] is: "+firstSplit[1]+"\n");
        String secondSplit[] = firstSplit[0].split("[+]"); //firstSplit[0] is the local name, now split the local name into 2 parts--before and after the first +
        System.out.print("secondSplit[0] is: "+secondSplit[0]+"\n");
        //now we need firstSplit[1] which is domain name, and secondSplit[0] which is local name before first + is encountered
        result = secondSplit[0]+'@'+firstSplit[1];
        System.out.print("email is now "+ result +"\n");
        return result; 
    }

    public static void main(String []args){
        String[] emailList={"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com", "test.email+al+ex@leetcode.com", "test.emailalex@leet+code.com"};
        int answer=numUniqueEmails(emailList); 
        System.out.println("There are "+answer+" distinctive e-mails");
    }
}
