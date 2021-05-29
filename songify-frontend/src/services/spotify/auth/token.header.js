export default function authHeader() {
    const token = JSON.parse(localStorage.getItem('token'));
  
    if (JWTToken) {
      return { Authorization: token };
    } else {
      return {};
    }
  }