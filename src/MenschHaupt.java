public class MenschHaupt {
    public static void main(String[] args) {
        Person objekt1 = new Person("Stefan Raab", 56, 1.81, 'm', 88.8);
        Person objekt2 = new Person("Angela Merkel", 69, 1.65, 'w', 83.5 );
        objekt1.sprechen();
        objekt2.sprechen();

        // Raab erst abnehmen und dann zunehmen
        objekt1.abnehmen(3.0);
        objekt1.zunehmen(1.5);

        // Merkel erst zunehmen und dann abnehmen
        objekt2.zunehmen(1.2);
        objekt2.abnehmen(2.6);


        System.out.println("");
        System.out.println("BMI von " + objekt1.name + " ist " + objekt1.errechnenBmi());
        System.out.println("BMI von " + objekt2.name + " ist " + objekt2.errechnenBmi());

    }
}
