package blackjack.step2.domain

import blackjack.step2.domain.ScoreCalculator.BLACKJACK_SCORE

class GameManager(
    private val cardPicker: CardPicker,
) {
    fun pickFirstCards(playerNames: List<String>): List<PlayerCard> {
        return playerNames.map { playerName ->
            val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
            PlayerCard.of(playerName, cards)
        }
    }

    fun pickCardIfValid(playerCard: PlayerCard): PlayerCard {
        if (playerCard.calculateScore() >= BLACKJACK_SCORE) {
            println("${playerCard.playerName}의 점수가 $BLACKJACK_SCORE 이상입니다. 카드를 더 받을 수 없습니다.")
            return playerCard
        }

        while (true) {
            val card = cardPicker.pick()
            if (!playerCard.allCards.contains(card)) {
                return playerCard.pickCard(card)
            }
        }
    }

    companion object {
        private const val INITIAL_DEAL_COUNT = 2
    }
}
