package blackJack.card.deck

import card.CardPack
import card.deck.GameDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameDeckTest {

    @Test
    fun `GameDeck을 생성하고, 카드 반환 요청을 했을 떄, 카드 반환과 카드 인덱스가 증가한다`() {
        // given : Cards를 생성한다.
        val gameDeck = GameDeck.create(CardPack.cards)

        // when : 카드 한장 반환을 요청한다.
        val actual = gameDeck.getCardWithIncrease()

        // then : 첫번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual).isEqualTo(CardPack.cards[0])

        // when : 카드 한장 반환을 요청한다.
        val actual2 = gameDeck.getCardWithIncrease()

        // then : 두번째 카드를 반환하고 카드 인덱스가 1 증가한다.
        assertThat(actual2).isEqualTo(CardPack.cards[1])
    }

    @Test
    fun `gameDeck을 생성하고, 카드덱을 추가할 때, gameDeck에 카드가 추가된다`() {
        // given : 게임 덱을 생성한다.
        val gameDeck = GameDeck.create(CardPack.cards)

        // when : 게임 덱을 추가한다.
        gameDeck.addCards(CardPack.cards)
        val actual = gameDeck.cardDeck.size

        // then : 카드 덱이 추가된다.
        val expect = CardPack.cards.size * 2
        assertThat(actual).isEqualTo(expect)
    }
}
