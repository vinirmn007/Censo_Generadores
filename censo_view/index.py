from app import create_app
app = create_app()
if __name__ == '__main__':
    app.secret_key = 'censo_generador'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(debug=True, host='0.0.0.0', port="3000")