<?php
$userName="kullanici";
$sifre="alpay1908";
$serverName="n3plcpnl0056";
$dbName="vetpet";    

$baglan=mysqli_connect($serverName,$userName,$sifre,$dbName);
mysqli_set_charset($baglan,"UTF-8");
mysqli_query($baglan,"SET NAMES UTF8");

?>