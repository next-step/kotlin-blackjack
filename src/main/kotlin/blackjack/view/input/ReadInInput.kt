package blackjack.view.input

class ReadInInput : Input {

    override fun readPlayers(): String {
        return readln()
    }

    override fun readHasNextCard(): String {
        return readln()
    }
}
