package wang.w2017年2月14日.Stream;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import 常量.真假;
import 常量.返回对象;


/**
 * @author 王必伟
 *
 * @date 2017年2月14日
 *
 * @tags
 */
public class StreamStudy
{
	public static void main(String[] args)
	{
		System.out.println("我就试下还能上传不？");
	}
	
	public static String 从键盘输入()
	{
		System.out.print("输入:");
		// BufferedReader bufRead = new BufferedReader(new
		// InputStreamReader(System.in));//(方式1)
		DataInputStream bufRead = new DataInputStream(System.in);// (方式2)
		StringBuilder result = new StringBuilder();
		String temp = null;
		try
		{
			while ((temp = bufRead.readLine()) != null)
			{
				result.append(temp).append("\n");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * 复制或者剪切二进制文件
	 * @param str 源文件
	 * @param goal 目标文件
	 * @param type 剪切还是复制
	 * @return
	 */
	public static 返回对象 CopyOrCutBinaryFile(String str, String goal, String type)
	{
		return CopyOrCutBinaryFile(new File(str), new File(goal), type);
	}
	
	/**
	 * 复制或者剪切二进制文件
	 * @param strFile 源文件
	 * @param goleFile 目标文件
	 * @param type 剪切还是复制
	 * @return
	 */
	public static 返回对象 CopyOrCutBinaryFile(File strFile, File goleFile, String type)
	{
		if (!strFile.exists())
		{
			return new 返回对象(真假.FLASE, "源文件不存在!");
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try
		{
			bis = new BufferedInputStream(new FileInputStream(strFile));
			bos = new BufferedOutputStream(new FileOutputStream(goleFile, true));
			byte[] temp = new byte[1024];
			while (bis.read(temp) != -1)
			{
				bos.write(temp);
			}
		} catch (IOException e)
		{
			return new 返回对象(真假.FLASE, "读取文件错误!");
		} finally
		{
			try
			{
				bis.close();
				bos.close();
			} catch (IOException e)
			{
				return new 返回对象(真假.FLASE, "关闭文件流错误!");
			}
		}
		if ("cut".equalsIgnoreCase(type))
		{
			strFile.delete();
		}
		return new 返回对象(真假.TRUE, "复制成功！");
	}
	
	/**
	 * 复制或者剪切文本文件 已实现： 未实现：
	 * @param str
	 * @param goal
	 * @param type 类型
	 * @return
	 */
	public static 返回对象 CopyOrCutFile(String str, String goal, String type)
	{
		
		return CopyOrCutFile(new File(str), new File(goal), type);
		
	}
	
	/**
	 * 复制或者剪切文本文件 已实现： 未实现：
	 * @param strFile
	 * @param goalFile
	 * @return
	 */
	public static 返回对象 CopyOrCutFile(File strFile, File goalFile, String type)
	{
		if (!strFile.exists())
		{
			return new 返回对象(真假.FLASE, "文件不存在！");
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
		try
		{
			// br = new BufferedReader(new FileReader(strFile));//方法1
			// bw = new BufferedWriter(new FileWriter(goalFile, true));//方法1
			br = new BufferedReader(new InputStreamReader(new FileInputStream(strFile)));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(goalFile, true)));
			String temp = null;
			while ((temp = br.readLine()) != null)
			{
				bw.write(temp);
				bw.newLine();
			}
		} catch (Exception e)
		{
			return new 返回对象(真假.FLASE, "复制出错");
		} finally
		{
			try
			{
				br.close();
				bw.close();
			} catch (IOException e)
			{
				return new 返回对象(真假.FLASE, "关闭输出或者输入了出错！");
			}
			
		}
		if ("cut".equalsIgnoreCase(type))
		{
			strFile.delete();
		}
		return new 返回对象(真假.TRUE, "复制文件成功");
	}
	
	/**
	 * 将str写入文件（）
	 * @param path 文件路径
	 * @param str 写入内容
	 * @param 是否追加 true:放在文件末尾
	 * @return
	 */
	public static 返回对象 写入文本文件(String path, String str, boolean 是否追加)
	{
		return 写入文本文件(new File(path), str, 是否追加);
	}
	
	/**
	 * 写入文件（）
	 * @param file
	 * @param str
	 * @param 是否追加 true:放在文件末尾
	 * @return
	 */
	public static 返回对象 写入文本文件(File file, String str, boolean 是否追加)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(file, 是否追加));
			bw.write(str);
			bw.newLine();
			bw.flush();
		} catch (IOException e)
		{
			return new 返回对象(真假.FLASE, "打开文件出错！");
		} finally
		{
			try
			{
				bw.close();
			} catch (IOException e)
			{
				return new 返回对象(真假.FLASE, "关闭BufferedWriter出错！");
			}
		}
		return new 返回对象(真假.TRUE, "写入成功");
	}
	
	/**
	 * 读取文本文档
	 * @param path 文件路径
	 * @return
	 */
	public static 返回对象 读取文本文件(String path)
	{
		return 读取文本文件(new File(path));
	}
	
	/**
	 * 读取文本文档
	 * @param file 文件
	 * @return
	 */
	public static 返回对象 读取文本文件(File file)
	{
		StringBuilder result = new StringBuilder();
		if (!file.exists())
		{
			return new 返回对象(真假.FLASE, "文件不存在");
		}
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));// 方法1
			// br = new BufferedReader(new FileReader(file));//方法2，不能调节编码格式
			String temp;
			while ((temp = br.readLine()) != null)
			{
				result.append(temp).append("\n");
			}
		} catch (FileNotFoundException e)
		{
			return new 返回对象(真假.FLASE, "文件未找到！");
		} catch (IOException e)
		{
			return new 返回对象(真假.FLASE, "文件读取错误！");
		} finally
		{
			try
			{
				br.close();
			} catch (IOException e)
			{
				return new 返回对象(真假.FLASE, "bufferedReader关闭错误！");
			}
		}
		return new 返回对象(真假.TRUE, result.toString());
	}
}
