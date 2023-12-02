package player

object PlayerFactory {

    private const val DELIMITERS = ','

    fun createPlayerList(playerNames: String): List<Player> {
        val playerNameList = splitInputData(playerNames)
        val playerList = mutableListOf<Player>()
        playerNameList.forEach { name ->
            playerList.add(Player(name))
        }
        return playerList
    }

    private fun splitInputData(inputData: String): List<String> {
        return inputData.split(DELIMITERS).map { it.trim() }.filter { it.isNotBlank() }
    }
}
