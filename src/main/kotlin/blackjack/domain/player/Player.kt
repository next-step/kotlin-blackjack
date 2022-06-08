package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Cards.Companion.WINNING_SCORE

class Player(
    val name: String,
    cards: Cards = Cards.empty(),
    var stay: Boolean = false
) {
    private val _cards: Cards = cards
    val cards: List<Card> get() = _cards.cards.toList()
    val score: Score get() = _cards.score()

    fun hit(card: Card) {
        check(isNotExceedWinningScore()) {
            "가지고 있는 카드의 합이 $WINNING_SCORE 를 넘으면 카드를 추가할 수 없습니다"
        }

        check(!stay) {
            "카드를 받지 않기로 하면 카드를 추가할 수 없습니다"
        }

        _cards.add(card)
    }

    fun stay() {
        stay = true
    }

    fun hittable(): Boolean {
        return !stay && isNotExceedWinningScore()
    }

    private fun isNotExceedWinningScore() = _cards.score() <= WINNING_SCORE

    companion object {
        fun ofList(value: String, delimiter: String = ","): List<Player> {
            val names = value.split(delimiter)
            return names.map(::Player)
        }
    }
}
