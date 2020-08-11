package blackjack.domain

const val WIN_SCORE = 21
const val CONTAIN_ACE_WIN_SCORE_1 = 120
const val CONTAIN_ACE_WIN_SCORE_2 = 110

class BlackJack(players: List<String>) {
    private val players = players.map { Player(it) }
    private val cardDeck = CardDeck()

    fun raceInit(name: String) {
        players.onEach {
            repeat(2) {
                players.first { it.name == name }.addScore(cardDeck.pickCard())
            }
        }
    }

    fun raceAvailable(name: String): Boolean {
        return players.first { it.name == name }.canRace
    }

    fun race(name: String, race: Boolean) {
        val pickCard = cardDeck.pickCard()
        if (race) {
            players.first { it.name == name }.addScore(pickCard).apply {
                checkState()
            }
        }
    }

    fun winner(): List<Player> {
        return players.filter { it.winner }
    }

    private fun checkState() {
        players.forEach {
            when (it.score) {
                WIN_SCORE, CONTAIN_ACE_WIN_SCORE_1, CONTAIN_ACE_WIN_SCORE_2 -> it.isWinner()
                else -> it.cantRace()
            }
        }
    }
}
