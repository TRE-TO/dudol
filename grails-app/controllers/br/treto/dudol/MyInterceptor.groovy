package br.treto.dudol


class MyInterceptor {

    public MyInterceptor(){
        matchAll().excludes(controller: 'login')
                  .excludes(controller: 'email')
    }
    boolean before() {
        if(session["logado"] && session["logado"] == true)
            return true
        else{
            session.invalidate()
            redirect(uri:"/login")
            return  false
        }


    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
