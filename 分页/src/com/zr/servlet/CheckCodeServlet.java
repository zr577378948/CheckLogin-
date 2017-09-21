package com.zr.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckCodeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Random r = new Random();
		// 1.确定图片的大小
		int w = 100;
		int h = 30;
		// 2.创建画板
		BufferedImage board = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		// 3.创建画笔
		Graphics pen = board.getGraphics();
		// 4.调制颜色
		Color c_bg = new Color(200 + r.nextInt(40), 200 + r.nextInt(40), 200 + r.nextInt(40));
		// 5.给画笔注入当前的背景要绘制的颜色
		pen.setColor(c_bg);
		// 6.画背景
		pen.fillRect(0, 0, w, h);

		String judgeCode = "";
		for (int i = 1; i < 5; i++) {
			// 生成一位随机验证码
			// 7.生成验证码
			// 改变画笔的颜色，防止与背景颜色相同
			Color c_code = new Color(100 + r.nextInt(40), 100 + r.nextInt(40), 100 + r.nextInt(40));
			// 给画笔注入验证码的颜色
			pen.setColor(c_code);
			// 设置画笔所画出的字体风格
			Font f_code = new Font("斜体", Font.ITALIC, 18);
			// 将字体风格注入笔中
			pen.setFont(f_code);
			// 随机生成一个大写字母Unicode(ASCII码)
			int upcaseCode = 65 + r.nextInt(26);
			// 随机生成一个小写字母的Unicode
			int lowcaseCode = 97 + r.nextInt(26);
			// 随机生成数字
			int numCode = r.nextInt(10);

			// 转化成具体的字符串，字符串可以追加起来
			// 将字母所对应的码转化成相应的字母
			String upcaseStr = String.valueOf((char) upcaseCode);
			String lowcaseStr = String.valueOf((char) lowcaseCode);
			String numStr = String.valueOf(numCode);
			// n1与n2的值是0或者1
			int n1 = r.nextInt(2);
			int n2 = r.nextInt(2);
			String code = "";
			if (n1 > 0.5) {
				// 生成的是字母
				// 生成大写字母
				if (n2 > 0.5) {
					code = upcaseStr;
					judgeCode = judgeCode + upcaseStr;
				} else {
					// 生成小写字母
					code = lowcaseStr;
					judgeCode = judgeCode + lowcaseStr;
				}
			} else {
				// 生成数字
				code = numStr;
				judgeCode = judgeCode + numStr;
			}

			request.getSession().setAttribute("judgeCode",judgeCode);
			System.out.println(judgeCode);
			
			// 画验证码(20*i, 20是验证码的位置)
			pen.drawString(code, 20*i, 20);
		}
		
//		压缩成图片，做成响应输出
		ImageIO.write(board, "jpg", response.getOutputStream());
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
