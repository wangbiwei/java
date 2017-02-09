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
	 * @param file 要遍历的文件夹
	 */
	public static void 遍历文件夹(File file)
	{
		File[] list = file.listFiles();
	}
	
	/**
	 * 遍历文件夹
	 * @param path 文件夹路径
	 */
	public static void 遍历文件夹(String path)
	{
		遍历文件夹(new File(path));
	}
}
