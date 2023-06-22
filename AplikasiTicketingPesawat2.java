import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AplikasiTicketingPesawat2 {
    private List<Pesawat> jadwalPenerbangan;
    private List<Penumpang> daftarPenumpang;
    private List<Penumpang> antrianPenumpang;

    public AplikasiTicketingPesawat2() {
        jadwalPenerbangan = new ArrayList<>();
        daftarPenumpang = new ArrayList<>();
        antrianPenumpang = new ArrayList<>();
    }

    public void tambahPesawat(Pesawat pesawat) {
        jadwalPenerbangan.add(pesawat);
    }

    public void tampilkanJadwal() {
        System.out.println("-----Jadwal Penerbangan-----");
        System.out.println("Tujuan\t\tMaskapai\tJam\t\tKode Tiket\tHarga");
        for (Pesawat pesawat : jadwalPenerbangan) {
            System.out.printf("%s\t\t%s\t\t%s\t%s\t\t%s%n", pesawat.getTujuan(), pesawat.getMaskapai(),
                    pesawat.getJam(), pesawat.getKodeTiket(), pesawat.getHarga());
        }
    }

    public Pesawat cariPesawatByKodeTiket(String kodeTiket) {
        for (Pesawat pesawat : jadwalPenerbangan) {
            if (pesawat.getKodeTiket().equals(kodeTiket)) {
                return pesawat;
            }
        }
        return null;
    }

    public void urutkanPesawatByHarga() {
        Collections.sort(jadwalPenerbangan, Comparator.comparingInt(Pesawat::getHarga));
    }

    public void pesanTiket(String nama, String kodeTiket, String noKTP, String noTelepon) {
        Pesawat pesawat = cariPesawatByKodeTiket(kodeTiket);
        if (pesawat != null) {
            Penumpang penumpang = new Penumpang(nama, kodeTiket, noKTP, noTelepon);
            daftarPenumpang.add(penumpang);
            if (antrianPenumpang.contains(penumpang)) {
                System.out.println("Penumpang " + penumpang.getNama() + " sudah ada dalam antrian.");
            } else {
                antrianPenumpang.add(penumpang);
                System.out.println("Penumpang " + penumpang.getNama() + " berhasil ditambahkan ke dalam antrian.");
            }
        } else {
            System.out.println("Tiket dengan kode " + kodeTiket + " tidak ditemukan.");
        }
    }

    public void batalkanTiket(String kodeTiket) {
        Penumpang penumpang = null;
        for (Penumpang p : daftarPenumpang) {
            if (p.getKodeTiket().equals(kodeTiket)) {
                penumpang = p;
                break;
            }
        }
        if (penumpang != null) {
            daftarPenumpang.remove(penumpang);
            antrianPenumpang.remove(penumpang);
            System.out.println("Tiket dengan kode " + kodeTiket + " berhasil dibatalkan.");
        } else {
            System.out.println("Tiket dengan kode " + kodeTiket + " tidak ditemukan.");
        }
    }

    public void tampilkanDaftarPenumpang() {
        System.out.println("-----Daftar Penumpang-----");
        System.out.println("Nama\t\tKode Tiket\tNo. KTP\t\tNo. Telepon");
        for (Penumpang penumpang : daftarPenumpang) {
            System.out.printf("%s\t\t%s\t\t%s\t\t%s%n", penumpang.getNama(), penumpang.getKodeTiket(),
                    penumpang.getNoKTP(), penumpang.getNoTelepon());
        }
    }

    public void tampilkanAntrianPenumpang() {
        System.out.println("-----Antrian Penumpang-----");
        System.out.println("Nama\t\tKode Tiket\tNo. KTP\t\tNo. Telepon");
        for (Penumpang penumpang : antrianPenumpang) {
            System.out.printf("%s\t\t%s\t\t%s\t\t%s%n", penumpang.getNama(), penumpang.getKodeTiket(),
                    penumpang.getNoKTP(), penumpang.getNoTelepon());
        }
    }

    public static void main(String[] args) {
        AplikasiTicketingPesawat aplikasi = new AplikasiTicketingPesawat();

        Pesawat pesawat1 = new Pesawat("Jakarta", "Garuda Indonesia", "08.00", "GIA123", 1500000);
        Pesawat pesawat2 = new Pesawat("Surabaya", "Lion Air", "10.30", "LIA456", 1200000);
        Pesawat pesawat3 = new Pesawat("Bali", "AirAsia", "12.45", "AIA789", 1000000);

        aplikasi.tambahPesawat(pesawat1);
        aplikasi.tambahPesawat(pesawat2);
        aplikasi.tambahPesawat(pesawat3);

        aplikasi.tampilkanJadwal();

        aplikasi.urutkanPesawatByHarga();

        aplikasi.tampilkanJadwal();

        aplikasi.pesanTiket("John Doe", "AIA789", "1234567890", "081234567890");
        aplikasi.pesanTiket("Jane Smith", "GIA123", "0987654321", "087654321098");
        aplikasi.pesanTiket("David Johnson", "LIA456", "1357924680", "089876543210");

        aplikasi.tampilkanDaftarPenumpang();
        aplikasi.tampilkanAntrianPenumpang();

        aplikasi.batalkanTiket("GIA123");

        aplikasi.tampilkanDaftarPenumpang();
        aplikasi.tampilkanAntrianPenumpang();
    }
}


