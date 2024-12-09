package blackjack.step2.domain

import blackjack.step2.domain.ScoreCalculator.BLACKJACK_SCORE

class GameManager(
    private val cardPicker: CardPicker,
) {
    fun pickFirstDealerCards(): Dealer {
        val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
        return Dealer.of(cards)
    }

    fun pickFirstPlayersCards(playerNames: List<String>): List<Player> {
        return playerNames.map { playerName ->
            val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
            Player.of(playerName, cards)
        }
    }

    fun pickPlayerCardIfValid(participant: Participant): Participant {
        if (participant.calculateScore() >= BLACKJACK_SCORE) {
            println("${participant.name}의 점수가 $BLACKJACK_SCORE 이상입니다. 카드를 더 받을 수 없습니다.")
            return participant
        }

        while (true) {
            val card = cardPicker.pick()
            if (!participant.cards.all.contains(card)) {
                return participant.pickCard(card)
            }
        }
    }

    fun pickDealerCardIfValid(dealer: Dealer): Dealer {
        if (dealer.calculateScore() > 17) {
            return dealer
        }

        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        while (true) {
            val card = cardPicker.pick()
            if (!dealer.cards.all.contains(card)) {
                return dealer.pickCard(card)
            }
        }
    }

    companion object {
        private const val INITIAL_DEAL_COUNT = 2
    }
}
