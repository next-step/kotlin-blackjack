package blackjack.domain.participant

import blackjack.domain.GameResult

class Players private constructor(
    val values: List<Player>
) {

    init {
        validateCount()
    }

    private fun validateCount() {
        require(values.size >= MIN_PLAYER_COUNT) {
            "플레이어는 최소 ${MIN_PLAYER_COUNT}명 이상이어야 합니다."
        }
    }

    fun getGameResult(dealer: Dealer): GameResult {
        val results = values.map { player ->
            player.getResult(dealer.calculateScore())
        }
        return GameResult(dealer.name(), results)
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 2

        fun create(playerNames: List<String>, dealer: Dealer): Players {
            return playerNames.map {
                Player(
                    name = ParticipantName(it),
                    firstCard = dealer.handCard(),
                    secondCard = dealer.handCard()
                )
            }.let { Players(it) }
        }
    }
}

fun Players.forEach(action: (Player) -> Unit) {
    values.forEach(action)
}
