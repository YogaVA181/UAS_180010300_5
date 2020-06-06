<?php
require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    $response = array();
    // Mengambil data
    $nama_buku = $_POST['nama_buku'];
    $pengarang_buku = $_POST['pengarang_buku'];
    $penerbit_buku = $_POST['penerbit_buku'];
    $tahun_terbit = $_POST['tahun_terbit'];
    $deskripsi = $_POST['deskripsi'];
    $tb_buku = "tb_buku";

    //Memeriksa apakah norek sudah terdaftar atau belum pada databse tb_nasabah
    $sql = "SELECT * FROM $tb_buku WHERE NAMA_BUKU = '$nama_buku'";
    $check = mysqli_fetch_array(mysqli_query($con, $sql));
    if(isset($check)){
        $response["value"] = 0;
        $response["message"] = "Buku dengan nomor rekening $nama_buku sudah terdaftar, silakan lakukan pembaharuan data";
        echo json_encode($response);
    } else {
        $sql = "INSERT INTO $tb_buku (NAMA_BUKU, PENGARANG_BUKU, PENERBIT_BUKU, TAHUN_TERBIT, DESKRIPSI) 
        VALUES ('$nama_buku','$pengarang_buku','$penerbit_buku','$tahun_terbit','$deskripsi')";
        if(mysqli_query($con, $sql)) {
            $response["value"] = 1;
            $response["message"] = "Sukses menambahkan buku baru";
            echo json_encode($response);
        } else {    
            $response["value"] = 0;
            $response["message"] = "Gagal menambahkan buku baru";
            echo json_encode($response);
        }
    }

    //tutup konekasi ke basis data
    mysqli_close($con);
} else {
    $response["value"] = 0;
    $response["message"] = "Pendaftaran buku baru gagal, silakan coba lagi!";
    echo json_encode($response);
}
?>