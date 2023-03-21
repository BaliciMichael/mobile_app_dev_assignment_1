package ie.setu.assignment1.models

import android.net.Uri
import org.junit.Test
import org.junit.Assert.*

class PlayerMemStoreTest {

    private val player1 = Player(1, "John", "Smith",23,"USA",false,0,"Warriors","Center")
    private val player2 =  Player(2, "Mary", "Lou",22,"Croatia",true,3,"Nuggets","Point Guard")
    private val player3 = Player(3, "Michael", "Smith",21,"Belgium",false,0,"Grizzlies","Center")
    private val store = PlayerMemStore()

    @Test
    fun create() {
        store.create(player1)
        assertEquals(1, store.findAll().size)
        assertEquals(player1, store.findById(1))
    }

    @Test
    fun update() {
        store.create(player2)
        store.update(2,"Mary","Jane",22,"Croatia",true,3,"Nuggets","Point Guard", Uri.EMPTY)
        assertEquals("Jane", store.findById(2)?.last)
        assertEquals(1, store.findAll().size)
    }

    @Test
    fun findById() {
        store.create(player3)
        assertEquals(player3, store.findById(3))
        assertNull(store.findById(4))
    }

    @Test
    fun findAll() {
        store.create(player1)
        store.create(player2)
        store.create(player3)
        val players = store.findAll()
        assertEquals(3, players.size)
        assertTrue(players.contains(player1))
        assertTrue(players.contains(player2))
        assertTrue(players.contains(player3))
    }

    @Test
    fun removePlayer() {
        store.create(player1)
        store.create(player2)
        store.removePlayer(2)
        assertEquals(1, store.findAll().size)
        assertNull(store.findById(2))
    }

    @Test
    fun logAll() {
        store.create(player1)
        store.create(player2)
        store.logAll()
        // This test just verifies that logAll() does not throw an exception
    }
}
