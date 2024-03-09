insert into sportlehrer (vorname, nachname, kuerzel) values ("Matthias", "Schürch", "Sc");
insert into sporttag (datum, bezeichnung) values ("2024-08-17", "Sporttag 2024");
insert into sportklasse (klassenname, sportlehrer_id, sporttag_id) values ("2abH", 1, 1);
insert into riege (nummer, sportklassen_id) values ("1", 1);
insert into student (vorname, nachname, geschlecht, geburtsdatum, klasse, riege_id) values ("Patrick", "Meier", "m", "1989-06-25", "3a", 2);