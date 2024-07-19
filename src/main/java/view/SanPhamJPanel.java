/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import excel.Excel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.ChiTietSanPham;
import model.SanPham;
import model.ThuocTinh;
import reponsitory.ChiTietSanPhamRepository;
import reponsitory.SanPhamRepository;
import reponsitory.ThuocTinhRepository;
import util.MsgHelper;
import util.ExceptionCus;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.ImageHelper;
import util.QRCodeHelper;

/**
 *
 * @author Admin
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    ThuocTinhRepository thuocTinhRepository = new ThuocTinhRepository();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    List<SanPham> listSanPham = new ArrayList<>();
    List<ChiTietSanPham> listCTSP = new ArrayList<>();
    int rowTbCTSP = -1;
    int rowTbSP = -1;

    DefaultComboBoxModel modelThuongHieu = new DefaultComboBoxModel();
    DefaultComboBoxModel modelTheLoai = new DefaultComboBoxModel();
    DefaultComboBoxModel modelThietKe = new DefaultComboBoxModel();
    DefaultComboBoxModel modelCoAo = new DefaultComboBoxModel();
    DefaultComboBoxModel modelPhongCach = new DefaultComboBoxModel();
    DefaultComboBoxModel modelMauSac = new DefaultComboBoxModel();
    DefaultComboBoxModel modelChatLieu = new DefaultComboBoxModel();
    DefaultComboBoxModel modelKichThuoc = new DefaultComboBoxModel();
    DefaultComboBoxModel modelNSX = new DefaultComboBoxModel();

    private TableRowSorter<DefaultTableModel> rowSorterSP;
    private TableRowSorter<DefaultTableModel> rowSorterCTSP;
    DefaultTableModel modelTbCTSP = new DefaultTableModel();
    DefaultTableModel modelTbSP = new DefaultTableModel();

    int pageSP = 1;
    int pageCTSP = 1;

    int limitSP = 9;
    int limitCTSP = 12;

    int lengthSP = (int) Math.ceil((double) sanPhamRepository.getAllData().size() / limitSP);
    int lengthCTSP = (int) Math.ceil((double) chiTietSanPhamRepository.getAllData().size() / limitCTSP);

    JFileChooser fileChooser = new JFileChooser();

    /**
     * Creates new form SanPhamJPanel
     */
    public SanPhamJPanel() {
        initComponents();

        hinddenColumsTbSPAndSPCT();
        initCbb();
        showData();
        sorter();
        showDefaultDetailCTSP();
        statusEdit();

    }

    void selectImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileChooser.setDialogTitle("Chọn logo");
            File file = fileChooser.getSelectedFile();
            ImageHelper.saveImage(file);
            ImageIcon icon = ImageHelper.readImage(file.getName());
            lblHinhAnhSPCT.setToolTipText(file.getName());
            lblHinhAnhSPCT.setIcon(icon);
        }
    }

    void nextPageSP() {
        pageSP++;
        if (!sanPhamRepository.getAll(pageSP, limitSP).isEmpty()) {
            listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
            showDataSP(listSanPham);
        } else {
            pageSP = 1;
            listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
            showDataSP(listSanPham);
        }
        lblPageSp.setText("Trang " + pageSP + "/ " + lengthSP);
    }

    void nextPageCTSP() {
        pageCTSP++;
        if (!chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP).isEmpty()) {
            listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
            showDataCTSP(listCTSP);
        } else {
            pageCTSP = 1;
            listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
            showDataCTSP(listCTSP);
        }
        lblPageCTSP.setText("Trang " + pageCTSP + "/ " + lengthCTSP);
    }

    void prevPageSP() {
        pageSP--;
        if (pageSP >= 1) {
            listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
            showDataSP(listSanPham);
        } else {
            pageSP = lengthSP;
            listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
            showDataSP(listSanPham);
        }
        lblPageSp.setText("Trang " + pageSP + "/ " + lengthSP);
    }

    void prevPageCTSP() {
        pageCTSP--;
        if (pageCTSP >= 1) {
            listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
            showDataCTSP(listCTSP);
        } else {
            pageCTSP = lengthCTSP;
            listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
            showDataCTSP(listCTSP);
        }
        lblPageCTSP.setText("Trang " + pageCTSP + "/ " + lengthCTSP);
    }

    void sorter() {
        rowSorterSP = new TableRowSorter<>(modelTbSP);
        tbSanPham.setRowSorter(rowSorterSP);
        rowSorterCTSP = new TableRowSorter<>(modelTbCTSP);
        tbCTSP.setRowSorter(rowSorterCTSP);
    }

    void hinddenColumsTbSPAndSPCT() {
        tbSanPham.getColumnModel().getColumn(4).setMinWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setMaxWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setPreferredWidth(0);

        tbSanPham.getColumnModel().getColumn(0).setMinWidth(30);
        tbSanPham.getColumnModel().getColumn(0).setMaxWidth(30);
        tbSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);

        tbSanPham.getColumnModel().getColumn(1).setMinWidth(50);
        tbSanPham.getColumnModel().getColumn(1).setMaxWidth(100);
        tbSanPham.getColumnModel().getColumn(1).setPreferredWidth(50);

        tbSanPham.getColumnModel().getColumn(3).setMinWidth(110);
        tbSanPham.getColumnModel().getColumn(3).setMaxWidth(150);
        tbSanPham.getColumnModel().getColumn(3).setPreferredWidth(150);

        tbSanPham.getColumnModel().getColumn(4).setMinWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setMaxWidth(0);
        tbSanPham.getColumnModel().getColumn(4).setPreferredWidth(0);

        tbCTSP.getColumnModel().getColumn(8).setMinWidth(0);
        tbCTSP.getColumnModel().getColumn(8).setMaxWidth(0);
        tbCTSP.getColumnModel().getColumn(8).setPreferredWidth(0);

        tbCTSP.getColumnModel().getColumn(0).setMinWidth(30);
        tbCTSP.getColumnModel().getColumn(0).setMaxWidth(30);
        tbCTSP.getColumnModel().getColumn(0).setPreferredWidth(30);

        tbCTSP.getColumnModel().getColumn(1).setMinWidth(70);
        tbCTSP.getColumnModel().getColumn(1).setMaxWidth(100);
        tbCTSP.getColumnModel().getColumn(1).setPreferredWidth(70);

        tbCTSP.getColumnModel().getColumn(2).setMinWidth(60);
        tbCTSP.getColumnModel().getColumn(2).setMaxWidth(100);
        tbCTSP.getColumnModel().getColumn(2).setPreferredWidth(60);

        tbCTSP.getColumnModel().getColumn(3).setMinWidth(150);
        tbCTSP.getColumnModel().getColumn(3).setMaxWidth(180);
        tbCTSP.getColumnModel().getColumn(3).setPreferredWidth(150);

        tbCTSP.getColumnModel().getColumn(4).setMinWidth(50);
        tbCTSP.getColumnModel().getColumn(4).setMaxWidth(70);
        tbCTSP.getColumnModel().getColumn(4).setPreferredWidth(50);

    }

    void statusEdit() {

        boolean editSP = (rowTbSP >= 0);

        boolean pageDieuHuongSP = (tfTimKiemSP.getText().trim().isEmpty());
        btnPrevPageSP.setEnabled(pageDieuHuongSP);
        btnNextPageSP.setEnabled(pageDieuHuongSP);

        boolean pageDieuHuongCTSP = (tfTimCTSPByTen.getText().trim().isEmpty() && tfTimKiemCTSPByMa.getText().trim().isEmpty());
        btnNextPageCTSP.setEnabled(pageDieuHuongCTSP);
        btnPrevPageCTSP.setEnabled(pageDieuHuongCTSP);

        btnThemSp.setEnabled(!editSP);
        btnSuaSp.setEnabled(editSP);
        btnAnSp.setEnabled(editSP);

        boolean editCTSP = (rowTbCTSP >= 0);

        btnThemCTSP.setEnabled(!editCTSP && editSP);
        btnSuaCTSP.setEnabled(editCTSP);
        btnAnCTSP.setEnabled(editCTSP);
    }

    void clearFormCTSP() {
        this.rowTbCTSP = -1;
        ChiTietSanPham ctsp = new ChiTietSanPham();

        ctsp.setIdSanPham("");
        ctsp.setTrangThai("Hoạt động");
        ctsp.setGiaBan(new BigDecimal("-1"));
        ctsp.setSoLuong(-1);
//        ctsp.setMaQr("null");
        showDetailCTSP(ctsp);

        tbCTSP.clearSelection();

        lblHinhAnhSPCT.setIcon(null);
        lblHinhAnhSPCT.setToolTipText(null);

        cbbChatLieu.setSelectedIndex(0);
        initCbb();
        this.statusEdit();
    }

    void clearFormSP() {
        this.rowTbSP = -1;

        SanPham sp = new SanPham();
        sp.setTrangThai("Hoạt động");
        showDetailSP(sp);

        tbSanPham.clearSelection();
        this.statusEdit();
    }

    SanPham getSPByRowinTable() {
        rowTbSP = tbSanPham.getSelectedRow();
        String id = String.valueOf(tbSanPham.getValueAt(rowTbSP, 4));
        if (rowTbSP >= 0) {
            SanPham sp = sanPhamRepository.getByID(id);
            if (sp != null) {
                return sp;
            }
        }
        return new SanPham();
    }

    ChiTietSanPham getCTSPByRowInTable() {
        rowTbCTSP = tbCTSP.getSelectedRow();
        String id = String.valueOf(tbCTSP.getValueAt(rowTbCTSP, 8));
        if (rowTbCTSP >= 0) {
            ChiTietSanPham ctsp = chiTietSanPhamRepository.getByID(id);
            if (ctsp != null) {
                return ctsp;
            }
        }
        return new ChiTietSanPham();
    }

    void showDefaultDetailCTSP() {
        if (tbCTSP.getRowCount() > 0) {
            rowTbCTSP = 0;
            tbCTSP.setRowSelectionInterval(rowTbCTSP, rowTbCTSP);
            showDetailCTSP(getCTSPByRowInTable());
        }
    }

    void selectedRowInSP(String id) {
        try {
            int index = 0;
            for (SanPham sanPham : listSanPham) {
                if (!id.equalsIgnoreCase(sanPham.getId())) {
                    index++;
                    clearFormSP();
                } else {
                    rowTbSP = index;
                    tbSanPham.setRowSelectionInterval(index, index);
                    showDetailSP(sanPhamRepository.getByID(id));
                    return;
                }
            }
        } catch (Exception e) {
            MsgHelper.alert(this, "Ôi trời, có vẻ như Mã sản phẩm không có trong danh sách sản phẩm rồi");
            throw new RuntimeException(e);
        }
    }

    void showDetailCTSP(ChiTietSanPham ctsp) {
        tfGiaBan.setText(String.valueOf(String.format("%.1f", ctsp.getGiaBan())));
        tfSoLuong.setText(String.valueOf(ctsp.getSoLuong()));
        taMoTa.setText(ctsp.getMoTa());

        selectedRowInSP(ctsp.getIdSanPham());
        modelChatLieu.setSelectedItem(thuocTinhRepository.getByID("chat_lieu", ctsp.getIdChatLieu()));
        modelCoAo.setSelectedItem(thuocTinhRepository.getByID("co_ao", ctsp.getIdCoAo()));
        modelKichThuoc.setSelectedItem(thuocTinhRepository.getByID("kich_thuoc", ctsp.getIdKichThuoc()));
        modelMauSac.setSelectedItem(thuocTinhRepository.getByID("mau_sac", ctsp.getIdMauSac()));
        modelNSX.setSelectedItem(thuocTinhRepository.getByID("nha_san_xuat", ctsp.getIdNSX()));
        modelPhongCach.setSelectedItem(thuocTinhRepository.getByID("phong_cach", ctsp.getIdPhongCach()));
        modelTheLoai.setSelectedItem(thuocTinhRepository.getByID("the_loai", ctsp.getIdTheLoai()));
        modelThietKe.setSelectedItem(thuocTinhRepository.getByID("thiet_ke", ctsp.getIdThietKe()));
        modelThuongHieu.setSelectedItem(thuocTinhRepository.getByID("thuong_hieu", ctsp.getIdThuongHieu()));

        if (ctsp.getHinhAnh() != null) {
            lblHinhAnhSPCT.setToolTipText(ctsp.getHinhAnh());
            lblHinhAnhSPCT.setIcon(ImageHelper.readImage(ctsp.getHinhAnh()));
//            System.out.println(ImageHelper.readImage(ctsp.getHinhAnh()));
        } else {
            lblHinhAnhSPCT.setToolTipText(null);
            lblHinhAnhSPCT.setIcon(null);
        }

        if (ctsp.getMaQr() != null) {
            String path = "";
            QRCodeHelper.generateQRCode(ctsp.getMaQr());
            lblQrCode.setIcon(QRCodeHelper.readQRCodeImage(ctsp.getMaQr()));
        } else {
            if (chiTietSanPhamRepository.getByID(ctsp.getId()) != null) {
                ctsp.setMaQr(QRCodeHelper.generateQRCode(ctsp.getId()));
                chiTietSanPhamRepository.updateQrCode(ctsp.getMaQr(), ctsp.getId());
                lblQrCode.setIcon(QRCodeHelper.readQRCodeImage(ctsp.getMaQr()));
            } else {
                lblQrCode.setIcon(null);
            }
        }

        if (ctsp.getTrangThai().equalsIgnoreCase("Hoạt động")) {
            rdHDCTSP.setSelected(true);
            return;
        }

        rdNgungHdCTSP.setSelected(true);
    }

    void getCTSPByIdSP() {
        if (tbSanPham.getSelectedRow() >= 0) {
            rowTbCTSP = -1;
            String id = String.valueOf(tbSanPham.getValueAt(tbSanPham.getSelectedRow(), 4));
            System.out.println(id);
            listCTSP = chiTietSanPhamRepository.getCTSPByIDSP(id, pageCTSP, limitCTSP);
            System.out.println(listCTSP);
            showDataCTSP(listCTSP);
        }

    }

    void showDetailSP(SanPham sp) {
        tfTenSp.setText(sp.getTen());
        tfMaSp.setText(sp.getMa());
        if (sp.getTrangThai().equalsIgnoreCase("Hoạt động")) {
            rdHDSP.setSelected(true);
            return;
        }
        rdNgungHdSP.setSelected(true);
    }

    // Chưa hoàn thiện
    void searchSPByProperties() {
        try {
            String value = tfTimKiemSP.getText();
            listSanPham = sanPhamRepository.getByProperties(value, pageSP, limitSP);
            this.showDataSP(listSanPham);
            this.statusEdit();
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    void searchCTSPByMaCTSP() {
        try {
            String tenCTSP = tfTimKiemCTSPByMa.getText();
            listCTSP = chiTietSanPhamRepository.searchByMaCTSP(tenCTSP, pageCTSP, limitCTSP);
            this.showDataCTSP(listCTSP);
            statusEdit();
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    void searchCTSPByTen() {
        try {
            String tenCTSP = tfTimCTSPByTen.getText();
            listCTSP = chiTietSanPhamRepository.searchByTen(tenCTSP, pageCTSP, limitCTSP);
            this.showDataCTSP(listCTSP);
            this.statusEdit();
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException(e);
        }
    }

    void deleteCTSP() {
        if (MsgHelper.confirm(this, "Ẩn sản phẩm chi tiết này nhớ?")) {
            try {
                String idCTSP = listCTSP.get(tbCTSP.getSelectedRow()).getId();
                chiTietSanPhamRepository.remove(idCTSP);
                MsgHelper.alert(this, "Ẩn chi tiết sản phẩm thành công");
                listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
                this.showDataCTSP(listCTSP);
                this.clearFormCTSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Xuất hiện lỗi");
                throw new RuntimeException(e);
            }
        }
    }

    void deletedSP() {
        if (MsgHelper.confirm(this, "Ẩn sản phẩm này nhớ?")) {
            try {
                String idSP = listSanPham.get(tbSanPham.getSelectedRow()).getId();
                sanPhamRepository.remove(idSP);
                MsgHelper.alert(this, "Ẩn sản phẩm thành công");
                listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
                this.showDataSP(listSanPham);
                this.clearFormSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Xuất hiện lỗi");
                throw new RuntimeException(e);
            }
        }
    }

    // Bổ sung check hình ảnh
    void updateCTSP() {
        if (MsgHelper.confirm(this, "Sửa sản phẩm chi tiết nhớ?")) {
            try {
                ChiTietSanPham ctsp = this.getFormCTSP();
                String gia = String.valueOf(ctsp.getGiaBan());
                String sl = String.valueOf(ctsp.getSoLuong());
                String idCTSP = listCTSP.get(tbCTSP.getSelectedRow()).getId();

                ExceptionCus.CheckEmpty(ctsp.getIdSanPham(), gia, sl);
                ExceptionCus.CheckValueInput("^(?!0*(\\.0+)?$)([1-9]\\d{0,11}(\\.\\d{1,2})?|999999999999)$", gia);
                ExceptionCus.CheckValueInput("^([1-9]\\d{0,2}|1000)$", sl);

                chiTietSanPhamRepository.update(ctsp, idCTSP);
                MsgHelper.alert(this, "Cập nhật chi tiết sản phẩm thành công");
                listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
                this.showDataCTSP(listCTSP);
                this.clearFormCTSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Kiểm tra các trường dữ liệu");
                throw new RuntimeException(e);
            }
        }
    }

    void updateSP() {
        if (MsgHelper.confirm(this, "Sửa sản phẩm nhớ?")) {
            try {
                SanPham sp = this.getFormSP();
                String idSP = listSanPham.get(tbSanPham.getSelectedRow()).getId();
                ExceptionCus.CheckEmpty(sp.getMa(), sp.getTen());
                sanPhamRepository.update(sp, idSP);
                MsgHelper.alert(this, "Cập nhật sản phẩm thành công");
                listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
                this.showDataSP(listSanPham);
                this.clearFormSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Kiểm tra các trường dữ liệu");
                throw new RuntimeException(e);
            }
        }
    }

    // Bổ sung check hình ảnh
    void addCTSP() {
        if (MsgHelper.confirm(this, "Thêm chi tiết sản phẩm này nhé?")) {
            try {
                ChiTietSanPham ctsp = this.getFormCTSP();
                ctsp.setMaQr(QRCodeHelper.generateQRCode(ctsp.getId()));
                System.out.println(ctsp + "485");

                String gia = String.valueOf(ctsp.getGiaBan());
                String sl = String.valueOf(ctsp.getSoLuong());

                ExceptionCus.CheckEmpty(ctsp.getIdSanPham(), gia, sl, lblHinhAnhSPCT.getToolTipText());
                ExceptionCus.CheckValueInput("^(?!0*(\\.0+)?$)([1-9]\\d{0,11}(\\.\\d{1,2})?|999999999999)$", gia);
                ExceptionCus.CheckValueInput("^([1-9]\\d{0,2}|1000)$", sl);

                chiTietSanPhamRepository.add(ctsp);
                MsgHelper.alert(this, "Thêm chi tiết sản phẩm thành công");
                listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
                this.showDataCTSP(listCTSP);
                this.clearFormCTSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Kiểm tra các trường dữ liệu");
                throw new RuntimeException(e);
            }
        }
    }

    void addSP() {
        if (MsgHelper.confirm(this, "Thêm sản phẩm này nhé?")) {
            try {
                SanPham sp = this.getFormSP();
                ExceptionCus.CheckEmpty(sp.getMa(), sp.getTen());
                sanPhamRepository.add(sp);
                MsgHelper.alert(this, "Thêm mới sản phẩm thành công");
                listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
                this.showDataSP(listSanPham);
                this.clearFormSP();
            } catch (Exception e) {
                MsgHelper.alert(this, "Kiểm tra các trường dữ liệu");
                throw new RuntimeException(e);
            }
        }
    }

    SanPham getFormSP() {
        SanPham sp = new SanPham();
        sp.setMa(tfMaSp.getText());
        sp.setTen(tfTenSp.getText());
        sp.setTrangThai(rdHDSP.isSelected() ? "Hoạt động" : "Ngưng hoạt động");
        return sp;
    }

    ChiTietSanPham getFormCTSP() {
        String id = String.valueOf(UUID.randomUUID());
        SanPham sanPham = getSPByRowinTable();
        ThuocTinh thuongHieu = (ThuocTinh) cbbThuongHieu.getSelectedItem();
        ThuocTinh theLoai = (ThuocTinh) cbbTheLoai.getSelectedItem();
        ThuocTinh thietKe = (ThuocTinh) cbbThietKe.getSelectedItem();
        ThuocTinh phongCach = (ThuocTinh) cbbPhongCach.getSelectedItem();
        ThuocTinh coAo = (ThuocTinh) cbbCoAo.getSelectedItem();
        ThuocTinh mauSac = (ThuocTinh) cbbMauSac.getSelectedItem();
        ThuocTinh chatLieu = (ThuocTinh) cbbChatLieu.getSelectedItem();
        ThuocTinh nhaSX = (ThuocTinh) cbbNSX.getSelectedItem();
        ThuocTinh kichThuoc = (ThuocTinh) cbbKichThuoc.getSelectedItem();
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(id);
        ctsp.setIdThuongHieu(thuongHieu.getId());
        ctsp.setIdChatLieu(chatLieu.getId());
        ctsp.setIdCoAo(coAo.getId());
        ctsp.setIdKichThuoc(kichThuoc.getId());
        ctsp.setIdMauSac(mauSac.getId());
        ctsp.setIdNSX(nhaSX.getId());
        ctsp.setIdPhongCach(phongCach.getId());
        ctsp.setIdTheLoai(theLoai.getId());
        ctsp.setIdThietKe(thietKe.getId());
        ctsp.setIdSanPham(sanPham.getId());
        ctsp.setSoLuong(Integer.parseInt(tfSoLuong.getText()));
        ctsp.setGiaBan(new BigDecimal(tfGiaBan.getText()));
        ctsp.setMoTa(taMoTa.getText());
        ctsp.setTrangThai(rdHDCTSP.isSelected() ? "Hoạt động" : "Ngưng hoạt động");
        ctsp.setHinhAnh(lblHinhAnhSPCT.getToolTipText());

        System.out.println(ctsp);
        return ctsp;
    }

    void showData() {
        listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
        listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
        showDataSP(listSanPham);
        showDataCTSP(listCTSP);
    }

    void showDataCTSP(List<ChiTietSanPham> list) {
        int intdex = 1;

        modelTbCTSP = (DefaultTableModel) tbCTSP.getModel();
        modelTbCTSP.setRowCount(0);
        for (ChiTietSanPham chiTietSanPham : list) {
            SanPham getSanPham = sanPhamRepository.getByID(chiTietSanPham.getIdSanPham());
            modelTbCTSP.addRow(new Object[]{
                intdex++,
                "# " + chiTietSanPham.getMaCTSP(),
                getSanPham.getMa(),
                getSanPham.getTen(),
                chiTietSanPham.getSoLuong(),
                String.format("%.1f", chiTietSanPham.getGiaBan()),
                chiTietSanPham.getMoTa(),
                chiTietSanPham.getTrangThai(),
                chiTietSanPham.getId()
            });
        }
    }

    void showDataSP(List<SanPham> list) {
        int index = 1;

        modelTbSP = (DefaultTableModel) tbSanPham.getModel();
        modelTbSP.setRowCount(0);
        for (SanPham sanPham : list) {
            modelTbSP.addRow(new Object[]{
                index++,
                sanPham.getMa(),
                sanPham.getTen(),
                sanPham.getTrangThai(),
                sanPham.getId()
            });
        }
    }

    public void initCbb() {
        initCbbThuongHieu();
        initCbbTheLoai();
        initCbbChatLieu();
        initCbbCoAo();
        initCbbKichThuoc();
        initCbbMauSac();
        initCbbNSX();
        initCbbThietKe();
        initCbbPhongCach();
    }

    void initCbbThuongHieu() {
        modelThuongHieu = (DefaultComboBoxModel) cbbThuongHieu.getModel();
        modelThuongHieu.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("thuong_hieu")) {
            modelThuongHieu.addElement(o);
        }
    }

    void initCbbTheLoai() {

        modelTheLoai = (DefaultComboBoxModel) cbbTheLoai.getModel();
        modelTheLoai.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("the_loai")) {
            modelTheLoai.addElement(o);
        }
    }

    void initCbbThietKe() {

        modelThietKe = (DefaultComboBoxModel) cbbThietKe.getModel();
        modelThietKe.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("thiet_ke")) {
            modelThietKe.addElement(o);
        }
    }

    void initCbbCoAo() {

        modelCoAo = (DefaultComboBoxModel) cbbCoAo.getModel();
        modelCoAo.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("co_ao")) {
            modelCoAo.addElement(o);
        }
    }

    void initCbbPhongCach() {

        modelPhongCach = (DefaultComboBoxModel) cbbPhongCach.getModel();
        modelPhongCach.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("phong_cach")) {
            modelPhongCach.addElement(o);
        }
    }

    void initCbbMauSac() {

        modelMauSac = (DefaultComboBoxModel) cbbMauSac.getModel();
        modelMauSac.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("mau_sac")) {
            modelMauSac.addElement(o);
        }
    }

    void initCbbChatLieu() {

        modelChatLieu = (DefaultComboBoxModel) cbbChatLieu.getModel();
        modelChatLieu.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("chat_lieu")) {
            modelChatLieu.addElement(o);
        }
    }

    void initCbbKichThuoc() {

        modelKichThuoc = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        modelKichThuoc.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("kich_thuoc")) {
            modelKichThuoc.addElement(o);
        }
    }

    void initCbbNSX() {

        modelNSX = (DefaultComboBoxModel) cbbNSX.getModel();
        modelNSX.removeAllElements();
        for (ThuocTinh o : thuocTinhRepository.getAll("nha_san_xuat")) {
            modelNSX.addElement(o);
        }
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnPrevPageSP = new javax.swing.JButton();
        btnNextPageSP = new javax.swing.JButton();
        lblPageSp = new javax.swing.JLabel();
        tfTimKiemSP = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfMaSp = new javax.swing.JTextField();
        tfTenSp = new javax.swing.JTextField();
        rdHDSP = new javax.swing.JRadioButton();
        rdNgungHdSP = new javax.swing.JRadioButton();
        btnThemSp = new javax.swing.JButton();
        btnSuaSp = new javax.swing.JButton();
        btnAnSp = new javax.swing.JButton();
        btnMoiSp = new javax.swing.JButton();
        btnXuatFileSP = new javax.swing.JButton();
        btnDanhSachAn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTSP = new javax.swing.JTable();
        btnPrevPageCTSP = new javax.swing.JButton();
        lblPageCTSP = new javax.swing.JLabel();
        btnNextPageCTSP = new javax.swing.JButton();
        tfTimCTSPByTen = new javax.swing.JTextField();
        btnXuatFileCTSP = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tfTimKiemCTSPByMa = new javax.swing.JTextField();
        lblHinhAnhSPCT = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbTheLoai = new javax.swing.JComboBox<>();
        cbbThietKe = new javax.swing.JComboBox<>();
        cbbPhongCach = new javax.swing.JComboBox<>();
        cbbCoAo = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        cbbNSX = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        rdHDCTSP = new javax.swing.JRadioButton();
        rdNgungHdCTSP = new javax.swing.JRadioButton();
        btnThuongHieu = new javax.swing.JButton();
        btnTheLoai = new javax.swing.JButton();
        btnThietKe = new javax.swing.JButton();
        btnPhongCach = new javax.swing.JButton();
        btnCoAo = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnChatLieu = new javax.swing.JButton();
        btnKichThuoc = new javax.swing.JButton();
        btnNSX = new javax.swing.JButton();
        btnThemCTSP = new javax.swing.JButton();
        btnSuaCTSP = new javax.swing.JButton();
        btnAnCTSP = new javax.swing.JButton();
        btnMoiCTSP = new javax.swing.JButton();
        tfSoLuong = new javax.swing.JTextField();
        tfGiaBan = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDsCTSPAn = new javax.swing.JButton();
        lblQrCode = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Trạng thái", "ID"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        btnPrevPageSP.setText("<");
        btnPrevPageSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageSPActionPerformed(evt);
            }
        });

        btnNextPageSP.setText(">");
        btnNextPageSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageSPActionPerformed(evt);
            }
        });

        lblPageSp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPageSp.setText("Trang 1");

        tfTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfTimKiemSPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemSPKeyTyped(evt);
            }
        });

        jLabel4.setText("Trạng thái");

        jLabel2.setText("Mã SP");

        jLabel3.setText("Tên SP");

        buttonGroup1.add(rdHDSP);
        rdHDSP.setSelected(true);
        rdHDSP.setText("Hoạt động");

        buttonGroup1.add(rdNgungHdSP);
        rdNgungHdSP.setText("Ngưng hoạt động");

        btnThemSp.setText("Thêm");
        btnThemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpActionPerformed(evt);
            }
        });

        btnSuaSp.setText("Sửa");
        btnSuaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSpActionPerformed(evt);
            }
        });

        btnAnSp.setText("Ẩn");
        btnAnSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnSpActionPerformed(evt);
            }
        });

        btnMoiSp.setText("Mới");
        btnMoiSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiSpActionPerformed(evt);
            }
        });

        btnXuatFileSP.setText("Xuất file");
        btnXuatFileSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileSPActionPerformed(evt);
            }
        });

        btnDanhSachAn.setText("Danh sách ẩn");
        btnDanhSachAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachAnActionPerformed(evt);
            }
        });

        jLabel5.setText("Tìm kiếm ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPrevPageSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPageSp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnNextPageSP)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTimKiemSP))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfTenSp))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfMaSp))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(rdHDSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdNgungHdSP)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSuaSp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnSp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemSp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnXuatFileSP)
                        .addGap(118, 118, 118)
                        .addComponent(btnDanhSachAn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrevPageSP)
                            .addComponent(btnNextPageSP)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblPageSp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdHDSP)
                    .addComponent(rdNgungHdSP)
                    .addComponent(btnAnSp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoiSp)
                    .addComponent(btnXuatFileSP)
                    .addComponent(btnDanhSachAn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết sản phẩm"));

        tbCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã CTSP", "Mã SP", "Tên SP", "SL", "Giá bán", "Mô tả", "Trạng thái", "ID"
            }
        ));
        tbCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTSP);

        btnPrevPageCTSP.setText("<");
        btnPrevPageCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevPageCTSPActionPerformed(evt);
            }
        });

        lblPageCTSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPageCTSP.setText("Trang 1");

        btnNextPageCTSP.setText(">");
        btnNextPageCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageCTSPActionPerformed(evt);
            }
        });

        tfTimCTSPByTen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfTimCTSPByTenCaretUpdate(evt);
            }
        });
        tfTimCTSPByTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimCTSPByTenKeyTyped(evt);
            }
        });

        btnXuatFileCTSP.setText("Xuất file");
        btnXuatFileCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileCTSPActionPerformed(evt);
            }
        });

        jLabel18.setText("Tìm kiếm theo tên");

        jLabel20.setText("Tìm kiếm theo mã");

        tfTimKiemCTSPByMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemCTSPByMaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnPrevPageCTSP)
                .addGap(4, 4, 4)
                .addComponent(lblPageCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnNextPageCTSP)
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTimKiemCTSPByMa, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(tfTimCTSPByTen))
                .addGap(49, 49, 49)
                .addComponent(btnXuatFileCTSP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTimCTSPByTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatFileCTSP)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTimKiemCTSPByMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addComponent(btnPrevPageCTSP)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblPageCTSP))
                    .addComponent(btnNextPageCTSP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblHinhAnhSPCT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnhSPCT.setText("Hình ảnh");
        lblHinhAnhSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhSPCTMouseClicked(evt);
            }
        });

        jLabel6.setText("Thương hiệu");

        jLabel7.setText("Thể loại");

        jLabel8.setText("Thiết kế");

        jLabel9.setText("Phong cách");

        jLabel10.setText("Cổ áo");

        jLabel11.setText("Màu sắc");

        jLabel12.setText("Chất liệu");

        jLabel13.setText("Kích thước");

        jLabel14.setText("Nhà sản xuất");

        jLabel15.setText("Số lượng");

        jLabel16.setText("Giá bán");

        jLabel17.setText("Trạng thái");

        jLabel19.setText("Mô tả");

        taMoTa.setColumns(20);
        taMoTa.setRows(5);
        jScrollPane1.setViewportView(taMoTa);

        buttonGroup2.add(rdHDCTSP);
        rdHDCTSP.setSelected(true);
        rdHDCTSP.setText("Hoạt động");

        buttonGroup2.add(rdNgungHdCTSP);
        rdNgungHdCTSP.setText("Ngưng hoạt động");

        btnThuongHieu.setText("+");
        btnThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuActionPerformed(evt);
            }
        });

        btnTheLoai.setText("+");
        btnTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheLoaiActionPerformed(evt);
            }
        });

        btnThietKe.setText("+");
        btnThietKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThietKeActionPerformed(evt);
            }
        });

        btnPhongCach.setText("+");
        btnPhongCach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhongCachActionPerformed(evt);
            }
        });

        btnCoAo.setText("+");
        btnCoAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoAoActionPerformed(evt);
            }
        });

        btnMauSac.setText("+");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        btnChatLieu.setText("+");
        btnChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuActionPerformed(evt);
            }
        });

        btnKichThuoc.setText("+");
        btnKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichThuocActionPerformed(evt);
            }
        });

        btnNSX.setText("+");
        btnNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNSXActionPerformed(evt);
            }
        });

        btnThemCTSP.setText("Thêm");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnSuaCTSP.setText("Sửa");
        btnSuaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTSPActionPerformed(evt);
            }
        });

        btnAnCTSP.setText("Ẩn");
        btnAnCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnCTSPActionPerformed(evt);
            }
        });

        btnMoiCTSP.setText("Mới");
        btnMoiCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiCTSPActionPerformed(evt);
            }
        });

        tfSoLuong.setText("-1");

        tfGiaBan.setText("-1.0");

        btnLamMoi.setText("O");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel1.setText("Làm mới");

        btnDsCTSPAn.setText("Danh sách ẩn");
        btnDsCTSPAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDsCTSPAnActionPerformed(evt);
            }
        });

        lblQrCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQrCode.setText("QR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(615, 615, 615)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLamMoi)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbPhongCach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnThuongHieu)
                                            .addComponent(btnTheLoai)
                                            .addComponent(btnThietKe)
                                            .addComponent(btnPhongCach)
                                            .addComponent(btnCoAo)
                                            .addComponent(btnMauSac))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel17))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rdHDCTSP)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(rdNgungHdCTSP))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(tfGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnDsCTSPAn))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnMoiCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnNSX)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnAnCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(12, 12, 12)
                                                        .addComponent(btnChatLieu))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(12, 12, 12)
                                                        .addComponent(btnKichThuoc)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnSuaCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnThemCTSP)))))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblHinhAnhSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(lblQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAnCTSP, btnDsCTSPAn, btnMoiCTSP, btnSuaCTSP, btnThemCTSP});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHinhAnhSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel6)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel7)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel8)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel9)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel10)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(cbbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(cbbThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbPhongCach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbCoAo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnThuongHieu)
                                        .addGap(19, 19, 19)
                                        .addComponent(btnTheLoai)
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnThietKe)
                                            .addComponent(jLabel14)
                                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNSX)
                                            .addComponent(btnAnCTSP))
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnPhongCach)
                                            .addComponent(jLabel15)
                                            .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnMoiCTSP))
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnCoAo)
                                            .addComponent(jLabel16)
                                            .addComponent(tfGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnDsCTSPAn))
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnMauSac)
                                            .addComponent(jLabel17)
                                            .addComponent(rdNgungHdCTSP)
                                            .addComponent(rdHDCTSP)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel12)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel13))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnChatLieu))
                                    .addGap(19, 19, 19)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnKichThuoc)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(btnThemCTSP)
                                    .addGap(17, 17, 17)
                                    .addComponent(btnSuaCTSP)
                                    .addGap(136, 136, 136))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLamMoi)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpActionPerformed
        // TODO add your handling code here:
        addSP();
    }//GEN-LAST:event_btnThemSpActionPerformed

    private void btnSuaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSpActionPerformed
        // TODO add your handling code here:
        updateSP();
    }//GEN-LAST:event_btnSuaSpActionPerformed

    private void btnAnSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnSpActionPerformed
        // TODO add your handling code here:
        deletedSP();
    }//GEN-LAST:event_btnAnSpActionPerformed

    private void btnDanhSachAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachAnActionPerformed
        // TODO add your handling code here:
        loadDataAfterUnRemove(1);
    }//GEN-LAST:event_btnDanhSachAnActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        initCbb();
        showData();
        clearFormCTSP();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("thuong_hieu");
    }//GEN-LAST:event_btnThuongHieuActionPerformed


    private void btnTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheLoaiActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("the_loai");
    }//GEN-LAST:event_btnTheLoaiActionPerformed

    private void btnThietKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThietKeActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("thiet_ke");
    }//GEN-LAST:event_btnThietKeActionPerformed

    private void btnPhongCachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhongCachActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("phong_cach");
    }//GEN-LAST:event_btnPhongCachActionPerformed

    private void btnCoAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoAoActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("co_ao");
    }//GEN-LAST:event_btnCoAoActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("mau_sac");
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("chat_lieu");
    }//GEN-LAST:event_btnChatLieuActionPerformed

    private void btnKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichThuocActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("kich_thuoc");
    }//GEN-LAST:event_btnKichThuocActionPerformed

    private void btnNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNSXActionPerformed
        // TODO add your handling code here:
        setFormThuocTinh("nha_san_xuat");
    }//GEN-LAST:event_btnNSXActionPerformed

    private void tfTimKiemSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSPKeyPressed

    }//GEN-LAST:event_tfTimKiemSPKeyPressed

    private void tfTimKiemSPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSPKeyTyped
        // TODO add your handling code here:
        searchSPByProperties();
    }//GEN-LAST:event_tfTimKiemSPKeyTyped

    private void tfTimCTSPByTenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimCTSPByTenKeyTyped
        // TODO add your handling code here:
        searchCTSPByTen();
    }//GEN-LAST:event_tfTimCTSPByTenKeyTyped

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        showDetailSP(getSPByRowinTable());
        getCTSPByIdSP();
        this.statusEdit();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnMoiSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiSpActionPerformed
        // TODO add your handling code here:
        clearFormSP();
    }//GEN-LAST:event_btnMoiSpActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        // TODO add your handling code here:
        addCTSP();
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void btnSuaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTSPActionPerformed
        // TODO add your handling code here:
        updateCTSP();
    }//GEN-LAST:event_btnSuaCTSPActionPerformed

    private void btnAnCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnCTSPActionPerformed
        // TODO add your handling code here:
        deleteCTSP();
    }//GEN-LAST:event_btnAnCTSPActionPerformed

    private void btnDsCTSPAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDsCTSPAnActionPerformed
        // TODO add your handling code here:
        loadDataAfterUnRemove(0);
    }//GEN-LAST:event_btnDsCTSPAnActionPerformed

    private void tbCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseClicked
        // TODO add your handling code here:
        showDetailCTSP(getCTSPByRowInTable());
        this.statusEdit();
    }//GEN-LAST:event_tbCTSPMouseClicked

    private void btnMoiCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiCTSPActionPerformed
        // TODO add your handling code here:
        clearFormCTSP();
    }//GEN-LAST:event_btnMoiCTSPActionPerformed

    private void btnNextPageSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageSPActionPerformed
        // TODO add your handling code here:
        nextPageSP();
    }//GEN-LAST:event_btnNextPageSPActionPerformed

    private void btnPrevPageSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageSPActionPerformed
        // TODO add your handling code here:
        prevPageSP();
    }//GEN-LAST:event_btnPrevPageSPActionPerformed

    private void btnNextPageCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageCTSPActionPerformed
        // TODO add your handling code here:
        nextPageCTSP();
    }//GEN-LAST:event_btnNextPageCTSPActionPerformed

    private void tfTimKiemCTSPByMaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemCTSPByMaKeyTyped
        // TODO add your handling code here:
