<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  </head>
  <body class="p-5 mb-5 bg-info text-dark" >
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <h1 class="h1" style = "text-align: center;">MENU PRINCIPAL</h1>
     <form method="post" action="consulta1">
            <div class="row">
                <div class="col">
                    <div class="d-grid gap-2">
                     <button class="btn btn-primary" type="submit" style = "margin-left: 25%; height: 100%; width: 50%;"  >Consulta 1</button>
                </div>
                </div>
            </div>
            </form>
            <br>
        <form method="post" action="consulta2">
                    <div class="row">
                        <div class="col">
                            <div class="d-grid gap-2">
                             <button class="btn btn-primary" type="submit" style = "margin-left: 25%; height: 100%; width: 50%;"  >Consulta 2</button>
                        </div>
                    </div>
                    </div>
                    </form>
                    <br>
        <form method="post" action="consulta3">
                    <div class="row">
                        <div class="col">
                            <div class="d-grid gap-2">
                             <button class="btn btn-primary" type="submit" style = "margin-left: 25%; height: 100%; width: 50%;"  >Consulta 3</button>
                        </div>
                    </div>
                    </div>
                    </form>
        <br>
                <form method="post" action="consulta4">
                                    <div class="row">
                                        <div class="col">
                                            <div class="d-grid gap-2">
                                             <button class="btn btn-primary" type="submit" style = "margin-left: 25%; height: 100%; width: 50%;"  >Consulta 4</button>
                                        </div>
                                    </div>
                                    </div>
                                    </form>
        <br>
        <br>
        <div class="row">
          <div class="col">
            <div class="d-grid gap-2">
              <a href='Cliente.html'><button class="btn btn-primary" style = "margin-left: 25%; height: 100%; width: 50%;"  type = "button"> Nuevo Cliente</button></a>
            </div>
          </div>
          </div>
          <br>
         <div class="row">
            <div class="col">
                <div class="d-grid gap-2">
                    <a href='Moneda.html'><button class="btn btn-primary" type="button" style = "margin-left: 25%; height: 100%; width: 50%;"   onclick="validarDocumentoId()">Nueva Moneda</button></a>
                </div>
            </div>
        </div>
        <br>
        <form method="post" action="formPro">
        <div class="row">
            <div class="col">
                <div class="d-grid gap-2">
                 <button class="btn btn-primary" type="submit" style = "margin-left: 25%; height: 100%; width: 50%;"  >Nuevo Producto</button>
            </div>
        </div>
        </form>
       </div>
      </div>
      </div>




    </div>
  </body>
  <script type="text/javascript" src="form.js" async></script>
</html>