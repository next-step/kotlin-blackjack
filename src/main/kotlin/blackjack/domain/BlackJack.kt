package blackjack.domain

const val WIN_SCORE = 21
const val CONTAIN_ACE_WIN_SCORE_1 = 110
const val CONTAIN_ACE_WIN_SCORE_2 = 120

class BlackJack(players: List<String>) {
    val players = players.map { Player(it) }
    private val cardDeck = CardDeck()

    fun raceInit(name: String) {
        repeat(2) {
            players.first { it.name == name }.addCard(cardDeck.pickCard())
        }
    }

    fun raceAvailable(name: String): Boolean {
        return players.first { it.name == name }.canRace
    }

    fun race(name: String) {
        val pickCard = cardDeck.pickCard()
        players.first { it.name == name }.addCard(pickCard).apply {
            checkState(name)
        }
    }

    fun winner(): List<String> {
        return players.filter { it.winner }.map { "${it.name}" }
    }

    private fun checkState(name: String) {
        players.filter { it.name == name }.forEach {
            val score = it.score
            if (score == WIN_SCORE || score == CONTAIN_ACE_WIN_SCORE_1 || score == CONTAIN_ACE_WIN_SCORE_2) {
                it.isWinner()
            } else if (score !in 0..WIN_SCORE && score !in 100..CONTAIN_ACE_WIN_SCORE_1 && score !in CONTAIN_ACE_WIN_SCORE_1..CONTAIN_ACE_WIN_SCORE_2) {
                it.cantRace()
            }
        }
    }
}
