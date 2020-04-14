package ru.gb.jtwo.lesson_3.homework_3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;


public class PhoneBook {
    private final HashMap<String, ArrayList<Person>> entries = new HashMap<>();

    // метод проверяет был ли уже Иванов или не был
    public void add(String name, String phone, String mail) {
        //если Иванов был
        if (entries.containsKey(name)) {
            //достаю лист с Персонами, который лежит по этому ключу
            ArrayList<Person> persons = entries.get(name);
            // добавляю в лист с персонами нового Иванова
            persons.add(new Person(phone, mail));
        } else {
            //если такого Иванова не было то я создаю Лист с персонами
            ArrayList<Person> persons = new ArrayList<>();
            // добавляюв Лист своего первого Иванова
            persons.add(new Person(phone, mail));
            //складываю Лист обратно в Мапу
            entries.put(name, persons);
        }
    }

    public ArrayList<String> getMails(String name) {
        if (!entries.containsKey(name)) return null;
        // оптимизация, что ты делаешь, прекрати)))
        return entries.get(name).stream().map(person -> person.email)
                .collect(Collectors.toCollection(ArrayList::new));
    }



    public ArrayList<String> getPhones(String name) {
        //для начала проверям есть ли вобще такая фамилия. и если такой фамилии нет возвращаем null
        if (!entries.containsKey(name)) return null;
        //если кто-то нашёлся я забираю список из Персон по этому ключу
        ArrayList<Person> persons = entries.get(name);
        //создаю свой список с результатами
        ArrayList<String> result = new ArrayList<>();
        // складываю все результат из списка с Персонами в список с Телефонами
        for (int i = 0; i < persons.size(); i++) {
            result.add(persons.get(i).phone);
        }
        return result;
    }


}




