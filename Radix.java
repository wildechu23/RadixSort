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

    public static void radixSortSimple(SortableLinkedList data) {
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        int maxCol = 1;

        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new SortableLinkedList();
        }

        for(int i = 0; i < maxCol; i++) {
            while(data.size() > 0) {
                int n = data.get(0);
                if(i == 0 && length(n) > maxCol) {
                    maxCol = length(n);
                }
                buckets[nth(n,i)].add(n);
                data.remove(0);
            }
            merge(data, buckets);
        }
    }

    public static void radixSort(SortableLinkedList data) {
        SortableLinkedList negatives = new SortableLinkedList();
        SortableLinkedList positives = new SortableLinkedList(); // + 0

        while(data.size() > 0) {
            int n =data.get(0);
            if(n >= 0) {
                positives.add(n);
            } else {
                negatives.add(-n);
            }
            data.remove(0);
        }

        radixSortSimple(negatives);
        radixSortSimple(positives);

        negatives = fixNegatives(negatives);
        negatives.extend(positives);

        data.extend(negatives);
    }

    private static SortableLinkedList fixNegatives(SortableLinkedList list) {
        SortableLinkedList hold = new SortableLinkedList();
        for(int i = list.size() - 1; i >=0; i--) {
            // System.out.println(i + ", " + list.get(i));
            hold.add(list.get(i)*-1);
        }
        return hold;
    }
}
