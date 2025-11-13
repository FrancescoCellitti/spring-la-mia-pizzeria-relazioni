<#:batchportion
@REM----------------------------------------------------------------------------
@REMLicensedtotheApacheSoftwareFoundation(ASF)underone
@REMormorecontributorlicenseagreements.SeetheNOTICEfile
@REMdistributedwiththisworkforadditionalinformation
@REMregardingcopyrightownership.TheASFlicensesthisfile
@REMtoyouundertheApacheLicense,Version2.0(the
@REM"License");youmaynotusethisfileexceptincompliance
@REMwiththeLicense.YoumayobtainacopyoftheLicenseat
@REM
@REMhttp://www.apache.org/licenses/LICENSE-2.0
@REM
@REMUnlessrequiredbyapplicablelaworagreedtoinwriting,
@REMsoftwaredistributedundertheLicenseisdistributedonan
@REM"ASIS"BASIS,WITHOUTWARRANTIESORCONDITIONSOFANY
@REMKIND,eitherexpressorimplied.SeetheLicenseforthe
@REMspecificlanguagegoverningpermissionsandlimitations
@REMundertheLicense.
@REM----------------------------------------------------------------------------

@REM----------------------------------------------------------------------------
@REMApacheMavenWrapperstartupbatchscript,version3.3.4
@REM
@REMOptionalENVvars
@REMMVNW_REPOURL-repourlbasefordownloadingmavendistribution
@REMMVNW_USERNAME/MVNW_PASSWORD-userandpasswordfordownloadingmaven
@REMMVNW_VERBOSE-true:enableverboselog;others:silencetheoutput
@REM----------------------------------------------------------------------------

@IF"%__MVNW_ARG0_NAME__%"==""(SET__MVNW_ARG0_NAME__=%~nx0)
@SET__MVNW_CMD__=
@SET__MVNW_ERROR__=
@SET__MVNW_PSMODULEP_SAVE=%PSModulePath%
@SETPSModulePath=
@FOR/F"usebackqtokens=1*delims=="%%AIN(`powershell-noprofile"&{$scriptDir='%~dp0';$script='%__MVNW_ARG0_NAME__%';icm-ScriptBlock([Scriptblock]::Create((Get-Content-Raw'%~f0')))-NoNewScope}"`)DO@(
IF"%%A"=="MVN_CMD"(set__MVNW_CMD__=%%B)ELSEIF"%%B"==""(echo%%A)ELSE(echo%%A=%%B)
)
@SETPSModulePath=%__MVNW_PSMODULEP_SAVE%
@SET__MVNW_PSMODULEP_SAVE=
@SET__MVNW_ARG0_NAME__=
@SETMVNW_USERNAME=
@SETMVNW_PASSWORD=
@IFNOT"%__MVNW_CMD__%"==""("%__MVNW_CMD__%"%*)
@echoCannotstartmavenfromwrapper>&2&&exit/b1
@GOTO:EOF
:endbatch/beginpowershell#>

$ErrorActionPreference="Stop"
if($env:MVNW_VERBOSE-eq"true"){
$VerbosePreference="Continue"
}

#calculatedistributionUrl,requires.mvn/wrapper/maven-wrapper.properties
$distributionUrl=(Get-Content-Raw"$scriptDir/.mvn/wrapper/maven-wrapper.properties"|ConvertFrom-StringData).distributionUrl
if(!$distributionUrl){
Write-Error"cannotreaddistributionUrlpropertyin$scriptDir/.mvn/wrapper/maven-wrapper.properties"
}

switch-wildcard-casesensitive($($distributionUrl-replace'^.*/','')){
"maven-mvnd-*"{
$USE_MVND=$true
$distributionUrl=$distributionUrl-replace'-bin\.[^.]*$',"-windows-amd64.zip"
$MVN_CMD="mvnd.cmd"
break
}
default{
$USE_MVND=$false
$MVN_CMD=$script-replace'^mvnw','mvn'
break
}
}

#applyMVNW_REPOURLandcalculateMAVEN_HOME
#mavenhomepattern:~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
if($env:MVNW_REPOURL){
$MVNW_REPO_PATTERN=if($USE_MVND-eq$False){"/org/apache/maven/"}else{"/maven/mvnd/"}
$distributionUrl="$env:MVNW_REPOURL$MVNW_REPO_PATTERN$($distributionUrl-replace"^.*$MVNW_REPO_PATTERN",'')"
}
$distributionUrlName=$distributionUrl-replace'^.*/',''
$distributionUrlNameMain=$distributionUrlName-replace'\.[^.]*$',''-replace'-bin$',''

$MAVEN_M2_PATH="$HOME/.m2"
if($env:MAVEN_USER_HOME){
$MAVEN_M2_PATH="$env:MAVEN_USER_HOME"
}

if(-not(Test-Path-Path$MAVEN_M2_PATH)){
New-Item-Path$MAVEN_M2_PATH-ItemTypeDirectory|Out-Null
}

$MAVEN_WRAPPER_DISTS=$null
if((Get-Item$MAVEN_M2_PATH).Target[0]-eq$null){
$MAVEN_WRAPPER_DISTS="$MAVEN_M2_PATH/wrapper/dists"
}else{
$MAVEN_WRAPPER_DISTS=(Get-Item$MAVEN_M2_PATH).Target[0]+"/wrapper/dists"
}

