#!/bin/bash

# trova tutti gli enigmi 

echo "# trova tutti gli enigmi" 
echo $(curl -s edipogram:80/enigmi/enigmi)
echo 

