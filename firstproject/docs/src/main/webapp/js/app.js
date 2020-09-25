    const App = Mn.Application.extend({
        region: '#wrap',
        userName: '',
        onStart() {
            this.showView(wrapper);
            appRouter = new Router();
            Backbone.history.start();
        }
    });
    let myApp = new App();
    myApp.start();