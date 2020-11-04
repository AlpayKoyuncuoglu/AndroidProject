<?php
include("ayar.php");
$musId=$_POST["id"];
$petId=$_POST["petId"];
$sor=mysqli_query($baglan,"SELECT a.petIsim,a.petTur,a.petCins,a.petResim,b.asiTarih,b.asiIsim FROM petlist a INNER JOIN veterinerTakipAsi b ON a.id=b.petId WHERE a.musId='$musId' 
AND b.musId='$musId' AND b.asiDurum='1' and b.petId='$petId' ");

$count=mysqli_num_rows($sor);

class asiTakip
{
    public $petIsim;
    public $petTur;
    public $petCins;
    public $petResim;
    public $asiTarih;
    public $asiIsim;
    public $tf;
}

$asi=new asiTakip();
$sayac=0;

if($count>0)
{
    echo("[");
    while($bilgi=mysqli_fetch_assoc($sor))
    {
        $sayac=$sayac+1;
        $asi->petIsim=$bilgi ["petIsim"];
        $asi->petTur=$bilgi ["petTur"];
        $asi->petCins=$bilgi ["petCins"];
        $asi->petResim=$bilgi ["petResim"];
        $asi->asiTarih=$bilgi ["asiTarih"];
        $asi->asiIsim=$bilgi ["asiIsim"];
        $asi->tf=true;
        echo(json_encode($asi));
        if($count>$sayac)
        {
            echo(",");
        }
    }
    echo("]");

}
else
{
        echo("[");
        $asi->petIsim=null;
        $asi->petTur=null;
        $asi->petCins=null;
        $asi->petResim=null;
        $asi->asiTarih=null;
        $asi->asiIsim=null;
        $asi->tf=false;
        echo(json_encode($asi));
        echo("]");

}

?>