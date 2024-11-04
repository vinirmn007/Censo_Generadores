from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

URL_LINKEDLISTS = 'http://localhost:4000/myapp/'
URL_ARRAYS = 'http://localhost:8080/myapp/'

@router.route('/')
def home():
    return render_template('home.html')

#RUTAS PARA EL SISTEMA EN LINKED LIST

#MUESTRA TODAS LAS FAMILIAS CON GENERADOR
@router.route('/linkedList/')
def linkedList_censo():
    r = requests.get(URL_LINKEDLISTS + 'censoAPI')
    data = r.json().get('data')
    return render_template('parts/censo.html', familiasCG=data)

#MUESTRA TODAS LAS FAMILIAS
@router.route('/linkedList/familias')
def linkedList_familias():
    r = requests.get(URL_LINKEDLISTS + 'familias')
    data = r.json().get('data')
    return render_template('parts/familias.html', familias=data)

#FORMULARIO DE REGISTRO PARA LAS FAMILIAS
@router.route('/linkedList/familias/registro')
def registro_familias():
    return render_template('forms/familia/registroFamilias.html')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO HACIA EL BACKEND
@router.route('/linkedList/familias/registro/save', methods=["POST"])
def save_familias():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL_LINKEDLISTS + 'familias/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash('No se ha podido guardar', category='error')
        return redirect('/linkedList/familias')
    
#RUTA PARA EDITAR UNA FAMILIA ESPECIFICA
@router.route('/linkedList/familias/edit/<id>')
def update_familias_view(id):
    r = requests.get(URL_LINKEDLISTS + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/familia/modificarFamilia.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/familias')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION HACIA EL BACKEND
@router.route('/linkedList/familias/update', methods=["POST"])
def update_familia():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"], "apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL_LINKEDLISTS + 'familias/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash('No se ha podido actualizar', category='error')
        return redirect('/linkedList/familias')

#RUTA PARA ELIMINAR UNA FAMILIA ESPECIFICA
@router.route('/linkedList/familias/delete', methods=["POST"])
def delete_familias():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"]}
    r = requests.post(URL_LINKEDLISTS + 'familias/delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Familia eliminada exitosamente', category='info')
    else:
        data = r.json()
        flash(data["data"], category='error')
    return redirect('/linkedList/familias')

#RUTA PARA REGISTRAR UN GENERADOR POR FAMILIA ESPECIFICA
@router.route('/linkedList/familias/generador/register/<id>')
def register_generador_view(id):
    r = requests.get(URL_LINKEDLISTS + 'familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/registroGenerador.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/familias')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE REGISTRO DE GENERADOR HACIA EL BACKEND
@router.route('/linkedList/familias/generador/save', methods=["POST"])
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
    r = requests.post(URL_LINKEDLISTS + 'generadores/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/familias')

#MUESTRA TODOS LOS GENERADORES
@router.route('/linkedList/generadores')
def linkedList_generadores():
    r = requests.get(URL_LINKEDLISTS + 'generadores')
    data = r.json().get('data')
    return render_template('parts/generadores.html', generadores=data)

#RUTA PARA EDITAR UN GENERADOR ESPECIFICO
@router.route('/linkedList/generadores/edit/<id>')
def update_generadores_view(id):
    r = requests.get(URL_LINKEDLISTS + 'generadores/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/generador/modificarGenerador.html', generador=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/generadores')

#RUTA QUE ENVIA LOS DATOS DEL FORMULARIO DE EDICION DEL GENERADOR HACIA EL BACKEND
@router.route('/linkedList/generadores/update', methods=["POST"])
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
    r = requests.post(URL_LINKEDLISTS + 'generadores/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
        return redirect('/linkedList/generadores')
    else:
        flash('No se ha podido actualizar ' + data["data"], category='error')
        return redirect('/linkedList/generadores')
    
#RUTA PARA ELIMINAR UN GENERADOR ESPECIFICO
@router.route('/linkedList/generadores/delete', methods=["POST"])
def delete_generadores():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"]}
    r = requests.post(URL_LINKEDLISTS + 'generadores/delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Generador eliminado exitosamente', category='info')
    else:
        data = r.json()
        flash(data["data"], category='error')
    return redirect('/linkedList/generadores')

#RUTA PARA EL HISTORIAL
@router.route('/linkedList/historial')
def view_historial():
    r = requests.get(URL_LINKEDLISTS + 'censoAPI/historial')
    data = r.json().get('data')
    return render_template('parts/historial.html', historial=data)

#RUTAS PARA EL SISTEMA EN ARRAYS

@router.route('/arrays/')
def arrays_censo():
    return render_template('censo.html')

@router.route('/arrays/familias')
def arrays_familias():
    return render_template('familias.html')

@router.route('/arrays/generadores')
def arrays_generadores():
    return render_template('generadores.html')