package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.common.Money
import blackjack.domain.score.CardScore
import blackjack.domain.score.Match
import blackjack.domain.score.Score

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards.empty(),
    private val deck: Deck = Deck.default()
) : Participant(name, cards) {

    override fun isEnd(): Boolean {
        return cards.score() >= PLAY_END_STANDARD
    }

    fun giveCard(player: Player) {
        require(!player.isEnd()) {
            "플레이가 종료된 참가자입니다"
        }

        player.addCard(deck.draw())
    }

    fun addBaseCards(baseCount: Int) {
        repeat(baseCount) { addCard(deck.draw()) }
    }

    fun addCard() {
        while (!isEnd()) {
            addCard(deck.draw())
        }
    }

    fun profit(players: Players): Money {
        return players.profit(this).values
            .fold(Money.ZERO) { acc, playerProfit -> acc.minus(playerProfit) }
    }

    fun match(other: Player): Match {
        return when (cards.cardScore()) {
            CardScore.BUST -> matchBust(other)
            CardScore.BLACKJACK -> matchBlackJack(other)
            CardScore.NORMAL -> matchNormal(other)
        }
    }

    private fun matchBust(other: Player): Match {
        return if (other.cards.cardScore() == CardScore.BUST) {
            Match.WIN
        } else {
            Match.LOSE
        }
    }

    private fun matchBlackJack(other: Player): Match {
        return if (other.cards.cardScore() == CardScore.BLACKJACK) {
            Match.DRAW
        } else {
            Match.WIN
        }
    }

    private fun matchNormal(other: Player): Match {
        return when (other.cards.cardScore()) {
            CardScore.BUST -> Match.WIN
            CardScore.BLACKJACK -> Match.LOSE
            CardScore.NORMAL -> matchScore(other)
        }
    }

    private fun matchScore(other: Player): Match {
        return when {
            cards.score() > other.cards.score() -> Match.WIN
            cards.score() == other.cards.score() -> Match.DRAW
            else -> Match.LOSE
        }
    }

    companion object {
        private val PLAY_END_STANDARD = Score(17)
    }
}
