public class Main {
    public static void main(String[] args) {
        System.out.println(">>> [UJI 1] INSTANSIASI OBJEK, OVERLOADING KONSTRUKTOR, & IMMUTABILITY <<<");
        System.out.println("Standar Regulasi Global  : " + kendaraanOtomotif.STANDAR_REGULASI_OTOMOTIF);
        System.out.println("Batas Kecepatan EV       : " + mobilListrikPintar.KECEPATAN_MAKSIMUM_EV + " km/jam");
        System.out.println("Batas Kecepatan Truk     : " + trukKargoDiesel.BATAS_KECEPATAN_TRUK + " km/jam\n");

        mobilListrikPintar ev1 = new mobilListrikPintar("VIN-EV-2025-8801", "Hyundai", "Ioniq 5 Signature", 2025, 72.6, 24.0, "SPORT");
        mobilListrikPintar ev2 = new mobilListrikPintar("VIN-EV-2026-8802", "BYD", "Seal Performance", 82.5);
        trukKargoDiesel truk1 = new trukKargoDiesel("VIN-TRK-2024-9901", "Hino", "500 Series Ranger", 2024, 20.0, 12.5, 80.0);
        trukKargoDiesel truk2 = new trukKargoDiesel("VIN-TRK-2026-9902", "Mitsubishi Fuso", "Fighter X", 18.0);

        System.out.println("Atribut Instans Kekal (private final VIN EV-1)          : " + ev1.getNomorRangkaVIN());
        System.out.println("Atribut Instans Kekal (private final Baterai EV-2)      : " + ev2.getKapasitasBateraiKWh() + " kWh");
        System.out.println("Atribut Instans Kekal (private final VIN TRUK-1)        : " + truk1.getNomorRangkaVIN());
        System.out.println("Atribut Instans Kekal (private final Maks Muatan TRUK-2): " + truk2.getKapasitasMuatanMaksTon() + " Ton");
        System.out.println();

        System.out.println(">>> [UJI 2] ENKAPSULASI (GETTER/SETTER), SUPER CHAINING, & METHOD OVERRIDING <<<");
        ev2.setModeBerkendara("NORMAL");
        truk2.setBeratMuatanSaatIniTon(9.0);

        System.out.println("Spesifikasi EV-1   : " + ev1.getInfoSpesifikasi());
        System.out.println("Spesifikasi EV-2   : " + ev2.getInfoSpesifikasi());
        System.out.println("Spesifikasi Truk-1 : " + truk1.getInfoSpesifikasi());
        System.out.println("Spesifikasi Truk-2 : " + truk2.getInfoSpesifikasi());

        double pajakEv1 = ev1.hitungPajakTahunan(780000000);
        double pajakTruk1 = truk1.hitungPajakTahunan(950000000);
        System.out.printf("Estimasi Pajak Tahunan EV-1   : Rp%,.2f%n", pajakEv1);
        System.out.printf("Estimasi Pajak Tahunan Truk-1 : Rp%,.2f%n", pajakTruk1);
        System.out.println();

        System.out.println(">>> [UJI 3] ABSTRACT METHOD & LOGIKA STATE PERCABANGAN (IF/ELSE) <<<");
        ev1.akselerasiKendaraan(40.0);
        ev1.nyalakanMesin();
        ev1.akselerasiKendaraan(30.0);
        ev1.akselerasiKendaraan(50.0);

        System.out.println();
        truk1.nyalakanMesin();
        truk1.akselerasiKendaraan(65.0);
        truk1.akselerasiKendaraan(40.0);
        truk1.tambahMuatanKargo(10.0);
        truk1.akselerasiKendaraan(10.0);
        System.out.println();

        System.out.println(">>> [UJI 4] POLIMORFISME INTERFACE & MULTIPLE INTERFACE <<<");
        pengisianDayaCepat stasiunCas = new mobilListrikPintar("VIN-EV-2026-8803", "Wuling", "Cloud EV", 2026, 50.6, 35.0, "ECO");
        navigasiCerdas navigasiArmadaEv = new mobilListrikPintar("VIN-EV-2026-8804", "Chery", "Omoda E5", 2026, 61.0, 80.0, "SPORT");
        navigasiCerdas navigasiArmadaTruk = new trukKargoDiesel("VIN-TRK-2025-9903", "Isuzu", "Giga FVM", 2025, 25.0, 16.0, 120.0);

        double waktuCasMenit = stasiunCas.estimasiWaktuCas(60.0);
        System.out.printf("Estimasi Waktu Cas Penuh (Charger 60 kW) : %.1f menit%n", waktuCasMenit);
        stasiunCas.mulaiFastCharging(90.0);
        navigasiArmadaEv.aturRuteTujuan("FT Unsoed Blater, Purbalingga");
        System.out.println(navigasiArmadaEv.lacakPosisiKendaraan());
        navigasiArmadaTruk.aturRuteTujuan("Pelabuhan Tanjung Priok, Jakarta");
        System.out.println(navigasiArmadaTruk.lacakPosisiKendaraan());

        System.out.println();
        System.out.println("--- Uji Multiple Interface pada Objek MobilListrikPintar (ev1) ---");
        ev1.aturRuteTujuan("SPKLU Rest Area KM 207A");
        System.out.println(ev1.lacakPosisiKendaraan());
        ev1.mulaiFastCharging(85.0);
        System.out.println("Status Akhir EV-1 : " + ev1.getInfoSpesifikasi());
    }
}
