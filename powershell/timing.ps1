#----------------------------------------------------------------------------
# Windows Powershell Timer.
#----------------------------------------------------------------------------

$timingName = "Timing";
$timingPoints = @(
    "Step One",
    "Step Two",
    "Step Three",
    "Step Four"
);

$resultFile = $timingName + ' Timing ' + [DateTime]::Now.ToString( "yyyy-MM-dd hh-mm-ss" ) + ".csv";

$timeStamps = @();
$textItems = @();
[DateTime] $timeStamp = [DateTime]::Now;

# Add timing message
function AddMsg( [string] $msgTxt )
{
    $SCRIPT:timeStamp = [DateTime]::Now;
    $SCRIPT:timeStamps += $SCRIPT:timeStamp;
    $SCRIPT:textItems += $msgTxt;
}

$resultTxt = @();
$resultTxt += "StartTime`tEndTime`tDuration`tTotalDuration`tEvent";

[DateTime] $startTimeStamp = [DateTime]::Now;
[DateTime] $prevTimeStamp = $startTimeStamp;
foreach( $timingPoint in $timingPoints )
{
    Read-Host "Press Enter";
    AddMsg( $timingPoint );
    Write-Host( $prevTimeStamp.ToString('yyyy-MM-dd hh:mm:ss.ffffff') + " / " +
                $timeStamp.ToString('yyyy-MM-dd hh:mm:ss.ffffff') + " / " +
                $timeStamp.Subtract( $prevTimeStamp ).ToString() + " / " +
                $timeStamp.Subtract( $startTimeStamp ).ToString() );
    $resultTxt += $prevTimeStamp.ToString('yyyy-MM-dd hh:mm:ss.ffffff') + "`t" +
                  $timeStamp.ToString('yyyy-MM-dd hh:mm:ss.ffffff') + "`t" +
                  $timeStamp.Subtract( $prevTimeStamp ).ToString() + "`t" +
                  $timeStamp.Subtract( $startTimeStamp ).ToString() + "`t" +
                  $timingPoint;
    $prevTimeStamp = $timeStamp;

    Write-Host( $timingPoint );
}

Write-Host;
Write-Host( "Created file:" );
Write-Host( $resultFile );
$resultTxt | Set-Content $resultFile;
Write-Host;
