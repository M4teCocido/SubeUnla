select persona.idpersona as idpersona, persona.nombre as nombre, persona.apellido as apellido, 
	   descuentoBoletoEstudiantil.idDescuento as idDescue1_0_1_, descuentoSube.nombre as nombre0_1_, descuentoBoletoEstudiantil.descuento as descuento3_1_, descuentoBoletoEstudiantil.viajesRestantes as viajesRe3_3_1_, descuentoBoletoEstudiantil.tipoBoleto as tipoBoleto3_1_, descuentoBoletoEstudiantil.idpersona as idpersona3_1_, 
       descuentotarifasocial.idDescuento as idDescue1_0_2_, descuentotarifasocial.descuento as descuento2_2_, descuentotarifasocial.idpersona as idpersona2_2_ 
FROM persona persona 
LEFT OUTER JOIN descuentoBoletoEstudiantil on persona.idpersona=descuentoBoletoEstudiantil.idPersona 
	LEFT OUTER JOIN descuentosube descuentoSube on descuentoBoletoEstudiantil.idDescuento=descuentoSube.idDescuento 
LEFT OUTER JOIN descuentotarifasocial on persona.idpersona=descuentotarifasocial.idPersona
	LEFT OUTER JOIN descuentosube descuentoSube2 on descuentotarifasocial.idDescuento=descuentoSube2.idDescuento 
WHERE persona.idpersona=16