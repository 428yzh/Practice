package test;

import bean.PersonListType;
import bean.person;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class json {
    @Test
    public void testJson(){
        Gson gson = new Gson();
        person a = new person(1,"yzh");
        person b = new person(2, "xzx");
        String JsonString = gson.toJson(a);
        System.out.println(JsonString);
        List<person> personList = new ArrayList<person>();
        personList.add(a);
        personList.add(b);
        String personListJson = gson.toJson(personList);
        System.out.println(personListJson);
        List<person> personList1 = new ArrayList<>();
        personList = gson.fromJson(personListJson, new PersonListType().getType());
        person xzx = personList.get(1);
        System.out.println(xzx);
    }
}
