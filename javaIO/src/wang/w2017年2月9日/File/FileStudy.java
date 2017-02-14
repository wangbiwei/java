/**
 * @todo javaIo
 */
package wang.w2017年2月9日.File;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import 工具.时间;
import 常量.真假;
import 常量.返回对象;


/**
 * @author 王必伟
 *
 * @date 2017年2月13日
 *
 * @tags
 */
public class FileStudy
{
	/**
	 * 重命名文件或者文件夹
	 * @param path 文件路径
	 * @param newName 新名称
	 * @return
	 */
	public static 返回对象 重命名(String path, String newName)
	{
		return 重命名(new File(path), newName);
	}
	
	/**
	 * @param file
	 * @param newName
	 * @return
	 */
	public static 返回对象 重命名(File file, String newName)
	{
		if (!file.exists()) {
			return new 返回对象(真假.FLASE, "源文件不存在！");
		}
		String path = file.getParent();
		path = path + File.separatorChar + newName;
		if (file.renameTo(new File(path))) {
			return new 返回对象(真假.TRUE, "重命名成功");
		}
		
		return new 返回对象(真假.TRUE, "重命名失败");
	}
	
	/**
	 * 新建文件夹或者文件
	 * @param file 要建的文件夹或者文件
	 * @param FileOrDirectory 标志
	 * @return
	 */
	public static 返回对象 createFileOrDirectory(String path, String FileOrDirectory)
	{
		final String FILE = "F";
		final String DIRECTORY = "D";
		File file = new File(path);
		if (file.exists()) {
			String eoerMsg;
			if (FILE.equalsIgnoreCase(FileOrDirectory)) {
				eoerMsg = "文件已经存在";
			} else {
				eoerMsg = "文件夹已经存在";
			}
			return new 返回对象(真假.FLASE, eoerMsg);
		}
		if (DIRECTORY.equalsIgnoreCase(FileOrDirectory)) {
			if (file.mkdirs()) {
				return new 返回对象(真假.TRUE, "文件夹创建成功");
			} else {
				return new 返回对象(真假.TRUE, "文件夹创建失败");
			}
		} else {
			try {
				file.createNewFile();
				return new 返回对象(真假.TRUE, "文件创建成功");
			} catch (IOException e) {
				return new 返回对象(真假.TRUE, "文件创建失败");
			}
		}
	}
	
	/**
	 * 类似Everything的功能
	 * @param regex
	 * @return
	 */
	public static 返回对象 类似Everything(String regex)
	{
		List<List<File>> re = new ArrayList<>();
		
		List<File> result = new ArrayList<File>();
		List<File> errorResult = new ArrayList<>();
		File[] 盘符 = File.listRoots();
		for (File file : 盘符) {
			@SuppressWarnings("unchecked")
			List<List<File>> temp = (List<List<File>>) 选择某一路径下满足某些条件的文件(file, regex).getObj();
			result.addAll(temp.get(0));
			errorResult.addAll(temp.get(1));
		}
		return new 返回对象(真假.TRUE, re);
	}
	
	/**
	 * 遍历文件夹
	 * 
	 * @param path 文件夹路径
	 */
	public static 返回对象 遍历文件夹(String path)
	{
		return 遍历文件夹(new File(path));
	}
	
	/**
	 * 选择某一路径下满足某些条件的文件（满足正则表达式）
	 * @param path
	 * @param regex 搜索条件
	 * @return
	 */
	public static 返回对象 选择某一路径下满足某些条件的文件(String path, String regex)
	{
		return 选择某一路径下满足某些条件的文件(new File(path), regex);
	}
	
