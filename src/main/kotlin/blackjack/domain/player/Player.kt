package blackjack.domain.player

import blackjack.domain.RoundResult
import blackjack.domain.RoundResults
import blackjack.domain.Score
import blackjack.domain.Win
import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name
import blackjack.domain.player.vo.PlayerStatus

private const val READY_CARD_COUNT = 2

open class Player constructor(
    private var playerStatus: PlayerStatus,
    val cardsInHand: CardsInHand
) {
    var roundResults: RoundResults = RoundResults(winCount = 0, loseCount = 0, drawCount = 0)
        private set

    val name: Name
        get() = playerStatus.name
    open fun isStay(): Boolean = playerStatus.stay || cardsInHand.calculateScore() >= Score.BLACKJACK

    open fun ready(cardDeck: CardDeck) {
        repeat(READY_CARD_COUNT) { cardsInHand.add(cardDeck.draw()) }
    }

    open fun score(players: List<Player>): RoundResults {
        players.filterNot { it === this }
            .forEach {
                val playerScore = cardsInHand.calculateScore()
                roundResults = roundResults.add(RoundResult.valueOf(playerScore.compareTo(it.cardsInHand.calculateScore())))
            }
        return roundResults
    }


    fun hit(cardDeck: CardDeck) {
        require(!playerStatus.stay) { "Stay를 선언하였다면 카드를 뽑을수 없습니다." }
        require(cardsInHand.calculateScore() < Score.BLACKJACK) {
            "현재 점수가 ${Score.BLACKJACK.value} 보다 크거나 같으면 카드를 뽑지 못합니다."
        }

        cardsInHand.add(cardDeck.draw())
    }

    fun stay() {
        playerStatus = playerStatus.copy(stay = true)
    }

    fun winToDealerBust() {
        roundResults = roundResults.add(Win)
    }

    companion object {
        fun sit(name: Name): Player = Player(PlayerStatus(name), CardsInHand(emptyList()))
    }
}
