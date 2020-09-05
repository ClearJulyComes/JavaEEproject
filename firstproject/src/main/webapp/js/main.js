const MyApp = Mn.Application.extend({
    region: '#wrap'
});

const myApp = new MyApp();

myApp.on('start', function() {
    Backbone.history.start({pushState: true, root: "/firstproject_war/"});
});
const wrapper = new Wrapper();
myApp.showView(wrapper);
const router = new Router();

$('body').on('click', 'a[href^="/"]', function (e) {
    e.preventDefault();
    router.navigate($(this).attr('href'), {trigger: true});
});

myApp.start();