//        searchCTSPByMaCTSP();
    }//GEN-LAST:event_tfTimKiemCTSPByMaKeyTyped

    private void lblHinhAnhSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhSPCTMouseClicked
        // TODO add your handling code here:
        selectImage();
    }//GEN-LAST:event_lblHinhAnhSPCTMouseClicked

    private void btnPrevPageCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevPageCTSPActionPerformed
        // TODO add your handling code here:
        prevPageCTSP();
    }//GEN-LAST:event_btnPrevPageCTSPActionPerformed

    public void excel(JTable tb) {
        try {
            Excel.outExcel((DefaultTableModel) tb.getModel());
            MsgHelper.alert(this, "Thành công");
        } catch (Exception e) {
            MsgHelper.alert(this, "Xuất hiện lỗi");
            throw new RuntimeException();
        }
    }

    private void btnXuatFileSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileSPActionPerformed
        // TODO add your handling code here:
        excel(tbSanPham);
    }//GEN-LAST:event_btnXuatFileSPActionPerformed

    private void btnXuatFileCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileCTSPActionPerformed
        // TODO add your handling code here:
        excel(tbCTSP);
    }//GEN-LAST:event_btnXuatFileCTSPActionPerformed

    private void tfTimCTSPByTenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfTimCTSPByTenCaretUpdate
        // TODO add your handling code here:
