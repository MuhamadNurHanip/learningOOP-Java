// Menggunakan konsep Array, Pencarian, Sorting,
import java.util.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Pesawat {
    private String tujuan;
    private String pesawat;
    private String jam;
    private String kodeTiket;
    private int harga;

    public Pesawat(String tujuan, String pesawat, String jam, String kodeTiket, int harga) {
        this.tujuan = tujuan;
        this.pesawat = pesawat;
        this.jam = jam;
        this.kodeTiket = kodeTiket;
        this.harga = harga;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getPesawat() {
        return pesawat;
    }

    public String getJam() {
        return jam;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public int getHarga() {
        return harga;
    }
}

class Penumpang {
    private String nama;
    private String kodeTiket;
    private String noKTP;
    private String noTelepon;

    public Penumpang(String nama, String kodeTiket, String noKTP, String noTelepon) {
        this.nama = nama;
        this.kodeTiket = kodeTiket;
        this.noKTP = noKTP;
        this.noTelepon = noTelepon;
    }

    public String getNama() {
        return nama;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public String getNoTelepon() {
        return noTelepon;
    }
}

public class AplikasiTicketingPesawat {
    private List<Pesawat> jadwalPenerbangan;
    private List<Penumpang> daftarPenumpang;

    public AplikasiTicketingPesawat() {
        jadwalPenerbangan = new ArrayList<>();
        daftarPenumpang = new ArrayList<>();

    }

    public void tambahPesawat(Pesawat pesawat) {
        jadwalPenerbangan.add(pesawat);
    }

    public void tampilkanJadwal() {
        System.out.println("-----Jadwal Penerbangan Hari ini-----");
        System.out.println("No\tTujuan\t\t\tPesawat\t\tJam\t\tHarga");
        for (int i = 0; i < jadwalPenerbangan.size(); i++) {
            Pesawat p = jadwalPenerbangan.get(i);
            System.out.printf("%d.\t%s\t%s\t\t%s\t\t%d%n", i + 1, p.getTujuan(), p.getPesawat(), p.getJam(), p.getHarga());
        }
    }

    public Pesawat cariPesawatByKodeTiket(String kodeTiket) {
        for (int i = 0; i < jadwalPenerbangan.size(); i++) {
            Pesawat p = jadwalPenerbangan.get(i);
            if (p.getKodeTiket().equals(kodeTiket)) {
                return p;
            }
        }
        return null;
    }

    public void sortingPesawatByHarga() {
        Collections.sort(jadwalPenerbangan, Comparator.comparingInt(Pesawat::getHarga));
        System.out.println("Jadwal penerbangan berhasil diurutkan berdasarkan harga.");
    }

    public void tampilkanJadwalTerurut() {
        List<Pesawat> jadwalTerurut = new ArrayList<>(jadwalPenerbangan);
        Collections.sort(jadwalTerurut, Comparator.comparingInt(Pesawat::getHarga));

        System.out.println("-----Jadwal Penerbangan Hari ini (Terurut Berdasarkan Harga)-----");
        System.out.println("No\tTujuan\t\t\tPesawat\t\tJam\t\tHarga");
        for (int i = 0; i < jadwalTerurut.size(); i++) {
            Pesawat p = jadwalTerurut.get(i);
            System.out.printf("%d.\t%s\t%s\t\t%s\t\t%d%n", i + 1, p.getTujuan(), p.getPesawat(), p.getJam(), p.getHarga());
        }
    }

    public void pesanTiket(String kodeTiket, String nama, String noKTP, String noTelepon) {
        Pesawat pesawatobj = this.cariPesawatByKodeTiket(kodeTiket);
        if (pesawatobj != null) {
            Penumpang penumpang = new Penumpang(nama, kodeTiket, noKTP, noTelepon);
            daftarPenumpang.add(penumpang);
            System.out.println("Tiket berhasil dipesan untuk " + nama + " dengan kode tiket " + kodeTiket + ".");
        } else {
            System.out.println("Pesawat dengan kode tiket " + kodeTiket + " tidak ditemukan.");
        }
    }

    public void batalPesanTiket(String kodeTiket) {
    Penumpang penumpang = null;
    for (int i = 0; i < daftarPenumpang.size(); i++) {
        Penumpang p = daftarPenumpang.get(i);
        if (p.getKodeTiket().equals(kodeTiket)) {
            penumpang = p;
            break;
        }
    }

    if (penumpang != null) {
        daftarPenumpang.remove(penumpang);
        System.out.println("Pemesanan tiket dengan kode tiket " + kodeTiket + " telah dibatalkan.");
    } else {
        System.out.println("Tidak ditemukan pemesanan tiket dengan kode tiket " + kodeTiket + ".");
    }
}

    public void tampilkanDaftarPenumpang() {
        System.out.println("-----Daftar Penumpang-----");
        System.out.println("No\tNama\t\tKode Tiket\tNo KTP\t\tNo Telepon");
        for (int i = 0; i < daftarPenumpang.size(); i++) {
            Penumpang p = daftarPenumpang.get(i);
            System.out.printf("%d.\t%s\t%s\t\t%s\t\t%s%n", i + 1, p.getNama(), p.getKodeTiket(), p.getNoKTP(),
                    p.getNoTelepon());
        }
    }

// fungsi utama 
    public static void main(String[] args) {
        
        AplikasiTicketingPesawat aplikasi = new AplikasiTicketingPesawat();
        Scanner scanner = new Scanner(System.in);

        aplikasi.tambahPesawat(new Pesawat("Jakarta - Banyuwangi", "Garuda Indonesia", "08:00", "GA123", 100000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Surabaya",   "Lion Air",         "09:30", "JT456", 80000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Bali",       "AirAsia",          "10:45", "QZ789", 120000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Yogyakarta", "Batik Air",        "07:45", "ID789", 90000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Medan",      "Citilink",         "11:15", "QG246", 75000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Bandung",    "Sriwijaya Air",    "13:30", "SJ567", 85000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Semarang",   "Garuda Indonesia", "14:45", "GA987", 95000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Makassar",   "Lion Air",         "16:00", "JT321", 78000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Palembang",  "AirAsia",          "17:30", "QZ654", 110000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Balikpapan", "Batik Air",        "19:15", "ID246", 85000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Lombok",     "Citilink",         "20:30", "QG789", 90000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Padang",     "Sriwijaya Air",    "22:00", "SJ432", 95000));
        aplikasi.tambahPesawat(new Pesawat("Jakarta - Pontianak",  "Garuda Indonesia", "23:45", "GA654", 78000));

        while (true) {
            System.out.println("================================================");
            System.out.println("   Aplikasi Ticketing Pesawat Kelas Eksekutif   ");
            System.out.println("================================================");
            System.out.println("-> Fitur");
            System.out.println("1. Tampilkan Jadwal Penerbangan");
            System.out.println("2. Cari Pesawat berdasarkan Kode Tiket");
            System.out.println("3. Tampilkan Jadwal Penerbangan Terurut (Harga)");
            System.out.println("4. Pesan Tiket");
            System.out.println("5. Batal Pesan Tiket");
            System.out.println("6. Tampilkan Daftar Penumpang");
            System.out.println("7. Antrian Penumpang Memasuki Pintu Pesawat ");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (1/2/3/4/5/6/7/0) : ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan karakter '\n' setelah membaca angka

            switch (menu) {
                case 1:
                    aplikasi.tampilkanJadwal();
                    break;
                case 2:
                    System.out.print("Masukkan kode tiket: ");
                    String kodeTiket = scanner.nextLine();
                    Pesawat pesawatobj = aplikasi.cariPesawatByKodeTiket(kodeTiket);
                    if (pesawatobj != null) {
                        System.out.println("Pesawat dengan kode tiket " + kodeTiket + " ditemukan!");
                        System.out.println("Detail Pesawat:");
                        System.out.println("Tujuan: " + pesawatobj.getTujuan());
                        System.out.println("Pesawat: " + pesawatobj.getPesawat());
                        System.out.println("Jam: " + pesawatobj.getJam());
                        System.out.println("Harga: " + pesawatobj.getHarga());
                    } else {
                        System.out.println("Pesawat dengan kode tiket " + kodeTiket + " tidak ditemukan.");
                    }
                    break;
                case 3:
                    aplikasi.tampilkanJadwalTerurut();
                    break;
case 4:
    System.out.print("Masukkan kode tiket: ");
    kodeTiket = scanner.nextLine();
    Pesawat pesawat = aplikasi.cariPesawatByKodeTiket(kodeTiket);
    if (pesawat != null) {
        System.out.println("Pesawat dengan kode tiket " + kodeTiket + " ditemukan!");
        System.out.println("Detail Pesawat:");
        System.out.println("Tujuan: " + pesawat.getTujuan());
        System.out.println("Pesawat: " + pesawat.getPesawat());
        System.out.println("Jam: " + pesawat.getJam());
        System.out.println("Harga: " + pesawat.getHarga());

        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan nomor KTP: ");
        String noKTP = scanner.nextLine();
        System.out.print("Masukkan nomor telepon: ");
        String noTelepon = scanner.nextLine();

        System.out.println("Harga tiket: " + pesawat.getHarga());
        int hargaTiket = pesawat.getHarga();
        System.out.print("Masukkan pembayaran: ");
        int pembayaran = scanner.nextInt();
        scanner.nextLine(); // Membersihkan karakter '\n' setelah membaca angka

        if (pembayaran < hargaTiket) {
            System.out.println("Pembayaran tidak mencukupi. Pemesanan tiket gagal.");
        } else {
            int kembalian = pembayaran - hargaTiket;
            aplikasi.pesanTiket(kodeTiket, nama, noKTP, noTelepon);
            System.out.println("Pemesanan tiket berhasil!");
            System.out.println("Kembalian: " + kembalian);
            System.out.println("Ini tiket Anda:");
            System.out.println("Nama: " + nama);
            System.out.println("Kode Tiket: " + kodeTiket);
            System.out.println("Tujuan: " + pesawat.getTujuan());
            System.out.println("Pesawat: " + pesawat.getPesawat());
            System.out.println("Jam: " + pesawat.getJam());
            System.out.println("Harga: " + hargaTiket);
        }
    } else {
        System.out.println("Pesawat dengan kode tiket " + kodeTiket + " tidak ditemukan.");
    }
    break;
               case 5:
    System.out.print("Masukkan kode tiket: ");
    kodeTiket = scanner.nextLine();
    aplikasi.batalPesanTiket(kodeTiket);
    break;
case 6:
    aplikasi.tampilkanDaftarPenumpang();
    break;
case 7:
    // Implementasi antrian penumpang
    
    break;
case 0:
    System.out.println("Terima kasih telah menggunakan Layanan Aplikasi Ticketing Pesawat Kelas Eksekutif.");
    System.exit(0);
default:
    System.out.println("Fitur dalam Pengembangan");
    break;
            }

            System.out.println(); // Baris kosong sebagai pemisah antar menu
        }
    }
}