# Responsi Akhir Praktikum Pemrograman Berorientasi Objek (PBO)

Sistem Manajemen Armada Otomotif Terpadu berbasis bahasa pemrograman **Java** yang dirancang untuk memenuhi seluruh 10 persyaratan teknis Responsi Akhir Praktikum PBO Jurusan Informatika, Fakultas Teknik, Universitas Jenderal Soedirman.

## 1. Identitas Mahasiswa dan Tema NIM

| Atribut | Keterangan |
| :--- | :--- |
| **Nama Mahasiswa** | *Muhammad Aflah Alfafa Mufa* |
| **NIM** | *H1D025078* |
| **Digit Terakhir NIM** | `8` |
| **Tema Sistem** | **Otomotif** |
| **Jurusan / Fakultas** | Informatika / Fakultas Teknik — Universitas Jenderal Soedirman |

## 2. Arsitektur Class dan Pemetaan 10 Persyaratan Teknis

Sistem ini memodelkan manajemen kendaraan di industri otomotif modern yang mencakup kendaraan listrik pintar (*Smart Electric Vehicle*) dan armada truk logistik diesel:

| No. | Konsep PBO | Ketentuan Lembar Penugasan | Implementasi pada Kode Sumber |
| :--- | :--- | :--- | :--- |
| **1** | *State dan Behavior* | Memiliki atribut berupa variabel, *method* prosedur (`void`), dan *method* fungsi yang mengembalikan nilai (*return value*). | `KendaraanOtomotif.java` memiliki atribut *state* (`merk`, `model`, `tahunProduksi`, `kecepatanSaatIni`, `mesinMenyala`), *method* prosedur `void` (`nyalakanMesin()`, `matikanMesin()`), serta *method* fungsi ber-*return value* (`getInfoSpesifikasi()`, `hitungPajakTahunan(double)`). |
| **2** | *Immutability* | Memiliki satu konstanta global (`public static final`) dan satu atribut instans kekal (`private final`). | Konstanta global `public static final String STANDAR_REGULASI_OTOMOTIF` pada `KendaraanOtomotif` (serta `KECEPATAN_MAKSIMUM_EV` dan `BATAS_KECEPATAN_TRUK`), dan atribut instans kekal `private final String nomorRangkaVIN`, `private final double kapasitasBateraiKwh`, serta `private final double kapasitasMuatanMaksTon`. |
| **3** | *Enkapsulasi dan Overloading* | Seluruh atribut berstatus `private` beserta *getter* dan *setter*; menggunakan *keyword* `this`; memiliki dua variasi konstruktor (*overloaded constructor*). | Seluruh atribut pada `KendaraanOtomotif`, `MobilListrikPintar`, dan `trukKargoDiesel` dideklarasikan `private` lengkap dengan *getter* dan *setter* menggunakan `this`. Masing-masing *class* memiliki 2 *overloaded constructor* (konstruktor parameter lengkap & konstruktor ringkas). |
| **4** | *Inheritance* | Terdapat relasi *superclass* dan dua *subclass* melalui `extends`; *class* `Main` dipisahkan dari *class* lainnya. | `mobilListrikPintar extends kendaraanOtomotif` dan `trukKargoDiesel extends kendaraanOtomotif`. `Main.java` dipisahkan dalam file sendiri. |
| **5** | *Super Chaining* | Pemanggilan `super(...)` pada konstruktor *subclass* dan pemanggilan `super.namaMethod()`. | Kedua konstruktor di `mobilListrikPintar` dan `trukKargoDiesel` memanggil `super(...)`, serta *method* `getInfoSpesifikasi()` pada kedua *subclass* memanggil `super.getInfoSpesifikasi()`. |
| **6** | *Method Overriding* | Penimpaan *method superclass* pada *subclass* menggunakan anotasi `@Override`. | *Method* `getInfoSpesifikasi()` dan `akselerasiKendaraan(double)` pada `mobilListrikPintar` serta `trukKargoDiesel` menimpa *method superclass* dengan anotasi `@Override`. |
| **7** | *Abstract Class* | *Superclass* berstatus `abstract` dan memiliki minimal satu *abstract method*. | `public abstract class kendaraanOtomotif` memiliki *abstract method* `public abstract void akselerasiKendaraan(double tambahanKecepatan);`. |
| **8** | *Logika State Percabangan* | Implementasi *abstract method* memuat logika `if/else` yang membaca dan mengubah *state* internal objek. | Implementasi `akselerasiKendaraan(double)` pada `mobilListrikPintar` dan `trukKargoDiesel` menggunakan percabangan `if / else if / else` untuk memeriksa status mesin, level baterai/solar, batas kecepatan, dan kapasitas muatan, lalu mengubah *state* kecepatan, bahan bakar/baterai, mode berkendara, atau mematikan mesin secara otomatis. |
| **9** | *Polimorfisme Interface* | Pemanggilan *method* objek melalui variabel referensi bertipe *interface* (`NamaInterface obj = new Subclass();`). | Pada `Main.java`: `PengisianDayaCepat stasiunCas = new mobilListrikPintar(...);` dan `NavigasiCerdas navigasiArmadaEv = new mobilListrikPintar(...);` serta `NavigasiCerdas navigasiArmadaTruk = new trukKargoDiesel(...);`. |
| **10** | *Multiple Interface* | *subclass* mengimplementasikan dua *interface* sekaligus (`implements A, B`). | `public class mobilListrikPintar extends kendaraanOtomotif implements pengisianDayaCepat, navigasiCerdas`. |

