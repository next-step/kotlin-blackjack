package view

class InputView {
    fun getPlayerNames(): List<String> {
        println(GUIDE_ENTER_PLAYER_NAMES)
        return readln().split(",").map { it.trim() }
    }

    companion object {
        private const val GUIDE_ENTER_PLAYER_NAMES = "Enter the names of the players (comma-separated):"
    }
}
