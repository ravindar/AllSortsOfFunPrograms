
public class DeadLock {
    static class SimpleClass {
        private final String name;
        public SimpleClass(String name){
        	this.name = name;
        }
        
        public String getName(){
        	return this.name;
        }
        
        public synchronized void methodA(SimpleClass simple) {
        			System.out.println("Got both locks - method - A" + this.name + simple.getName());
        			simple.methodB(this);
        }

        public synchronized void methodB(SimpleClass simple) {
        			System.out.println("Got both locks - method - B"+this.name + simple.getName());
        }
    }

    public static void main(String[] args) {
        final SimpleClass objectA = new SimpleClass("Object A");
        final SimpleClass objectB = new SimpleClass("Object B");
	        new Thread(new Runnable() {
	            public void run() { objectA.methodA(objectB); }
	        }).start();
	        new Thread(new Runnable() {
	            public void run() { objectB.methodA(objectA); }
	        }).start();
    }
}

//public class DeadLock {
//    static class Friend {
//        private final String name;
//        public Friend(String name) {
//            this.name = name;
//        }
//        public String getName() {
//            return this.name;
//        }
//        public synchronized void bow(Friend bower) {
//            System.out.format("%s: %s has bowed to me!%n", 
//                    this.name, bower.getName());
//            bower.bowBack(this);
//        }
//        public synchronized void bowBack(Friend bower) {
//            System.out.format("%s: %s has bowed back to me!%n",
//                    this.name, bower.getName());
//        }
//    }
//
//    public static void main(String[] args) {
//        final Friend alphonse = new Friend("Alphonse");
//        final Friend gaston = new Friend("Gaston");
//        new Thread(new Runnable() {
//            public void run() { alphonse.bow(gaston); }
//        }).start();
//        new Thread(new Runnable() {
//            public void run() { gaston.bow(alphonse); }
//        }).start();
//    }
//}