Create Table Garages(
    GarageNaam  		varchar(60) ,
    Xpos				int,
    Ypos				int,
    DeelGemeente		varchar(60),
	primary key(GarageNaam)
    );
	
Create Table Aurodiefstal(
	DeelGemeente		varchar(60),
	PercentageDiefstal	int,
	Jaar				int,
    primary key(DeelGemeente, Jaar)
	);