package JasaTitipApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

abstract class Orang {
    protected final int id;
    protected String nama;
    protected final String alamat, telepon, email;

    public Orang(int id, String nama, String alamat, String telepon, String email) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Alamat: " + alamat + ", Telepon: " + telepon + ", Email: " + email;
    }
}


class Reseller extends Orang {
    
    public Reseller(int id, String nama, String alamat, String telepon, String email) {
        super(id, nama, alamat, telepon, email);
    }

    // Tambahan method spesifik untuk Reseller (optional)
    public void kirimPromo() {
        System.out.println("Promo telah dikirim ke reseller: " + nama);
    }

    @Override
    public String toString() {
        return "Reseller - " + super.toString();
    }
}

class Pelanggan extends Orang {

    public Pelanggan(int id, String nama, String alamat, String telepon, String email) {
        super(id, nama, alamat, telepon, email);
    }

    // Method tambahan khusus pelanggan (opsional)
    public void beriUlasan() {
        System.out.println("Pelanggan " + nama + " memberikan ulasan.");
    }

    @Override
    public String toString() {
        return "Pelanggan - " + super.toString();
    }
}


class Menu {
    private final int id;
    private final String nama, jenis;
    private double harga;
    private int stok;
    
    public Menu(int id, String nama, String jenis, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        setHarga(harga);
        setStok(stok);
    }
    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) {
        if (harga > 0) this.harga = harga;
        else throw new IllegalArgumentException("Harga harus lebih dari 0!");
    }
    public int getStok() { return stok; }
    public void setStok(int stok) {
        if (stok >= 0) this.stok = stok;
        else throw new IllegalArgumentException("Stok tidak boleh negatif!");
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Jenis: " + jenis + ", Harga: " + harga + ", Stok: " + stok;
    }
}

class Pesanan {
    private final int id;
    private final int idPelanggan;
    private final int idMenu;
    private final int idReseller;
    private final int jumlah;
    private final String status;
    private final String tanggal;

    public Pesanan(int id, int idPelanggan, int idMenu, int idReseller, int jumlah, String status, String tanggal) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idMenu = idMenu;
        this.idReseller = idReseller;
        this.jumlah = jumlah;
        this.status = status;
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        return "ID Pesanan: " + id + ", Pelanggan: " + idPelanggan + ", Menu: " + idMenu + ", Reseller: " + idReseller + ", Jumlah: " + jumlah + ", Status: " + status + ", Tanggal: " + tanggal;
    }
}

public class JasaTitipApp {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Reseller> resellerList = new ArrayList<>();
    static ArrayList<Menu> menuList = new ArrayList<>();
    static ArrayList<Pelanggan> pelangganList = new ArrayList<>();
    static ArrayList<Pesanan> pesananList = new ArrayList<>();
    static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Reseller");
            System.out.println("2. Tambah Menu");
            System.out.println("3. Tambah Pelanggan");
            System.out.println("4. Buat Pesanan");
            System.out.println("5. Lihat Semua Data");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid!");
                continue;
            }

            switch (pilihan) {
                case 1 -> tambahReseller();
                case 2 -> tambahMenu();
                case 3 -> tambahPelanggan();
                case 4 -> buatPesanan();
                case 5 -> lihatSemuaData();
                case 6 -> {
                    System.out.println("Terima kasih!");
                    return;
                }
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        }
    }

    static void tambahReseller() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan Telepon: ");
        String telepon = scanner.nextLine();
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();

        resellerList.add(new Reseller(nextId++, nama, alamat, telepon, email));
        System.out.println("Reseller berhasil ditambahkan!");
    }

    static void tambahPelanggan() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan Telepon: ");
        String telepon = scanner.nextLine();
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();

        pelangganList.add(new Pelanggan(nextId++, nama, alamat, telepon, email));
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    static void tambahMenu() {
        try {
            System.out.print("Masukkan Nama Menu: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Jenis Menu: ");
            String jenis = scanner.nextLine();
            System.out.print("Masukkan Harga: ");
            double harga = Double.parseDouble(scanner.nextLine());
            System.out.print("Masukkan Stok: ");
            int stok = Integer.parseInt(scanner.nextLine());

            menuList.add(new Menu(nextId++, nama, jenis, harga, stok));
            System.out.println("Menu berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Input tidak valid: " + e.getMessage());
        }
    }

    static void buatPesanan() {
        try {
            System.out.print("Masukkan ID Pelanggan: ");
            int idPelanggan = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan ID Menu: ");
            int idMenu = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan ID Reseller: ");
            int idReseller = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Jumlah: ");
            int jumlah = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Status: ");
            String status = scanner.nextLine();

            pesananList.add(new Pesanan(nextId++, idPelanggan, idMenu, idReseller, jumlah, status, new Date().toString()));
            System.out.println("Pesanan berhasil dibuat!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    static void lihatSemuaData() {
        System.out.println("\nDaftar Reseller:");
        for (Reseller r : resellerList) {
            System.out.println(r);
        }

        System.out.println("\nDaftar Pelanggan:");
        for (Pelanggan p : pelangganList) {
            System.out.println(p);
        }

        System.out.println("\nDaftar Menu:");
        for (Menu m : menuList) {
            System.out.println(m);
        }

        System.out.println("\nDaftar Pesanan:");
        for (Pesanan p : pesananList) {
            System.out.println(p);
        }
    }
}
