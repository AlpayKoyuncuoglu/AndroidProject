<?php

include("ayar.php");

$musId=$_POST["id"];
$soru=$_POST["soru"];
$ekle=mysqli_query($baglan,"insert into veterinerSorular(musId,soru,durum) values ('$musId','$soru','0')");

class ekleme
{
    public $text;
    public $tf;
}

$ekle=new ekleme();

if($ekle)
{
    $ekle->text="sorunuz ulasti. Veteriner yanit verdiginde cevaplar alaninda gozukecektir ";
    $ekle->tf=true;
    echo(json_encode($ekle));
}
else
{
    $ekle->text="soru gonderilemedi ";
    $ekle->tf=false;
    echo(json_encode($ekle));

}

?>