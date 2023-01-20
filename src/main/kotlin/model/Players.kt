package model
class Players {
    private val players = mutableListOf<Person>()

    fun add(player: Person): Boolean {
        return players.add(player)
    }

    fun get(): List<Person> {
        return players
    }
}
