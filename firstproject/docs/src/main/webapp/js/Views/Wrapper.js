    const Wrapper = Mn.View.extend({
        template: _.template(`
        <div class="container vh-20">
            <nav id="menu" class="navbar navbar-expand-sm bg-dark text-white navbar-dark justify-content-center"></nav>
        </div>
        <div class="container-fluid content">
            <div class="row">
                <div class="col-sm-2"></div>
                <div id="main" class="col-sm-8"></div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div class="bg-dark p-2 fixed-bottom h-10" id="myBottom"></div>
            `),
        regions: {
            mainRegion: '#main',
            menuRegion: '#menu'
        },
        onRender() {
        }
    });
    let wrapper = new Wrapper();
