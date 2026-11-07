package responsi;

public class mobilListrikPintar extends kendaraanOtomotif implements pengisianDayaCepat, navigasiCerdas{
    public static final double KECEPATAN_MAKSIMUM_EV = 220.0;
    private final double kapasitasBateraiKWh;
    private double persentaseBaterai;
    private String modeBerkendara;
    private String ruteTujuanAktif;

    public mobilListrikPintar(
            String nomorRangkaVIN,
            String merk,
            String model,
            int tahunProduksi,
            double kapasitasBateraiKWh,
            double persentaseBaterai,
            String modeBerkendara) {
        super(nomorRangkaVIN, merk, model, tahunProduksi);
        this.kapasitasBateraiKWh = kapasitasBateraiKWh;
        this.persentaseBaterai = persentaseBaterai;
        this.modeBerkendara = modeBerkendara;
        this.ruteTujuanAktif = "Belum Diatur";
    }

    public mobilListrikPintar(
            String nomorRangkaVIN,
            String merk,
            String model,
            double kapasitasBateraiKWh) {
        super(nomorRangkaVIN, merk, model);
        this.kapasitasBateraiKWh = kapasitasBateraiKWh;
        this.persentaseBaterai = 100.0;
        this.modeBerkendara = "ECO";
        this.ruteTujuanAktif = "Belum Diatur";
    }

    @Override
    public void akselerasiKendaraan(double tambahanKecepatan) {
        if(!this.isMesinMenyala()) {
            System.out.println("[PERINGATAN EV] | Gagal akselerasi: Mesin " + this.getMerk() + " " + this.getModel() + " masih mati!");
        } else if (this.persentaseBaterai <= 5.0) {
            this.setKecepatanSaatIni(0.0);
            this.setMesinMenyala(false);
            System.out.println("[DARURAT EV] | Baterai kritis (" + this.persentaseBaterai + "%). Sistem menghentikan mobil otomatis!");
        } else if (tambahanKecepatan <= 0) {
            System.out.println("[INFO EV] | Tambahan kecepatan harus bernilai positif.");
        } else {
            double konsumsiBaterai = "SPORT".equalsIgnoreCase(this.modeBerkendara) ? tambahanKecepatan * 0.25 : tambahanKecepatan * 0.12;
            double kecepatanBaru = this.getKecepatanSaatIni() + tambahanKecepatan;

            if (kecepatanBaru > KECEPATAN_MAKSIMUM_EV) {
                kecepatanBaru = KECEPATAN_MAKSIMUM_EV;
                System.out.println("[LIMITER EV] | Kecepatan dibatasi pada batas maksimum " + KECEPATAN_MAKSIMUM_EV + " km/jam.");
            }
            this.setKecepatanSaatIni(kecepatanBaru);
            this.persentaseBaterai = Math.max(0.0, this.persentaseBaterai - konsumsiBaterai);

            if (this.persentaseBaterai < 20.0 && !"ECO".equalsIgnoreCase(this.modeBerkendara)) {
                this.modeBerkendara = "ECO";
                System.out.println("[AUTO-SWITCH EV] | Baterai di bawah 20%, mode berkendara diubah otomatis ke ECO.");
            }
            System.out.printf("[AKSELERASI EV] | %s %s melaju pada %.1f km/jam | Sisa Baterai: %.1f%% | Mode: %s%n", this.getMerk(), this.getModel(), this.getKecepatanSaatIni(), this.persentaseBaterai, this.modeBerkendara);
        }
    }

    @Override
    public String getInfoSpesifikasi() {
        return super.getInfoSpesifikasi() + String.format(" | Tipe: Mobil Listrik (EV)%n | Kapasitas Baterai: %.1f kWh (%.1f%%)%n | Mode: %s%n | Rute: %s%n", this.kapasitasBateraiKWh, this.persentaseBaterai, this.modeBerkendara, this.ruteTujuanAktif);
    }

    @Override
    public void mulaiFastCharging(double targetPersentase) {
        if (this.isMesinMenyala()) {
            this.matikanMesin();
        }
        if (targetPersentase <= this.persentaseBaterai) {
            System.out.printf("[FAST CHARGING] | Baterai saat ini (%.1f%%) sudah memenuhi target (%.1f%%).%n", this.persentaseBaterai, targetPersentase);
        } else {
            double batasTarget = Math.min(100.0, targetPersentase);
            System.out.printf("[FAST CHARGING] | Mengisi daya %s %s dari %.1f%% menuju %.1f%%...%n", this.getMerk(), this.getModel(), this.persentaseBaterai, batasTarget);
            this.persentaseBaterai = batasTarget;
            System.out.printf("[FAST CHARGING] | Selesai! Baterai sekarang berada pada level %.1f%%.%n", this.persentaseBaterai);
        }
    }

    @Override
    public double estimasiWaktuCas(double dayaChargerKw) {
        if (dayaChargerKw <= 0 || this.persentaseBaterai >= 100.0) {
            return 0.0;
        }
        double kekuranganPersen = 100.0 - this.persentaseBaterai;
        double energiDibutuhkanKwh = (kekuranganPersen / 100.0) * this.kapasitasBateraiKWh;
        return (energiDibutuhkanKwh / dayaChargerKw) * 60.0;
    }

    @Override
    public void aturRuteTujuan(String tujuan) {
        if (tujuan != null && !tujuan.trim().isEmpty()) {
            this.ruteTujuanAktif = tujuan.trim();
            System.out.println("[NAVIGASI CERDAS] | Rute berhasil diatur menuju: " + this.ruteTujuanAktif);
        } else {
            System.out.println("[NAVIGASI CERDAS] | Tujuan tidak valid.");
        }
    }

    @Override
    public String lacakPosisiKendaraan() {
        return String.format("GPS Aktif -> %s %s sedang menuju '%s' dengan kecepatan %.1f km/jam", this.getMerk(), this.getModel(), this.ruteTujuanAktif, this.getKecepatanSaatIni());
    }

    public double getKapasitasBateraiKWh() {
        return this.kapasitasBateraiKWh;
    }

    public double getPersentaseBaterai() {
        return this.persentaseBaterai;
    }

    public void setPersentaseBaterai(double persentaseBaterai) {
        this.persentaseBaterai = Math.max(0.0, Math.min(100.0, persentaseBaterai));
    }

    public String getModeBerkendara() {
        return this.modeBerkendara;
    }

    public void setModeBerkendara(String modeBerkendara) {
        this.modeBerkendara = modeBerkendara;
    }

    public String getRuteTujuanAktif() {
        return this.ruteTujuanAktif;
    }

    public void setRuteTujuanAktif(String ruteTujuanAktif) {
        this.ruteTujuanAktif = ruteTujuanAktif;
    }
}