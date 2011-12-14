$req = [System.Net.HttpWebRequest]::Create($args[0]);
$res = $req.GetResponse();

Write-Output $res;

$res.close();
