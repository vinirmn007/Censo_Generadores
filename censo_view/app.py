from flask import Flask

def create_app():
    app = Flask(__name__, instance_relative_config=True)

    with app.app_context():
        from routes.familiasRoute import familiasRouter
        from routes.generadoresRoute import generadoresRouter

        app.register_blueprint(familiasRouter)
        app.register_blueprint(generadoresRouter)

    return app