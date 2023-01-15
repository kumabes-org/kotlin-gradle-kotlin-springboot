# kotlin-gradle-kotlin-springboot


## Exemplo de chamadas

### Create
```
curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos -d '{"nome":"Fulano","telefone":"(11) 99999-9999","data_nascimento":"1995-08-15"}'
curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos -d '{"nome":"Sicrano","telefone":"(21) 99999-9999","data_nascimento":"1994-01-29"}'
curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos -d '{"nome":"Beltrano","telefone":"(37) 99999-9999","data_nascimento":"1988-02-18"}'
```

### Retrieve
```
curl -v -X GET -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos/1
```

### Update
```
curl -v -X PUT -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos/1 -d '{"codigo": 1,"nome":"Isabela","telefone":"(11) 99999-9999","data_nascimento":"1995-08-15"}'
```

### Delete
```
curl -v -X DELETE -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos/1
```

### List
```
curl -v -X GET -H 'Content-Type: application/json' http://localhost:8080/api/v1/contatos
```