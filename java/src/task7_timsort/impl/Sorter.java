package task7_timsort.impl;

@FunctionalInterface
interface Sorter
{
    void sort(int[] array, int l, int h);
}
