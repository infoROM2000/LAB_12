# LAB_12
## Structura
* Au fost create clasele: App, care contine metoda main si creaza un nou obiect de tip MainFrame(fereastra in sine) si il seteaza vizibil, pentru a putea fi vazut de catre utilizator.
* MainFrame, ce este format din 2 componente, designPanel si controlPanel, aranjate in centru si respectiv in nord conform cerinte, folosindu-se de BorderLayout si apoi de pack()
* DesignPanel, care este un JPanel setat implicit 800x600  si care are rolul de a gazdui elementele grafice ce urmeaza sa fie adaugata.
* ControlPanel, care este un JPanel format din 6 elemente, grupate pe 2 randuri si 3 coloane. 2 JLabel-uri, cu scopul de indica rolul campurilor, 2 JTextField, ce permit utilizatorului sa scrie o linie de text in aplicatie, si 2 JButton, unul de adaugare a unui element grafic conform cu ce este scris in JTextField si unul de reset, pentru a sterge ce este prezent in campuri. 
## Functionalitate
* In momentul de fata, majoritatea lucrurilor se intampla in ControlPanel, in cadrul functie asociate butonului de adaugare.
* La apasarea acestuia, este apelata functia add asociata prin ActionListener-ul butonului cu parametrul this::add.
* In functie se intampla 3 lucruri principale: se incearca incarcarea dinamica a clasei specificate in JTextArea-ul numeClasa
* Aceasta e necesara in general pentru ca este posibil ca utilizatorul sa doreasca sa adauge elemente care fac parte din clase nu au fost importate la momentul pornirii aplicatiei, si astfel trebuiesc adaugate la runtime in JVM 
* In contextul prezent, este inutil acest lucru, pentru ca noi deja folosim javax.swing.* si s-a cerut sa se adauge doar elemente din swing, deci nu este esential.
* Incarcare se produce prin metoda .forName, care primeste ca parametru numele, si este necesar un try catch pentru tratarea exceptiei in care numele clasei a fost scris gresit/nu exista.
* Dupa aceea, am folosit regex pentru a luat ultimul cuvant din numele clasei pentru a stii ce componenta sa initializez. Componentele din swing sunt de forma javax.swing.nume, astfel incat e de ajuns sa iau al treiea cuvant dupa separarea String-ului in cuvinte, folosind punctul ca delimitator (reg \\. datorita faptului ca si \ si . sunt caractere speciale).
* In functie de nume, se initializeaza elementul grafic corespunzator, apelandu-se Nume componenta= clasa.newInstance(), prin care se creaza un nou obiect "gol" cu parametrii impliciti. Nu se poate construi "normal" pentru ca clasa nu exista la compilare si ar declansa o eroare.
* Obiectului nou creat i se seteaza o dimensiune si pozitie oarecare (astfel incat sa nu se suprapuna cu alte eventuale elemente din Design) si se adauga la frame.designPanel. Dupa adaugare, este necesara o revalidare si un repaint asupra frame.designPanel.
* In momentul de fata sunt suportate JButton si JLabel. 
