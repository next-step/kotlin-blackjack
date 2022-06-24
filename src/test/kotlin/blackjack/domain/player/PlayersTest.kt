package blackjack.domain.player

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({
    "플레이어의 이름이 중복되면 예외를 발생한다." {
        // given
        val players = listOf(
            Player("김경록"),
            Player("김경록"),
        )

        // when // then
        shouldThrowExactly<IllegalArgumentException> { Players(players) }
    }

    "첫 턴에 모든 플레이어들이 카드를 뽑는다." {
        // given
        val players = Players("김경록", "로키")
        val deck = Deck(
            listOf(
                Card(Suit.SPADE, Face.TWO),
                Card(Suit.CLOVER, Face.THREE),
                Card(Suit.CLOVER, Face.FOUR),
                Card(Suit.DIAMOND, Face.FIVE),
            )
        )

        // when // then
        shouldNotThrowAny { players.drawInitCards(deck) }
    }
})
