<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
    $response = array();
    $id_buku = $_POST['id_buku'];
    $tb_buku = 'tb_buku';

    $sql = "DELETE FROM $tb_buku WHERE ID_BUKU = '$id_buku'";
    if(mysqli_query($con, $sql)) {
        $response['value'] = 1;
        $response['message'] = "Data Buku berhasil dihapus";
        echo json_encode($response);
    } else {
        $response['value'] = 0;
        $response['message'] = "Data Buku gagal dihapus!!!";
        echo json_encode($response);
    }
    mysqli_close($con);
}else {
    $response['value'] = 0;
    $response['message'] = "Penghapusan data Buku gagal, silakan coba kembali!";
    echo json_encode($response);
}
?>