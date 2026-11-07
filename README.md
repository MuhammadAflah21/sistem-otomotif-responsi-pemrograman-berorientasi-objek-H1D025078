# Responsi Akhir Praktikum Pemrograman Berorientasi Objek (PBO)

Sistem Manajemen Armada Otomotif Terpadu berbasis bahasa pemrograman **Java**.

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
| **1** | *State dan Behavior* | Memiliki atribut berupa variabel, *method* prosedur (`void`), dan *method* fungsi yang mengembalikan nilai (*return value*). | `kendaraanOtomotif.java` memiliki atribut *state* (`merk`, `model`, `tahunProduksi`, `kecepatanSaatIni`, `mesinMenyala`), *method* prosedur `void` (`nyalakanMesin()`, `matikanMesin()`), serta *method* fungsi ber-*return value* (`getInfoSpesifikasi()`, `hitungPajakTahunan(double)`). |
| **2** | *Immutability* | Memiliki satu konstanta global (`public static final`) dan satu atribut instans kekal (`private final`). | Konstanta global `public static final String STANDAR_REGULASI_OTOMOTIF` pada `kendaraanOtomotif` (serta `KECEPATAN_MAKSIMUM_EV` dan `BATAS_KECEPATAN_TRUK`), dan atribut instans kekal `private final String nomorRangkaVIN`, `private final double kapasitasBateraiKwh`, serta `private final double kapasitasMuatanMaksTon`. |
| **3** | *Enkapsulasi dan Overloading* | Seluruh atribut berstatus `private` beserta *getter* dan *setter*; menggunakan *keyword* `this`; memiliki dua variasi konstruktor (*overloaded constructor*). | Seluruh atribut pada `kendaraanOtomotif`, `mobilListrikPintar`, dan `trukKargoDiesel` dideklarasikan `private` lengkap dengan *getter* dan *setter* menggunakan `this`. Masing-masing *class* memiliki 2 *overloaded constructor* (konstruktor parameter lengkap & konstruktor ringkas). |
| **4** | *Inheritance* | Terdapat relasi *superclass* dan dua *subclass* melalui `extends`; *class* `Main` dipisahkan dari *class* lainnya. | `mobilListrikPintar extends kendaraanOtomotif` dan `trukKargoDiesel extends kendaraanOtomotif`. `Main.java` dipisahkan dalam file sendiri. |
| **5** | *Super Chaining* | Pemanggilan `super(...)` pada konstruktor *subclass* dan pemanggilan `super.namaMethod()`. | Kedua konstruktor di `mobilListrikPintar` dan `trukKargoDiesel` memanggil `super(...)`, serta *method* `getInfoSpesifikasi()` pada kedua *subclass* memanggil `super.getInfoSpesifikasi()`. |
| **6** | *Method Overriding* | Penimpaan *method superclass* pada *subclass* menggunakan anotasi `@Override`. | *Method* `getInfoSpesifikasi()` dan `akselerasiKendaraan(double)` pada `mobilListrikPintar` serta `trukKargoDiesel` menimpa *method superclass* dengan anotasi `@Override`. |
| **7** | *Abstract Class* | *Superclass* berstatus `abstract` dan memiliki minimal satu *abstract method*. | `public abstract class kendaraanOtomotif` memiliki *abstract method* `public abstract void akselerasiKendaraan(double tambahanKecepatan);`. |
| **8** | *Logika State Percabangan* | Implementasi *abstract method* memuat logika `if/else` yang membaca dan mengubah *state* internal objek. | Implementasi `akselerasiKendaraan(double)` pada `mobilListrikPintar` dan `trukKargoDiesel` menggunakan percabangan `if / else if / else` untuk memeriksa status mesin, level baterai/solar, batas kecepatan, dan kapasitas muatan, lalu mengubah *state* kecepatan, bahan bakar/baterai, mode berkendara, atau mematikan mesin secara otomatis. |
| **9** | *Polimorfisme Interface* | Pemanggilan *method* objek melalui variabel referensi bertipe *interface* (`NamaInterface obj = new Subclass();`). | Pada `Main.java`: `pengisianDayaCepat stasiunCas = new mobilListrikPintar(...);` dan `navigasiCerdas navigasiArmadaEv = new mobilListrikPintar(...);` serta `navigasiCerdas navigasiArmadaTruk = new trukKargoDiesel(...);`. |
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

## 4. Screenshot Output Program

### Screenshot Uji 1
[screenshotUji1.png]

### Screenshot Uji 2
[screenshotUji2.png]

### Screenshot Uji 3
[screenshotUji3.png]

### Screenshot Uji 4
[screenshotUji4.png]

### Screenshot Uji Multiple Interface
[ScreenshotUjiMultipleInterface.png]