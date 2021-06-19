import authHeader from './auth/auth-header';
const CHAT_SERVICE = "http://localhost:8080";

const request = (options) => {
    const defaults = { headers: authHeader() };
    options = Object.assign({}, defaults, options);
  
    return fetch(options.url, options).then((response) =>
      response.json().then((json) => {
        if (!response.ok) {
          return Promise.reject(json);
        }
        return json;
      })
    );
  };

export function countNewMessages(senderId, recipientId) {
    return request({
      url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId + "/count",
      method: "GET",
    });
  }
  
  export function findChatMessages(senderId, recipientId) {
    return request({
      url: CHAT_SERVICE + "/messages/" + senderId + "/" + recipientId,
      method: "GET",
    });
  }
  
  export function findChatMessage(id) {
    return request({
      url: CHAT_SERVICE + "/messages/" + id,
      method: "GET",
    });
  }