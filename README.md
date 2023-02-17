# Esempi Semafori

Applicazione [sharedobject.SemaphoreDemo](./src/sharedobject/SemaphoreDemo.java)


## Esempio alternanza

[alternanza.CasoBase](./src/alternanza/CasoBase.java) esempi output:

```
x
y
a
b
c
z
```

```
x
y
z
a
b
c
```

```
a
b
x
y
z
c
```

Sono possibili diverse sequenze di esecuzione.

Se volessimo ottenere la seguente esecuzione invece ottenere la seguente esecuzione?

```
a
x
b
y
c
z

```
Utilizzando [alternanza.CasoOrdinamento](./src/alternanza/CasoOrdinamento.java)



