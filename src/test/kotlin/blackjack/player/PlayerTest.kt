package blackjack.player

import blackjack.card.Card
import blackjack.card.CardFixture
import blackjack.card.Cards
import blackjack.card.Rank
import blackjack.card.Suit
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어의 카드의 합이 21 미만이면 카드를 더 받을 수 있다`(player: Player) {
        player.isHitCard() shouldBe true
    }

    @Test
    fun `플레이어의 카드의 합이 21 이상이면 카드를 더 받을 수 없다`() {
        val player =
            Player.fromNameAndCards(
                name = NAME,
                cards =
                    listOf(
                        CardFixture.generateTestCard(rank = Rank.FIVE),
                        CardFixture.generateTestCard(rank = Rank.SIX),
                        CardFixture.generateTestCard(rank = Rank.TEN),
                    ),
            )
        player.isHitCard() shouldBe false
    }

    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어의 카드가 추가되는지 확인한다()`(player: Player) {
        val newCard = Card(rank = Rank.FIVE, suit = Suit.DIAMOND)
        val result = player.hitCard(newCard)
        result.cards.cards shouldContainAll player.cards.cards + newCard
    }

    companion object {
        @JvmStatic
        private fun generateTestPlayer() =
            listOf(
                Player.fromNameAndCards(
                    name = NAME,
                    cards = List(size = 2) { CardFixture.generateTestCard(rank = Rank.SIX) },
                ),
            )

        private const val NAME = "nooblette"

        private fun Player.Companion.fromNameAndCards(
            name: String,
            cards: List<Card>,
        ): Player = Player(name = name, cards = Cards(cards = cards))
    }
}
