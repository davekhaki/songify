export default function authHeader() {
    const header = JSON.parse(localStorage.getItem('bearer'));

    if (header) {
      return { Authorization: header.Authorization };
    } else {
      return {};
    }
  }