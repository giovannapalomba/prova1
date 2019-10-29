Progetto di partenza per l'applicazione sviluppata per la tesi.
Specifiche dei professori:

Project 1 - Procedure Steps Management 

Obiettivo 
L’obiettivo dell’applicazione è quello di realizzare uno strumento di supporto per seguire passo passo una procedura da realizzare costituta da una serie di Step Successivi obbligatori e/o opzionali. 

Requirement/Functionalities 
● L’applicazione all’avvio scarica da un Server remoto la lista delle procedure disponibili in formato JSON 
● Ogni descrittore di una procedura deve contenere un titolo ed una descrizione 
● L’utente sceglie da una lista quale procedura vuole iniziare 
● Per ogni procedura l’utente dovrà sempre essere informato sullo step nel quale si trova 
● Ad ogni step (compreso il primo) l’applicazione deve fare una richiesta al server per ricevere lo step successivo da visualizzare. Il server risponde con un descrittore JSON con il contenuto. 
● Ogni step può essere caratterizzato da: i) titolo; ii) un contenuto testuale e iii) degli allegati multimediali (immagini e video) da 0 a n a seconda dello step 
● Per ogni step se previsto dalla configurazione l’utente potrà tornare allo step precedente e/o andare avanti 
● Ogni volta che una procedura viene completata dovrà essere generato e salvato sul device un log storico contenente Timestamp di inizio, Timestamp di fine, Tempo impiegato per ogni step e tipologia della procedura. Lo storico dei Log dovrà essere visualizzabile sotto forma di lista nell’applicazione. 
