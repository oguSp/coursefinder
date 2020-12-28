# Coursefinder

Software eseguibile da riga di comando. Il file <i>csv</i> contenente i corsi va passato come argomento (il path deve essere assoluto).
È stata utilizzata la libreria <i>apache commons cli</i> per la gestione ed il parsing degli argomenti e delle opzioni. 
Per quanto riguarda l'esportazione del risultato su file <i>xls</i> ho fatto uso
della libreria <i>apache poi</i>. Ho utilizzato il design pattern creazionale <b>factory method</b> per la selezione dell'output in base alla richiesta dell'utente (su console o su file excel).

Sono stati effettuati degli unit test con <i>Junit</i> per tutte le possibili combinazioni dei filtri.
Come sistema di building e risoluzione dipendenze ho utilizzato <i>Maven</i>.

# Uso

coursefinder <i>csvpath</i> <i>[options]</i>


<b>OPTIONS</b>

-i <i>instructors</i>   Il nome/i dei docenti: nel caso di più docenti vanno scritti nella forma<i>'docente1'              'docente2'</i>.

-l <i>launchyear</i>    Anno di lancio del corso nella forma YYYY.

-y <i>accademicyear</i> Anno accademico del corso (1,2,3,4).

-x                      Esporta i dati in formato xls nella directory corrente. Se non specificato l'output è su                      console.
