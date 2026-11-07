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
<img width="1002" height="244" alt="Screenshot 2026-11-07 155522" src="https://github.com/user-attachments/assets/67d2cf35-df33-4185-9fab-78363b2c40f1" />


### Screenshot Uji 2
<img width="1064" height="1196" alt="Screenshot 2026-11-07 155407" src="https://github.com/user-attachments/assets/f8e28e37-932a-4690-9048-70c733e9b065" />


### Screenshot Uji 3
<img width="1444" height="330" alt="Screenshot 2026-11-07 155425" src="https://github.com/user-attachments/assets/ec260f89-4c3a-4a8e-939c-17f1ca9e64cc" />


### Screenshot Uji 4
<img width="1350" height="226" alt="Screenshot 2026-11-07 155449" src="https://github.com/user-attachments/assets/4f667be4-682e-4644-881b-2f3e4e5b486f" />


### Screenshot Uji Multiple Interface
<img width="1426" height="428" alt="Screenshot 2026-11-07 155503" src="https://github.com/user-attachments/assets/17a287c7-7bfd-4510-9ee6-30d1b2d20d82" />
