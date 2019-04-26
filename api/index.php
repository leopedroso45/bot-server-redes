<?php
define('eeba84a7386437b2d4283306868a9788040237a392391f3e53236eb2d04d77f339252f5fa282dce609798657215e7a61a320549dc7e403c7116cdc5636a30514', true);

$request = $_SERVER['REDIRECT_URL'] ?? "/";
$request = (substr( $request, 0, 4 ) === "/api") ? preg_replace( '/\/api/', "", $request) : '/'; // $r is set to 'Yes'
switch ($request) {
    case '/':
        require_once __DIR__ . '\views\index.php';
        break;
    case '':
        require_once __DIR__ . '\views\index.php';
        break;
    case '/about':
        require_once __DIR__ . '\views\about.php';
        break;
    default:
        require_once __DIR__ . '\views\404.php';
        break;
}
echo "..".$request."..";
