package chap11;

import javax.print.attribute.standard.PresentationDirection;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        Optional<Integer> optionalInteger = Optional.empty();
        System.out.println(optionalInteger);        // Optional.empty

//        Optional<Integer> optInt = Optional.of(13);
//        System.out.println(optInt);         // Optional[13]

//        Optional<Object> optNull = Optional.of(null);
//        System.out.println(optNull);        // NullPointerException 발생

        Optional<Object> optNull = Optional.ofNullable(null);
        System.out.println(optNull);        // Optional.empty

        Optional<String> optName = Optional.of("hyun");
        Optional<Integer> optNameLength = optName.map(String::length);

        System.out.println(optNameLength);  // Optional[4]

        Parent parent = new Parent();
        Child child = new Child("hyun");

        parent.setChild(Optional.of(child));

        Optional<Parent> optParent = Optional.of(parent);

//        Optional<String> childNameError = optParent.map(Parent::getChild)
//                                                   .map(c -> c.getName);     // 컴파일 에러

        Optional<String> childName = optParent.flatMap(Parent::getChild)
                                              .map(Child::getName);

        System.out.println(childName);      // Optional[hyun]

        Optional<Object> empty = Optional.empty();

        Optional<Integer> result = Optional.of(10)
                .filter(optInt -> optInt > 5);

        System.out.println(result);     // Optional[10]


        Optional<Integer> result2 = Optional.of(3)
                .filter(optInt -> optInt > 5);

        System.out.println(result2);    // Optional.empty
    }


    public static class Parent {
        private Optional<Child> child;

        public Optional<Child> getChild() {
            return child;
        }

        public void setChild(Optional<Child> child) {
            this.child = child;
        }
    }

    public static class Child {
        private String name;

        public Child(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
