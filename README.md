# EDIPOGRAM

Progetto del corso di Architettura del software per l'anno accademico 2021-2022. 


## Descrizione di questo progetto 

Questo progetto contiene il il codice di *Edipogram*, 
un semplice social network per la condivisione di enigmi (ovvero, giochi enigmistici) di diversi tipi, come indovinelli ed anagrammi.  
Gli utenti del sistema possono pubblicare degli enigmi. 
Possono poi seguire gli enigmi di specifici tipi.  
Quando un utente accede alla pagina degli enigmi che segue, gli vengono mostrati gli enigmi dei tipi che segue. 

L'applicazione *Edipogram* è composta dai seguenti microservizi: 

* Il servizio *enigmi* gestisce gli enigmi. 
  Ogni enigma ha un autore, un tipo, una un tipo specifico, un titolo, un testo (che può essere composta da più righe) e una soluzione (che può essere composta da più parole). 
  Mentre autore, titolo, testo e soluzione hanno un significato ovvio, è utile discutere tipi e tipi specifici. 
  Un enigma potrebbe essere di tipo (generico) *Cambio* e di tipo specifico *Cambio di iniziale* oppure *Cambio di vocale*. 
  
  Un esempio di enigma: 
  * autore: Il Valletto
  * tipo: Cambio 
  * tipo specifico: Cambio di vocale (7)
  * titolo: Ponte pericolante
  * testo: Saldo non è... / e questo può esser grave!
  * soluzione: accOnto, accEnto
  
  Operazioni: 
  * `POST /enigmi` aggiunge un nuovo enigma (dati autore, tipo, titolo, testo e soluzione)
  * `GET /enigmi/{id}` trova un enigma, dato l'id 
  * `GET /enigmi/{id}/soluzione` trova la soluzione di un enigma, dato l'id 
  * `GET /enigmi` trova tutti gli enigmi (senza la soluzione)
  * `GET /cercaenigmi/autore/{autore}` trova tutti gli enigmi di un certo autore (senza soluzione)
  * `GET /cercaenigmi/autori/{elenco-di-autori}` trova tutti gli enigmi di un insieme di autori (senza soluzione) 
  * `GET /cercaenigmi/tipo/{tipo}` trova tutti gli enigmi di un certo tipo (senza soluzione)
  * `GET /cercaenigmi/tipi/{elenco-di-tipi}` trova tutti gli enigmi di un insieme di tipi (senza soluzione)
  
* Il servizio *connessioni* gestisce le connessioni degli utenti con i tipi di enigmi che essi seguono. 
  Le connessioni sono delle coppie *utente-tipo* . 
  Operazioni: 
  * `POST /connessioni` aggiunge una nuova connessione utente-tipo (dati utente e tipo)
  * `GET /connessioni` trova tutte le connessioni utente-tipo
  * `GET /connessioni/{utente}` trova tutte le connessioni utente-tipo di un certo utente

