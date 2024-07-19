/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;

import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import model.ChiTietPhuongThuc;
import model.ChiTietSanPham;
import model.HoaDon1;
import model.HoaDonChiTiet;
import model.HoaDonChiTiet1;
import model.KhuyenMai1;
import model.PhuongThucThanhToan;
import model.SanPham;
import model.ThuocTinh;
import model.KhachHang;
import reponsitory.ChiTietPtttRepository;
import reponsitory.ChiTietSanPhamRepository;
import reponsitory.DBConnect;
import reponsitory.HoaDonChiTietReponsitory;
import reponsitory.HoaDonReponsitory;
import reponsitory.KhachHangReponsitory;
import reponsitory.PhieuGiamGiaRepositoy;
import reponsitory.NhanVienrepostory;
import reponsitory.SanPhamRepository;
import reponsitory.ThuocTinhRepository;
import reponsitory.ThuocTinhRepository1;
import service.HoaDonService;
import service.KhachHangService;
import service.SAVENHANVIEN;
import service.SanPhamChiTietService;

/**
 *
 * @author Asus
 */
public class HoaDon extends javax.swing.JPanel implements Runnable, ThreadFactory {

    boolean tabAdded = false;
    private boolean enterPressed;
    private WebcamPanel panel = null;
    private Webcam webCam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private volatile boolean isRunning = true;
    private HoaDonService hds = new HoaDonService();
    private SanPhamChiTietService spcts = new SanPhamChiTietService();
    private ArrayList<model.HoaDon> lstHD;
//    private ArrayList<model.SanPhamChiTiet> lstSPCT;
    private model.HoaDon hd;
    private model.KhachHang kh;
    private int index = 0;
    private DefaultTableModel model;
//    private DefaultTableModel model1;
//    private DefaultTableModel model2;

    DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
    DefaultTableModel dtm;
    ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();
    SanPhamRepository SanPhamRepository = new SanPhamRepository();
    ThuocTinhRepository1 ThuocTinhRepository = new ThuocTinhRepository1();
    HoaDonReponsitory HoaDonRepository = new HoaDonReponsitory();
    HoaDonChiTietReponsitory hdctr = new HoaDonChiTietReponsitory();
    NhanVienrepostory NhanVienRepository = new NhanVienrepostory();
    ChiTietPtttRepository ptttRepository = new ChiTietPtttRepository();
    PhieuGiamGiaRepositoy kms = new PhieuGiamGiaRepositoy();
    KhachHangReponsitory khr = new KhachHangReponsitory();
    KhachHangService khs = new KhachHangService();

    public HoaDon() throws Exception {
        initComponents();
        model = (DefaultTableModel) tblHoaDon.getModel();
//        model1 = (DefaultTableModel) tblSanPham.getModel();
//        LoadDataHoaDonToTable();
//        LoadDataSPChiTietToTable();
//        innitWebcam();
//        OpenWebCam();
        loadTableCTSP();
        loadCbo();
        loadVocher();
        LoadDataHoaDonToTable();
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSP(); // Gọi hàm tìm kiếm khi có thay đổi trong ô tìm kiếm
            }
        });
        txtSdtKH.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTenKH(); // Gọi hàm tìm kiếm khi có thay đổi trong ô tìm kiếm
            }
        });
        txtQRCode.setEditable(false);
