package blackjack.domain.game

import blackjack.domain.deck.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringParserTest : StringSpec({
    "게임 참가자들 이름을 문자열로 받을 수 있다." {
        val deck = Deck.makeDeck()
        val playersFromString = StringParser.getPlayersFromString("pobi,jason", deck)
        playersFromString.size shouldBe 2
        playersFromString[0].name shouldBe "pobi"
        playersFromString[1].name shouldBe "jason"
    }
})
