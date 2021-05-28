// Cypress clears localstorage before each test is run, 
// but this would clear user data essentially logging the user out of the system,
// these two methods will be run before and after each test 
// to save localstorage data, and reinstate it before each test is run.
let LOCAL_STORAGE_MEMORY = {};

Cypress.Commands.add("saveLocalStorageCache", () => {
  Object.keys(localStorage).forEach(key => {
    LOCAL_STORAGE_MEMORY[key] = localStorage[key];
  });
});

Cypress.Commands.add("restoreLocalStorageCache", () => {
  Object.keys(LOCAL_STORAGE_MEMORY).forEach(key => {
    localStorage.setItem(key, LOCAL_STORAGE_MEMORY[key]);
  });
});

// command to manually clear local storage if needed
Cypress.Commands.add("clearLocalStorageCache", () => {
    localStorage.clear();
    LOCAL_STORAGE_MEMORY = {};
  });