export default function authHeader() {
    const JWTToken = JSON.parse(localStorage.getItem('jwt'));
  
    if (JWTToken) {
      return { Authorization: 'Bearer ' + JWTToken };
    } else {
      return {};
    }
  }