public void antrianMemasukiPesawat() {
    if (antrianPenumpang.isEmpty()) {
        System.out.println("Tidak ada penumpang dalam antrian.");
    } else {
        Penumpang penumpang = antrianPenumpang.remove();
        System.out.println("Penumpang " + penumpang.getNama() + " dengan kode tiket " + penumpang.getKodeTiket() + " masuk ke dalam pesawat.");
    }
}


     public void tampilkanAntrianPesawat() {
    if (antrianPenumpang.isEmpty()) {
        System.out.println("Tidak ada penumpang dalam antrian.");
    } else {
        System.out.println("-----Antrian Penumpang-----");
        System.out.println("No\tNama\t\tKode Tiket\tNo KTP\t\tNo Telepon");
        
        List<Penumpang> antrian = new ArrayList<>(antrianPenumpang);
        for (int i = 0; i < antrian.size(); i++) {
            Penumpang p = antrian.get(i);
            System.out.printf("%d.\t%s\t%s\t\t%s\t\t%s%n", i + 1, p.getNama(), p.getKodeTiket(), p.getNoKTP(), p.getNoTelepon());
        }
    }
}



      case 7:
                    aplikasi.tampilkanAntrianPesawat();
                    System.out.println("Apakah ingin memasukkan penumpang ke dalam pesawat? (y/n)");
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("y")) {
                        aplikasi.antrianMemasukiPesawat();
                    }
                    break;



import java.util.*;

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
        Pesawat pesawat = cariPesawatByKodeTiket(kodeTiket);
        if (pesawat != null) {
            Penumpang penumpang = new Penumpang(nama, kodeTiket, noKTP, noTelepon);
            daftarPenumpang.add(penumpang);
            System.out.println("Tiket berhasil dipesan untuk " + nama + " dengan kode tiket " + kodeTiket + ".");
        } else {
            System.out.println("Pesawat dengan kode tiket " + kodeTiket + " tidak ditemukan.");
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

    public static void main(String[] args) {
        AplikasiTicketingPesawat aplikasi = new AplikasiTicketingPesawat();
        Scanner scanner = new Scanner(System.in);

        aplikasi.tambahPesawat(new Pesawat("Jakarta", "Garuda Indonesia", "08:00", "GA123", 1000000));
        aplikasi.tambahPesawat(new Pesawat("Surabaya", "Lion Air", "09:30", "JT456", 800000));
        aplikasi.tambahPesawat(new Pesawat("Bali", "AirAsia", "10:45", "QZ789", 1200000));
        aplikasi.tambahPesawat(new Pesawat("Yogyakarta", "Batik Air", "07:45", "ID789", 900000));
        aplikasi.tambahPesawat(new Pesawat("Medan", "Citilink", "11:15", "QG246", 750000));
        aplikasi.tambahPesawat(new Pesawat("Bandung", "Sriwijaya Air", "13:30", "SJ567", 850000));
        aplikasi.tambahPesawat(new Pesawat("Semarang", "Garuda Indonesia", "14:45", "GA987", 950000));
        aplikasi.tambahPesawat(new Pesawat("Makassar", "Lion Air", "16:00", "JT321", 780000));
        aplikasi.tambahPesawat(new Pesawat("Palembang", "AirAsia", "17:30", "QZ654", 1100000));
        aplikasi.tambahPesawat(new Pesawat("Balikpapan", "Batik Air", "19:15", "ID246", 850000));
        aplikasi.tambahPesawat(new Pesawat("Lombok", "Citilink", "20:30", "QG789", 900000));
        aplikasi.tambahPesawat(new Pesawat("Padang", "Sriwijaya Air", "22:00", "SJ432", 950000));
        aplikasi.tambahPesawat(new Pesawat("Pontianak", "Garuda Indonesia", "23:45", "GA654", 780000));

        while (true) {
            System.out.println("========== Aplikasi Ticketing Pesawat ==========");
            System.out.println("1. Tampilkan Jadwal Penerbangan");
            System.out.println("2. Cari Pesawat berdasarkan Kode Tiket");
            System.out.println("3. Tampilkan Jadwal Penerbangan Terurut (Harga)");
            System.out.println("4. Pesan Tiket");
            System.out.println("5. Tampilkan Daftar Penumpang");
            System.out.println("6. Antrian Memasuki Pesawat");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan karakter '\n' setelah membaca angka

            switch (menu) {
                case 1:
                    aplikasi.tampilkanJadwal();
                    break;
                case 2:
                    System.out.print("Masukkan kode tiket: ");
                    String kodeTiket = scanner.nextLine();
                    Pesawat pesawat = aplikasi.cariPesawatByKodeTiket(kodeTiket);
                    if (pesawat != null) {
                        System.out.println("Pesawat dengan kode tiket " + kodeTiket + " ditemukan!");
                        System.out.println("Detail Pesawat:");
                        System.out.println("Tujuan: " + pesawat.getTujuan());
                        System.out.println("Pesawat: " + pesawat.getPesawat());
                        System.out.println("Jam: " + pesawat.getJam());
                        System.out.println("Harga: " + pesawat.getHarga());
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
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan nomor KTP: ");
                    String noKTP = scanner.nextLine();
                    System.out.print("Masukkan nomor telepon: ");
                    String noTelepon = scanner.nextLine();
                    aplikasi.pesanTiket(kodeTiket, nama, noKTP, noTelepon);
                    break;
                case 5:
                    aplikasi.tampilkanDaftarPenumpang();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi Ticketing Pesawat.");
                    return;
                default:
                    System.out.println("Menu tidak valid!");
                    break;
            }

            System.out.println(); // Baris kosong sebagai pemisah antar menu
        }
    }
}