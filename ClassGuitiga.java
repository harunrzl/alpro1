import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class ClassGuitiga extends javax.swing.JFrame {

    
    public ClassGuitiga() {
        initComponents();
    }
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bukaText = new javax.swing.JButton();
        namaDepan = new javax.swing.JButton();
        namaBelakang = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        bukaText.setText("OPEN FILE");
        bukaText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukaTextActionPerformed(evt);
            }
        });

        namaDepan.setText("NAMA DEPAN");

        namaBelakang.setText("NAMA BELAKANG");

        jLabel1.setText("Daftar Nama");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bukaText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(namaDepan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(namaBelakang))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bukaText)
                    .addComponent(namaBelakang)
                    .addComponent(namaDepan))
                .addGap(16, 16, 16))
        );

        pack();
    }

    private void bukaTextActionPerformed(java.awt.event.ActionEvent evt) {

    int returnVal = jFileChooser1.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = jFileChooser1.getSelectedFile();
        try {
    
          jTextArea1.read( new FileReader( file.getAbsolutePath() ), null );
        } catch (IOException ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }
    public static void main(String[]adim101) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassGuitiga().setVisible(true);
            }
        });
    }
    private javax.swing.JButton bukaText;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton namaBelakang;
    private javax.swing.JButton namaDepan;
}
