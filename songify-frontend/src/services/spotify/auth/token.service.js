class TokenService{

    setToken(token){
        localStorage.setItem("token", JSON.stringify(token));
    }

    getToken(){
        return JSON.parse(localStorage.getItem("token"));
    }
}

export default new TokenService();