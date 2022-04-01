package com.antkiller.jobs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtil {

	/** * ����Ǯ�� �޼ҵ� * * @param zipFileName �������� * @param directory ���� Ǯ ���� */
	public static boolean unZip(String zipPath, String zipFileName, String zipUnzipPath) {
		// ���� ���������� ������ ������ �Ǿ�°�.
		boolean isChk = false; // ������ Ȧ�� ��ġ�� ������
		zipUnzipPath = zipUnzipPath + zipFileName.replace(".zip", ""); // zip ����
		File zipFile = new File(zipPath + zipFileName);
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zipentry = null;
		try { // zipFileName�� ���ؼ� ���� �����
			if (makeFolder(zipUnzipPath)) {
				System.out.println("������ �����߽��ϴ�");
			}
			// ���� ��Ʈ��
			fis = new FileInputStream(zipFile); // Zip ���� ��Ʈ��
			zis = new ZipInputStream(fis, Charset.forName("EUC-KR")); // ����Ǿ� �ִ� ZIP ������ ��� ��ȸ
			while ((zipentry = zis.getNextEntry()) != null) {
				String filename = zipentry.getName();
				System.out.println("filename(zipentry.getName()) => " + filename);
				File file = new File(zipUnzipPath, filename); // entiry�� ������ ���� ����
				if (zipentry.isDirectory()) {
					System.out.println("zipentry�� ���丮�Դϴ�.");
					file.mkdirs();
				} else { // �����̸� ���� �����
					System.out.println("zipentry�� �����Դϴ�.");
					try {
						createFile(file, zis);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
			isChk = true;
		} catch (Exception e) {
			isChk = false;
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (IOException e) {
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return isChk;
	}

	/** * @param folder - ������ ���� ��ο� �̸� */

	private static boolean makeFolder(String folder) {
		if (folder.length() < 0) {
			return false;
		}
		String path = folder; // ���� ���
		File Folder = new File(path); // �ش� ���丮�� ������� ���丮�� �����մϴ�.
		if (!Folder.exists()) {
			try {
				Folder.mkdir();
				// ���� �����մϴ�.
				System.out.println("������ �����Ǿ����ϴ�.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
		}
		return true;
	}

	/** * ���� ����� �޼ҵ� * * @param file ���� * @param zis Zip��Ʈ�� */

	private static void createFile(File file, ZipInputStream zis) throws Throwable { // ���丮 Ȯ��
		File parentDir = new File(file.getParent()); // ���丮�� ������ ��������
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		FileOutputStream fos = null; // ���� ��Ʈ�� ����
		try {
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[256];
			int size = 0; // Zip��Ʈ�����κ��� byte�̾Ƴ���
			while ((size = zis.read(buffer)) > 0) { // byte�� ���� �����
			fos.write(buffer, 0, size);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
