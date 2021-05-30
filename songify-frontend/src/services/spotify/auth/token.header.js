export default function spotifyHeader() {
    const token = JSON.parse(localStorage.getItem('token'));
    
    if (token) {
      return { Authorization: token };
    } else {
      return {};
    }
  }