$MAVEN_HOME_PARENT="$MAVEN_WRAPPER_DISTS/$distributionUrlNameMain"
$MAVEN_HOME_NAME=([System.Security.Cryptography.SHA256]::Create().ComputeHash([byte[]][char[]]$distributionUrl)|ForEach-Object{$_.ToString("x2")})-join''
$MAVEN_HOME="$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"

if(Test-Path-Path"$MAVEN_HOME"-PathTypeContainer){
Write-Verbose"foundexistingMAVEN_HOMEat$MAVEN_HOME"
Write-Output"MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
exit$?
}

if(!$distributionUrlNameMain-or($distributionUrlName-eq$distributionUrlNameMain)){
Write-Error"distributionUrlisnotvalid,mustendwith*-bin.zip,butfound$distributionUrl"
}

#preparetmpdir
$TMP_DOWNLOAD_DIR_HOLDER=New-TemporaryFile
$TMP_DOWNLOAD_DIR=New-Item-ItemtypeDirectory-Path"$TMP_DOWNLOAD_DIR_HOLDER.dir"
$TMP_DOWNLOAD_DIR_HOLDER.Delete()|Out-Null
trap{
if($TMP_DOWNLOAD_DIR.Exists){
try{Remove-Item$TMP_DOWNLOAD_DIR-Recurse-Force|Out-Null}
catch{Write-Warning"Cannotremove$TMP_DOWNLOAD_DIR"}
}
}

New-Item-ItemtypeDirectory-Path"$MAVEN_HOME_PARENT"-Force|Out-Null

#DownloadandInstallApacheMaven
Write-Verbose"Couldn'tfindMAVEN_HOME,downloadingandinstallingit..."
Write-Verbose"Downloadingfrom:$distributionUrl"
Write-Verbose"Downloadingto:$TMP_DOWNLOAD_DIR/$distributionUrlName"

$webclient=New-ObjectSystem.Net.WebClient
if($env:MVNW_USERNAME-and$env:MVNW_PASSWORD){
$webclient.Credentials=New-ObjectSystem.Net.NetworkCredential($env:MVNW_USERNAME,$env:MVNW_PASSWORD)
}
[Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12
$webclient.DownloadFile($distributionUrl,"$TMP_DOWNLOAD_DIR/$distributionUrlName")|Out-Null

#Ifspecified,validatetheSHA-256sumoftheMavendistributionzipfile
$distributionSha256Sum=(Get-Content-Raw"$scriptDir/.mvn/wrapper/maven-wrapper.properties"|ConvertFrom-StringData).distributionSha256Sum
if($distributionSha256Sum){
if($USE_MVND){
Write-Error"Checksumvalidationisnotsupportedformaven-mvnd.`nPleasedisablevalidationbyremoving'distributionSha256Sum'fromyourmaven-wrapper.properties."
}
Import-Module$PSHOME\Modules\Microsoft.PowerShell.Utility-FunctionGet-FileHash
if((Get-FileHash"$TMP_DOWNLOAD_DIR/$distributionUrlName"-AlgorithmSHA256).Hash.ToLower()-ne$distributionSha256Sum){
Write-Error"Error:FailedtovalidateMavendistributionSHA-256,yourMavendistributionmightbecompromised.IfyouupdatedyourMavenversion,youneedtoupdatethespecifieddistributionSha256Sumproperty."
}
}

#unzipandmove
Expand-Archive"$TMP_DOWNLOAD_DIR/$distributionUrlName"-DestinationPath"$TMP_DOWNLOAD_DIR"|Out-Null

#Findtheactualextracteddirectoryname(handlessnapshotswherefilename!=directoryname)
$actualDistributionDir=""

#Firsttrytheexpecteddirectoryname(forregulardistributions)
$expectedPath=Join-Path"$TMP_DOWNLOAD_DIR""$distributionUrlNameMain"
$expectedMvnPath=Join-Path"$expectedPath""bin/$MVN_CMD"
if((Test-Path-Path$expectedPath-PathTypeContainer)-and(Test-Path-Path$expectedMvnPath-PathTypeLeaf)){
$actualDistributionDir=$distributionUrlNameMain
}

#Ifnotfound,searchforanydirectorywiththeMavenexecutable(forsnapshots)
if(!$actualDistributionDir){
Get-ChildItem-Path"$TMP_DOWNLOAD_DIR"-Directory|ForEach-Object{
$testPath=Join-Path$_.FullName"bin/$MVN_CMD"
if(Test-Path-Path$testPath-PathTypeLeaf){
$actualDistributionDir=$_.Name
}
}
}

if(!$actualDistributionDir){
Write-Error"CouldnotfindMavendistributiondirectoryinextractedarchive"
}

Write-Verbose"FoundextractedMavendistributiondirectory:$actualDistributionDir"
Rename-Item-Path"$TMP_DOWNLOAD_DIR/$actualDistributionDir"-NewName$MAVEN_HOME_NAME|Out-Null
try{
Move-Item-Path"$TMP_DOWNLOAD_DIR/$MAVEN_HOME_NAME"-Destination$MAVEN_HOME_PARENT|Out-Null
}catch{
if(!(Test-Path-Path"$MAVEN_HOME"-PathTypeContainer)){
Write-Error"failtomoveMAVEN_HOME"
}
}finally{
try{Remove-Item$TMP_DOWNLOAD_DIR-Recurse-Force|Out-Null}
catch{Write-Warning"Cannotremove$TMP_DOWNLOAD_DIR"}
}

Write-Output"MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
