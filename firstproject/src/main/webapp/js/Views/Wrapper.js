    const Wrapper = Mn.View.extend({
        template: _.template(`
        <div id="menu"></div>
        <div id="main"></div>
    `),
        regions: {
            mainRegion: '#main',
            menuRegion: '#menu'
        },
        onRender() {
            console.log("render Wrapper");
            this.showChildView('menuRegion', new Menu());
        }
    });
    let wrapper = new Wrapper();
