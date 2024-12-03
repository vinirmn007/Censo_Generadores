from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

familiasRouter = Blueprint('familiasRouter', __name__)

URL = 'http://localhost:8000/myapp/'

@familiasRouter.route('/')
def home():
    return render_template('home.html')

#MUESTRA TODAS LAS FAMILIAS CON GENERADOR
@familiasRouter.route('/censo/')
def view_censo():
    r = requests.get(URL + 'censoAPI')
    data = r.json().get('data')
    return render_template('parts/censo.html', familiasCG=data)

#ORDENA LAS FAMILIAS CON GENERADOR
@familiasRouter.route('/censo/order/<metodo>/<atributo>/<tipo>')
def order_families_CG(metodo, atributo, tipo):  
    url = URL + "censoAPI/order/"+metodo+"/"+atributo+"/"+tipo
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Familia ordenada', category='info')
        return render_template('parts/censo.html', familiasCG = data["data"])
    else:        
        flash('No se ha podido ordenar', category='error')
        return redirect('/censo/')
    
#BUSCA LAS FAMILIAS CON GENERADOR
@familiasRouter.route('/censo/search/<metodo>/<atributo>/<tipo>/<valor>')
def search_families_CG(metodo, atributo, tipo, valor):  
    url = URL + "censoAPI/search/"+metodo+"/"+atributo+"/"+tipo+"/"+valor
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Familia encontrada', category='info')
        return render_template('parts/censo.html', familiasCG = data["data"])
    else:        
        flash('No se ha podido encontrar', category='error')
        return redirect('/censo')

#MUESTRA TODAS LAS FAMILIAS
@familiasRouter.route('/censo/familias')
def linkedList_view_familias():
    r = requests.get(URL + 'familias')
    data = r.json().get('data')
    return render_template('parts/familias.html', familias=data)

#ORDENA LAS FAMILIAS
@familiasRouter.route('/censo/familias/order/<metodo>/<atributo>/<tipo>')
def order_families(metodo, atributo, tipo):  
    url = URL + "familias/order/"+metodo+"/"+atributo+"/"+tipo
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Familia ordenada', category='info')
        return render_template('parts/familias.html', familias = data["data"])
    else:        
        flash('No se ha podido ordenar', category='error')
        return redirect('/censo/familias')
    
#BUSCA LAS FAMILIAS
@familiasRouter.route('/censo/familias/search/<metodo>/<atributo>/<tipo>/<valor>')
def search_families(metodo, atributo, tipo, valor):  
    url = URL + "familias/search/"+metodo+"/"+atributo+"/"+tipo+"/"+valor
    
    r = requests.get(url) 
    
    data = r.json()
    if(r.status_code == 200):
        flash('Familia encontrada', category='info')
        return render_template('parts/familias.html', familias = data["data"])
    else:        
        flash('No se ha podido encontrar', category='error')
        return redirect('/censo/familias')

#FORMULARIO DE REGISTRO PARA LAS FAMILIAS
@familiasRouter.route('/censo/familias/registro')
def registro_familias():
    return render_template('forms/familia/registroFamilias.html')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO HACIA EL BACKEND
@familiasRouter.route('/censo/familias/registro/save', methods=["POST"])
def save_familias():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL + 'familias/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
    else:
        flash('No se ha podido guardar', category='error')
    return redirect('/censo/familias')
    
#RUTA PARA EDITAR UNA FAMILIA ESPECIFICA
@familiasRouter.route('/censo/familias/edit/<id>')
def update_familias_view(id):
    r = requests.get(URL + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/familia/modificarFamilia.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/censo/familias')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION HACIA EL BACKEND
@familiasRouter.route('/censo/familias/update', methods=["POST"])
def update_familia():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"], "apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL + 'familias/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
    else:
        flash('No se ha podido actualizar', category='error')
    return redirect('/censo/familias')

#RUTA PARA ELIMINAR UNA FAMILIA ESPECIFICA
@familiasRouter.route('/censo/familias/delete', methods=["POST"])
def delete_familias():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"]}
    r = requests.post(URL + 'familias/delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Familia eliminada exitosamente', category='info')
    else:
        data = r.json()
        flash(data["data"], category='error')
    return redirect('/censo/familias')

#RUTA PARA REGISTRAR UN GENERADOR POR FAMILIA ESPECIFICA
@familiasRouter.route('/censo/familias/generador/register/<id>')
def register_generador_view(id):
    r = requests.get(URL + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/registroGenerador.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/censo/familias')