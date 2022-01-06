package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Shape implements HW3_Interface {

	private int rows;
	private int cols;
	private int[][] shapeArr;
	private int[][][] outputArr;
	private int[] indexArr;
	private int count;

	public Shape() {
	}

	public Shape(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int[][] getShapeArr() {
		return shapeArr;
	}

	public void setShapeArr(int[][] shapeArr) {
		this.shapeArr = shapeArr;
	}

	public int[][][] getOutputArr() {
		return outputArr;
	}

	public void setOutputArr(int[][][] outputArr) {
		this.outputArr = outputArr;
	}

	public void initializeShapeArr(int rows, int cols) {
		shapeArr = new int[rows][cols];
		outputArr = new int[((rows / 2) + 1) * ((cols / 2) + 1)][rows][cols];
	}

	@Override
	public void ReadShapeFile(String path) {
		int countRow = 0, countCol = 0;
		boolean countFlag = false;
		Scanner sc, sc2, sc3, sc4;

		// Count rows & cols
		try {
			sc = new Scanner(new File("src/main/" + path));

			while (sc.hasNextLine()) {
				sc2 = new Scanner(sc.nextLine());

				while (sc2.hasNext()) {
					if (!countFlag) {
						countCol += 1;
					}
					sc2.next();
				}
				countFlag = true;
				countRow += 1;
			}

			rows = countRow;
			cols = countCol;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Initialize array & assign the values this time
		initializeShapeArr(rows, cols);
		int i = 0, j = 0;
		try {
			sc3 = new Scanner(new File("src/main/" + path));

			while (sc3.hasNextLine()) {
				sc4 = new Scanner(sc3.nextLine());

				while (sc4.hasNext()) {
					shapeArr[i][j] = sc4.nextInt();
					j += 1;
				}
				i += 1;
				j = 0;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void OutputShapes() {

		boolean insertFlag = false;
		int shapeCount = 0;
		int[] starCountArr = new int[outputArr.length];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// Check if it's 1
				if (shapeArr[i][j] == 1) {
					// Check if it's touching with any other 1's from the array+
					outerloop: for (int l = 0; l < outputArr.length; l++) {
						for (int k = 0; k < rows; k++) {
							for (int m = 0; m < cols; m++) {
								if (!insertFlag) {
									if (outputArr[l][k][m] == 1) {
										if ((i - 1 == k) && (j - 1 == m) || (i - 1 == k) && (j == m)
												|| (i - 1 == k) && (j + 1 == m)
												|| (i == k) && (j - 1 == m)
												|| (i == k) && (j + 1 == m)
												|| (i + 1 == k) && (j - 1 == m)
												|| (i + 1 == k) && (j == m)
												|| (i + 1 == k) && (j + 1 == m)) {
											outputArr[l][i][j] = 1;
											starCountArr[l] += 1;
											insertFlag = true;
											break outerloop;
										}
									}
								}
							}
						}
					}

					// If not matched with any shape, insert a new one
					if (!insertFlag) {
						outputArr[shapeCount][i][j] = 1;
						starCountArr[shapeCount] += 1;
						shapeCount += 1;
					}
					insertFlag = false;
				}
			}
		}

		// Combine the shapes if they're touching which are missed on the initial assignment
		for (int l = 0; l < outputArr.length; l++) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (outputArr[l][i][j] == 1) {
						// Check which one it's touching
						outerloop2: for (int g = 0; g < outputArr.length; g++) {
							if (l == g) {
								continue;
							}
							for (int k = 0; k < rows; k++) {
								for (int m = 0; m < cols; m++) {
									if (outputArr[g][k][m] == 1) {
										if ((i - 1 == k) && (j - 1 == m) || (i - 1 == k) && (j == m)
												|| (i - 1 == k) && (j + 1 == m)
												|| (i == k) && (j - 1 == m)
												|| (i == k) && (j + 1 == m)
												|| (i + 1 == k) && (j - 1 == m)
												|| (i + 1 == k) && (j == m)
												|| (i + 1 == k) && (j + 1 == m)) {

											// Check which one has more * in shape
											if (starCountArr[l] >= starCountArr[g]) {
												// Combine & reset smaller one
												for (int o = 0; o < rows; o++) {
													for (int p = 0; p < cols; p++) {
														if (outputArr[g][o][p] == 1) {
															outputArr[l][o][p] = 1;
															outputArr[g][o][p] = 0;
														}
													}
												}
											} else {
												for (int o = 0; o < rows; o++) {
													for (int p = 0; p < cols; p++) {
														if (outputArr[l][o][p] == 1) {
															outputArr[g][o][p] = 1;
															outputArr[l][o][p] = 0;
														}
													}
												}
											}
											break outerloop2;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// After generating the shapes, format them for printing out

		// Count the number of shapes & create index array
		indexArr = new int[outputArr.length];
		count = 0;
		for (int l = 0; l < outputArr.length; l++) {
			outerloop3: for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (outputArr[l][i][j] == 1) {
						indexArr[l] = 1;
						count += 1;
						break outerloop3;
					}
				}
			}
		}

		System.out.println("There are " + count + " shapes");

		int count2 = 1;

		// Find min max of corners

		for (int l = 0; l < outputArr.length; l++) {
			int imin = 0, jmin = 0;
			int imax = 0, jmax = 0;
			boolean initialized = false;

			if (indexArr[l] != 1) {
				continue;
			}

			System.out.println("Shape " + count2);
			count2 += 1;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (outputArr[l][i][j] == 1) {

						if (!initialized) {
							imin = i;
							imax = i;
							jmin = j;
							jmax = j;
							initialized = true;
						}

						if (i < imin) {
							imin = i;
						}

						if (i >= imax) {
							imax = i;
						}

						if (j < jmin) {
							jmin = j;
						}

						if (j >= jmax) {
							jmax = j;
						}
					}
				}
			}

			for (int k = imin; k <= imax; k++) {
				for (int m = jmin; m <= jmax; m++) {
					if (outputArr[l][k][m] == 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	@Override
	public void OutputShapesToFile(String path) {
		PrintWriter printWriter = null;

		try {
			printWriter = new PrintWriter("src/main/" + path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		printWriter.println("There are " + count + " shapes");
		int count2 = 1;

		for (int l = 0; l < outputArr.length; l++) {
			int imin = 0, jmin = 0;
			int imax = 0, jmax = 0;
			boolean initialized = false;

			if (indexArr[l] != 1) {
				continue;
			}

			printWriter.println("Shape " + count2);
			count2 += 1;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (outputArr[l][i][j] == 1) {

						if (!initialized) {
							imin = i;
							imax = i;
							jmin = j;
							jmax = j;
							initialized = true;
						}

						if (i < imin) {
							imin = i;
						}

						if (i >= imax) {
							imax = i;
						}

						if (j < jmin) {
							jmin = j;
						}

						if (j >= jmax) {
							jmax = j;
						}
					}
				}
			}

			for (int k = imin; k <= imax; k++) {
				for (int m = jmin; m <= jmax; m++) {
					if (outputArr[l][k][m] == 1) {
						printWriter.print("*");
					} else {
						printWriter.print(" ");
					}
				}
				printWriter.println();
			}
			printWriter.println();
		}
		printWriter.close();
	}

}
