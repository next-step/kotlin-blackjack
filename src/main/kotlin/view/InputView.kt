package view

interface InputView {
    fun readPlayerNames(): List<String>
    fun askForAnotherCard(playerName: String): Boolean
}
