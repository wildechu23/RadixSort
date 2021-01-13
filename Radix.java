public class Radix {
    public static int nth(int n, int col) {
        return (int)(n / Math.pow(10,col)) % 10;
    }

    public static int length(int n) {
        return (int)(Math.log10(n) + 1);
    }

    public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
        for(SortableLinkedList bucket : buckets) {
            original.extend(bucket);
        }
    }

    public static void radixSimpleSort(SortableLinkedList data) {
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        int maxCol = 0;

        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new SortableLinkedList();
        }

        for(int i = 0; i < data.size(); i++) {
            if(length(data.get(i)) > maxCol) {
                maxCol = data.get(i);
            }
        }

        for(int i = 0; i < maxCol; i++) {
            while(data.size() > 0) {
                int n = data.get(0);
                int digit = nth(n, i);
                buckets[digit].add(n);
                data.remove(0);
            }
            merge(data, buckets);
        }
    }

    public static void radixSort(SortableLinkedList data) {
        SortableLinkedList negatives = new SortableLinkedList();
        SortableLinkedList positives = new SortableLinkedList(); // + 0

        while(data.size() > 0) {
            if(data.get(0) >= 0) {
                positives.add(data.get(0));
            } else {
                negatives.add(-data.get(0));
            }
            data.remove(0);
        }

        radixSimpleSort(negatives);
        radixSimpleSort(positives);

        negatives = fixNegatives(negatives);
        negatives.extend(positives);

        data.extend(negatives);
    }

    private static SortableLinkedList fixNegatives(SortableLinkedList list) {
        SortableLinkedList hold = new SortableLinkedList();
        for(int i = list.size() - 1; i >=0; i--) {
            System.out.println(i + ", " + list.get(i));
            hold.add(list.get(i)*-1);
        }
        return hold;
    }
}
