<?php
include("ayar.php");
$mailA=$_POST["mailadres"];
$sifre =$_POST["sifre"];

$control=mysqli_query($baglan,"select * from kullanicilar where mailAdres='$mailA' and parola='$sifre'");
$count=mysqli_num_rows($control);

class UserLogin
{
    public $id;
    public $username;
    public $mailadres;
    public $parola;
    public $tf;
    public $text;
}

$user=new UserLogin();

if($count>0)
{
    $parse=mysqli_fetch_assoc($control);
    $durum=$parse["durum"];
    $id=$parse["id"];
    $username=$parse["kadi"];
    $parola=$parse["parola"];
    $mailadres=$parse["mailAdres"];

    
    if($durum==1)
    {
    $user->tf =true;
    $user->text ="sisteme giris basarilidir";
    $user->id=$id;
    $user->parola=$parola;
    $user->username=$username;
    $user->mailadres=$mailadres; 
        echo(json_encode($user));

    }
    else
    {
        
    $user->tf =false;
    $user->text ="sistemee giris yapabilmeniz icin mail adresinizi onaylamaniz gerekmektedir";
    $user->id=null;
    $user->parola=null;
    $user->username=null;
    $user->mailadres=null; 
        echo(json_encode($user));

    }
    
}
else
{
    $user->tf =false;
    $user->text ="sistemde girilen bilgilere gore bir kullanici bulunmamaktadir";
    $user->id=null;
    $user->parola=null;
    $user->username=null;
    $user->mailadres=null; 
    echo(json_encode($user));
}


?>