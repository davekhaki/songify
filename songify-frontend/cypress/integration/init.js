describe('Cypress', () => {
    it('Is working', () => {
        expect(true).to.equal(true);
    })

    it('Go to the app', () => {
        cy.visit('/')
    })
})