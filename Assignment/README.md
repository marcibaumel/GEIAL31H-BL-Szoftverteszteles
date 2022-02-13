# TesztelesBeadando2021
A beadandó követelményei:
Készítsen egy multi-modulos maven alkalmazást tetszőleges témában.
Modell osztály tartalmazza a következő típusú adattagokat:
- String
- int
- boolean
- dátum
- Enum
- double vagy float
Adattagokra legyen megkötés:
- Tartomány
- Reguláris kifejezés
- Minimális hossz
A megkötéseknek megfelelő kivételeket dobjon.

DAO osztály implementálja a CRUD műveleteket (mongodb, MySQL, fájl,  stb).
A DAO dobjon kivételeket.

A Service osztálynak legyen egy DAO interface típusú adattagja, metódusai:
- CRUD műveleteknek megfelelő metódusok
- 5 olyan metódus, amely nem csak egy DAO metódus meghívásából áll.


Készítsen egységteszteket, és integrációs teszteket. Legalább egy metódus tesztelésénél használjon parametrizált tesztet.
Az utasítások 80%-át le kell fedni teszttel. Minden osztályt tesztelni kell (kivétel Enum).
Az elágazások 100%-át le kell fedni. 


Az enum fájlokat kérem egy külön package-be tenni, amely enums-ra végződik.
Pl. hu.uni.miskolc.teszteles.beadando.model.enums
