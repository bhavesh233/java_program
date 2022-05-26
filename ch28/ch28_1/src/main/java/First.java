import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class First {
    public static Connection getConnet() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db1";
            c = DriverManager.getConnection(url, "postgres", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void main(String[] args) throws Exception {
        Connection connect = getConnet();
        System.out.println("Hello");
        UpdateRecords.UpdateWhichJoinBefore6To12MonthBefore();
//        UpdateRecords.UpdateRecordsWithDOB();
//        selectTraineeDateDescending(connect);
//        selectCityNotAhmedabad(connect);
        selectCityRajkot(connect);
        selectDataBetween20To21(connect);
        DeleteRecords.DeleteRecords();
//        multiInsertData(connect);
//        insertData(connect);
        UpdateRecords.UpdateRecords();


        selectData(connect);

//        ConnectDB onj_connect=new Connect
        if (connect != null) {
            PreparedStatement st = null;
            String sql = "select * from trainee";
            try {
                st = connect.prepareStatement("select * from trainee");

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("trainee_name");
                    String add = rs.getString("trainee_address");
                    System.out.println(name + " :" + add);

                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void multiInsertData(Connection connect) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (connect != null) {
            List list = new ArrayList<>();
            list.add(new Trainee("shhn", "y1", dateFormat.parse("17-10-2001"), dateFormat.parse("17-10-2021"), 21));
            list.add(new Trainee("x2", "y2", dateFormat.parse("18-10-2001"), dateFormat.parse("15-10-2021"), 22));
            list.add(new Trainee("x3", "y3", dateFormat.parse("19-10-2001"), dateFormat.parse("14-10-2021"), 23));
            list.add(new Trainee("x4", "y4", dateFormat.parse("10-10-2001"), dateFormat.parse("19-10-2021"), 19));
            Statement st = null;
            final String sql = "insert into trainee (traineeName,traineeAddress,trainee_dob,trainee_joining_date,traineeAge) VALUES (?,?,?,?,?)";

            try {
                connect.setAutoCommit(false);
                PreparedStatement myStatement = connect.prepareStatement(sql);

                for (Iterator<Trainee> iterator = list.iterator(); iterator.hasNext(); ) {
                    Trainee trainee = iterator.next();
                    myStatement.setString(1, trainee.getTraineeName());
                    myStatement.setString(2, trainee.getTraineeAddress());
                    myStatement.setDate(3, new java.sql.Date((trainee.getTraineeDOB()).getTime()));
                    myStatement.setDate(4, new java.sql.Date((trainee.getTraineeJoinDate()).getTime()));
                    myStatement.setInt(5, trainee.getTraineeAge());
                    System.out.println("trainee.getTraineeAddress() " + trainee.getTraineeAddress());
                    myStatement.addBatch();
                }
                int[] updateCounts = myStatement.executeBatch();
                System.out.println("array " + Arrays.toString(updateCounts));
                connect.commit();
                connect.setAutoCommit(true);
            } catch (Exception e) {
                System.out.println("exception in multi insert : " + e.toString());
            }
        }
    }

    public static void insertData(Connection connect) {
        if (connect != null) {
            Statement st = null;
            String sql = "insert into trainee (traineeName,traineeAddress,trainee_dob,trainee_joining_date,traineeAge) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter name : ");
                String s1 = sc.next();
                System.out.println("Enter address : ");
                String s2 = sc.next();
                Date d1 = new Date();
                int i = 10;
                pstmt.setString(1, s1);
                pstmt.setString(2, s2);
                pstmt.setDate(3, (java.sql.Date) d1);
                pstmt.setDate(4, (java.sql.Date) d1);
                pstmt.setInt(5, i);
                pstmt.executeUpdate();

            } catch (Exception e) {
                System.out.println("exception in insert : " + e.toString());
            }
        }
    }



    public static void selectData(Connection connect) {
        if (connect != null) {
            PreparedStatement st = null;
            String sql = "select * from trainee";
            try {
                st = connect.prepareStatement("select * from trainee where traineeName=?");

                Scanner sc = new Scanner(System.in);
                System.out.println("enter trainne name number : ");
                String s1 = sc.next();

                st.setString(1, s1);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("trainee_name");
                    String add = rs.getString("trainee_address");
                    System.out.println(name + " :" + add);
                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void selectTraineeDateDescending(Connection connect) {
        if (connect != null) {
            PreparedStatement st = null;
            try {
                st = connect.prepareStatement("select * from trainee ORDER BY trainee_dob");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String traineeId = rs.getString("trainee_id");
                    String traineeName = rs.getString("trainee_name");
                    String traineeAddress = rs.getString("trainee_address");
                    String traineeAge = rs.getString("trainee_age");
                    Date traineeBirthDate = rs.getDate("trainee_dob");
                    Date traineeJoinDate = rs.getDate("trainee_joining_date");
                    System.out.println("trainee id : " + traineeId + " trainee name : " + traineeName +" trainee Join date :"+traineeJoinDate+" trainee date : "+traineeBirthDate+ " address :" + traineeAddress + " Age : " + traineeAge);
                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void selectCityRajkot(Connection connect) {
        if (connect != null) {
            PreparedStatement st = null;
            try {
                st = connect.prepareStatement("select * from trainee where  traineeAddress like '%Rajkot%'");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String traineeId = rs.getString("trainee_id");
                    String traineeName = rs.getString("trainee_name");
                    String traineeAddress = rs.getString("trainee_address");
                    String traineeAge = rs.getString("trainee_age");
                    Date traineeBirthDate = rs.getDate("trainee_dob");
                    System.out.println("trainee id : " + traineeId + " trainee name : " + traineeName +" birth date : "+traineeBirthDate +" address :" + traineeAddress + " Age : " + traineeAge);
                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void selectCityNotAhmedabad(Connection connect) {
        if (connect != null) {
            PreparedStatement st = null;
            try {
                st = connect.prepareStatement("select * from trainee where  traineeAddress not like '%Ahmedabad%'");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String traineeId = rs.getString("trainee_id");
                    String traineeName = rs.getString("trainee_name");
                    String traineeAddress = rs.getString("trainee_address");
                    String traineeAge = rs.getString("trainee_age");
                    System.out.println("trainee id : " + traineeId + " trainee name : " + traineeName + " address :" + traineeAddress + " Age : " + traineeAge);
                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void selectDataBetween20To21(Connection connect) {
        if (connect != null) {
            PreparedStatement st = null;
            try {
                st = connect.prepareStatement("select * from trainee where traineeAge=20 or traineeAge=21");

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String traineeId = rs.getString("trainee_id");
                    String traineeName = rs.getString("trainee_name");
                    String traineeAddress = rs.getString("trainee_address");
                    String traineeAge = rs.getString("trainee_age");
                    System.out.println("trainee id : " + traineeId + " trainee name : " + traineeName + " address :" + traineeAddress + " Age : " + traineeAge);

                }
            } catch (Exception e) {
                System.out.println("exception in select : " + e);
            }
        }
    }

    public static void insertData1(Connection connect) {
        if (connect != null) {
            Statement st = null;
            String sql = "insert into trainee (name,address) VALUES ('a3','b3')";
            try {
                st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);

            } catch (Exception e) {
                System.out.println("exception in insert : " + e.toString());
            }
        }
    }
}
