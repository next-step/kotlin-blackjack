package blackjack.domain.game

import blackjack.domain.card.*
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerGroup
import blackjack.domain.player.PlayerScore
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

val mockDealer = object : CardDealer {
    override fun selectCard(): Card {
        return Card.of(CardKind.DIAMOND, CardNumber.JACK)
    }

    override fun selectCard(count: Int): CardSet {
        return if (count == 1) {
            CardSet.of(Card.of(CardKind.DIAMOND, CardNumber.JACK))
        } else {
            CardSet.of(Card.of(CardKind.DIAMOND, CardNumber.JACK), Card.of(CardKind.DIAMOND, CardNumber.QUEEN))
        }
    }
}

class BlackJackGameTest : FunSpec({
    test("블랙잭 카드 게임이 시작하면 2장의 카드를 플레이어게 지급한다") {
        val playerGroup = PlayerGroup(
            listOf(Player("jack"), Player("john"))
        )
        val blackJackGame = BlackJackGame(mockDealer)

        val actual = blackJackGame.dealCardToEveryOne(playerGroup)
        actual[0].cardSet.size shouldBe 2
    }

    test("카드 합이 21이 초과하는 인원에게 카드를 지급할 수 없다.") {
        val playerGroup = PlayerGroup(
            listOf(
                Player(
                    "jack",
                    CardSet.of(
                        Card(CardKind.CLOVER, CardNumber.ACE),
                        Card(CardKind.CLOVER, CardNumber.QUEEN),
                        Card(CardKind.HEART, CardNumber.KING)
                    )
                )
            )
        )
        val blackJackGame = BlackJackGame(mockDealer)

        val actual = blackJackGame.dealCardTo(playerGroup, playerGroup[0])
        actual[0].cardSet.size shouldBe 3
    }

    test("카드 합이 21 이하의 인원에게 카드를 지급한다.") {
        val playerGroup = PlayerGroup(
            listOf(
                Player(
                    "jack",
                    CardSet.of(Card(CardKind.CLOVER, CardNumber.QUEEN), Card(CardKind.HEART, CardNumber.KING))
                )
            )
        )
        val blackJackGame = BlackJackGame(mockDealer)

        val actual = blackJackGame.dealCardTo(playerGroup, playerGroup[0])
        actual[0].cardSet.size shouldBe 3

    }

    test("블랙잭 게임이 끝나면 누가 어떤 카드를 받았었는지, 점수가 얼마인지 계산한다.") {
        val playerGroup = PlayerGroup(
            listOf(
                Player(
                    "spring",
                    CardSet.of(Card(CardKind.CLOVER, CardNumber.KING), Card(CardKind.HEART, CardNumber.SIX))
                ),
                Player(
                    "john",
                    CardSet.of(Card(CardKind.DIAMOND, CardNumber.KING), Card(CardKind.SPADE, CardNumber.SEVEN))
                )
            )
        )
        val blackJackGameResult = BlackJackGame().end(playerGroup)
        blackJackGameResult shouldBe BlackJackGameResult(
            listOf(
                PlayerScore(playerGroup[0], CardScore(16)),
                PlayerScore(playerGroup[1], CardScore(17))
            )
        )
    }
})
