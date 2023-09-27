#!/bin/bash

# trova tutte le connessioni 

echo "# tutte le connessioni" 
echo $(curl -s edipogram:80/connessioni/connessioni)
echo 

