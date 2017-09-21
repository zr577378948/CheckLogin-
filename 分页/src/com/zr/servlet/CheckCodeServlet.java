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
		// 1.ȷ��ͼƬ�Ĵ�С
		int w = 100;
		int h = 30;
		// 2.��������
		BufferedImage board = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		// 3.��������
		Graphics pen = board.getGraphics();
		// 4.������ɫ
		Color c_bg = new Color(200 + r.nextInt(40), 200 + r.nextInt(40), 200 + r.nextInt(40));
		// 5.������ע�뵱ǰ�ı���Ҫ���Ƶ���ɫ
		pen.setColor(c_bg);
		// 6.������
		pen.fillRect(0, 0, w, h);

		String judgeCode = "";
		for (int i = 1; i < 5; i++) {
			// ����һλ�����֤��
			// 7.������֤��
			// �ı仭�ʵ���ɫ����ֹ�뱳����ɫ��ͬ
			Color c_code = new Color(100 + r.nextInt(40), 100 + r.nextInt(40), 100 + r.nextInt(40));
			// ������ע����֤�����ɫ
			pen.setColor(c_code);
			// ���û�����������������
			Font f_code = new Font("б��", Font.ITALIC, 18);
			// ��������ע�����
			pen.setFont(f_code);
			// �������һ����д��ĸUnicode(ASCII��)
			int upcaseCode = 65 + r.nextInt(26);
			// �������һ��Сд��ĸ��Unicode
			int lowcaseCode = 97 + r.nextInt(26);
			// �����������
			int numCode = r.nextInt(10);

			// ת���ɾ�����ַ������ַ�������׷������
			// ����ĸ����Ӧ����ת������Ӧ����ĸ
			String upcaseStr = String.valueOf((char) upcaseCode);
			String lowcaseStr = String.valueOf((char) lowcaseCode);
			String numStr = String.valueOf(numCode);
			// n1��n2��ֵ��0����1
			int n1 = r.nextInt(2);
			int n2 = r.nextInt(2);
			String code = "";
			if (n1 > 0.5) {
				// ���ɵ�����ĸ
				// ���ɴ�д��ĸ
				if (n2 > 0.5) {
					code = upcaseStr;
					judgeCode = judgeCode + upcaseStr;
				} else {
					// ����Сд��ĸ
					code = lowcaseStr;
					judgeCode = judgeCode + lowcaseStr;
				}
			} else {
				// ��������
				code = numStr;
				judgeCode = judgeCode + numStr;
			}

			request.getSession().setAttribute("judgeCode",judgeCode);
			System.out.println(judgeCode);
			
			// ����֤��(20*i, 20����֤���λ��)
			pen.drawString(code, 20*i, 20);
		}
		
//		ѹ����ͼƬ��������Ӧ���
		ImageIO.write(board, "jpg", response.getOutputStream());
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
