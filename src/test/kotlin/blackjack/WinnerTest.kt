package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinnerTest {

    @Test
    fun `점수 계산하기`() {
        val player = Player("moshi").apply {
            requestDeck(Deck(Card.Type.TWO, Card.Shape.HEART))
            requestDeck(Deck(Card.Type.EIGHT, Card.Shape.SPADE))
            requestDeck(Deck(Card.Type.ACE, Card.Shape.CLOVER))
        }

        assertThat(Winner.calculateRank(player.myReceivedDeck)).isEqualTo(21)
    }
}

object Winner {
    private const val WINNING_RANK = 21

    fun calculateRank(decks: List<Deck>): Int {
        val deckTypesExceptAceType = decks.map { it.getDeckType() }
            .filterNot { it.nickName == Card.Type.ACE.nickName }
        val aceCountInDeck = decks.map { it.getDeckType() }.filter { it.nickName == Card.Type.ACE.nickName }.size
        val rank = deckTypesExceptAceType.map { it.points.first() }.reduce { acc, i -> acc + i }

        if (deckTypesExceptAceType.size == decks.size) return rank

        return if (rank + aceCountInDeck * Card.Type.ACE.points.last() <= WINNING_RANK) {
            rank + aceCountInDeck * Card.Type.ACE.points.last()
        } else {
            rank + aceCountInDeck * Card.Type.ACE.points.first()
        }
    }
}
