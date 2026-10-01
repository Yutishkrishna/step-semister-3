import java.util.*;

public class P3_OnlineExaminationSystem {
    static abstract class Question {
        String id; int points;
        Question(String id,int p){this.id=id;points=p;}
        abstract boolean evaluate(String answer);
    }
    static class MultipleChoiceQuestion extends Question {
        String correct;
        MultipleChoiceQuestion(String id,int p,String c){super(id,p);correct=c;}
        boolean evaluate(String a){return correct.equalsIgnoreCase(a);}
    }
    static class TrueFalseQuestion extends Question {
        boolean correct;
        TrueFalseQuestion(String id,int p,boolean c){super(id,p);correct=c;}
        boolean evaluate(String a){return Boolean.parseBoolean(a)==correct;}
    }
    static class ShortAnswerQuestion extends Question {
        String correct;
        ShortAnswerQuestion(String id,int p,String c){super(id,p);correct=c;}
        boolean evaluate(String a){return correct.equalsIgnoreCase(a.trim());}
    }
    static class Student {String name; Student(String n){name=n;}}
    static class Examination {
        String name; List<Question> questions;
        Examination(String n,Question...q){name=n;questions=Arrays.asList(q);}
    }
    static class Attempt {
        Student student; Examination exam;
        Map<Question,String> answers=new LinkedHashMap<>();
        boolean submitted;
        Attempt(Student s,Examination e){
            student=s;exam=e;
            System.out.println(e.name+" started by "+s.name+".");
        }
        void answer(Question q,String a){
            if(submitted){
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            answers.put(q,a);
            System.out.println("Answer recorded for "+q.id+".");
        }
        void submit(){
            submitted=true;
            System.out.println(exam.name+" submitted by "+student.name+".");
            int score=0,total=0;
            for(Question q:exam.questions){
                total+=q.points;
                boolean correct=q.evaluate(answers.getOrDefault(q,""));
                if(correct) score+=q.points;
                System.out.println(q.id+": "+(correct?"Correct ("+q.points+" points)":"Incorrect (0 points)"));
            }
            System.out.println("Total score: "+score+"/"+total+".");
        }
    }
    public static void main(String[] args){
        Student s=new Student("Student 1");
        Question q1=new MultipleChoiceQuestion("Question 1",5,"C");
        Question q2=new TrueFalseQuestion("Question 2",5,false);
        Examination e=new Examination("Exam A",q1,q2);
        Attempt a=new Attempt(s,e);
        a.answer(q1,"C");
        a.answer(q2,"True");
        a.submit();
        a.answer(q1,"A");
    }
}