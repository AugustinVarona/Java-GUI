package clase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.Array;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Login {
    File parole = new File("parole.csv");
    File detal = new File("detalii.csv");
    ArrayList<Date> d = new ArrayList<>();
    ArrayList<Detalii> de = new ArrayList<>();
    private JList<String> listt;
    static int j =0;

    Login(){
        if(!parole.exists())
        {
            try{
                parole.createNewFile();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(!detal.exists())
        {
            try{
                detal.createNewFile();
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        d = CitesteDinFisierP();
        de = CitesteDinFIsierD();
        JFrame f1 = new JFrame("Login");
        JFrame f2 = new JFrame("Inregistrare");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();
        JLabel l1 = new JLabel("Utilizator");
        JLabel l2 = new JLabel("Parola");
        JLabel l3 = new JLabel("Logare cu succes!");
        JLabel nume = new JLabel("Nume");
        JLabel parola = new JLabel("Parola");
        JLabel conf = new JLabel("Confirmare parola");
        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");
        JButton sign = new JButton("Inregistrare utilizator nou");
        JButton adaugare = new JButton("Adaugare utilizator");
        f1.setSize(400,400);
        f2.setSize(400,400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dimension.getWidth()/2);
        int y = (int) (dimension.getHeight()/2);
        f1.setLocation(x,y);
        f1.setVisible(true);
        f1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f1.dispose();
                System.exit(0);
            }
        });
        l3.setBounds(110,200,100,20);
        l3.setVisible(false);
        t1.setBounds(110,100,150,30);
        t2.setBounds(110,160,150,30);
        l1.setBounds(110,80,150,20);
        l2.setBounds(110,140,150,20);
        login.setBounds(30,250,150,40);
        cancel.setBounds(200,250,150,40);
        sign.setBounds(65,300,250,40);
        f1.setLayout(null);
        f1.add(t1);
        f1.add(t2);
        f1.add(l1);
        f1.add(l2);
        f1.add(login);
        f1.add(cancel);
        f1.add(l3);
        f1.add(sign);
        f2.setLocation(x,y);
        f2.setLayout(null);
        f1.setResizable(false);
        f2.setResizable(false);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                System.exit(0);
            }
        });
        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(true);
                f1.dispose();
            }
        });
        f2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f2.dispose();
                System.exit(0);
            }
        });
        nume.setBounds(30,30,100,20);
        t3.setBounds(30,50,150,30);
        t4.setBounds(200,170,150,30);
        parola.setBounds(200,150,100,20);
        t5.setBounds(110,230,150,30);
        conf.setBounds(110,210,150,20);
        adaugare.setBounds(110,280,150,30);
        f2.add(nume);
        f2.add(parola);
        f2.add(conf);
        f2.add(t3);
        f2.add(t4);
        f2.add(t5);
        JTextField prenume = new JTextField();
        JTextField varsta = new JTextField();
        JTextField telefon = new JTextField();
        JTextField adresa = new JTextField();
        JLabel prenumel = new JLabel("Prenume");
        JLabel varstal = new JLabel("Varsta");
        JLabel telefonl = new JLabel("Telefon");
        JLabel adresal = new JLabel("Adresa");
        prenume.setBounds(200,50,150,30);
        prenumel.setBounds(200,30,100,20);
        varstal.setBounds(30,90,100,20);
        varsta.setBounds(30,110,150,30);
        telefonl.setBounds(200,90,100,20);
        telefon.setBounds(200,110,150,30);
        adresal.setBounds(30,150,100,20);
        adresa.setBounds(30,170,150,30);
        f2.add(adaugare);
        f2.add(prenume);
        f2.add(prenumel);
        f2.add(varsta);
        f2.add(varstal);
        f2.add(telefon);
        f2.add(telefonl);
        f2.add(adresa);
        f2.add(adresal);
        adaugare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = t3.getText();
                String prenum = prenume.getText();
                String parola = t4.getText();
                String conf = t5.getText();
                String adres = adresa.getText();
                int tel = Integer.parseInt(telefon.getText());
                int var = Integer.parseInt(varsta.getText());
                if(parola.equals(conf) == false)
                {
                    JOptionPane.showMessageDialog(f2,"Parola nu corespunde");
                    System.exit(0);
                }
                Date date =new Date(nume,prenum,parola,hashCode());
                Add(date);
                for(Detalii detalii : de)
                {
                    if(detalii.nume.equals(nume) && detalii.prenume.equals(prenum) && detalii.parola.equals(parola))
                    {
                        JOptionPane.showMessageDialog(f2,"Utilizatorul deja exista!");
                        f2.dispose();
                        System.exit(0);
                    }
                }
                Detalii detalii = new Detalii(hashCode(),nume,prenum,parola,var,tel,adres);
                Addd(detalii);
                ScrieInFisier();
                JOptionPane.showMessageDialog(f2,"Utilizator inregistrat cu succes!");
                f2.dispose();
                f1.setVisible(true);
                t3.setText("");
                t4.setText("");
                t5.setText("");
                prenume.setText("");
                adresa.setText("");
                telefon.setText("");
                varsta.setText("");
            }
        });
        JFrame frame = new JFrame();
        JButton logout = new JButton("Log out");
        logout.setBounds(110,300,150,30);
        frame.add(logout);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.setSize(400,400);
        frame.setLocation(x,y);
        DefaultListModel<String> list = new DefaultListModel<>();

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f1.setVisible(true);
                list.clear();
                listt = new JList<>(list);
                frame.add(listt);
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String par = t2.getText();
                String num = t1.getText();
                boolean r = false;
                for(Date date : d)
                {
                    if(par.equals(date.parola) && num.equals(date.nume + " " + date.prenume))
                        r = true;
                }
                System.out.println(r);
                if (r == true) {
                    frame.setVisible(true);
                    f1.dispose();
                    j = 0;
                    for (Detalii det : de) {
                        for(Date dat : d)
                        {if(par.equals(dat.parola) && num.equals(dat.nume + " " + dat.prenume))
                        {
                            if (det.hash == dat.hash) {
                                list.addElement("Id:" + String.valueOf(det.hash));
                                list.addElement("Nume:" + det.nume);
                                list.addElement("Prenume:" + det.prenume);
                                list.addElement("Parola:" + det.parola);
                                list.addElement("Varsta:" + String.valueOf(det.varsta));
                                list.addElement("Telefon:" + String.valueOf(det.telefon));
                                list.addElement("Adresa:" + det.adresa);
                                listt = new JList<>(list);
                                listt.setBounds(30, 30, 270, 200);
                                frame.add(listt);
                            }
                        }
                        }
                    }
                } else {
                    j++;

                } t1.setText("");
                t2.setText("");
                if (j == 1) {
                    JOptionPane.showMessageDialog(f1, "Eroare! Mai aveti doua incercari!");
                } else if (j == 2) {
                    JOptionPane.showMessageDialog(f1, "Eroare! Mai veti o incercare!");
                } else if (j == 3) {
                    JOptionPane.showMessageDialog(f1, "Eroare! Nu mai aveti incercari!");
                    System.exit(0);
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }
    public void ScrieInFisier()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(parole));
            BufferedWriter bww = new BufferedWriter(new FileWriter(detal));
        try{
            bw.write("Nume , Prenume ,  Parola , Id\r\n");
            bww.write("ID , Nume , Prenume , Varsta , Telefon , Adresa\r\n");
            for(Date a : d)
            {
                bw.write(a.toString() + " \r\n");
            }
            for(Detalii b : de)
            {
                bww.write(b.toString() +  "\r\n");
            }
            bw.flush();
            bww.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            bw.close();
            bww.close();
        }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Date> CitesteDinFisierP()
    {
        ArrayList<Date> date = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(parole));
            String line = br.readLine();
            if(line != null)
                line = br.readLine();
            while(line != null)
            {
                String[] split = line.split(",");
                Date date1 = new Date(split[0].trim(),split[1].trim(),split[2].trim(),Integer.parseInt(split[3].trim()));
                date.add(date1);
                line = br.readLine();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    public ArrayList<Detalii> CitesteDinFIsierD()
    {
        ArrayList<Detalii> detalii = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(detal));
            String line = br.readLine();
            if(line != null)
                line = br.readLine();
            while(line != null)
            {
                String[] split = line.split(",");
                Detalii detalii1 = new Detalii(Integer.parseInt(split[0].trim()),split[1].trim(),split[2].trim(), split[3].trim(),Integer.parseInt(split[4].trim()),Integer.parseInt(split[5].trim()),split[6].trim());
                detalii.add(detalii1);
                line = br.readLine();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return detalii;
    }
    public void Add(Date date)
    {
        int noualungime =d.size()+1;
        Date[] aux =new Date[noualungime];
        int index=0;
        for(Date a : d)
        {
            aux[index++] = a;
        }
        aux[index] =date;
        d.add(aux[index]);
    }
    public void Addd(Detalii detalii)
    {
        int noualungime = de.size()+1;
        Detalii[] aux = new Detalii[noualungime];
        int index = 0;
        for(Detalii a : de)
        {
            aux[index++] = a;
        }
        aux[index] = detalii;
        de.add(aux[index]);
    }
}