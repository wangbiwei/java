/**
 * @todo javaIo
 */
package wang.w2017年2月9日.File;


import java.io.File;


/**
 * @author 王必伟
 *
 * @date 2017年2月9日
 *
 * @tags
 */
public class FileStudy
{
	/**
	 * 遍历文件夹
	 * 
	 * @param file 要遍历的文件夹
	 */
	public static void 遍历文件夹(File file)
	{
		File[] list = file.listFiles();
		if (list == null) {
			System.err.println(file.getAbsolutePath());
			try {
				Thread.sleep(5000);// 让主线程休眠10秒，看哪些文件夹不可访问；
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		for (File item : list) {
			if (item.isFile()) {
				System.out.println(getInfo(item));
			} else if (item.isDirectory()) {
				遍历文件夹(item);
			}
		}
	}
	
	/**
	 * 遍历文件夹
	 * 
	 * @param path 文件夹路径
	 */
	public static void 遍历文件夹(String path)
	{
		遍历文件夹(new File(path));
	}
	
	/**
	 * 获取文件的名称、大小、路径
	 * 
	 * @param item 文件
	 * @return 上诉文件信息；
	 */
	private static String getInfo(File item)
	{
		String fileName = item.getName();
		String fileSize = getFileSize(item.length());
		String filePath = item.getParent();
		return fileName + " " + fileSize + " " + filePath;
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
		return 递归求余数(quotient, 单位, index++) + remainder + 单位[index];
	}
	
}
