package JasaTitipApp;

import java.util.;

class Reseller {
    private int id;
    private String nama, alamat, telepon, email;
    
    public Reseller(int id, String nama, String alamat, String telepon, String email) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
    }
    
    // Getter dan Setter
    public int getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Alamat: " + alamat + ", Telepon: " + telepon + ", Email: " + email;
    }
}

class Pelanggan {
    int id;
    String nama, alamat, telepon, email;
    
    Pelanggan(int id, String nama, String alamat, String telepon, String email) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Alamat: " + alamat + ", Telepon: " + telepon + ", Email: " + email;
    }
}

class Menu {
    private int id;
    private String nama, jenis;
    private double harga;
    private int stok;
    
    public Menu(int id, String nama, String jenis, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        setHarga(harga);
        setStok(stok);
    }
    
    // Getter dan Setter
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
    
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Jenis: " + jenis + ", Harga: " + harga + ", Stok: " + stok;
    }
}

class Pesanan {
    int id;
    int idPelanggan;
    int idMenu;
    int idReseller;
    int jumlah;
    String status;
    String tanggal;

    Pesanan(int id, int idPelanggan, int idMenu, int idReseller, int jumlah, String status, String tanggal) {
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
    static int nextId = 1;
    static ArrayList<Pelanggan> pelangganList = new ArrayList<>();
    static ArrayList<Pesanan> pesananList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Reseller");
            System.out.println("2. Tambah Menu");
            System.out.println("3. Lihat Semua Data");
            System.out.println("4. Keluar");
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
                case 3 -> lihatSemuaData();
                case 4 -> {
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
