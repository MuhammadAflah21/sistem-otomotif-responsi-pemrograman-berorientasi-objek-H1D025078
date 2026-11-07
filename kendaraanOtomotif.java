package responsi;

public abstract class kendaraanOtomotif {
    public static final String STANDAR_REGULASI_OTOMOTIF = "SNI_EURO4_INDONESIA";
    private final String nomorRangkaVIN;
    private String merk;
    private String model;
    private int tahunProduksi;
    private double kecepatanSaatIni;
    private boolean mesinMenyala;

    public kendaraanOtomotif(String nomorRangkaVIN, String merk, String model, int tahunProduksi) {
        this.nomorRangkaVIN = nomorRangkaVIN;
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
        this.kecepatanSaatIni = 0.0;
        this.mesinMenyala = mesinMenyala;
    }

    public kendaraanOtomotif(String nomorRangkaVIN, String merk, String model) {
        this(nomorRangkaVIN, merk, model, 2026);
    }

    public void nyalakanMesin() {
        if (!this.mesinMenyala) {
            this.mesinMenyala = true;
            System.out.println("[" + this.merk + " " + this.model + "] Mesin berhasil dinyalakan.");
        } else {
            System.out.println("[" + this.merk + " " + this.model + "] Mesin sudah dalam keadaan menyala.");
        }
    }

    public void matikanMesin() {
        if (this.mesinMenyala) {
            this.kecepatanSaatIni = 0.0;
            this.mesinMenyala = false;
            System.out.println("[" + this.merk + " " + this.model + "] Mesin dimatikan dan keadaan berhenti.");
        } else {
            System.out.println("[" + this.merk + " " + this.model + "] Mesin sudah dalam keadaan mati.");
        }
    }

    public String getInfoSpesifikasi() {
        return String.format(
            "%n | VIN: %s%n | Kendaraan: %s %s (%d)%n | Regulasi: %s%n | Status Mesin: %s%n | Kecepatan: %.1f km/jam%n",
            this.nomorRangkaVIN,
            this.merk,
            this.model,
            this.tahunProduksi,
            STANDAR_REGULASI_OTOMOTIF,
            (this.mesinMenyala ? "Menyala" : "Mati"),
            this.kecepatanSaatIni
        );
    }

    public double hitungPajakTahunan(double nilaiJualKendaraan) {
        int usiaKendaraan = Math.max(0, 2026 - this.tahunProduksi);
        double faktorPenyusutan = Math.max(0.5, 1.0 - (usiaKendaraan * 0.03));
        return nilaiJualKendaraan * 0.015 * faktorPenyusutan;
    }

    public abstract void akselerasiKendaraan(double tambahanKecepatan);

    public String getNomorRangkaVIN() {
        return this.nomorRangkaVIN;
    }

    public String getMerk() {
        return this.merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTahunProduksi() {
        return this.tahunProduksi;
    }

    public void setTahunProduksi(int tahunProduksi) {
        this.tahunProduksi = tahunProduksi;
    }

    public double getKecepatanSaatIni() {
        return this.kecepatanSaatIni;
    }

    public void setKecepatanSaatIni(double kecepatanSaatIni) {
        this.kecepatanSaatIni = Math.max(0.0, kecepatanSaatIni);
    }

    public boolean isMesinMenyala() {
        return this.mesinMenyala;
    }

    public void setMesinMenyala(boolean mesinMenyala) {
        this.mesinMenyala = mesinMenyala;
    }
}
