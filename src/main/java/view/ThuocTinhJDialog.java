/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import excel.Excel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.SanPham;
import model.ThuocTinh;
import reponsitory.ThuocTinhRepository;
import util.ExceptionCus;
import util.MsgHelper;

/**
 *
 * @author Admin
 */
public class ThuocTinhJDialog extends javax.swing.JDialog {

    String loaiTT;
    List<ThuocTinh> list = new ArrayList<>();
    ThuocTinhRepository thuocTinhRepository = new ThuocTinhRepository();
    SanPhamJPanel spjp = new SanPhamJPanel();

    int row = -1;

    /**
     * Creates new form ThuocTinhJDialog
     */
    public ThuocTinhJDialog(java.awt.Frame parent, boolean modal, String loaiThuocTinh) {
        super(parent, modal);
        initComponents();
        setTitle("Quản lý thuộc tính");
        setLocationRelativeTo(null);
        loaiTT = loaiThuocTinh;
        list = thuocTinhRepository.getAll(loaiTT);
        showData(list);
        setForm();
        defaultForm();
        statusEdit();

        tbThuocTinh.getColumnModel().getColumn(3).setMinWidth(0);
        tbThuocTinh.getColumnModel().getColumn(3).setMaxWidth(0);
        tbThuocTinh.getColumnModel().getColumn(3).setPreferredWidth(0);
    }

    public void excel(JTable tb) {
        try {
            Excel.outExcel((DefaultTableModel) tb.getModel());
            MsgHelper.alert(this, "Thành công");
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException();
        }
    }