* Il servizio *enigmi-seguiti* consente a un utente di trovare gli enigmi di tutti i tipi che segue. 
  Operazioni: 
  * `GET /enigmiseguiti/{utente}` trova tutti gli enigmi seguiti da un certo utente, ovvero gli enigmi di tipi seguiti da quell'utente (gli enigmi sono senza soluzione)
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *enigmi* sul path `/enigmi` - ad esempio, `GET /enigmi/enigmi`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioni/{utente}`
  * espone il servizio *enigmi-seguiti* sul path `/enigmi-seguiti` - ad esempio, `GET /enigmi-seguiti/enigmiseguiti/{utente}`

## Organizzazione del repository

Il repository è organizzato in diverse cartelle:

* [environment](environment/): contente il codice per l'esecuzione dell'ambiente virtuale Vagrant utilizzzato per il rilascio
  dell'applicazione con Kubernetes
* [docker](docker/): contiene tutti gli script relativi al rilascio e all'esecuzione dell'applicazione attraverso Docker
* [kubernetes](kubernetes/): contenente tutti i file relativi al rilascio e all'esecuzione dell'applicazione attraverso Kubernetes
  utilizzando l'ambiente virtuale fornito della cartella [environment](environment/)
  Diviso in due sottocartelle:
  * [script](kubernetes/script): contenente tutti gli script per il rilascio su kubernetes e per l'esecuzione dell'applicazione
  * [resources](kubernetes/resources): contenente tutti i file relativi alle risorse Kubernetes utilizzate
* [kubernetes-for-mac-m1](kubernetes-for-mac-m1/): contenente tutti i file relativi al rilascio e all'esecuzione dell'applicazione se si sta 
utilizzando un MacBook con processore M1
Diviso in due sottocartelle:
  * [script](kubernetes-for-mac-m1/script): contenente tutti gli script per il rilascio su kubernetes e per l'esecuzione dell'applicazione
  * [resources](kubernetes-for-mac-m1/resources): contenente tutti i file relativi alle risorse Kubernetes utilizzate
* [api-gateway](api-gateway/): contenente il codice sorgente del servizio applicativo apigateway
* [enigmi](enigmi/): contenente il codice sorgente del servizio applicativo enigmi
* [connessioni](connessioni/): contenente il codice sorgente del servizio applicativo connessioni
* [enigmi-seguiti](enigmi-seguiti/): contenente il codice sorgente del servizio applicativo enigmiseguiti


## Software da installare sul proprio PC

* [VirtualBox](https://www.virtualbox.org/)
* [Vagrant](https://www.vagrantup.com/)
* [Git](https://git-scm.com/)
* [Docker](https://www.docker.com/)

## Rilascio con Docker

Per eseguire questo progetto con Docker:

* avviare Docker

* `build-java-project.sh` per buildare il progetto

* posizionarsi nella cartella `docker` ed eseguire i seguenti script:

  * `run-docker-compose.sh` per eseguire l'applicazione su Docker
  
  * `do-init-enigmi.sh` e `do-init-connessioni.sh`  per inizializzare le basi di dati con dei dati di esempio

Attenzione: se una porta non è disponibile si può terminare il processo che la occupa con `sudo fuser -k PORTA/tcp`

## Rilascio con Kubernetes

### Prerequisiti:

* installa l'ultima versione di [Vagrant](https://www.vagrantup.com/downloads)

* installa l'ultima versione di [VirtualBox](https://www.virtualbox.org/wiki/Downloads)

### Deploy:

* posizionarsi nella cartella `/environment` ed eseguire il seguente comando per avviare o creare l'ambiente di esecuzione:
  ```shell
  vagrant up
  ```

* per collegarsi con SSH alla VM dell'ambiente esguire il seguente comando ed attendere la fine del processo:
  ```shell
  vagrant ssh
  ```
  
* all'interno della VM eseguire il seguente comando per aggiungere l'utente al gruppo 'docker':
  ```shell
  sudo usermod -aG docker $USER && newgrp docker
  ```

* all'interno della VM eseguire il seguente comando per modificare la memoria assegnata a minikube:
  ```shell
  minikube config set memory 4g
  ```

* all'interno della VM posizionarsi nella cartella `asw-edipogram/kubernetes/script` eseguire i seguenti script:

  * `start-minikube.sh` per inizializzare il cluster (attendere la fine del processo)

  * `enable-minikube-ingress-controller.sh` per attivare l'ingress controller utilizzato da minikube

  * `init-k8s-edipogram-namespace.sh` per aggiungere il namespace **edipogram**

  * `init-k8s-resources.sh` per inizializzare tutte le risorse del cluster (l'operazione di creazione dei contener potrebbe richiedere alcuni minuti)
  
  * eseguire il seguente comando:
    ```shell
    minikube ip
    ```
    e aggiungere la seguente riga alla fine del file `/etc/hosts`:
    ```shell
    192.168.49.2 edipogram
    ```
    sostituendo l'ip con quello in output dal comando precedente. Per farlo utilizzare il seguente comando per modificare il file `/etc/hosts`:
    ```shell
    sudo nano /etc/hosts
    ```
    premere ctrl + x, y, enter per salvare il file

  * `do-init-enigmi.sh` e `do-init-connessioni.sh`  per inizializzare le basi di dati con dei dati di esempio

  * opzionale: `run-minikube-dashboard.sh` per accedere alla dashboard interattiva di Kubernetes

  * opzionale: `run-k8s-get-pods.sh` per visualizzare la lista dei pod disponibili

  * `delete-minikube.sh` per eliminare il cluster minikube

* per eliminare l'ambiente virtuale Vagrant eseguire il seguente comando
```shell
  vagrant destroy
```

## Rilascio con Kubernetes su macOS con processore M1:

Per eseguire questo progetto con Kubernetes:

* installare kubectl e minikube

* avviare Docker

* posizionarsi nella cartella `kubernetes-for-mac-m1/script` ed eseguire i seguenti script:

  * `start-minikube.sh` per inizializzare il cluster (attendere la fine del processo)

  * `enable-minikube-ingress-controller.sh` per attivare l'ingress controller utilizzato da minikube

  * `init-k8s-edipogram-namespace.sh` per aggiungere il namespace "**edipogram**"

  * `init-k8s-resources.sh` per inizializzare tutte le risorse del cluster (l'operazione di creazione dei contener potrebbe richiedere alcuni minuti)

  * opzionale: `run-k8s-get-pods` per visualizzare la lista dei pod disponibili

  * aggiungere le seguente riga alla fine del file `/etc/hosts`
    ```shell
    127.0.0.1 edipogram
    ```
    per farlo utilizzare un qualsiasi editor di testo con i permessi di root

  * `run-minikube-tunnel.sh` per creare una connessione con l'applicazione

  * `do-init-enigmi.sh` e `do-init-connessioni.sh`  per inizializzare le basi di dati con dei dati di esempio
  
  * `delete-minikube.sh` per eliminare il cluster minikube

Attenzione: nel caso in cui alcuni pods non riescano a rimanere attivi, assicurarsi che la memoria a disposizione di minikube sia sufficiente. 
Per modificare la memoria allocabile da minikube utilizzare il comando `minikube config set memory 5g`
  
## Esecuzione

È possibile testare il corretto funzionamento dell'applicazione attraverso alcuni script di esempio:

* per Kubernetes eseguire gli script contenuti nella cartella `kubernetes/script`

### Script:

* lo script `run-curl-client.sh` esegue un insieme di interrogazioni di esempio 

* lo script `do-get-enigmi.sh` trova tutti gli enigmi 

* lo script `do-get-enigma.sh` trova un enigma 

* lo script `do-get-enigmi-di-autore.sh` trova tutti gli enigmi di un certo autore 

* lo script `do-get-enigmi-di-autori.sh` trova tutti gli enigmi di un insieme di autori  

* lo script `do-get-enigmi-di-tipo.sh` trova tutti gli enigmi di un certo tipo  

* lo script `do-get-enigmi-di-tipi.sh` trova tutti gli enigmi di un insieme di tipi  

* lo script `do-get-connessioni.sh` trova tutte le connessioni 

* lo script `do-get-enigmi-seguiti.sh` trova tutti gli enigmi seguiti da un certo utente 

Ed inoltre: 

* lo script `do-post-altri-enigmi.sh` aggiunge nuovi enigmi 

* lo script `do-post-altre-connessioni.sh` aggiunge nuove connessioni
