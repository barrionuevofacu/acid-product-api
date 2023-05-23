# acid-product-api

- Se creó una imagen Docker utilizando eclipse-temurin:17-jre-alpine 
- En instancia de AWS instalé Docker y desplegué la imagen creada anteriormente. Se puede consultar en el siguiente endpoint: http://ec2-13-38-119-83.eu-west-3.compute.amazonaws.com:8080/products/sorted?salesWeight=0.5&stockWeight=0.5
- Para la persistencia de datos con MongoDB se utilizó el cloud de MongoDB

Se que no es correcto tener credenciales directamente en el proyecto, pero fue solo para hacerlo mas simple en este test ya que la base de datos fue creada pura y exclusivamente con este fin.
