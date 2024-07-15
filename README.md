# MiniCAD

## Descrizione

MiniCAD è un'applicazione grafica che permette di creare, modificare e gestire oggetti come cerchi e rettangoli attraverso comandi testuali.

## Funzionalità

- **Creazione di oggetti**: Aggiunta di cerchi e rettangoli.
- **Modifica di oggetti**: Ridimensionamento e spostamento.
- **Undo**: Annullamento delle operazioni.
- **Dimensioni**: calcolo aree e perimetri.

## Requisiti

- **Java**: Versione 21.
- **JUnit**: Per eseguire i test unitari.

## Alcuni comandi di prova
new circle (80) (200.9,300.78)
new rectangle 90 100 (500.9,359.394)  **//LEGGERE "NOTA BENE"** 
grp 1 2
mvoff 3 (600,500)
scale 1 0.5
perimeter all

## NOTA BENE
Il comando di creazione di un rettangolo è stato specificato nella grammatica come segue:

**_"create"::= new "typeconstr" "pos"_**   dove

**_"typeconstr"::= circle ("posfloat") | rectangle "pos" | img ("path")_**

Essendo la posizione del rettangolo gia specificata come argomento della regola _create_, si è inteso 
tale fenomeno come un refuso, e si è sostituita l'argomento _pos_ in _typeconstr_ con due _posfloat_
separati da spazio per indicare base ed altezza altrimenti non specificati.
