# Simulation of Congestion Control Algorithms using NS2
set ns [new Simulator]
set tr [open out.tr w]
$ns trace-all $tr
set nam [open out.nam w]
$ns namtrace-all $nam
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail
set tcp1 [new Agent/TCP]
set sink1 [new Agent/TCPSink]
$ns attach-agent $n0 $tcp1
$ns attach-agent $n2 $sink1
$ns connect $tcp1 $sink1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set tcp2 [new Agent/TCP/Reno]
set sink2 [new Agent/TCPSink]
$ns attach-agent $n0 $tcp2
$ns attach-agent $n2 $sink2
$ns connect $tcp2 $sink2
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2
$ns at 0.5 "$ftp1 start"
$ns at 1.0 "$ftp2 start"
$ns at 3.0 "$ftp1 stop"
$ns at 3.0 "$ftp2 stop"
proc finish {} {
    global ns tr nam
    $ns flush-trace
    close $tr
    close $nam
    exec nam out.nam &
    exit 0
}
$ns at 4.0 "finish"
$ns run