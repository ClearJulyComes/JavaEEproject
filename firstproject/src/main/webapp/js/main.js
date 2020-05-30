const MyRegion = Mn.Region.extend({ el: $('#myArea') });
const mainRegion = new MyRegion();

const Auth = Mn.View.extend({
    template: _.template(`
        <div> 
            <form class="auth">
    <table>
    <tr>
    <td>Login</td>
    <td colspan="2">
    <input type="text" name="login" placeholder="login">
    </td>
    </tr>
    <tr>
    <td>Password</td>
    <td colspan="2">
    <input type="text" name="password" placeholder="password">
    </td>
    </tr>
    <tr>
    <td>
    </td>
    <td></td>
    <td>
    <button id="authButton" type="submit">Ok</button>
    </td>
    </tr>
    </table>
    </form>
    <button id="toRegButton">Registration</button>
        </div>`
    ),
    events: {
        'click @ui.toReg' : 'toRegistration',
        'click @ui.auth' : 'authorization',
    }
    ,
    ui: {
        toReg: '#toRegButton',
        auth: '#authButton'
    }
    ,
    toRegistration(){
        console.log("Hey");
        mainRegion.show(new Reg());
    }

});

const Reg = Mn.View.extend({
    template: _.template(`
        <div>
            <form class="reg">
                <table>
                    <tr>
                        <td>Login</td>
                        <td colspan="2">
                            <input type="text" name="login" placeholder="login">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td colspan="2">
                            <input type="text" name="password" placeholder="password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td></td>
                        <td>
                            <button id="regButton" type="submit">Ok</button>
                        </td>
                    </tr>
                </table>
            </form>
            <button id="toAuthButton">Authorization</button>
        </div>`
    )  ,
    events: {
        'click @ui.toAuth' : 'toAuthorization',
        'click @ui.reg' : 'registration',
    }
    ,
    ui: {
        toAuth: '#toAuthButton',
        reg: '#regButton'
    }
    ,
    toAuthorization(){
        console.dir("fuck you bitch!!!!");
        mainRegion.show(new Auth());
    },

});

mainRegion.show(new Auth());