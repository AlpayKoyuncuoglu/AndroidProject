<?php

echo("hosgeldiniz");

include("ayar.php");
    
    $mail=$_GET["mail"];
	$kod=$_GET["dogrulamakodu"];

$guncelle=mysqli_query($baglan,"update kullanicilar set durum = '1'  where mailAdres='$mail' and dogrulamaKodu='$kod' ");

if($guncelle)
{
	echo "hesabiniz dogrulandi durum 1";
}
else
echo "durum hala 0";


?>