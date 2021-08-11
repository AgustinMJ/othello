package main.utils;

import java.io.File;

/**
 * La classe CountingSort és l'encarregada d'executar l'algorisme Counting Sort que utilitzem per ordenar el rankinga persistència.
 *
 * @author Agustín Millán Jiménez
 */
public class CountingSort {
	/**
	 * Funció que executa l'algorisme.
	 * @param arr L'array a ordenar.
	 * @param nFiles El nombre de files a ordenar.
	 */
	public void sort(File arr[], int nFiles)
	{
		File[] output = new File[nFiles];

		int[] count = new int[nFiles];
		for (int i = 0; i < nFiles; ++i)
			count[i] = 0;

		for (int i = 0; i < nFiles; ++i)
			++count[Integer.parseInt(arr[i].getName().split(" ")[0])];

		for (int i = 1; i <= nFiles - 1; ++i)
			count[i] += count[i - 1];

		for (int i = nFiles - 1; i >= 0; i--) {
			output[count[Integer.parseInt(arr[i].getName().split(" ")[0])] - 1] = arr[i];
			--count[Integer.parseInt(arr[i].getName().split(" ")[0])];
		}

		for (int i = 0; i < nFiles; ++i)
			arr[i] = output[i];
	}
}
