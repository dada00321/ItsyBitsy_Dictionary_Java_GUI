import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI {
	static JFrame frm = new JFrame("Main_GUI");
	static Container ct = frm.getContentPane(); // 取得內容窗格物件
	static JTextField tf = new JTextField("");
	static JTextArea ta = new JTextArea("");
	static JButton btn = new JButton("Search");
	
	static class btnClickListener implements ActionListener {
		static HashMap<String, String> dict;
		// 可將文字資料分段(row)顯示，且每一列皆沒有被拆散的單字
		static String split_to_multiple_rows(int limit, String text_data) {
			String result = " ", text = text_data;
			int total, amt, tmp;
			while(text.indexOf(' ') != -1) {
				String[] slices = text.split(" ");
				total = 0;
				amt = 0;
				for(int i=0; i<slices.length; i++) {
					total += slices[i].length();
					tmp = total + i;
					if(tmp <= limit)
						amt = tmp;
					else
						break;
				}
				//System.out.println("amt: "+amt);
				//System.out.println(text.substring(0, amt));
				result += text.substring(0, amt) + "\n";
				text = text.substring(amt);
			}
			return result;
		}
		public void actionPerformed(ActionEvent e) {
			String path = "./res/Dictionary.txt";
			new DictReader();
			try {
				dict = DictReader.get_dict(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if(e.getSource() == btn) {
				String searching_word = tf.getText();
				if(dict.containsKey(searching_word)) {
					String explanation = dict.get(searching_word);
					int width_limit;
					width_limit = 38;  // each row can contain how many characters
					/*  若目前的 GUI 寬度不變，至多可容納 39 個字元
					 *  但 38 個字元較合適(不會跑版)。建議測試單字: "eclipse"
					 * */
					ta.setText(split_to_multiple_rows(width_limit, explanation));
				}
				else {
					ta.setText(" No descriptions about this!");
				}
			}
		}
	}
	
	public static void routine() {
		ct.setLayout(null);
		ct.add(btn);
		frm.setSize(600, 400);
	}
	
	public static void main(String[] args) {
		routine();
		tf.setBounds(25, 30, 420, 30);
		btn.setBounds(480, 25, 80, 40);	// setBounds: 設定元件位置與大小
		ta.setBounds(25, 95, 530, 230);
		
		ct.setBackground(Color.cyan);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Font f = new Font("serif", Font.PLAIN, 30);
		tf.setFont(f);
		ta.setFont(f);
		
		ct.add(btn);
		ct.add(tf);
		ct.add(ta);
		btn.addActionListener(new btnClickListener());
	}
}
