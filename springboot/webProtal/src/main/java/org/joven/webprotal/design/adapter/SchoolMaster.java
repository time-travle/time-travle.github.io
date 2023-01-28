package org.joven.webprotal.design.adapter;

/**
 * 校长类
 */
public class SchoolMaster implements Actions{
    private String name;

    public SchoolMaster(){}
    public SchoolMaster(String name){
        this.name=name;
    }
    InstructorTeacher instructorTeacher = null;
    DailyTeacher dailyTeacher=null;
    public void getInfo(String type,String teacherOrStuType,String target){
        if("Stu".equals(type)){
            //查询学生信息
            if (teacherOrStuType.equals("class")){
                dailyTeacher= new TeachingDirector("赵教导主任老师");
            }else {
                dailyTeacher = new HeadMaster("刘班主任");
            }
            System.out.println(dailyTeacher.getDailyTeacherName());
            dailyTeacher.getClassesInfo(name,target);
            dailyTeacher.getStudentInfo(name,target);
            dailyTeacher.getTeacherInfo(name,target);
        }else if ("tea".equals(type)){
            //查询老师信息
            switch (teacherOrStuType){
                case "chinese":
                    instructorTeacher = new ChemistryTeacher("刘化学老师");
                    break;
                case "math":
                    instructorTeacher = new MathTeacher("王数学老师");
                    break;
                case "chemistry":
                    instructorTeacher = new ChemistryTeacher("高化学老师");
                    break;
                default:
                    instructorTeacher = new HeadMaster("郭班主任");
            }
            System.out.println(instructorTeacher.getTeacherName());
            instructorTeacher.lecture(target);
            instructorTeacher.homeworkCheck(target);
            instructorTeacher.homeworkPublish(target);
        }

    }
}
