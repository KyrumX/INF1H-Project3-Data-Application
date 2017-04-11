Create Table Garages(
    GarageNaam  		varchar(60) ,
    Xpos				float,
    Ypos				float,
    DeelGemeente		varchar(60),
	primary key(GarageNaam)
    );
	
Create Table Aurodiefstal(
	DeelGemeente		varchar(60),
	PercentageDiefstal	int,
	Jaar				int,
    primary key(DeelGemeente, Jaar)
	);
