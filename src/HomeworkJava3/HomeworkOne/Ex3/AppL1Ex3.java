package HomeworkJava3.HomeworkOne.Ex3;
/* Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по
типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов
и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
которую подадут в compare в качестве параметра, true – если она равны по весу,
false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
(помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
которые были в этой коробке;
Не забываем про метод добавления фрукта в коробку. */

public class AppL1Ex3 {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
            appleBox.add(new Apple());
            appleBox.add(new Apple());
            appleBox.add(new Apple());
        Box<Orange> orangeBox = new Box<>();
            orangeBox.add(new Orange());
            orangeBox.add(new Orange());

        appleBox.show();
        orangeBox.show();
        appleBox.compare(orangeBox);

        Box<Apple> appleBox2 = new Box<>();
        appleBox.changeBox(appleBox2);
        appleBox2.show();
        appleBox.show();

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox.changeBox(orangeBox2);
        orangeBox2.show();
        orangeBox.show();
    }
}
