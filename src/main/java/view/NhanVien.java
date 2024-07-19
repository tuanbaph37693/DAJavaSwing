/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.alibaba.excel.EasyExcel;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.NguoiDung;
import reponsitory.NguoiDungReponsitory;
import service.NguoiDungService;

public class NhanVien extends javax.swing.JPanel {

    private NguoiDungService nds = new NguoiDungService();
    private DefaultTableModel model = new DefaultTableModel();
    private int index;
    private ArrayList<NguoiDung> lst = new ArrayList<>();

    public NhanVien() throws Exception {
        initComponents();
        model = (DefaultTableModel) tblnhanvien.getModel();
        lst = nds.getAllNguoiDung();
        loatdata();
        String dateString = "Dec 14, 2023, 11:04:43 AM";

        // Định dạng cho chuỗi ngày tháng
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a");

        try {
            // Chuyển đổi chuỗi ngày tháng thành đối tượng Date
            java.util.Date date = inputFormat.parse(dateString);

            // Định dạng cho JDateChooser
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Tạo JDateChooser và đặt giá trị
            jclNgaySinh.setDate(date);

            // In ra màn hình giá trị của JDateChooser
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txttim.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchByMaNV();
            }
        });
    }

    private void searchByMaNV() {
        String searchMaNV = txttim.getText().trim();
        DefaultTableModel searchModel = new DefaultTableModel();
        searchModel = (DefaultTableModel) tblnhanvien.getModel();
        searchModel.setRowCount(0);
        for (NguoiDung nd : lst) {
            if (nd.getMaNguoidung().toLowerCase().contains(searchMaNV.toLowerCase())) {
                searchModel.addRow(new Object[]{
                    nd.getMaNguoidung(), nd.getHoTen(), nd.getGioitinh()==1?"Nam":"Nữ", nd.getMatKhau(), nd.getVaiTro() == 0 ? "Nhân viên" : "Quản lý", nd.getSoDienthoai(), nd.getNgaySinh(), nd.geteMail(), nd.getTrangThai()
                });
            }
        }
        tblnhanvien.setModel(searchModel);
    }

    private void loatdata() {
        model.setRowCount(0);
        for (NguoiDung nd : lst) {
            model.addRow(new Object[]{
                nd.getMaNguoidung(), nd.getHoTen(), nd.getGioitinh()==1?"Nam":"Nữ", nd.getMatKhau(), nd.getVaiTro() == 0 ? "Nhân viên" : "Quản lý", nd.getSoDienthoai(), nd.getNgaySinh(), nd.geteMail(), nd.getTrangThai()
            });
        }
    }

    private void hienthi() {
        int index = tblnhanvien.getSelectedRow();
        NguoiDung nv = lst.get(index);
        txtmanv.setText(tblnhanvien.getValueAt(index, 0).toString());
        txttennv.setText(tblnhanvien.getValueAt(index, 1).toString());
        cbogioitinh.setSelectedItem(nv.getGioitinh() == 1 ? "Nam" : "Nữ");
        txtmakhau.setText(tblnhanvien.getValueAt(index, 3).toString());
        int indexCbb = nv.getVaiTro() == 0 ? 0 : 1;
        cbovatro.setSelectedIndex(indexCbb);
        System.out.println(cbovatro.getSelectedItem());
        System.out.println(nv.getVaiTro());
        txtsdt.setText(tblnhanvien.getValueAt(index, 5).toString());
        String dateStringDB = tblnhanvien.getValueAt(index, 6).toString();
        setDateOnJDateChooser(jclNgaySinh, dateStringDB);
        txtemail.setText(tblnhanvien.getValueAt(index, 7).toString());
        String trangthai = tblnhanvien.getValueAt(index, 8).toString();
        if (trangthai.equals("Đang làm việc")) {
            rdodanglm.setSelected(true);
            rdonghiviec.setSelected(false);
        } else {
            rdodanglm.setSelected(false);
            rdonghiviec.setSelected(true);
        }
    }

    private void setDateOnJDateChooser(JDateChooser dateChooser, String dateString) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            dateChooser.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private NguoiDung FormNhanVienInput() {
        NguoiDung nd = new NguoiDung();
        nd.setMaNguoidung(txtmanv.getText());
        nd.setHoTen(txttennv.getText());
        if (cbogioitinh.getSelectedIndex() == 0) {
            nd.setVaiTro(0);
        } else {
            nd.setVaiTro(1);
        }
        nd.setMatKhau(txtmakhau.getText());
        if (cbovatro.getSelectedIndex() == 0) {
            nd.setVaiTro(0);
        } else {
            nd.setVaiTro(1);
        }
        nd.setSoDienthoai(txtsdt.getText());

        java.util.Date utilDate = jclNgaySinh.getDate();
        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            nd.setNgaySinh(sqlDate);
        }

        nd.seteMail(txtemail.getText());
        if (rdodanglm.isSelected()) {
            nd.setTrangThai("Đang làm việc");
        } else {
            nd.setTrangThai("Nghỉ việc");
        }
        return nd;
    }

    private void clearForm() {
        txtmanv.setText("");
        txttennv.setText("");
        cbogioitinh.setSelectedIndex(0);
        txtmakhau.setText("");
        cbovatro.setSelectedIndex(0);
        txtsdt.setText("");
        txtemail.setText("");
        rdodanglm.setSelected(true);
        rdonghiviec.setSelected(false);
    }

    private void loadTableData() {
        model.setRowCount(0);
        for (NguoiDung nguoiDung : lst) {
            model.addRow(new Object[]{
                nguoiDung.getMaNguoidung(), nguoiDung.getHoTen(), nguoiDung.getGioitinh(), nguoiDung.getMatKhau(),
                (nguoiDung.getVaiTro() == 0) ? "Nhân viên" : "Quản lý",
                nguoiDung.getSoDienthoai(), nguoiDung.getNgaySinh(),
                nguoiDung.geteMail(), nguoiDung.getTrangThai()
            });
        }
    }

    private void filterDataByRoleAndStatus(String selectedRole, String selectedStatus) {
        model.setRowCount(0);
        for (NguoiDung nguoiDung : lst) {
            boolean roleCondition = selectedRole.equals("Tất cả")
                    || (selectedRole.equals("Quản lý") && nguoiDung.getVaiTro() == 1)
                    || (selectedRole.equals("Nhân viên") && nguoiDung.getVaiTro() == 0);
            boolean statusCondition = selectedStatus.equals("Tất cả") || nguoiDung.getTrangThai().equals(selectedStatus);

            if (roleCondition && statusCondition) {
                model.addRow(new Object[]{
                    nguoiDung.getMaNguoidung(), nguoiDung.getHoTen(), nguoiDung.getGioitinh(), nguoiDung.getMatKhau(),
                    (nguoiDung.getVaiTro() == 1) ? "Quản lý" : "Nhân viên",
                    nguoiDung.getSoDienthoai(), nguoiDung.getNgaySinh(),
                    nguoiDung.geteMail(), nguoiDung.getTrangThai()
                });
            }
        }
    }

    private ArrayList<NguoiDung> filterByStatus(ArrayList<NguoiDung> originalList, String status) {
        ArrayList<NguoiDung> filteredList = new ArrayList<>();
        for (NguoiDung nd : originalList) {
            if (nd.getTrangThai().equalsIgnoreCase(status)) {
                filteredList.add(nd);
            }
        }
        return filteredList;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^0\\d{9,10}$";
        return phoneNumber.matches(regex);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txttennv = new javax.swing.JTextField();
        cbovatro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnlmmoi = new javax.swing.JButton();
        txtsdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdodanglm = new javax.swing.JRadioButton();
        rdonghiviec = new javax.swing.JRadioButton();
        jclNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        cbogioitinh = new javax.swing.JComboBox<>();
        txtmakhau = new javax.swing.JTextField();
        txtmanv = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        btnXuatDanhSachKhachHang = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cboloctheovt = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbonhanvien = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        txttim = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbovatro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản Lý" }));

        jLabel2.setText("MÃ NHÂN VIÊN  :");

        jLabel3.setText("TÊN NHÂN VIÊN");

        jLabel4.setText("MẬT KHẨU : ");

        jLabel5.setText("VAI TRÒ : ");

        btnthem.setBackground(new java.awt.Color(0, 0, 0));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(0, 0, 0));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsua.setForeground(new java.awt.Color(255, 255, 255));
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnlmmoi.setBackground(new java.awt.Color(0, 0, 0));
        btnlmmoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlmmoi.setForeground(new java.awt.Color(255, 255, 255));
        btnlmmoi.setText("Làm Mới");
        btnlmmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlmmoiActionPerformed(evt);
            }
        });

        jLabel7.setText("SỐ ĐIỆN THOẠI : ");

        jLabel8.setText("NGÀY SINH : ");

        jLabel9.setText("EMAIL : ");

        jLabel11.setText("TRẠNG THÁI  : ");

        buttonGroup1.add(rdodanglm);
        rdodanglm.setText("Đang Làm Việc");

        buttonGroup1.add(rdonghiviec);
        rdonghiviec.setText("Nghỉ Việc");

        jclNgaySinh.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("GIỚI TÍNH");

        cbogioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btnXuatDanhSachKhachHang.setBackground(new java.awt.Color(51, 0, 51));
        btnXuatDanhSachKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatDanhSachKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatDanhSachKhachHang.setText("Xuất file EXCEL");
        btnXuatDanhSachKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel3)))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttennv)
                            .addComponent(txtmakhau)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbovatro, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbogioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 134, Short.MAX_VALUE))
                            .addComponent(txtmanv)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnthem)))
                .addGap(30, 30, 30)
                .addComponent(btnlmmoi)
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnsua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatDanhSachKhachHang)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 146, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(rdodanglm)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                            .addComponent(rdonghiviec))
                                        .addComponent(jclNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(1, 1, 1)))
                        .addGap(29, 29, 29))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnlmmoi, btnsua, btnthem});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jclNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbogioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtmakhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbovatro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(rdodanglm)
                    .addComponent(rdonghiviec))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnlmmoi)
                    .addComponent(btnXuatDanhSachKhachHang))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setText("Thết Lập Thông Tin Nhân Viên");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Lọc Theo Vai trò");

        cboloctheovt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nhân viên", "Quản lý" }));
        cboloctheovt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboloctheovtActionPerformed(evt);
            }
        });

        jLabel12.setText("Lọc nhân viên");

        jLabel10.setText("Lọc theo trạng thái");

        cbonhanvien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang làm việc", "Nghỉ việc" }));
        cbonhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbonhanvienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboloctheovt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbonhanvien, 0, 167, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboloctheovt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbonhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Giới tính", "Mật khẩu", "Vai trò", "Số điện thoại", "Ngày Sinh", "Email", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblnhanvien);

        jTabbedPane4.addTab("Đang Làm Việc", jScrollPane3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(530, 530, 530)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane4)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        jTabbedPane4.getAccessibleContext().setAccessibleName("Danh sách nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        try {
            if (checkEmty()) {
                return;
            } else {
                NguoiDung nd = FormNhanVienInput();

                for (NguoiDung nguoiDung : lst) {
                    // Use txtmanv.getText() to get the text from the component
                    if (txtmanv.getText().equals(nguoiDung.getMaNguoidung())) {
                        JOptionPane.showMessageDialog(this, "Nhân viên đã tồn tại với mã này!");
                        return;
                    }
                }
                Integer result = nds.addNguoiDung(nd);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                    lst = nds.getAllNguoiDung();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
                }
            }
            loatdata();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnthemActionPerformed
    private boolean checkEmty() {
        StringBuilder sb = new StringBuilder();

        if (txttennv.getText().equals("")) {
            sb.append("Vui lòng nhập vào tên nhân viên !\n");
        }
        if (txtmanv.getText().equals("")) {
            sb.append("Vui lòng nhập vào mã nhân viên !\n");
        }

        if (txtemail.getText().equals("")) {
            sb.append("Vui lòng nhập vào email !\n");
        } else if (!isValidEmail(txtemail.getText())) {
            sb.append("Địa chỉ email không hợp lệ !\n");
        }
        if (txtmakhau.getText().equals("")) {
            sb.append("Vui lòng nhập vào mật khẩu !\n");
        }

        int check = 0;

        try {
            if (txtsdt.getText().equals("")) {
                sb.append("Vui lòng nhập vào số điện thoại! \n");
            } else {
                int sdtValue = Integer.parseInt(txtsdt.getText());
                if (sdtValue == check) {
                    sb.append("Số điện thoại không được bằng 0!\n");
                }
            }
        } catch (NumberFormatException e) {
            sb.append("Số điện thoại phải là số!\n");
        }

        if (sb.length() > 0) {
            JOptionPane.showConfirmDialog(this, sb.toString(), "Thông báo !", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }


    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
            if (checkEmty()) {
                return;
            } else {
                int selectedRow = tblnhanvien.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa!");
                    return;
                }
                NguoiDung nd = FormNhanVienInput();
                int cf = JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN SỬA DỮ LIỆU KHÔNG", "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
                if (cf == JOptionPane.YES_OPTION) {
                    try {
                        nd.setMaNguoidung(tblnhanvien.getValueAt(selectedRow, 0).toString());

                        int result = nds.updateNguoiDung(nd);
                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công");
                            lst.set(selectedRow, nd);
                            clearForm();
                        } else {
                            JOptionPane.showMessageDialog(this, "Bạn đã sửa thất bại");
                        }

                        loatdata();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi sửa nhân viên");
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnlmmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlmmoiActionPerformed
        clearForm();
        loatdata();
    }//GEN-LAST:event_btnlmmoiActionPerformed

    private void cbonhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbonhanvienActionPerformed
        String selectedStatus = cbonhanvien.getSelectedItem().toString();
        String selectedRole = cboloctheovt.getSelectedItem().toString();
        filterDataByRoleAndStatus(selectedRole, selectedStatus);
    }//GEN-LAST:event_cbonhanvienActionPerformed

    private void cboloctheovtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboloctheovtActionPerformed
        String selectedStatus = cbonhanvien.getSelectedItem().toString();
        String selectedRole = cboloctheovt.getSelectedItem().toString();
        filterDataByRoleAndStatus(selectedRole, selectedStatus);
    }//GEN-LAST:event_cboloctheovtActionPerformed

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked
        try {
            index = tblnhanvien.getSelectedRow();
            hienthi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void btnXuatDanhSachKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachKhachHangActionPerformed
        String filePath = "E:\\AMITYC_SHOPV2 (2) (1)\\DanhSachNhanVien.xlsx";
        if (exportToExcel(tblnhanvien, filePath)) {
            JOptionPane.showMessageDialog(this, "Xuất file thành công !");
        } else {
            JOptionPane.showMessageDialog(this, "Xuất file thất bại !");
        }
    }//GEN-LAST:event_btnXuatDanhSachKhachHangActionPerformed
    private boolean exportToExcel(JTable table, String filePath) {
        try {
            TableModel model = table.getModel();
            List<List<Object>> data = new ArrayList<>();

            for (int row = 0; row < model.getRowCount(); row++) {
                List<Object> rowData = new ArrayList<>();
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object cellValue = model.getValueAt(row, col);
                    rowData.add(cellValue != null ? cellValue.toString() : null);
                }
                data.add(rowData);
            }

            EasyExcel.write(filePath).sheet("Nhân Viên 4MENSHOP").doWrite(data);

            System.out.println("Excel file exported successfully!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatDanhSachKhachHang;
    private javax.swing.JButton btnlmmoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbogioitinh;
    private javax.swing.JComboBox<String> cboloctheovt;
    private javax.swing.JComboBox<String> cbonhanvien;
    private javax.swing.JComboBox<String> cbovatro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private com.toedter.calendar.JDateChooser jclNgaySinh;
    private javax.swing.JRadioButton rdodanglm;
    private javax.swing.JRadioButton rdonghiviec;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmakhau;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttennv;
    private javax.swing.JTextField txttim;
    // End of variables declaration//GEN-END:variables
}
