package domain;

public class Box <T>{
        private T value;

        public Box(T value) {
            this.value = value;
        }
        public boolean isEmpty(){
            return value==null;
        }

        public Object getValue() {
            return value;
        }
}
