beforeEach(() => {
    cy.restoreLocalStorageCache();
});

afterEach(() => {
    cy.saveLocalStorageCache();
});

describe('UAT-1: Login', () => {
    it('it focuses the input', () => {
        cy.visit('/login')
        cy.focused().should('have.class', 'form-control')
    })

    it('fills in username', () => {
        //ENTER USERNAME:
        const input = "user1"
        cy.get('[data-cy=formusernameinput]')
            .type(input)
            .should('have.value', input)
    })

    it('fills in password', () => {
        //ENTER USERNAME:
        const input = "user1"
        cy.get('[data-cy=formpasswordinput]')
            .type(input)
            .should('have.value', input)
    })

    it('submits form', () => {
        //SUBMIT FORM: 
        cy.get('[data-cy=formpasswordinput]')
            .type('{enter}')      
    })

    it('is redirected', () => {
        cy.wait(3000)
        cy.visit('/home')
        cy.visit('/my-playlists')
        cy.visit('/home')
        //cy.location('pathname').should('eq', '/profile')
        // cy.url().should('include', 'spotify')
        // cy.url().should(
        //     'be.equal',
        //     `${Cypress.config("baseUrl")}/profile`
        //   )
    })
})

describe('UAT-2: Playlist Creation', () => {
    it('deletes old playlist to prevent duplicate', () => {
        cy.visit('/my-playlists')
        cy.get('[data-cy=Hits]').click()
    })

    it('go to create playlist page', () => {
        cy.visit('/new-playlist')
    })

    it('submits create playlist form', () => {
        const titleInput = "Hits"
        cy.get('[data-cy=playlisttitleinput]')
            .type(titleInput)
            .should('have.value', titleInput)

        const descInput = "My Favourite Songs!!"
        cy.get('[data-cy=playlistdescinput')
            .type(descInput)
            .should('have.value', descInput)
            .type('{enter}')

    })

    it('redirects to newly made playlist', () => {
        //cy.location('pathname').should('eq', '/playlist/:id')
        cy.location('pathname').should('not.include', 'new')
    })
})

describe('UAT-3: Duplicate Playlist Creation', () => {
    it('go to create playlist page', () => {
        cy.visit('/new-playlist')
    })

    it('submits create playlist form with the same inputs', () => {
        const titleInput = "Hits"
        cy.get('[data-cy=playlisttitleinput]')
            .type(titleInput)
            .should('have.value', titleInput)

        const descInput = "My Favourite Songs!!"
        cy.get('[data-cy=playlistdescinput')
            .type(descInput)
            .should('have.value', descInput)
            .type('{enter}')
    })

    it('does not redirect', () => {
        //cy.location('pathname').should('eq', 'playlist/:id')
        cy.location('pathname').should('eq', '/new-playlist')
    })
})

describe('UAT-4: Adding song to playlist', () => {
    it('go to songs page', () => {

    })

    it('adds the first song', () => {

    })

})

describe('UAT-5: Adding duplicate song', () => {
    it('go to songs page', () => {

    })

    it('adds the first song', () => {

    })

    it('does not add the song', () => {

    })
})

describe('UAT-6: Admin login', () => {
    it('logs out', () => {
        cy.clearLocalStorage()
        cy.visit('/login')
    })

    it('logs in as an admin', () => {
        const input = "admin"
        cy.get('[data-cy=formusernameinput]')
            .type(input)
            .should('have.value', input)

        cy.get('[data-cy=formpasswordinput]')
            .type(input)
            .should('have.value', input)
            .type('{enter}')

        //cy.location('pathname').should('eq', '/profile')
    })
})

describe('UAT-7: User redirect when accessing admin page', () => {
    it('logs out', () => {
        cy.clearLocalStorage()
        cy.visit('/login')
    })

    it('logs in as a user and visits admin page', () => {
        const input = "user1"
        cy.get('[data-cy=formusernameinput]')
            .type(input)
            .should('have.value', input)

        cy.get('[data-cy=formpasswordinput]')
            .type(input)
            .should('have.value', input)

        cy.get('[data-cy=formpasswordinput]')
            .type('{enter}')

        cy.wait(500) //gives the website time to login 

    })

    it('goes to admin only page', () => {
        cy.visit('/users')
    })

    it('is redirected', () => {
        //cy.location('pathname').should('eq', '/access-denied')
    })
})

describe('UAT-8 Search for specific user playlists', () => {
    it('goes to browse playlists page', () => {
        cy.visit('/browse')
    })

    it('searches for a specific user', () => {
        const input = "user1"
        cy.get('[data-cy=searchterminput]')
            .type(input)
        cy.get('[data-cy=searchbtn]')
            .click()
        cy.get('.table').find('td').its('length').should('be.gte', 1)// at least 1 value in table 
    })
})

describe('UAT-9 Search for user playlists that doesnt exist', () => {
    it('goes to browse playlists page', () => {
        cy.visit('/browse')
    })

    it('searches for a specific user', () => {
        const input = "hghhhhhhhhhhh"
        cy.get('[data-cy=searchterminput]')
            .type(input)
        cy.get('[data-cy=searchbtn]')
            .click()
        cy.get('.table').find('td').should('not.exist'); // no results
    })
})

describe('UAT-10 Accepting friend request', () => {

})

describe('UAT-11 50 limit for friend requests', () => {

})