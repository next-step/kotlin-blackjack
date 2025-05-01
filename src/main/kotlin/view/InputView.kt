package view

class InputView {
    fun getPlayerNames(): List<String> {
        println(GUIDE_ENTER_PLAYER_NAMES)
        return readln().split(",").map { it.trim() }
    }

    fun getResponse(name: String): Boolean {
        println(GUIDE_ENTER_RESPONSE.format(name))
        val response = readln().trim()
        if (response == "y") return true
        if (response == "n") return false
        return getResponse(name)
    }

    companion object {
        private const val GUIDE_ENTER_PLAYER_NAMES = "Enter the names of the players (comma-separated):"
        private const val GUIDE_ENTER_RESPONSE = "Would %s like to draw another card? (y for yes, n for no)"
    }
}
