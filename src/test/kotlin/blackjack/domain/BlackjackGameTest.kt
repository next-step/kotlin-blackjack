package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    "블랙잭 게임이 진행중인 상태인지 파악한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.ACE),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                false
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.NINE),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                true
            ),
        ).forEach { (cards, expected) ->
            // given
            val blackjackGame = BlackjackGame(
                deck = Deck.createOf(),
                participants = Participants(
                    listOf(
                        Player(
                            "김경록",
                            Cards(cards),
                        )
                    )
                ),
            )

            // when
            val actual = blackjackGame.isPlayerTurn()

            // then
            actual shouldBe expected
        }
    }

    "대기중인 플레이어에게 카드를 뽑을지 말지 묻는다." {
        // given
        val blackjackGame = BlackjackGame(
            deck = Deck(mutableListOf(Card(Suit.CLOVER, Face.THREE))),
            participants = Participants(
                listOf(
                    Player(
                        "김경록",
                        Cards(
                            mutableListOf(
                                Card(Suit.CLOVER, Face.NINE),
                                Card(Suit.CLOVER, Face.TEN),
                            )
                        ),
                    )
                )
            ),
        )

        // when
        blackjackGame.askDrawToCurrentTurnPlayer(true)

        // then
        blackjackGame.isPlayerTurn() shouldBe false
    }
})
