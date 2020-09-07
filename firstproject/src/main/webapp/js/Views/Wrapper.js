const Wrapper = Mn.View.extend({
    template: _.template(`
        <div id="main"></div>
        <div id="firstPart"></div>
    `),
    regions:{
        mainRegion: '#main',
        menuRegion: '#firstPart'
    },
    onRender() {
        console.log("render Wrapper");
        this.showChildView('menuRegion', new Menu());
        //work.navigate('registration');
    }
});