	/**
	 * 遍历文件夹
	 * 
	 * @param file 要遍历的文件夹
	 */
	@SuppressWarnings("unchecked")
	public static 返回对象 遍历文件夹(File file)
	{
		List<List<File>> re = new ArrayList<>();
		
		List<File> result = new ArrayList<File>();
		List<File> errorResult = new ArrayList<>();
		re.add(result);
		re.add(errorResult);
		
		File[] list = file.listFiles();
		if (list == null) {// 文件不存在或者不能访问
			errorResult.add(file);
			/*
			 * try { Thread.sleep(5000);// 让主线程休眠10秒，看哪些文件夹不可访问； } catch
			 * (InterruptedException e) { e.printStackTrace(); }
			 */
			return new 返回对象(真假.TRUE, re);
		}
		for (File item : list) {
			if (item.isFile()) {
				result.add(item);
			} else if (item.isDirectory()) {
				result.addAll((Collection<? extends File>) ((List<List<File>>) 遍历文件夹(item).getObj()).get(0));
				errorResult.addAll((Collection<? extends File>) ((List<List<File>>) 遍历文件夹(item).getObj()).get(1));
			}
		}
		return new 返回对象(真假.TRUE, re);
	}
	
	/**
	 * 选择某一路径下满足某些条件的文件（满足正则表达式）
	 * @param file
	 * @param regex 搜索条件
	 * @return
	 */
	public static 返回对象 选择某一路径下满足某些条件的文件(File file, String regex)
	{
		List<List<File>> re = new ArrayList<>();
		
		List<File> result = new ArrayList<File>();
		List<File> errorResult = new ArrayList<>();// 装的是不能访问或者是不存在的文件
		re.add(result);
		re.add(errorResult);
		File[] list = file.listFiles();
		File[] selectList = file.listFiles(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(tanslateString(regex));
			
			@Override
			public boolean accept(File dir, String name)
			{
				return pattern.matcher(name).matches();
			}
			
		});
		if (null == list) {// 文件不存在或者不能访问
			errorResult.add(file);
			System.out.println(file.getPath());
			return new 返回对象(真假.FLASE, re);
		}
		for (File item : selectList) {
			if (item.isFile()) {
				result.add(item);
			}
		}
		for (File item : list) {
			if (item.isDirectory()) {
				@SuppressWarnings("unchecked")
				List<List<File>> temp = (List<List<File>>) 选择某一路径下满足某些条件的文件(item, regex).getObj();
				result.addAll(temp.get(0));
				errorResult.addAll(temp.get(1));
			}
		}
		return new 返回对象(真假.TRUE, re);
	}
	
	/**
	 * 获取文件的名称、大小、路径
	 * 
	 * @param item 文件
	 * @return 上诉文件信息；
	 */
	public static String getInfo(File item)
	{
		String fileName = item.getName();
		String fileSize = getFileSize(item.length());
		String filePath = item.getParent();
		String lastModified = 时间.getSdf().format(new Date(item.lastModified()));
		return fileName + " " + fileSize + " " + filePath + "  " + lastModified;
	}
	
	/**
	 * 返回文件大小的格式化后的字符串
	 * @param length 文件的大小：单位字节；
	 * @return
	 */
	private static String getFileSize(long size)
	{
		if (size < 1024) {
			return size + "字节";
		}
		String[] 单位 = { "K", "M", "G", "T" };
		return 递归求余数(size / 1024, 单位, 0);// 大于一个k，忽略k以下的
	}
	
	/**
	 * 返回文件大小（在每个单位上）
	 * @param size 文件大小：单位字节
	 * @param 单位 单位数组
	 * @param index 第几个单位
	 * @return
	 */
	private static String 递归求余数(long size, String[] 单位, int index)
	{
		if (size < 1024 || index > 2) {// 当剩余的字节不足1024 或者 已经达到T了。
			return size + 单位[index];
		}
		int remainder = (int) (size % 1024);
		long quotient = size / 1024;
		return 递归求余数(quotient, 单位, index + 1) + remainder + 单位[index];
	}
	
	private static String tanslateString(String regex)
	{
		return regex.replace("*", "[\\s\\S]*");
	}
}