## 3. Petunjuk Compile dan Run Melalui Terminal

Pastikan **JDK (Java Development Kit)** sudah terinstal, lalu buka terminal atau *Command Prompt* pada direktori proyek ini, lalu jalankan perintah berikut:

### a. Kompilasi Seluruh Kode Sumber (`javac`)

```bash
javac *.java
```

Atau menyebutkan masing-masing berkas secara eksplisit:

```bash
javac navigasiCerdas.java pengisianDayaCepat.java kendaraanOtomotif.java mobilListrikPintar.java trukKargoDiesel.java Main.java
```

### b. Menjalankan Program Utama (`java`)

```bash
java Main
```



### Teks Luaran Program di Terminal

```text
================================================================================
          SISTEM MANAJEMEN ARMADA OTOMOTIF TERPADU (RESPONSI AKHIR PBO)
                     Tema Sistem: Otomotif (Digit NIM 8 & 9)
================================================================================

>>> [UJI 1] INSTANSIASI OBJEK, OVERLOADING KONSTRUKTOR, & IMMUTABILITY
Standar Regulasi Global (public static final) : SNI-EURO4-INDONESIA
Batas Kecepatan EV (public static final)      : 220.0 km/jam
Batas Kecepatan Truk (public static final)    : 90.0 km/jam

Atribut Instans Kekal (private final VIN EV-1)   : VIN-EV-2025-8801
Atribut Instans Kekal (private final Baterai EV) : 72.6 kWh
Atribut Instans Kekal (private final Maks Muatan): 20.0 Ton

>>> [UJI 2] ENKAPSULASI (GETTER/SETTER), SUPER CHAINING, & METHOD OVERRIDING
Spesifikasi EV-1   : VIN: VIN-EV-2025-8801 | Kendaraan: Hyundai Ioniq 5 Signature (2025) | Regulasi: SNI-EURO4-INDONESIA | Status Mesin: Mati | Kecepatan: 0.0 km/jam | Tipe: Mobil Listrik (EV) | Kapasitas Baterai: 72.6 kWh (24.0%) | Mode: SPORT | Rute: Belum Diatur
Spesifikasi EV-2   : VIN: VIN-EV-2026-8802 | Kendaraan: BYD Seal Performance (2026) | Regulasi: SNI-EURO4-INDONESIA | Status Mesin: Mati | Kecepatan: 0.0 km/jam | Tipe: Mobil Listrik (EV) | Kapasitas Baterai: 82.5 kWh (100.0%) | Mode: NORMAL | Rute: Belum Diatur
Spesifikasi Truk-1 : VIN: VIN-TRK-2024-9901 | Kendaraan: Hino 500 Series Ranger (2024) | Regulasi: SNI-EURO4-INDONESIA | Status Mesin: Mati | Kecepatan: 0.0 km/jam | Tipe: Truk Kargo Diesel | Muatan: 12.5/20.0 Ton | Solar: 80.0 L | Tujuan Logistik: Depot Utama
Spesifikasi Truk-2 : VIN: VIN-TRK-2026-9902 | Kendaraan: Mitsubishi Fuso Fighter X (2026) | Regulasi: SNI-EURO4-INDONESIA | Status Mesin: Mati | Kecepatan: 0.0 km/jam | Tipe: Truk Kargo Diesel | Muatan: 9.0/18.0 Ton | Solar: 150.0 L | Tujuan Logistik: Depot Utama
Estimasi Pajak Tahunan EV-1   : Rp 11,349,000.00
Estimasi Pajak Tahunan Truk-1 : Rp 13,395,000.00

>>> [UJI 3] ABSTRACT METHOD & LOGIKA STATE PERCABANGAN (IF/ELSE)
[PERINGATAN EV] Gagal akselerasi: Mesin Hyundai Ioniq 5 Signature masih mati!
[Hyundai Ioniq 5 Signature] Mesin berhasil dinyalakan.
[AUTO-SWITCH EV] Baterai di bawah 20%, mode berkendara diubah otomatis ke ECO.
[AKSELERASI EV] Hyundai Ioniq 5 Signature melaju pada 30.0 km/jam | Sisa Baterai: 16.5% | Mode: ECO
[AKSELERASI EV] Hyundai Ioniq 5 Signature melaju pada 80.0 km/jam | Sisa Baterai: 10.5% | Mode: ECO

[Hino 500 Series Ranger] Mesin berhasil dinyalakan.
[AKSELERASI TRUK] Hino 500 Series Ranger melaju 65.0 km/jam | Muatan: 12.5/20.0 ton | Sisa Solar: 71.6 Liter
[GOVERNOR TRUK] Kecepatan truk kargo dibatasi maksimal 90.0 km/jam.
[AKSELERASI TRUK] Hino 500 Series Ranger melaju 90.0 km/jam | Muatan: 12.5/20.0 ton | Sisa Solar: 66.4 Liter
[KARGO TRUK] Muatan bertambah 10.0 ton. Total muatan sekarang: 22.5 / 20.0 ton.
[OVERLOAD LOCK] Muatan (22.5 ton) melebihi kapasitas maksimal (20.0 ton)! Truk dilarang melaju.

>>> [UJI 4] POLIMORFISME INTERFACE & MULTIPLE INTERFACE
Estimasi Waktu Cas Penuh (Charger 60 kW) : 32.9 menit
[FAST CHARGING] Mengisi daya Wuling Cloud EV dari 35.0% menuju 90.0%...
[FAST CHARGING] Selesai! Baterai sekarang berada pada level 90.0%.

[NAVIGASI CERDAS] Rute berhasil diatur menuju: Kampus Teknik Unsoed Purbalingga
GPS Aktif -> Chery Omoda E5 sedang menuju 'Kampus Teknik Unsoed Purbalingga' dengan kecepatan 0.0 km/jam
[LOGISTIK TRUK] Titik distribusi kargo diarahkan ke: Pelabuhan Tanjung Priok Jakarta
Fleet Tracker -> Truk Isuzu Giga FVM membawa 16.0 ton kargo menuju 'Pelabuhan Tanjung Priok Jakarta'

--- Uji Multiple Interface pada Objek MobilListrikPintar (ev1) ---
[NAVIGASI CERDAS] Rute berhasil diatur menuju: SPKLU Rest Area KM 207A
GPS Aktif -> Hyundai Ioniq 5 Signature sedang menuju 'SPKLU Rest Area KM 207A' dengan kecepatan 80.0 km/jam
[Hyundai Ioniq 5 Signature] Mesin dimatikan dan kendaraan berhenti.
[FAST CHARGING] Mengisi daya Hyundai Ioniq 5 Signature dari 10.5% menuju 85.0%...
[FAST CHARGING] Selesai! Baterai sekarang berada pada level 85.0%.
Status Akhir EV-1 : VIN: VIN-EV-2025-8801 | Kendaraan: Hyundai Ioniq 5 Signature (2025) | Regulasi: SNI-EURO4-INDONESIA | Status Mesin: Mati | Kecepatan: 0.0 km/jam | Tipe: Mobil Listrik (EV) | Kapasitas Baterai: 72.6 kWh (85.0%) | Mode: ECO | Rute: SPKLU Rest Area KM 207A
