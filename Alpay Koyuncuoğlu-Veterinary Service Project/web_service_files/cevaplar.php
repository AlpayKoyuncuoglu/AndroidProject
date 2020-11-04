<?php

include("ayar.php");

$musId=$_POST["id"];

$sor=mysqli_query($baglan,"SELECT s.id as soruId, s.soru, c.cevap,c.id as cevapId,c.soruId FROM veterinerSorular 
s INNER JOIN veterinerCevaplar c ON s.id=c.soruId WHERE s.durum='1' and s.musId='$musId'");
//echo(mysqli_num_rows($sor));

$count=mysqli_num_rows($sor);

class soruClass{
    public $soru;
    public $cevap;
    public $cevapId;
    public $soruId;
    public $tf;
    
}

$soru=new soruClass();
$sayac=0;
if($count>0)
{
    echo("[");
    while($bilgi=mysqli_fetch_assoc($sor))
    {
        $sayac=$sayac+1;
        $soru->soru=$bilgi["soru"];
        $soru->cevap=$bilgi["cevap"];
        $soru->soruId=$bilgi["soruId"];
        $soru->cevapId=$bilgi["cevapId"];
        $soru->tf=true;
        echo(json_encode($soru));
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
        $soru->soru=null;
        $soru->cevap=null;
        $soru->soruId=null;
        $soru->cevapId=null;
        $soru->tf=false;
        echo(json_encode($soru));
             echo("]");

    
}


?>