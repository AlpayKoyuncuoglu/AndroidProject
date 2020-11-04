<?php

include("ayar.php");

$mailAdres=$_POST["mailAdres"];
$kadi=$_POST["kadi"];
$parola=$_POST["pass"];
$dogrulamaKodu=rand(0,10000).rand(0.10000);
$durum=0;

$kontrol=mysqli_query($baglan,"select * from kullanicilar where mailAdres='$mailAdres' or kadi ='$kadi'");
$count=mysqli_num_rows($kontrol);

class User{
	public $text;
	public $tf;
}
$user=new User();

if($count>0)
{
	$user->text="girmis oldugunuz bilgilere ait kullanici bulunmaktadir";
	$user->tf=false;
	echo(json_encode($user));

}
else
{


$addUser=mysqli_query($baglan,"insert into kullanicilar(kadi,mailAdres,parola,dogrulamaKodu,durum) values ('$kadi','$mailAdres','$parola','$dogrulamaKodu','$durum')");

$git="http://alpaykyncglu.org/veterinerservis/aktifet.php?mail=".$mailAdres."&dogrulamakodu=".$dogrulamaKodu."";
$to="alpaykoyuncuoglu1@gmail.com";
$subject="hesab aktiflestirme";
$text="sisteme giris icin <a href ='".$git."'>onayla </a> ";
$from="From: info@alpaykyncglu.org";
mail($to,$subject,$text,$from); 


$user->text= "hesap kaydedildi ancak gelen maili onaylamaniz gerekmektedir";
$user->tf=true;
echo(json_encode($user));

}
?>