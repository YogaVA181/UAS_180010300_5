<?php

    define('HOST', 'localhost'); // change with your host

    define('USER', 'root'); // change with your database user

    define('PASS', ''); // change with your user password

    define('DB', 'db_buku'); // database name

    $con = mysqli_connect(HOST, USER, PASS, DB) or die ('Gagal untuk terhubung');

?>