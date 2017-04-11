Create Table Garages(
    GarageNaam  		varchar(60) ,
    Xpos				float,
    Ypos				float,
    DeelGemeente		varchar(60),
	primary key(GarageNaam)
    );
	
Create Table Autodiefstal(
	DeelGemeente		varchar(60),
	PercentageDiefstal	float,
	Jaar				float,
    primary key(DeelGemeente, Jaar)
	);
