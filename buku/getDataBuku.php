<?php
require_once ('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='GET') {
    $sql = "SELECT * FROM tb_buku ORDER BY ID_BUKU ASC";
    $res = mysqli_query($con, $sql);
    $result = array();
    while($row = mysqli_fetch_array($res)) {
        array_push($result, array('id'=>$row[0],'nama_buku'=>$row[1], 
                                                'pengarang_buku'=>$row[2], 
                                                'penerbit_buku'=>$row[3], 
                                                'tahun_terbit'=>$row[4], 
                                                'deskripsi'=>$row[5]));
    }
    echo json_encode(array("value"=>1, "result"=>$result));
    mysqli_close($con);
}
?>