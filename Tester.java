public class Tester {
    public static void main(String[] args){
        System.out.println(Radix.nth(123, 0));
        System.out.println(Radix.nth(123, 1));
        System.out.println(Radix.nth(123, 2));

        SortableLinkedList a = new SortableLinkedList();
        a.add(90); a.add(70); a.add(53); a.add(67); a.add(2); a.add(12);
        a.add(-12); a.add(-3); a.add(-90); a.add(34); a.add(-69);
        System.out.println(a);
        // Radix.radixSimpleSort(a);
        Radix.radixSort(a);
        System.out.println(a);
    }
}