//        initTable();
    }

    void initTable() {
        tblSanPham.getColumnModel().getColumn(0).setMinWidth(0);
        tblSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(0);

//        tblGioHang.getColumnModel().getColumn(6).setMinWidth(0);
//        tblGioHang.getColumnModel().getColumn(6).setMaxWidth(0);
//        tblGioHang.getColumnModel().getColumn(6).setPreferredWidth(0);
        tblGioHang.getColumnModel().getColumn(7).setMinWidth(0);
        tblGioHang.getColumnModel().getColumn(7).setMaxWidth(0);
        tblGioHang.getColumnModel().getColumn(7).setPreferredWidth(0);
    }

    public void checkTxt() {
        if (txtQRCode.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "abc");
            return;
        } else {
            String txtString = txtQRCode.getText().trim();
            if (txtString.isEmpty()) {
                return;
            } else {
                Connection conn = DBConnect.openConnection();
                String sql = "SELECT * FROM chi_tiet_san_pham WHERE id = ? ";
                try ( PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, txtString);
                    try ( ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            index = tblHoaDon.getSelectedRow();
                            if (index < 0) {
                                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thanh toán !", "Thông báo ", JOptionPane.ERROR_MESSAGE);
                            } else {
                                themSPQR();
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Sản phẩm này không tồn tại !");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void searchTenKH() {
        try {
            hd = new model.HoaDon();
            kh = khs.timKhachHang(txtSdtKH.getText().trim());
            if (kh == null) {
                txtTenKH.setText("");
                txtMaKH.setText("");
            }
            if (kh != null) {
                txtTenKH.setText(kh.getTenKhachHang());
                txtMaKH.setText(String.valueOf(kh.getIdKH()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void searchSP() {
        String searchIdSp = txtSearch.getText().trim();
        List<ChiTietSanPham> lst = ctspr.getAllData();
        DefaultTableModel searchSP = new DefaultTableModel();
        searchSP = (DefaultTableModel) tblSanPham.getModel();
        searchSP.setRowCount(0);

        for (ChiTietSanPham ctsp : lst) {
            SanPham getSanPham = SanPhamRepository.getByID(ctsp.getIdSanPham());
            ThuocTinh getMauSac = ThuocTinhRepository.getByID("mau_sac", ctsp.getIdMauSac());
            ThuocTinh getThuongHieu = ThuocTinhRepository.getByID("thuong_hieu", ctsp.getIdThuongHieu());
            ThuocTinh getPhongCach = ThuocTinhRepository.getByID("phong_cach", ctsp.getIdPhongCach());
            ThuocTinh getKichThuoc = ThuocTinhRepository.getByID("kich_thuoc", ctsp.getIdKichThuoc());
            ThuocTinh getTheLoai = ThuocTinhRepository.getByID("the_loai", ctsp.getIdTheLoai());

            if (getSanPham.getMa().toLowerCase().contains(searchIdSp.toLowerCase()) || getSanPham.getTen().toLowerCase().contains(searchIdSp.toLowerCase())) {
                searchSP.addRow(new Object[]{
                    ctsp.getId(),
                    getSanPham.getMa(),
                    getSanPham.getTen(),
                    ctsp.getGiaBan(),
                    getMauSac.getTen(),
                    getKichThuoc.getTen(),
                    getTheLoai.getTen(),
                    getPhongCach.getTen(),
                    getThuongHieu.getTen(),
                    ctsp.getSoLuong()
                });
            }
        }
        tblSanPham.setModel(searchSP);
    }

    public void loadCbo() {
        dcm = (DefaultComboBoxModel) cboPhuongThucTT.getModel();
        for (PhuongThucThanhToan pttt : ptttRepository.getALl()) {
            dcm.addElement(pttt.getTenPttt());
        }
    }

    public void loadVocher() {
        dcm = (DefaultComboBoxModel) cboGiamGia.getModel();
        for (model.KhuyenMai km : kms.getAllKhuyenMai()) {
            dcm.addElement(km.getMaKM());
        }
    }

    public void loadTableCTSP() {
        dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        List<ChiTietSanPham> list = ctspr.getAllData();
        for (ChiTietSanPham ctsp : list) {

            SanPham getSanPham = SanPhamRepository.getByID(ctsp.getIdSanPham());
            ThuocTinh getMauSac = ThuocTinhRepository.getByID("mau_sac", ctsp.getIdMauSac());
            ThuocTinh getThuongHieu = ThuocTinhRepository.getByID("thuong_hieu", ctsp.getIdThuongHieu());
            ThuocTinh getPhongCach = ThuocTinhRepository.getByID("phong_cach", ctsp.getIdPhongCach());
            ThuocTinh getKichThuoc = ThuocTinhRepository.getByID("kich_thuoc", ctsp.getIdKichThuoc());
            ThuocTinh getTheLoai = ThuocTinhRepository.getByID("the_loai", ctsp.getIdTheLoai());
            if (ctsp.getTrangThai().equals("Hoạt động")) {
                Object[] rowData = {
                    ctsp.getId(),
                    getSanPham.getMa(),
                    getSanPham.getTen(),
                    ctsp.getGiaBan(),
                    getMauSac.getTen(),
                    getKichThuoc.getTen(),
                    getTheLoai.getTen(),
                    getPhongCach.getTen(),
                    getThuongHieu.getTen(),
                    ctsp.getSoLuong(),};
                dtm.addRow(rowData);
            }
        }
    }

    public void showDetailHD(JTable tbl, int sl, ChiTietSanPham ctsp) {
        dtm = (DefaultTableModel) tblGioHang.getModel();
        SanPham getSanPham = SanPhamRepository.getByID(ctsp.getIdSanPham());
        NumberFormat nf = NumberFormat.getNumberInstance();
        BigDecimal soLuong = BigDecimal.valueOf(sl);
        Object obj[] = new Object[8];

        obj[0] = tblGioHang.getRowCount() + 1;
        obj[1] = getSanPham.getMa();
        obj[2] = getSanPham.getTen();
        obj[3] = sl;
        obj[4] = ctsp.getGiaBan();
        obj[5] = nf.format(soLuong.multiply(ctsp.getGiaBan()));
//        obj[6] = 
        obj[7] = ctsp.getId();

        dtm.addRow(obj);
    }

    public void chooseSanPham() {
        UUID id = UUID.fromString(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString());
        ChiTietSanPham getChiTietSanPham = ctspr.getByIDhd(id);

        // Lấy số lượng nhập từ bàn phím
        int sl;
        try {
            if (tblHoaDon.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước");
                return;
            }
            String input = JOptionPane.showInputDialog("Nhập số lượng ");
            if (input == null) {
                // Handle the case where the user clicks the Cancel button
                JOptionPane.showMessageDialog(this, "Hủy thêm");
                return;
            }
            if (input.trim().isEmpty() || !input.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                return;
            }
            sl = Integer.parseInt(input);
            if (sl <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
            if (sl > getChiTietSanPham.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ");
                return;
            }
            // Handle other conditions
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hủy thêm ");
            txtQRCode.setText("");
            return;
        }
        // show lên giỏ hàng và thêm vào hdct trong db
        showDetailHD(tblHoaDon, sl, getChiTietSanPham);
        HoaDonChiTiet1 hdct = getModelHDCT(sl, getChiTietSanPham);
        hdctr.addHDCT(hdct, sl);

        //Trừ số lượng sản phẩm trong chi tiết sản phẩm
        int slc = getChiTietSanPham.getSoLuong() - sl;
        ctspr.UpdateSL(id, slc);
        loadTableCTSP();

        // tính tổng tiền sản phẩm hóa đơn
        NumberFormat nf = NumberFormat.getNumberInstance();
        int selectedColumn = 5;
        int rowCount = tblGioHang.getRowCount();
        double sum = 0;

        for (int i = 0; i < rowCount; i++) {
            String value = tblGioHang.getValueAt(i, selectedColumn).toString();
            // Loại bỏ dấu phẩy khỏi chuỗi trước khi chuyển đổi
            value = value.replace(",", "");
            sum += Double.parseDouble(value);
        }
        txtTongTien.setText(nf.format(sum));
        txtTienThanhToan.setText(nf.format(sum));
    }

//    public void showHD() {
//        dtm = (DefaultTableModel) tblHoaDon.getModel();
//        dtm.setRowCount(0);
//        ArrayList<HoaDon1> lst = HoaDonRepository.getAllHD();
//        int dong = 1;
//        for (HoaDon1 hd : lst) {
//            if (hd.getDeleted() == 1) {
//                Object[] rowData = {
//                    dong++,
//                    hd.getIdHD(),
//                    hd.getIdNV(),
//                    hd.getIdKH(),
//                    hd.getNgayTao(),
//                    hd.getTrangThai(),};
//                dtm.addRow(rowData);
//            }
//            txtMaHD.setText(hd.getIdHD());
//        }
//    }
//    public HoaDon1 getModelHD() {
//        HoaDon1 hd = new HoaDon1();
//
//        hd.setIdHD(UUID.randomUUID().toString());
//        hd.setIdNV(SaveNhanVien.nhanVien.getId());
//        hd.setIdKH(hd.getIdKH());
//        hd.setNgayTao(hd.getNgayTao());
//        hd.setTrangThai("Chờ thanh toán");
//
//        return hd;
//    }
    public HoaDonChiTiet1 getModelHDCT(int sl, ChiTietSanPham ctsp) {
        HoaDonChiTiet1 hdct = new HoaDonChiTiet1();

        BigDecimal soLuong = BigDecimal.valueOf(sl);

        hdct.setIdHDCT(UUID.randomUUID().toString());
        hdct.setIdCTSP(ctsp.getId());
        hdct.setIdHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
        hdct.setSl(sl);
        hdct.setThanhTien(soLuong.multiply(ctsp.getGiaBan()));

        return hdct;
    }

    private void innitWebcam() {
        Dimension size = WebcamResolution.VGA.getSize();
        webCam = Webcam.getWebcams().get(index);
        webCam.setViewSize(size);
        panel = new WebcamPanel(webCam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        panel.setFPSLimit(30);
        PN3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 256, 235));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, e);
            }
            Result rs = null;
            BufferedImage image = new BufferedImage(256, 235, BufferedImage.TYPE_INT_ARGB);
            if (webCam.isOpen()) {
                if ((image = webCam.getImage()) == null) {
//                    webCam.close();
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                rs = new MultiFormatReader().decode(bitmap);

            } catch (Exception e) {
//                Logger.getLogger(HoaDon.getClass().getName()).log(Level.SEVERE, null, e);

            }
            if (rs != null) {
                txtQRCode.setText(rs.getText());
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PANELALL = new javax.swing.JPanel();
        PN1 = new javax.swing.JPanel();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        rdoBihuy = new javax.swing.JRadioButton();
        PN2 = new javax.swing.JPanel();
        HoaDon = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        PN3 = new javax.swing.JPanel();
        PN4 = new javax.swing.JPanel();
        GioHang = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        PN5 = new javax.swing.JPanel();
        SanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        PN6 = new javax.swing.JPanel();
        txtTenKH = new javax.swing.JTextField();
        lblTenKH = new javax.swing.JLabel();
        lblSdtKH = new javax.swing.JLabel();
        txtSdtKH = new javax.swing.JTextField();
        lblMaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        PN7 = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        lblGiamGia = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        txtTienThanhToan = new javax.swing.JTextField();
        lblTienKhachDua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        lblPhuongThucTT = new javax.swing.JLabel();
        cboPhuongThucTT = new javax.swing.JComboBox<>();
        lblVND = new javax.swing.JLabel();
        lblVND2 = new javax.swing.JLabel();
        lblVND4 = new javax.swing.JLabel();
        lblVND3 = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        cboGiamGia = new javax.swing.JComboBox<>();
        txtQRCode = new javax.swing.JTextField();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        setLayout(new javax.swing.OverlayLayout(this));

        PN1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRẠNG THÁI HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoDaThanhToan.setForeground(new java.awt.Color(0, 153, 51));
        rdoDaThanhToan.setText("Đã thanh toán");

        buttonGroup1.add(rdoChoThanhToan);
        rdoChoThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChoThanhToan.setText("Chờ thanh toán");
        rdoChoThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChoThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoBihuy);
        rdoBihuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoBihuy.setForeground(new java.awt.Color(255, 153, 0));
        rdoBihuy.setText("Bị hủy");

        javax.swing.GroupLayout PN1Layout = new javax.swing.GroupLayout(PN1);
        PN1.setLayout(PN1Layout);
        PN1Layout.setHorizontalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN1Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(rdoBihuy, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(rdoChoThanhToan)
                .addGap(70, 70, 70)
                .addComponent(rdoDaThanhToan)
                .addGap(23, 23, 23))
        );
        PN1Layout.setVerticalGroup(
            PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(rdoChoThanhToan)
                    .addComponent(rdoBihuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PN2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tên NV", "Tên KH", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblHoaDonAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        HoaDon.setViewportView(tblHoaDon);

        javax.swing.GroupLayout PN2Layout = new javax.swing.GroupLayout(PN2);
        PN2.setLayout(PN2Layout);
        PN2Layout.setHorizontalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HoaDon)
                .addContainerGap())
        );
        PN2Layout.setVerticalGroup(
            PN2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN2Layout.createSequentialGroup()
                .addComponent(HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        PN3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PN3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PN3MouseClicked(evt);
            }
        });
        PN3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PN4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GIỎ HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền", "ID HD", "ID CTSP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        GioHang.setViewportView(tblGioHang);

        btnXoaSP.setText("XÓA");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PN4Layout = new javax.swing.GroupLayout(PN4);
        PN4.setLayout(PN4Layout);
        PN4Layout.setHorizontalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GioHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PN4Layout.setVerticalGroup(
            PN4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnXoaSP)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(PN4Layout.createSequentialGroup()
                .addComponent(GioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PN5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idCTSP", "Mã SP", "Tên SP", "Đơn giá", "Màu sắc", "Size", "Loại", "Phong cách", "Thương hiệu", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        SanPham.setViewportView(tblSanPham);

        jLabel1.setText("TÌM KIẾM  : ");

        javax.swing.GroupLayout PN5Layout = new javax.swing.GroupLayout(PN5);
        PN5.setLayout(PN5Layout);
        PN5Layout.setHorizontalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .addGroup(PN5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PN5Layout.setVerticalGroup(
            PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN5Layout.createSequentialGroup()
                .addGroup(PN5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PN6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTenKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTenKH.setBorder(null);

        lblTenKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenKH.setText("Tên Khách Hàng : ");

        lblSdtKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSdtKH.setText("Số Điện Thoại : ");

        txtSdtKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSdtKH.setBorder(null);

        lblMaKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaKH.setText("Mã Khách Hàng : ");

        txtMaKH.setEditable(false);
        txtMaKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaKH.setBorder(null);

        javax.swing.GroupLayout PN6Layout = new javax.swing.GroupLayout(PN6);
        PN6.setLayout(PN6Layout);
        PN6Layout.setHorizontalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSdtKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        PN6Layout.setVerticalGroup(
            PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN6Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKH)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PN6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSdtKH)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PN7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblMaHD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaHD.setText("Mã hóa đơn :");

        txtMaHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaHD.setBorder(null);

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTongTien.setText("Tổng tiền :");

        txtTongTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTongTien.setBorder(null);

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGiamGia.setText("Giảm giá :");

        lblThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThanhToan.setText("Thanh toán :");

        txtTienThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienThanhToan.setBorder(null);

        lblTienKhachDua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTienKhachDua.setText("Tiền khách đưa :");

        txtTienKhachDua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienKhachDua.setBorder(null);
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        lblTienThua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTienThua.setText("Tiền thừa :");

        txtTienThua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTienThua.setBorder(null);

        lblPhuongThucTT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhuongThucTT.setText("Phương thức thanh toán :");

        lblVND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND.setText("VNĐ");

        lblVND2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND2.setText("VNĐ");

        lblVND4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND4.setText("VNĐ");

        lblVND3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVND3.setText("VNĐ");

        btnTaoHoaDon.setText("TẠO HÓA ĐƠN");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setText("HỦY HÓA ĐƠN");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        cboGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn giảm giá--" }));
        cboGiamGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGiamGiaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout PN7Layout = new javax.swing.GroupLayout(PN7);
        PN7.setLayout(PN7Layout);
        PN7Layout.setHorizontalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addComponent(btnHuyHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addComponent(lblThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVND2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PN7Layout.createSequentialGroup()
                                .addComponent(lblTienThua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTienThua))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PN7Layout.createSequentialGroup()
                                .addComponent(lblTienKhachDua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVND3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblVND4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(PN7Layout.createSequentialGroup()
                        .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblMaHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPhuongThucTT)
                            .addGroup(PN7Layout.createSequentialGroup()
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVND)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                        .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PN7Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addGap(111, 111, 111))))
        );
        PN7Layout.setVerticalGroup(
            PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVND))
                .addGap(26, 26, 26)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGia)
                    .addComponent(cboGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThanhToan)
                    .addComponent(txtTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVND2))
                .addGap(36, 36, 36)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienKhachDua)
                    .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVND4)))
                .addGap(30, 30, 30)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienThua)
                    .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVND3)))
                .addGap(18, 18, 18)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucTT)
                    .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon)
                .addGap(18, 18, 18)
                .addGroup(PN7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDon)
                    .addComponent(btnThanhToan))
                .addGap(86, 86, 86))
        );

        txtQRCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQRCode.setForeground(new java.awt.Color(255, 51, 51));
        txtQRCode.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQRCodeCaretUpdate(evt);
            }
        });
        txtQRCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQRCodeFocusLost(evt);
            }
        });
        txtQRCode.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtQRCodeInputMethodTextChanged(evt);
            }
        });
        txtQRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQRCodeActionPerformed(evt);
            }
        });
        txtQRCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQRCodeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PANELALLLayout = new javax.swing.GroupLayout(PANELALL);
        PANELALL.setLayout(PANELALLLayout);
        PANELALLLayout.setHorizontalGroup(
            PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PANELALLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PANELALLLayout.createSequentialGroup()
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(PN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQRCode, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(PN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PANELALLLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PANELALLLayout.setVerticalGroup(
            PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PANELALLLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PANELALLLayout.createSequentialGroup()
                        .addGroup(PANELALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PANELALLLayout.createSequentialGroup()
                                .addComponent(PN3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PANELALLLayout.createSequentialGroup()
                        .addComponent(PN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        add(PANELALL);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        try {
            kh = new model.KhachHang();
            hd = new model.HoaDon();
//            kh.setIdKH(UUID.fromString(txtTenKH.getText()));
            if (txtTenKH.getText().length() > 0) {
                kh.setTenKhachHang(txtTenKH.getText());
            } else {
                kh.setTenKhachHang("Khách lẻ");
            }
            if (txtSdtKH.getText().length() > 0) {
                kh.setSoDienthoai(txtSdtKH.getText().trim());
            } else {
                kh.setSoDienthoai(null);
            }
            kh.setNguoiTao(SAVENHANVIEN.luuNguoiDung.getHoTen());
            hd.setIdNguoiDung(SAVENHANVIEN.luuNguoiDung.getIdND());
            if (hds.themHoaDon(kh, hd)) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại !");
            }
            LoadDataHoaDonToTable();
            txtMaKH.setText("");
            txtTenKH.setText("");
            txtSdtKH.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void rdoChoThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChoThanhToanActionPerformed

    }//GEN-LAST:event_rdoChoThanhToanActionPerformed
    public void showHDCT() {
        dtm = (DefaultTableModel) tblGioHang.getModel();
        String idHD = tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString();
        ArrayList<HoaDonChiTiet1> lst = hdctr.getHDCT(idHD);
        NumberFormat nf = NumberFormat.getNumberInstance();
        int dong = 1;
        if (dong != dong) {
            for (HoaDonChiTiet1 hdct : lst) {
                Object[] rowData = {
                    dong++,
                    hdct.getSp().getMa(),
                    hdct.getSp().getTen(),
                    hdct.getSl(),
                    hdct.getCtsp().getGiaBan(),
                    nf.format(hdct.getThanhTien()),
                    hdct.getIdHDCT(),
                    hdct.getIdCTSP()};
                dtm.addRow(rowData);
            }
        } else {
            Clear();
            for (HoaDonChiTiet1 hdct : lst) {
                Object[] rowData = {
                    dong++,
                    hdct.getSp().getMa(),
                    hdct.getSp().getTen(),
                    hdct.getSl(),
                    hdct.getCtsp().getGiaBan(),
                    nf.format(hdct.getThanhTien()),
                    hdct.getIdHDCT(),
                    hdct.getIdCTSP()};
                dtm.addRow(rowData);
            }
        }
    }

    public void Clear() {
        dtm.setRowCount(0);
        return;
    }
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
//        OpenWebCam();
        NumberFormat nf = NumberFormat.getNumberInstance();
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        showHDCT();
        txtMaHD.setText(tblHoaDon.getValueAt(row, 1).toString());
        int selectedColumn = 5;
        int rowCount = tblGioHang.getRowCount();
        double sum = 0;

        for (int i = 0; i < rowCount; i++) {
            String value = tblGioHang.getValueAt(i, selectedColumn).toString();
            // Loại bỏ dấu phẩy khỏi chuỗi trước khi chuyển đổi
            value = value.replace(",", "");
            sum += Double.parseDouble(value);
        }
        txtTongTien.setText(nf.format(sum));
        txtTienThanhToan.setText(nf.format(sum));

        txtTenKH.setText(tblHoaDon.getValueAt(row, 3).toString());
        try {
            String searchTenKH = txtSdtKH.getText().trim();
            ArrayList<KhachHang> lst = khr.getAllKhachHang();
            for (KhachHang kh : lst) {
                if (txtTenKH.getText().equals("Khách lẻ")) {
                    txtSdtKH.setText("");
                    return;
                }
                if (txtTenKH.getText().equals(kh.getTenKhachHang())) {
                    txtSdtKH.setText(kh.getSoDienthoai());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblHoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thanh toán !", "Thông báo ", JOptionPane.ERROR_MESSAGE);
        } else {
            chooseSanPham();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked
    public void themSPQR() {
        UUID id = UUID.fromString(txtQRCode.getText());
        ChiTietSanPham getChiTietSanPham = ctspr.getByIDhd(id);
        int sl;
        // Lấy số lượng nhập từ bàn phím
        try {
            if (tblHoaDon.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước");
                return;
            }
            String input = JOptionPane.showInputDialog("Nhập số lượng ");
            if (input == null) {
                // Handle the case where the user clicks the Cancel button
                JOptionPane.showMessageDialog(this, "Hủy thêm");
                return;
            }
            if (input.trim().isEmpty() || !input.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                return;
            }
            sl = Integer.parseInt(input);
            if (sl <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }

            if (sl > getChiTietSanPham.getSoLuong()) {
                while (true) {
                    if (sl > getChiTietSanPham.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Số lượng trong kho không đủ");
                        input = JOptionPane.showInputDialog("Nhập số lượng ");
                        sl = Integer.parseInt(input);
                    } else {
                        break;
                    }
                }
            }
            // Handle other conditions
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hủy thêm ");
            txtQRCode.setText("");
            return;
        }
        showDetailHD(tblHoaDon, sl, getChiTietSanPham);
        HoaDonChiTiet1 hdct = getModelHDCT(sl, getChiTietSanPham);
        hdctr.addHDCT(hdct, sl);

        //Trừ số lượng sản phẩm trong chi tiết sản phẩm
        int slc = getChiTietSanPham.getSoLuong() - sl;
        ctspr.UpdateSL(id, slc);
        loadTableCTSP();

        // tính tổng tiền sản phẩm hóa đơn
        int selectedColumn = 5;
        int rowCount = tblGioHang.getRowCount();
        double sum = 0;

        for (int i = 0; i < rowCount; i++) {
            sum += Double.parseDouble(tblGioHang.getValueAt(i, selectedColumn).toString());
        }
        txtTongTien.setText(String.valueOf(sum));
        txtTienThanhToan.setText(String.valueOf(sum));
    }
    private void txtQRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQRCodeActionPerformed

    }//GEN-LAST:event_txtQRCodeActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded

    }//GEN-LAST:event_formAncestorAdded

    private void PN3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PN3MouseClicked
        OpenWebCam();
    }//GEN-LAST:event_PN3MouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        OpenWebCam();

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void tblHoaDonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblHoaDonAncestorAdded

    }//GEN-LAST:event_tblHoaDonAncestorAdded
    public HoaDon1 thanhToan() {
        HoaDon1 hd = new HoaDon1();
        int selectedColumn = 5;
        int rowCount = tblGioHang.getRowCount();
        double sum = 0;
//        NumberFormat nf = NumberFormat.getInstance();

        for (int i = 0; i < rowCount; i++) {
            String value = tblGioHang.getValueAt(i, selectedColumn).toString();
            // Loại bỏ dấu phẩy khỏi chuỗi trước khi chuyển đổi
            value = value.replace(",", "");
            sum += Double.parseDouble(value);
        }
        KhuyenMai1 km = kms.getModel(cboGiamGia.getSelectedItem().toString());

        hd.setIdHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
        hd.setGia_tien(BigDecimal.valueOf(sum));
        // Loại bỏ dấu phẩy khổi chuỗi trước khi chuyển đổi
        String tongTien = txtTienThanhToan.getText();
        String tongTien1 = tongTien.replace(",", "");
        hd.setTong_tien(BigDecimal.valueOf(Double.parseDouble(tongTien1)));
        if (cboGiamGia.getSelectedIndex() == 0) {
            hd.setGiamGia(null);
        } else {
            hd.setGiamGia(BigDecimal.valueOf(Double.parseDouble(km.getGiaTriGiam())));
        }

        return hd;
    }

    public ChiTietPhuongThuc pttt() {
        ChiTietPhuongThuc ctpt = new ChiTietPhuongThuc();
        PhuongThucThanhToan pttt = ptttRepository.getModel(cboPhuongThucTT.getSelectedItem().toString());
        NumberFormat nf = NumberFormat.getNumberInstance();

        ctpt.setIdCTPT(UUID.randomUUID().toString());
        ctpt.setIdHD(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
        ctpt.setIdPttt(pttt.getIdPttt());
        String tongTien = txtTienThanhToan.getText();
        String tongTien1 = tongTien.replace(",", "");
        ctpt.setTongTien(BigDecimal.valueOf(Double.parseDouble(tongTien1)));
        if (pttt.getTenPttt().equals("Tiền mặt")) {
            if (txtTienKhachDua.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa");
                return null;
            } else {
                String soTienMat = txtTienKhachDua.getText();
                String soTienMat1 = soTienMat.replace(",", "");
                ctpt.setSoTienMat(BigDecimal.valueOf(Double.parseDouble(soTienMat1)));
                ctpt.setChuyenKhoan(BigDecimal.valueOf(0));
            }
        }
        if (pttt.getTenPttt().equals("Chuyển khoản")) {
            ctpt.setChuyenKhoan(BigDecimal.valueOf(Double.parseDouble(tongTien1)));
            ctpt.setSoTienMat(BigDecimal.valueOf(0));
        }

        return ctpt;
    }
    
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        try {
            int row = tblHoaDon.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thanh toán");
                return;
            }
            HoaDon1 hd = thanhToan();
            ChiTietPhuongThuc ctpt = pttt();
            // Check trống tiền khách đưa khi chọn pttt là tiền mặt
            if (cboPhuongThucTT.getSelectedIndex() == 0) {
                if (txtTienKhachDua.getText().trim().isEmpty()) {
                    return;
                }
            }
            int xn = JOptionPane.showConfirmDialog(this, "Bạn có muốn thanh toán không ?");
            if (xn == JOptionPane.YES_OPTION) {
                HoaDonRepository.ThanhToan(hd);
                ptttRepository.ThanhToan(ctpt);
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                LoadDataHoaDonToTable();
                dtm = (DefaultTableModel) tblGioHang.getModel();
                dtm.setRowCount(0);
            }
            if (xn == JOptionPane.NO_OPTION) {
                return;
            }
            if (xn == JOptionPane.CANCEL_OPTION) {
                return;
            }
            if (xn == JOptionPane.CLOSED_OPTION) {
                return;
            }
            txtTongTien.setText(" ");
            txtTienThanhToan.setText(" ");
            txtTienKhachDua.setText(" ");
            txtTienThua.setText(" ");
            txtTenKH.setText("");
            txtSdtKH.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cboGiamGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGiamGiaItemStateChanged
        // TODO add your handling code here:
        // tính tổng tiền sản phẩm hóa đơn
        NumberFormat nf = NumberFormat.getNumberInstance();
        int selectedColumn = 5; // Đảm bảo đây là chỉ mục cột chính xác
        int rowCount = tblGioHang.getRowCount();
        double sum = 0;

        for (int i = 0; i < rowCount; i++) {
            String value = tblGioHang.getValueAt(i, selectedColumn).toString();
            // Loại bỏ dấu phẩy khỏi chuỗi trước khi chuyển đổi
            value = value.replace(",", "");
            sum += Double.parseDouble(value);
        }

        // Tính số tiền cuối cùng phải trả có chiết khấu
        KhuyenMai1 km = kms.getModel(cboGiamGia.getSelectedItem().toString());
        if (cboGiamGia.getSelectedIndex() == 0) {
            // Không có giảm giá nào được chọn
            txtTienThanhToan.setText(nf.format(sum));
        } else {
            // Áp dụng giảm giá
            double thanhTien = sum - Double.parseDouble(km.getGiaTriGiam());
            txtTienThanhToan.setText(nf.format(thanhTien));
        }
    }//GEN-LAST:event_cboGiamGiaItemStateChanged
    public void UpdateSL() {
        int slc;
        //Lấy mã chi tiết sản phẩm
        UUID id = UUID.fromString(tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 7).toString());
        ChiTietSanPham getChiTietSanPham = ctspr.getByIDhd(id);

        // Tính số lượng tồn khi xóa sản phẩm trên giỏ hàng
        slc = Integer.parseInt(tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 3).toString()) + getChiTietSanPham.getSoLuong();
        ctspr.UpdateSL(id, slc);
        loadTableCTSP();
    }
    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int row = tblGioHang.getSelectedRow();
        if (row < 0) {
            return;
        }
        String id = tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 6).toString();
        System.out.println(id);
        int xn = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sản phẩm này ra hóa đơn không ?");
        if (xn == JOptionPane.YES_OPTION) {
            hdctr.deleteSP(id);
            JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm");
            UpdateSL();
            showHDCT();
            // Tính lại tổng tiền khi vừa xóa sản phẩm ra khỏi giở hàng
            int selectedColumn = 5;// Đảm bảo độ chính xác của cột được lấy giá trị
            int rowCount = tblGioHang.getRowCount();
            double sum = 0;
            NumberFormat nf = NumberFormat.getNumberInstance();

            for (int i = 0; i < rowCount; i++) {
                String value = tblGioHang.getValueAt(i, selectedColumn).toString();
                // Loại bỏ dấu phẩy khỏi chuỗi trước khi chuyển đổi
                value = value.replace(",", "");
                sum += Double.parseDouble(value);
            }
            // Set giá trị vào các textField
            txtTongTien.setText(nf.format(sum));
            txtTienThanhToan.setText(nf.format(sum));
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        // TODO add your handling code here:
        try {
            // Chọn dòng
            int row = tblHoaDon.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để hủy");
                return;
            }

            // Lấy id của hóa đơn
            String idHD = tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString();
            int xn = JOptionPane.showConfirmDialog(this, "Có muốn hủy hóa đơn này không ?");
            if (xn == JOptionPane.YES_OPTION) {
                HoaDonRepository.Update(idHD);
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công");
                LoadDataHoaDonToTable();
                txtTenKH.setText("");
                txtSdtKH.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        // TODO add your handling code here:
        // Update giá tiền khi nhập tiền khách đưa
        NumberFormat nf = NumberFormat.getInstance();
        if (!txtTienKhachDua.getText().trim().isEmpty()) {
            String tienThanhToan = txtTienThanhToan.getText();
            String value = tienThanhToan.replace(",", "");
            double tienThua = Double.parseDouble(txtTienKhachDua.getText()) - Double.parseDouble(value);
            txtTienThua.setText(nf.format(tienThua));
        } else {
            txtTienThua.setText("");
        }
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void txtQRCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQRCodeFocusLost
        // TODO add your handling code here:
        String txtString = txtQRCode.getText().trim();
        int soluong;
        if (txtString.length() > 0) {
            Connection conn = DBConnect.openConnection();
            String sql = "SELECT so_luong_ton FROM chi_tiet_san_pham WHERE id = ? ";
            try ( PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, txtString);
                try ( ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        soluong = rs.getInt("so_luong_ton");
                        index = tblHoaDon.getSelectedRow();
                        if (index < 0) {
                            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thanh toán !", "Thông báo ", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Sản phẩm này còn  " + soluong, "Thông báo !", JOptionPane.CLOSED_OPTION);
                            themSPQR();
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Sản phẩm này không tồn tại !");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtQRCodeFocusLost

    private void txtQRCodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtQRCodeCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQRCodeCaretUpdate

    private void txtQRCodeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtQRCodeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQRCodeInputMethodTextChanged

    private void txtQRCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQRCodeKeyReleased
        // TODO add your handling code here:
//        String txtString = txtQRCode.getText().trim();
//        if (txtString.length() > 0) {
//            txtQRCode.requestFocus();
//            Connection conn = DBConnect.openConnection();
//            String sql = "SELECT * FROM chi_tiet_san_pham WHERE id = ? ";
//            try ( PreparedStatement st = conn.prepareStatement(sql)) {
//                st.setString(1, txtString);
//                try ( ResultSet rs = st.executeQuery()) {
//                    if (rs.next()) {
//                        index = tblHoaDon.getSelectedRow();
//                        if (index < 0) {
//                            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thanh toán !", "Thông báo ", JOptionPane.ERROR_MESSAGE);
//                        } else {
//                            themSPQR();
//                        }
//
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Sản phẩm này không tồn tại !");
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            return;
//        }
    }//GEN-LAST:event_txtQRCodeKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane GioHang;
    private javax.swing.JScrollPane HoaDon;
    private javax.swing.JPanel PANELALL;
    private javax.swing.JPanel PN1;
    private javax.swing.JPanel PN2;
    private javax.swing.JPanel PN3;
    private javax.swing.JPanel PN4;
    private javax.swing.JPanel PN5;
    private javax.swing.JPanel PN6;
    private javax.swing.JPanel PN7;
    private javax.swing.JScrollPane SanPham;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboGiamGia;
    private javax.swing.JComboBox<String> cboPhuongThucTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblPhuongThucTT;
    private javax.swing.JLabel lblSdtKH;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblVND;
    private javax.swing.JLabel lblVND2;
    private javax.swing.JLabel lblVND3;
    private javax.swing.JLabel lblVND4;
    private javax.swing.JRadioButton rdoBihuy;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtQRCode;
    private javax.swing.JTextField txtSdtKH;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThanhToan;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    private void OpenWebCam() {
        Action enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Kiểm tra trạng thái của enterPressed
                if (enterPressed) {
                    // Xử lý logic khi Enter được nhấn lần thứ hai
                    handleSecondEnterKeyPress();
                    // Đặt lại trạng thái của enterPressed
                    enterPressed = false;
                } else {
                    // Xử lý logic khi Enter được nhấn lần đầu

                    handleFirstEnterKeyPress();
                    // Đặt trạng thái của enterPressed thành true
                    enterPressed = true;
                }
            }
        };

        // Gán hành động với phím Enter
        InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterAction");

        ActionMap actionMap = this.getActionMap();
        actionMap.put("enterAction", enterAction);
    }

    private void handleFirstEnterKeyPress() {
        webCam.close();
    }

    private void handleSecondEnterKeyPress() {
        innitWebcam();
    }

    private void LoadDataHoaDonToTable() throws Exception {
        lstHD = hds.getAllHoaDon();
        int dong = 1;
        model.setRowCount(0);
        for (model.HoaDon hoaDon : lstHD) {
            if (hoaDon.getDelete() == 1) {
                model.addRow(new Object[]{
                    dong++, hoaDon.getIdHD(), hoaDon.getTenNhanVien(), hoaDon.getTenKhachHang(), hoaDon.getNgayTao(), hoaDon.getTrangThai()
                });
            }
        }

    }
//
//    private void ShowDetail() {
//        hd = lstHD.get(index);
//        String id = String.valueOf(hd.getIdHD());
//        txtMaHD.setText(id);
//
//        txtTongTien.setText(String.valueOf(hd.getGiaTien()));
//
//    }

//    private void LoadDataSPChiTietToTable() throws Exception {
//        lstSPCT = spcts.getAllSanPhamChiTiet();
//        model1.setRowCount(0);
//        for (SanPhamChiTiet sanphamct : lstSPCT) {
//            model1.addRow(new Object[]{
//                sanphamct.getMaSanpham(),
//                sanphamct.getSoLuongton(),
//                sanphamct.getGiaBan(),
//                sanphamct.getKichThuoc(),
//                sanphamct.getMauSac(),
//                sanphamct.getThuongHieu(),
//                sanphamct.getThietKe(),
//                sanphamct.getPhongCach(),
//                sanphamct.getTheLoai(),
//                sanphamct.getNhaSanxuat(),
//                sanphamct.getTrangThai()
//            });
//        }
//    }
}
