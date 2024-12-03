from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

generadoresRouter = Blueprint('generadoresRouter', __name__)

URL = 'http://localhost:8000/myapp/'

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO DE GENERADOR HACIA EL BACKEND
@generadoresRouter.route('/censo/familias/generador/save', methods=["POST"])
def save_generador():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"familiaId": form["familiaId"],
                "marca": form["marca"],
                "modelo": form["modelo"],
                "consumo": float(form["consumo"]),
                "energia": float(form["energia"]),
                "precio": float(form["precio"]),
                "uso": form["uso"]}
    r = requests.post(URL + 'generadores/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
    else:
        flash(data["data"], category='error')
    return redirect('/censo/familias')

#MUESTRA TODOS LOS GENERADORES
@generadoresRouter.route('/censo/generadores')
def linkedList_generadores():
    r = requests.get(URL + 'generadores')
    data = r.json().get('data')
    return render_template('parts/generadores.html', generadores=data)

#ORDENA LOS GENERADORES
@generadoresRouter.route('/censo/generadores/order/<metodo>/<atributo>/<tipo>')
def order_generators(metodo, atributo, tipo):  
    url = URL + "generadores/order/"+metodo+"/"+atributo+"/"+tipo
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Generador encontrado', category='info')
        return render_template('parts/generadores.html', generadores = data["data"])
    else:        
        flash('No se ha podido ordenar', category='error')
        return redirect('/censo/generadores')
    
#BUSCA LOS GENERADORES
@generadoresRouter.route('/censo/generadores/search/<metodo>/<atributo>/<tipo>/<valor>')
def search_generators(metodo, atributo, tipo, valor):  
    url = URL + "generadores/search/"+metodo+"/"+atributo+"/"+tipo+"/"+valor
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Generador encontrado', category='info')
        return render_template('parts/generadores.html', generadores = data["data"])
    else:        
        flash('No se ha podido encontrar', category='error')
        return redirect('/censo/generadores')

#RUTA PARA EDITAR UN GENERADOR ESPECIFICO
@generadoresRouter.route('/censo/generadores/edit/<id>')
def update_generadores_view(id):
    r = requests.get(URL + 'generadores/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/modificarGenerador.html', generador=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/generadores')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION DEL GENERADOR HACIA EL BACKEND
@generadoresRouter.route('/censo/generadores/update', methods=["POST"])
def update_generador():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"],
                "marca": form["marca"],
                "modelo": form["modelo"],
                "consumo": float(form["consumo"]),
                "energia": float(form["energia"]),
                "precio": float(form["precio"]),
                "uso": form["uso"]}
    r = requests.post(URL + 'generadores/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
    else:
        flash('No se ha podido actualizar ' + data["data"], category='error')
    return redirect('/censo/generadores')
    
#RUTA PARA ELIMINAR UN GENERADOR ESPECIFICO
@generadoresRouter.route('/censo/generadores/delete', methods=["POST"])
def delete_generadores():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"]}
    r = requests.post(URL + 'generadores/delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Generador eliminado exitosamente', category='info')
    else:
        data = r.json()
        flash(data["data"], category='error')
    return redirect('/censo/generadores')

#RUTA PARA EL HISTORIAL
@generadoresRouter.route('/censo/historial')
def view_historial():
    r = requests.get(URL + 'censoAPI/historial')
    data = r.json().get('data')
    return render_template('parts/historial.html', historial=data)