    void statusEdit() {
        boolean edit = (row >= 0);
        System.out.println(edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
    }

    void exit() {
        if (MsgHelper.confirm(this, "Đóng tab này nhé?")) {
            spjp.initCbb();
            System.out.println(spjp);
            this.dispose();
            spjp.initCbb();
        }
    }

    void search() {
        try {
            String value = tfTimKiemTT.getText();
            list = thuocTinhRepository.getByName(loaiTT, value);
            showData(list);
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    ThuocTinh getForm() {
        ThuocTinh tt = new ThuocTinh();
        tt.setTen(tfTen.getText());
        if (rdHoatDong.isSelected()) {
            tt.setTrangThai("Hoạt động");
        } else {
            tt.setTrangThai("Ngưng hoạt động");
        }
        return tt;
    }

    void clearForm() {
        row = -1;
        ThuocTinh tt = new ThuocTinh();
        tt.setTrangThai("Hoạt động");
        tbThuocTinh.clearSelection();
        showDetail(tt);
        this.statusEdit();
    }

    void add() {
        if (MsgHelper.confirm(this, "Thêm thuộc tính này nhớ?")) {
            try {
                ThuocTinh tt = this.getForm();
                ExceptionCus.CheckEmpty(tt.getTen());
                thuocTinhRepository.add(loaiTT, tt);
                MsgHelper.alert(this, "Thêm thuộc tính thành công");
                list = thuocTinhRepository.getAll(loaiTT);
                showData(list);
                clearForm();
                statusEdit();
            } catch (Exception e) {
                MsgHelper.alert(this, "Thêm thuộc tính thất bại");
                throw new RuntimeException(e);
            }
        }
    }

    void update() {
        if (MsgHelper.confirm(this, "Sửa thuộc tính này nhớ?")) {
            try {
                ThuocTinh tt = this.getForm();
                String id = String.valueOf(tbThuocTinh.getValueAt(tbThuocTinh.getSelectedRow(), 3));
                ExceptionCus.CheckEmpty(tt.getTen());
                thuocTinhRepository.update(loaiTT, tt, id);
                MsgHelper.alert(this, "Cập nhật thuộc tính thành công");
                list = thuocTinhRepository.getAll(loaiTT);
                showData(list);
                clearForm();
                statusEdit();
            } catch (Exception e) {
                MsgHelper.alert(this, "Cập nhật thuộc tính thất bại");
                throw new RuntimeException(e);
            }
        }
    }

    ThuocTinh getTTByRow() {
        row = tbThuocTinh.getSelectedRow();
        String id = String.valueOf(tbThuocTinh.getValueAt(row, 3));
        ThuocTinh tt = thuocTinhRepository.getByID(loaiTT, id);
        return tt;
    }

    void defaultForm() {
        if (tbThuocTinh.getRowCount() > 0) {
            row = 0;
            tbThuocTinh.setRowSelectionInterval(row, row);
            String id = String.valueOf(tbThuocTinh.getValueAt(row, 3));
            ThuocTinh tt = thuocTinhRepository.getByID(loaiTT, id);
            showDetail(tt);
        }
    }

    void showDetail(ThuocTinh tt) {
        tfTen.setText(tt.getTen());
        rdHoatDong.setSelected(tt.getTrangThai().equals("Hoạt động") ? true : false);
        rdNgungHD.setSelected(!tt.getTrangThai().equals("Hoạt động") ? true : false);
        this.statusEdit();
    }

    void setForm() {
        switch (loaiTT) {
            case "thuong_hieu":
                rdThuongHIeu.setSelected(true);
                break;
            case "the_loai":
                rdTheLoai.setSelected(true);
                break;
            case "thiet_ke":
                rdThietKe.setSelected(true);
                break;
            case "phong_cach":
                rdPhongCach.setSelected(true);
                break;
            case "co_ao":
                rdCoAo.setSelected(true);
                break;
            case "chat_lieu":
                rdChatLieu.setSelected(true);
                break;
            case "kich_thuoc":
                rdKichThuoc.setSelected(true);
                break;
            case "mau_sac":
                rdMauSac.setSelected(true);
                break;
            case "nha_san_xuat":
                rdNSX.setSelected(true);
                break;
            default:
                throw new AssertionError();
        }
    }

    void showData(List<ThuocTinh> list) {
        int index = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbThuocTinh.getModel();
        model.setRowCount(0);
        for (ThuocTinh thuocTinh : list) {
            model.addRow(new Object[]{
                index++,
                thuocTinh.getTen(),
                thuocTinh.getTrangThai(),
                thuocTinh.getId()
            });
        }
    }

    void actionRdo(String value) {
        loaiTT = value;
        list = thuocTinhRepository.getAll(loaiTT);
        showData(list);
        clearForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbThuocTinh = new javax.swing.JTable();
        tfTimKiemTT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rdThuongHIeu = new javax.swing.JRadioButton();
        rdTheLoai = new javax.swing.JRadioButton();
        rdThietKe = new javax.swing.JRadioButton();
        rdPhongCach = new javax.swing.JRadioButton();
        rdCoAo = new javax.swing.JRadioButton();
        rdChatLieu = new javax.swing.JRadioButton();
        rdKichThuoc = new javax.swing.JRadioButton();
        rdNSX = new javax.swing.JRadioButton();
        rdMauSac = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tfTen = new javax.swing.JTextField();
        rdHoatDong = new javax.swing.JRadioButton();
        rdNgungHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách thuộc tính"));

        tbThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Trạng thái", "ID"
            }
        ));
        tbThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbThuocTinh);

        tfTimKiemTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemTTKeyTyped(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý thuộc tính"));

        buttonGroup2.add(rdThuongHIeu);
        rdThuongHIeu.setText("Thương hiệu");
        rdThuongHIeu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdThuongHIeuActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdTheLoai);
        rdTheLoai.setText("Thể loại");
        rdTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTheLoaiActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdThietKe);
        rdThietKe.setText("Thiết kế");
        rdThietKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdThietKeActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdPhongCach);
        rdPhongCach.setText("Phong cách");
        rdPhongCach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPhongCachActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdCoAo);
        rdCoAo.setText("Cổ áo");
        rdCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCoAoActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdChatLieu);
        rdChatLieu.setText("Chất liệu");
        rdChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChatLieuActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdKichThuoc);
        rdKichThuoc.setText("Kích thước");
        rdKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKichThuocActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdNSX);
        rdNSX.setText("Nhà sản xuất");
        rdNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNSXActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdMauSac);
        rdMauSac.setText("Màu sắc");
        rdMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdCoAo)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdThuongHIeu)
                            .addComponent(rdTheLoai)
                            .addComponent(rdThietKe)
                            .addComponent(rdPhongCach))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdChatLieu)
                            .addComponent(rdKichThuoc)
                            .addComponent(rdNSX)
                            .addComponent(rdMauSac))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdThuongHIeu)
                        .addGap(29, 29, 29)
                        .addComponent(rdTheLoai)
                        .addGap(29, 29, 29)
                        .addComponent(rdThietKe)
                        .addGap(29, 29, 29)
                        .addComponent(rdPhongCach))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdChatLieu)
                        .addGap(29, 29, 29)
                        .addComponent(rdKichThuoc)
                        .addGap(29, 29, 29)
                        .addComponent(rdNSX)
                        .addGap(29, 29, 29)
                        .addComponent(rdMauSac)))
                .addGap(29, 29, 29)
                .addComponent(rdCoAo)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setText("Tên");

        jLabel2.setText("Trạng thái");

        buttonGroup1.add(rdHoatDong);
        rdHoatDong.setSelected(true);
        rdHoatDong.setText("Hoạt động");

        buttonGroup1.add(rdNgungHD);
        rdNgungHD.setText("Ngưng hoạt động");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton5.setText("Xuất file");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tìm kiếm theo tên");

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfTen))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdHoatDong)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdNgungHD)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(btnMoi)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfTimKiemTT))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(jSeparator1))))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(27, 27, 27)
                        .addComponent(btnSua)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTimKiemTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnMoi))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdHoatDong)
                    .addComponent(rdNgungHD)
                    .addComponent(jButton5))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfTimKiemTTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemTTKeyTyped
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_tfTimKiemTTKeyTyped

    private void tbThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocTinhMouseClicked
        // TODO add your handling code here:
        showDetail(getTTByRow());
    }//GEN-LAST:event_tbThuocTinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void rdThuongHIeuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThuongHIeuActionPerformed
        // TODO add your handling code here:
        actionRdo("thuong_hieu");
    }//GEN-LAST:event_rdThuongHIeuActionPerformed

    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        actionRdo("chat_lieu");
    }//GEN-LAST:event_rdChatLieuActionPerformed

    private void rdTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTheLoaiActionPerformed
        // TODO add your handling code here:
        actionRdo("the_loai");
    }//GEN-LAST:event_rdTheLoaiActionPerformed

    private void rdKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKichThuocActionPerformed
        // TODO add your handling code here:
        actionRdo("kich_thuoc");
    }//GEN-LAST:event_rdKichThuocActionPerformed

    private void rdThietKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThietKeActionPerformed
        // TODO add your handling code here:
        actionRdo("thiet_ke");
    }//GEN-LAST:event_rdThietKeActionPerformed

    private void rdNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNSXActionPerformed
        // TODO add your handling code here:
        actionRdo("nha_san_xuat");
    }//GEN-LAST:event_rdNSXActionPerformed

    private void rdPhongCachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPhongCachActionPerformed
        // TODO add your handling code here:
        actionRdo("phong_cach");
    }//GEN-LAST:event_rdPhongCachActionPerformed

    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed
        // TODO add your handling code here:
        actionRdo("mau_sac");
    }//GEN-LAST:event_rdMauSacActionPerformed

    private void rdCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCoAoActionPerformed
        // TODO add your handling code here:
        actionRdo("co_ao");
    }//GEN-LAST:event_rdCoAoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        excel(tbThuocTinh);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdCoAo;
    private javax.swing.JRadioButton rdHoatDong;
    private javax.swing.JRadioButton rdKichThuoc;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JRadioButton rdNSX;
    private javax.swing.JRadioButton rdNgungHD;
    private javax.swing.JRadioButton rdPhongCach;
    private javax.swing.JRadioButton rdTheLoai;
    private javax.swing.JRadioButton rdThietKe;
    private javax.swing.JRadioButton rdThuongHIeu;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField tfTen;
    private javax.swing.JTextField tfTimKiemTT;
    // End of variables declaration//GEN-END:variables
}
