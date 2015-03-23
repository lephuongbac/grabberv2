package com.ketnoiso.core.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * The Class FileSplit.
 */
public class FileSplit {

	/** The f. */
	private File f;

	/** The fis. */
	private FileInputStream fis;

	/** The path. */
	private String path;

	/** The file name. */
	private String fileName;

	/** The count. */
	int count;

	/**
	 * Instantiates a new file split.
	 * 
	 * @param f
	 *            the f
	 */
	public FileSplit(File f) {

		this.f = f;

		fileName = f.getName();

		count = 1;

		path = f.getParent();

	}

	/**
	 * Split.
	 * 
	 * @return the int
	 */
	public int split() {

		try {

			fis = new FileInputStream(f);

			byte buf[] = new byte[10 * 1000 * 1000];

			int num = 0;

			while ((num = fis.read(buf)) != -1) {

				if (createSplitFile(buf, 0, num) == -1) {

					return 0;

				}

				count++;

			}

		} catch (Exception e) {

		} finally {

			if (fis != null) {

				try {

					fis.close();

				} catch (Exception e) {

				}

			}
			f.delete();

		}

		return count;

	}

	/**
	 * Creates the split file.
	 * 
	 * @param buf
	 *            the buf
	 * @param zero
	 *            the zero
	 * @param num
	 *            the num
	 * @return the int
	 */
	private int createSplitFile(byte buf[], int zero, int num) {

		FileOutputStream fosTemp = null;

		try {
			File fileOut = new File(path,f.getName() + ".00" + count);
			fosTemp = new FileOutputStream(fileOut);

			fosTemp.write(buf, zero, num);

			fosTemp.flush();

		} catch (Exception e) {

			return -1;

		} finally {

			try {

				fosTemp.close();

			} catch (Exception e) {

			}

		}

		return 1;

	}

}
