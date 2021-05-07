package CustomerSystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class CardLayoutTest extends JFrame {
    Container contentPane;
   
    //생성자 구현
    public CardLayoutTest() {
          setTitle("카드레이아웃 테스트");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
          contentPane = getContentPane();
          contentPane.add(new West(), BorderLayout.NORTH);
          setBounds(1000, 300, 280, 300);
          setVisible(true);
    }
   
    //내부 클래스 구현
    class West extends JPanel implements ActionListener{

          JPanel p = new JPanel();       //신상정보카드를 만들기 위한 JPanel
          JPanel search = new JPanel();  //검색카드를 만들기 위한 JPanel
          CardLayout card;
         
          JLabel ageLabel = new JLabel();
          JLabel sexLabel = new JLabel();
          JLabel chulLabel = new JLabel();
          JLabel birthLabel = new JLabel();
         
          //생성자 구현
          public West() {
                card = new CardLayout();
                setLayout(card);
                //신상정보 카드 만들기
                p.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "신상정보"));
                p.setLayout(new GridLayout(4, 2, 10, 5));
               
                p.add(new JLabel("나이: ", JLabel.LEFT));
                p.add(ageLabel);
               
                p.add(new JLabel("성별: ", JLabel.LEFT));
                p.add(sexLabel);
               
                p.add(new JLabel("출신지역: ", JLabel.LEFT));
                p.add(chulLabel);
               
                p.add(new JLabel("생년월일: ", JLabel.LEFT));
                p.add(birthLabel);
               
                p.setPreferredSize(new Dimension(260, 210));
               
                add(p, "첫번째 카드");
               
                JButton btn = new JButton("검색");
                contentPane.add(btn, BorderLayout.SOUTH);
                btn.addActionListener(this);
               
                //검색카드 만들기
                search.setBorder(new TitledBorder(new LineBorder(Color.pink, 2), "검색"));
               
                ButtonGroup group = new ButtonGroup();
                JRadioButton searchRadio[] = new JRadioButton[3];
                String search_name[] = {"이름","직업","출신지역"};
                JTextField jf = new JTextField(10);
                JButton search_btn = new JButton("찾기");
                JButton exit_btn = new JButton("나가기");
               
                //사용자 자유배치 Layout 하는 방법
                search.setLayout(null);
                int x = -70;
                //앞 카드와 크기가 똑같아야 교환할 때 바뀌는 느낌을 줌
                search.setPreferredSize(new Dimension(260, 210));
               
                for(int i=0; i<searchRadio.length; i++){
                       searchRadio[i] = new JRadioButton(search_name[i]);
                       //반복문 돌릴 때, x 값이 변경되면서 searchRadio 버튼위치를 조정해주기 위해
                      //밖에 -70 선언해준 것
                       searchRadio[i].setBounds(x += 80,30,80,30);
                      
                       group.add(searchRadio[i]);
                       search.add(searchRadio[i]);
                }
                jf.setBounds(25, 80, 200, 30);
                search_btn.setBounds(25, 130, 70, 30);
                exit_btn.setBounds(115, 130, 110, 30);
                search.add(jf);
                search.add(search_btn);
                search.add(exit_btn);
               
                add(search, "두번째 카드");
               
                exit_btn.addActionListener(this);
          }
         
          @Override
          public void actionPerformed(ActionEvent e) {
                card.next(this);
          }          
    }

    public static void main(String[] args) {
          new CardLayoutTest();
    }
}