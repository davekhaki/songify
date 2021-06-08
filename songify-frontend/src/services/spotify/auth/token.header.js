import TokenService from "./token.service";

export default function spotifyHeader() {
    const token = TokenService.getToken()
    
    if (token) {
      return { Authorization: "Bearer " + token };
    } else {
      return {};
    }
  }