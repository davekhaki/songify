import TokenService from "./token.service";

export default function spotifyHeader() {
    const token = TokenService.getCurrentToken()
    
    if (token) {
      return { Authorization: token };
    } else {
      return {};
    }
  }