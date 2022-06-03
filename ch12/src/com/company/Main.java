package com.company;
class Member{
    String name="",Address="";
    int age,salary=500;
    double PhoneNumber;
    int printSalary(){
        return  salary;
    }

}
class  Employee extends  Member{
    String specialization;
    String department;
}
class  Manager extends  Member{
    String specialization;
    String department;
}
public class Main {

    public static void main(String[] args) {
        // write your code here
        Employee e1=new Employee();
        e1.name="harsh ";
        e1.Address="Rajkot";
        e1.age=20;
        e1.PhoneNumber=1234567890;
        e1.salary=50000;
        e1.specialization="Full satck developer";
        e1.department="Developement and tester";

        Manager m1=new Manager();
        
        m1.name="Tulesh ";
        m1.Address="Mavadi,Rajkot";
        m1.age=20;
        m1.PhoneNumber=852877452;
        m1.salary=500000;
        m1.specialization="Full satck developer";
        m1.department="Developement and manager";
        System.out.println("Employee name : "+e1.name+
        "\nAddress"+e1.Address+"\nAge"+e1.age+"PhoneNumber : "+e1.PhoneNumber+" Salary : "+e1.salary+
                "\nspecialization : "+e1.specialization+"\n department : "+e1.department);
        System.out.println("Employee name : "+m1.name+
                "\nAddress"+m1.Address+"\nAge"+m1.age+"PhoneNumber : "+m1.PhoneNumber+" Salary : "+m1.salary+
                "\nspecialization : "+m1.specialization+"\n department : "+m1.department);


    }
}
