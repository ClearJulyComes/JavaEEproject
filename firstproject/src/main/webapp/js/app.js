const App = Mn.Application.extend({
    region: '#wrap',
    userName: '',
    onStart() {
        this.showView(new Wrapper());
        appRouter = new Router();
        Backbone.history.start();
    }
});

const myApp = new App();
myApp.start();