//        searchCTSPByTen();
        
    }//GEN-LAST:event_tfTimCTSPByTenCaretUpdate

    void loadDataAfterUnRemove(int type) {
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        if (type == 0) {
            DsCTSPAnJDialog dialog = new DsCTSPAnJDialog(jFrame, true);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    listCTSP = chiTietSanPhamRepository.getAll(pageCTSP, limitCTSP);
                    showDataCTSP(listCTSP);
                }
            });
            dialog.setVisible(true);
        } else {
            DsSPAnJDialog dialog = new DsSPAnJDialog(jFrame, true);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    listSanPham = sanPhamRepository.getAll(pageSP, limitSP);
                    showDataSP(listSanPham);
                }
            });
            dialog.setVisible(true);
        }
    }

    void setFormThuocTinh(String thuocTinh) {
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        ThuocTinhJDialog dialog = new ThuocTinhJDialog(jFrame, true, thuocTinh);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                initCbb();
            }
        });
        dialog.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnCTSP;
    private javax.swing.JButton btnAnSp;
    private javax.swing.JButton btnChatLieu;
    private javax.swing.JButton btnCoAo;
    private javax.swing.JButton btnDanhSachAn;
    private javax.swing.JButton btnDsCTSPAn;
    private javax.swing.JButton btnKichThuoc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnMoiCTSP;
    private javax.swing.JButton btnMoiSp;
    private javax.swing.JButton btnNSX;
    private javax.swing.JButton btnNextPageCTSP;
    private javax.swing.JButton btnNextPageSP;
    private javax.swing.JButton btnPhongCach;
    private javax.swing.JButton btnPrevPageCTSP;
    private javax.swing.JButton btnPrevPageSP;
    private javax.swing.JButton btnSuaCTSP;
    private javax.swing.JButton btnSuaSp;
    private javax.swing.JButton btnTheLoai;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnThemSp;
    private javax.swing.JButton btnThietKe;
    private javax.swing.JButton btnThuongHieu;
    private javax.swing.JButton btnXuatFileCTSP;
    private javax.swing.JButton btnXuatFileSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbCoAo;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> cbbPhongCach;
    private javax.swing.JComboBox<String> cbbTheLoai;
    private javax.swing.JComboBox<String> cbbThietKe;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHinhAnhSPCT;
    private javax.swing.JLabel lblPageCTSP;
    private javax.swing.JLabel lblPageSp;
    private javax.swing.JLabel lblQrCode;
    private javax.swing.JRadioButton rdHDCTSP;
    private javax.swing.JRadioButton rdHDSP;
    private javax.swing.JRadioButton rdNgungHdCTSP;
    private javax.swing.JRadioButton rdNgungHdSP;
    private javax.swing.JTextArea taMoTa;
    private javax.swing.JTable tbCTSP;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField tfGiaBan;
    private javax.swing.JTextField tfMaSp;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfTenSp;
    private javax.swing.JTextField tfTimCTSPByTen;
    private javax.swing.JTextField tfTimKiemCTSPByMa;
    private javax.swing.JTextField tfTimKiemSP;
    // End of variables declaration//GEN-END:variables
}
