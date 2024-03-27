package sample.utils;

import sample.JShellLink;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;


public class FileManipulator {

	/**
	 * @param args
	 */
	public static ArrayList<File> programs = new ArrayList<>();

	private static void hidePrograms(String path) {
		// list files, returns the array of abstract pathnames
		File[] files = new File(path).listFiles();
		for (File file : files) {
			// returns their absolute path
			hideOrUnhide(file.getAbsolutePath(), Boolean.TRUE);
			if (file.isDirectory()) {
				hidePrograms(file.getAbsolutePath());
			}
		}
	}

	private static void setPrograms(String path) {
		try{
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if(programs.size() > 20){
				break;
			}
			if (file.isDirectory()) {
				setPrograms(file.getAbsolutePath());
			} else {
				if (file.getName().length() > 4
						&& file.getName()
								.substring(file.getName().length() - 4,
										file.getName().length()).equals(".exe")
						&& !programs.contains(file)) {
					programs.add(file);
					System.out.println(programs.size());
					System.out.println("from file manipulator setPrograms");
				}
			}
		}
		}catch(Exception e){
			
		}
	}

	public static void hideOrUnhide(String path, Boolean bool) {
		Path p = FileSystems.getDefault().getPath(path);
		try {
			Files.setAttribute(p, "dos:hidden", bool, LinkOption.NOFOLLOW_LINKS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removePrograms(ArrayList<File> progs){
		for(int i = 0 ; i < progs.size(); i++){

			File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\" + progs.get(i).getName().substring(0, progs.get(i).getName().length() - 3) + "lnk");
			//System.out.println("Deleting");
			//System.out.println("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\" + progs.get(i).getName().substring(0, progs.get(i).getName().length() - 3) + "lnk");
			if(file.delete()){
				System.out.println("DELETED " + file.getName());
			}
		}
	}

	public static void showTaskPrograms(ArrayList<File> progs) {
		for (int i = 0; i < progs.size(); i++) {
			File file = progs.get(i);
			try {

				JShellLink link = new JShellLink();
				link.setFolder(JShellLink.getDirectory("desktop"));

				link.setName(file.getName().substring(0, file.getName().length() - 4));

				link.setPath(file.getAbsolutePath());

				link.save();
		

			} catch (Exception ex) {

				ex.printStackTrace();

			}
		}
	}

	public static void hiderAndProgramsSetter() {
		//hidePrograms("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs");
		//hidePrograms("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs");
		//hidePrograms("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop");
		//hidePrograms("C:\\Users\\alloka\\Desktop");
		setPrograms("C:\\Program Files");
		setPrograms("C:\\Program Files (x86)");
	}

	public static void main(String[] args) {
		try {

			File file = new File("C:\\Users\\Johnny Bishara\\Desktop\\derwy.lnk");
			if(file.delete()){
				System.out.println("DELETED");
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}
}
