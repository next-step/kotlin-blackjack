package blackjack.domain

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GamerTest {

    @DisplayName("참여자는 이름을 가지고 있다")
    @Test
    fun hasName() {
        val player = Gamer(
            "hjw", Cards(
                listOf(
                    Card(CardNumber.Seven, Suit.Heart),
                    Card(CardNumber.Seven, Suit.Diamond),
                    Card(CardNumber.Seven, Suit.Spade)
                )
            )
        )

        player.name shouldBe "hjw"
    }

    @DisplayName("플레이어는 하트 7 카드를 받아서 가지고 있다")
    @Test
    fun receiveCard() {
        val player = Gamer(
            "hjw", Cards(
                listOf(
                    Card(CardNumber.Seven, Suit.Heart),
                    Card(CardNumber.Seven, Suit.Diamond),
                    Card(CardNumber.Seven, Suit.Spade)
                )
            )
        )
        val card = Card(CardNumber.Seven, Suit.Heart)

        player.receive(card)

        player.myCards.getCardList() shouldContain card
    }

    @DisplayName("플레이어는 카드 합계가 21 미만이면 카드를 뽑을 수 있다")
    @Test
    internal fun canDraw() {
        val player = Gamer(
            name = "hjw",
            myCards = Cards(
                listOf(
                    Card(CardNumber.Seven, Suit.Heart),
                    Card(CardNumber.Seven, Suit.Diamond)
                )
            )
        )

        player.canDraw() shouldBe true
    }

    @DisplayName("플레이어는 카드 합계가 21이 넘으면 카드를 뽑을 수 없다")
    @Test
    internal fun canNotDraw() {
        val player = Gamer(
            name = "hjw",
            myCards = Cards(
                listOf(
                    Card(CardNumber.Seven, Suit.Heart),
                    Card(CardNumber.Seven, Suit.Diamond),
                    Card(CardNumber.Seven, Suit.Spade)
                )
            )
        )

        player.canDraw() shouldBe false
    }
}
