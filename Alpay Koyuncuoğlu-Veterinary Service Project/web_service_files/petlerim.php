<?php
include("ayar.php");
$musId=$_POST["musid"];
$sorgula=mysqli_query($baglan,"select * from petlist where musId='$musId' ");
$count=mysqli_num_rows($sorgula);

class petClass{
    public $petid;
    public $petresim;
    public $petisim;
    public $pettur;
    public $petcins;
    public $tf;
    
}

$pet=new petClass();
$sayac=0;
if($count>0)
{
    echo("[");
    while($bilgi=mysqli_fetch_assoc($sorgula))
    {
        $sayac=$sayac+1;
        $pet->petid=$bilgi["id"];
        $pet->petresim=$bilgi["petResim"];
        $pet->petisim=$bilgi["petIsim"];
        $pet->pettur=$bilgi["petTur"];
        $pet->petcins=$bilgi["petCins"];
        $pet->tf=true;
        echo(json_encode($pet));
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
        $pet->petid=null;
        $pet->petesim=null;
        $pet->petisim=null;
        $pet->pettur=null;
        $pet->petcins=null;
        $pet->tf=false;
        echo(json_encode($pet));
             echo("]");

    
}


?>