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
   
    //������ ����
    public CardLayoutTest() {
          setTitle("ī�巹�̾ƿ� �׽�Ʈ");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
          contentPane = getContentPane();
          contentPane.add(new West(), BorderLayout.NORTH);
          setBounds(1000, 300, 280, 300);
          setVisible(true);
    }
   
    //���� Ŭ���� ����
    class West extends JPanel implements ActionListener{

          JPanel p = new JPanel();       //�Ż�����ī�带 ����� ���� JPanel
          JPanel search = new JPanel();  //�˻�ī�带 ����� ���� JPanel
          CardLayout card;
         
          JLabel ageLabel = new JLabel();
          JLabel sexLabel = new JLabel();
          JLabel chulLabel = new JLabel();
          JLabel birthLabel = new JLabel();
         
          //������ ����
          public West() {
                card = new CardLayout();
                setLayout(card);
                //�Ż����� ī�� �����
                p.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�Ż�����"));
                p.setLayout(new GridLayout(4, 2, 10, 5));
               
                p.add(new JLabel("����: ", JLabel.LEFT));
                p.add(ageLabel);
               
                p.add(new JLabel("����: ", JLabel.LEFT));
                p.add(sexLabel);
               
                p.add(new JLabel("�������: ", JLabel.LEFT));
                p.add(chulLabel);
               
                p.add(new JLabel("�������: ", JLabel.LEFT));
                p.add(birthLabel);
               
                p.setPreferredSize(new Dimension(260, 210));
               
                add(p, "ù��° ī��");
               
                JButton btn = new JButton("�˻�");
                contentPane.add(btn, BorderLayout.SOUTH);
                btn.addActionListener(this);
               
                //�˻�ī�� �����
                search.setBorder(new TitledBorder(new LineBorder(Color.pink, 2), "�˻�"));
               
                ButtonGroup group = new ButtonGroup();
                JRadioButton searchRadio[] = new JRadioButton[3];
                String search_name[] = {"�̸�","����","�������"};
                JTextField jf = new JTextField(10);
                JButton search_btn = new JButton("ã��");
                JButton exit_btn = new JButton("������");
               
                //����� ������ġ Layout �ϴ� ���
                search.setLayout(null);
                int x = -70;
                //�� ī��� ũ�Ⱑ �Ȱ��ƾ� ��ȯ�� �� �ٲ�� ������ ��
                search.setPreferredSize(new Dimension(260, 210));
               
                for(int i=0; i<searchRadio.length; i++){
                       searchRadio[i] = new JRadioButton(search_name[i]);
                       //�ݺ��� ���� ��, x ���� ����Ǹ鼭 searchRadio ��ư��ġ�� �������ֱ� ����
                      //�ۿ� -70 �������� ��
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
               
                add(search, "�ι�° ī��");
               
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