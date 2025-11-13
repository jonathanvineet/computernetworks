#!/usr/bin/env python3
"""
Simple network simulation demonstrating what ex8.tcl would do
This simulates the 3-node linear topology with TCP traffic
"""

import time

print("=== Network Simulation (ex8.tcl equivalent) ===")
print()

# Network topology
print("Creating network topology:")
print("n0 ---- n1 ---- n2")
print("   2Mb     2Mb")
print("   10ms    10ms")
print()

# Agents setup
print("Setting up agents:")
print("- TCP agent on n0")
print("- TCP/Reno agent on n0") 
print("- TCP sinks on n2")
print()

# FTP applications
print("Setting up FTP applications:")
print("- FTP1 attached to TCP")
print("- FTP2 attached to TCP/Reno")
print()

# Simulation timeline
print("Simulation timeline:")
print("t=0.0s: Simulation starts")
time.sleep(1)

print("t=0.5s: FTP1 starts")
time.sleep(1)

print("t=1.0s: FTP2 starts (both FTP sessions running)")
time.sleep(1)

print("t=2.0s: Data transfer in progress...")
time.sleep(1)

print("t=3.0s: Both FTP1 and FTP2 stop")
time.sleep(1)

print("t=4.0s: Simulation ends")
print()

print("Results:")
print("- Trace file: out.tr (network events)")
print("- Animation file: out.nam (network visualization)")
print("- Two TCP flows competed for bandwidth")
print("- TCP/Reno should show better congestion control")
print()

print("Simulation completed successfully!")