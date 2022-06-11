package blackjack.view.input

class StubInput(private val players: String, private val hasNextCard: String) : Input {
    companion object {
        fun ofPlayers(players: String): StubInput {
            return StubInput(players, "")
        }

        fun ofNextCard(hasNextCard: String): StubInput {
            return StubInput("", hasNextCard)
        }
    }

    override fun readPlayers(): String {
        return this.players
    }

    override fun readHasNextCard(): String {
        return this.hasNextCard
    }
}
