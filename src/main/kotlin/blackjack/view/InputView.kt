package blackjack.view

interface InputView {
    fun getPlayerNames(): List<String>

    fun getPlayerCommand(playerName: String): InputViewCommand
}

sealed interface InputViewCommand {
    object Yes : InputViewCommand
    object No: InputViewCommand

    companion object {
        private val validYesCommands: Set<String> = setOf("y")
        fun get(commandString: String) : InputViewCommand {
            return if (commandString in validYesCommands) Yes else No
        }
    }
}
