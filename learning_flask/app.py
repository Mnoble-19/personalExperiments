from flask import Flask, url_for, render_template
from markupsafe import escape

app = Flask(__name__)


@app.route('/<name>')
def hello(name):
    return f'Hello, {escape(name)}!'

@app.route('/hello2/')
@app.route('/hello2/<name>')
def hello2(name=None):
    return render_template('hello.html', name=name)

# @app.route('/')
# def hello_world():  # put application's code here
#     return '<h1>Hello World!</h1>'

@app.route('/')
def index():
    return 'Index Page'


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        return do_the_login()
    else:
        return 'Please login'


@app.get
@app.route('/user/<username>')
def profile(username):
    return 'User: %s' % escape(username)


with app.test_request_context():
    print(url_for('index'))
    print(url_for('static', filename='style.css'))
    print(url_for('login'))  # allows you to test as if it was a real URL
    print(url_for('login', next='/'))
    print(url_for('profile', username='John Doe'))

if __name__ == '__main__':
    app.run()
