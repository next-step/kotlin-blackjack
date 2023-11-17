package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드덱에서 카드를 넣는 만큼 사이즈가 나온다`() {
        val cardDeck = CardDeck(cards = listOf(PlayingCard(Suits.DIAMOND, CardNumber.NINE)))
        assertThat(cardDeck.cardDeck.size).isEqualTo(1)
    }

    @Test
    fun `카드덱에서 카드를 드로우하면 한장이 빠진다`() {
        val cardDeck = CardDeck()
        assertThat(cardDeck.cardDeck.size).isEqualTo(52)
        cardDeck.drawCard()
        assertThat(cardDeck.cardDeck.size).isEqualTo(51)
        cardDeck.drawCard()
        assertThat(cardDeck.cardDeck.size).isEqualTo(50)
    }

    @Test
    fun `셔플 전략에 따라 맨위에 나오는 카드가 다르다`() {
        val cardDeck = CardDeck(
            shuffleStrategy = object : ShuffleStrategy {
                override fun shuffle(cards: List<PlayingCard>): List<PlayingCard> {
                    return cards.sortedWith(
                        compareBy({ it.suit.ordinal }, { it.number.ordinal })
                    )
                }
            }
        )

        assertThat(cardDeck.drawCard()).isEqualTo(PlayingCard(Suits.DIAMOND, CardNumber.KING))

        val cardDeck2 = CardDeck(
            shuffleStrategy = object : ShuffleStrategy {
                override fun shuffle(cards: List<PlayingCard>): List<PlayingCard> {
                    return cards.sortedWith(compareByDescending<PlayingCard> { it.suit.ordinal }.thenByDescending { it.number.ordinal })
                }
            }
        )

        assertThat(cardDeck2.drawCard()).isEqualTo(PlayingCard(Suits.CLOVER, CardNumber.ACE))
    }

    @Test
    fun `카드덱에서 초반 카드를 나눠줄 때 2개의 카드를 준다`() {
        val cardDeck = CardDeck()
        assertThat(cardDeck.drawIntialCards().size).isEqualTo(2)
    }
}
