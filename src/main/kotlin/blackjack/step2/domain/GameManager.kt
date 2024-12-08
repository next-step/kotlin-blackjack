package blackjack.step2.domain

import blackjack.step2.domain.ScoreCalculator.BLACKJACK_SCORE

class GameManager(
    private val cardPicker: CardPicker,
) {
    fun pickFirstCards(playerNames: List<String>): List<Player> {
        return playerNames.map { playerName ->
            val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
            Player.of(playerName, cards)
        }
    }

    fun pickCardIfValid(player: Player): Player {
        if (player.calculateScore() >= BLACKJACK_SCORE) {
            println("${player.playerName}의 점수가 $BLACKJACK_SCORE 이상입니다. 카드를 더 받을 수 없습니다.")
            return player
        }

        while (true) {
            val card = cardPicker.pick()
            if (!player.allCards.contains(card)) {
                return player.pickCard(card)
            }
        }
    }

    companion object {
        private const val INITIAL_DEAL_COUNT = 2
    }
}
