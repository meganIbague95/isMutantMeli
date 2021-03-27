PROYECTO API ISMUTANT PARA ASPIRACIÓN CARGO DESARROLLADOR BACK 
Aspirante: Megan Nicol Ibagué

# isMutant
Entrega de api que determina si la secuencia de ADN es mutante o no

Para la ejecución del servicio /mutant inicialmente debe tener una secuencia de ADN, tenga en cuenta que cada cadena solo debe llevar las letras A,C,G,T.
Luego debe crear una nueva coleccion en postman, con un request de metodo post con la siguiente url:
https://ln0tppjw8b.execute-api.us-east-2.amazonaws.com/Produccion/mutant
y en el body agregar el objeto en formato json.

Ejemplo:
{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Dependiendo de como se haya formado la cadena, el servicio devolverá estado 200-ok si se econtró ADN mutante, en el caso contrario aparecerá estado 403- forbbiden.

Para el servicio  /stats lo unico que se debe realizar es crear un request en postman de tipo GET, con la siguiente url: le pasaremos el parametro lastEvaluatedKey ya que use 
un paginador lo cual traera un maximo de 15 registros, inicialmente ese valor puede ser vacio, para que traiga los primeros 15 registros, en la respuesta del serevicio me devuelve 
un valor lastEvaluatedKey que seria el id del ultimo registro que aparece, para que si se quieren visualizar los otros registros se pase como parametros esa llave que nos da el response.
https://ln0tppjw8b.execute-api.us-east-2.amazonaws.com/Produccion/stats?lastEvaluatedKey=
Sin ningun parametro, o cuerpo.

Este servicio le devolverá las estadisticas de las secuencias de los ADN, analizados.

El cubrimiento de las pruebas unitarias se podran encontrar en el reporte, en la carpeta del proyecto en la ruta : Prueba_Meli\lambda-validate\build\reports\jacoco\test\html

