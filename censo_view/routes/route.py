from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

URL = 'http://localhost:8000/myapp/'

@router.route('/')
def home():
    return render_template('home.html')

#MUESTRA TODAS LAS FAMILIAS CON GENERADOR
@router.route('/censo/')
def linkedList_view_censo():
    r = requests.get(URL + 'censoAPI')
    data = r.json().get('data')
    return render_template('parts/censo.html', familiasCG=data)

#MUESTRA TODAS LAS FAMILIAS
@router.route('/censo/familias')
def linkedList_view_familias():
    r = requests.get(URL + 'familias')
    data = r.json().get('data')
    return render_template('parts/familias.html', familias=data)

#FORMULARIO DE REGISTRO PARA LAS FAMILIAS
@router.route('/censo/familias/registro')
def registro_familias():
    return render_template('forms/familia/registroFamilias.html')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO HACIA EL BACKEND
@router.route('/censo/familias/registro/save', methods=["POST"])
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
@router.route('/censo/familias/edit/<id>')
def update_familias_view(id):
    r = requests.get(URL + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/familia/modificarFamilia.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/censo/familias')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION HACIA EL BACKEND
@router.route('/censo/familias/update', methods=["POST"])
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
@router.route('/censo/familias/delete', methods=["POST"])
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
@router.route('/censo/familias/generador/register/<id>')
def register_generador_view(id):
    r = requests.get(URL + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/registroGenerador.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/censo/familias')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO DE GENERADOR HACIA EL BACKEND
@router.route('/censo/familias/generador/save', methods=["POST"])
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
@router.route('/censo/generadores')
def linkedList_generadores():
    r = requests.get(URL + 'generadores')
    data = r.json().get('data')
    return render_template('parts/generadores.html', generadores=data)

#RUTA PARA EDITAR UN GENERADOR ESPECIFICO
@router.route('/censo/generadores/edit/<id>')
def update_generadores_view(id):
    r = requests.get(URL + 'generadores/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/modificarGenerador.html', generador=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/generadores')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION DEL GENERADOR HACIA EL BACKEND
@router.route('/censo/generadores/update', methods=["POST"])
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
@router.route('/censo/generadores/delete', methods=["POST"])
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
@router.route('/censo/historial')
def view_historial():
    r = requests.get(URL + 'censoAPI/historial')
    data = r.json().get('data')
    return render_template('parts/historial.html', historial=data)
