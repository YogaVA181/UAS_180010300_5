<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
    $response = array();
    // Mengambil data
    $id_buku = $_POST['id_buku'];
    $nama_buku = $_POST['nama_buku'];
    $pengarang_buku = $_POST['pengarang_buku'];
    $penerbit_buku = $_POST['penerbit_buku'];
    $tahun_terbit = $_POST['tahun_terbit'];
    $deskripsi = $_POST['deskripsi'];
    $tb_buku = "tb_buku";
   

    $sql = "UPDATE $tb_buku SET NAMA_BUKU = '$nama_buku', PENGARANG_BUKU = '$pengarang_buku', PENERBIT_BUKU = '$penerbit_buku', TAHUN_TERBIT = '$tahun_terbit', DESKRIPSI = '$deskripsi' 
    WHERE ID_BUKU = '$id_buku'";
    if(mysqli_query($con, $sql)) {
        $response['value'] = 1;
        $response['message'] = "Data nasabah berhasil diperbaharui";
        echo json_encode($response);
    } else {
        $response['value'] = 0;
        $response['message'] = "Data nasabah gagal diperbaharui!!!";
        echo json_encode($response);
    }
    mysqli_close($con);
} else {
    $response['value'] = 0;
    $response['message'] = "Pembaharuan data nasabah gagal, silakan coba kembali!";
    echo json_encode($response);
}
?>