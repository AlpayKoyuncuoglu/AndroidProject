<?php
include("ayar.php");
$cevapId=$_POST["cevap"];
$soruId=$_POST["soru"];
$sil=mysqli_query($baglan,"delete from veterinerCevaplar where id='$cevapId' and soruId='$soruId' ");
$sil2=mysqli_query($baglan,"delete from veterinerSorular where id='$soruId' ");

class deleteRecord
{
    public $text;
    public $tf;
}

$del=new deleteRecord();
$del->text="silme islemi gerceklesti";
$del->tf=true;
echo(json_encode($del));


                            
?>