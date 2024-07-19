USE [master]
GO
/****** Object:  Database [AMITYC_SHOP]    Script Date: 11/12/2023 11:49:06 PM ******/
CREATE DATABASE [AMITYC_SHOP]
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AMITYC_SHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AMITYC_SHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AMITYC_SHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AMITYC_SHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET  ENABLE_BROKER 
GO
ALTER DATABASE [AMITYC_SHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AMITYC_SHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET RECOVERY FULL 
GO
ALTER DATABASE [AMITYC_SHOP] SET  MULTI_USER 
GO
ALTER DATABASE [AMITYC_SHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AMITYC_SHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AMITYC_SHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AMITYC_SHOP] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AMITYC_SHOP] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AMITYC_SHOP] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AMITYC_SHOP', N'ON'
GO
ALTER DATABASE [AMITYC_SHOP] SET QUERY_STORE = OFF
GO
USE [AMITYC_SHOP]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]
ADD [ma_qr] [varchar](255) NULL;
/****** Object:  Table [dbo].[chat_lieu]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chat_lieu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chi_tiet_san_pham]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chi_tiet_san_pham](
	[id] [uniqueidentifier] NOT NULL,
	[id_san_pham] [uniqueidentifier] NULL,
	[id_thuong_hieu] [uniqueidentifier] NULL,
	[id_the_loai] [uniqueidentifier] NULL,
	[id_thiet_ke] [uniqueidentifier] NULL,
	[id_phong_cach] [uniqueidentifier] NULL,
	[id_co_ao] [uniqueidentifier] NULL,
	[id_mau_sac] [uniqueidentifier] NULL,
	[id_chat_lieu] [uniqueidentifier] NULL,
	[id_kich_thuoc] [uniqueidentifier] NULL,
	[id_nha_san_xuat] [uniqueidentifier] NULL,
	[hinh_anh] [varchar](50) NULL,
	[so_luong_ton] [int] NULL,
	[gia_nhap] [float] NULL,
	[gia_ban] [float] NULL,
	[ngay_nhap_kho] [date] NULL,
	[ngay_sua] [date] NULL,
	[mo_ta] [nvarchar](255) NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[co_ao]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[co_ao](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[gio_hang]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gio_hang](
	[id] [uniqueidentifier] NOT NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[gio_hang_chi_tiet]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gio_hang_chi_tiet](
	[id] [uniqueidentifier] NOT NULL,
	[id_ctsp] [uniqueidentifier] NULL,
	[id_gio_hang] [uniqueidentifier] NULL,
	[so_luong] [int] NULL,
	[gia] [float] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don](
	[id] [uniqueidentifier] NOT NULL,
	[id_nguoi_tao] [uniqueidentifier] NULL,
	[id_khach_hang] [uniqueidentifier] NULL,
	[id_voucher] [uniqueidentifier] NULL,
	[ten_nguoi_nhan] [nvarchar](50) NULL,
	[so_dien_thoai] [varchar](20) NULL,
	[dia_chi] [nvarchar](255) NULL,
	[gia_tien] [float] NULL,
	[giam_gia] [float] NULL,
	[tong_tien] [float] NULL,
	[ngay_xac_nhan] [date] NULL,
	[ngay_van_chuyen] [date] NULL,
	[ngay_nhan] [date] NULL,
	[ghi_chu] [nvarchar](255) NULL,
	[tien_van_chuyen] [float] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don_chi_tiet]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don_chi_tiet](
	[id] [uniqueidentifier] NOT NULL,
	[id_ctsp] [uniqueidentifier] NULL,
	[id_hoa_don] [uniqueidentifier] NULL,
	[so_luong] [int] NULL,
	[gia_tien] [float] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khach_hang]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khach_hang](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[so_dien_thoai] [varchar](20) NULL,
	[dia_chi] [nvarchar](255) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[nguoi_tao] [varchar](50) NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kich_thuoc]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kich_thuoc](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[mau_sac]    Script Date: 11/12/2023 11:49:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[mau_sac](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nguoi_dung]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nguoi_dung](
	[id] [uniqueidentifier] NOT NULL,
	[ma_nguoi_dung] [varchar](20) NULL,
	[mat_khau] [varchar](20) NULL,
	[ho_ten] [nvarchar](50) NULL,
	[ngay_sinh] [date] NULL,
	[gioi_tinh] [bit] NULL,
	[hinh_anh] [varchar](50) NULL,
	[so_dien_thoai] [varchar](20) NULL,
	[email] [varchar](50) NULL,
	[dia_chi] [nvarchar](255) NULL,
	[vai_tro] [bit] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nha_san_xuat]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nha_san_xuat](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phong_cach]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phong_cach](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phuong_thuc_thanh_toan]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phuong_thuc_thanh_toan](
	[id] [uniqueidentifier] NOT NULL,
	[phuong_thuc] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pttt_chi_tiet]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pttt_chi_tiet](
	[id] [uniqueidentifier] NOT NULL,
	[id_hoa_don] [uniqueidentifier] NULL,
	[id_pttt] [uniqueidentifier] NULL,
	[so_tien_mat] [float] NULL,
	[so_tien_ck] [float] NULL,
	[tong_tien] [float] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[san_pham]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[san_pham](
	[id] [uniqueidentifier] NOT NULL,
	[ma_san_pham] [varchar](10) NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[the_loai]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[the_loai](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thiet_ke]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thiet_ke](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thuong_hieu]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thuong_hieu](
	[id] [uniqueidentifier] NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 11/12/2023 11:49:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[id] [uniqueidentifier] NOT NULL,
	[ma_voucher] [varchar](20) NULL,
	[ten] [nvarchar](255) NULL,
	[gia_tri] [float] NULL,
	[so_luong] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[ngay_bat_dau] [date] NULL,
	[ngay_ket_thuc] [date] NULL,
	[trang_thai] [nvarchar](50) NULL,
	[deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[chat_lieu] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] ADD  DEFAULT (getdate()) FOR [ngay_nhap_kho]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[co_ao] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[co_ao] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[co_ao] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[gio_hang] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_xac_nhan]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_van_chuyen]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_nhan]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_don] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[khach_hang] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[khach_hang] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[khach_hang] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[kich_thuoc] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[mau_sac] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[nguoi_dung] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[nha_san_xuat] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[nha_san_xuat] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[nha_san_xuat] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[phong_cach] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[phong_cach] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[phong_cach] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[phuong_thuc_thanh_toan] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[phuong_thuc_thanh_toan] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[phuong_thuc_thanh_toan] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[pttt_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[pttt_chi_tiet] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[pttt_chi_tiet] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[san_pham] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[the_loai] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[the_loai] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[the_loai] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[thiet_ke] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[thiet_ke] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[thiet_ke] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_chat_lieu] FOREIGN KEY([id_chat_lieu])
REFERENCES [dbo].[chat_lieu] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_chat_lieu]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_co_ao] FOREIGN KEY([id_co_ao])
REFERENCES [dbo].[co_ao] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_co_ao]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_kich_thuoc] FOREIGN KEY([id_kich_thuoc])
REFERENCES [dbo].[kich_thuoc] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_kich_thuoc]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_mau_sac] FOREIGN KEY([id_mau_sac])
REFERENCES [dbo].[mau_sac] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_mau_sac]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_nha_san_xuat] FOREIGN KEY([id_nha_san_xuat])
REFERENCES [dbo].[nha_san_xuat] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_nha_san_xuat]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_phong_cach] FOREIGN KEY([id_phong_cach])
REFERENCES [dbo].[phong_cach] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_phong_cach]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_san_pham] FOREIGN KEY([id_san_pham])
REFERENCES [dbo].[san_pham] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_san_pham]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_the_loai] FOREIGN KEY([id_the_loai])
REFERENCES [dbo].[the_loai] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_the_loai]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_thiet_ke] FOREIGN KEY([id_thiet_ke])
REFERENCES [dbo].[thiet_ke] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_thiet_ke]
GO
ALTER TABLE [dbo].[chi_tiet_san_pham]  WITH CHECK ADD  CONSTRAINT [FK_chi_tiet_san_pham.id_thuong_hieu] FOREIGN KEY([id_thuong_hieu])
REFERENCES [dbo].[thuong_hieu] ([id])
GO
ALTER TABLE [dbo].[chi_tiet_san_pham] CHECK CONSTRAINT [FK_chi_tiet_san_pham.id_thuong_hieu]
GO
ALTER TABLE [dbo].[gio_hang]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang_khach_hang] FOREIGN KEY([id])
REFERENCES [dbo].[khach_hang] ([id])
GO
ALTER TABLE [dbo].[gio_hang] CHECK CONSTRAINT [FK_gio_hang_khach_hang]
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang_chi_tiet.id_ctsp] FOREIGN KEY([id_ctsp])
REFERENCES [dbo].[chi_tiet_san_pham] ([id])
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] CHECK CONSTRAINT [FK_gio_hang_chi_tiet.id_ctsp]
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_gio_hang_chi_tiet.id_gio_hang] FOREIGN KEY([id_gio_hang])
REFERENCES [dbo].[gio_hang] ([id])
GO
ALTER TABLE [dbo].[gio_hang_chi_tiet] CHECK CONSTRAINT [FK_gio_hang_chi_tiet.id_gio_hang]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_khach_hang] FOREIGN KEY([id_khach_hang])
REFERENCES [dbo].[khach_hang] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_khach_hang]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_nguoi_tao] FOREIGN KEY([id_nguoi_tao])
REFERENCES [dbo].[nguoi_dung] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_nguoi_tao]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don.id_voucher] FOREIGN KEY([id_voucher])
REFERENCES [dbo].[voucher] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don.id_voucher]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet.id_ctsp] FOREIGN KEY([id_ctsp])
REFERENCES [dbo].[chi_tiet_san_pham] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet.id_ctsp]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet.id_hoa_don] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet.id_hoa_don]
GO
ALTER TABLE [dbo].[pttt_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_pttt_chi_tiet.id_hoa_don] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[pttt_chi_tiet] CHECK CONSTRAINT [FK_pttt_chi_tiet.id_hoa_don]
GO
ALTER TABLE [dbo].[pttt_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_pttt_chi_tiet.id_pttt] FOREIGN KEY([id_pttt])
REFERENCES [dbo].[phuong_thuc_thanh_toan] ([id])
GO
ALTER TABLE [dbo].[pttt_chi_tiet] CHECK CONSTRAINT [FK_pttt_chi_tiet.id_pttt]
GO
USE [master]
GO
ALTER DATABASE [AMITYC_SHOP] SET  READ_WRITE 
GO

USE [AMITYC_SHOP]
GO

INSERT INTO san_pham (id, ma_san_pham, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('D7F63232-4A78-4C6C-81A0-B05D4BC6F44E', 'SP001', N'Áo phông Super Sayza', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('C5B45149-5F1D-4CB3-9479-B367955E940A', 'SP002', N'Áo phông 210', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('3EA4DE49-2F20-4719-A4FA-CD6E58DECC5F', 'SP003', N'Áo phông idol Emi', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from san_pham

INSERT INTO thuong_hieu (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('490CA78B-053E-4D18-883C-4A0ED0FA1FD5', N'Nike', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('4ADE83A7-624F-41D1-BE61-6D69689AF0B7', N'Adidas', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('04455736-B721-494C-9FE9-C61BA58ECD23', N'Uniqlo', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from thuong_hieu

INSERT INTO the_loai (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('7A6C2DA2-A4D5-454B-BB6E-473D4533D446', N'Áo Polo nam', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('1AB1145E-D246-4EDD-A63E-75EAE685DC38', N'Áo Polo nữ', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('3992E48F-1449-4D02-9F4E-F91A1FBBB719', N'Áo Polo trẻ em', '2023-11-01', '2023-11-01', N'Mới', 0);
select * from the_loai

INSERT INTO thiet_ke (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('04C70CCC-68CC-4304-B7B5-2C2606CDEBF8', N'Đơn giản', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('C5B45149-5F1D-4CB3-9479-B367955E940A', N'In họa tiết', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('3EA4DE49-2F20-4719-A4FA-CD6E58DECC5F', N'Độc quyền', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from thiet_ke

INSERT INTO phong_cach (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('AD0E3863-CF41-43BE-943E-3ED09BB01664', N'Phong cách thể thao', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('766F95DE-6E98-4EDC-9697-78C923B7D115', N'Phong cách thời trang', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('E3C33DDB-8193-4C39-87AE-BB3A918A5495', N'Phong cách cổ điển', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from phong_cach

INSERT INTO co_ao (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('400C0924-7BEA-41F9-BE00-166DFCEBDD1D', N'Cổ tròn', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('2CC0F887-C65E-465C-8F45-2E1BA79E0062', N'Cổ V', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('A118E52B-3503-491F-BD27-458A5BD0AEC9', N'Cổ bẻ', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from co_ao

INSERT INTO mau_sac (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('48420E14-8154-4D6C-B3F7-4E3EEB6CF817', N'Xanh', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('972B5C61-8FF0-4B29-86E0-57E8E63EF590', N'Đen', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('A926FD87-C60D-4AE3-9328-7D3BF42364CD', N'Trắng', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from mau_sac

INSERT INTO chat_lieu (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('E0E1BB52-0E25-46EE-AE31-172A225AE5BD', N'Vải cotton', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('79A0C0E6-58DD-4CB9-9A62-291DCDD216A3', N'Vải polyester', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('06C132B3-4B82-4458-BBD2-2C3A562679FF', N'Vải linen', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from chat_lieu

INSERT INTO kich_thuoc (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('92273607-79D2-4EE2-A098-C70C802AE2AD', N'Size S', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('0C141556-DCC7-4A20-AB1F-D7559252345E', N'Size M', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('44CC0AFB-77C6-41A5-BFD8-F579B5BC955E', N'Size XL', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from kich_thuoc

INSERT INTO nha_san_xuat (id, ten, ngay_tao, ngay_sua, trang_thai, deleted)
VALUES
  ('E150B015-1B6B-4E03-AA68-22E0140A0625', N'Sandro', '2023-10-30', '2023-10-30', N'Mới', 0),
  ('0C10999E-5F47-48CE-8BC5-B69C3C42B4BB', N'Canifa', '2023-10-31', '2023-10-31', N'Mới', 0),
  ('AFD3BD00-580E-4D1C-998C-C23FA2D7EED2', N'Coolmate', '2023-11-01', '2023-11-01', N'Mới', 0);

select * from nha_san_xuat

INSERT INTO chi_tiet_san_pham (id, id_san_pham, id_thuong_hieu, id_mau_sac, id_the_loai, id_chat_lieu, id_kich_thuoc, id_nha_san_xuat, id_thiet_ke, id_phong_cach, id_co_ao, so_luong_ton, gia_nhap, gia_ban, ngay_nhap_kho, ngay_sua, mo_ta, trang_thai, deleted, hinh_anh)
VALUES 
  ('FEB6B3B9-F5A7-4486-BEE3-1435536282D2', 'D7F63232-4A78-4C6C-81A0-B05D4BC6F44E', '490CA78B-053E-4D18-883C-4A0ED0FA1FD5', '48420E14-8154-4D6C-B3F7-4E3EEB6CF817', '7A6C2DA2-A4D5-454B-BB6E-473D4533D446', '79A0C0E6-58DD-4CB9-9A62-291DCDD216A3', '44CC0AFB-77C6-41A5-BFD8-F579B5BC955E', 'E150B015-1B6B-4E03-AA68-22E0140A0625', '04C70CCC-68CC-4304-B7B5-2C2606CDEBF8', 'AD0E3863-CF41-43BE-943E-3ED09BB01664', '400C0924-7BEA-41F9-BE00-166DFCEBDD1D', 100, 180000, 240000, '2023-10-30', '2023-10-31', N'Áo vip', N'Mới', 0, 'anh1.png'),
  ('AE2BD82B-E2C2-4163-A341-3D8AF4DCEC24', 'C5B45149-5F1D-4CB3-9479-B367955E940A', '490CA78B-053E-4D18-883C-4A0ED0FA1FD5', '972B5C61-8FF0-4B29-86E0-57E8E63EF590', '1AB1145E-D246-4EDD-A63E-75EAE685DC38', '79A0C0E6-58DD-4CB9-9A62-291DCDD216A3', '44CC0AFB-77C6-41A5-BFD8-F579B5BC955E', 'E150B015-1B6B-4E03-AA68-22E0140A0625', '3EA4DE49-2F20-4719-A4FA-CD6E58DECC5F', 'AD0E3863-CF41-43BE-943E-3ED09BB01664', '400C0924-7BEA-41F9-BE00-166DFCEBDD1D', 10, 450000, 540000, '2023-10-30', '2023-10-31', N'Áo xịn', N'Mới', 0, 'anh2.png'),
  ('FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9', 'C5B45149-5F1D-4CB3-9479-B367955E940A', '4ADE83A7-624F-41D1-BE61-6D69689AF0B7', 'A926FD87-C60D-4AE3-9328-7D3BF42364CD', '7A6C2DA2-A4D5-454B-BB6E-473D4533D446', '06C132B3-4B82-4458-BBD2-2C3A562679FF', '44CC0AFB-77C6-41A5-BFD8-F579B5BC955E', 'E150B015-1B6B-4E03-AA68-22E0140A0625', '04C70CCC-68CC-4304-B7B5-2C2606CDEBF8', 'AD0E3863-CF41-43BE-943E-3ED09BB01664', '400C0924-7BEA-41F9-BE00-166DFCEBDD1D', 15, 300000, 340000, '2023-10-30', '2023-10-31', N'Áo pro', N'Mới', 0, 'anh3.png'),
  (NEWID(), 'C5B45149-5F1D-4CB3-9479-B367955E940A', '4ADE83A7-624F-41D1-BE61-6D69689AF0B7', '48420E14-8154-4D6C-B3F7-4E3EEB6CF817', '7A6C2DA2-A4D5-454B-BB6E-473D4533D446', '06C132B3-4B82-4458-BBD2-2C3A562679FF', '44CC0AFB-77C6-41A5-BFD8-F579B5BC955E', 'E150B015-1B6B-4E03-AA68-22E0140A0625', '04C70CCC-68CC-4304-B7B5-2C2606CDEBF8', 'AD0E3863-CF41-43BE-943E-3ED09BB01664', '400C0924-7BEA-41F9-BE00-166DFCEBDD1D', 15, 300000, 340000, '2023-10-30', '2023-10-31', N'Áo pro', N'Mới', 0, 'anh4.png');

select * from chi_tiet_san_pham

INSERT INTO nguoi_dung 
VALUES ('06AB237C-653A-4FE0-AB06-DB4074ECB2BC', 'THUONGCMNV001', 'a1234b5c', N'Chu Minh Thương', '2004-03-11', 1, 'thuong.png', '0349463694', 'thuongcmph42492@gmail.com', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', 1, '2023-11-08', '2023-11-08', N'Người mới', 0),
('FE2535C4-44C9-4435-B04A-C8F5D659A9A5', 'anhln002', 'a1234b5c', N'Lê Ngọc Anh', '2004-11-11', 1, 'anh.png', '0336592363', 'thuongcmph42492@gmail.com', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', 0, '2023-11-08', '2023-11-08', N'Người mới', 0),
('F10432CA-DAF1-499F-A903-7023161D35E6', 'chienpv003', 'a1234b5c', N'Phùng Văn Chiến', '2004-10-24', 1, 'chien.png', '0193893843', 'thuongcmph42492@gmail.com', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', 0, '2023-11-08', '2023-11-08', N'Người mới', 0);

select * from nguoi_dung

INSERT INTO voucher VALUES
('B4136AA9-3CBA-4E7B-B125-BC7B4A8FE241', 'AMITY', N'CHÚC MỪNG KHAI TRƯƠNG CỬA HÀNG MỚI', 50000, 100, '2023-11-08', '2023-11-08', '2023-12-01', '2023-12-08', N'Còn hiệu lực', 0),
('9C378E72-AFD3-4A14-88CE-173C824D551B', 'AMITYVIP', N'CHÚC MỪNG KHAI TRƯƠNG CỬA HÀNG MỚI', 100000, 10, '2023-11-08', '2023-11-08', '2023-12-01', '2023-12-08', N'Còn hiệu lực', 0);

INSERT INTO khach_hang VALUES
('6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', N'Chu Văn Khiếu', '0986135905', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', '2023-11-08', '2023-11-08', 'THUONGCMNV001', N'Khách hàng mới', 0),
('E80586B0-63A1-4AFB-96B0-A18361C37661', N'Trần Thị Thu Hà', '0378039808', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', '2023-11-08', '2023-11-08', 'THUONGCMNV001', N'Khách hàng mới', 0);

INSERT INTO gio_hang VALUES
('6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('E80586B0-63A1-4AFB-96B0-A18361C37661', '2023-11-08', '2023-11-08', N'Hoạt động', 0);

INSERT INTO gio_hang_chi_tiet VALUES
('2C06A4DA-3005-4708-96A2-FE5DF5FF7328', 'FEB6B3B9-F5A7-4486-BEE3-1435536282D2', '6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', 2, 340000, '2023-11-08', '2023-11-08', N'Đã tạo', 0),
('1A225A40-F813-40A1-958B-C903B26BEBD9', 'AE2BD82B-E2C2-4163-A341-3D8AF4DCEC24', '6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', 1, 540000, '2023-11-08', '2023-11-08', N'Đã tạo', 0),
('7457E5C6-539B-4ECF-8922-A9A7910B27D8', 'FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9', '6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', 1, 340000, '2023-11-08', '2023-11-08', N'Đã tạo', 0),
('CE7C7A91-07CD-4EF2-B66D-59AE6211DBDB', 'AE2BD82B-E2C2-4163-A341-3D8AF4DCEC24', 'E80586B0-63A1-4AFB-96B0-A18361C37661', 1, 540000, '2023-11-08', '2023-11-08', N'Đã tạo', 0),
('73570FA5-F5DC-4986-BE22-29365F079019', 'FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9', 'E80586B0-63A1-4AFB-96B0-A18361C37661', 1, 340000, '2023-11-08', '2023-11-08', N'Đã tạo', 0);

INSERT INTO phuong_thuc_thanh_toan VALUES
('6FCB236D-EC8F-4C19-B9DC-3C73CE9C6930', N'Tiền mặt', '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('F77320E3-3120-4869-B4C1-86B8F4822841', N'Chuyển khoản', '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('FFE589A9-BFAB-4B25-B18D-F1D80E33E75A', N'Tiền mặt + Chuyển khoản', '2023-11-08', '2023-11-08', N'Hoạt động', 0);

INSERT INTO hoa_don VALUES
('2A7D035D-706C-4A33-AF1F-15FBAB32833F', '06AB237C-653A-4FE0-AB06-DB4074ECB2BC', '6CECE699-091E-4EC3-B9BE-3AE0A789E7F6', '9C378E72-AFD3-4A14-88CE-173C824D551B', N'Chu Văn Khiếu', '0986135905', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', 1560000, 100000, 1460000, '2023-11-08', NULL, NULL, NULL, 0, '2023-11-08', '2023-11-08', N'Đã hoàn thành', 0),
('AF42A7CF-B652-48A3-8786-40D24B558C11', '06AB237C-653A-4FE0-AB06-DB4074ECB2BC', 'E80586B0-63A1-4AFB-96B0-A18361C37661', '9C378E72-AFD3-4A14-88CE-173C824D551B', N'Trần Thị Thu Hà', '0986135905', N'Xóm Thuận Trại - Xã Phú Đông - Huyện Ba Vì - TP. Hà Nội', 880000, 100000, 900000, '2023-11-08', '2023-11-08', '2023-11-10', N'Giao siêu tốc', 20000, '2023-11-08', '2023-11-08', N'Đã nhận hàng', 0);

INSERT INTO hoa_don_chi_tiet VALUES
('C5249B81-B86E-4A88-BE7C-680F7AC53C2A', 'FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9', '2A7D035D-706C-4A33-AF1F-15FBAB32833F', 1, 340000, '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('F8DBE1B6-C73F-44C5-B0F7-E050C6716161', 'FA696CDE-B5B8-477B-8D0F-7CD8E3FEE2F9', 'AF42A7CF-B652-48A3-8786-40D24B558C11', 1, 340000, '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('CCEEE611-86FC-4FF2-923E-4012C6C95FE0', 'AE2BD82B-E2C2-4163-A341-3D8AF4DCEC24', '2A7D035D-706C-4A33-AF1F-15FBAB32833F', 1, 540000, '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('7D81E3AE-DCC5-4398-8D82-263D596099B4', 'AE2BD82B-E2C2-4163-A341-3D8AF4DCEC24', 'AF42A7CF-B652-48A3-8786-40D24B558C11', 1, 540000, '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('8A04E8EA-1A0F-4C5D-BB3B-FD12E9887373', 'FEB6B3B9-F5A7-4486-BEE3-1435536282D2', '2A7D035D-706C-4A33-AF1F-15FBAB32833F', 2, 680000, '2023-11-08', '2023-11-08', N'Hoạt động', 0);

INSERT INTO pttt_chi_tiet VALUES
('228E45AD-E05D-444E-9F66-8121B3DAAFCD', '2A7D035D-706C-4A33-AF1F-15FBAB32833F', '6FCB236D-EC8F-4C19-B9DC-3C73CE9C6930', 1600000, 0, 1560000, '2023-11-08', '2023-11-08', N'Hoạt động', 0),
('3825CA5A-928B-4513-9E2B-B8DD8F4F4FBD', 'AF42A7CF-B652-48A3-8786-40D24B558C11', 'F77320E3-3120-4869-B4C1-86B8F4822841', 0, 900000, 900000, '2023-11-08', '2023-11-08', N'Hoạt động', 0);

select * from nguoi_dung
select * from voucher
select * from khach_hang
select * from gio_hang
select * from gio_hang_chi_tiet
select * from hoa_don_chi_tiet
select id, id_nguoi_tao, id_khach_hang, ngay_tao, trang_thai from hoa_don 
select * from phuong_thuc_thanh_toan
select * from pttt_chi_tiet

select * from kich_thuoc
select ctsp.id_san_pham, sp.ma_san_pham, sp.ten, kt.ten , ms.ten , tl.ten , th.ten , pc.ten , ctsp.gia_ban , ctsp.so_luong_ton  from chi_tiet_san_pham ctsp
	inner join san_pham sp on sp.id = ctsp.id_san_pham
	inner join thuong_hieu th on th.id = ctsp.id_thuong_hieu
	inner join the_loai tl on tl.id = ctsp.id_the_loai
	inner join thiet_ke tk on tk.id = ctsp.id_thiet_ke
	inner join phong_cach pc on pc.id = ctsp.id_phong_cach
	inner join co_ao ca on ca.id = ctsp.id_co_ao
	inner join mau_sac ms on ms.id = ctsp.id_mau_sac
	inner join chat_lieu cl on cl.id = ctsp.id_chat_lieu
	inner join kich_thuoc kt on kt.id = ctsp.id_kich_thuoc
	inner join nha_san_xuat nsx on nsx.id = ctsp.id_nha_san_xuat

	select s.ma_san_pham, s.ten, ctsp.gia_ban from chi_tiet_san_pham ctsp
		inner join san_pham s on s.id = ctsp.id_san_pham
	where s.ma_san_pham = ?

select hd.id, kh.ten, nd.ho_ten, hd.ngay_tao, hd.trang_thai from hoa_don hd
	inner join khach_hang kh on kh.id = hd.id_khach_hang
	inner join nguoi_dung nd on nd.id = hd.id_nguoi_tao

	select hdct.id_hoa_don, sp.ma_san_pham, sp.ten, ms.ten, kt.ten, hdct.so_luong, ctsp.gia_ban, hdct.gia_tien from hoa_don_chi_tiet hdct
		inner join chi_tiet_san_pham ctsp on ctsp.id = hdct.id_ctsp
		inner join san_pham sp on sp.id = ctsp.id_san_pham
		inner join mau_sac ms on ms.id = ctsp.id_mau_sac
		inner join kich_thuoc kt on kt.id = ctsp.id_kich_thuoc
	where hdct.id_hoa_don = '2A7D035D-706C-4A33-AF1F-15FBAB32833F'

select sum(gia_tien) from hoa_don_chi_tiet where id_hoa_don = '2A7D035D-706C-4A33-AF1F-15FBAB32833F' 
select ptttct.id_hoa_don, so_tien_mat, so_tien_ck, tong_tien from pttt_chi_tiet ptttct
where ptttct.id_hoa_don = '2A7D035D-706C-4A33-AF1F-15FBAB32833F'
select sp.ma_san_pham, sp.ten, ctsp.id_mau_sac, ctsp.id_kich_thuoc, ctsp.gia_ban  from chi_tiet_san_pham ctsp
		inner join san_pham sp on sp.id = ctsp.id_san_pham
where id_san_pham = ?
select id, ma_nguoi_dung, mat_khau, ho_ten, vai_tro from nguoi_dung 
/*
select * from san_pham
select * from thuong_hieu
select * from the_loai
select * from thiet_ke
select * from phong_cach
select * from co_ao
select * from mau_sac
select * from chat_lieu
select * from kich_thuoc
select * from nha_san_xuat
select * from chi_tiet_san_pham

select * from nguoi_dung 
select * from voucher
select * from khach_hang
select * from gio_hang
select * from gio_hang_chi_tiet
select * from hoa_don_chi_tiet
select * from hoa_don
select * from phuong_thuc_thanh_toan
select * from pttt_chi_tiet
*/