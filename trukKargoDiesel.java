package responsi;

public class trukKargoDiesel extends kendaraanOtomotif implements navigasiCerdas{
    public static final double BATAS_KECEPATAN_TRUK = 90.0;
    private final double kapasitasMuatanMaksTon;
    private double beratMuatanSaatIniTon;
    private double volumeSolarLiter;
    private String titikDistribusiLogistik;

    public trukKargoDiesel(
            String nomorRangkaVIN,
            String merk,
            String model,
            int tahunProduksi,
            double kapasitasMuatanMaksTon,
            double beratMuatanSaatIniTon,
            double volumeSolarLiter) {
        super(nomorRangkaVIN, merk, model, tahunProduksi);
        this.kapasitasMuatanMaksTon = kapasitasMuatanMaksTon;
        this.beratMuatanSaatIniTon = beratMuatanSaatIniTon;
        this.volumeSolarLiter = volumeSolarLiter;
        this.titikDistribusiLogistik = "Depot Utama";
    }

    public trukKargoDiesel(
            String nomorRangkaVIN,
            String merk,
            String model,
            double kapasitasMuatanMaksTon) {
        super(nomorRangkaVIN, merk, model);
        this.kapasitasMuatanMaksTon = kapasitasMuatanMaksTon;
        this.beratMuatanSaatIniTon = 0.0;
        this.volumeSolarLiter = 150.0;
        this.titikDistribusiLogistik = "Depot Utama";
    }

    public void tambahMuatanKargo(double tambahanTon) {
        if (tambahanTon > 0) {
            this.beratMuatanSaatIniTon += tambahanTon;
            System.out.printf("[KARGO TRUK] | Muatan bertambah %.1f ton. Total muatan sekarang: %.1f / %.1f ton.%n", tambahanTon, this.beratMuatanSaatIniTon, this.kapasitasMuatanMaksTon);
        }
    }

    @Override
    public void akselerasiKendaraan(double tambahanKecepatan) {
        if (!this.isMesinMenyala()) {
            System.out.println("[PERINGATAN TRUK] | Mesin diesel " + this.getMerk() + " " + this.getModel() + " belum menyala!");
        } else if (this.beratMuatanSaatIniTon > this.kapasitasMuatanMaksTon) {
            this.setKecepatanSaatIni(0.0);
            System.out.printf("[OVERLOAD LOCK] | Muatan (%.1f ton) melebihi kapasitas maksimal (%.1f ton)! Truk dilarang melaju.%n", this.beratMuatanSaatIniTon, this.kapasitasMuatanMaksTon);
        } else if (this.volumeSolarLiter <= 2.0) {
            this.setKecepatanSaatIni(0.0);
            this.setMesinMenyala(false);
            System.out.println("[DARURAT SOLAR] | Bahan bakar solar hampir habis (" + this.volumeSolarLiter + " L). Mesin dimatikan!");
        } else {
            double faktorBeban = 1.0 + (this.beratMuatanSaatIniTon / this.kapasitasMuatanMaksTon);
            double konsumsiSolar = (tambahanKecepatan + 0.08) * faktorBeban;
            double kecepatanBaru = this.getKecepatanSaatIni() + tambahanKecepatan;
            if (kecepatanBaru > BATAS_KECEPATAN_TRUK) {
                kecepatanBaru = BATAS_KECEPATAN_TRUK;
                System.out.println("[GOVERNOR TRUK] | Kecepatan truk kargo dibatasi maksimal " + BATAS_KECEPATAN_TRUK + " km/jam.");
            }
            this.setKecepatanSaatIni(kecepatanBaru);
            this.volumeSolarLiter = Math.max(0.0, this.volumeSolarLiter - konsumsiSolar);
            System.out.printf("[AKSELERASI TRUK] | %s %s melaju %.1f km/jam | Muatan: %.1f/%.1f ton | Sisa solar: %.1f liter%n", this.getMerk(), this.getModel(), this.getKecepatanSaatIni(), this.beratMuatanSaatIniTon, this.kapasitasMuatanMaksTon, this.volumeSolarLiter);
        }
    }

    @Override
    public String getInfoSpesifikasi() {
        return super.getInfoSpesifikasi() + String.format(" | Tipe: Truk Kargo Diesel%n | Muatan: %.1f/%.1f Ton%n | Solar: %.1f L%n | Tujuan Logistik: %s%n", this.beratMuatanSaatIniTon, this.kapasitasMuatanMaksTon, this.volumeSolarLiter, this.titikDistribusiLogistik);
    }

    @Override
    public void aturRuteTujuan(String tujuan) {
        if (tujuan != null && !tujuan.trim().isEmpty()) {
            this.titikDistribusiLogistik = tujuan.trim();
            System.out.println("[LOGISTIK TRUK] | Titik distribusi kargo diarahkan ke: " + this.titikDistribusiLogistik);
        }
    }

    @Override
    public String lacakPosisiKendaraan() {
        return String.format("Fleet Tracker -> Truk %s %s membawa %.1f ton kargo menuju '%s'", this.getMerk(), this.getModel(), this.beratMuatanSaatIniTon, this.titikDistribusiLogistik);
    }

    public double getKapasitasMuatanMaksTon() {
        return this.kapasitasMuatanMaksTon;
    }

    public double getBeratMuatanSaatIniTon() {
        return this.beratMuatanSaatIniTon;
    }

    public void setBeratMuatanSaatIniTon(double beratMuatanSaatIniTon) {
        this.beratMuatanSaatIniTon = Math.max(0.0, beratMuatanSaatIniTon);
    }

    public double getVolumeSolarLiter() {
        return this.volumeSolarLiter;
    }

    public void setVolumeSolarLiter(double volumeSolarLiter) {
        this.volumeSolarLiter = Math.max(0.0, volumeSolarLiter);
    }

    public String getTitikDistribusiLogistik() {
        return this.titikDistribusiLogistik;
    }

    public void setTitikDistribusiLogistik(String titikDistribusiLogistik) {
        this.titikDistribusiLogistik = titikDistribusiLogistik;
    }
}
